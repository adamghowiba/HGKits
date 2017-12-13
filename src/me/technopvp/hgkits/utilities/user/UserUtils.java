package me.technopvp.hgkits.utilities.user;

import me.technopvp.common.Basic;
import me.technopvp.common.utilities.player.User;

public class UserUtils extends User {

	protected UserUtils(Basic plugin, String user) {
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
		getUser(user).getPlayerConfig().getConfig().set("Kit.kit", kitName);
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
			getUser(user).getPlayerConfig().getConfig().set("Kit.kit", null);
			getUser(user).getPlayerConfig().savePlayerConfig();
		}
	}

	/**
	 * Returns the user kit in String from. <br>
	 * It gets the kit from the an individual user's file, attached with
	 * dCommon.
	 *
	 * @param user
	 *            - The user from the string.
	 * @return The user's kit from the configeration file.
	 */

	public static String getUserKit(String user) {
		return getUser(user).getPlayerConfig().getConfig().getString("Kit.kit");
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
		return (getUserConfig(user).contains("Kit.kit"));
	}

	/**
	 *
	 * Check if a user has the specified kit is attachted to a user via
	 * configeration file. <br>
	 * It will return the value depending on the condition on if they have the
	 * specified kit.
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

	/**
	 * Add a kill to a players stats, in the data base.
	 *
	 * @param user
	 *            - The user you will be adding a kill to.
	 */

	public static void addKill(String user) {
		getUserConfig(user).set("Kit.kills", getUserConfig(user).getInt("Kit.kills") + 1);
		getUser(user).getPlayerConfig().savePlayerConfig();
	}

	/**
	 * Add a kill to a players stats, in the data base.
	 *
	 * @param user
	 *            - The user you will be adding a kill to.
	 */

	public static void setKills(String user, int kills) {
		getUserConfig(user).set("Kit.kills", kills);
		getUser(user).getPlayerConfig().savePlayerConfig();
	}

	/**
	 * Remove a kill from a user, from the datab base.
	 *
	 * @param user
	 *            - The user you will be removing a kill from.
	 */

	public static void removeKill(String user) {
		getUserConfig(user).set("Kit.kills", getUserConfig(user).getInt("Kit.kills") - 1);
		getUser(user).getPlayerConfig().savePlayerConfig();
	}

	/**
	 * Add a death to a user, in the data base.
	 *
	 * @param user
	 *            - The user you will be adding a death to.
	 */

	public static void addDeath(String user) {
		getUserConfig(user).set("Kit.deaths", getUserConfig(user).getInt("Kit.deaths") + 1);
		getUser(user).getPlayerConfig().savePlayerConfig();
	}

	/**
	 * Add a death to a user, in the data base.
	 *
	 * @param user
	 *            - The user you will be adding a death to.
	 */

	public static void setDeaths(String user, int deaths) {
		getUserConfig(user).set("Kit.deaths", deaths);
		getUser(user).getPlayerConfig().savePlayerConfig();
	}

	/**
	 * Remove a death from a user.
	 *
	 * @param user
	 *            - The user you will be removing a death from.
	 */

	public static void removeDeath(String user) {
		getUserConfig(user).set("Kit.deaths", getUserConfig(user).getInt("Kit.deaths") - 1);
		getUser(user).getPlayerConfig().savePlayerConfig();
	}

	/**
	 * Get the deat's of a user from the data base. <br>
	 * In this instance we will be grabbing it from our FLATLINE configeration
	 * file.
	 *
	 * @return The user's deaths.
	 */

	public static int getDeaths(String user) {
		return getUserConfig(user).getInt("Kit.deaths");
	}

	/**
	 * Get the user's kills from the data base.
	 *
	 * @return The users kills.
	 */

	public static int getKills(String user) {
		return getUserConfig(user).getInt("Kit.kills");
	}

	/**
	 * Set a users current kill streak, to an integer.
	 *
	 * @param killstreak
	 *            - The integer you will be setting as the user's killstreak.
	 * @param user
	 *            - The user you will be removing a kill from.
	 */

	public static void setKillstreak(String user, int killstreak) {
		getUserConfig(user).set("Kit.killstreak", killstreak);
		getUser(user).getPlayerConfig().savePlayerConfig();
	}

	/**
	 * Remove a kill from a user, from the datab base.
	 *
	 * @return The users current kill streak.
	 */

	public static int getCurrentKillstreak(String user) {
		return getUserConfig(user).getInt("Kit.killstreak");
	}

	public static void resetStats(String user) {
		setKillstreak(user, 0);
		setDeaths(user, 0);
		setKills(user, 0);
	}

}
