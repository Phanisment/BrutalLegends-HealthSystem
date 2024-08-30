package io.phanisment.brutal.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityResurrectEvent;

import io.phanisment.brutal.HealthSystem;

public class DisableTotem implements Listener{
	private final HealthSystem plugin;
	
	public DisableTotem(HealthSystem plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onResurrect(EntityResurrectEvent event) {
		Boolean ENABLE = this.plugin.getConfig().getBoolean("Disable_Vanilla_System.Disable_Totem_Function");
		if (ENABLE == true) {
			event.setCancelled(true);
		}
	}
}