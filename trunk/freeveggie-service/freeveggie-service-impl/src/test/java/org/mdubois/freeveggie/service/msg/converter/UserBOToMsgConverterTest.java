package org.mdubois.freeveggie.service.msg.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.UserStatus;
import org.mdubois.freeveggie.bo.AddressBO;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.ProfileBO;
import org.mdubois.freeveggie.bo.SubscriptionBO;
import org.mdubois.freeveggie.bo.UserBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.service.msg.AddressMsg;
import org.mdubois.freeveggie.service.msg.ProfileMsg;
import org.mdubois.freeveggie.service.msg.SubscriptionMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;
import org.unitils.reflectionassert.ReflectionAssert;

/**
 *
 * @author Mickael Dubois
 */
public class UserBOToMsgConverterTest extends AbstractConverterTest<UserMsg, UserBO> {

    @Mocked
    private Converter<AddressMsg, AddressBO> mockAddressBOToMsgConverter;
    @Mocked
    private Converter<ProfileMsg, ProfileBO> mockProfileBOToMsgConverter;
    @Mocked
    private Converter<SubscriptionMsg, SubscriptionBO> mockSubscriptionBOToMsgConverter;

    private static final Date NOW = new Date();

    @Test
    public void testConvertFull() {

        final UserBO userBO = getBusinessObject();
        UserMsg expectedResult = getExpectedMessage();
        final UserBOToMsgConverter converter = getConverter();
        List<GardenBO> gardens = new ArrayList<GardenBO>();
        gardens.add(new GardenBO());
        userBO.setGardens(gardens);
        userBO.setHomeAddress(new AddressBO());
        userBO.setProfile(new ProfileBO());
        userBO.setSubscription(new SubscriptionBO());

        new Expectations() {

            {
                Deencapsulation.setField(converter, "addressBOToMsgConverter", mockAddressBOToMsgConverter);
                Deencapsulation.setField(converter, "profileBOToMsgConverter", mockProfileBOToMsgConverter);
                Deencapsulation.setField(converter, "subscriptionBOToMsgConverter", mockSubscriptionBOToMsgConverter);

                mockAddressBOToMsgConverter.convert(userBO.getHomeAddress());
                returns(null);
                mockProfileBOToMsgConverter.convert(userBO.getProfile());
                returns(null);
                mockSubscriptionBOToMsgConverter.convert(userBO.getSubscription());
                returns(null);

            }
        };

        UserMsg result = converter.convert(userBO);

        ReflectionAssert.assertLenientEquals(expectedResult, result);
    }

    @Override
    public UserMsg getExpectedMessage() {
        UserMsg expResult = new UserMsg();
        expResult.setCreationDate(NOW);
        expResult.setId(10L);
        expResult.setLastConnexion(NOW);
        expResult.setProfileImgFilename("profile img file name");
        expResult.setRole(UserRole.USER);
        expResult.setUsername("username");
        expResult.setBlacklistedDate(NOW);
        expResult.setCancellationDate(NOW);
        expResult.setEmail("email");
        expResult.setFirstname("firstname");
        expResult.setLastname("lastname");
        expResult.setProfileImgFilename("profile img file name");
        expResult.setStatus(UserStatus.NEW);

        return expResult;
    }

    @Override
    public UserBO getBusinessObject() {
        UserBO pUserBO = new UserBO();
        pUserBO.setCreationDate(NOW);
        pUserBO.setId(10L);
        pUserBO.setLastConnexion(NOW);
        pUserBO.setProfileImgFilename("profile img file name");
        pUserBO.setRole(UserRole.USER);
        pUserBO.setUsername("username");
        pUserBO.setBlacklistedDate(NOW);
        pUserBO.setCancellationDate(NOW);
        pUserBO.setEmail("email");
        pUserBO.setFirstname("firstname");
        pUserBO.setLastname("lastname");
        pUserBO.setPassword("password");
        pUserBO.setProfileImgFilename("profile img file name");
        pUserBO.setStatus(UserStatus.NEW);
        pUserBO.setTemporaryPassword("tmp password");

        return pUserBO;
    }

    @Override
    public UserBOToMsgConverter getConverter() {
        return new UserBOToMsgConverter();
    }
}
