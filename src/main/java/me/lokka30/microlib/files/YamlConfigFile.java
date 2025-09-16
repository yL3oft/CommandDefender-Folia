package me.lokka30.microlib.files;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class YamlConfigFile {
   private final Plugin plugin;
   private final File configFile;
   private YamlConfiguration config;
   private boolean copyDefaults;

   public YamlConfigFile(Plugin plugin, File configFile) {
      this.copyDefaults = false;
      this.plugin = plugin;
      this.configFile = configFile;
   }

   public YamlConfigFile(Plugin plugin, String configName) {
      this(plugin, new File(plugin.getDataFolder(), configName));
   }

   private void createIfNotExists() throws IOException {
      if (!this.getConfigFile().exists() || this.getConfigFile().isDirectory()) {
         try {
            this.plugin.saveResource(this.getName(), false);
         } catch (IllegalArgumentException var2) {
             if(!this.getConfigFile().createNewFile()) throw new IllegalArgumentException("Unable to create new YAML file!", var2);
         }
      }

   }

   public void load() throws IOException {
      this.createIfNotExists();
      this.config = YamlConfiguration.loadConfiguration(this.getConfigFile());
      this.getConfig().options().copyDefaults(this.copyDefaults);
   }

   public void save() throws IOException {
      this.createIfNotExists();
      this.getConfig().save(this.getConfigFile());
   }

   public void setCopyDefaults(boolean copyDefaults) {
      this.copyDefaults = copyDefaults;
   }

   public File getConfigFile() {
      return this.configFile;
   }

   public YamlConfiguration getConfig() {
      return this.config;
   }

   public String getName() {
      return this.configFile.getName();
   }
}
