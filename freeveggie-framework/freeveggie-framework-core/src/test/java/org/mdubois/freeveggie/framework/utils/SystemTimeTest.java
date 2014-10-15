package org.mdubois.freeveggie.framework.utils;

import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author mdubois
 */
@RunWith(JMockit.class)
public class SystemTimeTest {

    /**
     * Test of isCriteriaPresent method, of class CriteriaUtils.
     */
//    @Test
//    public void testAsMillis() {
//        SystemTime.reset();
//        Mockit.setUpMock(SystemMock.class);
//
//        Long ret = SystemTime.asMillis();
//        Assert.assertEquals(ret.longValue(), 123L);
//
//    }

    /**
     * Test of isCriteriaPresent method, of class CriteriaUtils.
     */
    @Test
    public void testSetTimeSource() {
        SystemTime.reset();
        ITimeSource timeSource = new ITimeSource() {

            @Override
            public long millis() {
                return 124L;
            }
        };
        SystemTime.setTimeSource(timeSource);
        Long ret = SystemTime.asMillis();
        Assert.assertEquals(ret.longValue(), 124L);

    }

    /**
     * Test of isCriteriaPresent method, of class CriteriaUtils.
     */
//    @Test
//    public void testAsDate() {
//        SystemTime.reset();
//        Mockit.setUpMock(SystemMock.class);
//        Date ret = SystemTime.asDate();
//        Assert.assertEquals(ret, new Date (123L));
//
//    }

//   @MockClass(realClass = System.class)
//   public static class SystemMock {
//       /**
//        * Fake current time millis returns value modified by required offset.
//        *
//        * @return fake "current" millis
//        */
//       @Mock
//       public static long currentTimeMillis() {
//           return 123L;
//       }
//   }

}
