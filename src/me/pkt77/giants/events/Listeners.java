package me.pkt77.giants.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import me.pkt77.giants.Giants;
import me.pkt77.giants.file.Config;
import me.pkt77.giants.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Listeners implements Listener {
	private Giants _giants;

	public Listeners(Giants giants) {
		_giants = giants;
		_giants.getServer().getPluginManager().registerEvents(this, giants);
	}

	@EventHandler
	public void onGiantSpawn(SpawnEvent event) {
		if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Debug Mode") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Debug Mode").equalsIgnoreCase("true")) {
			String message = API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Language.Debug Message");
			if (message != null) {
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					if (_giants.getPermissions().hasDubugPerm(player)) {
						message = ChatColor.translateAlternateColorCodes('&', message);
						String x = String.valueOf(Math.round(event.getLocation().getX()));
						String y = String.valueOf(Math.round(event.getLocation().getY()));
						String z = String.valueOf(Math.round(event.getLocation().getZ()));
						player.sendMessage(message.replace("%X", x).replace("%Y", y).replace("%Z", z));
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void GiantSpawnEvent(CreatureSpawnEvent event) {
		Entity entity = event.getEntity();
		EntityType type = event.getEntityType();
		SpawnReason spawnReason = event.getSpawnReason();

		if (!API.getGiantSpawnWorlds().contains(entity.getWorld().getName())) {
			return;
		}

		if (spawnReason == SpawnReason.NATURAL) {
			if ((type == EntityType.ZOMBIE) || (type == EntityType.COW) || (type == EntityType.MUSHROOM_COW) || (type == EntityType.PIG_ZOMBIE) || (type == EntityType.ENDERMAN)) {
				String string = API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Spawn Settings.Spawn Chance");
				float sRate;
				try {
					sRate = Float.parseFloat(string);
				} catch (NumberFormatException e) {
					sRate = 0;
				}
				float chance = 100 - sRate;

				Random rand = new Random();
				double choice = rand.nextInt(100) < chance ? 1 : 0;
				if (choice == 0) {
					Location location = event.getEntity().getLocation();
					double x = location.getX();
					double y = location.getY();
					double z = location.getZ();

					int x2 = (int) x;
					int y2 = (int) y;
					int z2 = (int) z;

					int spawngiant = 1;
					double checkcount = 0.01;
					while (checkcount < 10) {
						y2 += checkcount;

						if (entity.getWorld().getBlockTypeIdAt(x2, y2, z2) != 0) {
							spawngiant = 0;
						}
						checkcount++;
					}
					if (spawngiant == 1) {
						SpawnEvent SE = new SpawnEvent(location);
						Bukkit.getServer().getPluginManager().callEvent(SE);
					}
				}
			}
		}
	}

	@EventHandler
	public void setGiantHealth(EntityRegainHealthEvent event) {
		Entity entity = event.getEntity();
		String string = API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Giant Stats.Health");
		int health;

		try {
			health = Integer.parseInt(string);
		} catch (Exception e) {
			health = 100;
		}

		if (API.isGiant(entity)) {
			event.setAmount(health);
		}
	}

	@EventHandler
	public void FireAttack(EntityTargetEvent event) {
		String ticks1 = API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Giant Stats.Fire Attack.Ticks for Target");
		String ticks2 = API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Giant Stats.Fire Attack.Ticks for Giant");
		Entity entity = event.getEntity();
		Entity target = event.getTarget();
		int ticksTarget;
		int ticksGiant;

		try {
			ticksTarget = Integer.parseInt(ticks1);
			ticksGiant = Integer.parseInt(ticks2);
		} catch (Exception e) {
			ticksTarget = 0;
			ticksGiant = 0;
		}

		if ((entity instanceof LivingEntity)) {
			if (API.isGiant(entity)) {
				if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Giant Stats.Fire Attack.Enabled") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Giant Stats.Fire Attack.Enabled").equalsIgnoreCase("true")) {
					try {
						event.getTarget().setFireTicks(ticksTarget);
						event.getEntity().setFireTicks(ticksGiant);
					} catch (Exception e) {
					}
				} else {
					event.setTarget(target);
				}
			}
		}
	}

	@EventHandler
	public void LightningAttack(EntityTargetEvent event) {
		Entity entity = event.getEntity();
		Entity target = event.getTarget();

		if ((entity instanceof LivingEntity)) {
			if (API.isGiant(entity)) {
				if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Giant Stats.Lightning Attack") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Giant Stats.Lightning Attack").equalsIgnoreCase("true")) {
					try {
						target.getLocation().getWorld().strikeLightning(target.getLocation());
					} catch (Exception e) {
					}
				} else {
					event.setTarget(target);
				}
			}
		}
	}

	/*
		@EventHandler
		public void ThrownBoulderAttack(EntityTargetEvent event) {
			Entity entity = event.getEntity();
			final Entity target = event.getTarget();

			if ((entity instanceof LivingEntity)) {
				if (API.isGiant(entity)) {
					_giants.getServer().getScheduler().scheduleSyncDelayedTask(_giants, new Runnable() {
						@Override
						public void run() {
							try {
								target.getLocation().getWorld().spawnEntity(target.getLocation(), EntityType.FIREBALL);
							} catch (Exception e) {
							}
						}
					}, 60L);
				}
			}
		}*/

	@EventHandler
	public void KickAttack(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Giant Stats.Kick Attack") != null && API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Giant Stats.Kick Attack").equalsIgnoreCase("true")) {
			Random pick = new Random();
			int chance = 0;
			for (int counter = 1; counter <= 1; counter++) {
				chance = 1 + pick.nextInt(100);
			}
			if (chance == 50) {
				for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
					if (API.isGiant(entity)) {
						if (entity.getNearbyEntities(5, 5, 5).contains(player)) {
							Vector kick = new Vector(0, 5, 0);
							player.setVelocity(kick);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void GiantDrops(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		String string = API.getFileHandler().getProperty(Config.CONFIG, "Giants Configuration.Giant Stats.Experience");
		int exp;

		try {
			exp = Integer.parseInt(string);
		} catch (Exception e) {
			exp = 5;
		}

		if (API.isGiant(entity)) {
			event.setDroppedExp(exp);
		}

		if (API.isGiant(entity)) {
			List<String> newDrop = API.getFileHandler().getPropertyList(Config.CONFIG, "Giants Configuration.Giant Stats.Drops");
			if (newDrop == null || newDrop.contains("") || newDrop.toString().equalsIgnoreCase("[]")) {
				return;
			}
			List<ItemStack> drops = new ArrayList<ItemStack>();
			for (String s : newDrop) {
				int id = 0;
				int amt = 0;
				short dmg = 0;
				try {
					String[] split = s.split(":");
					if (split.length == 2) {
						String idS = split[0];
						String amtS = split[1];
						id = Integer.parseInt(idS);
						if (amtS.contains("-")) {
							String[] newSplit = amtS.split("-");
							int range;
							int loc;
							Random rand = new Random();
							if (Double.valueOf(newSplit[0]) > Double.valueOf(newSplit[1])) {
								range = (int) ((Double.valueOf(newSplit[0]) * 100) - (Double.valueOf(newSplit[1]) * 100));
								loc = (int) (Double.valueOf(newSplit[1]) * 100);
							} else {
								range = (int) ((Double.valueOf(newSplit[1]) * 100) - (Double.valueOf(newSplit[0]) * 100));
								loc = (int) (Double.valueOf(newSplit[0]) * 100);
							}
							amt = ((int) (loc + rand.nextInt(range + 1))) / 100;
						} else {
							amt = Integer.parseInt(amtS);
						}
						dmg = 0;
					} else if (split.length == 3) {
						String idS = split[0];
						String dmgS = split[1];
						String amtS = split[2];
						id = Integer.parseInt(idS);
						if (amtS.contains("-")) {
							String[] newSplit = amtS.split("-");
							int range;
							int loc;
							Random rand = new Random();
							if (Double.valueOf(newSplit[0]) > Double.valueOf(newSplit[1])) {
								range = (int) ((Double.valueOf(newSplit[0]) * 100) - (Double.valueOf(newSplit[1]) * 100));
								loc = (int) (Double.valueOf(newSplit[1]) * 100);
							} else {
								range = (int) ((Double.valueOf(newSplit[1]) * 100) - (Double.valueOf(newSplit[0]) * 100));
								loc = (int) (Double.valueOf(newSplit[0]) * 100);
							}
							amt = ((int) (loc + rand.nextInt(range + 1))) / 100;
						} else {
							amt = Integer.parseInt(amtS);
						}
						dmg = Short.parseShort(dmgS);
					}
				} catch (Exception e) {
					id = 1;
					amt = 1;
					dmg = 0;
				}
				ItemStack newItem = new ItemStack(id, amt, dmg);
				drops.add(newItem);
			}
			event.getDrops().addAll(drops);
		}
	}
}