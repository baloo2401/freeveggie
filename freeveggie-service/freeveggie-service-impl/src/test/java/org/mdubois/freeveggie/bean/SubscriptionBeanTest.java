package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bean.local.SubscriptionBeanLocal;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.service.api.ISubscriptionService;
import org.mdubois.freeveggie.service.msg.CreateAccountMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class SubscriptionBeanTest {

    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testCreate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final CreateAccountMsg pUserMsg = new CreateAccountMsg();
        final ISubscriptionBean instance = new SubscriptionBeanLocal();

        new Expectations() {

            @Mocked
            private ISubscriptionService subscriptionService;

            {
                Deencapsulation.setField(instance, subscriptionService);

                subscriptionService.create(pUserMsg);
                returns(123L);
            }
        };

        Long result = instance.create(pUserMsg);
        Assert.assertEquals((Long)123L, result);
    }

    @Test(expected = BusinessException.class)
    public void testCreateThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final CreateAccountMsg pUserMsg = new CreateAccountMsg();
        final ISubscriptionBean instance = new SubscriptionBeanLocal();

        new Expectations() {

            @Mocked
            private ISubscriptionService subscriptionService;

            {
                Deencapsulation.setField(instance, subscriptionService);

                subscriptionService.create(pUserMsg);
                throwsException(new BusinessException("BusinessException"));
            }
        };

        instance.create(pUserMsg);
    }

    @Test(expected = TechnicalException.class)
    public void testCreateThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final CreateAccountMsg pUserMsg = new CreateAccountMsg();
        final ISubscriptionBean instance = new SubscriptionBeanLocal();

        new Expectations() {

            @Mocked
            private ISubscriptionService subscriptionService;

            {
                Deencapsulation.setField(instance, subscriptionService);

                subscriptionService.create(pUserMsg);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };

        instance.create(pUserMsg);
    }
    
}
