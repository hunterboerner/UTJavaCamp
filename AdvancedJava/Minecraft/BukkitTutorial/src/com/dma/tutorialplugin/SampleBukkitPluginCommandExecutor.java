package com.dma.tutorialplugin;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 * This is a sample CommandExectuor
 */
public class SampleBukkitPluginCommandExecutor implements CommandExecutor {
    private final SampleBukkitPlugin plugin;
    Location pLoc;
    int pLength;

    /*
     * This command executor needs to know about its plugin from which it came from
     */
    public SampleBukkitPluginCommandExecutor(SampleBukkitPlugin plugin) {
        this.plugin = plugin;
    }

    /*
     * On command set the sample message
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("fly")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Location loc = player.getLocation();
                player.teleport(new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY() + 50, loc.getBlockZ()));
                player.sendMessage("flying now");
                return true;
            }
        }

        if (command.getName().equalsIgnoreCase("maketunnel")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Location loc = player.getLocation();
                Location newLoc = loc.add(5, 0, 0);
                generateTunnel(newLoc, 5);
                player.sendMessage("making way out now");
                return true;
            }
        }

        if (command.getName().equalsIgnoreCase("maketower")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Location loc = player.getLocation();
                Location newLoc = loc.add(5, 0, 0);
                TowerGen tower = new TowerGen(plugin);
                tower.generateTower(newLoc, 5);
                player.sendMessage("tower4u");
                return true;
            }
        }

        if (command.getName().equalsIgnoreCase("gotobottom")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Location loc = player.getLocation();
                player.teleport(new Location(player.getWorld(), loc.getBlockX(), 33, loc.getBlockZ()));
                player.sendMessage("now jumping to bottom of DungeonMaze. If you weren't in a DungeonMaze world, chances are you're stuck in the floor now");
                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("upalevel")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Location loc = player.getLocation();
                player.teleport(new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY() + 6, loc.getBlockZ()));
                player.sendMessage("jumping up a level now, if you have a dragon egg. If you're not in DungeonMaze, you're probably in midair right now");
                return true;
            }
        }
        return false;
    }

    public void generateTunnel(Location loc, int length) {  // public visible method generateCube() with 2 parameters point and location
        World world = loc.getWorld();

        int x_start = loc.getBlockX();     // Set the startpoints to the coordinates of the given location
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
                    blockToChange.setTypeId(0);    // set the block to Type 0 (air)
                }
            }
        }
    }



}
