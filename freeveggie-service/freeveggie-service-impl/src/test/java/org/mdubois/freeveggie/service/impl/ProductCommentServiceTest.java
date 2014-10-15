package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductCommentBO;
import org.mdubois.freeveggie.criteria.ProductCommentCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IProductCommentDAO;
import org.mdubois.freeveggie.dao.api.IProductDAO;
import org.mdubois.freeveggie.dao.api.IUserPartialDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.order.ProductCommentOrderColumn;
import org.mdubois.freeveggie.service.matcher.ProductCommentBOMatcher;
import org.mdubois.freeveggie.service.matcher.TechnicalInformationMatcher;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.ProductCommentMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
// </editor-fold>

/**
 *
 * @author francishuynh
 */
@RunWith(JMockit.class)
public class ProductCommentServiceTest {

    @Mocked
    private IUserPartialDAO mockUserPartialDAO;
    @Mocked
    private IProductDAO mockProductDAO;
    @Mocked
    private IProductCommentDAO mockProductCommentDAO;
    @Mocked
    private BusinessObjectConverter<ProductCommentBO, ProductCommentMsg> productCommentMsgConverter;
    @Mocked
    private Converter< ProductCommentMsg, ProductCommentBO> mockProductCommentBOConverter;
    @Mocked
    @SuppressWarnings("unused")
    private final SystemTime systemTime = null;

