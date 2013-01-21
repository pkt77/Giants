package me.pkt77.giants.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import me.pkt77.giants.Giants;

public class FileHandler {
	private final Giants _giants;
	private final HashMap<Config, YamlConfiguration> _configurations;

	public FileHandler(Giants giants) {
		_giants = giants;
		_configurations = new HashMap<Config, YamlConfiguration>();
		loadDefaultDrop();
		loadWorlds();
		loadConfig();
	}

	private List<String> loadWorlds() {
		List<String> worldList = new ArrayList<String>();
		for (World w : _giants.getServer().getWorlds()) {
			worldList.add(w.getName());
		}
		return worldList;
	}

	private String[] loadDefaultDrop() {
		String[] drops = { "1:0:1" };
		return drops;
	}

	@SuppressWarnings("unused")
	private String[] loadDefaultDeathMessage() {
		String[] message = { "Player got stomped by a Giant" };
		return message;
	}

	public void loadConfig() {
		for (Config file : Config.values()) {
			File confFile = new File(file.getPath());

			if (confFile.exists()) {
				if (_configurations.containsKey(file)) {
					_configurations.remove(file);
				}
				YamlConfiguration conf = YamlConfiguration.loadConfiguration(confFile);
				_configurations.put(file, conf);
			} else {
				File parentFile = confFile.getParentFile();

				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				this.createConfig(file, confFile);
			}
		}
	}

	private void createConfig(Config config, File file) {
		switch (config) {
			case CONFIG:
				YamlConfiguration Config = YamlConfiguration.loadConfiguration(file);
				Config.set("Giants Configuration.Giant Stats.Health", new Integer(100));
				Config.set("Giants Configuration.Giant Stats.Experience", new Integer(5));
				Config.set("Giants Configuration.Giant Stats.Drops", Arrays.asList(loadDefaultDrop()));
				Config.set("Giants Configuration.Spawn Settings.Chance", new Integer(10));
				Config.set("Giants Configuration.Spawn Settings.Worlds", loadWorlds());
				Config.set("Giants Configuration.Spawn Settings.Biomes.Forest", false);
				Config.set("Giants Configuration.Spawn Settings.Biomes.Desert", true);
				Config.set("Giants Configuration.Spawn Settings.Biomes.Plains", true);
				Config.set("Giants Configuration.Spawn Settings.Biomes.Swampland", true);
				Config.set("Giants Configuration.Spawn Settings.Biomes.Jungle", false);
				Config.set("Giants Configuration.Spawn Settings.Biomes.Ice Plains", false);
				Config.set("Giants Configuration.Spawn Settings.Biomes.Taiga", false);
				Config.set("Giants Configuration.Spawn Settings.Biomes.Extreme Hills", true);
				Config.set("Giants Configuration.Spawn Settings.Biomes.Ice Mountains", false);
				Config.set("Giants Configuration.Spawn Settings.Biomes.Mushroom Island", true);
				Config.set("Giants Configuration.Spawn Settings.Biomes.Other", true);
				Config.set("Giants Configuration.Attack Mechanisms.Lightning Attack", false);
				Config.set("Giants Configuration.Attack Mechanisms.Throw Boulder Attack", false);
				Config.set("Giants Configuration.Attack Mechanisms.Stomp Attack", false);
				Config.set("Giants Configuration.Attack Mechanisms.Kick Attack.Enabled", false);
				Config.set("Giants Configuration.Attack Mechanisms.Kick Attack.Kick Height", new Integer(1));
				Config.set("Giants Configuration.Attack Mechanisms.Fire Attack.Enabled", false);
				Config.set("Giants Configuration.Attack Mechanisms.Fire Attack.Ticks for Target", new Integer(100));
				Config.set("Giants Configuration.Attack Mechanisms.Fire Attack.Ticks for Giant", new Integer(100));
				Config.set("Giants Configuration.Attack Mechanisms.Spawn Zombies Attack.Enabled", false);
				Config.set("Giants Configuration.Attack Mechanisms.Spawn Zombies Attack.Zombies to Spawn", new Integer(5));
				Config.set("Giants Configuration.Sounds.Fire Attack", true);
				Config.set("Giants Configuration.Sounds.Throw Boulder Attack", true);
				Config.set("Giants Configuration.Sounds.Kick Attack", true);
				Config.set("Giants Configuration.Sounds.Death", true);
				Config.set("Giants Configuration.Optional Dependencies.Spout", true);
				Config.set("Giants Configuration.Debug Mode", false);
				Config.set("Giants Configuration.Language.Debug Message", "&2A Giant has spawned at X:&F%X &2Y:&F%Y &2Z:&F%Z");
				try {
					Config.save(file);
				} catch (IOException e) {
				}
				_configurations.put(config, Config);
				break;
		}
	}

	public String getProperty(Config file, String path) {
		FileConfiguration conf = _configurations.get(file);

		if (conf != null) {
			String prop = conf.getString(path, "NULL");

			if (!prop.equalsIgnoreCase("NULL"))
				return prop;
			conf.set(path, null);
		}
		return null;
	}

	public List<String> getPropertyList(Config file, String path) {
		FileConfiguration conf = _configurations.get(file);

		if (conf != null) {
			List<String> prop = conf.getStringList(path);
			if (!prop.contains("NULL"))
				return prop;
			conf.set(path, null);
		}
		return null;
	}
}