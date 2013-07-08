package com.dma.tutorialplugin;

import org.bukkit.craftbukkit.libs.org.ibex.nestedvm.util.Seekable;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/*
 * This is the main class of the sample plug-in
 */
public final class SampleBukkitPlugin extends JavaPlugin {
    /*
     * This is called when your plug-in is enabled
     */
    @Override
    public void onEnable() {
        // save the configuration file

        saveDefaultConfig();
        
        // Create the SampleBukkitPluginListener
        new SampleBukkitPluginListener(this);
        
        // set the command executor for sample
        this.getCommand("fly").setExecutor(new SampleBukkitPluginCommandExecutor(this));
        this.getCommand("warp").setExecutor(new SampleBukkitPluginCommandExecutor(this));
        this.getCommand("maketunnel").setExecutor(new SampleBukkitPluginCommandExecutor(this));
        this.getCommand("maketower").setExecutor(new SampleBukkitPluginCommandExecutor(this));
        this.getCommand("gotobottom").setExecutor(new SampleBukkitPluginCommandExecutor(this));
        this.getCommand("upalevel").setExecutor(new SampleBukkitPluginCommandExecutor(this));
    }
    
    /*
     * This is called when your plug-in shuts down
     */
    @Override
    public void onDisable() {        
        
    }

}
