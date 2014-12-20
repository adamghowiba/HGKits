package me.technopvp.hgkits.commands.subcommands;

import me.technopvp.common.utilities.player.InventoryUtils;
import me.technopvp.hgkits.managers.MessageManager;
import me.technopvp.hgkits.utilities.enums.CommandType;
import me.technopvp.hgkits.utilities.enums.Permissions.Permission;
import me.technopvp.hgkits.utilities.enums.Permissions.PermissionUtils;
import me.technopvp.hgkits.utilities.user.UserUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ClearKit extends SubCommand {

	@SuppressWarnings("deprecation")
	public boolean onCommand(Player player, String[] args) {
		if (args.length == 0) {
			UserUtils.removeUserKit(player.getName());
			InventoryUtils.clearLoadout(player);
			MessageManager.message(true, player, "&aYour kit has been cleared.");
			return true;
		}
		if (args.length == 1) {
			if (PermissionUtils.hasPermission(player, Permission.ADMIN)) {
				Player target = Bukkit.getPlayer(args[0]);

				if (target == null) {
					MessageManager.message(true, player, "Player '&6" + args[0] + "&a' is not currently online.");
					return true;
				}

				MessageManager.message(true, player, "&aYou have cleared &6" + target.getName() + "'s &akit.");
				MessageManager.message(true, target, "&aYour kit has been cleared by &6" + player.getName() + "&a.");
				UserUtils.removeUserKit(target.getName());
				InventoryUtils.clearLoadout(target);
				return true;
			}
			return true;
		}
		return true;
	}

	public ClearKit() {
		super(CommandType.DEFAULT, "clearkit", "Clear a kit", "clear");
	}

}
