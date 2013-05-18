package me.pkt77.giants.utils;

import java.util.List;
import me.pkt77.giants.Commands;
import me.pkt77.giants.Giants;
import me.pkt77.giants.events.Listeners;
import me.pkt77.giants.file.Config;
import me.pkt77.giants.file.FileHandler;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;

public class API {
	private static Giants giants;
	private Commands commands;
	private static FileHandler fileHandler;

	@Deprecated
	public API(Giants giants) {
	}
	
	public void setup(Giants giants){
		this.giants = giants;
		new Listeners(_giants);
		commands = new Commands(_giants);
		this.giants.getCommand("giants").setExecutor(commands);
		fileHandler = new FileHandler(_giants);
	}

	public boolean isGiant(Entity entity) {
		return entity instanceof Giant;
	}

	public boolean isGiant(LivingEntity livingEntity) {
		return livingEntity instanceof Giant;
	}

	public List<String> getGiantSpawnWorlds() {
		return getFileHandler().getPropertyList(Config.CONFIG, "Giants Configuration.Spawn Settings.Worlds");
	}

	public FileHandler getFileHandler() {
		return fileHandler;
	}
}
