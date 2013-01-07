package me.pkt77.giants.events;

import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import me.pkt77.giants.Giants;
import me.pkt77.giants.file.Config;
import me.pkt77.giants.utils.API;

public class SpawnEvent extends Event {
	@SuppressWarnings("unused")
	private Giants giants;
	private static boolean cancelled = false;
	private Entity entity;
	private Location location;
	private static final HandlerList handlers = new HandlerList();

	public SpawnEvent(Location loc) {
		location = loc;
		Biome biome = loc.getWorld().getBiome(loc.getBlockX(), loc.getBlockZ());

		if (!API.getGiantSpawnWorlds().contains(loc.getWorld().getName())) {
			setCancelled(true);
		}

		if (!isCancelled()) {
			if (biome == Biome.FOREST) {
				if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Forest") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Forest").equalsIgnoreCase("true")) {
					entity = loc.getWorld().spawnEntity(location, EntityType.GIANT);
				}
			}
			if (biome == Biome.DESERT) {
				if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Desert") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Desert").equalsIgnoreCase("true")) {
					entity = loc.getWorld().spawnEntity(location, EntityType.GIANT);
				}
			}
			if (biome == Biome.PLAINS) {
				if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Plains") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Plains").equalsIgnoreCase("true")) {
					entity = loc.getWorld().spawnEntity(location, EntityType.GIANT);
				}
			}
			if (biome == Biome.SWAMPLAND) {
				if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Swampland") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Swampland").equalsIgnoreCase("true")) {
					entity = loc.getWorld().spawnEntity(location, EntityType.GIANT);
				}
			}
			if (biome == Biome.JUNGLE) {
				if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Jungle") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Jungle").equalsIgnoreCase("true")) {
					entity = loc.getWorld().spawnEntity(location, EntityType.GIANT);
				}
			}
			if (biome == Biome.ICE_PLAINS) {
				if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Ice Plains") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Ice Plains").equalsIgnoreCase("true")) {
					entity = loc.getWorld().spawnEntity(location, EntityType.GIANT);
				}
			}
			if (biome == Biome.TAIGA) {
				if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Taiga") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Taiga").equalsIgnoreCase("true")) {
					entity = loc.getWorld().spawnEntity(location, EntityType.GIANT);
				}
			}
			if (biome == Biome.EXTREME_HILLS) {
				if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Extreme Hills") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Extreme Hills").equalsIgnoreCase("true")) {
					entity = loc.getWorld().spawnEntity(location, EntityType.GIANT);
				}
			}
			if (biome == Biome.ICE_MOUNTAINS) {
				if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Ice Mountains") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Ice Mountains").equalsIgnoreCase("true")) {
					entity = loc.getWorld().spawnEntity(location, EntityType.GIANT);
				}
			}
			if (biome == Biome.MUSHROOM_ISLAND) {
				if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Mushroom Island") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Mushroom Island").equalsIgnoreCase("true")) {
					entity = loc.getWorld().spawnEntity(location, EntityType.GIANT);
				}
			}
			if ((biome == Biome.BEACH) || (biome == Biome.FROZEN_OCEAN) || (biome == Biome.FROZEN_RIVER) || (biome == Biome.MUSHROOM_SHORE) || (biome == Biome.OCEAN) || (biome == Biome.RIVER)) {
				if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Other") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Biomes.Other").equalsIgnoreCase("true")) {

				}
			}
			if (biome == Biome.HELL) {
				entity = loc.getWorld().spawnEntity(location, EntityType.GIANT);
			}
			if (biome == Biome.SKY) {
				entity = loc.getWorld().spawnEntity(location, EntityType.GIANT);
			}
		}
	}

	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}

	public static boolean isCancelled() {
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