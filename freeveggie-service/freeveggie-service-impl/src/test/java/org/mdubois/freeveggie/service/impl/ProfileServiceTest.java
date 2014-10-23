package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.bo.ProfileBO;
import org.mdubois.freeveggie.bo.UserBO;
import org.mdubois.freeveggie.dao.api.IProfileDAO;
import org.mdubois.freeveggie.dao.api.IUserDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.api.IProfileService;
import org.mdubois.freeveggie.service.msg.ProfileMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class ProfileServiceTest {

    @Mocked
    private IProfileDAO profileDAO;
    @Mocked
    private BusinessObjectConverter<ProfileBO, ProfileMsg> converter;
    @Mocked
    private IUserDAO userDAO;
    @Mocked
    private Converter<ProfileMsg, ProfileBO> profilBOConverter;

    @Test
    public void update() throws BusinessException {
        final IProfileService profileService = new ProfileService();
        final Long userId = 12765L;
        final ProfileMsg profileMsg = new ProfileMsg();
        profileMsg.setExperience("2experience");
        profileMsg.setInterest("2interest");
        profileMsg.setOther("2other");
        profileMsg.setParticipation("2participation");
        profileMsg.setPersonalDescription("2personal description");
        profileMsg.setPhilosophy("2philosophy");
        profileMsg.setPreferedMeals("2prefered meals");
        profileMsg.setReason("2reason");
        profileMsg.setUserPictureFilename("2user picture filename");

        new Expectations() {

            {
                Deencapsulation.setField(profileService, profileDAO);
                Deencapsulation.setField(profileService, converter);

                ProfileBO profileBO = new ProfileBO();
                profileBO.setExperience("experience");
                profileBO.setInterest("interest");
                profileBO.setOther("other");
                profileBO.setParticipation("participation");
                profileBO.setPersonalDescription("personal description");
                profileBO.setPhilosophy("philosophy");
                profileBO.setPreferedMeals("prefered meals");
                profileBO.setReason("reason");
                profileBO.setUserPictureFilename("user picture filename");

                profileDAO.get(userId);
                returns(profileBO);

                converter.update(profileBO, profileMsg);

                profileDAO.update(profileBO);
            }
        };

        boolean update = profileService.update(userId, profileMsg);
        Assert.assertTrue(update);
    }

    @Test
    public void updateNoProfile() throws BusinessException {
        final IProfileService profileService = new ProfileService();
        final Long userId = 12765L;
        final ProfileMsg profileMsg = new ProfileMsg();
        profileMsg.setExperience("2experience");
        profileMsg.setInterest("2interest");
        profileMsg.setOther("2other");
        profileMsg.setParticipation("2participation");
        profileMsg.setPersonalDescription("2personal description");
        profileMsg.setPhilosophy("2philosophy");
        profileMsg.setPreferedMeals("2prefered meals");
        profileMsg.setReason("2reason");
        profileMsg.setUserPictureFilename("2user picture filename");

        new Expectations() {

            {
                Deencapsulation.setField(profileService, profileDAO);
                Deencapsulation.setField(profileService, converter);

                profileDAO.get(userId);
                returns(null);

                ProfileBO profileBO = new ProfileBO();
                converter.createNew(profileMsg);
                returns(profileBO);

                profileDAO.save(profileBO);
            }
        };

        profileService.update(userId, profileMsg);
    }

    @Test
    public void getProfilById() throws BusinessException {
        final IProfileService profileService = new ProfileService();
        final Long userId = 12765L;

        new Expectations() {

            {
                Deencapsulation.setField(profileService, userDAO);
                Deencapsulation.setField(profileService, profileDAO);
                Deencapsulation.setField(profileService, "profilBOConverter", profilBOConverter);

                UserBO userBO = new UserBO();
                ProfileBO profileBO = new ProfileBO();
                profileBO.setExperience("experience");
                profileBO.setInterest("interest");
                profileBO.setOther("other");
                profileBO.setParticipation("participation");
                profileBO.setPersonalDescription("personal description");
                profileBO.setPhilosophy("philosophy");
                profileBO.setPreferedMeals("prefered meals");
                profileBO.setReason("reason");
                profileBO.setUserPictureFilename("user picture filename");
                userBO.setProfile(profileBO);

                ProfileMsg profileMsg = new ProfileMsg();
                profileMsg.setExperience("experience");
                profileMsg.setInterest("interest");
                profileMsg.setOther("other");
                profileMsg.setParticipation("participation");
                profileMsg.setPersonalDescription("personal description");
                profileMsg.setPhilosophy("philosophy");
                profileMsg.setPreferedMeals("prefered meals");
                profileMsg.setReason("reason");
                profileMsg.setUserPictureFilename("user picture filename");

                userDAO.get(userId);
                returns(userBO);

                profilBOConverter.convert(profileBO);
                returns(profileMsg);

            }
        };

        ProfileMsg profile = profileService.getProfilById(userId);
    }

    @Test
    public void getProfilByIdNoProfile() throws BusinessException {
        final IProfileService profileService = new ProfileService();
        final Long userId = 12765L;

        new Expectations() {

            {
                Deencapsulation.setField(profileService, profileDAO);
                Deencapsulation.setField(profileService, userDAO);
                Deencapsulation.setField(profileService, profilBOConverter);

                UserBO userBO = new UserBO();

                userDAO.get(userId);
                returns(userBO);

                profilBOConverter.convert(userBO.getProfile());
                returns(null);
            }
        };

        profileService.getProfilById(userId);
    }

    @Test(expected = BusinessException.class)
    public void getProfilByIdNoUser() throws BusinessException {
        final IProfileService profileService = new ProfileService();
        final Long userId = 12765L;

        new Expectations() {

            {
                Deencapsulation.setField(profileService, profileDAO);
                Deencapsulation.setField(profileService, userDAO);
                Deencapsulation.setField(profileService, profilBOConverter);

                UserBO userBO = new UserBO();

                userDAO.get(userId);
                returns(null);

            }
        };

        profileService.getProfilById(userId);
    }

}
