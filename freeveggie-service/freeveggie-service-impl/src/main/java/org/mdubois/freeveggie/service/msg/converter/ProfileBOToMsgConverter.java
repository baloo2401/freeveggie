package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bo.ProfileBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.ProfileMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProfileBOToMsgConverter extends AbstractConverter<ProfileMsg, ProfileBO> implements Converter<ProfileMsg, ProfileBO> {

    @Override
    public ProfileMsg doConvert(ProfileBO pProfilBO) {
        ProfileMsg profilMsg = new ProfileMsg();
        profilMsg.setId(pProfilBO.getId());
        profilMsg.setExperience(pProfilBO.getExperience());
        profilMsg.setInterest(pProfilBO.getInterest());
        profilMsg.setOther(pProfilBO.getOther());
        profilMsg.setParticipation(pProfilBO.getParticipation());
        profilMsg.setPersonalDescription(pProfilBO.getPersonalDescription());
        profilMsg.setPhilosophy(pProfilBO.getPhilosophy());
        profilMsg.setPreferedMeals(pProfilBO.getPreferedMeals());
        profilMsg.setReason(pProfilBO.getReason());
        profilMsg.setUserPictureFilename(pProfilBO.getUserPictureFilename());

        return profilMsg;
    }
}
