package com.dma.testplugin;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
//Any other imports can go here
 
/* Example Template
 * 
 * Plugin Tutorial
 */
 
public class Example extends JavaPlugin {
 
        public void onDisable() {}
 
        public void onEnable() {
            PluginManager pm = this.getServer().getPluginManager();
            pm.registerEvents(new ExamplePlayerListener(this), this);
            pm.registerEvents(new ExampleBlockListener(this), this);
            pm.registerEvents(new ExampleEntityListener(this), this);  
        }
}
