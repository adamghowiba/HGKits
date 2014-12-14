package me.technopvp.hgkits.commands.subcommands;

import me.technopvp.hgkits.managers.MessageManager;
import me.technopvp.hgkits.utilities.enums.CommandType;
import me.technopvp.hgkits.utilities.user.UserUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CheckKit extends SubCommand {

	@SuppressWarnings("deprecation")
	public boolean onCommand(Player player, String[] args) {
		if (args.length == 0) {
			MessageManager.message(true, player, getSubCommandUsage());
			return true;
		}
		if (args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);

			/* If the player has a kit, return the kit. If they don't tell them */
			MessageManager.message(true, player, "&a" + (UserUtils.userHasKit(target.getName()) == true
					? target.getName() + "'s kit: " + UserUtils.getUserKit(target.getName())
					: target.getName() + " does not have a kit."));
			return true;
		}
		return true;
	}

	public CheckKit() {
		super(CommandType.ADMIN,  "check <player>", "Check a players kit", "check");
	}

}
