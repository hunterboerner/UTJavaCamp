package com.dma.dungeonmazemod.listener;

import java.util.logging.Logger;

import org.bukkit.event.world.WorldLoadEvent;

import com.dma.dungeonmazemod.DungeonMaze;
import com.dma.dungeonmazemod.manager.DMWorldManager;

public class DungeonMazeWorldListener {
	public static Logger log = Logger.getLogger("Minecraft");
	public static DungeonMaze plugin;

	public DungeonMazeWorldListener(DungeonMaze instance) {
		plugin = instance;
	}
	
	public void onWorldLoad(WorldLoadEvent e) {
		plugin.getDMWorldManager();
		// Refresh the list with DM worlds
		DMWorldManager.refresh();
	}
}
