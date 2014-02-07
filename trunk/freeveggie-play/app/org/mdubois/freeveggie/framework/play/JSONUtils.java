package org.mdubois.freeveggie.framework.play;

import java.io.IOException;

import org.codehaus.jackson.type.JavaType;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;

import play.libs.WS.Response;

/**
 * Utility class for JSON needs.
 * 
 * @author a000gbw
 * 
 */
public class JSONUtils {

/**
	 * Create a {@link TechnicalInformation} from JSON String.
	 * @param pJSONTechnicalInformation - The JSON string to convert
	 * @param pCriteriaColumnClass - The {@link CriteriaColumn} of the {@link TechnicalInformation} to create 
	 * @param pOrderColumnClass - The {@link OrderColumn} of the {@link TechnicalInformation} to create 
	 * @return A {@link TechnicalInformation
	 * @throws BusinessException - If the JSON String is not applicable to the {@link TechnicalInformation} requested
	 */
	public static <T extends CriteriaColumn, V extends OrderColumn> TechnicalInformation<T, V> getTechnicalInformation(
			final String pJSONTechnicalInformation, Class<? extends CriteriaColumn> pCriteriaColumnClass,
			Class<? extends OrderColumn> pOrderColumnClass) throws BusinessException {
		if (!"".equals(pJSONTechnicalInformation)) {
			try {
				JavaType javaType = FreeveggieMapper.getInstance().getTypeFactory()
						.constructParametricType(TechnicalInformation.class, pCriteriaColumnClass, pOrderColumnClass);
				TechnicalInformation<T, V> technicalInformation = FreeveggieMapper.getInstance().readValue(
						pJSONTechnicalInformation, javaType);
				return technicalInformation;
			} catch (IOException ex) {
				throw new BusinessException("Couldn't parse JSON string: " + pJSONTechnicalInformation, ex);
			}
		} else {
			return new TechnicalInformation<T, V>();
		}

	}

	/**
	 * Create a JSON String that represent an error. The message will look like
	 * that {"errorCode":"${pReponse.getStatus(), "errorMessage":"${pReponse.
	 * getBody()}"}"}
	 * 
	 * @param pReponse
	 *            {@link Response}
	 * @return
	 */
	public static String createJSONError(Response pReponse) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"errorCode\":\"");
		sb.append(pReponse.getStatus());
		sb.append("\",\"errorMessage\":\"");
		sb.append(pReponse.getBody());
		sb.append("\"}");
		return sb.toString();
	}

	/**
	 * 
	 * Create a JSON String that represent an error. The message will look like
	 * that {"errorCode":"${pReponse.getStatus(), "errorMessage":"pMessage"}"}
	 * 
	 * @param pReponse
	 *            {@link Response}
	 * @param pMessage
	 *            The message to replace the error
	 * @return
	 */
	public static String createJSONError(Response pReponse, final String pMessage) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"errorCode\":\"");
		sb.append(pReponse.getStatus());
		sb.append("\",\"errorMessage\":\"");
		sb.append(pMessage);
		sb.append("\"}");
		return sb.toString();
	}
}
