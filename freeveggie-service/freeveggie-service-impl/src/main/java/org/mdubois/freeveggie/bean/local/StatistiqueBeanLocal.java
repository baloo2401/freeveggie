package org.mdubois.freeveggie.bean.local;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.mdubois.freeveggie.bean.IStatistiqueBean;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.interceptor.MessageValidatorInterceptor;
import org.mdubois.freeveggie.framework.interceptor.TraceInterceptor;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.api.IStatistiqueService;
import org.mdubois.freeveggie.service.msg.BestRatedProductMsg;
import org.mdubois.freeveggie.service.msg.LastExchangeProductMsg;
import org.mdubois.freeveggie.service.msg.MostSharedProductMsg;

// </editor-fold>
/**
 * This class give database information statistics.
 *
 * @author Mickael Dubois
 *
 */
@Stateless
@Interceptors({MessageValidatorInterceptor.class, TraceInterceptor.class})
@Local(IStatistiqueBean.class)
public class StatistiqueBeanLocal implements IStatistiqueBean {

    /**
     * {@link IStatistiqueService}
     */
    @Inject
    private IStatistiqueService statistiqueService;
    /**
     * {@link IRightControlerService}
     */
    @Inject
    private IRightControlerService rightControlerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BestRatedProductMsg> getBestRatedProductByUser(
            ContextMsg pContextMsg, Long pUserPartialId, Pagination pPagination)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return statistiqueService.getBestRatedProductByUser(pUserPartialId,
                    pPagination);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BestRatedProductMsg> getBestRatedProductByGarden(
            ContextMsg pContextMsg, Long pGardenId, Pagination pPagination)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return statistiqueService.getBestRatedProductByGarden(pGardenId,
                    pPagination);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BestRatedProductMsg> getBestRatedProductByCity(
            ContextMsg pContextMsg, Integer pRefCityId, Pagination pPagination)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return statistiqueService.getBestRatedProductByCity(pRefCityId,
                    pPagination);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BestRatedProductMsg> getBestRatedProductByRegion(
            ContextMsg pContextMsg, Integer pRefRegionId, Pagination pPagination)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return statistiqueService.getBestRatedProductByRegion(pRefRegionId,
                    pPagination);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LastExchangeProductMsg> getLastExchangeProductByUser(
            ContextMsg pContextMsg, Long pUserPartialId, Pagination pPagination)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return statistiqueService.getLastExchangeProductByUser(
                    pUserPartialId, pPagination);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LastExchangeProductMsg> getLastExchangeProductByGarden(
            ContextMsg pContextMsg, Long pGardenId, Pagination pPagination)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return statistiqueService.getLastExchangeProductByGarden(pGardenId,
                    pPagination);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LastExchangeProductMsg> getLastExchangeProductByProduct(
            ContextMsg pContextMsg, Long pProductId, Pagination pPagination)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return statistiqueService.getLastExchangeProductByProduct(
                    pProductId, pPagination);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LastExchangeProductMsg> getLastExchangeProductByCity(
            ContextMsg pContextMsg, Integer pRefCityId, Pagination pPagination)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return statistiqueService.getLastExchangeProductByCity(pRefCityId,
                    pPagination);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LastExchangeProductMsg> getLastExchangeProductByRegion(
            ContextMsg pContextMsg, Integer pRefRegionId, Pagination pPagination)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return statistiqueService.getLastExchangeProductByRegion(
                    pRefRegionId, pPagination);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MostSharedProductMsg> getMostSharedProductByUser(
            ContextMsg pContextMsg, Long pUserPartialId, Pagination pPagination)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return statistiqueService.getMostSharedProductByUser(
                    pUserPartialId, pPagination);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MostSharedProductMsg> getMostSharedProductByGarden(
            ContextMsg pContextMsg, Long pGardenId, Pagination pPagination)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return statistiqueService.getMostSharedProductByGarden(pGardenId,
                    pPagination);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MostSharedProductMsg> getMostSharedProductByCity(
            ContextMsg pContextMsg, Integer pRefCityMsg, Pagination pPagination)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return statistiqueService.getMostSharedProductByCity(pRefCityMsg,
                    pPagination);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MostSharedProductMsg> getMostSharedProductByRegion(
            ContextMsg pContextMsg, Integer pRefRegionMsg,
            Pagination pPagination) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return statistiqueService.getMostSharedProductByRegion(
                    pRefRegionMsg, pPagination);
        } else {
            throw new AccessNotGrantedException();
        }
    }
}
