package me.ferjao.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ferjao.main.Main;

public class CommandMoney implements CommandExecutor{

	private Main main;
	
	public CommandMoney (Main main){
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) 
		{
			Player player = (Player) sender;
			
			if (args.length < 0){
				
				player.sendMessage("§2Seu saldo: §f$"+
						main.getDatabaseManager().getPlayerBalance(player.getUniqueId()));
				return true;
			}
			
			
			if(args[0].equalsIgnoreCase("pay")) 
			{
				
				if (args.length == 1 || args.length == 2) {
						
					player.sendMessage("§cUse /money pay <jogador> <quantia>");
					
						
				}else if(args.length == 3) {
					
					try {
							
						Player playerArg1 = main.getServer().getPlayer(args[1]);
						int amount = Integer.parseInt(args[2]);
							
						if (!main.getDatabaseManager().playerExists(playerArg1.getUniqueId())) return true;
						
								
						if (amount < 0) {
							player.sendMessage("§cApenas valores maiores que zero");
							return true;
						}
						
						if (amount > main.getDatabaseManager().getPlayerBalance(player.getUniqueId())) {
							player.sendMessage("§cVocê não tem saldo suficiente para depositar");
							return true;
						}
							
						main.getDatabaseManager().removeBalance(player.getUniqueId(),amount);
										
						main.getDatabaseManager().addBalance(playerArg1.getUniqueId(),amount);
										
						player.sendMessage("§2Você enviou §f$" + args[2] + " §2para §f" + args[1]);
										
						playerArg1.sendMessage("§2Você recebeu §f$" + args[2] +" §2de §f"+player.getName());
											
						}catch (Exception e) {
						
						player.sendMessage("§cO player §4" + args[1] + " §cnão existe!");
						}
					}					
				}else {
					
					try {
						
						Player playerArg0 = main.getServer().getPlayer(args[0]);
						
						player.sendMessage(
								"§2O dinheiro do player §f"+ args[0] + " §2é §f" + 
								Integer.toString(main.getDatabaseManager().getPlayerBalance(playerArg0.getUniqueId())));
						
					}catch(Exception e) {
						
						player.sendMessage("§cO player §4" + args[0] + " §cnão existe!");
					}
				}
		}
		return true;
	}
}
