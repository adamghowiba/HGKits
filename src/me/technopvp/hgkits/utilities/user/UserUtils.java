package me.technopvp.hgkits.utilities.user;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.player.User;

public class UserUtils extends User {

	protected UserUtils(dCommon plugin, String user) {
		super(plugin, user);
	}

	/**
	 * Set the user's kit in the user file.
	 *
	 * @param user
	 *            - The user that will be adding the kit to in there user file.
	 * @param kitName
	 *            - The name of the kit you will be adding.
	 */

	public static void setUserKit(String user, String kitName) {
		getUser(user).getPlayerConfig().getConfig().set("Kit", kitName);
		getUser(user).getPlayerConfig().savePlayerConfig();
	}

	/**
	 * Remove a users kit from there user file.
	 *
	 * @param user
	 *            - The user that you will be removing there kit.
	 *
	 */

	public static void removeUserKit(String user) {
		if (userHasKit(user) == true) {
			getUser(user).getPlayerConfig().getConfig().set("Kit", null);
			getUser(user).getPlayerConfig().savePlayerConfig();
		}
	}

	/**
	 * Returns the user kit in String from.
	 * It gets the kit from the an individual user's file, attached with dCommon.
	 *
	 * @param user
	 *            - The user from the string.
	 * @return The user's kit from the configeration file.
	 */

	public static String getUserKit(String user) {
		return getUser(user).getPlayerConfig().getConfig().getString("Kit");
	}

	/**
	 *
	 * Check if a user has a kit. <br>
	 * It will return the value depending on the condition.
	 *
	 * @param user
	 *            - The user you will be checking
	 * @return the condition on if they have the kit.
	 */

	public static boolean userHasKit(String user) {
		return (getUserConfig(user).contains("Kit"));
	}

	/**
	 *
	 * Check if a user has the specified kit is attachted to a user via configeration file. <br>
	 * It will return the value depending on the condition on if they have the specified kit.
	 *
	 * @param user
	 *            - The user you will be checking.
	 * @param kitName
	 *            - The name of the kit you are checking.
	 * @return
	 */

	public static boolean userHasKit(String user, String kitName) {
		return (getUserKit(user).equals(kitName));
	}
}
