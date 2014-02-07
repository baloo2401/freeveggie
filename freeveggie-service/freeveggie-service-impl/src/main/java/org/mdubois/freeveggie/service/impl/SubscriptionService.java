package org.mdubois.freeveggie.service.impl;

import javax.inject.Inject;
import org.mdubois.freeveggie.UserStatus;
import org.mdubois.freeveggie.bo.AddressBO;
import org.mdubois.freeveggie.bo.SubscriptionBO;
import org.mdubois.freeveggie.bo.UserBO;
import org.mdubois.freeveggie.dao.api.IAddressDAO;
import org.mdubois.freeveggie.dao.api.INotificationDAO;
import org.mdubois.freeveggie.dao.api.ISubscriptionDAO;
import org.mdubois.freeveggie.dao.api.IUserDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.EncryptionUtils;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.api.ISubscriptionService;
import org.mdubois.freeveggie.service.msg.AddressMsg;
import org.mdubois.freeveggie.service.msg.CreateAccountMsg;

/**
 * 
 * @author mdubois
 */
public class SubscriptionService implements ISubscriptionService {

	/**
	 * {@link IUserDAO}
	 */
	@Inject
	private IUserDAO userDAO;
	/**
	 * {@link IAddressDAO}
	 */
	@Inject
	private IAddressDAO addressDAO;
	/**
	 * {@link ISubscriptionDAO}
	 */
	@Inject
	private ISubscriptionDAO subscriptionDAO;
	/**
	 * {@link INotificationDAO}
	 */
	@Inject
	private INotificationDAO notificationDAO;
        
        /**
         * {@link BusinessObjectConverter<AddressBO, AddressMsg>}
         */
        @Inject
        private BusinessObjectConverter<AddressBO, AddressMsg> addressMsgConverter;

	/** {@inheritDoc} */
	@Override
	public Long create(CreateAccountMsg pCreateAccountrMsg) throws BusinessException {
		pCreateAccountrMsg.getSubscription().validate();
                
                AddressBO addressBO = addressMsgConverter.createNew(pCreateAccountrMsg.getAddressMsg());
                
                addressDAO.save(addressBO);
                
                if (userDAO.getUserByLogin(pCreateAccountrMsg.getUsername()) == null) {
                        if (userDAO.getUserByEmail(pCreateAccountrMsg.getEmail()) == null) {
                                SubscriptionBO subscriptionBO = new SubscriptionBO();
                                subscriptionBO.setFreeveggieAgreement(true);
                                subscriptionBO.setFreeveggieNewsletter(true);
                                subscriptionBO.setShareGardenInformation(true);
                                subscriptionBO.setSharePersonalInformation(true);


                                UserBO userBO = new UserBO();
                                userBO.setCreationDate(SystemTime.asDate());
                                userBO.setEmail(pCreateAccountrMsg.getEmail());
                                userBO.setFirstname(pCreateAccountrMsg.getFirstname());
                                userBO.setHomeAddress(addressBO);
                                userBO.setLastConnexion(SystemTime.asDate());
                                userBO.setLastname(pCreateAccountrMsg.getLastname());
                                userBO.setPassword(EncryptionUtils.getMD5(pCreateAccountrMsg
                                                .getPassword()));
                                userBO.setRole(UserRole.USER);
                                userBO.setStatus(UserStatus.NEW);
                                userBO.setSubscription(subscriptionBO);
                                userBO.setUsername(pCreateAccountrMsg.getUsername());

                                Long idUser = userDAO.save(userBO);

                                subscriptionBO.setId(idUser);
                                subscriptionDAO.save(subscriptionBO);

                                notificationDAO.sendCreationUserNotice(userBO, EncryptionUtils.encrypt(userBO.getUsername()));
                                return idUser;
                        } else {
                                throw new BusinessException("Duplicate email");
                        }
                } else {
                        throw new BusinessException("Duplicate username");
                }
	}
}
