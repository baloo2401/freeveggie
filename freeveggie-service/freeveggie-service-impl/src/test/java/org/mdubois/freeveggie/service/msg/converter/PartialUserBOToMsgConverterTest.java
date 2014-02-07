package org.mdubois.freeveggie.service.msg.converter;

import java.util.Date;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;

/**
 *
 * @author Mickael Dubois
 */
public class PartialUserBOToMsgConverterTest extends AbstractConverterTest<PartialUserMsg, PartialUserBO> {

    private static final Date NOW = new Date();

    @Override
    public PartialUserMsg getExpectedMessage() {
        PartialUserMsg expResult = new PartialUserMsg();
        expResult.setCreationDate(NOW);
        expResult.setId(10L);
        expResult.setLastConnexion(NOW);
        expResult.setProfileImgFilename("profile img file name");
        expResult.setRole(UserRole.USER);
        expResult.setUsername("username");
        expResult.setFirstname("firstname");
        expResult.setLastname("lastname");
        return expResult;
    }

    @Override
    public PartialUserBO getBusinessObject() {
        PartialUserBO pPartialUserBO = new PartialUserBO();
        pPartialUserBO.setCreationDate(NOW);
        pPartialUserBO.setId(10L);
        pPartialUserBO.setLastConnexion(NOW);
        pPartialUserBO.setProfileImgFilename("profile img file name");
        pPartialUserBO.setRole(UserRole.USER);
        pPartialUserBO.setUsername("username");
        pPartialUserBO.setFirstname("firstname");
        pPartialUserBO.setLastname("lastname");
        return pPartialUserBO;
    }

    @Override
    public Converter<PartialUserMsg, PartialUserBO> getConverter() {
        return new PartialUserBOToMsgConverter();
    }
}
