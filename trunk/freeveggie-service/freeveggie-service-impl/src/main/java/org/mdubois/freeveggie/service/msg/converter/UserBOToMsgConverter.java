package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.*;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class UserBOToMsgConverter extends AbstractConverter<UserMsg, UserBO> implements Converter<UserMsg, UserBO> {

    /**
     * {@link Converter<AddressMsg, AddressBO>}
     */
    @Inject
    private Converter<AddressMsg, AddressBO> addressBOToMsgConverter;

    /**
     * {@link Converter<ProfileMsg, ProfileBO>}
     */
     @Inject
    private Converter<ProfileMsg, ProfileBO> profileBOToMsgConverter;

    /**
     * {@link Converter<SubscriptionMsg, SubscriptionBO>}
     */
     @Inject
    private Converter<SubscriptionMsg, SubscriptionBO> subscriptionBOToMsgConverter;

    @Override
    public UserMsg doConvert(UserBO pUserBO) {
        UserMsg userMsg = new UserMsg();
        userMsg.setId(pUserBO.getId());
        userMsg.setBlacklistedDate(pUserBO.getBlacklistedDate());
        userMsg.setCancellationDate(pUserBO.getCancellationDate());
        userMsg.setCreationDate(pUserBO.getCreationDate());
        userMsg.setEmail(pUserBO.getEmail());
        userMsg.setFirstname(pUserBO.getFirstname());
        if (pUserBO.getHomeAddress() != null) {
            userMsg.setHomeAdresse(addressBOToMsgConverter.convert(pUserBO.getHomeAddress()));
        }
        userMsg.setLastConnexion(pUserBO.getLastConnexion());
        userMsg.setLastname(pUserBO.getLastname());
        //userMsg.setPassword(pUserBO.getPassword()); have to be set by the service
        if (pUserBO.getProfile() != null) {
            userMsg.setProfile(profileBOToMsgConverter.convert(pUserBO.getProfile()));
        }
        userMsg.setProfileImgFilename(pUserBO.getProfileImgFilename());
        userMsg.setRole(pUserBO.getRole());
        userMsg.setUsername(pUserBO.getUsername());
        userMsg.setStatus(pUserBO.getStatus());
        if (pUserBO.getSubscription() != null) {
            userMsg.setSubscription(subscriptionBOToMsgConverter.convert(pUserBO.getSubscription()));
        }
        //userMsg.setTemporaryPassword(pUserBO.getTemporaryPassword()); have to be set by the service

        return userMsg;
    }
}