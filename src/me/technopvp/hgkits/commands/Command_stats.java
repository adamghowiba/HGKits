package me.technopvp.hgkits.commands;

import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.managers.MessageManager;
import me.technopvp.hgkits.utilities.enums.Permissions;
import me.technopvp.hgkits.utilities.enums.Permissions.Permission;
import me.technopvp.hgkits.utilities.enums.Source;
import me.technopvp.hgkits.utilities.enums.SourceType;
import me.technopvp.hgkits.utilities.user.UserUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.ANYONE)
public class Command_stats extends CommonCommand {
	HGKits plugin = HGKits.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;

		if (args.length == 0) {
			MessageManager.message(false, player, "&6**** &a" + player.getName() + " &6****");
			MessageManager.message(false, player, "&aKills: &6" + UserUtils.getKills(player.getName()));
			MessageManager.message(false, player, "&aDeaths: &6" + UserUtils.getDeaths(player.getName()));
			MessageManager.message(false, player, "&aCurrent Killstreak: &6" + UserUtils.getCurrentKillstreak(player.getName()));
			return true;
		}
		return true;
	}
}
