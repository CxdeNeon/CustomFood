package food.de.neon.main;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import food.de.neon.configs.RecipeConfig;
import food.de.neon.listener.FoodInteractlistener;
import food.de.neon.listener.command.ReloadCommand;
import food.de.neon.recipes.RecipeRegisterer;

public class Main extends JavaPlugin implements Listener {

	private static Main instance;
	
	private static RecipeConfig recipeConfig;
	
	@Override
	public void onEnable() {
		instance = this;
		
			registerObjects();
			registerEvents();
			registerCommands();
			registerRecipes();

	}


	private void registerObjects() {
		recipeConfig = new RecipeConfig();
		
	}
	
	private void registerEvents() {
		PluginManager manager = Bukkit.getPluginManager();
		
			manager.registerEvents(new FoodInteractlistener(), this);
			
	}
	
	private void registerCommands() {
		
		getCommand("food").setExecutor(new ReloadCommand());
		
	}
	
	private void registerRecipes() {
		ShapedRecipe[] recipes = getRecipeConfig().getRecipes();
			new RecipeRegisterer(recipes).register();
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public static RecipeConfig getRecipeConfig() {
		return recipeConfig;
	}
	
}

// ItemStack skull = p.getItemInHand();
// SkullMeta sm = (SkullMeta) skull.getItemMeta();
// if (sm.getOwner() != null) {
// UUID id = Bukkit.getPlayer("CxdeNeon").getUniqueId();
// if (Bukkit.getOfflinePlayer(sm.getOwner()) == Bukkit.getOfflinePlayer(id)) {
// p.sendMessage("Gleich");
// } else {
// p.sendMessage("Nicht gleich");
// p.sendMessage(sm.getOwner().toString());
// }
// }