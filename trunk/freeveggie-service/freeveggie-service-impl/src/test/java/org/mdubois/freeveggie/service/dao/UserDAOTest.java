package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.UserStatus;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.criteria.UserCriteriaColumn;
import org.mdubois.freeveggie.dao.impl.*;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.UserOrderColumn;
// </editor-fold>

/**
 *
 * @author Mickael
 */
public class UserDAOTest extends ReadWriteDAOTest<UserBO, Long>  {

    private UserDAO userDAO = null;
    private RefCountryDAO refCountryDAO = null;
    private RefCityDAO refCityDAO = null;
    private RefProductDAO refProductDAO = null;
    private RefStateDAO refStateDAO = null;
    private RefRegionDAO refRegionDAO = null;
    private Integer france;
    private Integer boisColombesCity;
    private Integer hautSaine;
    private Integer ileDeFrance;
    private Integer tomato;

    private Pagination pagination;
    private TechnicalInformation<UserCriteriaColumn, UserOrderColumn> techInfo;

    public UserDAOTest() {
    }

    @Before
    public void setUp() {
        userDAO = new UserDAO();
        userDAO.setEntityManager(em);

        refCountryDAO = new RefCountryDAO();
        refCountryDAO.setEntityManager(em);

        refCityDAO = new RefCityDAO();
        refCityDAO.setEntityManager(em);

        refStateDAO = new RefStateDAO();
        refStateDAO.setEntityManager(em);

        refRegionDAO = new RefRegionDAO();
        refRegionDAO.setEntityManager(em);

        refProductDAO = new RefProductDAO();
        refProductDAO.setEntityManager(em);

        france = 76;
        boisColombesCity = 2;
        hautSaine = 5;
        ileDeFrance = 1;
        tomato = 1;

        techInfo = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
        pagination = new Pagination(1, 1);
        techInfo.setPagination(pagination);

    }

    @Test
    public void testGetUserByLoginNull() {
       Assert.assertNull(userDAO.getUserByLogin("qdsf"));
    }

    @Test
    public void testGetUserByLogin() {
       Assert.assertEquals(userDAO.get(1L), userDAO.getUserByLogin("baloo"));
    }

    @Test
    public void testGetUserByEmailNull() {
       Assert.assertNull(userDAO.getUserByEmail("qdsf"));
    }

    @Test
    public void testGetUserByEmail() {
       Assert.assertEquals(userDAO.get(1L), userDAO.getUserByEmail("mickael@edubois.org"));
    }

    @Test
    public void testGetUserByCity() {
        //Basic test
        List<UserBO> result = userDAO.getUserByCityAndProduct(boisColombesCity, tomato, null);
        // This assert might fail be because of the dataset
        assertTrue("Expected more or equals to 2 but find " + result.size(),result.size()>=1);
        AssertBusinnesObject.assertNoDuplicate(result);
        for (UserBO userBO : result) {
            assertTrue("The user "+ userBO +"is not living in paris city", UserUtils.isUserHasReferenceProductInCity(userBO, boisColombesCity, tomato));
        }
        //Test pagination
        result = userDAO.getUserByCityAndProduct(boisColombesCity, tomato, techInfo);
        assertTrue(result.size()==1);
        assertTrue("The user "+ result.get(0) +"is not living in paris city", UserUtils.isUserHasReferenceProductInCity(result.get(0), boisColombesCity, tomato));

        //Test user
    }

