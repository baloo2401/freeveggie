package org.mdubois.freeveggie.service.api;

import java.util.List;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.service.msg.BestRatedProductMsg;
import org.mdubois.freeveggie.service.msg.LastExchangeProductMsg;
import org.mdubois.freeveggie.service.msg.MostSharedProductMsg;

/**
 * This class give database information statistics.
 * @author Mickael Dubois
 */
public interface IStatistiqueService {

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link UserPartialMsg} order by the best noted.
     * @param pUserPartialId - The given {@link UserPartialMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductMsg}
     */
    List<BestRatedProductMsg> getBestRatedProductByUser(final Long pUserPartialId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link GardenMsg} order by the best noted.
     * @param pGardenId - The given {@link GardenMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductMsg}
     */
    List<BestRatedProductMsg> getBestRatedProductByGarden(final Long pGardenId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link RefCityMsg} order by the best noted.
     * @param pRefCityId - The given {@link RefCityMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductMsg}
     */
    List<BestRatedProductMsg> getBestRatedProductByCity(final Integer pRefCityId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link RefRegionMsg} order by the best noted.
     * @param pRefRegionMsg - The given {@link RefRegionMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductMsg}
     */
    List<BestRatedProductMsg> getBestRatedProductByRegion(final Integer pRefRegionId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link UserPartialMsg} order by the last exchange.
     * @param pUserPartialId - The given {@link UserPartialMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductMsg}
     */
    List<LastExchangeProductMsg> getLastExchangeProductByUser(final Long pUserPartialId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link GardenMsg} order by the last exchange.
     * @param pGardenId - The given {@link GardenMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductMsg}
     */
    List<LastExchangeProductMsg> getLastExchangeProductByGarden(final Long pGardenId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link ProductMsg} order by the last exchange.
     * @param pProductId - The given {@link ProductMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductMsg}
     */
    List<LastExchangeProductMsg> getLastExchangeProductByProduct(final Long pProductId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link RefCityMsg} order by the last exchange.
     * @param pRefCityId - The given {@link RefCityMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductMsg}
     */
    List<LastExchangeProductMsg> getLastExchangeProductByCity(final Integer pRefCityId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link RefRegionMsg} order by the last exchange.
     * @param pRefRegionId - The given {@link RefRegionMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductMsg}
     */
    List<LastExchangeProductMsg> getLastExchangeProductByRegion(final Integer pRefRegionId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link UserPartialMsg} order by the most shared (in quantity).
     * @param pUserPartialId - The given {@link UserPartialMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductMsg}
     */
    List<MostSharedProductMsg> getMostSharedProductByUser(final Long pUserPartialId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link GardenMsg} order by the most shared (in quantity).
     * @param pGardenId - The given {@link GardenMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductMsg}
     */
    List<MostSharedProductMsg> getMostSharedProductByGarden(final Long pGardenId, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link RefCityMsg} order by the most shared (in quantity).
     * @param pRefCityMsg - The given {@link RefCityMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductMsg}
     */
    List<MostSharedProductMsg> getMostSharedProductByCity(final Integer pRefCityMsg, final Pagination pPagination) throws BusinessException;

    /**
     * Get a sorted list of {@link ProductMsg} from a given {@link RefRegionMsg} order by the most shared (in quantity).
     * @param pRefRegionMsg - The given {@link RefRegionMsg} to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductMsg}
     */
    List<MostSharedProductMsg> getMostSharedProductByRegion(final Integer pRefRegionMsg, final Pagination pPagination) throws BusinessException;

}
