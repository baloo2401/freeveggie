package org.mdubois.freeveggie.service.impl;

import javax.inject.Inject;
import org.mdubois.freeveggie.bo.AddressBO;
import org.mdubois.freeveggie.bo.RefCityBO;
import org.mdubois.freeveggie.dao.api.IAddressDAO;
import org.mdubois.freeveggie.dao.api.IRefCityDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.api.IAddressService;
import org.mdubois.freeveggie.service.msg.AddressMsg;

/**
 * 
 * @author mdubois
 */
public class AddressService implements IAddressService {

	/**
	 * {@link IRefCityDAO}
	 */
	@Inject
	private IRefCityDAO refCityDAO;
	/**
	 * {@link IAddressDAO}
	 */
	@Inject
	private IAddressDAO addressDAO;
	/**
	 * {@link BusinessObjectConverter<AddressBO,AddressMsg>}
	 */
	@Inject
	private BusinessObjectConverter<AddressBO, AddressMsg> addressMsgConverter;
	/**
	 * {@link BusinessObjectConverter<AddressBO,AddressMsg>}
	 */
	@Inject
	private Converter<AddressMsg, AddressBO> addressBoToMsgConverter;

	/** {@inheritDoc} */
	@Override
	public Long create(AddressMsg pAddressMsg) throws BusinessException {
		RefCityBO refCityBO = refCityDAO.get(pAddressMsg.getCity().getId());
		if (refCityBO != null) {
			AddressBO addressBO = addressMsgConverter.createNew(pAddressMsg);
			addressBO.setCity(refCityBO);
			return addressDAO.save(addressBO);
		} else {
			throw new BusinessException("Try to add address to an unknown city");
		}
	}

	/** {@inheritDoc} */
	@Override
	public void update(Long pAddressId, AddressMsg pAddressMsg)
			throws BusinessException {
		AddressBO addressBO = addressDAO.get(pAddressId);
		if (addressBO != null) {
			//RefCityBO refCityBO = refCityDAO.get(pAddressMsg.getCity().getId());
			//if (refCityBO != null) {
				addressMsgConverter.update(addressBO, pAddressMsg);
				addressDAO.update(addressBO);
//			} else {
//				throw new BusinessException(
//						"Try to update an address to an unknown city");
//			}
		} else {
			throw new BusinessException("Try to udpate un inexiting address");
		}
	}

	/** {@inheritDoc} */
	@Override
	public AddressMsg getAddressById(Long pAddressId) throws BusinessException {
		return addressBoToMsgConverter.convert(addressDAO.get(pAddressId));
	}

	/** {@inheritDoc} */
	@Override
	public AddressMsg getUserHomeAddressByUserId(Long pUserId) throws BusinessException {
		return addressBoToMsgConverter.convert(addressDAO.getUserHomeAddressByUserId(pUserId));
	}
}
