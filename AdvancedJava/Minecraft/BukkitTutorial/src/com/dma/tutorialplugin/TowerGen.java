package com.dma.tutorialplugin;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

/**
 * Created with IntelliJ IDEA.
 * User: theron
 * Date: 7/8/13
 * Time: 2:30 PM
 */

public class TowerGen {
    protected final SampleBukkitPlugin plugin;
    World world;
    int z_operate, y_operate, x_operate;
    private Location pLoc;
    private int pLength;

    public TowerGen(SampleBukkitPlugin plugin) {
        this.plugin = plugin;
    }

    public void generateTower(Location loc, int length) {
        // int coords[][][] = new int[length][length][length];
        pLoc = loc;
        pLength = length;
        world = loc.getWorld();
        int x_start = loc.getBlockX();
        // Set the startpoints to the coordinates of the given location
        int y_start = loc.getBlockY();
        int z_start = loc.getBlockZ();
            /* Note: used getBlockX() instead of getX() because it returns an int value and avoid the cast with (int)loc.getX() */

        int x_length = x_start + length;    // now i set the lengths for each dimension... should be clear.
        int y_length = y_start + length;
        int z_length = z_start + length;

        for (int x_operate = x_start; x_operate <= x_length; x_operate++) {
            // Loop 1 for the X-Dimension "for x_operate (which is set to x_start)
            //do whats inside the loop while x_operate is
            //<= x_length and after each loop increase
            //x_operate by 1 (x_operate++ is the same as x_operate=x_operate+1;)
            for (int y_operate = y_start; y_operate <= y_length; y_operate++) {// Loop 2 for the Y-Dimension
                for (int z_operate = z_start; z_operate <= z_length; z_operate++) {// Loop 3 for the Z-Dimension

                    // get the block with the current coordinates
                    Block blockToChange = world.getBlockAt(x_operate, y_operate, z_operate);
                    blockToChange.setTypeId(1);    // set the block to Type 0 (air)
                }
            }
        }

        this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin,
                new Runnable() {

                    public void run() {
                        world = pLoc.getWorld();
                        int x_start = pLoc.getBlockX();
                        // Set the start points to the coordinates of the given location
                        int y_start = pLoc.getBlockY();
                        int z_start = pLoc.getBlockZ();
            /* Note: used getBlockX() instead of getX() because it returns an int value and avoid the cast with (int)loc.getX() */

                        int x_length = x_start + pLength;    // now i set the lengths for each dimension... should be clear.
                        int y_length = y_start + pLength;
                        int z_length = z_start + pLength;
                        for (x_operate = x_start; x_operate <= x_length; x_operate++) {
                            // Loop 1 for the X-Dimension "for x_operate (which is set to x_start)
                            //do whats inside the loop while x_operate is
                            //<= x_length and after each loop increase
                            //x_operate by 1 (x_operate++ is the same as x_operate=x_operate+1;)
                            for (y_operate = y_start; y_operate <= y_length; y_operate++) {// Loop 2 for the Y-Dimension
                                for (z_operate = z_start; z_operate <= z_length; z_operate++) {// Loop 3 for the Z-Dimension
                                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                        public void run() {
                                            System.out.println("OH MA GERD");
                                            // get the block with the current coordinates
                                            System.out.println(x_operate + "|" + y_operate + "|" + z_operate);
                                            Block blockToChange = world.getBlockAt(x_operate, y_operate, z_operate);
                                            blockToChange.setTypeId(0);    // set the block to Type 0 (air)
                                        }
                                    }, 5L);

                                }
                            }
                        }
                    }
                }, 100L);
    }
}
