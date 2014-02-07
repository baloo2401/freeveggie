package org.mdubois.freeveggie.dao.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.framework.service.Pagination;
// </editor-fold>

/**
 * This class give database information statistics.
 * @author Mickael Dubois
 */
public interface IStatistiqueDAO {

    /**
     * Get a sorted list of {@link ProductBO} from a given {@link UserPartialBO} order by the best noted.
     * @param pIdUserPartialBO - The given {@link UserPartialBO} id to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<BestRatedProductBO> getBestRatedProductOffAUser(final Long pIdUserPartialBO, final Pagination pPagination);

    /**
     * Get a sorted list of {@link ProductBO} from a given {@link GardenBO} order by the best noted.
     * @param pIdGardenBO - The given {@link GardenBO} id to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<BestRatedProductBO> getBestRatedProductOffAGarden(final Long pIdGardenBO, final Pagination pPagination);

    /**
     * Get a sorted list of {@link ProductBO} from a given {@link RefCityBO} order by the best noted.
     * @param pIdRefCityBO - The given {@link RefCityBO} id to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<BestRatedProductBO> getBestRatedProductOffACity(final Integer pIdRefCityBO, final Pagination pPagination);

    /**
     * Get a sorted list of {@link ProductBO} from a given {@link RefRegionBO} order by the best noted.
     * @param pIdRefRegionBO - The given {@link RefRegionBO} id to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<BestRatedProductBO> getBestRatedProductOffARegion(final Integer pIdRefRegionBO, final Pagination pPagination);

    /**
     * Get a sorted list of {@link ProductBO} from a given {@link UserPartialBO} order by the last exchange.
     * @param pIdUserPartialBO - The given {@link UserPartialBO} id to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<LastExchangeProductBO> getLastExchangeProductOffAUser(final Long pIdUserPartialBO, final Pagination pPagination);

    /**
     * Get a sorted list of {@link ProductBO} from a given {@link GardenBO} order by the last exchange.
     * @param pIdGardenBO - The given {@link GardenBO} id to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<LastExchangeProductBO> getLastExchangeProductOffAGarden(final Long pIdGardenBO, final Pagination pPagination);

    /**
     * Get a sorted list of {@link ProductBO} from a given {@link ProductBO} order by the last exchange.
     * @param pProductBO - The given {@link ProductBO} id to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<LastExchangeProductBO> getLastExchangeProductOffAProduct(final Long pIdProductO, final Pagination pPagination);

    /**
     * Get a sorted list of {@link ProductBO} from a given {@link RefCityBO} order by the last exchange.
     * @param pIdRefCityBO - The given {@link RefCityBO} id to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<LastExchangeProductBO> getLastExchangeProductByCity(final Integer pIdRefCityBO, final Pagination pPagination);

    /**
     * Get a sorted list of {@link ProductBO} from a given {@link RefRegionBO} order by the last exchange.
     * @param pIdRefRegionBO - The given {@link RefRegionBO} id to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<LastExchangeProductBO> getLastExchangeProductByRegion(final Integer pIdRefRegionBO, final Pagination pPagination);

    /**
     * Get a sorted list of {@link ProductBO} from a given {@link UserPartialBO} order by the most shared (in quantity).
     * @param pIdUserPartialBO - The given {@link UserPartialBO} id to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<MostSharedProductBO> getMostSharedProductByUser(final Long pIdUserPartialBO, final Pagination pPagination);

    /**
     * Get a sorted list of {@link ProductBO} from a given {@link GardenBO} order by the most shared (in quantity).
     * @param pIdGardenBO - The given {@link GardenBO} id to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<MostSharedProductBO> getMostSharedProductByGarden(final Long pIdGardenBO, final Pagination pPagination);

    /**
     * Get a sorted list of {@link ProductBO} from a given {@link RefCityBO} order by the most shared (in quantity).
     * @param pIdRefCityBO - The given {@link RefCityBO} id to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<MostSharedProductBO> getMostSharedProductByCity(final Integer pIdRefCityBO, final Pagination pPagination);

    /**
     * Get a sorted list of {@link ProductBO} from a given {@link RefRegionBO} order by the most shared (in quantity).
     * @param pIdRefRegionBO - The given {@link RefRegionBO} id to look in for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<MostSharedProductBO> getMostSharedProductByRegion(final Integer pIdRefRegionBO, final Pagination pPagination);

}
