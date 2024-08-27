package io.phanisment.brutal.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityResurrectEvent;

public class DisableTotem implements Listener{
	private final HealthSystem plugin;
	
	public DisableTotem(HealthSystem plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onResurrect(EntityResurrectEvent event) {
		Boolean DISABLE_TOTEM = this.plugin.getConfig().getBoolean("Disable_Vanilla_System.Disable_Totem_Function");
		if (DISABLE_TOTEM == true) {
			event.setCancelled(true);
		}
	}
}