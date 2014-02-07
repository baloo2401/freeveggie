package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.BestRatedProductBO;
import org.mdubois.freeveggie.bo.LastExchangeProductBO;
import org.mdubois.freeveggie.bo.MostSharedProductBO;
import org.mdubois.freeveggie.dao.api.*;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.service.api.IStatistiqueService;
import org.mdubois.freeveggie.service.msg.BestRatedProductMsg;
import org.mdubois.freeveggie.service.msg.LastExchangeProductMsg;
import org.mdubois.freeveggie.service.msg.MostSharedProductMsg;

// </editor-fold>

/**
 * This class give database information statistics.
 * 
 * @author Mickael Dubois
 */
public class StatistiqueService implements IStatistiqueService {

	// <editor-fold defaultstate="collapsed" desc="DAO's">
        /**
         * {@link IStatistiqueDAO}
         */
	@Inject
	private IStatistiqueDAO statistiqueDAO;
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Converter">
	@Inject
	private Converter<BestRatedProductMsg, BestRatedProductBO> bestRatedProductBoToMsgConverter;
	@Inject
	private Converter<LastExchangeProductMsg, LastExchangeProductBO> lastExchangeProductBoToMsgConverter;
	@Inject
	private Converter<MostSharedProductMsg, MostSharedProductBO> mostSharedProductBoToMsgConverter;

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Best rated product method's">
	/** {@inheritDoc} */
	@Override
	public List<BestRatedProductMsg> getBestRatedProductByUser(
			final Long pUserPartialId, final Pagination pPagination)
			throws BusinessException {
		return bestRatedProductBoToMsgConverter.convert(statistiqueDAO
				.getBestRatedProductOffAUser(pUserPartialId, pPagination));
	}

	/** {@inheritDoc} */
	@Override
	public List<BestRatedProductMsg> getBestRatedProductByGarden(
			final Long pGardenId, final Pagination pPagination)
			throws BusinessException {
		return bestRatedProductBoToMsgConverter.convert(statistiqueDAO
				.getBestRatedProductOffAGarden(pGardenId, pPagination));
	}

	/** {@inheritDoc} */
	@Override
	public List<BestRatedProductMsg> getBestRatedProductByCity(
			final Integer pRefCityId, final Pagination pPagination)
			throws BusinessException {
		return bestRatedProductBoToMsgConverter.convert(statistiqueDAO
				.getBestRatedProductOffACity(pRefCityId, pPagination));
	}

	/** {@inheritDoc} */
	@Override
	public List<BestRatedProductMsg> getBestRatedProductByRegion(
			final Integer pRefRegionId, final Pagination pPagination)
			throws BusinessException {
		return bestRatedProductBoToMsgConverter.convert(statistiqueDAO
				.getBestRatedProductOffARegion(pRefRegionId, pPagination));
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed"
	// desc="Last exchanged product method's">
	/** {@inheritDoc} */
	@Override
	public List<LastExchangeProductMsg> getLastExchangeProductByUser(
			final Long pUserPartialId, final Pagination pPagination)
			throws BusinessException {
		return lastExchangeProductBoToMsgConverter.convert(statistiqueDAO
				.getLastExchangeProductOffAUser(pUserPartialId, pPagination));
	}

	/** {@inheritDoc} */
	@Override
	public List<LastExchangeProductMsg> getLastExchangeProductByGarden(
			final Long pGardenId, final Pagination pPagination)
			throws BusinessException {
		return lastExchangeProductBoToMsgConverter.convert(statistiqueDAO
				.getLastExchangeProductOffAGarden(pGardenId, pPagination));
	}

	/** {@inheritDoc} */
	@Override
	public List<LastExchangeProductMsg> getLastExchangeProductByProduct(
			final Long pProductId, final Pagination pPagination)
			throws BusinessException {
		return lastExchangeProductBoToMsgConverter.convert(statistiqueDAO
				.getLastExchangeProductOffAProduct(pProductId, pPagination));
	}

	/** {@inheritDoc} */
	@Override
	public List<LastExchangeProductMsg> getLastExchangeProductByCity(
			final Integer pRefCityId, final Pagination pPagination)
			throws BusinessException {
		return lastExchangeProductBoToMsgConverter.convert(statistiqueDAO
				.getLastExchangeProductByCity(pRefCityId, pPagination));
	}

	/** {@inheritDoc} */
	@Override
	public List<LastExchangeProductMsg> getLastExchangeProductByRegion(
			final Integer pRefRegionId, final Pagination pPagination)
			throws BusinessException {
		return lastExchangeProductBoToMsgConverter.convert(statistiqueDAO
				.getLastExchangeProductByRegion(pRefRegionId, pPagination));
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed"
	// desc="Most shared product method's">
	/** {@inheritDoc} */
	@Override
	public List<MostSharedProductMsg> getMostSharedProductByUser(
			final Long pUserPartialId, final Pagination pPagination)
			throws BusinessException {
		return mostSharedProductBoToMsgConverter.convert(statistiqueDAO
				.getMostSharedProductByUser(pUserPartialId, pPagination));
	}

	/** {@inheritDoc} */
	@Override
	public List<MostSharedProductMsg> getMostSharedProductByGarden(
			final Long pGardenId, final Pagination pPagination)
			throws BusinessException {
		return mostSharedProductBoToMsgConverter.convert(statistiqueDAO
				.getMostSharedProductByGarden(pGardenId, pPagination));
	}

	/** {@inheritDoc} */
	@Override
	public List<MostSharedProductMsg> getMostSharedProductByCity(
			final Integer pRefCityMsg, final Pagination pPagination)
			throws BusinessException {
		return mostSharedProductBoToMsgConverter.convert(statistiqueDAO
				.getMostSharedProductByCity(pRefCityMsg, pPagination));
	}

	/** {@inheritDoc} */
	@Override
	public List<MostSharedProductMsg> getMostSharedProductByRegion(
			final Integer pRefRegionMsg, final Pagination pPagination)
			throws BusinessException {
		return mostSharedProductBoToMsgConverter.convert(statistiqueDAO
				.getMostSharedProductByRegion(pRefRegionMsg, pPagination));
	}
	// </editor-fold>
}
