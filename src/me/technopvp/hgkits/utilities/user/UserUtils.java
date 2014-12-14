package me.technopvp.hgkits.utilities.user;

import me.technopvp.common.dCommon;
import me.technopvp.common.utilities.player.User;

public class UserUtils extends User {

	protected UserUtils(dCommon plugin, String user) {
		super(plugin, user);
	}

	public static void setUserKit(String user, String kitName) {
		getUser(user).getPlayerConfig().getConfig().set("Kit", kitName);
		getUser(user).getPlayerConfig().savePlayerConfig();
	}

	public static void removeUserKit(String user) {
		if (userHasKit(user) == true) {
			getUser(user).getPlayerConfig().getConfig().set("Kit", null);
			getUser(user).getPlayerConfig().savePlayerConfig();
		}
	}

	public static String getUserKit(String user) {
		return getUser(user).getPlayerConfig().getConfig().getString("Kit");
	}

	public static boolean userHasKit(String user) {
		return (getUserConfig(user).contains("Kit"));
	}

}
