package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.mdubois.freeveggie.bo.UserBO;
import org.mdubois.freeveggie.dao.api.IAuthenticationDAO;
import org.mdubois.freeveggie.framework.dao.DataAccessObject;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.security.EncryptionUtils;

// </editor-fold>

/**
 * 
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({ DAOInterceptor.class })
public class AuthenticationDAO extends DataAccessObject implements
		IAuthenticationDAO {

	private static final String USER_BO_CLASS = UserBO.class.getName();
	private static final String GET_USER_FROM_LOGIN_REQUEST = "SELECT e FROM "
			+ USER_BO_CLASS + " e WHERE e.username = :Username";

	/** {@inheritDoc} */
	@Override
	public UserBO controlPassword(final String pLogin, final String pPassword) {
		Query query = entityManager.createQuery(GET_USER_FROM_LOGIN_REQUEST);
		query.setParameter("Username", pLogin);

		UserBO user;
		try {
			user = (UserBO) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		String encryptedPassword = EncryptionUtils.getMD5(pPassword);
		if (!encryptedPassword.equals(user.getPassword())) {
			return null;
		} else {
			return user;
		}
	}

	/** {@inheritDoc} */
	@Override
	public UserBO controlTempPassword(final String pLogin,
			final String pPassword) {
		Query query = entityManager.createQuery(GET_USER_FROM_LOGIN_REQUEST);
		query.setParameter("Username", pLogin);

		UserBO user;
		try {
			user = (UserBO) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		String encryptedPassword = EncryptionUtils.getMD5(pPassword);
		if (!encryptedPassword.equals(user.getTemporaryPassword())) {
			return null;
		} else {
			return user;
		}
	}
}
