package me.pkt77.giants.file;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.World;
import org.bukkit.entity.Player;
import me.pkt77.giants.Giants;
import me.pkt77.giants.utils.API;

public class WorldHandler {
	private static Giants _giants;
	@SuppressWarnings("unused")
	private API api;

	public WorldHandler(Giants giants) {
		WorldHandler._giants = giants;
	}

	public static List<String> getServerWorlds() {
		List<String> worldList = new ArrayList<String>();
		for (World w : _giants.getServer().getWorlds()) {
			worldList.add(w.getName());
		}
		return worldList;
	}

	public boolean isEnabledWorld(Player player) {
		return API.getFileHandler().getPropertyList(Config.CONFIG, "Giants Configuration.Giant Stats.Spawn Worlds").contains(player.getWorld().getName());
	}
}