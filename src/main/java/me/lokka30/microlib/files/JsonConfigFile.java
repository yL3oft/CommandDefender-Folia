package me.lokka30.microlib.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.plugin.Plugin;

public class JsonConfigFile {
   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
   private final Plugin plugin;
   private final File file;
   private final Map<String, Object> values;
   private final BufferedReader reader;

   public JsonConfigFile(Plugin plugin, File file) throws IOException {
      this.values = new HashMap();
      this.plugin = plugin;
      this.file = file;
      this.reader = Files.newBufferedReader(this.getPath());
   }

   public JsonConfigFile(Plugin plugin, String name) throws IOException {
      this(plugin, new File(plugin.getDataFolder(), name));
   }

   public BufferedReader getReader() {
      return this.reader;
   }

   public Map<String, Object> getValues() {
      return this.values;
   }

   public Object get(String key) throws ClassCastException, NullPointerException {
      return this.getValues().get(key);
   }

   public Object set(String key, Object value) throws UnsupportedOperationException, ClassCastException, NullPointerException, IllegalArgumentException {
      return this.getValues().put(key, value);
   }

   public File getFile() {
      return this.file;
   }

   public String getName() {
      return this.getFile().getName();
   }

   public Path getPath() {
      return this.getFile().toPath();
   }

   private void createIfNotExists() throws IOException {
      if (!this.getFile().exists() || this.getFile().isDirectory()) {
         try {
            this.plugin.saveResource(this.getName(), false);
         } catch (IllegalArgumentException var2) {
            if(!this.getFile().createNewFile()) throw new IllegalArgumentException("Unable to create new JSON file!", var2);
         }
      }

   }

   public void load() throws IOException {
      this.createIfNotExists();
      this.getValues().putAll((Map)GSON.fromJson(this.getReader(), this.getValues().getClass()));
   }

   public void save() throws IOException {
      String json = GSON.toJson(this.getValues());
      Files.write(this.getPath(), Collections.singletonList(json), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
   }
}
