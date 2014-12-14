package me.technopvp.hgkits.commands.subcommands;

import me.technopvp.hgkits.managers.KitManager;
import me.technopvp.hgkits.managers.MessageManager;
import me.technopvp.hgkits.utilities.enums.CommandType;

import org.bukkit.entity.Player;

public class UpdateKit extends SubCommand {

	public boolean onCommand(Player player, String[] args) {
		if (args.length == 0) {
			MessageManager.message(true, player, getSubCommandUsage());
			return true;
		}
		if (args.length == 1) {
			String kitName = args[0];

			/* Update kit method. check for overriding method */
			KitManager.createKit(player, kitName, true);
			return true;
		}
		return true;
	}

	public UpdateKit() {
		super(CommandType.ADMIN, "update <kitName>", "Update a kit", "update");
	}

}
