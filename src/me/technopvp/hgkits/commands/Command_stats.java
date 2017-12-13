package me.technopvp.hgkits.commands;

import java.util.Arrays;
import java.util.List;

import me.technopvp.common.managers.MessageManager;
import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.utilities.enums.Permissions;
import me.technopvp.hgkits.utilities.enums.Permissions.Permission;
import me.technopvp.hgkits.utilities.enums.Source;
import me.technopvp.hgkits.utilities.enums.SourceType;
import me.technopvp.hgkits.utilities.user.UserUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Source(SourceType.ANY)
@Permissions(Permission.ANYONE)
public class Command_stats extends CommonCommand {
	HGKits plugin = HGKits.instance;

	@SuppressWarnings("deprecation")
	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player) sender;
		List<String> types = Arrays.asList("deaths", "kills", "killstreak");

		if (args.length == 0) {
			MessageManager.message(false, player, "&6**** &a" + player.getName() + " &6****");
			MessageManager.message(false, player, "&aKills: &6" + UserUtils.getKills(player.getName()));
			MessageManager.message(false, player, "&aDeaths: &6" + UserUtils.getDeaths(player.getName()));
			MessageManager.message(false, player, "&aCurrent Killstreak: &6" + UserUtils.getCurrentKillstreak(player.getName()));
			return true;
		}
		if (args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				targetOffline(args[0]);
				return true;
			}
			MessageManager.message(false, player, "&6**** &a" + target.getName() + " &6****");
			MessageManager.message(false, player, "&aKills: &6" + UserUtils.getKills(target.getName()));
			MessageManager.message(false, player, "&aDeaths: &6" + UserUtils.getDeaths(target.getName()));
			MessageManager.message(false, player, "&aCurrent Killstreak: &6" + UserUtils.getCurrentKillstreak(target.getName()));
			return true;
		}
		if (args.length <= 4) {
			if (args[0].equalsIgnoreCase("reset")) {
				// /stats reset tpvp
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) targetOffline(args[1]);
				UserUtils.resetStats(target.getName());
				player.sendMessage(Yellow + "You have reset " + Gold + target.getName() + "'s" + Yellow + " stats.");
				return true;
			}
			else if (args[0].equalsIgnoreCase("set")) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) targetOffline(args[1]);
				/* /stats set tpvp kills 1 */
				if (types.contains(args[2])) {
					Integer num = Integer.parseInt(args[3]);
					UserUtils.getUserConfig(target.getName()).set("Kit." + args[2], num);
					UserUtils.getUser(target.getName()).getPlayerConfig().savePlayerConfig();
					player.sendMessage(ChatColor.YELLOW + "You have set " + Gold + target.getName() + Yellow + "'s " + Gold + args[2] + Yellow + " to " + Gold + num);
					return true;
				}
			}else {
				showUsage(cmd);
				return true;
			}
		}else {
			showUsage(cmd);
			return true;
		}
		return true;
	}
}
