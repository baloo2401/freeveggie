package org.mdubois.freeveggie.framework.test;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;
import org.junit.Test;
import org.mdubois.freeveggie.framework.bo.RefBusinessObject;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.MessageEnum;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public abstract class MessageTest<T extends Message> {

    // <editor-fold defaultstate="collapsed" desc="Constants" >
    /**
     * String to use to test setter needing {@link String}.
     */
    private static final String STRING_TO_SET = "string";

    /**
     * String to use to test setter needing {@link Integer}.
     */
    private static final Integer INTEGER_TO_SET = 1234;

    /**
     * String to use to test setter needing {@link Long}.
     */
    private static final Long LONG_TO_SET = 12334564L;

    /**
     * String to use to test setter needing {@link Double}.
     */
    private static final Double DOUBLE_TO_SET = 1734d;

    /**
     * String to use to test setter needing {@link Float}.
     */
    private static final Float FLOAT_TO_SET = 1284f;

    /**
     * String to use to test setter needing {@link Date}.
     */
    private static final Date DATE_TO_SET = new Date();

    /**
     * String to use to test setter needing {@link Boolean}.
     */
    private static final Boolean BOOLEAN_TO_SET = Boolean.TRUE;

    /**
     * Map list of class and object to use to test setter.
     */
    private static final Map<Class, Object> RETURN_TO_SET = new HashMap<Class, Object>();
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constant initialization" >
    {
        RETURN_TO_SET.put(String.class, STRING_TO_SET);
        RETURN_TO_SET.put(Integer.class, INTEGER_TO_SET);
        RETURN_TO_SET.put(Long.class, LONG_TO_SET);
        RETURN_TO_SET.put(Double.class, DOUBLE_TO_SET);
        RETURN_TO_SET.put(Float.class, FLOAT_TO_SET);
        RETURN_TO_SET.put(Date.class, DATE_TO_SET);
        RETURN_TO_SET.put(Boolean.class, BOOLEAN_TO_SET);
    }
    // </editor-fold>

    protected abstract Map<Class, Object> getMessageAndBusinessObjectUsed();

    @Test
    public void testGetNull() throws InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        if (!this.getClass().equals(MessageTest.class)) {
            Class<T> messageClass;
            ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
            messageClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
            T messegeObject = messageClass.newInstance();
            for (PropertyDescriptor propertyDescriptor
                    : Introspector.getBeanInfo(messageClass, Message.class).getPropertyDescriptors()) {
                Assert.assertNull(propertyDescriptor.getReadMethod().invoke(messegeObject, null));
            }
        }
    }

    @Test
    public void testSet() throws InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        if (!this.getClass().equals(MessageTest.class)) {
            Class<T> messageClass;
            ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
            messageClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
            T messageObject = messageClass.newInstance();
            for (PropertyDescriptor propertyDescriptor
                    : Introspector.getBeanInfo(messageClass, Message.class).getPropertyDescriptors()) {
                Class propertyType = propertyDescriptor.getPropertyType();
                Object valueToSet = RETURN_TO_SET.get(propertyType);
                if (valueToSet != null) {
                    propertyDescriptor.getWriteMethod().invoke(messageObject, valueToSet);
                } else if (Message.class.isAssignableFrom(propertyType)
                        || RefBusinessObject.class.isAssignableFrom(propertyType)
                        || MessageEnum.class.isAssignableFrom(propertyType)) {
                    valueToSet = getMessageAndBusinessObjectUsed().get(propertyType);
                    propertyDescriptor.getWriteMethod().invoke(messageObject, valueToSet);
                } else if (Collection.class.isAssignableFrom(propertyType)) {
                    //TODO : Try to test this too
                } else {
                    //TODO : Try to test this too
                }
            }
        }
    }

    @Test
    public void testSetNull() throws InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        if (!this.getClass().equals(MessageTest.class)) {
            Class<T> messageClass;
            ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
            messageClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
            T messegeObject = messageClass.newInstance();
            for (PropertyDescriptor propertyDescriptor
                    : Introspector.getBeanInfo(messageClass, Message.class).getPropertyDescriptors()) {
                Class propertyType = propertyDescriptor.getPropertyType();
                Object valueToSet = RETURN_TO_SET.get(propertyType);
                if (valueToSet != null) {
                    Assert.assertNull(propertyDescriptor.getWriteMethod().invoke(messegeObject, (propertyType.cast(null))));

                } else if (Message.class.isAssignableFrom(propertyType)
                        || RefBusinessObject.class.isAssignableFrom(propertyType)
                        || MessageEnum.class.isAssignableFrom(propertyType)) {
                    Assert.assertNull(propertyDescriptor.getWriteMethod().invoke(messegeObject, (propertyType.cast(null))));
                } else if (Collection.class.isAssignableFrom(propertyType)) {
                    //TODO : Try to test this too
                } else {
                    //TODO : Try to test this too
                }
            }
        }
    }

    @Test
    public void testGet() throws InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        if (!this.getClass().equals(MessageTest.class)) {
            Class<T> messageClass;
            ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
            messageClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
            T messegeObject = messageClass.newInstance();
            for (PropertyDescriptor propertyDescriptor
                    : Introspector.getBeanInfo(messageClass, Message.class).getPropertyDescriptors()) {
                Class propertyType = propertyDescriptor.getPropertyType();
                Object valueToSet = RETURN_TO_SET.get(propertyType);
                if (valueToSet != null) {
                    propertyDescriptor.getWriteMethod().invoke(messegeObject, valueToSet);
                    Assert.assertEquals(valueToSet, propertyDescriptor.getReadMethod().invoke(messegeObject, null));

                } else if (Message.class.isAssignableFrom(propertyType)
                        || RefBusinessObject.class.isAssignableFrom(propertyType)
                        || MessageEnum.class.isAssignableFrom(propertyType)) {
                    valueToSet = getMessageAndBusinessObjectUsed().get(propertyType);
                    propertyDescriptor.getWriteMethod().invoke(messegeObject, valueToSet);
                    Assert.assertEquals(valueToSet, propertyDescriptor.getReadMethod().invoke(messegeObject, null));
                } else if (Collection.class.isAssignableFrom(propertyType)) {
                    //TODO : Try to test this too
                } else {
                    //TODO : Try to test this too
                }
            }
        }
    }

}
