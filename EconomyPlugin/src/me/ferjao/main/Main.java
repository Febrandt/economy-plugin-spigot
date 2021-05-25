package me.ferjao.main;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.ferjao.commands.CommandMoney;
import me.ferjao.events.Events;
import me.ferjao.sql.MySQL;


public class Main extends JavaPlugin {

	
	public MySQL mysql;
	public DatabaseManager databaseManager;
	
	@Override
	public void onEnable() 
	{
		this.mysql = new MySQL();
		this.databaseManager = new DatabaseManager(this);
	
		getServer().getPluginManager().registerEvents(new Events(this), this);
		getCommand("money").setExecutor(new CommandMoney(this));
		
		try {
			mysql.connect();
		} catch (ClassNotFoundException | SQLException e) {
			Bukkit.getLogger().info("Database not connected!");
		}
		
		if (mysql.isConnected()) {
			Bukkit.getLogger().info("Database connected succesfully!");
		}
		
	}
	
	@Override
	public void onDisable() 
	{
		
	}

	
}
