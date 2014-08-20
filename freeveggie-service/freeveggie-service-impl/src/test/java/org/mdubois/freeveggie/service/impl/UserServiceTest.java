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
import org.mdubois.freeveggie.UserStatus;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.criteria.UserCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IUserDAO;
import org.mdubois.freeveggie.dao.api.IUserPartialDAO;
import org.mdubois.freeveggie.dao.impl.*;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.order.UserOrderColumn;
import org.mdubois.freeveggie.service.api.IUserService;
import org.mdubois.freeveggie.service.matcher.TechnicalInformationMatcher;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class UserServiceTest {

    // <editor-fold defaultstate="collapsed" desc="Get user by id">
    @Test(expected = BusinessException.class)
    public void getUserByIdExc() throws BusinessException {
        final IUserService service = new UserService();
        final Long userId = 1L;

        new Expectations() {

            @Mocked
            private UserDAO mockUserDAO;

            {
                Deencapsulation.setField(service, "userDAO", mockUserDAO);

                mockUserDAO.get(userId);
                repeats(1);
                returns(null);
            }
        };

        service.getUserById(1L);
    }

    @Test
    public void getUserById() throws BusinessException {

        final IUserService service = new UserService();
        final Long userId = 1L;
        final UserBO userExpected = new UserBO();
        userExpected.setId(userId);

        new Expectations() {

            @Mocked
            private UserDAO mockUserDAO;
            @Mocked
            private Converter<UserMsg, UserBO> mockUserBOConverter;

            {
                Deencapsulation.setField(service, "userDAO", mockUserDAO);
                Deencapsulation.setField(service, "userBOConverter", mockUserBOConverter);

                mockUserDAO.get(userId);
                returns(userExpected);

                mockUserBOConverter.convert(userExpected);
            }
        };

        service.getUserById(1L);
    }

// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get user partial by id">
    @Test(expected = BusinessException.class)
    public void getUserPartialByIdExc() throws BusinessException {
        final IUserService service = new UserService();
        final Long userId = 1L;

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;

            {
                Deencapsulation.setField(service, "userPartialDAO", mockUserPartialDAO);

                mockUserPartialDAO.get(userId);
                repeats(1);
                returns(null);
            }
        };

        service.getUserPartialById(1L);
    }

    @Test
    public void getUserPartialById() throws BusinessException {

        final IUserService service = new UserService();
        final Long userId = 1L;
        final PartialUserBO userExpected = new PartialUserBO();
        userExpected.setId(userId);

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockPartialDAO;
            @Mocked
            private Converter<PartialUserMsg, PartialUserBO> mockPartialUserBOConverter;

            {
                Deencapsulation.setField(service, "userPartialDAO", mockPartialDAO);
                Deencapsulation.setField(service, "partialUserBOConverter", mockPartialUserBOConverter);

                mockPartialDAO.get(userId);
                returns(userExpected);

                mockPartialUserBOConverter.convert(userExpected);
            }
        };

        service.getUserPartialById(1L);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="change role">
    @Test(expected = BusinessException.class)
    public void changeRoleNoUser() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);

                userDAO.get(122L);

                returns(null);

            }
        };
        userService.changeRole(122L, UserRole.USER);
    }

    @Test
    public void changeRole() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.ADMIN);
                userDAO.get(122L);
                returns(userExc);

                userDAO.save(with(new UserBOMatcher(userExc)));
            }
        };
        userService.changeRole(122L, UserRole.USER);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Upgrade">
    @Test(expected=BusinessException.class)
    public void upgradeNoUser() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);
                userDAO.get(122L);
                returns(null);

            }
        };
        userService.upgrade(122L);
    }

    @Test
    public void upgradeAdmin() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.ADMIN);
                userDAO.get(122L);
                returns(userExc);

                userDAO.save(with(new UserBOMatcher(userExc)));
            }
        };
        userService.upgrade(122L);
    }

    @Test
    public void upgradeUser() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.USER);
                userDAO.get(122L);
                returns(userExc);

                userDAO.save(with(new UserBOMatcher(userExc)));
            }
        };
        userService.upgrade(122L);
    }

    @Test(expected=BusinessException.class)
    public void upgradeSuperAdmin() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.SUPERADMIN);
                userDAO.get(122L);
                returns(userExc);

            }
        };
        userService.upgrade(122L);
    }

    @Test(expected=BusinessException.class)
    public void upgradeManager() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.MANAGER);
                userDAO.get(122L);
                returns(userExc);

            }
        };
        userService.upgrade(122L);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Downgrade">
    @Test(expected=BusinessException.class)
    public void downgradeNoUser() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);

                userDAO.get(122L);
                returns(null);
            }
        };
        userService.downgrade(122L);
    }

    @Test(expected=BusinessException.class)
    public void downgradeAdmin() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.ADMIN);
                userDAO.get(122L);
                returns(userExc);            }
        };
        userService.downgrade(122L);
    }

    @Test(expected=BusinessException.class)
    public void downgradeUser() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.USER);
                userDAO.get(122L);
                returns(userExc);
            }
        };
        userService.downgrade(122L);
    }

    @Test
    public void downgradeSuperAdmin() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.SUPERADMIN);
                userDAO.get(122L);
                returns(userExc);

                userDAO.save(with(new UserBOMatcher(userExc)));

            }
        };
        userService.downgrade(122L);
    }

    @Test
    public void downgradeManager() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.MANAGER);
                userDAO.get(122L);
                returns(userExc);

                userDAO.save(with(new UserBOMatcher(userExc)));


            }
        };
        userService.downgrade(122L);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Validate">
    @Test(expected=BusinessException.class)
    public void validateNoUser() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);
                userDAO.getUserByUUID("122L");
                returns(null);

            }
        };
        userService.validate("122L");
    }

    @Test
    public void validateNew() throws BusinessException {

        final IUserService userService = new UserService();

        final String uuid = "Boro34r+B9yCtRCJY5rLHw==";
        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.ADMIN);
                userExc.setStatus(UserStatus.NEW);
                userDAO.getUserByUUID(uuid);
                returns(userExc);

                userDAO.update(with(new UserBOMatcher(userExc)));
            }
        };
        userService.validate(uuid);
    }

    @Test(expected=BusinessException.class)
    public void validateValidated() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.SUPERADMIN);
                userExc.setStatus(UserStatus.VALIDED);
                userDAO.getUserByUUID("122L");
                returns(userExc);

            }
        };
        userService.validate("122L");
    }

    @Test(expected=BusinessException.class)
    public void validateDeleted() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.SUPERADMIN);
                userExc.setStatus(UserStatus.DELETED);
                userDAO.getUserByUUID("122L");
                returns(userExc);

            }
        };
        userService.validate("122L");
    }

    @Test(expected=BusinessException.class)
    public void validateBlacklisted() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.SUPERADMIN);
                userExc.setStatus(UserStatus.BLACKLISTED);
                userDAO.getUserByUUID("122L");
                returns(userExc);

            }
        };
        userService.validate("122L");
    }

    @Test(expected=BusinessException.class)
    public void validateArchived() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.SUPERADMIN);
                userExc.setStatus(UserStatus.ARCHIVED);
                userDAO.getUserByUUID("122L");
                returns(userExc);

            }
        };
        userService.validate("122L");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Delete">
    @Test(expected=BusinessException.class)
    public void deleteNoUser() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);
                userDAO.get(122L);
                returns(null);

            }
        };
        userService.delete(122L);
    }

    @Test(expected=BusinessException.class)
    public void deleteNew() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.ADMIN);
                userExc.setStatus(UserStatus.NEW);
                userDAO.get(122L);
                returns(userExc);

            }
        };
        userService.delete(122L);
    }

    @Test
    public void deleteValidated() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.SUPERADMIN);
                userExc.setStatus(UserStatus.VALIDED);
                userDAO.get(122L);
                returns(userExc);

                userDAO.update(with(new UserBOMatcher(userExc)));

            }
        };
        userService.delete(122L);
    }

    @Test(expected=BusinessException.class)
    public void deleteDeleted() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.SUPERADMIN);
                userExc.setStatus(UserStatus.DELETED);
                userDAO.get(122L);
                returns(userExc);

            }
        };
        userService.delete(122L);
    }

    @Test(expected=BusinessException.class)
    public void deleteBlacklisted() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.SUPERADMIN);
                userExc.setStatus(UserStatus.BLACKLISTED);
                userDAO.get(122L);
                returns(userExc);

            }
        };
        userService.delete(122L);
    }

    @Test(expected=BusinessException.class)
    public void deleteArchived() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.SUPERADMIN);
                userExc.setStatus(UserStatus.ARCHIVED);
                userDAO.get(122L);
                returns(userExc);

            }
        };
        userService.delete(122L);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="undelete">
    @Test(expected=BusinessException.class)
    public void undeleteNoUser() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);
                userDAO.get(122L);
                returns(null);

            }
        };
        userService.undelete(122L);
    }

    @Test(expected=BusinessException.class)
    public void undeleteNew() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.ADMIN);
                userExc.setStatus(UserStatus.NEW);
                userDAO.get(122L);
                returns(userExc);

            }
        };
        userService.undelete(122L);
    }

    @Test(expected=BusinessException.class)
    public void undeleteValidated() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.SUPERADMIN);
                userExc.setStatus(UserStatus.VALIDED);
                userDAO.get(122L);
                returns(userExc);

            }
        };
        userService.undelete(122L);
    }

    @Test
    public void undeleteDeleted() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.SUPERADMIN);
                userExc.setStatus(UserStatus.DELETED);
                userDAO.get(122L);
                returns(userExc);

                userDAO.update(with(new UserBOMatcher(userExc)));

            }
        };
        userService.undelete(122L);
    }

    @Test(expected=BusinessException.class)
    public void undeleteBlacklisted() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.SUPERADMIN);
                userExc.setStatus(UserStatus.BLACKLISTED);
                userDAO.get(122L);
                returns(userExc);

            }
        };
        userService.undelete(122L);
    }

    @Test(expected=BusinessException.class)
    public void undeleteArchived() throws BusinessException {

        final IUserService userService = new UserService();

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(userService, "userDAO", userDAO);


                UserBO userExc = new UserBO();
                userExc.setId(122L);
                userExc.setRole(UserRole.SUPERADMIN);
                userExc.setStatus(UserStatus.ARCHIVED);
                userDAO.get(122L);
                returns(userExc);

            }
        };
        userService.undelete(122L);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get user by city">
    @Test
    public void getUserByCityNullResult() throws BusinessException {


        final IUserService userService = new UserService();

        final Integer cityId = 1;
        final Integer prodId = 1;

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOConverter;
            {
                Deencapsulation.setField(userService, "userDAO", userDAO);
                Deencapsulation.setField(userService, "userBOConverter", userBOConverter);

                TechnicalInformation<UserCriteriaColumn, UserOrderColumn> techInfo = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
                QueryCriteria<UserCriteriaColumn> userCriteria = new QueryCriteria<UserCriteriaColumn>(UserCriteriaColumn.STATUS, CriteriaOperation.EQUAL, UserStatus.VALIDED);
                techInfo.addCriteria(userCriteria);

                userDAO.getUserByCityAndProduct(cityId, prodId, with(new TechnicalInformationMatcher(techInfo)));
                returns(null);

                userBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(userService.getUserByCity(cityId, prodId, null));
    }

    @Test()
    public void getUserByCity() throws BusinessException {

        final IUserService userService = new UserService();

        final Integer cityId = 1;
        final Integer prodId = 1;
        RefCityBO expectedCity = new RefCityBO();
        expectedCity.setId(cityId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private UserDAO mockUserDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOConverter;

            {
                Deencapsulation.setField(userService, "userDAO", mockUserDAO);
                Deencapsulation.setField(userService, "userBOConverter", userBOConverter);

                RefCityBO city = new RefCityBO();
                city.setId(cityId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<UserCriteriaColumn, UserOrderColumn> techInfo = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
                QueryCriteria<UserCriteriaColumn> userCriteria = new QueryCriteria<UserCriteriaColumn>(UserCriteriaColumn.STATUS, CriteriaOperation.EQUAL, UserStatus.VALIDED);
                techInfo.addCriteria(userCriteria);

                mockUserDAO.getUserByCityAndProduct(cityId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<UserBO> resList = new ArrayList<UserBO>();

                AddressBO address = new AddressBO();
                address.setCity(city);

                List<ProductBO> userProducts = new ArrayList<ProductBO>();
                ProductBO userProduct = new ProductBO();
                userProduct.setReferenceProduct(refProduct);
                userProducts.add(userProduct);

                UserBO user = new UserBO();
                user.setHomeAddress(address);
                List<GardenBO> userGardens = new ArrayList<GardenBO>();
                GardenBO garden = new GardenBO();
                garden.setId(11L);
                garden.setProducts(userProducts);
                user.setGardens(userGardens);
                resList.add(user);

                user = new UserBO();
                user.setHomeAddress(address);
                user.setGardens(userGardens);
                resList.add(user);

                returns(resList);
                userBOConverter.convert(resList);
            }
        };
        userService.getUserByCity(cityId, prodId, null);
    }

    @Test()
    public void getUserByCityEmptyTechnicalInformation() throws BusinessException {

        final IUserService userService = new UserService();

        final Integer cityId = 1;
        final Integer prodId = 1;
        RefCityBO expectedCity = new RefCityBO();
        expectedCity.setId(cityId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private UserDAO mockUserDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOConverter;

            {
                Deencapsulation.setField(userService, "userDAO", mockUserDAO);
                Deencapsulation.setField(userService, "userBOConverter", userBOConverter);

                RefCityBO city = new RefCityBO();
                city.setId(cityId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<UserCriteriaColumn, UserOrderColumn> techInfo = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
                QueryCriteria<UserCriteriaColumn> userCriteria = new QueryCriteria<UserCriteriaColumn>(UserCriteriaColumn.STATUS, CriteriaOperation.EQUAL, UserStatus.VALIDED);
                techInfo.addCriteria(userCriteria);

                mockUserDAO.getUserByCityAndProduct(cityId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<UserBO> resList = new ArrayList<UserBO>();

                AddressBO address = new AddressBO();
                address.setCity(city);

                List<ProductBO> userProducts = new ArrayList<ProductBO>();
                ProductBO userProduct = new ProductBO();
                userProduct.setReferenceProduct(refProduct);
                userProducts.add(userProduct);

                UserBO user = new UserBO();
                user.setHomeAddress(address);
                List<GardenBO> userGardens = new ArrayList<GardenBO>();
                GardenBO garden = new GardenBO();
                garden.setId(11L);
                garden.setProducts(userProducts);
                user.setGardens(userGardens);
                resList.add(user);

                user = new UserBO();
                user.setHomeAddress(address);
                user.setGardens(userGardens);
                resList.add(user);

                returns(resList);
                userBOConverter.convert(resList);
            }
        };
        userService.getUserByCity(cityId, prodId, new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get user by region">
    @Test
    public void getUserByRegionNullResult() throws BusinessException {

        final IUserService userService = new UserService();

        final Integer regionId = 1;
        final Integer prodId = 1;

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOConverter;
            {
                Deencapsulation.setField(userService, "userDAO", userDAO);
                Deencapsulation.setField(userService, "userBOConverter", userBOConverter);

                TechnicalInformation<UserCriteriaColumn, UserOrderColumn> techInfo = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
                QueryCriteria<UserCriteriaColumn> userCriteria = new QueryCriteria<UserCriteriaColumn>(UserCriteriaColumn.STATUS, CriteriaOperation.EQUAL, UserStatus.VALIDED);
                techInfo.addCriteria(userCriteria);

                userDAO.getUserByRegionAndProduct(regionId, prodId, with(new TechnicalInformationMatcher(techInfo)));
                returns(null);

                userBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(userService.getUserByRegion(regionId, prodId, null));
    }

    @Test()
    public void getUserByRegion() throws BusinessException {

        final IUserService userService = new UserService();

        final Integer regionId = 1;
        final Integer prodId = 1;
        RefRegionBO expectedRegion = new RefRegionBO();
        expectedRegion.setId(regionId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private UserDAO mockUserDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOConverter;

            {
                Deencapsulation.setField(userService, "userDAO", mockUserDAO);
                Deencapsulation.setField(userService, "userBOConverter", userBOConverter);

                RefRegionBO region = new RefRegionBO();
                region.setId(regionId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<UserCriteriaColumn, UserOrderColumn> techInfo = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
                QueryCriteria<UserCriteriaColumn> userCriteria = new QueryCriteria<UserCriteriaColumn>(UserCriteriaColumn.STATUS, CriteriaOperation.EQUAL, UserStatus.VALIDED);
                techInfo.addCriteria(userCriteria);

                mockUserDAO.getUserByRegionAndProduct(regionId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<UserBO> resList = new ArrayList<UserBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> userProducts = new ArrayList<ProductBO>();
                ProductBO userProduct = new ProductBO();
                userProduct.setReferenceProduct(refProduct);
                userProducts.add(userProduct);

                UserBO user = new UserBO();
                user.setHomeAddress(address);
                List<GardenBO> userGardens = new ArrayList<GardenBO>();
                GardenBO garden = new GardenBO();
                garden.setId(11L);
                garden.setProducts(userProducts);
                user.setGardens(userGardens);
                resList.add(user);

                user = new UserBO();
                user.setHomeAddress(address);
                user.setGardens(userGardens);
                resList.add(user);

                returns(resList);

                userBOConverter.convert(resList);
            }
        };
        userService.getUserByRegion(regionId, prodId, null);
    }

    @Test()
    public void getUserByRegionEmptyTechnicalInformation() throws BusinessException {

        final IUserService userService = new UserService();

        final Integer regionId = 1;
        final Integer prodId = 1;
        RefRegionBO expectedRegion = new RefRegionBO();
        expectedRegion.setId(regionId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private UserDAO mockUserDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOConverter;

            {
                Deencapsulation.setField(userService, "userDAO", mockUserDAO);
                Deencapsulation.setField(userService, "userBOConverter", userBOConverter);

                RefRegionBO region = new RefRegionBO();
                region.setId(regionId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<UserCriteriaColumn, UserOrderColumn> techInfo = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
                QueryCriteria<UserCriteriaColumn> userCriteria = new QueryCriteria<UserCriteriaColumn>(UserCriteriaColumn.STATUS, CriteriaOperation.EQUAL, UserStatus.VALIDED);
                techInfo.addCriteria(userCriteria);

                mockUserDAO.getUserByRegionAndProduct(regionId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<UserBO> resList = new ArrayList<UserBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> userProducts = new ArrayList<ProductBO>();
                ProductBO userProduct = new ProductBO();
                userProduct.setReferenceProduct(refProduct);
                userProducts.add(userProduct);

                UserBO user = new UserBO();
                user.setHomeAddress(address);
                List<GardenBO> userGardens = new ArrayList<GardenBO>();
                GardenBO garden = new GardenBO();
                garden.setId(11L);
                garden.setProducts(userProducts);
                user.setGardens(userGardens);
                resList.add(user);

                user = new UserBO();
                user.setHomeAddress(address);
                user.setGardens(userGardens);
                resList.add(user);

                returns(resList);

                userBOConverter.convert(resList);
            }
        };
        userService.getUserByRegion(regionId, prodId, new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get user by state">
    @Test
    public void getUserByStateNullResult() throws BusinessException {

        final IUserService userService = new UserService();

        final Integer stateId = 1;
        final Integer prodId = 1;

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOConverter;
            {
                Deencapsulation.setField(userService, "userDAO", userDAO);
                Deencapsulation.setField(userService, "userBOConverter", userBOConverter);

                TechnicalInformation<UserCriteriaColumn, UserOrderColumn> techInfo = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
                QueryCriteria<UserCriteriaColumn> userCriteria = new QueryCriteria<UserCriteriaColumn>(UserCriteriaColumn.STATUS, CriteriaOperation.EQUAL, UserStatus.VALIDED);
                techInfo.addCriteria(userCriteria);

                userDAO.getUserByStateAndProduct(stateId, prodId, with(new TechnicalInformationMatcher(techInfo)));
                returns(null);

                userBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(userService.getUserByState(stateId, prodId, null));
    }

    @Test()
    public void getUserByState() throws BusinessException {

        final IUserService userService = new UserService();

        final Integer stateId = 1;
        final Integer prodId = 1;
        RefStateBO expectedState = new RefStateBO();
        expectedState.setId(stateId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private UserDAO mockUserDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOConverter;
            {
                Deencapsulation.setField(userService, "userDAO", mockUserDAO);
                Deencapsulation.setField(userService, "userBOConverter", userBOConverter);

                RefStateBO state = new RefStateBO();
                state.setId(stateId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<UserCriteriaColumn, UserOrderColumn> techInfo = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
                QueryCriteria<UserCriteriaColumn> userCriteria = new QueryCriteria<UserCriteriaColumn>(UserCriteriaColumn.STATUS, CriteriaOperation.EQUAL, UserStatus.VALIDED);
                techInfo.addCriteria(userCriteria);

                mockUserDAO.getUserByStateAndProduct(stateId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<UserBO> resList = new ArrayList<UserBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                RefRegionBO region = new RefRegionBO();
                region.setState(state);
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> userProducts = new ArrayList<ProductBO>();
                ProductBO userProduct = new ProductBO();
                userProduct.setReferenceProduct(refProduct);
                userProducts.add(userProduct);

                UserBO user = new UserBO();
                user.setHomeAddress(address);
                List<GardenBO> userGardens = new ArrayList<GardenBO>();
                GardenBO garden = new GardenBO();
                garden.setId(11L);
                garden.setProducts(userProducts);
                user.setGardens(userGardens);
                resList.add(user);

                user = new UserBO();
                user.setHomeAddress(address);
                user.setGardens(userGardens);
                resList.add(user);

                returns(resList);

                userBOConverter.convert(resList);
            }
        };
        userService.getUserByState(stateId, prodId, null);
    }

    @Test()
    public void getUserByStateEmptyTechnicalInformation() throws BusinessException {

        final IUserService userService = new UserService();

        final Integer stateId = 1;
        final Integer prodId = 1;
        RefStateBO expectedState = new RefStateBO();
        expectedState.setId(stateId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private UserDAO mockUserDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOConverter;

            {
                Deencapsulation.setField(userService, "userDAO", mockUserDAO);
                Deencapsulation.setField(userService, "userBOConverter", userBOConverter);

                RefStateBO state = new RefStateBO();
                state.setId(stateId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<UserCriteriaColumn, UserOrderColumn> techInfo = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
                QueryCriteria<UserCriteriaColumn> userCriteria = new QueryCriteria<UserCriteriaColumn>(UserCriteriaColumn.STATUS, CriteriaOperation.EQUAL, UserStatus.VALIDED);
                techInfo.addCriteria(userCriteria);

                mockUserDAO.getUserByStateAndProduct(stateId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<UserBO> resList = new ArrayList<UserBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                RefRegionBO region = new RefRegionBO();
                region.setState(state);
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> userProducts = new ArrayList<ProductBO>();
                ProductBO userProduct = new ProductBO();
                userProduct.setReferenceProduct(refProduct);
                userProducts.add(userProduct);

                UserBO user = new UserBO();
                user.setHomeAddress(address);
                List<GardenBO> userGardens = new ArrayList<GardenBO>();
                GardenBO garden = new GardenBO();
                garden.setId(11L);
                garden.setProducts(userProducts);
                user.setGardens(userGardens);
                resList.add(user);

                user = new UserBO();
                user.setHomeAddress(address);
                user.setGardens(userGardens);
                resList.add(user);

                returns(resList);

                userBOConverter.convert(resList);
            }
        };
        userService.getUserByState(stateId, prodId, new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get user by country">
    @Test
    public void getUserByCountryBadCountry() throws BusinessException {

        final IUserService userService = new UserService();

        final Integer countryId = 1;
        final Integer prodId = 1;

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOConverter;
            {
                Deencapsulation.setField(userService, "userDAO", userDAO);
                Deencapsulation.setField(userService, "userBOConverter", userBOConverter);

                TechnicalInformation<UserCriteriaColumn, UserOrderColumn> techInfo = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
                QueryCriteria<UserCriteriaColumn> userCriteria = new QueryCriteria<UserCriteriaColumn>(UserCriteriaColumn.STATUS, CriteriaOperation.EQUAL, UserStatus.VALIDED);
                techInfo.addCriteria(userCriteria);

                userDAO.getUserByCountryAndProduct(countryId, prodId, with(new TechnicalInformationMatcher(techInfo)));
                returns(null);

                userBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(userService.getUserByCountry(countryId, prodId, null));
    }

    @Test()
    public void getUserByCountry() throws BusinessException {

        final IUserService userService = new UserService();

        final Integer countryId = 1;
        final Integer prodId = 1;
        RefCountryBO expectedCountry = new RefCountryBO();
        expectedCountry.setId(countryId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private UserDAO mockUserDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOConverter;

            {
                Deencapsulation.setField(userService, "userDAO", mockUserDAO);
                Deencapsulation.setField(userService, "userBOConverter", userBOConverter);

                RefCountryBO country = new RefCountryBO();
                country.setId(countryId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<UserCriteriaColumn, UserOrderColumn> techInfo = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
                QueryCriteria<UserCriteriaColumn> userCriteria = new QueryCriteria<UserCriteriaColumn>(UserCriteriaColumn.STATUS, CriteriaOperation.EQUAL, UserStatus.VALIDED);
                techInfo.addCriteria(userCriteria);

                mockUserDAO.getUserByCountryAndProduct(countryId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<UserBO> resList = new ArrayList<UserBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                RefRegionBO region = new RefRegionBO();
                RefStateBO state = new RefStateBO();
                state.setCountry(country);
                region.setState(state);
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> userProducts = new ArrayList<ProductBO>();
                ProductBO userProduct = new ProductBO();
                userProduct.setReferenceProduct(refProduct);
                userProducts.add(userProduct);

                UserBO user = new UserBO();
                user.setHomeAddress(address);
                List<GardenBO> userGardens = new ArrayList<GardenBO>();
                GardenBO garden = new GardenBO();
                garden.setId(11L);
                garden.setProducts(userProducts);
                user.setGardens(userGardens);
                resList.add(user);

                user = new UserBO();
                user.setHomeAddress(address);
                user.setGardens(userGardens);
                resList.add(user);

                returns(resList);

                userBOConverter.convert(resList);
            }
        };
        userService.getUserByCountry(countryId, prodId, null);

    }

    @Test()
    public void getUserByCountryEmptyTechnicalInformation() throws BusinessException {

        final IUserService userService = new UserService();

        final Integer countryId = 1;
        final Integer prodId = 1;
        RefCountryBO expectedCountry = new RefCountryBO();
        expectedCountry.setId(countryId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private UserDAO mockUserDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOConverter;
            {
                Deencapsulation.setField(userService, "userDAO", mockUserDAO);
                Deencapsulation.setField(userService, "userBOConverter", userBOConverter);

                RefCountryBO country = new RefCountryBO();
                country.setId(countryId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<UserCriteriaColumn, UserOrderColumn> techInfo = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
                QueryCriteria<UserCriteriaColumn> userCriteria = new QueryCriteria<UserCriteriaColumn>(UserCriteriaColumn.STATUS, CriteriaOperation.EQUAL, UserStatus.VALIDED);
                techInfo.addCriteria(userCriteria);

                mockUserDAO.getUserByCountryAndProduct(countryId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<UserBO> resList = new ArrayList<UserBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                RefRegionBO region = new RefRegionBO();
                RefStateBO state = new RefStateBO();
                state.setCountry(country);
                region.setState(state);
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> userProducts = new ArrayList<ProductBO>();
                ProductBO userProduct = new ProductBO();
                userProduct.setReferenceProduct(refProduct);
                userProducts.add(userProduct);

                UserBO user = new UserBO();
                user.setHomeAddress(address);
                List<GardenBO> userGardens = new ArrayList<GardenBO>();
                GardenBO garden = new GardenBO();
                garden.setId(11L);
                garden.setProducts(userProducts);
                user.setGardens(userGardens);
                resList.add(user);

                user = new UserBO();
                user.setHomeAddress(address);
                user.setGardens(userGardens);
                resList.add(user);

                returns(resList);

                userBOConverter.convert(resList);
            }
        };
        userService.getUserByCountry(countryId, prodId, new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>());

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Blacklist">
    @Test(expected = BusinessException.class)
    public void blacklistNo() throws BusinessException {
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                mockUserDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        userService.blacklist(pUserId);
    }

    /**
     * Control that we can't blacklist a deleted .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void blacklistDeleted() throws BusinessException {
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                UserBO userBO = new UserBO();
                userBO.setStatus(UserStatus.DELETED);
                mockUserDAO.get(1L);
                repeats(1);
                returns(userBO);
            }
        };

        userService.blacklist(pUserId);
    }

    /**
     * Control that we can't blacklist a deleted .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void blacklistNew() throws BusinessException {
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                UserBO userBO = new UserBO();
                userBO.setStatus(UserStatus.NEW);
                mockUserDAO.get(1L);
                repeats(1);
                returns(userBO);
            }
        };

        userService.blacklist(pUserId);
    }

    /**
     * Control that we can't blacklist a deleted .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void blacklistArchived() throws BusinessException {
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                UserBO userBO = new UserBO();
                userBO.setStatus(UserStatus.ARCHIVED);
                mockUserDAO.get(1L);
                repeats(1);
                returns(userBO);
            }
        };

        userService.blacklist(pUserId);
    }

    /**
     * Control that we can blacklist an already blacklist without using save.
     *
     * @throws BusinessException
     */
    @Test
    public void blacklistAlreadyBlacklist() throws BusinessException {
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                UserBO userBO = new UserBO();
                userBO.setStatus(UserStatus.BLACKLISTED);
                mockUserDAO.get(1L);
                repeats(1);
                returns(userBO);
            }
        };

        userService.blacklist(pUserId);
    }

    /**
     * Control that blacklist works.
     *
     * @throws BusinessException
     */
    @Test
    public void blacklist() throws BusinessException {
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {


                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                UserBO user = new UserBO();
                user.setId(1L);

                UserBO userBO = new UserBO();
                userBO.setId(1L);
                userBO.setStatus(UserStatus.VALIDED);
                mockUserDAO.get(1L);
                repeats(1);
                returns(userBO);

                UserBO userBOExpected = new UserBO();
                userBOExpected.setId(1L);
                userBOExpected.setStatus(UserStatus.BLACKLISTED);

                mockUserDAO.update(with(new UserBOMatcher(userBOExpected)));
            }
        };

        userService.blacklist(pUserId);
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
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                mockUserDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        userService.unblacklist(pUserId);
    }

    /**
     * Control that we can unblacklist a deleted .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unblacklistDeleted() throws BusinessException {
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                UserBO userBO = new UserBO();
                userBO.setStatus(UserStatus.DELETED);
                mockUserDAO.get(1L);
                repeats(1);
                returns(userBO);
            }
        };

        userService.unblacklist(pUserId);
    }

    /**
     * Control that we don't call save when we unblacklist an unblacklistd .
     *
     * @throws BusinessException
     */
    @Test
    public void unblacklistAlreadyUnblacklist() throws BusinessException {
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                UserBO userBO = new UserBO();
                userBO.setStatus(UserStatus.VALIDED);
                mockUserDAO.get(1L);
                repeats(1);
                returns(userBO);
            }
        };

        userService.unblacklist(pUserId);
    }

    /**
     * Control that unblacklist works.
     *
     * @throws BusinessException
     */
    @Test
    public void unblacklist() throws BusinessException {
        final UserService userService = new UserService();

        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                UserBO user = new UserBO();
                user.setId(1L);

                Date now = new Date();

                UserBO userBO = new UserBO();
                userBO.setId(1L);
                userBO.setStatus(UserStatus.BLACKLISTED);
                mockUserDAO.get(1L);
                repeats(1);
                returns(userBO);

                UserBO userBOExpected = new UserBO();
                userBOExpected.setId(1L);
                userBOExpected.setStatus(UserStatus.VALIDED);

                mockUserDAO.update(with(new UserBOMatcher(userBOExpected)));
            }
        };

        userService.unblacklist(pUserId);
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
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                mockUserDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        userService.archive(pUserId);
    }

    /**
     * Control that we can't archive a deleted .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void archiveDeleted() throws BusinessException {
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                UserBO userBO = new UserBO();
                userBO.setStatus(UserStatus.DELETED);
                mockUserDAO.get(1L);
                repeats(1);
                returns(userBO);
            }
        };

        userService.archive(pUserId);
    }

    /**
     * Control that we can archive an already archive without using save.
     *
     * @throws BusinessException
     */
    @Test
    public void archiveAlreadyArchive() throws BusinessException {
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                UserBO userBO = new UserBO();
                userBO.setStatus(UserStatus.ARCHIVED);
                mockUserDAO.get(1L);
                repeats(1);
                returns(userBO);
            }
        };

        userService.archive(pUserId);
    }

    /**
     * Control that archive works.
     *
     * @throws BusinessException
     */
    @Test
    public void archive() throws BusinessException {
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {


                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                UserBO user = new UserBO();
                user.setId(1L);

                Date now = new Date();

                UserBO userBO = new UserBO();
                userBO.setId(1L);
                userBO.setStatus(UserStatus.VALIDED);

                mockUserDAO.get(1L);
                repeats(1);
                returns(userBO);

                UserBO userBOExpected = new UserBO();
                userBOExpected.setId(1L);
                userBOExpected.setStatus(UserStatus.ARCHIVED);


                mockUserDAO.update(with(new UserBOMatcher(userBOExpected)));
            }
        };

        userService.archive(pUserId);
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
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                mockUserDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        userService.unarchive(pUserId);
    }

    /**
     * Control that we can unarchive a deleted .
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unarchiveDeleted() throws BusinessException {
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                UserBO userBO = new UserBO();
                userBO.setStatus(UserStatus.DELETED);
                mockUserDAO.get(1L);
                repeats(1);
                returns(userBO);
            }
        };

        userService.unarchive(pUserId);
    }

    /**
     * Control that we don't call save when we unarchive an unarchived .
     *
     * @throws BusinessException
     */
    @Test
    public void unarchiveAlreadyUnarchive() throws BusinessException {
        final UserService userService = new UserService();


        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                UserBO userBO = new UserBO();
                userBO.setStatus(UserStatus.VALIDED);
                mockUserDAO.get(1L);
                repeats(1);
                returns(userBO);
            }
        };

        userService.unarchive(pUserId);
    }

    /**
     * Control that unarchive works.
     *
     * @throws BusinessException
     */
    @Test
    public void unarchive() throws BusinessException {
        final UserService userService = new UserService();

        final Long pUserId = 1L;

        new Expectations() {

            @Mocked
            private IUserDAO mockUserDAO;

            {

                Deencapsulation.setField(userService, "userDAO", mockUserDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                UserBO user = new UserBO();
                user.setId(1L);

                Date now = new Date();

                UserBO userBO = new UserBO();
                userBO.setId(1L);
                userBO.setStatus(UserStatus.ARCHIVED);

                mockUserDAO.get(1L);
                repeats(1);
                returns(userBO);

                UserBO userBOExpected = new UserBO();
                userBOExpected.setId(1L);
                userBOExpected.setStatus(UserStatus.VALIDED);


                mockUserDAO.update(with(new UserBOMatcher(userBOExpected)));
            }
        };

        userService.unarchive(pUserId);
    }
    // </editor-fold>

}
