package org.mdubois.freeveggie.service.msg;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.CultureMode;
import org.mdubois.freeveggie.CultureType;
import org.mdubois.freeveggie.ExchangeType;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.Required;
// </editor-fold>

/**
 * A user product.
 * @author Mickael DuMsgis
 */
@XmlRootElement
public class ProductMsg extends Message {

    /**
     * The database id.
     */
    private Long id;
    /**
     * The garden that this product belong to.
     */
    @Required(field = "id")
    private GardenMsg garden;
    /**
     * The name of the product.
     */
    @Required
    private String name;
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
     * The list of picture of the garden
     */
    private List<PictureMsg> pictureMsg;

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
     * Get the garden from wich this product belong to.
     * @return The garden
     */
    public GardenMsg getGarden() {
        return garden;
    }

    /**
     * Set the garden that the product belong to.
     * @param pGarden - The garden to set
     */
    public void setGarden(GardenMsg pGarden) {
        this.garden = pGarden;
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

    /**
     * Set the list of picture.
     * @param pictureMsg - The list of picture to set
     */
    public void setPictureMsg(List<PictureMsg> pictureMsg) {
        this.pictureMsg = pictureMsg;
    }

    /**
     * Get the picture.
     * @return - The list of picture
     */
    public List<PictureMsg> getPictureMsg() {
        return pictureMsg;
    }
}
