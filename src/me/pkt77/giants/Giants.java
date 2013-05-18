package me.pkt77.giants;

import java.util.logging.Logger;
import me.pkt77.giants.spout.GiantEgg;
import me.pkt77.giants.spout.SpoutListener;
import me.pkt77.giants.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Giants extends JavaPlugin {
	public final Logger log = Logger.getLogger("Minecraft");

	/*
	 * CHANGE LOG:
	 * V1.1 :
	 * Giants no longer spawn from spawn eggs
	 * changed spawn chance to a float so it can store decimals
	 * Removed Damage modifier - it causes a player to kill a Giant instantly
	 * V1.2 :
	 * Fixed spelling error for Experience in config
	 * V2.0 :
	 * Biome Spawn settings
	 * V2.1 :
	 * Update to CB 1.4.6-R0.1
	 * Fixed Giants not targetting players if Fire Attack is set to False
	 * V3.0 :
	 * Added more Biome Spawn options
	 * Added Giant Spawn Egg
	 * V3.1 :
	 * Added 'Other' Biome option incase other plugins modify where mobs can spawn
	 * V3.2 :
	 * Temporarily Disabled Spout Features due to servers getting errors if they did not have the SpoutPlugin
	 * V3.3 :
	 * Added a check to see if the server has the SpoutPlugin before enabling Spout features
	 * V3.4 :
	 * Fixed errors when a player interacts while the server does not have the SpoutPlugin
	 * V4.0 :
	 * Update to craftbukkit-1.4.6-R0.3 and SpoutPlugin 1412
	 * Added Kick Attack
	 * Added Lightning Attack
	 * \giants reload can now be used in console
	 * V5.0 :
	 * Updated to Craftbukkit-1.4.7-R0.1 and SpoutPlugin 1.4.7-R0.2
	 * Added "Throw Boulder Attack"
	 * Added "Stomp Attack"
	 * Added "Spawn Zombies Attack"
	 * Added Sounds to some of the Attack Mechanisms (the ones that don't have sounds)
	 * Added Config to set the height the player is kicked (for the Kick Attack)
	 * Cleaned code
	 * Organized the config.yml
	 * Added option to use Spout features
	 */

	@Override
	public void onEnable() {
		new API(this);
		if (Bukkit.getPluginManager().getPlugin("Spout") != null) {
			log.info("[Giants] Found SpoutPlugin: Enabling Spout features.");
			new GiantEgg(this);
			new SpoutListener(this);
		} else {
			log.info("[Giants] Cannot Find SpoutPlugin: Disabling Spout features.");
		}
		log.info("[Giants] --- Enabled ---");
	}
	
	@Override
	public void onDisable(){
		log.info("[Giants] --- Disabled ---");
	}
}
