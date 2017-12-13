package me.technopvp.hgkits.commands.subcommands;

import me.technopvp.common.managers.MessageManager;
import me.technopvp.hgkits.managers.KitManager;
import me.technopvp.hgkits.utilities.enums.CommandType;

import org.bukkit.entity.Player;

public class EnableKit extends SubCommand {

	public boolean onCommand(Player player, String[] args) {
		if (args.length == 0) {
			MessageManager.message(true, player, getSubCommandUsage());
			return true;
		}
		if (args.length == 1) {
			String kitName = args[0];

			/* Enable kit method, all checking done there.*/
			KitManager.enableKit(player, kitName);
			return true;
		}
		return true;
	}

	public EnableKit() {
		super(CommandType.ADMIN, "enable <kitName>", "Enable a kit", "enable");
	}

}
