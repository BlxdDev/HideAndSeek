package net.Blxd.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	public void onEnable(){
		Listeners();
		registerCommands();
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        console.sendMessage(color("&c&lHideNSeek enabled"));
        for(Entity entity : Bukkit.getWorld("world").getEntities()){
        	if(!(entity instanceof Player)){
        		entity.remove();
        	}
        }
        
	}
	
	public void onDisable(){
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		console.sendMessage(color("&c&lHideNSeek disabled"));
	}
	
	public void Listeners(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new hashMapStorage(this), this);
		pm.registerEvents(new CommandHandler(this), this);
	}
	
	public void registerCommands(){
		getCommand("team").setExecutor(new CommandHandler(this));
		getCommand("reset").setExecutor(new CommandHandler(this));
	}
	
	public static String color(String string){
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
}
