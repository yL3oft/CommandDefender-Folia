package me.lokka30.microlib.messaging;

import java.util.logging.Logger;
import me.lokka30.microlib.other.VersionUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MicroLogger {
   final boolean serverIsSpigot;
   private final Logger logger;
   private String prefix;

   public MicroLogger(String prefix) {
      this.prefix = prefix;
      this.logger = Bukkit.getLogger();
      this.serverIsSpigot = VersionUtils.isRunningSpigot();
   }

   public String getPrefix() {
      return this.prefix;
   }

   public void setPrefix(String prefix) {
      this.prefix = prefix;
   }

   public void info(String message) {
      if (this.serverIsSpigot) {
         Bukkit.getServer().getConsoleSender().sendMessage(MessageUtils.colorizeAll(this.prefix + message));
      } else {
         this.logger.info(MessageUtils.colorizeAll(this.prefix + message));
      }

   }

   public void warning(String message) {
      if (this.serverIsSpigot) {
         Bukkit.getServer().getConsoleSender().sendMessage(MessageUtils.colorizeAll(ChatColor.YELLOW + "[WARN] " + ChatColor.RESET + this.prefix + message));
      } else {
         this.logger.warning(MessageUtils.colorizeAll(this.prefix + message));
      }

   }

   public void error(String message) {
      if (this.serverIsSpigot) {
         Bukkit.getServer().getConsoleSender().sendMessage(MessageUtils.colorizeAll(ChatColor.RED + "[ERROR] " + ChatColor.RESET + this.prefix + message));
      } else {
         this.logger.severe(MessageUtils.colorizeAll(this.prefix + message));
      }

   }
}
