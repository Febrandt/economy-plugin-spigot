package me.ferjao.main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class DatabaseManager {

	private Main main;
	
	public DatabaseManager(Main main){
		this.main = main;
	}
	

	public boolean playerExists(UUID uuid) 
	{
		try {
			PreparedStatement stm = main.getMySQL().getConnection().prepareStatement(
					"SELECT * FROM balances WHERE uuid = ?");
			stm.setString(1, uuid.toString());
			
			ResultSet results = stm.executeQuery();
			
			if(results.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			
			return false;
		}
		return false;
	}
	
	public void createPlayerBalance(UUID uuid,int amount) {
		
		if(playerExists(uuid)) return;
		
		try {
			PreparedStatement stm = main.getMySQL().getConnection().prepareStatement(
					"INSERT INTO balances (uuid,amount) VALUE(?,?)");
			stm.setString(1, uuid.toString());
			stm.setInt(2,amount);
			stm.executeUpdate();
			
		} catch (SQLException e) {
			
		}
	}
	
	public void setPlayerBalance(UUID uuid,int amount) {
		
		if (!playerExists(uuid)) return;
		
		try {
			PreparedStatement stm = main.getMySQL().getConnection().prepareStatement(
					"UPDATE balances SET amount = ? WHERE uuid = ?");
			stm.setInt(1,amount);
			stm.setString(2, uuid.toString());
			stm.executeUpdate();
		} catch (SQLException e) {
		}
	}
	
	public int getPlayerBalance(UUID uuid) {
		
		if(!playerExists(uuid)) return 0;
		
		try {
			PreparedStatement stm = main.getMySQL().getConnection().prepareStatement(
					"SELECT * FROM balances WHERE uuid = ?");
			stm.setString(1,uuid.toString());
			ResultSet result = stm.executeQuery();
			if(result.next()) {
				return result.getInt("amount");
			}
		} catch (SQLException e) {
			return 0;
		}
		
		return 0;
	}
	
	public void addBalance(UUID uuid,int amount) {
		this.setPlayerBalance(uuid,this.getPlayerBalance(uuid) + amount);
	}
	
	public void removeBalance(UUID uuid, int amount) {
		this.setPlayerBalance(uuid, this.getPlayerBalance(uuid) - amount);
	}
}
