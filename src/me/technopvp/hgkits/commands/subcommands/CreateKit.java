package me.technopvp.hgkits.commands.subcommands;

import me.technopvp.common.managers.MessageManager;
import me.technopvp.hgkits.utilities.enums.CommandType;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CreateKit extends SubCommand {

	public boolean onCommand(Player player, String[] args) {
		if (args.length == 0) {
			MessageManager.message(true, player, getSubCommandUsage());
			return true;
		}
		if (args.length == 1) {
			String kitName = args[0];

			/* Create kit method. All checking is done there */
				player.sendMessage(ChatColor.RED + "You created kit " + kitName);
			return true;
		}
		return true;
	}

	public CreateKit() {
		super(CommandType.ADMIN, "create <kitName>", "Create a kit", "create");
	}

}
