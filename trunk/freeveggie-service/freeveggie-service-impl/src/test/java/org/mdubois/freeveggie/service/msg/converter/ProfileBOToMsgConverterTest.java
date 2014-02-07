package org.mdubois.freeveggie.service.msg.converter;

import org.mdubois.freeveggie.bo.ProfileBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.ProfileMsg;

/**
 *
 * @author Mickael Dubois
 */
public class ProfileBOToMsgConverterTest extends AbstractConverterTest<ProfileMsg, ProfileBO> {

    @Override
    public ProfileMsg getExpectedMessage() {
        ProfileMsg expResult = new ProfileMsg();
        expResult.setId(10L);
        expResult.setExperience("experience");
        expResult.setInterest("interrest");
        expResult.setOther("other");
        expResult.setParticipation("participation");
        expResult.setPersonalDescription("personal description");
        expResult.setPhilosophy("philosophy");
        expResult.setPreferedMeals("prefered meal");
        expResult.setReason("reason");
        expResult.setUserPictureFilename("user picture file name");

        return expResult;
    }

    @Override
    public ProfileBO getBusinessObject() {
        ProfileBO pProfileBO = new ProfileBO();
        pProfileBO.setId(10L);
        pProfileBO.setExperience("experience");
        pProfileBO.setInterest("interrest");
        pProfileBO.setOther("other");
        pProfileBO.setParticipation("participation");
        pProfileBO.setPersonalDescription("personal description");
        pProfileBO.setPhilosophy("philosophy");
        pProfileBO.setPreferedMeals("prefered meal");
        pProfileBO.setReason("reason");
        pProfileBO.setUserPictureFilename("user picture file name");

        return pProfileBO;
    }

    @Override
    public Converter<ProfileMsg, ProfileBO> getConverter() {
        return new ProfileBOToMsgConverter();
    }
}
