package org.mdubois.freeveggie.service.exp.rest;

import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.JavaType;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;

/**
 * Utility class for JSON needs.
 *
 * @author a000gbw
 *
 */
public class JSONUtils {

    /**
     * Create a {@link TechnicalInformation} from JSON String.
     *
     * @param pJSONTechnicalInformation - The JSON string to convert
     * @param pCriteriaColumnClass - The {@link CriteriaColumn} of the
     * {@link TechnicalInformation} to create
     * @param pOrderColumnClass - The {@link OrderColumn} of the
     * {@link TechnicalInformation} to create
     * @return A {@link TechnicalInformation}
     * @throws BusinessException - If the JSON String is not applicable to the
     * {@link TechnicalInformation} requested
     */
    public static <T extends CriteriaColumn, V extends OrderColumn> TechnicalInformation<T, V> getTechnicalInformation(final String pJSONTechnicalInformation, Class<? extends CriteriaColumn> pCriteriaColumnClass, Class<? extends OrderColumn> pOrderColumnClass) throws BusinessException {
        if (StringUtils.isNotEmpty(pJSONTechnicalInformation)) {
            try {
                JavaType javaType = FreeveggieMapper.getInstance().getTypeFactory().constructParametricType(TechnicalInformation.class, pCriteriaColumnClass, pOrderColumnClass);
                TechnicalInformation<T, V> technicalInformation = FreeveggieMapper.getInstance().readValue(pJSONTechnicalInformation, javaType);
                return technicalInformation;
            } catch (IOException ex) {
                throw new BusinessException("Couldn't parse JSON string: " + pJSONTechnicalInformation);
            }
        } else {
            return new TechnicalInformation<T, V>();
        }

    }
}
