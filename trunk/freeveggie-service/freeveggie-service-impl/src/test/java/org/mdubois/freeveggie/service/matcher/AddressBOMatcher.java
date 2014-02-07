package org.mdubois.freeveggie.service.matcher;

import org.apache.commons.lang.StringUtils;
import org.mdubois.freeveggie.bo.AddressBO;

/**
 *
 * @author Mickael Dubois
 */
public class AddressBOMatcher<AdressBO> extends BusinessObjectMatcher {

    private static final String ADDRESS_LINE1_ARE_NOT_MATCHING = "Line 1 are not matching";
    private static final String ADDRESS_LINE2_ARE_NOT_MATCHING = "Line 2 are not matching";
    private static final String NAME_ARE_NOT_MATCHING = "Name's are note matching";
    private static final String STREET_NUMBER_ARE_NOT_MATCHING = "Street number are not matching";
    private AddressBO addressBO;

    public AddressBOMatcher(AddressBO pAddressBO) {
        super(pAddressBO);
        this.addressBO = pAddressBO;
    }

    @Override
    public boolean matches(Object item) {
        if (super.matches(item)) {
            AddressBO lAddressBO = (AddressBO) item;
            if (testStreetName(lAddressBO)) {
                    if (testName(lAddressBO)) {
                        if (testStreetNumber(lAddressBO)) {
                            return true;
                        }
                        errorDescription = STREET_NUMBER_ARE_NOT_MATCHING;
                    }
                    errorDescription = NAME_ARE_NOT_MATCHING;
            }
            errorDescription = ADDRESS_LINE1_ARE_NOT_MATCHING;
        }
        return false;
    }

    private boolean testStreetNumber(AddressBO pAddressBO) {
        if (pAddressBO.getStreetNumber() != null && addressBO.getStreetNumber() != null) {
            if (pAddressBO.getStreetNumber().equals(addressBO.getStreetNumber())) {
                return true;
            }
        } else if (pAddressBO.getStreetNumber() == null && addressBO.getStreetNumber() == null) {
            return true;
        }
        return false;
    }

    private boolean testName(AddressBO pAddressBO) {
        if (!StringUtils.isEmpty(pAddressBO.getName()) && !StringUtils.isEmpty(addressBO.getName())) {
            if (pAddressBO.getName().trim().equals(addressBO.getName().trim())) {
                return true;
            }
        } else if (StringUtils.isEmpty(pAddressBO.getName()) && StringUtils.isEmpty(addressBO.getName())) {
            return true;
        }
        return false;
    }

    private boolean testStreetName(AddressBO pAddressBO) {
        if (!StringUtils.isEmpty(pAddressBO.getStreetName()) && !StringUtils.isEmpty(addressBO.getStreetName())) {
            if (pAddressBO.getStreetName().trim().equals(addressBO.getStreetName().trim())) {
                return true;
            }
        } else if (StringUtils.isEmpty(pAddressBO.getStreetName()) && StringUtils.isEmpty(addressBO.getStreetName())) {
            return true;
        }
        return false;
    }
}
