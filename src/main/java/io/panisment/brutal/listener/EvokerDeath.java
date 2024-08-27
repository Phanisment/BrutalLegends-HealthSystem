package io.phanisment.brutal.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.entity.EntityDeathEvent;

import io.phanisment.brutal.HealthSystem;

import java.util.Random;

public class EvokerDeath implements Listener {
	private final Random random = new Random();
	private final HealthSystem plugin;
	
	public EvokerDeath(HealthSystem plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEvokerDeath(EntityDeathEvent event) {
		if (event.getEntityType() == EntityType.EVOKER) {
			event.getDrops().clear();
			Boolean isEnable = this.plugin.getBoolean("Custom_System.Evoker_Drops.Enable");
			if(isEnable) {
				double dropChance = this.plugin.getDouble("Custom_System.Evoker_Drops.Chance");
				if (dropChance == null) {
					double dropChance = 0.1;
				}
				if (random.nextDouble() <= dropChance) {
					ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
					event.getDrops().add(totem);
				}
			}
		}
	}
}