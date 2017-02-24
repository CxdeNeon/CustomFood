package food.de.neon.item;

import org.bukkit.inventory.ItemStack;

public class FoodItem extends ItemStack {
	
	int saturation;
	
	public FoodItem(ItemStack item) {
		super(item);
		readMeta();
	}
	
	private void readMeta() {
		
		if(hasSaturation())
			for(String line : getItemMeta().getLore())
				if(line.startsWith("§8Saturation")) {
					line = line.substring(14, line.length());
						saturation = (int) (Double.valueOf(line) * (double) 2);
				}
		
	}
	
	public boolean hasSaturation() {
		
		
			if(hasLore())
				for(String line : getItemMeta().getLore()) 
					if(line.startsWith("§8Saturation"))
						return true;
					
				
			return false;
	}
	
	public int getSaturation() {
		return saturation;
	}
	
	private boolean hasLore() {
		if(hasItemMeta())
			if(getItemMeta().getLore() != null)
				return true;
		
		return false;
	}
}
