package io.phanisment.brutal.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class EvokerDeath implements Listener {
	private final Random random = new Random();
	@EventHandler
	public void onEvokerDeath(EntityDeathEvent event) {
		if (event.getEntityType() == EntityType.EVOKER) {
			event.getDrops().clear();
			double dropChance = 0.1;
			if (random.nextDouble() <= dropChance) {
				ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
				event.getDrops().add(totem);
			}
		}
	}
}