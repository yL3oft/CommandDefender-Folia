package me.lokka30.microlib.messaging;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.lokka30.microlib.other.VersionUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public class MessageUtils {
   @NotNull
   public static String colorizeAll(String msg) {
      return colorizeStandardCodes(colorizeHexCodes(msg));
   }

   public static String colorizeHexCodes(String msg) {
      return colorizeHexCodes("&#", "", msg);
   }

   public static String colorizeHexCodes(String startTag, String endTag, String message) {
      if (VersionUtils.isOneSixteen() && VersionUtils.isRunningSpigot()) {
         Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
         Matcher matcher = hexPattern.matcher(message);
         StringBuffer buffer = new StringBuffer(message.length() + 32);

         while(matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, "§x§" + group.charAt(0) + '§' + group.charAt(1) + '§' + group.charAt(2) + '§' + group.charAt(3) + '§' + group.charAt(4) + '§' + group.charAt(5));
         }

         return matcher.appendTail(buffer).toString();
      } else {
         return message;
      }
   }

   @NotNull
   public static String colorizeStandardCodes(String msg) {
      return Bukkit.getName().equalsIgnoreCase("CraftBukkit") ? ChatColor.translateAlternateColorCodes('&', msg) : net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', msg);
   }
}
