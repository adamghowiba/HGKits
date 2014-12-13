package me.technopvp.hgkits.commands;

import me.technopvp.hgkits.HGKits;
import me.technopvp.hgkits.managers.MessageManager;
import me.technopvp.hgkits.utilities.KitUtils;
import me.technopvp.hgkits.utilities.enums.Permissions;
import me.technopvp.hgkits.utilities.enums.Permissions.Permission;
import me.technopvp.hgkits.utilities.enums.Source;
import me.technopvp.hgkits.utilities.enums.SourceType;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Source(SourceType.ANY)
@Permissions(Permission.ANYONE)
public class Command_kits extends CommonCommand {
	HGKits plugin = HGKits.instance;

	public boolean run(CommandSender sender, Command cmd, String[] args) {
		if (args.length >= 0) {
			/* Check if the array list is empty, is it? */
			if (!KitUtils.listKits(sender).isEmpty()) {
				MessageManager.message(true, sender, "&aKits: " + KitUtils.listKits(sender));
			} else {
				/* Shit.. It's empty... Just... give them some message about there being no kits */
				MessageManager.message(true, sender, "&aThere are currently no kits.");
				return true;
			}
			return true;
		}
		return true;
	}
}
