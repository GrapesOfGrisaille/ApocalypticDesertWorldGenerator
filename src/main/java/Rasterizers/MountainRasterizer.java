package Rasterizers;

import Facets.MountainFacet;
import Providers.MountainRangeProvider;
import org.terasology.math.geom.BaseVector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;

import java.util.Map;

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
public class MountainRasterizer implements WorldRasterizer {
    private Block sand;

    @Override
    public void initialize() {
        sand = CoreRegistry.get(BlockManager.class).getBlock("CoreBlocks:Sand");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
//        MountainFacet jeff = chunkRegion.getFacet(MountainFacet.class);
//        for(Map.Entry<BaseVector3i, MountainRangeProvider.Mountain> entry : MountainFacet.getWorldEntries().entrySet()){
//
//        }
    }
}
