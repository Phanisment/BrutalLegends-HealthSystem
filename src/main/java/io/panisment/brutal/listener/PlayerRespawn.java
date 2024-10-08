package io.phanisment.brutal.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.BanList;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;

import io.phanisment.brutal.HealthSystem;

public class PlayerRespawn implements Listener {
	private final HealthSystem plugin;
	
	public PlayerRespawn(HealthSystem plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		boolean ENABLE = this.plugin.getConfig().getBoolean("Custom_System.Health_System.Enable");
		if (ENABLE == true) {
			Player player = event.getPlayer();
			AttributeInstance health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
			double baseHealth = health.getBaseValue();
			double reduceHealth = this.plugin.getConfig().getDouble("Custom_System.Health_System.Reduce_Health");
			if (baseHealth > 1.0) {
				health.setBaseValue(baseHealth - reduceHealth);
			} else {
				health.setBaseValue(20.0);
				banPlayer(player);
			}
		}
	}
	
	private void banPlayer(Player player) {
		Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(player.getName(), "You're banned form server because your health is empty.", null, null);
		player.kickPlayer("You're banned form server because your health is empty.");
	}
}