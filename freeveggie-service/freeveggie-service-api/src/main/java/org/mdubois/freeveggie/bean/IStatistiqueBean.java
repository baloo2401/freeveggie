package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.service.msg.BestRatedProductMsg;
import org.mdubois.freeveggie.service.msg.LastExchangeProductMsg;
import org.mdubois.freeveggie.service.msg.MostSharedProductMsg;
// </editor-fold>

/**
 * This class give database information statistics.
 *
 * @author Mickael Dubois
 */
public interface IStatistiqueBean {

    /**
     * Get a sorted list of {@link ProductMsg} from a given
     * {@link UserPartialMsg} order by the best noted.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserPartialId - The given {@link UserPartialMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be
     * return
     * @return A {@link List} of {@link {@link ProductMsg}
     */
    List<BestRatedProductMsg> getBestRatedProductByUser(final ContextMsg pContextMsg, final Long pUserPartialId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link GardenMsg}
     * order by the best noted.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenId - The given {@link GardenMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be
     * return
     * @return A {@link List} of {@link {@link ProductMsg}
     */
    List<BestRatedProductMsg> getBestRatedProductByGarden(final ContextMsg pContextMsg, final Long pGardenId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link RefCityMsg}
     * order by the best noted.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefCityId - The given {@link RefCityMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be
     * return
     * @return A {@link List} of {@link {@link ProductMsg}
     */
    List<BestRatedProductMsg> getBestRatedProductByCity(final ContextMsg pContextMsg, final Integer pRefCityId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link RefRegionMsg}
     * order by the best noted.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefRegionMsg - The given {@link RefRegionMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be
     * return
     * @return A {@link List} of {@link {@link ProductMsg}
     */
    List<BestRatedProductMsg> getBestRatedProductByRegion(final ContextMsg pContextMsg, final Integer pRefRegionId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given
     * {@link UserPartialMsg} order by the last exchange.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserPartialId - The given {@link UserPartialMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be
     * return
     * @return A {@link List} of {@link {@link ProductMsg}
     */
    List<LastExchangeProductMsg> getLastExchangeProductByUser(final ContextMsg pContextMsg, final Long pUserPartialId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link GardenMsg}
     * order by the last exchange.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenId - The given {@link GardenMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be
     * return
     * @return A {@link List} of {@link {@link ProductMsg}
     */
    List<LastExchangeProductMsg> getLastExchangeProductByGarden(final ContextMsg pContextMsg, final Long pGardenId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link ProductMsg}
     * order by the last exchange.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductId - The given {@link ProductMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be
     * return
     * @return A {@link List} of {@link {@link ProductMsg}
     */
    List<LastExchangeProductMsg> getLastExchangeProductByProduct(final ContextMsg pContextMsg, final Long pProductId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link RefCityMsg}
     * order by the last exchange.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefCityId - The given {@link RefCityMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be
     * return
     * @return A {@link List} of {@link {@link ProductMsg}
     */
    List<LastExchangeProductMsg> getLastExchangeProductByCity(final ContextMsg pContextMsg, final Integer pRefCityId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link RefRegionMsg}
     * order by the last exchange.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefRegionId - The given {@link RefRegionMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be
     * return
     * @return A {@link List} of {@link {@link ProductMsg}
     */
    List<LastExchangeProductMsg> getLastExchangeProductByRegion(final ContextMsg pContextMsg, final Integer pRefRegionId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given
     * {@link UserPartialMsg} order by the most shared (final ContextMsg
     * pContextMsg,in quantity).
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserPartialId - The given {@link UserPartialMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be
     * return
     * @return A {@link List} of {@link {@link ProductMsg}
     */
    List<MostSharedProductMsg> getMostSharedProductByUser(final ContextMsg pContextMsg, final Long pUserPartialId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link GardenMsg}
     * order by the most shared (final ContextMsg pContextMsg,in quantity).
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenId - The given {@link GardenMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be
     * return
     * @return A {@link List} of {@link {@link ProductMsg}
     */
    List<MostSharedProductMsg> getMostSharedProductByGarden(final ContextMsg pContextMsg, final Long pGardenId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link RefCityMsg}
     * order by the most shared (final ContextMsg pContextMsg,in quantity).
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefCityMsg - The given {@link RefCityMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be
     * return
     * @return A {@link List} of {@link {@link ProductMsg}
     */
    List<MostSharedProductMsg> getMostSharedProductByCity(final ContextMsg pContextMsg, final Integer pRefCityMsg, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link RefRegionMsg}
     * order by the most shared (final ContextMsg pContextMsg,in quantity).
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefRegionMsg - The given {@link RefRegionMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be
     * return
     * @return A {@link List} of {@link {@link ProductMsg}
     */
    List<MostSharedProductMsg> getMostSharedProductByRegion(final ContextMsg pContextMsg, final Integer pRefRegionMsg, final Pagination pPagination) throws BusinessException;
}
