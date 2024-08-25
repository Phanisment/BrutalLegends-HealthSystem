package io.phanisment.test;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HealthSystem extends JavaPlugin implements Listener {
	private final Random random = new Random();
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		loadConfigValues();
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("Plugin enabled!");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Plugin disabled!");
	}
	
	@Override
	public void loadConfigValues() {
		FileConfiguration config = getConfig();
		MaxHealth = config.getDouble("Main.Size-Max-Health", 20.0);
		Decrease = config.getDouble("Main.Decrease-Max-Health", 2.0);
		
		// Totem
		Totem = config.getBoolean("Main.Totem.Enable-Totem-Health", true);
		EnableTotem = config.getBoolean("Main.Totem.Enable-Totem-Rescued", false);
		
		// Evoker
		EnableEvoker = config.getBoolean("Main.Enable-Evoker-Drop-Totem", false);
		TotemChance = config.getBoolean("Main.Evoker.Custom-Drops.Chance", 0.1);
		// Item Modify
		ItemDisplayName = config.getString("Main.Evoker.Custom-Drops.Drop.DisplayName", "&eTotem of Undying");
		ItemModel = config.getInt("Main.Evoker.Custom-Drops.Drop.Model", 1);
		List<String> ItemLore = config.getListString("Main.Evoker.Custom-Drops.Drop.Lore");
		
		// Mesaage
		MaxMessage = config.getString("Message.Health-is-Max", "[BL] You cant add more than default max health of player.");
		
		// Custom Item
		ItemTotem = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
		ItemMeta meta = Item_Totem.getMeta();
		if (meta != null) {
			meta.setDisplayName(ChatColor.translateAlternateColorCodes("&", ItemDisplayName));
			meta.setCustomModelData(ItemModel);
			meta.setLore(ChatColor.translateAlternateColorCodes("&", ItemLore));
			ItemTotem.setItemMeta(meta);
		}
	}
	
	/*When respawn, Check if Player health is not more than variable max health
	if less than max health, decease player health by value in configuration.*/
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		double health = player.getHealthScale();
		if (currentHealth > 1) {
			player.setHealthScale(health - 2);
		} else {
			player.setHealthScale(1.0);
		}
	}
	
	/*On Player Right, check if equipment slot is hand and item is totem, Check player health
	if player health is equal less than Configuration value add 1 health point to player.*/
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (Totem == true) {
			if (event.getHand() == EquipmentSlot.HAND && event.getItem().getType() == Material.TOTEM_OF_UNDYING &&
					event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Player player = event.getPlayer();
				ItemStack item = event.getItem();
				double health = player.getHealthScale();
				if (health <= MaxHealth) {
					player.getInventory().removeItem(item);
					player.setHealthScale(health + 2);
				} else {
					player.sendRawMessage(ChatColor.translateAlternateColorCodes("&", MaxMessage));
				}
			}
		}
	}
	/*on entity death, if Entity is Evoker, cancel Evoker drops then make custom drops
	with random chance in configuration*/
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity() instanceof Evoker && EnableEvoker == false) {
			event.getDrops().clear();
			if (random.nexDouble() < TotemChance) {
				ItemStack item = ItemTotem.clone();
				item.setAmount(1);
				event.getDrops().add(item);
			}
		}
	}
  }
