package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class PartialUserBOToMsgConverter extends AbstractConverter<PartialUserMsg, PartialUserBO> implements Converter<PartialUserMsg, PartialUserBO> {

    @Override
    public PartialUserMsg doConvert(PartialUserBO pPartialUserBO) {
        PartialUserMsg partialUserMsg = new PartialUserMsg();
        partialUserMsg.setId(pPartialUserBO.getId());
        partialUserMsg.setCreationDate(pPartialUserBO.getCreationDate());
        partialUserMsg.setId(pPartialUserBO.getId());
        partialUserMsg.setLastConnexion(pPartialUserBO.getLastConnexion());
        partialUserMsg.setProfileImgFilename(pPartialUserBO.getProfileImgFilename());
        partialUserMsg.setRole(pPartialUserBO.getRole());
        partialUserMsg.setUsername(pPartialUserBO.getUsername());
        partialUserMsg.setFirstname(pPartialUserBO.getFirstname());
        partialUserMsg.setLastname(pPartialUserBO.getLastname());
        return partialUserMsg;
    }
}
