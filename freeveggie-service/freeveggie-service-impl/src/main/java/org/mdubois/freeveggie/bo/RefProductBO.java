package org.mdubois.freeveggie.bo;

import java.util.List;
import javax.persistence.*;
import org.mdubois.freeveggie.ProductType;
import static org.mdubois.freeveggie.bo.RefProductBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.RefBusinessObject;

/**
 * The reference fruit and vegetable.
 *
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_REF_PRODUCT")
@Cacheable(value = true)
@SuppressWarnings("serial")
@NamedQueries(value = {
    @NamedQuery(name = GET_PRODUCT_BY_NAME_NAME, query = GET_PRODUCT_BY_NAME_QUERY),
    @NamedQuery(name = GET_PRODUCT, query = GET_PRODUCT_QUERY),
    @NamedQuery(name = GET_PRODUCT_BY_PRODUCT_TYPE_NAME, query = GET_PRODUCT_BY_PRODUCT_TYPE_QUERY),
    @NamedQuery(name = GET_PRODUCT_BY_REAPSEASON_NAME, query = GET_PRODUCT_BY_REAPSEASON_QUERY),
    @NamedQuery(name = GET_PRODUCT_BY_SEEDSEASON_NAME, query = GET_PRODUCT_BY_SEEDSEASON_QUERY)})
public class RefProductBO extends RefBusinessObject<Integer> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 14L;
    /**
     * The database id.
     */
    @Id
    @Column(name = "rpc_id")
    private Integer id;
    /**
     * The name of the product.
     */
    @Column(name = "rpc_name", nullable = false)
    private String name;
    /**
     * The product type.
     */
    @Transient
    private ProductType type;
    /**
     * The technical value.
     */
    @Column(name = "rpc_rpt_id", nullable = false)
    private Integer _type;
    /**
     * Content of protein for 100g of the product.
     */
    @Column(name = "rpc_protein")
    private Double protein;
    /**
     * Content of lipid for 100g of the product.
     */
    @Column(name = "rpc_lipid")
    private Double lipid;
    /**
     * Content of carbohydrate for 100g of the product.
     */
    @Column(name = "rpc_carbohydrate")
    private Double carbohydrate;
    /**
     * An image of the product
     */
    @Column(name = "rpc_img_filename")
    private String pictureFilename;
    /**
     * The reaping months.
     */
    @ManyToMany(targetEntity = RefMonthBO.class)
    @JoinTable(name = "TJ_REAP_CALENDAR",
    joinColumns = {
        @JoinColumn(name = "rpc_id")
    },
    inverseJoinColumns = {
        @JoinColumn(name = "rmn_id")
    })
    private List<RefMonthBO> reapMonths;
    /**
     * The seedling months.
     */
    @ManyToMany(targetEntity = RefMonthBO.class)
    @JoinTable(name = "TJ_SEED_CALENDAR",
    joinColumns = {
        @JoinColumn(name = "rpc_id")
    },
    inverseJoinColumns = {
        @JoinColumn(name = "rmn_id")
    })
    private List<RefMonthBO> seedMonths;

    /**
     * Get the database id.
     *
     * @return The id
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Set the database id.
     *
     * @param pId The id to set.
     */
    public void setId(Integer pId) {
        this.id = pId;
    }

    /**
     * Get the quantity of carbohydrate in 100g of the product.
     *
     * @return - The quantity in g
     */
    public Double getCarbohydrate() {
        return carbohydrate;
    }

    /**
     * Set the quantity of carbohydrate in 100g of the product.
     *
     * @param pCarbohydrate - The quantity in g to to set
     */
    public void setCarbohydrate(Double pCarbohydrate) {
        this.carbohydrate = pCarbohydrate;
    }

    /**
     * Get the picture of the product.
     *
     * @return - The picture
     */
    public String getPictureFilename() {
        return pictureFilename;
    }

    /**
     * Set the picture of the product.
     *
     * @param pPictureFilename - The picture to set
     */
    public void setPictureFilename(String pPictureFilename) {
        this.pictureFilename = pPictureFilename;
    }

    /**
     * Get the quantity of lipid in 100g of the product.
     *
     * @return - The quantity in g
     */
    public Double getLipid() {
        return lipid;
    }

    /**
     * Set the quantity of carbohydate in 100g of the product.
     *
     * @param pLipid - The quantity in g to to set
     */
    public void setLipid(Double pLipid) {
        this.lipid = pLipid;
    }

    /**
     * Get the name of the product.
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the product.
     *
     * @param pName - The name to set
     */
    public void setName(String pName) {
        this.name = pName;
    }

    /**
     * Get the quantity of protein in 100g of the product.
     *
     * @return - The quantity in g
     */
    public Double getProtein() {
        return protein;
    }

    /**
     * Set the quantity of carbohydate in 100g of the product.
     *
     * @param pProtein - The quantity in g to to set
     */
    public void setProtein(Double pProtein) {
        this.protein = pProtein;
    }

    /**
     * Get the list of month when the product should be reaped.
     *
     * @return The list of month
     */
    public List<RefMonthBO> getReapMonths() {
        return reapMonths;
    }

    /**
     * Set the list of month when the product should be reaped.
     *
     * @param pReapMonths - The list of month to set
     */
    public void setReapMonths(List<RefMonthBO> pReapMonths) {
        this.reapMonths = pReapMonths;
    }

    /**
     * Get the mont of when the product should be seeded.
     *
     * @return The list of month
     */
    public List<RefMonthBO> getSeedMonths() {
        return seedMonths;
    }

    /**
     * Set the list of month when the product should be seeded.
     *
     * @param pSeedMonths - The list of month to set
     */
    public void setSeedMonths(List<RefMonthBO> pSeedMonths) {
        this.seedMonths = pSeedMonths;
    }

    /**
     * Get the product type (Fruit or Vegetable).
     *
     * @return The product
     */
    public ProductType getType() {
        return type;
    }

    /**
     * Set the product type.
     *
     * @param pType - The product type to set
     */
    public void setType(ProductType pType) {
        this.type = pType;
        if(pType !=  null){
            _type = pType.toInt();
        } else {
            _type = -1;
        }
    }

    @PostLoad
    void populateTransientFields() {
        type = ProductType.fromInt(_type);
    }

    public static final class QueryNamedConstant {

        /*
         * The name of GetProductByName query.
         */
        public static final String GET_PRODUCT_BY_NAME_NAME = "RefProductBO.getProductByName";
        /*
         * The GetProductByName query.
         */
        public static final String GET_PRODUCT_BY_NAME_QUERY = "SELECT e FROM RefProductBO e WHERE e.name LIKE :name ORDER BY e.name ASC";
        /*
         * The name of GetProductByProductType query.
         */
        public static final String GET_PRODUCT_BY_PRODUCT_TYPE_NAME = "RefProductBO.getProductByProductType";
        /*
         * The GetProductByType query.
         */
        public static final String GET_PRODUCT_BY_PRODUCT_TYPE_QUERY = "SELECT e FROM RefProductBO e WHERE e._type = :productType ORDER BY e._type ASC";
        /*
         * The name of GetProductByProductType query.
         */
        public static final String GET_PRODUCT = "RefProductBO.getProduct";
        /*
         * The GetProductByType query.
         */
        public static final String GET_PRODUCT_QUERY = "SELECT e FROM RefProductBO e";

        /*
         * The name of GetProductByReapSeason query.
         */
        public static final String GET_PRODUCT_BY_REAPSEASON_NAME = "RefProductBO.getProductByReapSeason";
        /*
         * The GetProductByReapSeason query.
         */
        public static final String GET_PRODUCT_BY_REAPSEASON_QUERY = "SELECT e FROM RefProductBO e JOIN e.reapMonths as reapMonth WHERE reapMonth.id IN (:reapMonths)";

        /*
         * The name of GetProductBySeedSeason query.
         */
        public static final String GET_PRODUCT_BY_SEEDSEASON_NAME = "RefProductBO.getProductBySeedSeason";
        /*
         * The GetProductBySeedSeason query.
         */
        public static final String GET_PRODUCT_BY_SEEDSEASON_QUERY = "SELECT e FROM RefProductBO e JOIN e.seedMonths as seedMonth WHERE seedMonth.id IN (:seedMonths)";

        /*
         * Default constructor.
         */
        private QueryNamedConstant() {
        }
    }
}
