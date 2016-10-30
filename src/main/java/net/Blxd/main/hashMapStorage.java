package net.Blxd.main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class hashMapStorage implements Listener{
	
	@SuppressWarnings("unused")
	private Main plugin;
	public hashMapStorage(Main listener) {
		this.plugin = listener;		
	}
	
	public static HashMap<Player, String> Team = new HashMap<Player, String>();
	
	 @EventHandler
	  public void onJoin(PlayerJoinEvent e)
	  {
	    Player p = e.getPlayer();
	    p.setFoodLevel(40);
	    p.setHealth(40.0);
	    p.setPlayerListName(Main.color("&e" + p.getName()));
	  }
	  
	  @EventHandler
		public void noHunger(FoodLevelChangeEvent e){
			e.setCancelled(true);
		}
	  
	  @EventHandler
	  public void weatherChange(WeatherChangeEvent event)
	  {
	    if (event.toWeatherState()) {
	        World world = event.getWorld();
	          event.setCancelled(true);
	          world.setStorm(false);
	          world.setThundering(false);
	          world.setWeatherDuration(0);
	    }
	  }
	  
	  
	  @EventHandler
	  public void onBreak(BlockBreakEvent e){
		  if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
			  e.setCancelled(true);
		  }
	  }
	  
	  @EventHandler
	  public void onBreak(BlockPlaceEvent e){
		  if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
			  e.setCancelled(true);
		  }
	  }
	  
	  @EventHandler
		public void onHit(EntityDamageByEntityEvent e){
			e.setCancelled(true);
			if(e.getEntity() instanceof Player){
				if(e.getDamager() instanceof Player){
					Player player = (Player) e.getEntity();
					Player damager = (Player) e.getDamager();
					if(hashMapStorage.Team.get(damager).equals("red") && hashMapStorage.Team.get(player).equals("blue")){
						hashMapStorage.Team.put(player, "red");
						player.setPlayerListName(Main.color("&c&lRED " + "&e" + player.getName()));
						Bukkit.broadcastMessage(Main.color("&8&l[&e&lHideNSeek&8&l] &b" + player.getName() + " &ehas been tagged by &c" + damager.getName()));
					}
				}
			}
		}
		
		@EventHandler
		public void onHit(EntityDamageEvent e){
			e.setCancelled(true);
		}
		
		
		
		
		
	
}
