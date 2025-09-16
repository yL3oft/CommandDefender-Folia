package me.lokka30.microlib.other;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

import me.lokka30.microlib.folia.SchedulerUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class UpdateChecker {
   private final JavaPlugin plugin;
   private final int resourceId;

   public UpdateChecker(JavaPlugin plugin, int resourceId) {
      this.plugin = plugin;
      this.resourceId = resourceId;
   }

   public void getLatestVersion(Consumer<String> consumer) {
      SchedulerUtils.runTaskAsynchronously(() -> {
         InputStream inputStream;
         try {
            inputStream = (new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId)).openStream();
         } catch (IOException var4) {
            var4.printStackTrace();
            return;
         }

         Scanner scanner = new Scanner(inputStream);
         if (scanner.hasNext()) {
            consumer.accept(scanner.next());
         }

      });
   }

   public String getCurrentVersion() {
      return this.plugin.getDescription().getVersion();
   }
}
