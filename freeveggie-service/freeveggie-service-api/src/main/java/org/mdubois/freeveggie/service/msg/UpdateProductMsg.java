package org.mdubois.freeveggie.service.msg;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.CultureMode;
import org.mdubois.freeveggie.CultureType;
import org.mdubois.freeveggie.ExchangeType;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.Required;
// </editor-fold>

/**
 * A user product to edit.
 * @author Mickael DuMsgis
 */
@XmlRootElement
public class UpdateProductMsg extends Message {

    /**
     * The database id.
     */
    @Required
    private Long id;
    /**
     * The name of the product.
     */
    @Required
    private String name;
    /**
     * The garden picture
     */
    private String pictureFilename;
    /**
     * The reference product.
     */
    @Required(field = "id")
    private RefProductMsg referenceProduct;
    /**
     * The exchange type.
     */
    @Required
    private ExchangeType exchangeType;
    /**
     * The culture mode.
     */
    @Required
    private CultureMode cultureMode;
    /**
     * The culture mode.
     */
    @Required
    private CultureType cultureType;
    /**
     * The quantity of product that the user want to free.
     */
    @Required
    private Float quantity;
    /**
     * The user comment of the product.
     */
    @Required
    private String description;

    /**
     * The status of the product.
     */
    private Status status;

    /**
     * Get the database id.
     * @return The id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the database id.
     * @param pId The id to set.
     */
    public void setId(Long pId) {
        this.id = pId;
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
     * Get the reference product.
     * @return The reference product
     */
    public RefProductMsg getReferenceProduct() {
        return referenceProduct;
    }

    /**
     * Set the reference product.
     * @param pReferenceProduct - The product to set
     */
    public void setReferenceProduct(RefProductMsg pReferenceProduct) {
        this.referenceProduct = pReferenceProduct;
    }

    /**
     * Get the weight the product is produce.
     * @return The culture mode
     */
    public CultureMode getCultureMode() {
        return cultureMode;
    }

    /**
     * Set the weight the product is produce.
     * @param pCultureMode - The culture mode to set
     */
    public void setCultureMode(CultureMode pCultureMode) {
        this.cultureMode = pCultureMode;
    }

    /**
     * Get the weight the product is growing.
     * @return The culture type
     */
    public CultureType getCultureType() {
        return cultureType;
    }

    /**
     * Set the weight the product is growing.
     * @param pCultureType - The culture type to set
     */
    public void setCultureType(CultureType pCultureType) {
        this.cultureType = pCultureType;
    }

    /**
     * Get the weight the user want to exchange his product.
     * @return The exchange type
     */
    public ExchangeType getExchangeType() {
        return exchangeType;
    }

    /**
     * Set the weight the user want to exchange his product.
     * @param pExchangeType - The exchange type to set.
     */
    public void setExchangeType(ExchangeType pExchangeType) {
        this.exchangeType = pExchangeType;
    }

    /**
     * Get the quantity in gram that the user want to free.
     * @return The quantity.
     */
    public Float getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of product to free (in gram).
     * @param quantity - The quantity to set
     */
    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    /**
     * Get the comment that the owner make on his product.
     * @return  The comment
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the comment of the product.
     * @param description - The comment to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * Get the status of the product.
     * @return The status of the product
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set the status of the product.
     * @param status - The status of the product to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
