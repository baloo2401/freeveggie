package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import mockit.Expectations;
import org.mdubois.freeveggie.bo.ProfileBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.service.msg.ProfileMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProfileMsgToBOConverterTest extends BusinessObjectConverterTest<ProfileBO,ProfileMsg>{


    private final ProfileMsgToBOConverter converter = new ProfileMsgToBOConverter();

    @Override
    public ProfileBO getNewBusinessObject() {
        ProfileBO  bo = new ProfileBO();
        bo.setExperience("experience");
        bo.setInterest("interest");
        bo.setOther("other");
        bo.setParticipation("participation");
        bo.setPersonalDescription("PersonalDescription");
        bo.setPhilosophy("Philosophy");
        bo.setPreferedMeals("PreferedMeals");
        bo.setReason("Reason");
        bo.setUserPictureFilename("UserPictureFilename");
        return bo;


    }

    @Override
    public ProfileMsg getNewMessage() {
        ProfileMsg  msg = new ProfileMsg();
        msg.setExperience("experience");
        msg.setInterest("interest");
        msg.setOther("other");
        msg.setParticipation("participation");
        msg.setPersonalDescription("PersonalDescription");
        msg.setPhilosophy("Philosophy");
        msg.setPreferedMeals("PreferedMeals");
        msg.setReason("Reason");
        msg.setUserPictureFilename("UserPictureFilename");
        return msg;
    }

    @Override
    public BusinessObjectConverter<ProfileBO,ProfileMsg> getConverter() {
        return converter;
    }

    @Override
    public Expectations getConvertCallExpectaion() {
        return new Expectations(){
            {

            }
        };
    }


}
