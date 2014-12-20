package me.technopvp.hgkits.commands.subcommands;

import me.technopvp.hgkits.managers.MessageManager;
import me.technopvp.hgkits.managers.SubCommandManager;
import me.technopvp.hgkits.utilities.enums.CommandType;
import me.technopvp.hgkits.utilities.enums.Permissions.Permission;
import me.technopvp.hgkits.utilities.enums.Permissions.PermissionUtils;

import org.bukkit.entity.Player;

public class HelpCommand extends SubCommand {

	public boolean onCommand(Player player, String[] args) {
		if (args.length == 0) {
			if (PermissionUtils.hasPermission(player, Permission.ADMIN)) {
				MessageManager.message(true, player, "&6**** &a&lAdmin Commands &6****");
				for (SubCommand commands : SubCommandManager.cmds) {
					if (commands.getCommandType().equals(CommandType.ADMIN)) {
						MessageManager.message(true, player, "&6/&a" + commands.getUsage() + " &6- &a" + commands.getDescription());
					}
				}
				MessageManager.message(true, player, "&6**** &a&lPlayer Commands &6****");
				for (SubCommand commands : SubCommandManager.cmds) {
					if (commands.getCommandType().equals(CommandType.DEFAULT)) {
						MessageManager.message(true, player, "&6/&a" + commands.getUsage() + " &6- &a" + commands.getDescription());
					}
				}
				MessageManager.message(true, player, "&6/&a" + "kits" + " &6- &a" + "Lists all the kits.");
			}
			return true;
		}

		return true;
	}
	public HelpCommand() {
		super(CommandType.DEFAULT, "help", "List the help", "help");
	}

}
