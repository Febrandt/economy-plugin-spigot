package me.ferjao.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.ferjao.main.Main;

public class Events implements Listener {

	private Main main;
	
	public Events(Main main){
		this.main = main;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		main.getDatabaseManager().createPlayerBalance(event.getPlayer().getUniqueId(),0);
	}
}
