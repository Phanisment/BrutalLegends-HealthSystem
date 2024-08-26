package io.phanisment.brutallegend;

import org.bukkit.EntityEffect;
import org.bukkit.Particle;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class HealthSystem extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("Plugin enabled!");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Plugin disabled!");
	}
	
	/*When respawn, Check if Player health is not more than variable max health
	if less than max health, decease player health by value in configuration.*/
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
	
	/*On Player Right, check if equipment slot is hand and item is totem, Check player health
	if player health is equal less than Configuration value add 1 health point to player.*/
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getHand() == EquipmentSlot.HAND && event.getItem().getType() == Material.TOTEM_OF_UNDYING) {
			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Player player = event.getPlayer();
				ItemStack item = event.getItem();
				AttributeInstance health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
				double baseHealth = health.getBaseValue();
				if (baseHealth < 20.0) {
					player.getInventory().removeItem(item);
					player.playEffect(EntityEffect.TOTEM_RESURRECT);
					health.setBaseValue(baseHealth + 2.0);
				} else {
					player.sendMessage("BRUTAL - Tidak bisa melebihi dari max health player");
				}
			}
		}
	}
}
