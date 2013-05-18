package me.pkt77.giants.spout;

import me.pkt77.giants.Giants;
import me.pkt77.giants.file.Config;
import me.pkt77.giants.utils.API;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomItem;

public class SpoutListener implements Listener {
	private Giants giants;

	public SpoutListener(Giants giants) {
		this.giants = giants;
		this.giants.getServer().getPluginManager().registerEvents(this, giants);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Optional Dependencies.Spout").equalsIgnoreCase("true")) {
			Player player = event.getPlayer();
			Location location = player.getLocation();
			SpoutItemStack itemInHand = new SpoutItemStack(player.getItemInHand());

			if (itemInHand.isCustomItem()) {
				CustomItem ci = (CustomItem) itemInHand.getMaterial();
				if (ci instanceof GiantEgg) {
					try {
						event.getClickedBlock().getWorld().spawnEntity(location, EntityType.GIANT);
					} catch (Exception e) {
					}
				}
			}
		}
	}
}
