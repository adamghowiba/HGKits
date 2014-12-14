package me.technopvp.hgkits.commands.subcommands;

import me.technopvp.hgkits.managers.KitManager;
import me.technopvp.hgkits.managers.MessageManager;
import me.technopvp.hgkits.utilities.enums.CommandType;

import org.bukkit.entity.Player;

public class DisableKit extends SubCommand {

	public boolean onCommand(Player player, String[] args) {
		if (args.length == 0) {
			MessageManager.message(true, player, getSubCommandUsage());
			return true;
		}
		if (args.length == 1) {
			String kitName = args[0];

			KitManager.disableKit(player, kitName);
			return true;
		}
		return true;
	}

	public DisableKit() {
		super(CommandType.ADMIN, "disable <kitName>", "Disable a kit", "disable");
	}

}
