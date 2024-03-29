package Rasterizers;

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.utilities.procedural.Noise;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

/*
 * Copyright 2019 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class WollRasterizer implements WorldRasterizer {

    private Block dirt;
    private Block grass;
    private Block snow;
    private Block sand;
    private Block stone;
    private Block lava;

    @Override
    public void initialize() {
        dirt = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:Dirt");
        grass = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:Grass");
        snow = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:Snow");
        sand = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:Sand");
        stone = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:Stone");
        lava = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:lava");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);
        for (Vector3i position : chunkRegion.getRegion()) {
            float surfaceHeight = surfaceHeightFacet.getWorld(position.x, position.z);
            if(position.y < surfaceHeight) {
                if (surfaceHeight < -7) {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), Math.random() > .7 ? lava : stone);
                }
                else if(surfaceHeight >95){
                    if (position.y < surfaceHeight - 10) {
                        chunk.setBlock(ChunkMath.calcBlockPos(position), dirt);
                    }
                    else chunk.setBlock(ChunkMath.calcBlockPos(position),grass);
                }
                else if (position.y < surfaceHeight - 10) {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), stone);
                }
                else chunk.setBlock(ChunkMath.calcBlockPos(position), sand);
            }
//            if (position.y < surfaceHeight-1 && position.y < 100) {
//                chunk.setBlock(ChunkMath.calcBlockPos(position), dirt);
//            }
//            else if(position.y < surfaceHeight && position.y < 100){
//                chunk.setBlock(ChunkMath.calcBlockPos(position), grass);
//            }
//            else if(position.y < surfaceHeight) chunk.setBlock(ChunkMath.calcBlockPos(position), snow);
        }
    }

}
