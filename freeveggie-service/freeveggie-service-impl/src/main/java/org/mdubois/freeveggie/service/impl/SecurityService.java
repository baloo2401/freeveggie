package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import javax.inject.Inject;
import org.apache.commons.lang.StringUtils;
import org.mdubois.freeveggie.bo.UserBO;
import org.mdubois.freeveggie.dao.api.IAuthenticationDAO;
import org.mdubois.freeveggie.dao.api.INotificationDAO;
import org.mdubois.freeveggie.dao.api.IUserDAO;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.security.EncryptionUtils;
import org.mdubois.freeveggie.framework.security.PasswordValidator;
import org.mdubois.freeveggie.service.api.ISecurityService;
import org.mdubois.freeveggie.service.msg.AuthenticationMsg;
import org.mdubois.freeveggie.service.msg.ChangePasswordMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;

// </editor-fold>
/**
 *
 * @author Mickael Dubois
 */
public class SecurityService implements ISecurityService {

    // <editor-fold defaultstate="collapsed" desc="DAO Resource's">
    /**
     * {@link IUserDAO}
     */
    @Inject
    private IUserDAO userDAO;
    /**
     * {@link IAuthenticationDAO}
     */
    @Inject
    private IAuthenticationDAO authenticationDAO;
    /**
     * {@link IAuthenticationDAO}
     */
    @Inject
    private INotificationDAO notificationDAO;
    // <editor-fold defaultstate="collapsed" desc="Converter Resource's">
    /**
     * {@link Converter<GardenBO, GardenMsg>}
     */
    @Inject
    private Converter<UserMsg, UserBO> userBOToMsgConverter;

    // </editor-fold>
    /**
     * {@inheritDoc}
     */
    @Override
    public UserMsg controlPassword(AuthenticationMsg pAuthentificationMsg) throws BusinessException {
        UserBO userBO = authenticationDAO.controlPassword(
                pAuthentificationMsg.getLogin(),
                pAuthentificationMsg.getPassword());
        if (userBO != null) {
            switch (userBO.getStatus()) {
                case VALIDED:
                    //TODO Call utility class to be able to unit test this
                    userBO.setLastConnexion(new Date());
                    userDAO.save(userBO);
                    return userBOToMsgConverter.convert(userBO);
                default:
                    //All the other status can't control there password
                    throw new BusinessException("Your account is on an unusual state :" + userBO.getStatus());
            }
        } else {
            throw new BusinessException("Username and/or password is not valid");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserMsg controlTempPassword(AuthenticationMsg pAuthentificationMsg) {
        return userBOToMsgConverter.convert(authenticationDAO
                .controlTempPassword(pAuthentificationMsg.getLogin(),
                pAuthentificationMsg.getPassword()));
    }

    @Override
    public Boolean changePassword(ChangePasswordMsg pChangePasswordMsg) throws BusinessException {
        if(!PasswordValidator.isValid(pChangePasswordMsg.getNewPassword())){
            UserBO userBO = userDAO.get(pChangePasswordMsg.getUserId());
            if (userBO != null) {
                UserBO userBOFromControl = authenticationDAO.controlPassword(
                        userBO.getUsername(),
                        pChangePasswordMsg.getOldPassword());
                if (userBOFromControl != null) {
                    String encryptedPassword = EncryptionUtils.getMD5(pChangePasswordMsg.getNewPassword());
                    userBO.setPassword(encryptedPassword);
                    userDAO.save(userBO);
                    return Boolean.TRUE;
                } else {
                    throw new BusinessException("Try to change password but the old password given is not valid");
                }
            } else {
                throw new BusinessException("Try to change password of an inexisting user");
            }
        }else {
            throw new BusinessException("Unvalid password : A password need to have at least 8 charactere and have no space");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasTempPassword(String pCode) throws BusinessException {
        String decryptLogin;
        try {
            decryptLogin = EncryptionUtils.decrypt(pCode);
        } catch (Exception ex) {
            throw new BusinessException("Unvalid code : can not decrypt code",
                    ex);
        }
        UserBO userBO = userDAO.getUserByLogin(decryptLogin);
        if (userBO == null) {
            throw new BusinessException("Unvalid code : can not find user");
        }

        return StringUtils.isNotBlank(userBO.getTemporaryPassword());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExistingLogin(String pLogin) {
        UserBO userBO = userDAO.getUserByLogin(pLogin);
        return userBO != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExistingEmail(String pEmail) {
        UserBO userBO = userDAO.getUserByEmail(pEmail);
        return userBO != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateTempPassword(final String pEmail)
            throws BusinessException {
        UserBO userBO = userDAO.getUserByEmail(pEmail);
        if (userBO == null) {
            throw new BusinessException("Unvalid code : can not find user");
        }
        String code;
        try {
            code = EncryptionUtils.encrypt(userBO.getUsername());
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
        String generatedPassword = generatePassword();
        userBO.setTemporaryPassword(EncryptionUtils.getMD5(generatedPassword));
        userDAO.update(userBO);
        notificationDAO.sendLostEmailNotice(userBO, code, generatedPassword);
    }

    private String generatePassword() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
}
