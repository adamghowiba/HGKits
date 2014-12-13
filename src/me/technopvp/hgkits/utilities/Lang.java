package me.technopvp.hgkits.utilities;

import me.technopvp.common.utilities.StringUtils;

public enum Lang {

	PREFIX("&a[&6HGKITS&a]");

	private String message;

	private Lang(String message) {
		this.message = message;
	}

	/**
	 *
	 * Get the a languge message.
	 *
	 * @return The string of the mssage, colorized.
	 */

	public String getMessage() {
		return StringUtils.colorize(message + " ");
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
