package io.phanisment.brutal.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityResurrectEvent;

public class DisableTotem implements Listener{
	@EventHandler
	public void onResurrect(EntityResurrectEvent event) {
		Boolean Disable = this.plugin.getConfig().getBoolean("Disable_Vanilla_System.Disable_Totem_Function");
		if (Disable == true) {
			event.setCancelled(true);
		}
	}
}