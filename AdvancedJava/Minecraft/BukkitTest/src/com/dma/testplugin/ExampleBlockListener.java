package com.dma.testplugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
 
public class ExampleBlockListener implements Listener {
 
        public Example plugin;
       
        public ExampleBlockListener(Example instance) {
                plugin = instance;
        }
 
        @EventHandler
        public void onBlockPlace(BlockPlaceEvent event){
                Player player = event.getPlayer();
                Block block = event.getBlock();
                Material mat = block.getType();
 
                player.sendMessage("You placed a block with ID : " + mat);//Display a message to the player telling them what type of block they placed.
 
        }
}
