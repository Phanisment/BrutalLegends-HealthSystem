package io.phanisment.brutal.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		AttributeInstance health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		double baseHealth = health.getBaseValue();
		if (baseHealth > 1.0) {
			health.setBaseValue(baseHealth - 2.0);
		} else {
			health.setBaseValue(2.0);
		}
	}
}