package org.mdubois.freeveggie.service.msg;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.Month;
import org.mdubois.freeveggie.ProductType;
import org.mdubois.freeveggie.framework.msg.Message;

/**
 * The reference fruit and vegetable.
 * @author Mickael Dubois
 */
@XmlRootElement
public class RefProductMsg extends Message {

    /**
     * The database id.
     */
    private Integer id;
    /**
     * The name of the product.
     */
    private String name;
    /**
     * The product type.
     */
    private ProductType type;
    /**
     * Content of protein for 100g of the product.
     */
    private Double protein;
    /**
     * Content of lipid for 100g of the product.
     */
    private Double lipid;
    /**
     * Content of carbohydrate for 100g of the product.
     */
    private Double carbohydrate;
    /**
     * An image of the product
     */
    private String pictureFilename;
    /**
     * The reaping months.
     */
    private List<Month> reapMonths;
    /**
     * The seedling months.
     */
    private List<Month> seedMonths;

    /**
     * Get the database id.
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the database id.
     * @param pId The id to set.
     */
    public void setId(Integer pId) {
        this.id = pId;
    }

    /**
     * Get the quantity of carbohydrate in 100g of the product.
     * @return - The quantity in g
     */
    public Double getCarbohydrate() {
        return carbohydrate;
    }

    /**
     * Set the quantity of carbohydrate in 100g of the product.
     * @param pCarbohydrate - The quantity in g to to set
     */
    public void setCarbohydrate(Double pCarbohydrate) {
        this.carbohydrate = pCarbohydrate;
    }

    /**
     * Get the picture of the product.
     * @return - The picture
     */
    public String getPictureFilename() {
        return pictureFilename;
    }

    /**
     * Set the picture of the product.
     * @param pPictureFilename - The picture to set
     */
    public void setPictureFilename(String pPictureFilename) {
        this.pictureFilename = pPictureFilename;
    }

    /**
     * Get the quantity of lipid in 100g of the product.
     * @return - The quantity in g
     */
    public Double getLipid() {
        return lipid;
    }

    /**
     * Set the quantity of carbohydate in 100g of the product.
     * @param pLipid - The quantity in g to to set
     */
    public void setLipid(Double pLipid) {
        this.lipid = pLipid;
    }

    /**
     * Get the name of the product.
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the product.
     * @param pName - The name to set
     */
    public void setName(String pName) {
        this.name = pName;
    }

    /**
     * Get the quantity of protein in 100g of the product.
     * @return - The quantity in g
     */
    public Double getProtein() {
        return protein;
    }

    /**
     * Set the quantity of carbohydate in 100g of the product.
     * @param pProtein - The quantity in g to to set
     */
    public void setProtein(Double pProtein) {
        this.protein = pProtein;
    }

    /**
     * Get the list of month when the product should be reaped.
     * @return The list of month
     */
    public List<Month> getReapMonths() {
        return reapMonths;
    }

    /**
     * Set the list of month when the product should be reaped.
     * @param pReapMonths - The list of month to set
     */
    public void setReapMonths(List<Month> pReapMonths) {
        this.reapMonths = pReapMonths;
    }

    /**
     * Get the month of when the product should be seeded.
     * @return The list of month
     */
    public List<Month> getSeedMonths() {
        return seedMonths;
    }

    /**
     * Set the list of month when the product should be seeded.
     * @param pSeedMonths - The list of month to set
     */
    public void setSeedMonths(List<Month> pSeedMonths) {
        this.seedMonths = pSeedMonths;
    }

    /**
     * Get the product type (Fruit or Vegetable).
     * @return The product
     */
    public ProductType getType() {
        return type;
    }

    /**
     * Set the product type.
     * @param pType - The product type to set
     */
    public void setType(ProductType pType) {
        this.type = pType;
    }

}
