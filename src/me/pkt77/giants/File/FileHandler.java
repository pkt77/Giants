package me.pkt77.giants.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import me.pkt77.giants.Giants;
import me.pkt77.giants.utils.Message;

public class FileHandler {
	@SuppressWarnings("unused")
	private final Giants _giants;
	private final HashMap<Config, YamlConfiguration> _configurations;

	public FileHandler(Giants giants) {
		_giants = giants;
		_configurations = new HashMap<Config, YamlConfiguration>();
		loadDefaultDrop();
		this.loadConfig();
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
			Config.set("Giants Configuration.Giant Stats.Experience", new Integer(5));
			Config.set("Giants Configuration.Giant Stats.Health", new Integer(100));
			//Config.set("Giants Configuration.Giant Stats.Damage", new Integer(100));
			Config.set("Giants Configuration.Giant Stats.Fire Attack.Enabled", false);
			Config.set("Giants Configuration.Giant Stats.Fire Attack.Ticks for Target", new Integer(100));
			Config.set("Giants Configuration.Giant Stats.Fire Attack.Ticks for Giant", new Integer(100));
			Config.set("Giants Configuration.Giant Stats.Spawn Worlds", WorldHandler.getServerWorlds());
			Config.set("Giants Configuration.Giant Stats.Drops", Arrays.asList(this.loadDefaultDrop()));
			Config.set("Giants Configuration.Spawn Settings.Spawn Chance", new Integer(10));
			//Config.set("Giants Configuration.Spawn Settings.Time.Day", true);
			//Config.set("Giants Configuration.Spawn Settings.Time.Night", false);
			Config.set("Giants Configuration.Spawn Settings.Biomes.Forest", true);
			Config.set("Giants Configuration.Spawn Settings.Biomes.Desert", true);
			Config.set("Giants Configuration.Spawn Settings.Biomes.Extreme Hills", true);
			Config.set("Giants Configuration.Spawn Settings.Biomes.Jungle", true);
			Config.set("Giants Configuration.Spawn Settings.Biomes.Plains", true);
			Config.set("Giants Configuration.Spawn Settings.Biomes.Swamp", true);
			Config.set("Giants Configuration.Spawn Settings.Biomes.Taiga", true);
			Config.set("Giants Configuration.Debug Mode", true);
			Config.set("Giants Configuration.Language.Debug Message", "&2A Giant has spawned! X:&F%X &2Y:&F%Y &2Z:&F%Z");
			//Config.set("Giants Configuration.Language.Death Messages", Arrays.asList(this.loadDefaultDeathMessage()));
			try {
				Config.save(file);
			} catch (IOException e) {
			}
			_configurations.put(config, Config);
			break;
		}
	}

	public void saveConfig() {
		for (Config file : Config.values()) {
			if (_configurations.containsKey(file)) {
				try {
					_configurations.get(file).save(new File(file.getPath()));
				} catch (IOException e) {
				}
			}
		}
		Message.logInfo("Config saved.");
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

	public ItemStack getDropList(Config file, String path) {
		return null;
	}

	public boolean setProperty(Config file, String path, String value) {
		FileConfiguration conf = _configurations.get(file);

		if (conf != null) {
			conf.set(path, value);
			try {
				conf.save(new File(file.getPath()));
			} catch (IOException e) {

			}
			return true;
		}

		return false;
	}

	public boolean removeProperty(Config file, String path) {
		FileConfiguration conf = _configurations.get(file);

		if (conf != null) {
			conf.set(path, null);
			return true;
		}
		return false;
	}

	public boolean propertyExists(Config file, String path) {
		FileConfiguration conf = _configurations.get(file);

		if (conf != null) {
			if (conf.contains(path))
				return true;
		}
		return false;
	}

	public boolean setProperty(Config file, String path, boolean b) {
		FileConfiguration conf = _configurations.get(file);

		if (conf != null) {
			conf.set(path, b);
			try {
				conf.save(new File(file.getPath()));
			} catch (IOException e) {

			}
			return true;
		}
		return false;
	}

	public boolean setProperty(Config file, String path, Double double1) {
		FileConfiguration conf = _configurations.get(file);

		if (conf != null) {
			conf.set(path, double1);
			try {
				conf.save(new File(file.getPath()));
			} catch (IOException e) {

			}
			return true;
		}
		return false;
	}

	public boolean setPropertyList(Config file, String path, List<String> list) {
		FileConfiguration conf = _configurations.get(file);

		if (conf != null) {
			conf.set(path, list);
			try {
				conf.save(new File(file.getPath()));
			} catch (IOException e) {
			}
			return true;
		}
		return false;
	}

	public boolean setProperty(Config file, String path, Integer integer) {
		FileConfiguration conf = _configurations.get(file);

		if (conf != null) {
			conf.set(path, integer);
			try {
				conf.save(new File(file.getPath()));
			} catch (IOException e) {
			}
			return true;
		}
		return false;
	}
}