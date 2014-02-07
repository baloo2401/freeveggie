package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import javax.persistence.Query;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.BestRatedProductBO;
import org.mdubois.freeveggie.bo.LastExchangeProductBO;
import org.mdubois.freeveggie.bo.MostSharedProductBO;
import org.mdubois.freeveggie.dao.api.IStatistiqueDAO;
import org.mdubois.freeveggie.framework.dao.DataAccessObject;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.Pagination;

// </editor-fold>

/**
 * 
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({ DAOInterceptor.class })
public class StatistiqueDAO extends DataAccessObject implements IStatistiqueDAO {

	/**
	 * The class name of the best rated product result.
	 */
	private static final String BEST_RATED_PRODUCT_CLASS = BestRatedProductBO.class
			.getName();

	/**
	 * The class name of the most shared product result.
	 */
	private static final String MOST_SHARED_PRODUCT_CLASS = MostSharedProductBO.class
			.getName();

	/**
	 * The class name of the last shared product result.
	 */
	private static final String LAST_SHARED_PRODUCT_CLASS = LastExchangeProductBO.class
			.getName();

	/**
	 * The accepted request status.
	 */
	private static final Integer REQUEST_STATUS = RequestStatus.ACCEPTED
			.getValue();
	/**
	 * The accepted request status.
	 */
	private static final Integer EVALUATION_STATUS = EvaluationStatus.SETTED
			.getValue();

	/**
	 * The created product status.
	 */
	private static final Integer PRODUCT_STATUS = Status.CREATED.getValue();

	/**
	 * The best rated product by user request.
	 */
	private static final String BEST_RATED_PRODUCT_BY_USER = "SELECT NEW "
			+ BEST_RATED_PRODUCT_CLASS
			+ "(AVG(e._note), e.product) "
			// TODO : Mettre les classes utilisées en valeur static
			+ "FROM ProductCommentBO e "
			+ "WHERE e.product.garden.owner.id = :UserPartialBO "
			+ "AND e._status = " + EVALUATION_STATUS + " "
			+ "AND e.product._status = " + PRODUCT_STATUS + " "
			+ "GROUP BY e.product " + "ORDER BY AVG(e._note) DESC ";

	/**
	 * The best rated product by user request.
	 */
	private static final String BEST_RATED_PRODUCT_BY_GARDEN = "SELECT NEW "
			+ BEST_RATED_PRODUCT_CLASS
			+ "(AVG(e._note), e.product) "
			// TODO : Mettre les classes utilisées en valeur static
			+ "FROM ProductCommentBO e "
			+ "WHERE e.product.garden.id = :GardenBO " + "AND e._status = "
			+ EVALUATION_STATUS + " " + "AND e.product._status = "
			+ PRODUCT_STATUS + " " + "GROUP BY e.product "
			+ "ORDER BY AVG(e._note) DESC ";

	/**
	 * The best rated product by user request.
	 */
	private static final String BEST_RATED_PRODUCT_BY_CITY = "SELECT NEW "
			+ BEST_RATED_PRODUCT_CLASS
			+ "(AVG(e._note), e.product) "
			// TODO : Mettre les classes utilisées en valeur static
			+ "FROM ProductCommentBO e "
			+ "WHERE e.product.garden.address.city.id = :RefCityBO "
			+ "AND e._status = " + EVALUATION_STATUS + " "
			+ "AND e.product._status = " + PRODUCT_STATUS + " "
			+ "GROUP BY e.product " + "ORDER BY AVG(e._note) DESC ";

	/**
	 * The best rated product by user request.
	 */
	private static final String BEST_RATED_PRODUCT_BY_REGION = "SELECT NEW "
			+ BEST_RATED_PRODUCT_CLASS
			+ "(AVG(e._note), e.product) "
			// TODO : Mettre les classes utilisées en valeur static
			+ "FROM ProductCommentBO e "
			+ "WHERE e.product.garden.address.city.region.id = :RefRegionBO "
			+ "AND e._status = " + EVALUATION_STATUS + " "
			+ "AND e.product._status = " + PRODUCT_STATUS + " "
			+ "GROUP BY e.product " + "ORDER BY AVG(e._note) DESC ";

	/**
	 * The last shared product by user request.
	 */
	private static final String LAST_SHARED_PRODUCT_BY_USER = "SELECT NEW "
			+ LAST_SHARED_PRODUCT_CLASS + "(e.pickingDate, e.product) "
			+ "FROM ProductRequestBO e "
			+ "WHERE e.product.garden.owner.id = :UserPartialBO "
			+ "AND e._status = " + REQUEST_STATUS + " "
			+ "AND e.product._status = " + PRODUCT_STATUS + " "
			+ "AND e.pickingDate < current_date() "
			+ "ORDER BY e.pickingDate DESC";

	/**
	 * The last shared product by garden request.
	 */
	private static final String LAST_SHARED_PRODUCT_BY_GARDEN = "SELECT NEW "
			+ LAST_SHARED_PRODUCT_CLASS + "(e.pickingDate, e.product) "
			+ "FROM ProductRequestBO e "
			+ "WHERE e.product.garden.id = :GardenBO " + "AND e._status = "
			+ REQUEST_STATUS + " " + "AND e.product._status = "
			+ PRODUCT_STATUS + " " + "AND e.pickingDate < current_date() "
			+ "ORDER BY e.pickingDate DESC";

	/**
	 * The last shared product by product request.
	 */
	private static final String LAST_SHARED_PRODUCT_BY_PRODUCT = "SELECT NEW "
			+ LAST_SHARED_PRODUCT_CLASS + "(e.pickingDate, e.product) "
			+ "FROM ProductRequestBO e " + "WHERE e.product.id = :ProductBO "
			+ "AND e._status = " + REQUEST_STATUS + " "
			+ "AND e.product._status = " + PRODUCT_STATUS + " "
			+ "AND e.pickingDate < current_date() "
			+ "ORDER BY e.pickingDate DESC";

	/**
	 * The last shared product by city request.
	 */
	private static final String LAST_SHARED_PRODUCT_BY_CITY = "SELECT NEW "
			+ LAST_SHARED_PRODUCT_CLASS + "(e.pickingDate, e.product) "
			+ "FROM ProductRequestBO e "
			+ "WHERE e.product.garden.address.city.id = :RefCityBO "
			+ "AND e._status = " + REQUEST_STATUS + " "
			+ "AND e.product._status = " + PRODUCT_STATUS + " "
			+ "AND e.pickingDate < current_date() "
			+ "ORDER BY e.pickingDate DESC";

	/**
	 * The last shared product by region request.
	 */
	private static final String LAST_SHARED_PRODUCT_BY_REGION = "SELECT NEW "
			+ LAST_SHARED_PRODUCT_CLASS + "(e.pickingDate, e.product) "
			+ "FROM ProductRequestBO e "
			+ "WHERE e.product.garden.address.city.region.id = :RefRegionBO "
			+ "AND e._status = " + REQUEST_STATUS + " "
			+ "AND e.product._status = " + PRODUCT_STATUS + " "
			+ "AND e.pickingDate < current_date() "
			+ "ORDER BY e.pickingDate DESC";

	/**
	 * The most shared product by user request.
	 */
	private static final String MOST_SHARED_PRODUCT_BY_USER = "SELECT NEW "
			+ MOST_SHARED_PRODUCT_CLASS + "(SUM(e.quantity), e.product) "
			+ "FROM ProductRequestBO e "
			+ "WHERE e.product.garden.owner.id = :UserPartialBO "
			+ "AND e._status = " + REQUEST_STATUS + " "
			+ "AND e.product._status = " + PRODUCT_STATUS + " "
			+ "AND e.pickingDate < current_date() " + "GROUP BY e.product "
			+ "ORDER BY SUM(e.quantity) DESC";

	/**
	 * The most shared product by garden request.
	 */
	private static final String MOST_SHARED_PRODUCT_BY_GARDEN = "SELECT NEW "
			+ MOST_SHARED_PRODUCT_CLASS + "(SUM(e.quantity), e.product) "
			+ "FROM ProductRequestBO e "
			+ "WHERE e.product.garden.id = :GardenBO " + "AND e._status = "
			+ REQUEST_STATUS + " " + "AND e.product._status = "
			+ PRODUCT_STATUS + " " + "AND e.pickingDate < current_date() "
			+ "GROUP BY e.product " + "ORDER BY SUM(e.quantity) DESC";

	/**
	 * The most shared product by city request.
	 */
	private static final String MOST_SHARED_PRODUCT_BY_CITY = "SELECT NEW "
			+ MOST_SHARED_PRODUCT_CLASS + "(SUM(e.quantity), e.product) "
			+ "FROM ProductRequestBO e "
			+ "WHERE e.product.garden.address.city.id = :RefCityBO "
			+ "AND e._status = " + REQUEST_STATUS + " "
			+ "AND e.product._status = " + PRODUCT_STATUS + " "
			+ "AND e.pickingDate < current_date() " + "GROUP BY e.product "
			+ "ORDER BY SUM(e.quantity) DESC";

	/**
	 * The most shared product by region request.
	 */
	private static final String MOST_SHARED_PRODUCT_BY_REGION = "SELECT NEW "
			+ MOST_SHARED_PRODUCT_CLASS + "(SUM(e.quantity), e.product) "
			+ "FROM ProductRequestBO e "
			+ "WHERE e.product.garden.address.city.region.id = :RefRegionBO "
			+ "AND e._status = " + REQUEST_STATUS + " "
			+ "AND e.product._status = " + PRODUCT_STATUS + " "
			+ "AND e.pickingDate < current_date() " + "GROUP BY e.product "
			+ "ORDER BY SUM(e.quantity) DESC";

	/** {@inheritDoc} */
	@Override
	public List<BestRatedProductBO> getBestRatedProductOffAUser(
			Long pIdUserPartialBO, Pagination pPagination) {
		Query query = entityManager.createQuery(BEST_RATED_PRODUCT_BY_USER);
		query.setParameter("UserPartialBO", pIdUserPartialBO);

		addPagination(query, pPagination);

		return query.getResultList();
	}

	/** {@inheritDoc} */
	@Override
	public List<BestRatedProductBO> getBestRatedProductOffAGarden(
			Long pIdGardenBO, Pagination pPagination) {
		Query query = entityManager.createQuery(BEST_RATED_PRODUCT_BY_GARDEN);
		query.setParameter("GardenBO", pIdGardenBO);

		addPagination(query, pPagination);

		return query.getResultList();
	}

	/** {@inheritDoc} */
	@Override
	public List<BestRatedProductBO> getBestRatedProductOffACity(
			Integer pRefCityBO, Pagination pPagination) {
		Query query = entityManager.createQuery(BEST_RATED_PRODUCT_BY_CITY);
		query.setParameter("RefCityBO", pRefCityBO);

		addPagination(query, pPagination);

		return query.getResultList();
	}

	/** {@inheritDoc} */
	@Override
	public List<BestRatedProductBO> getBestRatedProductOffARegion(
			Integer pRefRegionBO, Pagination pPagination) {
		Query query = entityManager.createQuery(BEST_RATED_PRODUCT_BY_REGION);
		query.setParameter("RefRegionBO", pRefRegionBO);

		addPagination(query, pPagination);

		return query.getResultList();
	}

	/** {@inheritDoc} */
	@Override
	public List<LastExchangeProductBO> getLastExchangeProductOffAUser(
			Long pIdUserPartialBO, Pagination pPagination) {
		Query query = entityManager.createQuery(LAST_SHARED_PRODUCT_BY_USER);
		query.setParameter("UserPartialBO", pIdUserPartialBO);

		addPagination(query, pPagination);

		return query.getResultList();
	}

	/** {@inheritDoc} */
	@Override
	public List<LastExchangeProductBO> getLastExchangeProductOffAGarden(
			Long pIdGardenBO, Pagination pPagination) {
		Query query = entityManager.createQuery(LAST_SHARED_PRODUCT_BY_GARDEN);
		query.setParameter("GardenBO", pIdGardenBO);

		addPagination(query, pPagination);

		return query.getResultList();
	}

	/** {@inheritDoc} */
	@Override
	public List<LastExchangeProductBO> getLastExchangeProductOffAProduct(
			Long pProductBO, Pagination pPagination) {
		Query query = entityManager.createQuery(LAST_SHARED_PRODUCT_BY_PRODUCT);
		query.setParameter("ProductBO", pProductBO);

		addPagination(query, pPagination);

		return query.getResultList();
	}

	/** {@inheritDoc} */
	@Override
	public List<LastExchangeProductBO> getLastExchangeProductByCity(
			Integer pRefCityBO, Pagination pPagination) {
		Query query = entityManager.createQuery(LAST_SHARED_PRODUCT_BY_CITY);
		query.setParameter("RefCityBO", pRefCityBO);

		addPagination(query, pPagination);

		return query.getResultList();
	}

	/** {@inheritDoc} */
	@Override
	public List<LastExchangeProductBO> getLastExchangeProductByRegion(
			Integer pRefRegionBO, Pagination pPagination) {
		Query query = entityManager.createQuery(LAST_SHARED_PRODUCT_BY_REGION);
		query.setParameter("RefRegionBO", pRefRegionBO);

		addPagination(query, pPagination);

		return query.getResultList();
	}

	/** {@inheritDoc} */
	@Override
	public List<MostSharedProductBO> getMostSharedProductByUser(
			Long pIdUserPartialBO, Pagination pPagination) {

		Query query = entityManager.createQuery(MOST_SHARED_PRODUCT_BY_USER);
		query.setParameter("UserPartialBO", pIdUserPartialBO);

		addPagination(query, pPagination);

		return query.getResultList();
	}

	/** {@inheritDoc} */
	@Override
	public List<MostSharedProductBO> getMostSharedProductByGarden(
			Long pIdGardenBO, Pagination pPagination) {

		Query query = entityManager.createQuery(MOST_SHARED_PRODUCT_BY_GARDEN);
		query.setParameter("GardenBO", pIdGardenBO);

		addPagination(query, pPagination);

		return query.getResultList();
	}

	/** {@inheritDoc} */
	@Override
	public List<MostSharedProductBO> getMostSharedProductByCity(
			Integer pRefCityBO, Pagination pPagination) {

		Query query = entityManager.createQuery(MOST_SHARED_PRODUCT_BY_CITY);
		query.setParameter("RefCityBO", pRefCityBO);

		addPagination(query, pPagination);

		return query.getResultList();
	}

	/** {@inheritDoc} */
	@Override
	public List<MostSharedProductBO> getMostSharedProductByRegion(
			Integer pRefRegionBO, Pagination pPagination) {
		Query query = entityManager.createQuery(MOST_SHARED_PRODUCT_BY_REGION);
		query.setParameter("RefRegionBO", pRefRegionBO);

		addPagination(query, pPagination);

		return query.getResultList();
	}

}
