package me.technopvp.hgkits.managers;

import me.technopvp.common.utilities.CommonCore;
import me.technopvp.common.utilities.StringUtils;
import me.technopvp.common.utilities.enums.BroadcastType;
import me.technopvp.common.utilities.enums.Level;
import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.utilities.Lang;
import me.technopvp.hgkits.utilities.enums.Permissions.Permission;
import me.technopvp.hgkits.utilities.enums.Permissions.PermissionUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageManager extends CommonCore {
	static HGKits plugin = HGKits.instance;

	/**
	 * @param sender
	 *            - The console or player.
	 * @param message
	 *            - The string, that will be sent.
	 * @param colorize
	 *            - Colorize the text (replace '&')
	 *
	 * @see MessageManager#message(sender, message, colorize)
	 */

	public static void message(boolean prefix, CommandSender sender, String message) {
		sender.sendMessage((prefix == true ? Lang.PREFIX.getMessage() + StringUtils.colorize(message)
				: StringUtils.colorize(message)));
	}

	/**
	 * @param sender
	 *            - The console or player.
	 * @param message
	 *            - The string, that will be sent.
	 *
	 * @see MessageManager#message(sender, message)
	 */

	public static void message(CommandSender sender, String message) {
		sender.sendMessage(StringUtils.colorize(message));
	}

	/**
	 * @param command
	 *            - The sender that will be returned the usage..
	 * @param command
	 *            - The command usage it will out put.
	 *
	 * @see MessageManager#broadcast(sender, message)
	 */

	public static void showUsage(CommandSender sender, Command command) {
		MessageManager.message(sender, ChatColor.YELLOW + "Usage: " + command.getUsage().replaceAll("<command>", command.getName()));
	}

	/**
	 * @param message
	 *            - The string, that will be sent.
	 *
	 * @see MessageManager#broadcast(player, message, colorize)
	 */

	public static void broadcast(String message) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.sendMessage(StringUtils.colorize(message));
		}
	}

	/**
	 * @param castType
	 *            - The castType for the broadcast.
	 * @param message
	 *            - The string, that will be broadcasted.
	 *
	 * @see MessageManager#broadcast(sender, message)
	 */

	public static void broadcast(BroadcastType castType, String message) {
		MessageManager.broadcast((castType == null ? "" : castType.getPrefix()) + message);
	}

	/**
	 * @param castType
	 *            - The castType for the broadcast.
	 * @param message
	 *            - The string, that will be sent.
	 * @param colorize
	 *            - Colorize the text (replace '&')
	 *
	 * @see MessageManager#broadcast(sender, message, colorize)
	 */

	public static void broadcast(BroadcastType castType, String message, boolean colorize) {
		broadcast(castType, (colorize == true ? StringUtils.colorize(message)
				: message));
	}

	/**
	 * @param castType
	 *            - The castType for the broadcast.
	 * @param permission
	 *            - The permission required to get the broadcast.
	 * @param message
	 *            - The string, that will be broadcasted.
	 *
	 * @see MessageManager#broadcast(sender, message)
	 */

	public static void broadcastWPermission(BroadcastType castType, String permission, String message) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (all.hasPermission(permission)) {
				broadcast(castType, StringUtils.colorize(message));
			}
		}
	}

	/**
	 * @param permission
	 *            - The permission required to get the broadcast.
	 * @param message
	 *            - The string, that will be sent.
	 *
	 * @see MessageManager#broadcastWPermission(permission, message)
	 */

	public static void broadcastWPermission(String permission, String message) {
		broadcastWPermission(permission, message);
	}

	/**
	 * @param castType
	 *            - The casting type, for the broadcast.
	 * @param message
	 *            - The string, that will be sent.
	 *
	 * @see MessageManager#broadcast(castType, message)
	 */

	public static void broadcastOps(BroadcastType castType, String message) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (all.isOp()) {
				broadcast(castType, StringUtils.colorize(message));
			}
		}
	}

	/**
	 * @param castType
	 *            - The casting type, for the broadcast.
	 * @param permissionLevel
	 *            - The permission level required to get the broadcast.
	 * @param message
	 *            - The string, that will be sent.
	 *
	 * @see broadcastWPermissionLevel#broadcast(castType, permissionLevel,
	 *      message)
	 */

	public static void broadcastWPermissionLevel(BroadcastType castType, Permission permissionLevel, String message) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (PermissionUtils.hasPermission(all, permissionLevel)) {
				broadcast(castType, StringUtils.colorize(message));
			}
		}
	}

	/**
	 * @param permissionLevel
	 *            - The sender that will be returned the usage.
	 * @param message
	 *            - The string, that will be sent.
	 *
	 * @see MessageManager#broadcastWPermissionLevel(permissionLevel, message)
	 */

	public static void broadcastWPermissionLevel(Permission permissionLevel, String message) {
		broadcastWPermissionLevel(permissionLevel, message);
	}

	/**
	 * @param level
	 *            - The level of atire it will be logged at.
	 * @param message
	 *            - The string, that will be sent.
	 *
	 * @see MessageManager#log(level, message)
	 */

	public static void log(Level level, String message) {
		plugin.getServer().getConsoleSender().sendMessage(level.getPrefix() + message);
	}

	/**
	 * @param message
	 *            - The string, that will be sent.
	 *
	 * @see MessageManager#log(message)
	 */

	public static void log(String message) {
		plugin.log.info(message);
	}
}
