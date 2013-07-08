package com.dma.dungeonmazemod.populator;

import java.util.Random;


import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import com.dma.dungeonmazemod.DungeonMaze;

public class SilverfishBlockPopulator extends BlockPopulator {
	public static final int CHANCE = 75;
	public static final int ITERATIONS = 8;
	public static final double CHANCE_ADDITION_PER_LEVEL = -4.167; /* to 75 */

	@Override
	public void populate(World world, Random random, Chunk source) {
		
		if(!DungeonMaze.isConstantChunk(world.getName(), source)) {
			// The layers for each 4 rooms in the variable y
			for (int y=30+(2*6); y < 30+(7*6); y+=6) {
				
				// The 4 rooms on each layer saved in the variables x and z
				for (int x=0; x < 16; x+=8) {
					for (int z=0; z < 16; z+=8) {
						
						if(!DungeonMaze.isConstantRoom(world.getName(), source, x, y, z)) {
							for (int i = 0; i < ITERATIONS; i++) {
								if (random.nextInt(100) < CHANCE+(CHANCE_ADDITION_PER_LEVEL*(y-30)/6)) {
									int blockX = x + random.nextInt(8);
									int blockY = y;
									int blockZ = z + random.nextInt(8);
									
									// Get the floor location 
									int yfloor = y;
									int yfloorRelative = 0;
									Block roomBottomBlock = source.getBlock(blockX, y, blockZ);
									int typeId = roomBottomBlock.getTypeId();
									if(!(typeId==4 || typeId==48 || typeId==87 || typeId==88)) {  // x and z +2 so that you aren't inside a wall!
										yfloor++;
										yfloorRelative = 1;
									}
									blockY = y + random.nextInt(4 - yfloorRelative) + 1 + yfloorRelative;
									
									Block lanternBlock = source.getBlock(blockX, blockY, blockZ);
									if(lanternBlock.getTypeId() == 1) {
										lanternBlock.setTypeId(97);
										lanternBlock.setData((byte) 0);
									} else if(lanternBlock.getTypeId() == 4) {
										lanternBlock.setTypeId(97);
										lanternBlock.setData((byte) 1);
									} else if(lanternBlock.getTypeId() == 48) {
										lanternBlock.setTypeId(97);
										lanternBlock.setData((byte) 1);
									} else if(lanternBlock.getTypeId() == 98) {
										lanternBlock.setTypeId(97);
										lanternBlock.setData((byte) 2);
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
