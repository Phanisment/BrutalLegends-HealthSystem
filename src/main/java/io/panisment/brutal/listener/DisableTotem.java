package io.phanisment.brutal.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityResurrectEvent;

public class DisableTotem implements Listener{
	@EventHandler
	public void onResurrect(EntityResurrectEvent event) {
		event.setCancelled(true);
	}
}