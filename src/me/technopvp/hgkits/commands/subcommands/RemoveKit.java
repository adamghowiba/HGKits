package me.technopvp.hgkits.commands.subcommands;

import me.technopvp.common.managers.MessageManager;
import me.technopvp.hgkits.managers.KitManager;
import me.technopvp.hgkits.utilities.enums.CommandType;

import org.bukkit.entity.Player;

public class RemoveKit extends SubCommand {

	public boolean onCommand(Player player, String[] args) {
		if (args.length == 0) {
			MessageManager.message(true, player, getSubCommandUsage());
			return true;
		}
		if (args.length == 1) {
			String kitName = args[0];

			/* Remove kit method. All checking done there */
			KitManager.removeKit(player, kitName);
			return true;
		}
		return true;
	}

	public RemoveKit() {
		super(CommandType.ADMIN, "remove <kitName>", "Delete a kit", "remove", "delete");
	}

}
