package org.mdubois.freeveggie.bean.it;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
// </editor-fold>

/**
 *
 * @author mdubois
 */
@Ignore
public abstract class AbstractBeanIntegrationTest {

    /**
     * {@link EJBContainer}
     */
    protected static EJBContainer container;
    private static Throwable containerThrowable;

    @Before
    public void setUp() throws Throwable {
        if (containerThrowable == null) {
            if (container == null) {
                if (StringUtils.isNotBlank(System.getProperty("GLASSFISH_HOME"))
                        && StringUtils.isNotBlank(System.getProperty("INSTANCE_ROOT"))) {
                    try {
                        String GLASSFISH_HOME = System.getProperty("GLASSFISH_HOME");
                        String INSTANCE_ROOT = GLASSFISH_HOME + System.getProperty("INSTANCE_ROOT");
                        String CONFIG_FILE = INSTANCE_ROOT + "/config/domain.xml";

                        Map<String, Object> props = new HashMap<String, Object>();
                        props.put(EJBContainer.MODULES, new File("target/classes"));
                        props.put("org.glassfish.ejb.embedded.glassfish.installation.root", GLASSFISH_HOME);
                        props.put("org.glassfish.ejb.embedded.glassfish.instance.root", INSTANCE_ROOT);
                        props.put("org.glassfish.ejb.embedded.glassfish.configuration.file", CONFIG_FILE);

                        container = EJBContainer.createEJBContainer(props);
                    } catch (Exception e) {
                        containerThrowable = e;
                        throw e;
                    }
                } else {
                    Assert.fail("GLASSFISH_HOME and/or INSTANCE_ROOT property have not be set to integration can't be run");
                }
            }
        } else {
            throw containerThrowable;
        }
    }
}
