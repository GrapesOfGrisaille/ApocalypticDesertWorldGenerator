/*
 * Copyright 2014 MovingBlocks
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
package Providers;

import org.terasology.core.world.CoreBiome;
import org.terasology.core.world.generator.facets.BiomeFacet;
import org.terasology.math.geom.BaseVector2i;
import org.terasology.utilities.procedural.WhiteNoise;
import org.terasology.world.generation.*;
import org.terasology.world.generation.facets.SeaLevelFacet;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.generation.facets.SurfaceHumidityFacet;
import org.terasology.world.generation.facets.SurfaceTemperatureFacet;
/**
 * Determines the biome based on temperature and humidity
 */
@Produces(BiomeFacet.class)
public class BiomeProvider implements FacetProvider {
    private WhiteNoise noise;

    @Override
    public void setSeed(long seed) {
        noise = new WhiteNoise(seed);
    }

    @Override
    public void process(GeneratingRegion region) {

        Border3D border = region.getBorderForFacet(BiomeFacet.class);
        BiomeFacet biomeFacet = new BiomeFacet(region.getRegion(), border);

        for (BaseVector2i pos : biomeFacet.getRelativeRegion().contents()) {
            float yes = noise.noise(pos.getX(), pos.getY());
            if(yes > 0.9999) {
                biomeFacet.set(pos, NukedDesertBiome.MOUNTAINRANGE);
            }
            else if(yes <= 0.9999 && yes > .5){
                biomeFacet.set(pos, NukedDesertBiome.CRATER);
            }
            else{
                biomeFacet.set(pos, NukedDesertBiome.PLAINS);
            }
            region.setRegionFacet(BiomeFacet.class, biomeFacet);
        }
    }
}
