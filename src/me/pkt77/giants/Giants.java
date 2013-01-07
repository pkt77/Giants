package me.pkt77.giants;

import me.pkt77.giants.file.WorldHandler;
import me.pkt77.giants.spout.GiantEgg;
import me.pkt77.giants.spout.SpoutListeners;
import me.pkt77.giants.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Giants extends JavaPlugin {
	private final Permissions permissions = new Permissions(this);
	private final WorldHandler worldHandler = new WorldHandler(this);
	public GiantEgg giantEgg;

	/*CHANGE LOG:
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
	 * \giants reload  can now be used in console
	 */

	@Override
	public void onEnable() {
		new API(this);
		if (Bukkit.getPluginManager().getPlugin("Spout") != null) {
			System.out.println("[Giants] Found SpoutPlugin: Enabling Spout features.");
			giantEgg = new GiantEgg(this);
			new SpoutListeners(this);
		} else {
			System.out.println("[Giants] Cannot Find SpoutPlugin: Disabling Spout features.");
		}
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public WorldHandler getWorldHandler() {
		return worldHandler;
	}
}