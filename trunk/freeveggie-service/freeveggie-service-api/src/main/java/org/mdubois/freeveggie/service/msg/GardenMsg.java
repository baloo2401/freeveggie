package org.mdubois.freeveggie.service.msg;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.framework.msg.Length;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.Required;
// </editor-fold>

/**
 * The garden of a user.
 *
 * @author Mickael DuMsgis
 */
@XmlRootElement
public class GardenMsg extends Message {

    /*
     * The database id.
     */
    private Long id;
    /**
     * A given name of the garden. The user define it.
     */
    @Required
    private String name;
    /**
     * A description given by the owner.
     */
    @Length(min = 10, max = 512)
    private String description;
    /**
     * The user address.
     */
    @Required(field = "id")
    private AddressMsg address;
    /**
     * The date when the garden have been created.
     */
    private Date creationDate;
    /**
     * The owner of the garden.
     */
    @Required(field = "id")
    private PartialUserMsg owner;
    /**
     * The products of the garden.
     */
    private List<ProductMsg> products;
    /**
     * The status of the garden.
     */
    private Status status;
    /**
     * The list of picture of the product
     */
    private List<PictureMsg> pictureMsg;

    /**
     * Get the owner of the garden.
     *
     * @return The owner
     */
    public PartialUserMsg getOwner() {
        return owner;
    }

    /**
     * Set the owner of the user.
     *
     * @param owner The owner to set
     */
    public void setOwner(PartialUserMsg owner) {
        this.owner = owner;
    }

    /**
     * Get the id database.
     *
     * @return The id database
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id database.
     *
     * @param pId - The database id to set
     */
    public void setId(Long pId) {
        this.id = pId;
    }

    /**
     * Get the user description of the garden.
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the user description.
     *
     * @param pDescription - The description to set
     */
    public void setDescription(String pDescription) {
        this.description = pDescription;
    }

    /**
     * Get the garden name set by th user.
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the garden.
     *
     * @param pName - The name to set
     */
    public void setName(String pName) {
        this.name = pName;
    }

    /**
     * Get the address of the garden.
     *
     * @return The address
     */
    public AddressMsg getAddress() {
        return address;
    }

    /**
     * Set the address of the garden.
     *
     * @param pAddress - The address to set
     */
    public void setAddress(AddressMsg pAddress) {
        this.address = pAddress;
    }

    /**
     * Get the date the user crate the garden.
     *
     * @return The creation date
     */
    public Date getCreationDate() {
        if (creationDate != null) {
            return new Date(creationDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Set the date of creation
     *
     * @param pCreationDate - The date to set
     */
    public void setCreationDate(Date pCreationDate) {
        if(pCreationDate!=null){
            this.creationDate = new Date(pCreationDate.getTime());
        } else {
            this.creationDate = null;
        }
    }

    /**
     * Get the status of the garden.
     *
     * @return The status of the garden
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set the status of the garden.
     *
     * @param status - The status of the garden to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Get product list.
     * @return The product list
     */
    public List<ProductMsg> getProducts() {
        return products;
    }

    /**
     * Set the product list.
     * @param products - The product list to set.
     */
    public void setProducts(List<ProductMsg> products) {
        this.products = products;
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
