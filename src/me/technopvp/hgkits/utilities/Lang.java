package me.technopvp.hgkits.utilities;


public enum Lang {

	PREFIX("&a[&6HGKITS&a]"),
	KIT_MENU("&cKit Menu.");

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
		return message + " ";
	}


	public void setMessage(String message) {
		this.message = message;
	}

}