    @Test
    public void testGetUserByCityWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getUserByCityAndProduct", boisColombesCity, tomato);
    }

    @Test
    public void testGetUserByCityWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getUserByCityAndProduct", boisColombesCity, tomato);
    }

    @Test
    public void testGetUserByCityWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getUserByCityAndProduct", boisColombesCity, tomato);
    }

    @Test
    public void testGetUserByRegion() {
        List<UserBO> result = userDAO.getUserByRegionAndProduct(hautSaine, tomato, null);
        // This assert might fail be because of the dataset
        assertTrue("Expected more or equals to 2 but find " + result.size(),result.size()>=1);
        AssertBusinnesObject.assertNoDuplicate(result);
        for (UserBO userBO : result) {
            assertTrue("The user "+ userBO +"is not living in paris city", UserUtils.isUserHasReferenceProductInRegion(userBO, hautSaine, tomato));
        }
        result = userDAO.getUserByRegionAndProduct(hautSaine, tomato, techInfo);
        assertTrue(result.size()==1);
        assertTrue("The user "+ result.get(0) +"is not living in paris city", UserUtils.isUserHasReferenceProductInRegion(result.get(0), hautSaine, tomato));
    }

    @Test
    public void testGetUserByRegionWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getUserByRegionAndProduct", hautSaine, tomato);
    }

    @Test
    public void testGetUserByRegionWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getUserByRegionAndProduct", hautSaine, tomato);
    }

    @Test
    public void testGetUserByRegionWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getUserByRegionAndProduct", hautSaine, tomato);
    }

    @Test
    public void testGetUserByState() {
        List<UserBO> result = userDAO.getUserByStateAndProduct(ileDeFrance, tomato, null);
        // This assert might fail be because of the dataset
        assertTrue("Expected more or equals to 2 but find " + result.size(),result.size()>=1);
        AssertBusinnesObject.assertNoDuplicate(result);
        for (UserBO userBO : result) {
            assertTrue("The user "+ userBO +"is not living in paris city", UserUtils.isUserHasReferenceProductInState(userBO, ileDeFrance, tomato));
        }
        result = userDAO.getUserByStateAndProduct(ileDeFrance, tomato, techInfo);
        assertTrue(result.size()==1);
        assertTrue("The user "+ result.get(0) +"is not living in paris city", UserUtils.isUserHasReferenceProductInState(result.get(0), ileDeFrance, tomato));
    }

    @Test
    public void testGetUserByStateWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getUserByStateAndProduct", ileDeFrance, tomato);
    }

    @Test
    public void testGetUserByStateWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getUserByStateAndProduct", ileDeFrance, tomato);
    }

    @Test
    public void testGetUserByStateWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getUserByStateAndProduct", ileDeFrance, tomato);
    }

    @Test
    public void testGetUserByCountry() {
        List<UserBO> result = userDAO.getUserByCountryAndProduct(france, tomato, null);
        // This assert might fail be because of the dataset
        assertTrue("Expected more or equals to 2 but find " + result.size(),result.size()>=1);
        AssertBusinnesObject.assertNoDuplicate(result);
        for (UserBO userBO : result) {
            assertTrue("The user "+ userBO +"is not living in paris city", UserUtils.isUserHasReferenceProductInCountry(userBO, france, tomato));
        }
        result = userDAO.getUserByCountryAndProduct(france, tomato, techInfo);
        assertTrue(result.size()==1);
        assertTrue("The user "+ result.get(0) +"is not living in paris city", UserUtils.isUserHasReferenceProductInCountry(result.get(0), france, tomato));
    }

    @Test
    public void testGetUserByCountryWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getUserByCountryAndProduct", france, tomato);
    }

    @Test
    public void testGetUserByCountryWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getUserByCountryAndProduct", france, tomato);
    }

    @Test
    public void testGetUserByCountryWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getUserByCountryAndProduct", france, tomato);
    }

    @Override
    public UserBO createEntity() {
        AddressDAO addressDAO = new AddressDAO();
        addressDAO.setEntityManager(em);
        AddressBO addressBO = new AddressBO();
        addressBO.setStreetName("rue louis legrand");
        addressBO.setCity(refCityDAO.get(1));
        addressBO.setName("Test Adresse");
        addressBO.setStreetNumber("25");
        addressBO.setAdministrativeAreaLevel1("pAddressBO.getAdministrativeAreaLevel1()");
        addressBO.setAdministrativeAreaLevel2("pAddressBO.getAdministrativeAreaLevel2()");
        addressBO.setCountry("pAddressBO.getCountry()");
        addressBO.setLatitude(1.0);
        addressBO.setLongitude(1.0);
        addressBO.setName("pAddressBO.getName()");
        addressBO.setPostalCode("pAddressBO.getPostalCode()");
        addressBO.setLocality("locality");
        addressDAO.save(addressBO);

        UserBO userBO = new UserBO();
        userBO.setBlacklistedDate(new Date());
        userBO.setCancellationDate(new Date());
        userBO.setCreationDate(new Date());
        userBO.setEmail("test@test.com");
        userBO.setFirstname("Firstname");
        userBO.setLastConnexion(new Date());
        userBO.setLastname("Lastname");
        userBO.setPassword("password");
        userBO.setRole(UserRole.USER);
        userBO.setStatus(UserStatus.NEW);
        userBO.setUsername("username");
        userBO.setTemporaryPassword("**");

        userBO.setHomeAddress(addressBO);

        return userBO;
    }

    @Override
    public IReadWriteDAO<UserBO, Long> getDao() {
        return userDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return UserOrderColumn.class;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return UserCriteriaColumn.class;
    }

    @Override
    public Long getId(){
        return 1L;
    }
}


class UserUtils {

    private UserUtils() {
    }

    /**
     * Control that the user as in one of his garden a given reference product.
     * @param user - The {@link UserBO} to test
     * @param refProduct - The {@link RefProductBO} to look for
     * @return True if the user as the reference product, False otherwise
     */
    public static boolean isUserHasReferenceProduct(final UserBO user, final Integer refProduct) {
        for (GardenBO garden : user.getGardens()) {
            for (ProductBO product : garden.getProducts()) {
                if (product.getReferenceProduct().getId().equals(refProduct)) {
                    return true;

                }
            }
        }
        return false;
    }

