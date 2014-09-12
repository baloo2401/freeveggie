package org.mdubois.freeveggie.framework.play;

public class QueryParameter {
	/**
	 * The parameter key.
	 */
	private String key;
	/**
	 * The parameter value.
	 */
	private String value;

	/**
	 * Constructor
	 * 
	 * @param pKey
	 *            - The parameter key
	 * @param pValue
	 *            - The parameter value
	 */
	public QueryParameter(final String pKey, final String pValue) {
		super();
		this.key = pKey;
		this.value = pValue;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param pKey
	 *            the key to set
	 */
	public void setKey(final String pKey) {
		this.key = pKey;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param pValue
	 *            the value to set
	 */
	public void setValue(final String pValue) {
		this.value = pValue;
	}

}
