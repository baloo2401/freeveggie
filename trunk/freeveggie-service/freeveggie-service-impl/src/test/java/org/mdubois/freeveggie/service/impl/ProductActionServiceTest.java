package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.dao.api.IGardenDAO;
import org.mdubois.freeveggie.dao.api.IProductDAO;
import org.mdubois.freeveggie.dao.api.IRefProductDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.service.matcher.ProductBOMatcher;
import org.mdubois.freeveggie.service.msg.*;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class ProductActionServiceTest {

    // <editor-fold defaultstate="collapsed" desc="Test create">
    @Test(expected = BusinessException.class)
    public void createUnexistingGarden() throws BusinessException {
        final ProductService productService = new ProductService();

        final RefCityMsg refCityMsg = new RefCityMsg();
        final PartialUserMsg user = new PartialUserMsg();
        user.setId(10L);
        refCityMsg.setId(1);
        AddressMsg addressMsg = new AddressMsg();
        addressMsg.setStreetName("Rue de la loire");
        addressMsg.setCity(refCityMsg);
        GardenMsg gardenMsg = new GardenMsg();
        gardenMsg.setId(1L);
        ProductMsg productMsg = new ProductMsg();
        productMsg.setGarden(gardenMsg);
        productMsg.setName("My New Product");
        final RefProductMsg refProductMsg = new RefProductMsg();
        refProductMsg.setId(1234);
        productMsg.setReferenceProduct(refProductMsg);
        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;
            @Mocked
            private IGardenDAO mockGardenDAO;
            @Mocked
            private IRefProductDAO refProductDAO;

            {
                Deencapsulation.setField(productService, "refProductDAO", refProductDAO);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "gardenDAO", mockGardenDAO);

                final RefProductBO refProductBO = new RefProductBO();
                refProductBO.setId(1234);

                refProductDAO.get(1234);
                returns(refProductBO);

                mockGardenDAO.get(1L);
                returns(null);
            }
        };
        productService.create(productMsg);

    }

    @Test
    public void create() throws BusinessException {
        final ProductService productService = new ProductService();

        final RefCityMsg refCityMsg = new RefCityMsg();
        final PartialUserMsg user = new PartialUserMsg();
        user.setId(10L);
        refCityMsg.setId(1);
        AddressMsg addressMsg = new AddressMsg();
        addressMsg.setStreetName("Rue de la loire");
        addressMsg.setCity(refCityMsg);
        GardenMsg gardenMsg = new GardenMsg();
        gardenMsg.setId(1L);
        final ProductMsg productMsg = new ProductMsg();
        productMsg.setGarden(gardenMsg);
        productMsg.setName("My New Product");
        final RefProductMsg refProductMsg = new RefProductMsg();
        refProductMsg.setId(1234);
        productMsg.setReferenceProduct(refProductMsg);
        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;
            @Mocked
            private IGardenDAO mockGardenDAO;
            @Mocked
            private BusinessObjectConverter<ProductBO, ProductMsg> mockProductMsgConverter;
            @Mocked
            private IRefProductDAO refProductDAO;

            {
                Deencapsulation.setField(productService, "refProductDAO", refProductDAO);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(productService, "productMsgConverter", mockProductMsgConverter);

                final RefProductBO refProductBO = new RefProductBO();
                refProductBO.setId(1234);

                refProductDAO.get(1234);
                returns(refProductBO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(1L);
                mockGardenDAO.get(1L);
                returns(gardenBO);

                ProductBO productBO = new ProductBO();
                productBO.setGarden(gardenBO);
                productBO.setName("My New Product");
                mockProductMsgConverter.createNew(productMsg);
                returns(productBO);

                mockProductDAO.save(with(new ProductBOMatcher(productBO)));
            }
        };
        productService.create(productMsg);

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test update">
    @Test
    public void update() throws BusinessException {
        final ProductService productService = new ProductService();

        final RefCityBO refCityBO = new RefCityBO();
        final PartialUserBO user = new PartialUserBO();
        user.setId(10L);
        refCityBO.setId(1);
       
        final UpdateProductMsg productMsg = new UpdateProductMsg();
        productMsg.setId(12L);
        productMsg.setName("My New Product");
        productMsg.setPictureFilename("my picture");
        final RefProductMsg refProductMsg = new RefProductMsg();
        refProductMsg.setId(1234);
        productMsg.setReferenceProduct(refProductMsg);
        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;
            @Mocked
            private BusinessObjectConverter<ProductBO, UpdateProductMsg> mockProductMsgConverter;
            @Mocked
            private IRefProductDAO refProductDAO;

            {
                Deencapsulation.setField(productService, "refProductDAO", refProductDAO);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "updateProductMsgConverter", mockProductMsgConverter);

                ProductBO productBO = new ProductBO();
                productBO.setId(12L);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(1L);
                productBO.setGarden(gardenBO);
                productBO.setName("My New Product test");
                mockProductDAO.get(12L);
                returns(productBO);

                final RefProductBO refProductBO = new RefProductBO();
                refProductBO.setId(1234);

                refProductDAO.get(1234);
                returns(refProductBO);

                productBO.setName("My New Product");
                mockProductMsgConverter.update(productBO, productMsg);


                mockProductDAO.update(with(new ProductBOMatcher(productBO)));
            }
        };
        productService.update(productMsg);

    }

    @Test(expected = BusinessException.class)
    public void updateExecption() throws BusinessException {
        final ProductService productService = new ProductService();

        final RefCityMsg refCityMsg = new RefCityMsg();
        final PartialUserMsg user = new PartialUserMsg();
        user.setId(10L);
        refCityMsg.setId(1);
        AddressMsg addressMsg = new AddressMsg();
        addressMsg.setStreetName("Rue de la loire");
        addressMsg.setCity(refCityMsg);
        final UpdateProductMsg productMsg = new UpdateProductMsg();
        productMsg.setId(12L);
        productMsg.setName("My New Product");
        productMsg.setPictureFilename("my picture");
        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                mockProductDAO.get(12L);
                returns(null);

            }
        };
        productService.update(productMsg);

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Blacklist ">
    /**
     * Control that we can't blacklist a unexisting
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void blacklistNo() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                mockProductDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        productService.blacklist(pProductId);
    }

    /**
     * Control that we can't blacklist a deleted .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void blacklistDeleted() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                ProductBO productBO = new ProductBO();
                productBO.setStatus(Status.DELETED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);
            }
        };

        productService.blacklist(pProductId);
    }

    /**
     * Control that we can't blacklist a archived .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void blacklistArchived() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                ProductBO productBO = new ProductBO();
                productBO.setStatus(Status.ARCHIVED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);
            }
        };

        productService.blacklist(pProductId);
    }

    /**
     * Control that we can blacklist an already blacklist without using save.
     *
     * @throws BusinessException
     */
    @Test
    public void blacklistAlreadyBlacklist() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                ProductBO productBO = new ProductBO();
                productBO.setStatus(Status.BLACKLISTED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);
            }
        };

        productService.blacklist(pProductId);
    }

    /**
     * Control that blacklist works.
     *
     * @throws BusinessException
     */
    @Test
    public void blacklist() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {


                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                ProductBO product = new ProductBO();
                product.setId(1L);

                ProductBO productBO = new ProductBO();
                productBO.setId(1L);
                productBO.setStatus(Status.CREATED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);

                ProductBO productBOExpected = new ProductBO();
                productBOExpected.setId(1L);
                productBOExpected.setStatus(Status.BLACKLISTED);

                mockProductDAO.update(with(new ProductBOMatcher(productBOExpected)));
            }
        };

        productService.blacklist(pProductId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unblacklist">
    /**
     * Control that we can't unblacklist an unexisting .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unblacklistNo() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                mockProductDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        productService.unblacklist(pProductId);
    }

    /**
     * Control that we can unblacklist a deleted .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unblacklistDeleted() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                ProductBO productBO = new ProductBO();
                productBO.setStatus(Status.DELETED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);
            }
        };

        productService.unblacklist(pProductId);
    }

    /**
     * Control that we don't call save when we unblacklist an unblacklistd .
     *
     * @throws BusinessException
     */
    @Test
    public void unblacklistAlreadyUnblacklist() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                ProductBO productBO = new ProductBO();
                productBO.setStatus(Status.CREATED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);
            }
        };

        productService.unblacklist(pProductId);
    }

    /**
     * Control that unblacklist works.
     *
     * @throws BusinessException
     */
    @Test
    public void unblacklist() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                ProductBO product = new ProductBO();
                product.setId(1L);

                Date now = new Date();

                ProductBO productBO = new ProductBO();
                productBO.setId(1L);
                productBO.setStatus(Status.BLACKLISTED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);

                ProductBO productBOExpected = new ProductBO();
                productBOExpected.setId(1L);
                productBOExpected.setStatus(Status.CREATED);

                mockProductDAO.update(with(new ProductBOMatcher(productBOExpected)));
            }
        };

        productService.unblacklist(pProductId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test create">
    /**
     * Test that if we cant remove an unexiting .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void removeNo() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                mockProductDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        productService.remove(pProductId);
    }

    /**
     * Test that we can't remove an archive .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void removeArchived() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                ProductBO productBO = new ProductBO();
                productBO.setStatus(Status.ARCHIVED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);
            }
        };

        productService.remove(pProductId);
    }

    /**
     * That if we try to remove an already DELETED there is no pb and no call to
     * save.
     *
     * @throws BusinessException
     */
    @Test
    public void removeAlreadyDELETED() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                ProductBO productBO = new ProductBO();
                productBO.setStatus(Status.DELETED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);
            }
        };

        productService.remove(pProductId);
    }

    /**
     * Test that remove works.
     *
     * @throws BusinessException
     */
    @Test
    public void remove() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                ProductBO product = new ProductBO();
                product.setId(1L);

                Date now = new Date();

                ProductBO productBO = new ProductBO();
                productBO.setId(1L);
                productBO.setStatus(Status.CREATED);

                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);

                ProductBO productBOExpected = new ProductBO();
                productBOExpected.setId(1L);
                productBOExpected.setStatus(Status.DELETED);


                mockProductDAO.update(with(new ProductBOMatcher(productBOExpected)));
            }
        };

        productService.remove(pProductId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test reactivate">
    /**
     * Test that if we cant reactive an unexiting .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void reactivateNo() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                mockProductDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        productService.reactivate(pProductId);
    }

    /**
     * Control that if we can't reactived an archive .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void reactivateArchived() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                ProductBO productBO = new ProductBO();
                productBO.setStatus(Status.ARCHIVED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);
            }
        };

        productService.reactivate(pProductId);
    }

    /**
     * Control that we can reactivated an already activate .
     *
     * @throws BusinessException
     */
    @Test
    public void reactivateAlreadyActivated() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                ProductBO productBO = new ProductBO();
                productBO.setStatus(Status.CREATED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);
            }
        };

        productService.reactivate(pProductId);
    }

    /**
     * Controle that reactivate works.
     *
     * @throws BusinessException
     */
    @Test
    public void reactivate() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                ProductBO product = new ProductBO();
                product.setId(1L);

                Date now = new Date();

                ProductBO productBO = new ProductBO();
                productBO.setId(1L);
                productBO.setStatus(Status.DELETED);

                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);

                ProductBO productBOExpected = new ProductBO();
                productBOExpected.setId(1L);
                productBOExpected.setStatus(Status.CREATED);


                mockProductDAO.update(with(new ProductBOMatcher(productBOExpected)));
            }
        };

        productService.reactivate(pProductId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Archive ">
    /**
     * Control that we can't archive a unexisting
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void archiveNo() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                mockProductDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        productService.archive(pProductId);
    }

    /**
     * Control that we can't archive a deleted .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void archiveDeleted() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                ProductBO productBO = new ProductBO();
                productBO.setStatus(Status.DELETED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);
            }
        };

        productService.archive(pProductId);
    }

    /**
     * Control that we can archive an already archive without using save.
     *
     * @throws BusinessException
     */
    @Test
    public void archiveAlreadyArchive() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                ProductBO productBO = new ProductBO();
                productBO.setStatus(Status.ARCHIVED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);
            }
        };

        productService.archive(pProductId);
    }

    /**
     * Control that archive works.
     *
     * @throws BusinessException
     */
    @Test
    public void archive() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {


                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                ProductBO product = new ProductBO();
                product.setId(1L);

                Date now = new Date();

                ProductBO productBO = new ProductBO();
                productBO.setId(1L);
                productBO.setStatus(Status.CREATED);

                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);

                ProductBO productBOExpected = new ProductBO();
                productBOExpected.setId(1L);
                productBOExpected.setStatus(Status.ARCHIVED);


                mockProductDAO.update(with(new ProductBOMatcher(productBOExpected)));
            }
        };

        productService.archive(pProductId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unarchive ">
    /**
     * Control that we can't unarchive an unexisting .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unarchiveNo() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                mockProductDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        productService.unarchive(pProductId);
    }

    /**
     * Control that we can unarchive a deleted .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unarchiveDeleted() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                ProductBO productBO = new ProductBO();
                productBO.setStatus(Status.DELETED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);
            }
        };

        productService.unarchive(pProductId);
    }

    /**
     * Control that we don't call save when we unarchive an unarchived .
     *
     * @throws BusinessException
     */
    @Test
    public void unarchiveAlreadyUnarchive() throws BusinessException {
        final ProductService productService = new ProductService();


        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                ProductBO productBO = new ProductBO();
                productBO.setStatus(Status.CREATED);
                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);
            }
        };

        productService.unarchive(pProductId);
    }

    /**
     * Control that unarchive works.
     *
     * @throws BusinessException
     */
    @Test
    public void unarchive() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                ProductBO product = new ProductBO();
                product.setId(1L);

                Date now = new Date();

                ProductBO productBO = new ProductBO();
                productBO.setId(1L);
                productBO.setStatus(Status.ARCHIVED);

                mockProductDAO.get(1L);
                repeats(1);
                returns(productBO);

                ProductBO productBOExpected = new ProductBO();
                productBOExpected.setId(1L);
                productBOExpected.setStatus(Status.CREATED);


                mockProductDAO.update(with(new ProductBOMatcher(productBOExpected)));
            }
        };

        productService.unarchive(pProductId);
    }
    // </editor-fold>
}
