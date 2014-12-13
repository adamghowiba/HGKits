package me.technopvp.hgkits.commands;

import me.technopvp.common.utilities.enums.Level;
import me.technopvp.hgkits.managers.KitManager;
import me.technopvp.hgkits.managers.MessageManager;
import me.technopvp.hgkits.utilities.KitUtils;
import me.technopvp.hgkits.utilities.enums.Permissions;
import me.technopvp.hgkits.utilities.enums.Permissions.Permission;
import me.technopvp.hgkits.utilities.enums.Source;
import me.technopvp.hgkits.utilities.enums.SourceType;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_kit extends CommonCommand {

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player) sender;

		if (args.length == 0) {
			/* Check if the array list is empty, is it? */
			if (!KitUtils.listKits(sender).isEmpty()) {
				MessageManager.message(true, sender, "&aKits: " + KitUtils.listKits(sender));
			} else {
				/* Shit.. It's empty... Just... give them some message about there being no kits */
				MessageManager.message(true, sender, "&aThere are currently no kits.");
				return true;
			}
			return true;
		}
		if (args.length == 1) {
			String kitName = args[0];

			/* Check if there we're ANY erros that occoured while grabbing the kit */
			try {
				/* Let's try this. Just for percautions, better save then sorry */
				KitManager.giveKit(player, kitName);
				return true;
			} catch (Exception execption) {
				/* Well... I guess soemthig went wrong. Let's make sure they know */
				MessageManager.log(Level.HIGH, player.getName() + " has recived an error while trying to get the kit '" + kitName + "'.");
			}
		}
		if (args.length >= 2) {
			String kitName = args[1];

			/*
			 * TODO
			 * Arguments for kit options [remove, disable, create, enable]
			 * Add sub-commands into individual class. Handle checking in class.
			 */

			if (args[0].equalsIgnoreCase("remove")) {
				KitManager.removeKit(player, kitName);
				return true;
			}

			if (args[0].equalsIgnoreCase("disable")) {
				KitManager.disableKit(player, kitName);
				return true;
			}
			if (args[0].equalsIgnoreCase("enable")) {
				KitManager.enableKit(player, kitName);
				return true;
			}
			if (args[0].equalsIgnoreCase("create")) {
				KitManager.createKit(player, kitName);
				return true;
			}
		}
		return true;
	}
}
