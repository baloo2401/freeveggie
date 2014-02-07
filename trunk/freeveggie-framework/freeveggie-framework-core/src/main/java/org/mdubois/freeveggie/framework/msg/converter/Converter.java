package org.mdubois.freeveggie.framework.msg.converter;

import java.util.List;

/**
 * The is to doConvert {@link BusinessObject} to {@link Message}.
 *
 * @author Mickael Dubois
 */
public interface Converter<Target,Source> {

    /**
     * It doConvert a object to another one.
     * @param pSource - The object source
     * @return The result object
     */
    Target convert(final Source pSource);

    /**
     * Convert a list of object to a list of another object
     * @param pSources - The list source
     * @return The result list
     */
    List<Target> convert(final List<Source> pSources);
}
