package org.mdubois.freeveggie.service.msg.converter;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.unitils.reflectionassert.ReflectionAssert;

/**
 *
 * @author Mickael Dubois
 */
public abstract class AbstractConverterTest<T, S>{

    public abstract T getExpectedMessage();

    public abstract S getBusinessObject();

    public abstract Converter<T,S> getConverter();

    @Test
    public void testConvertMinimal(){
        T expectedResult = getExpectedMessage();
        S origine = getBusinessObject();

        Converter<T,S> converter = getConverter();

        T result = converter.convert(origine);

        ReflectionAssert.assertLenientEquals(expectedResult, result);

    }

    @Test
    public void testConvertList(){
        List<T> expectedMessages  = new ArrayList<T>();
        List<S> origines = new ArrayList<S>();

        expectedMessages.add(getExpectedMessage());
        expectedMessages.add(getExpectedMessage());

        origines.add(getBusinessObject());
        origines.add(getBusinessObject());

        Converter<T,S> converter = getConverter();

        List<T> result = converter.convert(origines);

        Assert.assertEquals(2, result.size());

        ReflectionAssert.assertLenientEquals(getExpectedMessage(), result.get(0));
        ReflectionAssert.assertLenientEquals(getExpectedMessage(), result.get(1));
    }

}
