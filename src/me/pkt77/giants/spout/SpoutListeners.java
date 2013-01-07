package me.pkt77.giants.spout;

import me.pkt77.giants.Giants;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomItem;

public class SpoutListeners implements Listener {
	private Giants _giants;

	public SpoutListeners(Giants giants) {
		_giants = giants;
		_giants.getServer().getPluginManager().registerEvents(this, giants);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
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
	/*
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		Player player = (Player) event.getEntity();
		
		if (API.isGiant(entity)) {
			SpoutManager.getSoundManager().playCustomSoundEffect(_giants, SpoutManager.getPlayer(player), "https://sites.google.com/site/pkt77projects/config/pagetemplates/files/npc_giant_footdistant_02.wav?attredirects=0&d=1", true);
		}
	}*/
}