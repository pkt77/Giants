package me.pkt77.giants.events;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DeathEvent extends Event {
	private Giant entity;
	private Location location;
	private static final HandlerList handlers = new HandlerList();
	private EntityType entityType;

	public Giant getEntity() {
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

	public EntityType getEntityType() {
		return entityType;
	}
}