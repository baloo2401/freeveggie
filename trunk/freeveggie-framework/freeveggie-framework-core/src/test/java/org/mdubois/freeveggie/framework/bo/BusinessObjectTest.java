package org.mdubois.freeveggie.framework.bo;

import org.mdubois.freeveggie.framework.bo.BusinessObject;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Mickael Dubois
 */
public class BusinessObjectTest {
    /**
     * Test of hashCode method, of class BusinessObject.
     */
    @Test
    public void testHashCode() {
        BusinessObjectImpl testObject1 = new BusinessObjectImpl(1L);
        BusinessObjectImpl testObject3 = new BusinessObjectImpl(1L);

        Assert.assertEquals(testObject1.hashCode(), testObject1.hashCode());
        Assert.assertEquals(testObject3.hashCode(), testObject1.hashCode());
    }

    /**
     * Test of equals method, of class BusinessObject.
     */
    @Test
    public void testEquals() {

        BusinessObjectImpl testObject1 = new BusinessObjectImpl(1L);
        BusinessObjectImpl testObject2 = new BusinessObjectImpl(2L);
        BusinessObjectImpl testObject3 = new BusinessObjectImpl(1L);
        BusinessObjectImpl testObject4 = new BusinessObjectImplBis(1L);

        Assert.assertFalse(testObject1.equals(null));
        Assert.assertEquals(testObject1, testObject1);
        Assert.assertEquals(testObject1, testObject3);
        Assert.assertNotSame(testObject1, testObject2);
        Assert.assertNotSame(testObject1, testObject4);
    }
    /**
     * Test of toString method, of class BusinessObject.
     */
    @Test
    public void testToString() {
        BusinessObject instance = new BusinessObjectImpl(1L);
        String result = instance.toString();
        Assert.assertTrue(result.contains("1"));
    }

    public class BusinessObjectImpl extends BusinessObject<Long> {

        protected Long id;

        public BusinessObjectImpl(Long id) {
            this.id = id;
        }

        @Override
        public Long getId() {
            return id;
        }
    }

    public class BusinessObjectImplBis extends BusinessObjectImpl{

        public BusinessObjectImplBis(Long id) {
            super(id);
        }

    }
}
