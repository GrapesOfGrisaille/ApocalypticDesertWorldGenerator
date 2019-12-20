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
package Rasterizers;

import Facets.ExodusHouseFacet;
import Facets.HouseFacet;
import Providers.ExodusHouseProvider;
import Providers.HouseProv;
import org.terasology.math.ChunkMath;
import org.terasology.math.Region3i;
import org.terasology.math.geom.BaseVector3i;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;

import java.util.Map;

public class ExodusHouseRasterizer implements WorldRasterizer {
    private Block air;
    private Block glass;
    private Block plank;
    private Block chest;
    private Block torch;
    private Block stone;

    @Override
    public void initialize() {
        air = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:Air");
        glass = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:Glass");
        plank = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:Plank");
        chest = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:Chest");
        torch = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:Torch.TOP");
        stone = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:Stone");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        ExodusHouseFacet eHouseFacet = chunkRegion.getFacet(ExodusHouseFacet.class);

        for(Map.Entry<BaseVector3i, ExodusHouseProvider.ExodusHouse> entry : eHouseFacet.getWorldEntries().entrySet()){
            Vector3i centerHousePosition = new Vector3i(entry.getKey());
            int extent = entry.getValue().getExtent();
            centerHousePosition.add(0, extent, 0);
            Region3i walls = Region3i.createFromCenterExtents(centerHousePosition, extent);
            Region3i inside = Region3i.createFromCenterExtents(centerHousePosition, extent -1);
            boolean chestyn = false;
            for(Vector3i newBlockPosition : walls){
                if(chunkRegion.getRegion().encompasses(newBlockPosition) && !inside.encompasses(newBlockPosition)){
                    if(newBlockPosition.y <= centerHousePosition.y+2 && newBlockPosition.y >= centerHousePosition.y-2) {
                        chunk.setBlock(ChunkMath.calcBlockPos(newBlockPosition), glass);
                    }
                    else if (newBlockPosition.y <= centerHousePosition.y+4 && newBlockPosition.y >= centerHousePosition.y-4){
                        chunk.setBlock(ChunkMath.calcBlockPos(newBlockPosition), stone);
                    }
                    else chunk.setBlock(ChunkMath.calcBlockPos(newBlockPosition), plank);
                }
                else if(chunkRegion.getRegion().encompasses(newBlockPosition) && inside.encompasses(newBlockPosition)){
                    if(newBlockPosition.y == centerHousePosition.y-2){
                        chunk.setBlock(ChunkMath.calcBlockPos(newBlockPosition), torch);
                    }
                    else chunk.setBlock(ChunkMath.calcBlockPos(newBlockPosition), air);
                }
            }
        }
    }
}
