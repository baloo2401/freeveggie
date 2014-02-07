package org.mdubois.freeveggie.bo;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author mdubois
 */
public class SubscriptionBOTest {

    @Test
    public void test() {
        SubscriptionBO subscriptionBO = new SubscriptionBO();
        subscriptionBO.setFreeveggieAgreement(Boolean.FALSE);
        subscriptionBO.setFreeveggieNewsletter(Boolean.FALSE);
        subscriptionBO.setShareGardenInformation(Boolean.FALSE);
        subscriptionBO.setSharePersonalInformation(Boolean.FALSE);
        Assert.assertFalse(subscriptionBO.getFreeveggieAgreement());
        Assert.assertFalse(subscriptionBO.getFreeveggieNewsletter());
        Assert.assertFalse(subscriptionBO.getShareGardenInformation());
        Assert.assertFalse(subscriptionBO.getSharePersonalInformation());
    }

    @Test
    public void testFalse() {
        SubscriptionBO subscriptionBO = new SubscriptionBO();
        subscriptionBO.setFreeveggieAgreement(false);
        subscriptionBO.setFreeveggieNewsletter(false);
        subscriptionBO.setShareGardenInformation(false);
        subscriptionBO.setSharePersonalInformation(false);
        Assert.assertFalse(subscriptionBO.getFreeveggieAgreement());
        Assert.assertFalse(subscriptionBO.getFreeveggieNewsletter());
        Assert.assertFalse(subscriptionBO.getShareGardenInformation());
        Assert.assertFalse(subscriptionBO.getSharePersonalInformation());
    }
    @Test
    public void testTrue() {
        SubscriptionBO subscriptionBO = new SubscriptionBO();
        subscriptionBO.setFreeveggieAgreement(true);
        subscriptionBO.setFreeveggieNewsletter(true);
        subscriptionBO.setShareGardenInformation(true);
        subscriptionBO.setSharePersonalInformation(true);
        Assert.assertTrue(subscriptionBO.getFreeveggieAgreement());
        Assert.assertTrue(subscriptionBO.getFreeveggieNewsletter());
        Assert.assertTrue(subscriptionBO.getShareGardenInformation());
        Assert.assertTrue(subscriptionBO.getSharePersonalInformation());
    }
}
