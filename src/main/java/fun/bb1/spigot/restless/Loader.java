package fun.bb1.spigot.restless;

import org.bukkit.Bukkit;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * 
 * Copyright 2022 BradBot_1
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class Loader extends JavaPlugin implements Listener {
	
	private static final String PERMISSION = "restless.allow";
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public final void onSleep(final PlayerBedEnterEvent event) {
		if (event.getPlayer().hasPermission(PERMISSION)) return;
		event.setCancelled(true);
		event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("You feel restless"));
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public final void onUse(final PlayerInteractEvent event) {
		if (event.getClickedBlock() == null || event.getClickedBlock().isEmpty()) return;
		if (event.getPlayer().hasPermission(PERMISSION)) return;
		if (event.getClickedBlock().getBlockData() instanceof RespawnAnchor anchor && anchor.getCharges() > 0) {
			event.setCancelled(true);
			event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("You feel restless"));
		}
	}
	
}
