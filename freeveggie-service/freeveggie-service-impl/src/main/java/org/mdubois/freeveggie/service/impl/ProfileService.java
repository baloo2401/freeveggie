package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.ProfileBO;
import org.mdubois.freeveggie.bo.UserBO;
import org.mdubois.freeveggie.dao.api.IProfileDAO;
import org.mdubois.freeveggie.dao.api.IUserDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.api.IProfileService;
import org.mdubois.freeveggie.service.msg.ProfileMsg;

// </editor-fold>

/**
 * 
 * @author Mickael Dubois
 */
public class ProfileService implements IProfileService {

	/**
	 * {@link IProductDAO}
	 */
	@Inject
	private IProfileDAO profilDAO;
	/**
	 * {@link IUserDAO}
	 */
	@Inject
	private IUserDAO userDAO;
	/**
	 * {@link BusinessObjectConverter<ProfilBO, ProfilMsg>}
	 */
	@Inject
	private BusinessObjectConverter<ProfileBO, ProfileMsg> profilMsgConverter;
	/**
	 * {@link Converter<ProfilMsg, ProfilBO>}
	 */
	@Inject
	private Converter<ProfileMsg, ProfileBO> profilBOConverter;

	/** {@inheritDoc} */
	@Override
	public boolean update(Long pUserId, ProfileMsg pProfilMsg)
			throws BusinessException {
		ProfileBO profilBO = profilDAO.get(pUserId);
                if (profilBO == null) {
                    //If the profil doest not exist we create this happen the first time the user modify his account
                    profilBO = profilMsgConverter.createNew(pProfilMsg);
                    profilBO.setId(pUserId);
                    profilDAO.save(profilBO);
		} else {
                    profilMsgConverter.update(profilBO, pProfilMsg);
                    profilDAO.update(profilBO);
                }
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public ProfileMsg getProfilById(Long pUserId) throws BusinessException {
                //A user can ask for an inexisting profil but the user need to exist
		UserBO userBO = userDAO.get(pUserId);
		if (userBO == null) {
			throw new BusinessException("Try to get an profil on an inexisting user");
		}
		return profilBOConverter.convert(userBO.getProfile());
	}

}