    /**
     * Control that the user as in one of his garden a given reference product.
     * @param user - The {@link UserBO} to test
     * @param refProduct - The {@link RefProductBO} to look for
     * @param refCity - The {@link RefCityBO} to look for
     * @return True if the user as the reference product, False otherwise
     */
    public static boolean isUserHasReferenceProductInCity(final UserBO user, final Integer refCity,  final Integer refProduct) {
        for (GardenBO garden : user.getGardens()) {
            if(garden.getAddress().getCity().getId().equals(refCity)){
                for (ProductBO product : garden.getProducts()) {
                    if (product.getReferenceProduct().getId().equals(refProduct)) {
                        return true;

                    }
                }
            }
        }
        return false;
    }

    /**
     * Control that the user as in one of his garden a given reference product.
     * @param user - The {@link UserBO} to test
     * @param refProduct - The {@link RefProductBO} to look for
     * @param refRegion - The {@link RefRegionBO} to look for
     * @return True if the user as the reference product, False otherwise
     */
    public static boolean isUserHasReferenceProductInRegion(final UserBO user, final Integer refRegion,  final Integer refProduct) {
        for (GardenBO garden : user.getGardens()) {
            if(garden.getAddress().getCity().getRegion().getId().equals(refRegion)){
                for (ProductBO product : garden.getProducts()) {
                    if (product.getReferenceProduct().getId().equals(refProduct)) {
                        return true;

                    }
                }
            }
        }
        return false;
    }

    /**
     * Control that the user as in one of his garden a given reference product.
     * @param user - The {@link UserBO} to test
     * @param refProduct - The {@link RefProductBO} to look for
     * @param refState - The {@link RefStateBO} to look for
     * @return True if the user as the reference product, False otherwise
     */
    public static boolean isUserHasReferenceProductInState(final UserBO user, final Integer refState,  final Integer refProduct) {
        for (GardenBO garden : user.getGardens()) {
            if(garden.getAddress().getCity().getRegion().getState().getId().equals(refState)){
                for (ProductBO product : garden.getProducts()) {
                    if (product.getReferenceProduct().getId().equals(refProduct)) {
                        return true;

                    }
                }
            }
        }
        return false;
    }

    /**
     * Control that the user as in one of his garden a given reference product.
     * @param user - The {@link UserBO} to test
     * @param refProduct - The {@link RefProductBO} to look for
     * @param refCountry - The {@link RefCountryBO} to look for
     * @return True if the user as the reference product, False otherwise
     */
    public static boolean isUserHasReferenceProductInCountry(final UserBO user, final Integer refCountry,  final Integer refProduct) {
        for (GardenBO garden : user.getGardens()) {
            if(garden.getAddress().getCity().getRegion().getState().getCountry().getId().equals(refCountry)){
                for (ProductBO product : garden.getProducts()) {
                    if (product.getReferenceProduct().getId().equals(refProduct)) {
                        return true;

                    }
                }
            }
        }
        return false;
    }

    /**
     * Is a {@link UserBO} has a home address in {@link RefCityBO} or has a garden in there.
     * @param user - The {@link UserBO} to control
     * @param refCity - The {@link RefCityBO} to look for
     * @return
     */
    public static boolean hasUserAnAddressIn(final UserBO user, final RefCityBO refCity){
        if(user.getHomeAddress().getCity().equals(refCity)){
            return true;
        }
        for (GardenBO garden : user.getGardens()) {
            if(garden.getAddress().getCity().equals(refCity)){
                return true;
            }
        }
        return false;
    }

    /**
     * Is a {@link UserBO} has a home address in {@link RefRegionBO} or has a garden in there.
     * @param user - The {@link UserBO} to control
     * @param refRegion - The {@link RefCityBO} to look for
     * @return
     */
    public static boolean hasUserAnAddressIn(final UserBO user, final RefRegionBO refRegion){
        if(user.getHomeAddress().getCity().getRegion().equals(refRegion)){
            return true;
        }
        for (GardenBO garden : user.getGardens()) {
            if(garden.getAddress().getCity().getRegion().equals(refRegion)){
                return true;
            }
        }
        return false;
    }

    /**
     * Is a {@link UserBO} has a home address in {@link RefStateBO} or has a garden in there.
     * @param user - The {@link UserBO} to control
     * @param refState - The {@link RefStateBO} to look for
     * @return
     */
    public static boolean hasUserAnAddressIn(final UserBO user, final RefStateBO refState){
        if(user.getHomeAddress().getCity().getRegion().getState().equals(refState)){
            return true;
        }
        for (GardenBO garden : user.getGardens()) {
            if(garden.getAddress().getCity().getRegion().getState().equals(refState)){
                return true;
            }
        }
        return false;
    }

    /**
     * Is a {@link UserBO} has a home address in {@link RefCountryBO} or has a garden in there.
     * @param user - The {@link UserBO} to control
     * @param refCountry - The {@link RefCountryBO} to look for
     * @return
     */
    public static boolean hasUserAnAddressIn(final UserBO user, final RefCountryBO refCountry){
        if(user.getHomeAddress().getCity().getRegion().getState().getCountry().equals(refCountry)){
            return true;
        }
        for (GardenBO garden : user.getGardens()) {
            if(garden.getAddress().getCity().getRegion().getState().getCountry().equals(refCountry)){
                return true;
            }
        }
        return false;
    }


}
