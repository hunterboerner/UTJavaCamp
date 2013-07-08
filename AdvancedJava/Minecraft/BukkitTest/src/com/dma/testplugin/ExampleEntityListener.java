package com.dma.testplugin;

import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
 

 
public class ExampleEntityListener implements Listener {
       
        public Example plugin;
       
        public ExampleEntityListener(Example instance) {
                plugin = instance;
        }
 
        @EventHandler
        public void onEntityDamage(EntityDamageEvent event){
        	//If the entity being damaged is a player then...
        		if(event.getEntity() instanceof Spider)
        		{
        			//Spider spider = (Spider)event.getEntity();
        				//spider.teleport(null);
        		}
                if(event.getEntity() instanceof Player){
                	{
                	Player player = (Player)event.getEntity();
                	
                	if (player.isOp())
                	//Cancel the damage event, this will give all players on this server unlimited health
                		event.setCancelled(true);
                	}
                }
        }
 
}
