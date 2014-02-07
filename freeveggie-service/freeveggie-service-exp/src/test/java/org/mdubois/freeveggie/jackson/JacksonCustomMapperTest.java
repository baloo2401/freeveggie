package org.mdubois.freeveggie.jackson;


import junit.framework.Assert;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.junit.Test;
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.criteria.ProductRequestCriteriaColumn;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.ProductRequestOrderColumn;
import org.mdubois.freeveggie.service.exp.rest.FreeveggieMapper;
import org.unitils.reflectionassert.ReflectionAssert;

public class JacksonCustomMapperTest {

	@Test
	public void testSerialise() throws Exception {

		TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechInput = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
		RequestStatus requestStatus = RequestStatus.ACCEPTED;
		QueryCriteria<ProductRequestCriteriaColumn> statusCriteria = new QueryCriteria<ProductRequestCriteriaColumn>(
				ProductRequestCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
				requestStatus);
		pTechInput.addCriteria(statusCriteria);
		pTechInput.setPagination(new Pagination(1, 1));
		OrderWay pOrderWay = OrderWay.ASC;
		ProductRequestOrderColumn pOrderColumn = ProductRequestOrderColumn.ANSWER_DATE;
		ResultOrder<ProductRequestOrderColumn> pOrder = new ResultOrder<ProductRequestOrderColumn>(
				pOrderColumn, pOrderWay);
		pTechInput.setOrder(pOrder);

		String pTechnicalInformation = FreeveggieMapper.getInstance()
				.writeValueAsString(pTechInput);
		String expected = "{\"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":3}],\"pagination\":{\"pageNumber\":1,\"nbPerPage\":1},\"order\":{\"way\":\"ASC\",\"orderColumn\":\"ANSWER_DATE\"}}";
		Assert.assertEquals(expected, pTechnicalInformation);

	}

	@Test
	public void testDeserialise() throws Exception {

		TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechInput = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();

		QueryCriteria<ProductRequestCriteriaColumn> statusCriteria = new QueryCriteria<ProductRequestCriteriaColumn>(
				ProductRequestCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
				3/*RequestStatus.ACCEPTED*/);
		pTechInput.addCriteria(statusCriteria);
		pTechInput.setPagination(new Pagination(1, 1));
		OrderWay pOrderWay = OrderWay.ASC;
		ProductRequestOrderColumn pOrderColumn = ProductRequestOrderColumn.ANSWER_DATE;
		ResultOrder<ProductRequestOrderColumn> pOrder = new ResultOrder<ProductRequestOrderColumn>(
				pOrderColumn, pOrderWay);
		pTechInput.setOrder(pOrder);

		String JSONInput = "{\"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":3}],\"pagination\":{\"pageNumber\":1,\"nbPerPage\":1},\"order\":{\"way\":\"ASC\",\"orderColumn\":\"ANSWER_DATE\"}}";

		JavaType javaType = new ObjectMapper().getTypeFactory()
				.constructParametricType(TechnicalInformation.class,
						ProductRequestCriteriaColumn.class,
						ProductRequestOrderColumn.class);
		TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTech = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();

		pTech = FreeveggieMapper.getInstance().readValue(JSONInput, javaType);

		ReflectionAssert.assertReflectionEquals(pTech, pTechInput, null);
		
	}
}
