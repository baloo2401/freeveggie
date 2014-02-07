package org.mdubois.freeveggie.framework.utils;

import java.util.Date;

/**
 *
 * @author Mickael Dubois
 */
public final class SystemTime {

    private SystemTime() {
    }

    private static final ITimeSource DEFAULT_SRC =
            new ITimeSource() {

                @Override
                public long millis() {
                    return System.currentTimeMillis();
                }
            };
    private static ITimeSource source = null;

    public static long asMillis() {
        return getTimeSource().millis();
    }

    public static Date asDate() {
        return new Date(asMillis());
    }

    public static void reset() {
        setTimeSource(null);
    }

    public static void setTimeSource(ITimeSource source) {
        SystemTime.source = source;
    }

    private static ITimeSource getTimeSource() {
        return (source != null ? source : DEFAULT_SRC);
    }
}