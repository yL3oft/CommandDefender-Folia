package me.lokka30.microlib.messaging;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MultiMessage {
   public List<String> content;
   public List<Placeholder> placeholders;

   public MultiMessage(List<String> content, List<Placeholder> placeholders) {
      this.content = content;
      this.placeholders = placeholders;
   }

   public List<String> getTranslatedContent() {
      ArrayList<String> translated = new ArrayList();
      Iterator var2 = this.content.iterator();

      while(var2.hasNext()) {
         String line = (String)var2.next();
         String translatedLine = MessageUtils.colorizeAll(line);

         Placeholder placeholder;
         for(Iterator var5 = this.placeholders.iterator(); var5.hasNext(); translatedLine = placeholder.translateInMessage(translatedLine)) {
            placeholder = (Placeholder)var5.next();
         }

         translated.add(translatedLine);
      }

      return translated;
   }

   public List<String> getUntranslatedContent() {
      return this.content;
   }

   public void setContent(List<String> content) {
      this.content = content;
   }

   public List<Placeholder> getPlaceholders() {
      return this.placeholders;
   }

   public void setPlaceholders(List<Placeholder> placeholders) {
      this.placeholders = placeholders;
   }

   public void send(@NotNull CommandSender sender) {
      this.getTranslatedContent().forEach(sender::sendMessage);
   }

   public void send(@NotNull Player player) {
      this.getTranslatedContent().forEach(player::sendMessage);
   }

   public static class Placeholder {
      public final String id;
      public final String value;
      public final boolean colorizeValue;

      public Placeholder(String id, String value, boolean colorizeValue) {
         this.id = id;
         this.value = value;
         this.colorizeValue = colorizeValue;
      }

      public String translateInMessage(String msg) {
         return this.colorizeValue ? msg.replace("%" + this.id + "%", MessageUtils.colorizeAll(this.value)) : msg.replace("%" + this.id + "%", this.value);
      }
   }
}
