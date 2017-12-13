package me.technopvp.hgkits.commands;

import me.technopvp.common.managers.MessageManager;
import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.utilities.enums.Permissions;
import me.technopvp.hgkits.utilities.enums.Permissions.Permission;
import me.technopvp.hgkits.utilities.enums.Source;
import me.technopvp.hgkits.utilities.enums.SourceType;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Source(SourceType.PLAYER)
@Permissions(Permission.ANYONE)
public class Command_drop extends CommonCommand {
	HGKits plugin = HGKits.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		Player player = (Player)sender;

		if (args.length == 0) {
			if (!player.getItemInHand().getType().equals(Material.AIR)) {
				player.setItemInHand(new ItemStack(Material.AIR));
			}else {
				MessageManager.message(true, player, "&aYou are not holding an item");
				return true;
			}
			return true;
		}
		return true;
	}

}
