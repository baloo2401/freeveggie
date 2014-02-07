package org.mdubois.freeveggie.framework.msg.validator;

import java.lang.annotation.Annotation;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mdubois.freeveggie.framework.msg.Length;
import org.mdubois.freeveggie.framework.msg.Required;

/**
 *
 * @author Mickael Dubois
 */
public class RequiredConstraintValidatorTest {

    /**
     * Test of accept method, of class RequiredConstraintValidator.
     */
    @Test
    public void testAccept() {
        RequiredConstraintValidator instance = new RequiredConstraintValidator();
        Required required = new Required() {

            @Override
            public String field() {
                return "";
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return Required.class;
            }

        };
        instance.setAnnotation(required);
        assertFalse(instance.accept(null));
        assertTrue(instance.accept(""));
        assertTrue(instance.accept(" "));
        assertTrue(instance.accept(1));
        assertTrue(instance.accept(0L));
        assertTrue(instance.accept(new Object()));
    }

    /**
     * Test of accept method, of class RequiredConstraintValidator.
     */
    @Test
    public void testAcceptComplexe() {
        RequiredConstraintValidator instance = new RequiredConstraintValidator();
        Required required = new Required() {

            @Override
            public String field() {
                return "id";
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return Required.class;
            }

        };
        instance.setAnnotation(required);
        assertFalse(instance.accept(new Message()));

        Message m = new Message();
        m.setId("");
        assertTrue(instance.accept(m));
    }

    private class Message {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


    }
}
