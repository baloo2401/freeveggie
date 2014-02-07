package org.mdubois.freeveggie.framework.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.io.Serializable;
import org.apache.commons.lang.ObjectUtils;
// </editor-fold>

/**
 * An database object.
 * @param <PK> - The primary key type.
 * @author Mickael Dubois
 */
public abstract class BusinessObject<PK extends Serializable> implements Serializable{

    /**
     * Get primary key value of the database object.
     * @return
     */
    public abstract PK getId();

    @Override
    public int hashCode() {
        return ObjectUtils.hashCode(getId());
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null || !this.getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }

        final BusinessObject other = BusinessObject.class.cast(obj);

        return ObjectUtils.equals(getId(), other.getId());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getClass().getSimpleName());
        stringBuilder.append("[ id=").append(getId()).append(" ]");
        return stringBuilder.toString();
    }
}