    //TODO : Assert that what returns converter is what the service returns
    /**
     * {@link Criteria}
     */
    private static QueryCriteria<ProductCommentCriteriaColumn> criteriaStatusEqualSetted = new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL, EvaluationStatus.SETTED);

    // <editor-fold defaultstate="collapsed" desc="Test add Comment">
    /**
     * Test that if the user in parameter does not exist we have an
     * BusinnessException.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void commentNoUser() throws BusinessException {
        final ProductService productService = new ProductService();

        final ProductCommentMsg pProductCommentMsg = new ProductCommentMsg();
        pProductCommentMsg.setComment("Comment");
        ProductMsg product = new ProductMsg();
        product.setId(1L);
        pProductCommentMsg.setProduct(product);
        pProductCommentMsg.setNote(EvaluationNote.BAD);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(1L);
        pProductCommentMsg.setWriter(writer);
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            {
                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);

                mockUserPartialDAO.get(1L);
                times = 1;
                returns(null);
            }
        };

        productService.comment(pProductCommentMsg);
    }

    /**
     * Test that if the product in parameter does not exist we have an
     * BusinnessException.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void commentNoProduct() throws BusinessException {

        final ProductService productService = new ProductService();

        final ProductCommentMsg pProductCommentMsg = new ProductCommentMsg();
        pProductCommentMsg.setComment("Comment");
        ProductMsg product = new ProductMsg();
        product.setId(1L);
        pProductCommentMsg.setProduct(product);
        pProductCommentMsg.setNote(EvaluationNote.BAD);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(1L);
        pProductCommentMsg.setWriter(writer);
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            {
                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                mockUserPartialDAO.get(1L);
                times = 1;
                returns(new PartialUserBO());

                mockProductDAO.get(1L);
                times = 1;
                returns(null);
            }
        };

        productService.comment(pProductCommentMsg);
    }

    /**
     * Test that a user can't comment on he's own product.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void commentFromTheOwner() throws BusinessException {
        final ProductService productService = new ProductService();

        final ProductCommentMsg pProductCommentMsg = new ProductCommentMsg();
        pProductCommentMsg.setComment("Comment");
        ProductMsg product = new ProductMsg();
        product.setId(1L);
        pProductCommentMsg.setProduct(product);
        pProductCommentMsg.setNote(EvaluationNote.BAD);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(2L);
        pProductCommentMsg.setWriter(writer);

        final Date now = new Date();

        new Expectations() {

            {
                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(2L);
                mockUserPartialDAO.get(2L);
                times = 1;
                returns(userBO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setOwner(userBO);

                ProductBO productBO = new ProductBO();
                productBO.setId(1L);
                productBO.setGarden(gardenBO);
                mockProductDAO.get(1L);
                times = 1;
                returns(productBO);

            }
        };

        productService.comment(pProductCommentMsg);
    }

    /**
     * Test that nobody can comment twice a product.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void commentTwice() throws BusinessException {
        final ProductService productService = new ProductService();

        final ProductCommentMsg pProductCommentMsg = new ProductCommentMsg();
        pProductCommentMsg.setComment("Comment");
        ProductMsg product = new ProductMsg();
        final long pProductId = 1L;
        product.setId(pProductId);
        pProductCommentMsg.setProduct(product);
        pProductCommentMsg.setNote(EvaluationNote.BAD);
        PartialUserMsg writer = new PartialUserMsg();
        final long pUserId = 2L;
        writer.setId(pUserId);
        pProductCommentMsg.setWriter(writer);

        final Date now = new Date();

        new Expectations() {

            {
                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(2L);
                mockUserPartialDAO.get(2L);
                times = 1;
                returns(userBO);

                GardenBO gardenBO = new GardenBO();

                ProductBO productBO = new ProductBO();
                productBO.setId(1L);
                productBO.setGarden(gardenBO);
                mockProductDAO.get(1L);
                times = 1;
                returns(productBO);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setId(10L);
                productCommentBO.setComment("Comment");
                productCommentBO.setProduct(productBO);
                productCommentBO.setNote(EvaluationNote.BAD);
                productCommentBO.setStatus(EvaluationStatus.SETTED);
                productCommentBO.setWriter(userBO);
                productCommentBO.setCreationDate(now);
                mockProductCommentDAO.getProductCommentByUserAndProduct(pUserId, pProductId);
                returns(productCommentBO);
            }
        };

        productService.comment(pProductCommentMsg);
    }

    /**
     * Test comment works.
     *
     * @throws BusinessException
     */
    @Test
    public void comment() throws BusinessException {
        final ProductService productService = new ProductService();

        final ProductCommentMsg pProductCommentMsg = new ProductCommentMsg();
        pProductCommentMsg.setComment("Comment");
        ProductMsg product = new ProductMsg();
        product.setId(1L);
        pProductCommentMsg.setProduct(product);
        pProductCommentMsg.setNote(EvaluationNote.BAD);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(2L);
        pProductCommentMsg.setWriter(writer);

        final Date now = new Date();

        new Expectations() {

            {
                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentMsgConverter", productCommentMsgConverter);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(2L);
                mockUserPartialDAO.get(2L);
                times = 1;
                returns(userBO);

                GardenBO gardenBO = new GardenBO();

                ProductBO productBO = new ProductBO();
                productBO.setId(1L);
                productBO.setGarden(gardenBO);
                mockProductDAO.get(1L);
                times = 1;
                returns(productBO);

                mockProductCommentDAO.getProductCommentByUserAndProduct(userBO.getId(), productBO.getId());
                returns(null);

                ProductCommentBO productCommentBO = new ProductCommentBO();

                productCommentMsgConverter.createNew(pProductCommentMsg);
                returns(productCommentBO);
                mockProductCommentDAO.save(productCommentBO);
            }
        };

        productService.comment(pProductCommentMsg);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Remove Comment">
    /**
     * Test that if we cant remove an unexiting comment.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void removeCommentNoComment() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                mockProductCommentDAO.get(1L);
                times = 1;
                returns(null);
            }
        };

        productService.removeComment(pProductCommentId);
    }

    /**
     * Test that we can't remove an archive comment.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void removeCommentArchived() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setStatus(EvaluationStatus.ARCHIVED);
                mockProductCommentDAO.get(1L);
                times = 1;
                returns(productCommentBO);
            }
        };

        productService.removeComment(pProductCommentId);
    }

    /**
     * That if we try to remove an already removed comment there is no pb and no
     * call to save.
     *
     * @throws BusinessException
     */
    @Test
    public void removeCommentAlreadyRemoved() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setStatus(EvaluationStatus.REMOVED);
                mockProductCommentDAO.get(1L);
                times = 1;
                returns(productCommentBO);
            }
        };

        productService.removeComment(pProductCommentId);
    }

    /**
     * Test that remove works.
     *
     * @throws BusinessException
     */
    @Test
    public void removeComment() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                ProductBO product = new ProductBO();
                product.setId(1L);

                Date now = new Date();

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setId(1L);
                productCommentBO.setComment("Comment");
                productCommentBO.setNote(EvaluationNote.BAD);
                productCommentBO.setStatus(EvaluationStatus.SETTED);
                productCommentBO.setWriter(writer);
                productCommentBO.setProduct(product);
                productCommentBO.setCreationDate(now);
                mockProductCommentDAO.get(1L);
                times = 1;
                returns(productCommentBO);

                ProductCommentBO productCommentBOExpected = new ProductCommentBO();
                productCommentBOExpected.setId(1L);
                productCommentBOExpected.setComment("Comment");
                productCommentBOExpected.setNote(EvaluationNote.BAD);
                productCommentBOExpected.setStatus(EvaluationStatus.REMOVED);
                productCommentBOExpected.setWriter(writer);
                productCommentBOExpected.setProduct(product);
                productCommentBOExpected.setCreationDate(now);

                mockProductCommentDAO.update(with(productCommentBOExpected, new ProductCommentBOMatcher(productCommentBOExpected)));
            }
        };

        productService.removeComment(pProductCommentId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Reactivate Comment">
    /**
     * Test that if we cant reactive an unexiting comment.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void reactivateCommentNoComment() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                mockProductCommentDAO.get(1L);
                times = 1;
                returns(null);
            }
        };

        productService.reactivateComment(pProductCommentId);
    }

    /**
     * Control that if we can't reactived an archive comment.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void reactivateCommentArchived() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setStatus(EvaluationStatus.ARCHIVED);
                mockProductCommentDAO.get(1L);
                times = 1;
                returns(productCommentBO);
            }
        };

        productService.reactivateComment(pProductCommentId);
    }

    /**
     * Control that we can reactivated an already activate comment.
     *
     * @throws BusinessException
     */
    @Test
    public void reactivateCommentAlreadyActivated() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setStatus(EvaluationStatus.SETTED);
                mockProductCommentDAO.get(1L);
                times = 1;
                returns(productCommentBO);
            }
        };

        productService.reactivateComment(pProductCommentId);
    }

    /**
     * Controle that reactivate works.
     *
     * @throws BusinessException
     */
    @Test
    public void reactivateComment() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                ProductBO product = new ProductBO();
                product.setId(1L);

                Date now = new Date();

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setId(1L);
                productCommentBO.setComment("Comment");
                productCommentBO.setNote(EvaluationNote.BAD);
                productCommentBO.setStatus(EvaluationStatus.REMOVED);
                productCommentBO.setWriter(writer);
                productCommentBO.setProduct(product);
                productCommentBO.setCreationDate(now);
                mockProductCommentDAO.get(1L);
                times = 1;
                returns(productCommentBO);

                ProductCommentBO productCommentBOExpected = new ProductCommentBO();
                productCommentBOExpected.setId(1L);
                productCommentBOExpected.setComment("Comment");
                productCommentBOExpected.setNote(EvaluationNote.BAD);
                productCommentBOExpected.setStatus(EvaluationStatus.SETTED);
                productCommentBOExpected.setWriter(writer);
                productCommentBOExpected.setProduct(product);
                productCommentBOExpected.setCreationDate(now);

                mockProductCommentDAO.update(with(productCommentBOExpected, new ProductCommentBOMatcher(productCommentBOExpected)));
            }
        };

        productService.reactivateComment(pProductCommentId);
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Archive Comment">
    /**
     * Control that we can't archive a unexisting comment
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void archiveNoComment() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                mockProductCommentDAO.get(1L);
                times = 1;
                returns(null);
            }
        };

        productService.archiveComment(pProductCommentId);
    }

    /**
     * Control that we can't archive a deleted comment.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void archiveCommentDeleted() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setStatus(EvaluationStatus.REMOVED);
                mockProductCommentDAO.get(1L);
                times = 1;
                returns(productCommentBO);
            }
        };

        productService.archiveComment(pProductCommentId);
    }

    /**
     * Control that we can archive an already archive comment without using
     * save.
     *
     * @throws BusinessException
     */
    @Test
    public void archiveCommentAlreadyArchive() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setStatus(EvaluationStatus.ARCHIVED);
                mockProductCommentDAO.get(1L);
                times = 1;
                returns(productCommentBO);
            }
        };

        productService.archiveComment(pProductCommentId);
    }

    /**
     * Control that archive works.
     *
     * @throws BusinessException
     */
    @Test
    public void archive() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                ProductBO product = new ProductBO();
                product.setId(1L);

                Date now = new Date();

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setId(1L);
                productCommentBO.setComment("Comment");
                productCommentBO.setNote(EvaluationNote.BAD);
                productCommentBO.setStatus(EvaluationStatus.SETTED);
                productCommentBO.setWriter(writer);
                productCommentBO.setProduct(product);
                productCommentBO.setCreationDate(now);
                mockProductCommentDAO.get(1L);
                times = 1;
                returns(productCommentBO);

                ProductCommentBO productCommentBOExpected = new ProductCommentBO();
                productCommentBOExpected.setId(1L);
                productCommentBOExpected.setComment("Comment");
                productCommentBOExpected.setNote(EvaluationNote.BAD);
                productCommentBOExpected.setStatus(EvaluationStatus.ARCHIVED);
                productCommentBOExpected.setWriter(writer);
                productCommentBOExpected.setProduct(product);
                productCommentBOExpected.setCreationDate(now);

                mockProductCommentDAO.update(with(productCommentBOExpected, new ProductCommentBOMatcher(productCommentBOExpected)));
            }
        };

        productService.archiveComment(pProductCommentId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unarchive Comment">
    /**
     * Control that we can't unarchive an unexisting comment.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unarchiveNoComment() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                mockProductCommentDAO.get(1L);
                times = 1;
                returns(null);
            }
        };

        productService.unarchiveComment(pProductCommentId);
    }

    /**
     * Control that we can unarchive a deleted comment.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unarchiveCommentDeleted() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setStatus(EvaluationStatus.REMOVED);
                mockProductCommentDAO.get(1L);
                times = 1;
                returns(productCommentBO);
            }
        };

        productService.unarchiveComment(pProductCommentId);
    }

    /**
     * Control that we don't call save when we unarchive an unarchived comment.
     *
     * @throws BusinessException
     */
    @Test
    public void unarchiveCommentAlreadyUnarchive() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setStatus(EvaluationStatus.SETTED);
                mockProductCommentDAO.get(1L);
                times = 1;
                returns(productCommentBO);
            }
        };

        productService.unarchiveComment(pProductCommentId);
    }

    /**
     * Control that unarchive works.
     *
     * @throws BusinessException
     */
    @Test
    public void unarchive() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductCommentId = 1L;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                ProductBO product = new ProductBO();
                product.setId(1L);

                Date now = new Date();

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setId(1L);
                productCommentBO.setComment("Comment");
                productCommentBO.setNote(EvaluationNote.BAD);
                productCommentBO.setStatus(EvaluationStatus.ARCHIVED);
                productCommentBO.setWriter(writer);
                productCommentBO.setProduct(product);
                productCommentBO.setCreationDate(now);
                mockProductCommentDAO.get(1L);
                times = 1;
                returns(productCommentBO);

                ProductCommentBO productCommentBOExpected = new ProductCommentBO();
                productCommentBOExpected.setId(1L);
                productCommentBOExpected.setComment("Comment");
                productCommentBOExpected.setNote(EvaluationNote.BAD);
                productCommentBOExpected.setStatus(EvaluationStatus.SETTED);
                productCommentBOExpected.setWriter(writer);
                productCommentBOExpected.setProduct(product);
                productCommentBOExpected.setCreationDate(now);

                mockProductCommentDAO.update(with(productCommentBOExpected, new ProductCommentBOMatcher(productCommentBOExpected)));
            }
        };

        productService.unarchiveComment(pProductCommentId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test on getProductCommentByProduct">
    /**
     * This test controle that, if the user id given in parameter is not
     * corresponding to a valid user database id, the service throw a business
     * exception
     *
     * @throws BusinessException Expected
     */
    @Test
    public void getProductCommentProductNotExit() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                mockProductCommentDAO.getProductCommentByProduct(pProductId, with(lTechnicalInformation, new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(null);

                mockProductCommentBOConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(productService.getProductComment(pProductId, pTechnicalInformation));
    }

    /**
     * This test that if the service is call with a null technical information
     * the underlying DAO is call with a criteria having the status equal to
     * setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductCommentWithNullTechnicalInformation() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<ProductCommentBO> productCommentBOs = new ArrayList<ProductCommentBO>();
                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBOs.add(productCommentBO);

                mockProductCommentDAO.getProductCommentByProduct(pProductId, with(lTechnicalInformation, new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(productCommentBOs);
                mockProductCommentBOConverter.convert(productCommentBOs);

            }
        };

        productService.getProductComment(pProductId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with an empty criteria the
     * underlying DAO is call with a criteria having the status equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductCommentWithEmptyCriteria() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<ProductCommentBO> productCommentBOs = new ArrayList<ProductCommentBO>();
                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBOs.add(productCommentBO);

                mockProductCommentDAO.getProductCommentByProduct(pProductId, with(lTechnicalInformation, new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(productCommentBOs);
                mockProductCommentBOConverter.convert(productCommentBOs);

            }
        };

        productService.getProductComment(pProductId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that don't have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductCommentWithCriteriaWithStatus() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        criterias.add(new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL, EvaluationStatus.ARCHIVED));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                List<ProductCommentBO> productCommentBOs = new ArrayList<ProductCommentBO>();
                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBOs.add(productCommentBO);
                mockProductCommentDAO.getProductCommentByProduct(pProductId, with(pTechnicalInformation, new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productCommentBOs);
                mockProductCommentBOConverter.convert(productCommentBOs);

            }
        };

        productService.getProductComment(pProductId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that don't have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductCommentWithCriteriaButNotStatus() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                List<ProductCommentBO> productCommentBOs = new ArrayList<ProductCommentBO>();
                productCommentBOs.add(productCommentBO);
                mockProductCommentDAO.getProductCommentByProduct(pProductId, with(pTechnicalInformation, new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productCommentBOs);
                mockProductCommentBOConverter.convert(productCommentBOs);
            }
        };

        productService.getProductComment(pProductId, pTechnicalInformation);
    }

    /**
     * This test that the pagination is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getProductCommentWithPagination() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                List<ProductCommentBO> productCommentBOs = new ArrayList<ProductCommentBO>();
                productCommentBOs.add(productCommentBO);
                mockProductCommentDAO.getProductCommentByProduct(pProductId, with(pTechnicalInformation, new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productCommentBOs);
                mockProductCommentBOConverter.convert(productCommentBOs);
            }
        };

        productService.getProductComment(pProductId, pTechnicalInformation);
    }

    /**
     * This test that the result order is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getProductCommentWithOrder() throws BusinessException {

        final ProductService productService = new ProductService();

        final Long pProductId = 1L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        pTechnicalInformation.setOrder(new ResultOrder<ProductCommentOrderColumn>(ProductCommentOrderColumn.CREATION_DATE, OrderWay.DESC));
        new Expectations() {

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                List<ProductCommentBO> productCommentBOs = new ArrayList<ProductCommentBO>();
                productCommentBOs.add(productCommentBO);
                mockProductCommentDAO.getProductCommentByProduct(pProductId, with(pTechnicalInformation, new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productCommentBOs);
                mockProductCommentBOConverter.convert(productCommentBOs);
            }
        };

        productService.getProductComment(pProductId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test on getProductCommentByWriter">
    /**
     * This test controle that, if the user id given in parameter is not
     * corresponding to a valid user database id, the service throw a business
     * exception
     *
     * @throws BusinessException Expected
     */
    @Test
    public void getProductCommentWriteUserNotExit() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                mockProductCommentDAO.getProductCommentByWriter(pUserWriterId, with(lTechnicalInformation, new TechnicalInformationMatcher(lTechnicalInformation)));
                times = 1;
                returns(null);

                mockProductCommentBOConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(productService.getProductCommentWrite(pUserWriterId, pTechnicalInformation));
    }

    /**
     * This test that if the service is call with a null technical information
     * the underlying DAO is call with a criteria having the status equal to
     * setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductCommentWriteWithNullTechnicalInformation() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                List<ProductCommentBO> productCommentBOs = new ArrayList<ProductCommentBO>();
                productCommentBOs.add(productCommentBO);
                mockProductCommentDAO.getProductCommentByWriter(pUserWriterId, with(lTechnicalInformation, new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(productCommentBOs);
                mockProductCommentBOConverter.convert(productCommentBOs);
            }
        };

        productService.getProductCommentWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with an empty criteria the
     * underlying DAO is call with a criteria having the status equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductCommentWriteWithEmptyCriteria() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                List<ProductCommentBO> productCommentBOs = new ArrayList<ProductCommentBO>();
                productCommentBOs.add(productCommentBO);
                mockProductCommentDAO.getProductCommentByWriter(pUserWriterId, with(lTechnicalInformation, new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(productCommentBOs);
                mockProductCommentBOConverter.convert(productCommentBOs);
            }
        };

        productService.getProductCommentWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that don't have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductCommentWriteWithCriteriaWithStatus() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        criterias.add(new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL, EvaluationStatus.ARCHIVED));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                List<ProductCommentBO> productCommentBOs = new ArrayList<ProductCommentBO>();
                productCommentBOs.add(productCommentBO);
                mockProductCommentDAO.getProductCommentByWriter(pUserWriterId, with(pTechnicalInformation, new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productCommentBOs);
                mockProductCommentBOConverter.convert(productCommentBOs);
            }
        };

        productService.getProductCommentWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that don't have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductCommentWriteWithCriteriaButNotStatus() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                List<ProductCommentBO> productCommentBOs = new ArrayList<ProductCommentBO>();
                productCommentBOs.add(productCommentBO);
                mockProductCommentDAO.getProductCommentByWriter(pUserWriterId, with(pTechnicalInformation, new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productCommentBOs);
                mockProductCommentBOConverter.convert(productCommentBOs);
            }
        };

        productService.getProductCommentWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that the pagination is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getProductCommentWriteWithPagination() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        new Expectations() {

            {

                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                List<ProductCommentBO> productCommentBOs = new ArrayList<ProductCommentBO>();
                productCommentBOs.add(productCommentBO);
                mockProductCommentDAO.getProductCommentByWriter(pUserWriterId, with(pTechnicalInformation, new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productCommentBOs);
                mockProductCommentBOConverter.convert(productCommentBOs);
            }
        };

        productService.getProductCommentWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that the result order is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getProductCommentWriteWithOrder() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        pTechnicalInformation.setOrder(new ResultOrder<ProductCommentOrderColumn>(ProductCommentOrderColumn.CREATION_DATE, OrderWay.DESC));
        new Expectations() {

            {
                Deencapsulation.setField(productService, "productCommentDAO", mockProductCommentDAO);
                Deencapsulation.setField(productService, "productCommentBOConverter", mockProductCommentBOConverter);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                List<ProductCommentBO> productCommentBOs = new ArrayList<ProductCommentBO>();
                productCommentBOs.add(productCommentBO);
                mockProductCommentDAO.getProductCommentByWriter(pUserWriterId, with(pTechnicalInformation, new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productCommentBOs);
                mockProductCommentBOConverter.convert(productCommentBOs);
            }
        };

        productService.getProductCommentWrite(pUserWriterId, pTechnicalInformation);
    }
    // </editor-fold>
}
