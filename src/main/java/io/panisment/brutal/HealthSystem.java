package io.phanisment.brutal;

import org.bukkit.plugin.java.JavaPlugin;

import io.phanisment.brutal.listener.PlayerRespawn;
import io.phanisment.brutal.listener.PlayerInteraction;
import io.phanisment.brutal.listener.EvokerDeath;

public class HealthSystem extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("Plugin enabled!");
		
		getServer().getPluginManager().registerEvents(new PlayerInteraction(), this);
		getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
		getServer().getPluginManager().registerEvents(new EvokerDeath(), this);
	}
}