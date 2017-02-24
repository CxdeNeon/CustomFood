package food.de.neon.configs;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import food.de.neon.main.Main;

public class Config {

	   
    File file;
    protected FileConfiguration config;
   
    protected Config(File parentFile, String configName) {
        file = new File(parentFile, configName);
    }
   
    /*
     * Loads and, if the YML File not exists calls createConfig(), the YML
     * configuration File. Declares the YML configuration variable 'config'
     */
    protected FileConfiguration loadConfig() {
       
        /*
         * Creates a new FileConfiguration Object 'config' with the createConfig() method.
         * 'Config' will be our configuration Object to work with.
         */
        config = createConfig();
   
        // Sets some options of the FileConfiguration 'config'.
        config.options().copyHeader(true);
        config.options().copyDefaults(true);
       
        saveConfig();
       
        return config;
    }
   
   
    // Saves the configuration of the current 'config' variable.
    public void saveConfig() {
       
        /*
         * Tries to save the configuration in the given file 'configFile'.
         */
        try {
            config.save(file);
        } catch (IOException exeption) {
            exeption.printStackTrace();
        }
    }
   
    // Method to get the size of the config file in bytes.
    public long getByteSize() {
        return file.length();
    }
   
   
    // Saves the configuration and loads it again.
    public FileConfiguration reloadConfig() {
       
        /*
         * Declares a new configuration to the existing FileConfiguartion 'config'.
         */
        config = loadConfig();
       
        return config;
    }
 
   
    // Deletes the current configuration File.
    public void deleteConfig() {
       
        /*
         * Checks if the File exists.
         * If so, deletes the File.
         */
        if(file.exists()){
            file.delete();
        }
    }
 
   
    // Creates a new configuration File, if no File with the same path exists.
    protected FileConfiguration createConfig() {
       
        /*
         * Checks if the File exists.
         * If not, creates the File and all parent Folders.
         */
        if(!file.exists()){
            file.getParentFile().mkdirs();
            Main.getInstance().saveResource(file.getName(), false);
        }
   
        // Creates the new YamlConfiguration 'config', which will be our new Config.
        FileConfiguration config = new YamlConfiguration();
       
            // Tries to load the configuration into the new 'config' Object
            try {
               
                config.load(file);
                Main.getInstance().getLogger().info("Successfully loaded " + file.getName() + "!");
               
            } catch (IOException e) {
                Main.getInstance().getLogger().warning("Error while loading " + file.getName() + "!");
                e.printStackTrace();
            } catch(InvalidConfigurationException  e) {
                Main.getInstance().getLogger().warning("Error while loading " + file.getName() + "!");
                e.printStackTrace();
            }
           
        // Returns the loaded configuration Object 'config'.
        return config;
    }
   
    /*
     * Configuration value getters and setters
     */
    protected Object getObject(String path) {
        Object arg = config.get(path);
            return arg;
    }
    protected void setObject(String path, Object object) {
        createSection(path);
            config.set(path, object);
    }
   
    protected String getString(String path) {
        String string = config.getString(path);
            return string;
    }
    
    protected void setString(String path, String string) {
        createSection(path);
            config.set(path, string);
    }
   
    protected String getFormatString(String path) {
        String arg = getString(path);
            if(arg != null) {
                arg = ChatColor.translateAlternateColorCodes('&', arg);
            }
                return arg;
    }
    protected void setFormatString(String path, String arg) {
        createSection(path);
            if(arg != null) {
                arg = arg.replaceAll("§", "&");
            }
                    config.set(path, arg);
    }
   
    protected short getShort(String path) {
        if(getString(path) != null) {
            short arg = Short.valueOf(getString(path));
                return arg;
        }else{
            return 0;
        }
    }
    protected void setShort(String path, short arg) {
        createSection(path);
            config.set(path, arg);
    }
   
    protected int getInteger(String path) {
        int arg = config.getInt(path);
            return arg;
    }
    protected void setInteger(String path, int arg) {
        createSection(path);
            config.set(path, arg);
    }
   
    protected long getLong(String path) {
        long arg = Long.valueOf(config.getString(path));
            return arg;
    }
    protected void setLong(String path, long arg) {
        createSection(path);
            config.set(path, arg);
    }
   
    protected boolean getBoolean(String path) {
        boolean arg = config.getBoolean(path);
            return arg;
    }
    protected void setBoolean(String path, boolean arg) {
        createSection(path);
            config.set(path, arg);
    }
   
    @SuppressWarnings("unchecked")
	protected List<String> getListString(String path) {
        List<String> arg = (List<String>) config.getList(path);
            return arg;
    }
    protected void setListString(String path, List<String> arg) {
        createSection(path);
            config.set(path, arg);
    }
   
    @SuppressWarnings("unchecked")
	protected List<String> getListFormatString(String path) {
		List<String> arg = (List<String>) config.getList(path);
            if(arg != null) {
                for(String line : arg) {
                    arg.set(arg.indexOf(line), ChatColor.translateAlternateColorCodes('&', line));
                }
            }
                return arg;
    }
    protected void setListFormatString(String path, List<String> arg) {
        createSection(path);
            if(arg != null) {
                for(String line : arg) {
                    arg.set(arg.indexOf(line), line.replaceAll("§", "&"));
                }
            }
                        config.set(path, arg);
    }
   
    protected HashMap<Object, Object> getHashMap(String path) {
        HashMap<Object, Object> map     = new HashMap<Object, Object>();
        ConfigurationSection section    = config.getConfigurationSection(path);
       
            for(String key : section.getKeys(false)) {
                map.put(key, getObject(path + key));
            }
       
        return map;
    }
   
    protected Material getMaterial(String path) {
            if(getString(path) != null) {
                return Material.valueOf(getString(path));
            }
        return null;
    }
    protected void setMaterial(String path, Material arg) {
        createSection(path);
            config.set(path, arg.name());
    }
   
    protected ItemStack getSkullItem(String path){
    	path +=".";
    	
    	String owner = getString(path + "owner");
    	double saturation = (double) getInteger(path + "saturation") / (double) 2;
    	String name = ChatColor.translateAlternateColorCodes('&', getString(path + "name"));
    	List<String> lore = getListFormatString(path + "lore");
    		lore.add("§8Saturation: " + String.valueOf(saturation));
    	
    	ItemStack skull = new ItemStack(Material.SKULL_ITEM,1, (short) 3);
    	SkullMeta meta = (SkullMeta) skull.getItemMeta();
    		meta.setOwner(owner);
    		meta.setDisplayName(name);
    		meta.setLore(lore);
    	skull.setItemMeta(meta);
    	
    	return skull;
    		
    }
    
    // Method to create a new config section.
    public void createSection(String path) {
        config.createSection(path);
        saveConfig();
    }
	
	
	
	
}
