package org.mdubois.freeveggie.framework.msg.converter;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

/**
 *
 * @author Mickael Dubois
 */
public class AbstractConverterTest {

    private static class Source {
        public int input;
        public Source() {
        }
    }

    private static class Target {
        public int input;
        public Target() {
        }

    }


    /**
     * Test of doConvert method, of class AbstractConverter.
     */
    @Test
    public void testConvert() {
        Source source = new Source();
        source.input = 12;
        Target target = new Target();
        target.input = 12;

        List<Source> pSource = new ArrayList<Source>();
        pSource.add(source);
        pSource.add(source);

        AbstractConverter instance = new AbstractConverterImpl();
        List result = instance.convert(pSource);
        assertEquals(2, result.size());
        ReflectionAssert.assertLenientEquals(target, result.get(0));
        ReflectionAssert.assertLenientEquals(target, result.get(1));
    }

    /**
     * Test of doConvert method, of class AbstractConverter.
     */
    @Test
    public void testConvertEmpty() {

        List<Source> pSource = new ArrayList<Source>();

        AbstractConverter instance = new AbstractConverterImpl();
        List result = instance.convert(pSource);
        Assert.assertNull(result);
    }

    /**
     * Test of doConvert method, of class AbstractConverter.
     */
    @Test
    public void testConvertNull() {

        AbstractConverter instance = new AbstractConverterImpl();
        List result = instance.convert(null);
        Assert.assertNull(result);
    }

    public class AbstractConverterImpl extends AbstractConverter<Target,Source> {

        @Override
        public Target doConvert(Source pSource) {
            Target newTarget = new Target();
            newTarget.input = pSource.input;
            return newTarget;
        }
    }
}
