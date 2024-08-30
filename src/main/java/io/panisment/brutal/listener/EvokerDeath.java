package io.phanisment.brutal.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

import io.phanisment.brutal.HealthSystem;

public class EvokerDeath implements Listener {
	private final Random random = new Random();
	private final HealthSystem plugin;
	
	public EvokerDeath(HealthSystem plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEvokerDeath(EntityDeathEvent event) {
		Boolean ENABLE = this.plugin.getConfig().getDouble("Disable_Vanilla_System.Disable_Evoker_Drop");
		if (event.getEntityType() == EntityType.EVOKER && ENABLE_EVOKER = true) {
			event.getDrops().clear();
			double dropChance = 0.1;
			if (random.nextDouble() <= dropChance) {
				ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
				event.getDrops().add(totem);
			}
		}
	}
}