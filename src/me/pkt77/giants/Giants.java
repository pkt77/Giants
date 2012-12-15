package me.pkt77.giants;

import java.util.logging.Logger;
import me.pkt77.giants.file.WorldHandler;
import me.pkt77.giants.utils.API;
import org.bukkit.plugin.java.JavaPlugin;

public class Giants extends JavaPlugin {
	private Logger log;
	private final Permissions permissions = new Permissions(this);
	private final WorldHandler worldHandler = new WorldHandler(this);

	/*TODO:
	 * add custom death messages (Maybe Customizable)
	 * add spout features (Custom skins/sounds)
	 * make a giant spawn egg with spout
	 * look into movement speed
	 * improve attacking mechanics
	 * Spawn in day OR night
	 * MORE TO COME (THINKING)
	 */

	/*CHANGE LOG:
	 * V1.1 :
	 * Giants no longer spawn from spawn eggs
	 * changed spawn chance to a float so it can store decimals
	 * Removed Damage modifier - it causes a player to kill a Giant instantly
	 * V1.2 :
	 * Fixed spelling error for Experience in config
	 * V2.0 :
	 * Biome Spawn settings
	 */

	@Override
	public void onEnable() {
		new API(this);
	}

	public Logger getLog() {
		return log;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public WorldHandler getWorldHandler() {
		return worldHandler;
	}
}