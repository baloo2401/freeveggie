package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import mockit.Expectations;
import org.junit.Test;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.msg.Message;
import org.unitils.reflectionassert.ReflectionAssert;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public abstract class BusinessObjectConverterTest<Target extends BusinessObject, Source extends Message> {

    public abstract Target getNewBusinessObject();
    public abstract Source getNewMessage();
    public abstract BusinessObjectConverter<Target,Source> getConverter();
    public abstract Expectations getConvertCallExpectaion();

    @Test
    public void testCreateNew(){
        Target expectedBO = getNewBusinessObject();
        Source message = getNewMessage();
        BusinessObjectConverter<Target,Source> converter = getConverter();

        getConvertCallExpectaion();

        Target result = converter.createNew(message);

        ReflectionAssert.assertLenientEquals(expectedBO, result);
        ReflectionAssert.assertLenientEquals(result, expectedBO);
    }

    @Test
    public void testUpdate(){
        Target expectedBO = getNewBusinessObject();
        Target result = getNewBusinessObject();
        Source message = getNewMessage();
        BusinessObjectConverter<Target,Source> converter = getConverter();

        converter.update(result, message);

        ReflectionAssert.assertLenientEquals(expectedBO, result);
        ReflectionAssert.assertLenientEquals(result, expectedBO);
    }




}
