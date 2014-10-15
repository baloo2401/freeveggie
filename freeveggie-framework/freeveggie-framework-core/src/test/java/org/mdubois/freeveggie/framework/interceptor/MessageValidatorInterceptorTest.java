package org.mdubois.freeveggie.framework.interceptor;

import java.lang.reflect.Method;
import javax.interceptor.InvocationContext;
import junit.framework.Assert;
import mockit.Expectations;
import mockit.Mocked;
//import mockit.Mockit;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.framework.exception.MessageDefinitionException;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.Required;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;

/**
 * 
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class MessageValidatorInterceptorTest {

	@Mocked
	InvocationContext mockContext;

//	@After
//	public void tearDown() throws Exception {
//		Mockit.restoreAllOriginalDefinitions();
//	}

	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test(expected = MessageValidationException.class)
	public void testValidateMessageValidationException() throws Exception {
		final Object[] parameters = new Object[2];
		parameters[0] = null;
		parameters[1] = null;
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("twoParamMethod", String.class, Integer.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
			}
		};

		instance.validate(mockContext);

	}
	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test(expected = MessageValidationException.class)
	public void testValidateMessageValidationException_2() throws Exception {
		final Object[] parameters = new Object[2];
		parameters[0] = "";
		parameters[1] = null;
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("twoParamMethod", String.class, Integer.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
			}
		};

		instance.validate(mockContext);

	}
	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test(expected = MessageValidationException.class)
	public void testValidateMessageValidationException_3() throws Exception {
		final Object[] parameters = new Object[2];
		parameters[0] = "test";
		parameters[1] = -1;
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("twoParamMethod", String.class, Integer.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
			}
		};

		instance.validate(mockContext);

	}
	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidateMessageValidationException_4() throws Exception {
		final Object[] parameters = new Object[2];
		parameters[0] = "test";
		parameters[1] = 1;
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("twoParamMethod", String.class, Integer.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
				
				mockContext.proceed();
			}
		};

		instance.validate(mockContext);

	}
	

	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidateMessageValidationException_5() throws Exception {
		final Object[] parameters = new Object[0];
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("noParamMethod");
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
				
				mockContext.proceed();
			}
		};

		instance.validate(mockContext);

	}
	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidateMessageValidationException_6() throws Exception {
		final Object[] parameters = new Object[2];
		parameters[0] = "test";
		parameters[1] = null;
		
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("paginationParamMethod", String.class, Pagination.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
				
				mockContext.proceed();
			}
		};

		instance.validate(mockContext);

	}
	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidateMessageValidationException_7() throws Exception {
		final Object[] parameters = new Object[2];
		parameters[0] = "test";
		parameters[1] = new Pagination(0, 0);
		
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("paginationParamMethod", String.class, Pagination.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
				
				mockContext.proceed();
			}
		};

		instance.validate(mockContext);

	}
	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidateMessageValidationException_8() throws Exception {
		final Object[] parameters = new Object[2];
		parameters[0] = "test";
		parameters[1] = null;
		
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("technicalInformationaramMethod", String.class, TechnicalInformation.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
				
				mockContext.proceed();
			}
		};

		instance.validate(mockContext);

	}
	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidateMessageValidationException_9() throws Exception {
		final Object[] parameters = new Object[2];
		parameters[0] = "test";
		parameters[1] = new TechnicalInformation();
		
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("technicalInformationaramMethod", String.class, TechnicalInformation.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
				
				mockContext.proceed();
			}
		};

		instance.validate(mockContext);

	}
	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test(expected = MessageValidationException.class)
	public void testValidateMessageValidationException_10() throws Exception {
		final Object[] parameters = new Object[1];
		parameters[0] = null;
		
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("contextMsgParamMethod", ContextMsg.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
			}
		};

		instance.validate(mockContext);

	}
	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test(expected = MessageValidationException.class)
	public void testValidateMessageValidationException_11() throws Exception {
		final Object[] parameters = new Object[1];
		parameters[0] = new ContextMsg();
		
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("contextMsgParamMethod", ContextMsg.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
			}
		};

		instance.validate(mockContext);

	}
	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test(expected = MessageValidationException.class)
	public void testValidateMessageValidationException_12() throws Exception {
		final Object[] parameters = new Object[1];
		ContextMsg context = new ContextMsg();
		context.setUser(new UserContext());
		parameters[0] = context;
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("contextMsgParamMethod", ContextMsg.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
			}
		};

		instance.validate(mockContext);

	}
	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidateMessageValidationException_13() throws Exception {
		final Object[] parameters = new Object[1];
		ContextMsg context = new ContextMsg();
		context.setUser(new UserContext());
		context.getUser().setId(1L);
		parameters[0] = context;
		
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("contextMsgParamMethod", ContextMsg.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
				
				mockContext.proceed();
			}
		};

		instance.validate(mockContext);

	}
	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test(expected = MessageDefinitionException.class)
	public void testValidateMessageValidationException_14() throws Exception {
		final Object[] parameters = new Object[1];
		parameters[0] = new ExempleObject();
		
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("oneParamMethod", ExempleObject.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
			}
		};

		instance.validate(mockContext);

	}
	

	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test(expected = MessageValidationException.class)
	public void testValidateMessageValidationException_15() throws Exception {
		final Object[] parameters = new Object[1];
		parameters[0] = new ExempleMsg();
		
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("complexeParamMethod", ExempleMsg.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
			}
		};

		instance.validate(mockContext);

	}
	
	/**
	 * * Test of validate method, of class MessageValidatorInterceptor.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValidateMessageValidationException_16() throws Exception {
		final Object[] parameters = new Object[1];
		ExempleMsg exempleMsg = new ExempleMsg();
		exempleMsg.setId("test");
		parameters[0] = exempleMsg;
		
		MessageValidatorInterceptor instance = new MessageValidatorInterceptor();

		new Expectations() {
			private Method method = ExempleBeanLocal.class.getMethod("complexeParamMethod", ExempleMsg.class);
			{
				mockContext.getParameters();
				returns(parameters);

				mockContext.getMethod();
				returns(method);
				
				mockContext.proceed();
			}
		};

		instance.validate(mockContext);

	}
	


	public class ExempleObject {
		
	}

	public class ExempleMsg extends Message {
		@Required
		private String id;
		
		public String getId() {
			return id;
		}
		
		public void setId(String id) {
			this.id = id;
		}
		
	}

	public class ExempleBeanLocal {

		public void noParamMethod() {

		}

		public void oneParamMethod(ExempleObject param1) {

		}

		public void twoParamMethod(String param1, Integer param2) {

		}
		
		public void complexeParamMethod(ExempleMsg exempleMsg) {

		}
		
		public void paginationParamMethod(String param1, Pagination pagination) {

		}
		
		public void technicalInformationaramMethod(String param1, TechnicalInformation technicalInformation) {

		}
		
		public void contextMsgParamMethod(ContextMsg param1) {

		}
	}
}
