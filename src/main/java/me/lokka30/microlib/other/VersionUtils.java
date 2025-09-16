package me.lokka30.microlib.other;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

public class VersionUtils {
   public static MajorMinecraftVersion getMajorMinecraftVersion() {
      if (isOneNineteen()) {
         return MajorMinecraftVersion.ONE_NINETEEN;
      } else if (isOneEighteen()) {
         return MajorMinecraftVersion.ONE_EIGHTEEN;
      } else if (isOneSeventeen()) {
         return MajorMinecraftVersion.ONE_SEVENTEEN;
      } else if (isOneSixteen()) {
         return MajorMinecraftVersion.ONE_SIXTEEN;
      } else if (isOneFifteen()) {
         return MajorMinecraftVersion.ONE_FIFTEEN;
      } else if (isOneFourteen()) {
         return MajorMinecraftVersion.ONE_FOURTEEN;
      } else if (isOneThirteen()) {
         return MajorMinecraftVersion.ONE_THIRTEEN;
      } else if (isOneTwelve()) {
         return MajorMinecraftVersion.ONE_TWELVE;
      } else if (isOneEleven()) {
         return MajorMinecraftVersion.ONE_ELEVEN;
      } else if (isOneTen()) {
         return MajorMinecraftVersion.ONE_TEN;
      } else if (isOneNine()) {
         return MajorMinecraftVersion.ONE_NINE;
      } else if (isOneEight()) {
         return MajorMinecraftVersion.ONE_EIGHT;
      } else if (isOneSeven()) {
         return MajorMinecraftVersion.ONE_SEVEN;
      } else {
         return isOneSix() ? MajorMinecraftVersion.ONE_SIX : MajorMinecraftVersion.UNKNOWN;
      }
   }

   public static boolean isOneNineteen() {
      return hasEntityType("WARDEN");
   }

   public static boolean isOneEighteen() {
      return hasBiome("GROVE");
   }

   public static boolean isOneSeventeen() {
      return hasEntityType("AXOLOTL");
   }

   public static boolean isOneSixteen() {
      return hasEntityType("PIGLIN");
   }

   public static boolean isOneFifteen() {
      return hasEntityType("BEE");
   }

   public static boolean isOneFourteen() {
      return hasEntityType("PILLAGER");
   }

   public static boolean isOneThirteen() {
      return hasEntityType("TURTLE");
   }

   public static boolean isOneTwelve() {
      return hasMaterial("WHITE_CONCRETE");
   }

   public static boolean isOneEleven() {
      return hasMaterial("OBSERVER");
   }

   public static boolean isOneTen() {
      return hasMaterial("MAGMA_BLOCK");
   }

   public static boolean isOneNine() {
      return hasMaterial("END_ROD");
   }

   public static boolean isOneEight() {
      return hasMaterial("PRISMARINE");
   }

   public static boolean isOneSeven() {
      return hasMaterial("WHITE_STAINED_GLASS");
   }

   public static boolean isOneSix() {
      return hasEntityType("HORSE");
   }

   public static boolean isRunningSpigot() {
      try {
         Class.forName("net.md_5.bungee.api.ChatColor");
         return true;
      } catch (ClassNotFoundException var1) {
         return false;
      }
   }

   public static boolean isRunningPaper() {
      try {
         Class.forName("com.destroystokyo.paper.ParticleBuilder");
         return true;
      } catch (ClassNotFoundException var1) {
         return false;
      }
   }

    public static boolean isRunningFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.scheduler.RegionScheduler");
            return true;
        } catch (ClassNotFoundException var1) {
            return false;
        }
    }

   public static boolean isSpecific(String version) {
      return Bukkit.getServer().getVersion().contains(version);
   }

   public static boolean hasEntityType(String entityTypeStr) {
      try {
         EntityType.valueOf(entityTypeStr);
         return true;
      } catch (IllegalArgumentException var2) {
         return false;
      }
   }

   public static boolean hasMaterial(String materialStr) {
      try {
         Material.valueOf(materialStr);
         return true;
      } catch (IllegalArgumentException var2) {
         return false;
      }
   }

   public static boolean hasBiome(String biome) {
      try {
         Biome.valueOf(biome);
         return true;
      } catch (IllegalArgumentException var2) {
         return false;
      }
   }

   public static enum MajorMinecraftVersion {
      ONE_NINETEEN,
      ONE_EIGHTEEN,
      ONE_SEVENTEEN,
      ONE_SIXTEEN,
      ONE_FIFTEEN,
      ONE_FOURTEEN,
      ONE_THIRTEEN,
      ONE_TWELVE,
      ONE_ELEVEN,
      ONE_TEN,
      ONE_NINE,
      ONE_EIGHT,
      ONE_SEVEN,
      ONE_SIX,
      UNKNOWN;
   }
}
