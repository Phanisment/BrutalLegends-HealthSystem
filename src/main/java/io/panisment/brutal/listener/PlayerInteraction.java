package io.phanisment.brutal.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.EntityEffect;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import io.phanisment.brutal.HealthSystem;

public class PlayerInteraction implements Listener {
	private final HealthSystem plugin;
	
	public PlayerInteraction(HealthSystem plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Double MAX_HEALTH = this.plugin.getConfig().getBoolean("Custom_System.Totem_of_Undying.Max_Health");
		if (event.getHand() == EquipmentSlot.HAND && event.getItem().getType() == Material.TOTEM_OF_UNDYING && MAX_HEALTH != null) {
			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Player player = event.getPlayer();
				ItemStack item = event.getItem();
				AttributeInstance health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
				double baseHealth = health.getBaseValue();
				if (baseHealth < MAX_HEALTH) {
					player.getInventory().removeItem(item);
					player.playEffect(EntityEffect.TOTEM_RESURRECT);
					health.setBaseValue(baseHealth + 2.0);
				} 
			}
		}
	}
}