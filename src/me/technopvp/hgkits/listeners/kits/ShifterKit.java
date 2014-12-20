package me.technopvp.hgkits.listeners.kits;

import me.technopvp.hgkits.managers.KitManager;
import me.technopvp.hgkits.utilities.KitUtils;
import me.technopvp.hgkits.utilities.user.UserUtils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class ShifterKit implements Listener {

	@EventHandler
	public void onShifterEvent(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();

		if (UserUtils.getUserKit(player.getName()).equals("shifter")) {
			if (player.isSneaking()) {
				KitManager.giveKitArmor(player, "shifter");
			} else {
				KitUtils.setArmor(player, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS);
			}
		}
	}
}
