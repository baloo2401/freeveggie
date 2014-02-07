package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bo.ProfileBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.service.msg.ProfileMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProfileMsgToBOConverter implements BusinessObjectConverter<ProfileBO, ProfileMsg> {

    @Override
    public ProfileBO createNew(ProfileMsg pProfileMsg) {
        ProfileBO profileBO = new ProfileBO();
        profileBO.setExperience(pProfileMsg.getExperience());
        profileBO.setInterest(pProfileMsg.getInterest());
        profileBO.setOther(pProfileMsg.getOther());
        profileBO.setParticipation(pProfileMsg.getParticipation());
        profileBO.setPersonalDescription(pProfileMsg.getPersonalDescription());
        profileBO.setPhilosophy(pProfileMsg.getPhilosophy());
        profileBO.setPreferedMeals(pProfileMsg.getPreferedMeals());
        profileBO.setReason(pProfileMsg.getReason());
        profileBO.setUserPictureFilename(pProfileMsg.getUserPictureFilename());
        return profileBO;
    }

    @Override
    public void update(ProfileBO pProfileBO, ProfileMsg pProfileMsg) {
        pProfileBO.setExperience(pProfileMsg.getExperience());
        pProfileBO.setInterest(pProfileMsg.getInterest());
        pProfileBO.setOther(pProfileMsg.getOther());
        pProfileBO.setParticipation(pProfileMsg.getParticipation());
        pProfileBO.setPersonalDescription(pProfileMsg.getPersonalDescription());
        pProfileBO.setPhilosophy(pProfileMsg.getPhilosophy());
        pProfileBO.setPreferedMeals(pProfileMsg.getPreferedMeals());
        pProfileBO.setReason(pProfileMsg.getReason());
        pProfileBO.setUserPictureFilename(pProfileMsg.getUserPictureFilename());
    }
}
