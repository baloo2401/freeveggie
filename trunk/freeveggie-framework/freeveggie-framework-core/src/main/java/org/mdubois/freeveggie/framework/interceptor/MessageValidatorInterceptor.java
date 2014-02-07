package org.mdubois.freeveggie.framework.interceptor;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.apache.commons.lang.StringUtils;
import org.mdubois.freeveggie.framework.exception.MessageDefinitionException;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.MessageEnum;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
// </editor-fold>

/**
 * The interceptor control that input messages are valid.
 *
 * @author Mickael Dubois
 */
@Interceptor
public class MessageValidatorInterceptor {

    // <editor-fold defaultstate="collapsed" desc="Exception message constants">
    /**
     * Message for empty string message.
     */
    private static final String INPUT_MESSAGE_CAN_NOT_BEN_EMPTY = "Input message can not ben empty.";
    /**
     * Message for null input message.
     */
    private static final String INPUT_MESSAGE_CAN_NOT_BEN_NULL = "Input message can not be null.";
    /**
     * Message for negative input message.
     */
    private static final String INPUT_MESSAGE_CAN_NOT_BE_NEGATIVE = "Input message can not be negative.";
    /**
     * Message for non correct type input message.
     */
    private static final String INPUT_MESSAGE_IS_NOT_A_GOOD_TYPE = "Input message is not properly configured. The second parameters of an"
            + " ejb have to be easer a String, a Number or a Message";
    /**
     * Message for context message not.
     */
    private static final String CONTEXT_MESSAGE_NOT_VALID = "Context message not valid.";
    // </editor-fold>

    /**
     * Control that input messages are valid. First they must not be null, then
     * they have to be either a String, a Number or extends the Message object.
     * If they are a String they can be empty and if they are an Message object
     * they must validate the message.
     *
     * @param ctx The context call
     * @return The value of the method initialy called
     * @throws Exception when the invocation parameters are not valid
     */
    @AroundInvoke
    public final Object validate(final InvocationContext ctx) throws Exception {
    	Object[] context = ctx.getParameters();
    	Class<?>[] parameterTypes = ctx.getMethod().getParameterTypes();
        for (int i = 0; i < context.length; i++) {
            Object parameter = context[i];
            Class<?> parameterType = parameterTypes[i];
            //None of the parameter can be null exept Technical object
            if (!parameterType.equals(Pagination.class)
                    && !parameterType.equals(TechnicalInformation.class) ) {
                if (parameter == null) {
                    throw new MessageValidationException("Parameter " + parameterType + " not valid : " +
                            INPUT_MESSAGE_CAN_NOT_BEN_NULL);
                } else if (parameter instanceof ContextMsg) {
                    ContextMsg contextMsg = (ContextMsg) parameter;
                    if (contextMsg.getUser() == null || contextMsg.getUser().getId() == null
                            || contextMsg.getUser().getId() < 0) {
                        throw new MessageValidationException("Parameter " + parameterType + " not valid : " +
                                CONTEXT_MESSAGE_NOT_VALID);
                    }
                } else if (parameter instanceof Message) {
                    ((Message) parameter).validate();
                } else if (parameter instanceof String) {
                    if (StringUtils.isBlank((String) parameter)) {
                        throw new MessageValidationException("Parameter " + (i+1) + " not valid : " +
                                INPUT_MESSAGE_CAN_NOT_BEN_EMPTY);
                    }
                } else if ((parameter instanceof Number)) {
                    Number value = (Number) parameter;
                    if (value.intValue() < 0) {
                        throw new MessageValidationException("Parameter " + (i+1) + " not valid : " +
                                INPUT_MESSAGE_CAN_NOT_BE_NEGATIVE);
                    }
                } else if(parameter instanceof MessageEnum){
                    
                } else {
                    throw new MessageDefinitionException("Parameter " + (i+1) + " not valid : " +
                            INPUT_MESSAGE_IS_NOT_A_GOOD_TYPE);
                }
            }
        }
        return ctx.proceed();
    }
}
