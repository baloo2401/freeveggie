package org.mdubois.freeveggie.framework.msg;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.framework.msg.validator.EmailConstraintValidator;
import org.mdubois.freeveggie.framework.msg.validator.FalseConstraintValidator;
import org.mdubois.freeveggie.framework.msg.validator.IntegerValueConstraintValidator;
import org.mdubois.freeveggie.framework.msg.validator.LengthConstraintValidator;
import org.mdubois.freeveggie.framework.msg.validator.MessageConstraintValidator;
import org.mdubois.freeveggie.framework.msg.validator.NotBlankConstraintValidator;
import org.mdubois.freeveggie.framework.msg.validator.NotEmptyConstraintValidator;
import org.mdubois.freeveggie.framework.msg.validator.RequiredConstraintValidator;
import org.mdubois.freeveggie.framework.msg.validator.StringValueConstraintValidator;
import org.mdubois.freeveggie.framework.msg.validator.TrueConstraintValidator;
import org.mdubois.freeveggie.framework.msg.validator.ZipCodeConstraintValidator;
// </editor-fold>

/**
 * Object that every business method have to use as parameters.
 *
 * @author Mickael Dubois
 */
public abstract class Message extends ExtendedSerializable {

    // <editor-fold defaultstate="collapsed" desc="Exception message constants">
    /**
     * Message for empty string message.
     */
    private static final String INPUT_MESSAGE_NOT_VALID = "Input message is not valid. %s doesn't respect the object resctriction";
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Validation tools concstants">
    /**
     * The reflection class history.
     */
    private static final Map<Class<?>, Map<Method, List<MessageConstraintValidator<? extends Annotation>>>> FIELD_MAP = new HashMap<Class<?>, Map<Method, List<MessageConstraintValidator<? extends Annotation>>>>();
    /**
     * Map of annotation restriction and message constraint validator
     */
    private static final Map<Class<? extends Annotation>, Class<? extends MessageConstraintValidator<? extends Annotation>>> MESSAGE_CONSTRAINT_VALIDATOR = new HashMap<Class<? extends Annotation>, Class<? extends MessageConstraintValidator<? extends Annotation>>>();

    static {
        MESSAGE_CONSTRAINT_VALIDATOR.put(Email.class, EmailConstraintValidator.class);
        MESSAGE_CONSTRAINT_VALIDATOR.put(Length.class, LengthConstraintValidator.class);
        MESSAGE_CONSTRAINT_VALIDATOR.put(NotBlank.class, NotBlankConstraintValidator.class);
        MESSAGE_CONSTRAINT_VALIDATOR.put(NotEmpty.class, NotEmptyConstraintValidator.class);
        MESSAGE_CONSTRAINT_VALIDATOR.put(Required.class, RequiredConstraintValidator.class);
        MESSAGE_CONSTRAINT_VALIDATOR.put(False.class, FalseConstraintValidator.class);
        MESSAGE_CONSTRAINT_VALIDATOR.put(True.class, TrueConstraintValidator.class);
        MESSAGE_CONSTRAINT_VALIDATOR.put(ZipCode.class, ZipCodeConstraintValidator.class);
        MESSAGE_CONSTRAINT_VALIDATOR.put(StringValue.class, StringValueConstraintValidator.class);
        MESSAGE_CONSTRAINT_VALIDATOR.put(IntegerValue.class, IntegerValueConstraintValidator.class);
    }
    // </editor-fold>

    /**
     * Validate the message using the restriction annotation on object field.
     *
     * @throws MessageValidationException If the instance object is not valid
     */
    public void validate() throws MessageValidationException {
        Set<String> listOfError;
        try {
            listOfError = getErrorField(this);
        } catch (Exception ex) {
            throw new TechnicalException("Can not validate message", ex);
        }
        if (listOfError.size() > 0) {
            throw new MessageValidationException(String.format(INPUT_MESSAGE_NOT_VALID, listOfError.toString()));
        }
    }

    /**
     * Get the list of field that are not valid.
     *
     * @param object The instance object to control.
     * @return The list of non respecting field.
     */
    //TODO : Coment needed
    private Set<String> getErrorField(final Object object) throws
            IntrospectionException, NoSuchFieldException,
            InstantiationException, IllegalAccessException,
            InvocationTargetException {
        Set<String> toReturn = new HashSet<String>();
        Class<?> messageClass = object.getClass();
        //if we dont have the information about this class constraints
        if (!FIELD_MAP.containsKey(messageClass)) {
            //we cretate an emtpy list of the annoted field
            Map<Method, List<MessageConstraintValidator<? extends Annotation>>> annotatedField = new HashMap<Method, List<MessageConstraintValidator<? extends Annotation>>>();
            //for every field of this class
            for (PropertyDescriptor propertyDescriptor
                    : Introspector.getBeanInfo(messageClass, Message.class).getPropertyDescriptors()) {
                //we create an empty list of contraints
                List<MessageConstraintValidator<? extends Annotation>> constraints = new ArrayList<MessageConstraintValidator<? extends Annotation>>();

                //we get the field
                Field field = object.getClass().getDeclaredField(propertyDescriptor.getName());

                //we get the getter method
                Method getter = propertyDescriptor.getReadMethod();

                //we get the list of the possible constrainte
                Set<Class<? extends Annotation>> annotationClasses = MESSAGE_CONSTRAINT_VALIDATOR.keySet();

                //for each possible constraint
                for (Class<? extends Annotation> annotationClass : annotationClasses) {

                    //if the field have a constraint
                    if (field.isAnnotationPresent(annotationClass)) {
                        //we add this contraint to the contraint list of the field
                        MessageConstraintValidator constraint = MESSAGE_CONSTRAINT_VALIDATOR.get(annotationClass).newInstance();
                        constraint.setAnnotation(field.getAnnotation(annotationClass));
                        constraints.add(constraint);
                        Object value = getter.invoke(object);
                        //we check that the constraint is respect
                        if (!constraint.accept(value)) {
                            toReturn.add(getter.getName());
                        }
                    }
                }
                //if the fild have constraint
                if (constraints.size() > 0) {
                    //we add the list of the constraint on the getter list
                    annotatedField.put(getter, constraints);
                }
            }
            // we add the constraint of the object
            FIELD_MAP.put(object.getClass(), annotatedField);
        } //if we already did have the information about this class constraints
        else {
            Map<Method, List<MessageConstraintValidator<? extends Annotation>>> mapFieldsConstraints = FIELD_MAP.get(messageClass);
            if (mapFieldsConstraints.size() > 0) {
                Set<Method> methods = mapFieldsConstraints.keySet();
                for (Method method : methods) {
                    List<MessageConstraintValidator<? extends Annotation>> fieldConstraints = mapFieldsConstraints.get(method);
                    for (MessageConstraintValidator<? extends Annotation> messageConstraintValidator : fieldConstraints) {
                        if (!messageConstraintValidator.accept(method.invoke(object))) {
                            toReturn.add(method.getName());
                        }
                    }
                }
            }
        }
        return toReturn;
    }
}
