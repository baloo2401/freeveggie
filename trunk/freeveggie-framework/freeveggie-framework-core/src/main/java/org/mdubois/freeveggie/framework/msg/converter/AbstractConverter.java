package org.mdubois.freeveggie.framework.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.List;
// </editor-fold>

/**
 * A basic converter implementation. This use the converter API to implement a default
 * manner to implement the doConvert list method.
 *
 * @author Mickael Dubois
 */
public abstract class AbstractConverter<TARGET,SOURCE> implements Converter<TARGET,SOURCE> {

    public abstract TARGET doConvert(SOURCE pSource);

    @Override
    public TARGET convert(SOURCE pSource) {
        if(pSource != null){
            return doConvert(pSource);
        } else {
            return null;
        }
    }



    @Override
    public List<TARGET> convert(List<SOURCE> pSource) {
        if(pSource!=null && !pSource.isEmpty()){
            List<TARGET> targets = new ArrayList<TARGET>();
            for (SOURCE source : pSource) {
               targets.add(convert(source));
            }
            return targets;
        }
        return new ArrayList<TARGET>(0);
    }

}
