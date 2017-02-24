package food.de.neon.configs;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import food.de.neon.main.Main;

public class RecipeConfig extends Config {

	public RecipeConfig() {
		super(Main.getInstance().getDataFolder(), "RecipeConfig.yml");
		loadConfig();
	}
	
	public ShapedRecipe[] getRecipes(){
		ConfigurationSection section = config.getConfigurationSection("Recipes");
		
		if(section != null) {
			ShapedRecipe[] recipes = new ShapedRecipe[section.getKeys(false).size()];
		
				for(String number : section.getKeys(false)){
					String path = "Recipes" + "." + number;
						recipes[Integer.valueOf(number)] = getRecipe(path);
				}
				
			return recipes;
		}
				
				
		return null;
	}

	
	private ShapedRecipe getRecipe(String path) {
		path += ".";
		
		ItemStack[][] components = getRecepieComponents(getString(path + "input"));
		ItemStack output = getSkullItem(path + "output");		
		
		return createRecipe(components, output);
		
	}

	private ShapedRecipe createRecipe(ItemStack[][] components, ItemStack output) {

		ShapedRecipe recipe = new ShapedRecipe(output);
		recipe.shape("123", "456", "789");
		
		int key = 1;

		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++, key++) {
				
				ItemStack ingredient;				
				ingredient = components[row][column];
				
				if (ingredient != null) {
					recipe.setIngredient(Character.forDigit(key, 10), ingredient.getData());
				}
			}
		}
		return recipe;
	}
	
	private static ItemStack[][] getRecepieComponents(String recepie) {
		
		ItemStack[][] components = new ItemStack[3][3];
		recepie = recepie.substring(1, (recepie.length() - 1));
		recepie = recepie.replace(" ", "");

		String[] rows = recepie.split("&");
		for (int rIndex = 0; rIndex < rows.length; rIndex++) {
			String row = rows[rIndex];

			String[] columns = row.split(",");
			for (int cIndex = 0; cIndex < columns.length; cIndex++) {
				String column = columns[cIndex];

				if (!column.equals("null")) {
					String[] attributes = column.split("/");

					ItemStack item = new ItemStack(Material.valueOf(attributes[0]), 1, Short.valueOf(attributes[1]));
					components[rIndex][cIndex] = item;
				} else {
					components[rIndex][cIndex] = null;
				}
			}
		}

		return components;
	}
	
}
