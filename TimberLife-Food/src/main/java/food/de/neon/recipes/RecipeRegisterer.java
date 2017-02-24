package food.de.neon.recipes;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapedRecipe;

public class RecipeRegisterer {
	
	private ShapedRecipe[] recipes;

	public RecipeRegisterer(ShapedRecipe[] recipes) {
		this.recipes = recipes;
	}
	
	public ShapedRecipe[] getRecipes(){
		return recipes;
	}
	
	public void setRecipes(ShapedRecipe[] recipes) {
		this.recipes = recipes;
	}
	
	public void register(){
		if(recipes != null) 
		{
			for(ShapedRecipe recipe : recipes){
				Bukkit.getServer().addRecipe(recipe);
			}
		}
	}
	
	
	
	
	
	
}
