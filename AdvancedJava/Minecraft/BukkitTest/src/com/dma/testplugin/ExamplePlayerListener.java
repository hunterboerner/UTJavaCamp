package com.dma.testplugin;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
 
public class ExamplePlayerListener implements Listener {
       
        @SuppressWarnings("unused")
		private Example plugin;
       
        public ExamplePlayerListener(Example instance) {
                plugin = instance;
        }
        
        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent event){
        	Player player = event.getPlayer(); // The player who joined
        	
        	//Use this pattern to set all the player's mandatory starting inventory (doesn't work if inventory totally full?)
            PlayerInventory inventory = player.getInventory(); // The player's inventory
            ItemStack itemstack = new ItemStack(Material.DIAMOND, 2); // A stack of diamonds
            ItemStack stickstack = new ItemStack(Material.STICK, 1);
            ItemStack woodstack = new ItemStack(Material.WOOD, 4);
            ItemStack mapstack = new ItemStack(Material.MAP, 1);
            if (!inventory.contains(itemstack) || !inventory.contains(stickstack) || !inventory.contains(woodstack)) {
                inventory.addItem(itemstack); // Adds a stack of diamonds to the player's inventory
                inventory.addItem(stickstack);
                inventory.addItem(woodstack);
                player.sendMessage("Welcome! You seem to be be new, so we gave you some diamonds, a stick, and a crafting table!");
                inventory.addItem(mapstack);
            	player.sendMessage("Arrr, matey! Ye be also needing a map, y'hear!");
            }
        }
 
        @EventHandler
        public void onPlayerMove(PlayerMoveEvent event){
                Player p = event.getPlayer();
                Location loc = p.getLocation();
                DecimalFormat df = new DecimalFormat("#.##");
                
                p.sendMessage("Your X Coordinates : " + df.format(loc.getX()));
                p.sendMessage("Your Y Coordinates : " + df.format(loc.getY()));
                p.sendMessage("Your Z Coordinates : " + df.format(loc.getZ()));
        		
                double y = loc.getY();			
        		if(y >= 75) {
        			// The player is above the surface
        			PlayerInventory inventory = p.getInventory();
        			ItemStack eggstack = new ItemStack(Material.DRAGON_EGG, 1);
        			if (inventory.contains(eggstack))
        			{
        				p.sendMessage("You Win!");
        				Bukkit.broadcastMessage(ChatColor.RED + (p.getDisplayName () + " won the game!"));
        			}   
        			else
        				p.sendMessage("You don't have a dragon egg yet... go get one!");		
        		} 
   }
 
}
