package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenMsgToBOConverter implements BusinessObjectConverter<GardenBO, GardenMsg> {

    @Override
    public GardenBO createNew(GardenMsg pGardenMsg) {
        GardenBO gardenBO = new GardenBO();
        //has to be set by ther service
        //gardenBO.setAddress(addressBOConverter.createNew(pGardenMsg.getAddress())); has to be set by the service
        gardenBO.setCreationDate(SystemTime.asDate());
        gardenBO.setDescription(pGardenMsg.getDescription());
        gardenBO.setName(pGardenMsg.getName());
        gardenBO.setStatus(Status.CREATED);
        //gardenBO.setOwner(); has to be set by the service
        return gardenBO;
    }

    @Override
    public void update(GardenBO pRGardenBO, GardenMsg pGardenMsg) {
        pRGardenBO.setDescription(pGardenMsg.getDescription());
        pRGardenBO.setName(pGardenMsg.getName());
        //gardenBO.setOwner(); has to be set by the service
    }
}
