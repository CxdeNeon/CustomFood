package food.de.neon.listener;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import food.de.neon.item.FoodItem;

public class FoodInteractlistener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onFoodInteract(PlayerInteractEvent event) {
		if (event.getItem() != null) {
			Player player = event.getPlayer();
			FoodItem item = new FoodItem(event.getItem());

			if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
				if (item.hasSaturation()) {
					event.setCancelled(true);

					if (player.getFoodLevel() < 20) {
						player.playSound(player.getLocation(), Sound.EAT, 10, 10);
						player.playEffect(player.getLocation(), Effect.TILE_BREAK, 10);

						item.setAmount(item.getAmount() - 1);
						player.setFoodLevel(player.getFoodLevel() + item.getSaturation());

						if (item.getAmount() == 0) {
							player.setItemInHand(new ItemStack(Material.AIR));
						} else {
							player.setItemInHand(item);
						}
					}
				}
			}
		}
	}
}
