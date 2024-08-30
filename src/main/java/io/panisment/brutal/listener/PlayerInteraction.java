package io.phanisment.brutal.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.ChatColor;
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

import java.util.HashMap;
import java.util.UUID;


import io.phanisment.brutal.HealthSystem;

public class PlayerInteraction implements Listener {
	private final HealthSystem plugin;
	private final HashMap<UUID, Integer> usedAmount;
	
	public PlayerInteraction(HealthSystem plugin) {
		this.plugin = plugin;
		this.usedAmount = new HashMap<>();
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		double MAX_HEALTH = this.plugin.getConfig().getDouble("Custom_System.Totem_of_Undying.Max_Health");
		if (event.getHand() == EquipmentSlot.HAND && event.getItem().getType() == Material.TOTEM_OF_UNDYING && MAX_HEALTH > 1.0) {
			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Player player = event.getPlayer();
				ItemStack item = event.getItem();
				AttributeInstance health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
				double baseHealth = health.getBaseValue();
				String MAX_MESSAGE = this.plugin.getConfig().getString("System_Message.Max_Use_Totem");
				if (baseHealth < MAX_HEALTH) {
					UUID playerUUID = player.getUniqueId();
					int used = usedAmount.getOrDefault(playerUUID, 0);
					int maxUse = this.plugin.getConfig().getInt("Custom_System.Totem_of_Undying.Max_Usage_Per_Hours");
					
					if (maxUse > 0) {
						if (used < maxUse) {
							usedAmount.put(playerUUID, used + 1);
							player.getInventory().setItemInMainHand(null);
							player.playEffect(EntityEffect.TOTEM_RESURRECT);
							double addHealth = this.plugin.getConfig().getDouble("Custom_System.Totem_of_Undying.Add_Health");
							if (addHealth < 0.0) {
								addHealth = 2.0;
							}
							health.setBaseValue(baseHealth + addHealth);
						} else {
							String MAX_USED_MESSAGE = this.plugin.getConfig().getString("System_Message.Max_Usage_Totem");
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', MAX_USED_MESSAGE));
						}
					}
				} else if (MAX_MESSAGE != "") {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', MAX_MESSAGE));
				}
			}
		}
	}
}