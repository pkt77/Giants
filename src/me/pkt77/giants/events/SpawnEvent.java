package me.pkt77.giants.events;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import me.pkt77.giants.Giants;
import me.pkt77.giants.utils.API;

public class SpawnEvent extends Event {
	@SuppressWarnings("unused")
	private Giants giants;
	private boolean cancelled = false;
	private Entity entity;
	private Location location;
	private static final HandlerList handlers = new HandlerList();

	public SpawnEvent(Location loc) {
		location = loc;

		if (!API.getGiantSpawnWorlds().contains(loc.getWorld().getName())) {
			setCancelled(true);
		}

		if (!isCancelled()) {
			entity = loc.getWorld().spawnEntity(location, EntityType.GIANT);
		}
	}

	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public Entity getEntity() {
		return entity;
	}

	public Location getLocation() {
		return location;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}