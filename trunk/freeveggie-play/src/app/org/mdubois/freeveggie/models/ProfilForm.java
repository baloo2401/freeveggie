package org.mdubois.freeveggie.models;

import org.mdubois.freeveggie.service.msg.ProfileMsg;

import play.data.validation.Constraints.MaxLength;

public class ProfilForm {
	/**
	 * The user personal picture
	 */
	@MaxLength(value = 512)
	private String userPictureFilename;

	/**
	 * A personal description.
	 */
	@MaxLength(value = 512)
	private String personalDescription;

	/**
	 * A sentence on how to participate to the idea.
	 */
	@MaxLength(value = 512)
	private String participation;

	/**
	 * Share an experience.
	 */
	@MaxLength(value = 512)
	private String experience;

	/**
	 * Give what the user is interest in.
	 */
	@MaxLength(value = 512)
	private String interest;

	/**
	 * The user philosophy in life.
	 */
	@MaxLength(value = 512)
	private String philosophy;

	/**
	 * Anything the user want to say.
	 */
	@MaxLength(value = 512)
	private String other;

	/**
	 * The user prefered meals.
	 */
	@MaxLength(value = 512)
	private String preferedMeals;

	/**
	 * The user reason to be on the website.
	 */
	private String reason;

	/**
	 * Get the picture of the garden.
	 * 
	 * @return - The picture
	 */
	public String getUserPictureFilename() {
		return userPictureFilename;
	}

	/**
	 * Set the picture of the garden.
	 * 
	 * @param pUserPictureFilename
	 *            - The picture to set
	 */
	public void setUserPictureFilename(String pUserPictureFilename) {
		this.userPictureFilename = pUserPictureFilename;
	}

	/**
	 * Get an expresion message.
	 * 
	 * @return The message
	 */
	public String getExperience() {
		return experience;
	}

	/**
	 * Set the expression message.
	 * 
	 * @param pExperience
	 *            - The message to set
	 */
	public void setExperience(String pExperience) {
		this.experience = pExperience;
	}

	/**
	 * Get an interest message.
	 * 
	 * @return The message
	 */
	public String getInterest() {
		return interest;
	}

	/**
	 * Set the interest message.
	 * 
	 * @param interest
	 *            - The message to set
	 */
	public void setInterest(String interest) {
		this.interest = interest;
	}

	/**
	 * Get a message that the user want to write.
	 * 
	 * @return The message
	 */
	public String getOther() {
		return other;
	}

	/**
	 * Set the user message.
	 * 
	 * @param other
	 *            The message to set
	 */
	public void setOther(String other) {
		this.other = other;
	}

	/**
	 * Get the message left aMsgut particiaption
	 * 
	 * @return The message
	 */
	public String getParticipation() {
		return participation;
	}

	/**
	 * Set the messsage for participation idea.
	 * 
	 * @param participation
	 *            - The message to set
	 */
	public void setParticipation(String participation) {
		this.participation = participation;
	}

	/**
	 * Get the personal description.
	 * 
	 * @return The description
	 */
	public String getPersonalDescription() {
		return personalDescription;
	}

	/**
	 * Set the personal description.
	 * 
	 * @param personalDescription
	 *            - The description to set
	 */
	public void setPersonalDescription(String personalDescription) {
		this.personalDescription = personalDescription;
	}

	/**
	 * Get the phylosophy message of the user.
	 * 
	 * @return The message
	 */
	public String getPhilosophy() {
		return philosophy;
	}

	/**
	 * Set the philosophie message.
	 * 
	 * @param philosophy
	 *            - The message to set
	 */
	public void setPhilosophy(String philosophy) {
		this.philosophy = philosophy;
	}

	/**
	 * Get the prefered meal message.
	 * 
	 * @return The message
	 */
	public String getPreferedMeals() {
		return preferedMeals;
	}

	/**
	 * Set the prefered meal message.
	 * 
	 * @param preferedMeals
	 *            - The message to set
	 */
	public void setPreferedMeals(String preferedMeals) {
		this.preferedMeals = preferedMeals;
	}

	/**
	 * Get the user reason of been a free veggie user.
	 * 
	 * @return The user reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * Set the user reason of been a free veggie user
	 * 
	 * @param reason
	 *            - The reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	public ProfileMsg getCorrespondingProfilMsg() {
		ProfileMsg profileMsg = new ProfileMsg();
		profileMsg.setExperience(experience);
		profileMsg.setInterest(interest);
		profileMsg.setOther(other);
		profileMsg.setParticipation(participation);
		profileMsg.setPersonalDescription(personalDescription);
		profileMsg.setPhilosophy(philosophy);
		profileMsg.setPreferedMeals(preferedMeals);
		profileMsg.setReason(reason);
		profileMsg.setUserPictureFilename(userPictureFilename);
		return profileMsg;
	}
}
