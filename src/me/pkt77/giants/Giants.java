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
	 * fix item drops
	 * add language option
	 * add custom death messages (Maybe Customizable)
	 * add spout features (Custom skins/sounds)
	 * make a giant spawn egg with spout
	 * look into movement speed
	 * improve attacking mechanics
	 * Spawn in day OR night
	 * MORE TO COME (THINKING)
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