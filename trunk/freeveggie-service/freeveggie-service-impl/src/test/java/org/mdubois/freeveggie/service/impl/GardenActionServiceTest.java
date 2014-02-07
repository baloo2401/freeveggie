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
import org.mdubois.freeveggie.bo.AddressBO;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.RefCityBO;
import org.mdubois.freeveggie.dao.api.IAddressDAO;
import org.mdubois.freeveggie.dao.api.IGardenDAO;
import org.mdubois.freeveggie.dao.api.IUserPartialDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.service.api.IGardenService;
import org.mdubois.freeveggie.service.matcher.GardenBOMatcher;
import org.mdubois.freeveggie.service.msg.AddressMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.RefCityMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class GardenActionServiceTest {

    /**
     * {@link IGardenService}
     */

    private static final Date dateTest = new Date();

    // <editor-fold defaultstate="collapsed" desc="Test create">
    @Test
    public void createWithoutNewAddress() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final RefCityBO refCityBO = new RefCityBO();
        final PartialUserMsg user = new PartialUserMsg();
        user.setId(10L);
        refCityBO.setId(1);
        final AddressMsg addressMsg = new AddressMsg();
        addressMsg.setId(122L);
        final GardenMsg gardenMsg = new GardenMsg();
        gardenMsg.setAddress(addressMsg);
        gardenMsg.setName("My New Garden");
        gardenMsg.setOwner(user);
        new Expectations(){
            @Mocked
            private IGardenDAO mockGardenDAO;
            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IAddressDAO mockAddressDAO;
            @Mocked
            private BusinessObjectConverter<GardenBO, GardenMsg> mockGardenMsgConverter;
            @Mocked
            private BusinessObjectConverter<AddressBO, AddressMsg> mockaddressMsgConverter;
            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "addressDAO", mockAddressDAO);
                Deencapsulation.setField(gardenService, "gardenMsgConverter", mockGardenMsgConverter);
                Deencapsulation.setField(gardenService, "userPartialDAO", mockUserPartialDAO);


                AddressBO addressBO = new AddressBO();
                addressBO.setStreetName("Rue de la loire");
                addressBO.setCity(refCityBO);
                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(10L);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setName("My New Garden");
                gardenBO.setCreationDate(dateTest);
                mockGardenMsgConverter.createNew(gardenMsg);
                returns(gardenBO);

                mockAddressDAO.get(122L);
                returns(addressBO);

                gardenBO.setAddress(addressBO);

                mockUserPartialDAO.get(10L);
                returns(userBO);
                gardenBO.setOwner(userBO);

                mockGardenDAO.save(gardenBO);
            }
        };
        gardenService.create(gardenMsg);

    }

    @Test(expected=BusinessException.class)
    public void createWithUnexistingUserBOAsOwner() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final RefCityBO refCityBO = new RefCityBO();
        final PartialUserMsg user = new PartialUserMsg();
        user.setId(10L);
        refCityBO.setId(1);
        AddressMsg addressMsg = new AddressMsg();
        addressMsg.setId(122L);
        final GardenMsg gardenMsg = new GardenMsg();
        gardenMsg.setAddress(addressMsg);
        gardenMsg.setName("My New Garden");
        gardenMsg.setOwner(user);
        new Expectations(){
            @Mocked
            private IGardenDAO mockGardenDAO;
            @Mocked
            private IAddressDAO mockAddressDAO;
            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private BusinessObjectConverter<GardenBO, GardenMsg> mockGardenMsgConverter;
            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "addressDAO", mockAddressDAO);
                Deencapsulation.setField(gardenService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(gardenService, "gardenMsgConverter", mockGardenMsgConverter);


                AddressBO addressBO = new AddressBO();
                addressBO.setStreetName("Rue de la loire");
                addressBO.setCity(refCityBO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setName("My New Garden");
                gardenBO.setCreationDate(dateTest);
                mockGardenMsgConverter.createNew(gardenMsg);
                returns(gardenBO);

                mockAddressDAO.get(122L);
                returns(addressBO);
                gardenBO.setAddress(addressBO);

                mockUserPartialDAO.get(10L);
                returns(null);
            }
        };
        gardenService.create(gardenMsg);

    }

    @Test(expected=BusinessException.class)
    public void createWithNewAddressInexisting() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final RefCityBO refCityBO = new RefCityBO();
        final PartialUserMsg user = new PartialUserMsg();
        user.setId(10L);
        refCityBO.setId(1);
        AddressMsg addressMsg = new AddressMsg();
        addressMsg.setId(122L);
        final GardenMsg gardenMsg = new GardenMsg();
        gardenMsg.setAddress(addressMsg);
        gardenMsg.setName("My New Garden");
        gardenMsg.setOwner(user);
        new Expectations(){
            @Mocked
            private IGardenDAO mockGardenDAO;
            @Mocked
            private IAddressDAO mockAddressDAO;
            @Mocked
            private BusinessObjectConverter<GardenBO, GardenMsg> mockGardenMsgConverter;
            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "addressDAO", mockAddressDAO);
                Deencapsulation.setField(gardenService, "gardenMsgConverter", mockGardenMsgConverter);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(10L);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setName("My New Garden");
                gardenBO.setOwner(userBO);
                gardenBO.setCreationDate(dateTest);
                mockGardenMsgConverter.createNew(gardenMsg);
                returns(gardenBO);

                mockAddressDAO.get(122L);
                returns(null);

                }
        };
        gardenService.create(gardenMsg);

    }

    @Test
    public void createWithNewAddress() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final RefCityBO refCityBO = new RefCityBO();
        final PartialUserMsg user = new PartialUserMsg();
        user.setId(10L);
        refCityBO.setId(1);
        final AddressMsg addressMsg = new AddressMsg();
        addressMsg.setStreetName("line 1");
        final GardenMsg gardenMsg = new GardenMsg();
        gardenMsg.setAddress(addressMsg);
        gardenMsg.setName("My New Garden");
        gardenMsg.setOwner(user);
        new Expectations(){
            @Mocked
            private IGardenDAO mockGardenDAO;
            @Mocked
            private IAddressDAO mockAddressDAO;
            @Mocked
            private BusinessObjectConverter<GardenBO, GardenMsg> mockGardenMsgConverter;
            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private BusinessObjectConverter<AddressBO, AddressMsg> mockAddressMsgConverter;
            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "addressDAO", mockAddressDAO);
                Deencapsulation.setField(gardenService, "gardenMsgConverter", mockGardenMsgConverter);
                Deencapsulation.setField(gardenService, "addressMsgConverter", mockAddressMsgConverter);
                Deencapsulation.setField(gardenService, "userPartialDAO", mockUserPartialDAO);

                AddressBO addressBO = new AddressBO();
                addressBO.setStreetName("Rue de la loire");
                addressBO.setCity(refCityBO);
                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(10L);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setName("My New Garden");
                gardenBO.setCreationDate(dateTest);
                mockGardenMsgConverter.createNew(gardenMsg);
                returns(gardenBO);

                mockAddressMsgConverter.createNew(addressMsg);
                returns(addressBO);

                gardenBO.setAddress(addressBO);

                mockUserPartialDAO.get(10L);
                returns(userBO);
                gardenBO.setOwner(userBO);

                mockGardenDAO.save(gardenBO);
            }
        };
        gardenService.create(gardenMsg);

    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test update">
    @Test
    public void update() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final RefCityMsg refCityMsg = new RefCityMsg();
        final PartialUserMsg user = new PartialUserMsg();
        user.setId(10L);
        refCityMsg.setId(1);
        AddressMsg addressMsg = new AddressMsg();
        addressMsg.setStreetName("Rue de la loire");
        addressMsg.setCity(refCityMsg);
        final GardenMsg gardenMsg = new GardenMsg();
        gardenMsg.setId(12L);
        gardenMsg.setAddress(addressMsg);
        gardenMsg.setName("My New Garden");
        gardenMsg.setOwner(user);
        new Expectations(){
            @Mocked
            private IGardenDAO mockGardenDAO;
            @Mocked
            private BusinessObjectConverter<GardenBO, GardenMsg> mockGardenMsgConverter;
            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenMsgConverter", mockGardenMsgConverter);


                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(12L);
                gardenBO.setName("My New Garden Test");
                mockGardenDAO.get(12L);
                returns(gardenBO);

                gardenBO.setName("My New Garden");
                mockGardenMsgConverter.update(gardenBO, gardenMsg);

                mockGardenDAO.update(gardenBO);
            }
        };
        gardenService.update(gardenMsg);

    }

    @Test(expected=BusinessException.class)
    public void updateExecption() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final RefCityMsg refCityMsg = new RefCityMsg();
        final PartialUserMsg user = new PartialUserMsg();
        user.setId(10L);
        refCityMsg.setId(1);
        AddressMsg addressMsg = new AddressMsg();
        addressMsg.setStreetName("Rue de la loire");
        addressMsg.setCity(refCityMsg);
        final GardenMsg gardenMsg = new GardenMsg();
        gardenMsg.setId(12L);
        gardenMsg.setAddress(addressMsg);
        gardenMsg.setName("My New Garden");
        gardenMsg.setOwner(user);
        new Expectations(){
            @Mocked
            private IGardenDAO mockGardenDAO;
            @Mocked
            private BusinessObjectConverter<GardenBO, GardenMsg> mockGardenMsgConverter;
            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                mockGardenDAO.get(12L);
                returns(null);

            }
        };
        gardenService.update(gardenMsg);

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Blacklist ">
    /**
     * Control that we can't blacklist a unexisting
     * @throws BusinessException
     */
    @Test(expected=BusinessException.class)
    public void blacklistNo() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                mockGardenDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        gardenService.blacklist(pGardenId);
    }

    /**
     * Control that we can't blacklist a deleted .
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void blacklistDeleted() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setStatus(Status.DELETED);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);
            }
        };

        gardenService.blacklist(pGardenId);
    }

    /**
     * Control that we can blacklist an already blacklist  without using save.
     * @throws BusinessException
     */
    @Test
    public void blacklistAlreadyBlacklist() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setStatus(Status.BLACKLISTED);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);
            }
        };

        gardenService.blacklist(pGardenId);
    }

    /**
     * Control that blacklist works.
     * @throws BusinessException
     */
    @Test
    public void blacklist() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;
            {


                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                GardenBO garden = new GardenBO();
                garden.setId(1L);

                Date now = new Date();

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(1L);
                gardenBO.setStatus(Status.CREATED);
                gardenBO.setCreationDate(now);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);

                GardenBO gardenBOExpected = new GardenBO();
                gardenBOExpected.setId(1L);
                gardenBOExpected.setStatus(Status.BLACKLISTED);
                gardenBOExpected.setCreationDate(now);

                mockGardenDAO.update(with(new GardenBOMatcher(gardenBOExpected)));
            }
        };

        gardenService.blacklist(pGardenId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unblacklist ">
    /**
     * Control that we can't unblacklist an unexisting .
     * @throws BusinessException
     */
    @Test(expected=BusinessException.class)
    public void unblacklistNo() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                mockGardenDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        gardenService.unblacklist(pGardenId);
    }

    /**
     * Control that we can unblacklist a deleted .
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unblacklistDeleted() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setStatus(Status.DELETED);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);
            }
        };

        gardenService.unblacklist(pGardenId);
    }

    /**
     * Control that we don't call save when we unblacklist an unblacklistd .
     * @throws BusinessException
     */
    @Test
    public void unblacklistAlreadyUnblacklist() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setStatus(Status.CREATED);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);
            }
        };

        gardenService.unblacklist(pGardenId);
    }

    /**
     * Control that unblacklist works.
     * @throws BusinessException
     */
    @Test
    public void unblacklist() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                GardenBO garden = new GardenBO();
                garden.setId(1L);

                Date now = new Date();

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(1L);
                gardenBO.setStatus(Status.BLACKLISTED);
                gardenBO.setCreationDate(now);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);

                GardenBO gardenBOExpected = new GardenBO();
                gardenBOExpected.setId(1L);
                gardenBOExpected.setStatus(Status.CREATED);
                gardenBOExpected.setCreationDate(now);

                mockGardenDAO.update(with(new GardenBOMatcher(gardenBOExpected)));
            }
        };

        gardenService.unblacklist(pGardenId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test create">
    /**
     * Test that if we cant remove an unexiting .
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void removeNo() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                mockGardenDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        gardenService.remove(pGardenId);
    }

    /**
     * Test that we can't remove an archive .
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void removeArchived() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setStatus(Status.ARCHIVED);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);
            }
        };

        gardenService.remove(pGardenId);
    }

    /**
     * That if we try to remove an already DELETED  there is no pb and no call to save.
     * @throws BusinessException
     */
    @Test
    public void removeAlreadyDELETED() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setStatus(Status.DELETED);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);
            }
        };

        gardenService.remove(pGardenId);
    }

    /**
     * Test that remove works.
     * @throws BusinessException
     */
    @Test
    public void remove() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                GardenBO garden = new GardenBO();
                garden.setId(1L);

                Date now = new Date();

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(1L);
                gardenBO.setStatus(Status.CREATED);
                gardenBO.setCreationDate(now);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);

                GardenBO gardenBOExpected = new GardenBO();
                gardenBOExpected.setId(1L);
                gardenBOExpected.setStatus(Status.DELETED);
                gardenBOExpected.setCreationDate(now);

                mockGardenDAO.update(with(new GardenBOMatcher(gardenBOExpected)));
            }
        };

        gardenService.remove(pGardenId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test reactivate">
    /**
     * Test that if we cant reactive an unexiting .
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void reactivateNo() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                mockGardenDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        gardenService.reactivate(pGardenId);
    }

    /**
     * Control that if we can't reactived an archive .
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void reactivateArchived() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setStatus(Status.ARCHIVED);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);
            }
        };

        gardenService.reactivate(pGardenId);
    }

    /**
     * Control that we can reactivated an already activate .
     * @throws BusinessException
     */
    @Test
    public void reactivateAlreadyActivated() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setStatus(Status.CREATED);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);
            }
        };

        gardenService.reactivate(pGardenId);
    }

    /**
     * Controle that reactivate works.
     * @throws BusinessException
     */
    @Test
    public void reactivate() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                GardenBO garden = new GardenBO();
                garden.setId(1L);

                Date now = new Date();

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(1L);
                gardenBO.setStatus(Status.DELETED);
                gardenBO.setCreationDate(now);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);

                GardenBO gardenBOExpected = new GardenBO();
                gardenBOExpected.setId(1L);
                gardenBOExpected.setStatus(Status.CREATED);
                gardenBOExpected.setCreationDate(now);

                mockGardenDAO.update(with(new GardenBOMatcher(gardenBOExpected)));
            }
        };

        gardenService.reactivate(pGardenId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Archive ">
    /**
     * Control that we can't archive a unexisting
     * @throws BusinessException
     */
    @Test(expected=BusinessException.class)
    public void archiveNo() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                mockGardenDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        gardenService.archive(pGardenId);
    }

    /**
     * Control that we can't archive a deleted .
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void archiveDeleted() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setStatus(Status.DELETED);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);
            }
        };

        gardenService.archive(pGardenId);
    }

    /**
     * Control that we can archive an already archive  without using save.
     * @throws BusinessException
     */
    @Test
    public void archiveAlreadyArchive() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setStatus(Status.ARCHIVED);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);
            }
        };

        gardenService.archive(pGardenId);
    }

    /**
     * Control that archive works.
     * @throws BusinessException
     */
    @Test
    public void archive() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;
            {


                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                GardenBO garden = new GardenBO();
                garden.setId(1L);

                Date now = new Date();

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(1L);
                gardenBO.setStatus(Status.CREATED);
                gardenBO.setCreationDate(now);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);

                GardenBO gardenBOExpected = new GardenBO();
                gardenBOExpected.setId(1L);
                gardenBOExpected.setStatus(Status.ARCHIVED);
                gardenBOExpected.setCreationDate(now);

                mockGardenDAO.update(with(new GardenBOMatcher(gardenBOExpected)));
            }
        };

        gardenService.archive(pGardenId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unarchive ">
    /**
     * Control that we can't unarchive an unexisting .
     * @throws BusinessException
     */
    @Test(expected=BusinessException.class)
    public void unarchiveNo() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                mockGardenDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        gardenService.unarchive(pGardenId);
    }

    /**
     * Control that we can unarchive a deleted .
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unarchiveDeleted() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setStatus(Status.DELETED);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);
            }
        };

        gardenService.unarchive(pGardenId);
    }

    /**
     * Control that we don't call save when we unarchive an unarchived .
     * @throws BusinessException
     */
    @Test
    public void unarchiveAlreadyUnarchive() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setStatus(Status.CREATED);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);
            }
        };

        gardenService.unarchive(pGardenId);
    }

    /**
     * Control that unarchive works.
     * @throws BusinessException
     */
    @Test
    public void unarchive() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pGardenId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                GardenBO garden = new GardenBO();
                garden.setId(1L);

                Date now = new Date();

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(1L);
                gardenBO.setStatus(Status.ARCHIVED);
                gardenBO.setCreationDate(now);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);

                GardenBO gardenBOExpected = new GardenBO();
                gardenBOExpected.setId(1L);
                gardenBOExpected.setStatus(Status.CREATED);
                gardenBOExpected.setCreationDate(now);

                mockGardenDAO.update(with(new GardenBOMatcher(gardenBOExpected)));
            }
        };

        gardenService.unarchive(pGardenId);
    }
    // </editor-fold>


}
