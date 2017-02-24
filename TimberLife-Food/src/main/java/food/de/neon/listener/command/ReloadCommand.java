package food.de.neon.listener.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import food.de.neon.main.Main;

public class ReloadCommand implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("food")) {
			
			switch(args.length) {
				case 1:
					if(args[0].equalsIgnoreCase("reload")) {
						if(sender.hasPermission("food.reload")){
							Main.getRecipeConfig().reloadConfig();
							sender.sendMessage("§aRecipeConfig wurde erfolgreich neu geladen!");
						}
					}else{
						return false;
					}
				break;
				
				default:
					return false;
			}
		}
		
		return true;
	}
	
	
	
	
}
