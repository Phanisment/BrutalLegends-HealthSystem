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
		boolean ENABLE = this.plugin.getConfig().getBoolean("Disable_Vanilla_System.Disable_Evoker_Drop");
		if (event.getEntityType() == EntityType.EVOKER && ENABLE == true) {
			event.getDrops().clear();
			boolean CUSTOM_DROP = this.plugin.getConfig().getBoolean("Custom_System.Evoker_Drops.Enable");
			if (CUSTOM_DROP == true) {
				double dropChance = this.plugin.getConfig().getDouble("Custom_System.Evoker_Drops.Chance");
				if (random.nextDouble() <= dropChance) {
					ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
					event.getDrops().add(totem);
				}
			}
		}
	}
}