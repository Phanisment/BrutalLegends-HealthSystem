package io.phanisment.brutal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;

import io.phanisment.brutal.HealthSystem;

public class ReloadCommand implements CommandExecutor {
	private final HealthSystem plugin;
	
	public ReloadCommand(HealthSystem plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("hsreload")) {
			plugin.getLogger().info("Reloading plugin...");
			plugin.reloadConfig();
			sender.sendMessage("Plugin reloaded successfully.");
			return true;
		} else {
			return false;
		}
	}
}