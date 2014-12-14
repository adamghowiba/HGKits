package me.technopvp.hgkits.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import me.technopvp.common.utilities.enums.Level;
import me.technopvp.hgkits.commands.subcommands.CheckKit;
import me.technopvp.hgkits.commands.subcommands.CreateKit;
import me.technopvp.hgkits.commands.subcommands.DisableKit;
import me.technopvp.hgkits.commands.subcommands.EnableKit;
import me.technopvp.hgkits.commands.subcommands.RemoveKit;
import me.technopvp.hgkits.commands.subcommands.SubCommand;
import me.technopvp.hgkits.commands.subcommands.UpdateKit;
import me.technopvp.hgkits.utilities.KitUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SubCommandManager implements CommandExecutor {

	private ArrayList<SubCommand> cmds = new ArrayList<SubCommand>();

	public SubCommandManager() {
		/* Add the commands */
		cmds.add(new CreateKit());
		cmds.add(new RemoveKit());
		cmds.add(new CheckKit());
		cmds.add(new EnableKit());
		cmds.add(new DisableKit());
		cmds.add(new UpdateKit());
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use team commands!");
			return true;
		}
		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("kit")) {
			if (args.length == 0) {
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
			if (args.length >= 1) {
				SubCommand c = getCommand(args[0]);

				if (c == null) {
					String kitName = args[0];

					/* Check if there we're ANY erros that occoured while grabbing the kit */
					try {
						KitManager.giveKit(player, kitName);
					} catch (Exception execption) {
						/* Well... I guess soemthig went wrong. Let's make sure they know */
						MessageManager.log(Level.HIGH, player.getName() + " has recived an error while trying to get the kit '" + kitName + "'.");
					}
					return true;
				}

				Vector<String> a = new Vector<String>(Arrays.asList(args));
				a.remove(0);
				args = a.toArray(new String[a.size()]);

				c.onCommand(player, args);
				return true;
			}
			return true;
		}
		return true;
	}

	public String aliases(SubCommand cmd) {
		String fin = "";

		for (String a : cmd.getAliases()) {
			fin += a + " | ";
		}

		return fin.substring(0, fin.lastIndexOf(" | "));
	}

	private SubCommand getCommand(String name) {
		for (SubCommand cmd : cmds) {
			if (cmd.getClass().getSimpleName().equalsIgnoreCase(name)) return cmd;
			for (String alias : cmd.getAliases())
				if (name.equalsIgnoreCase(alias)) return cmd;
		}
		return null;
	}
}