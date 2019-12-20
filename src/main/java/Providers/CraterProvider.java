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
package Providers;

import org.terasology.core.world.generator.facets.BiomeFacet;
import org.terasology.math.TeraMath;
import org.terasology.math.geom.BaseVector2i;
import org.terasology.math.geom.Rect2i;
import org.terasology.math.geom.Vector2f;
import org.terasology.utilities.procedural.*;
import org.terasology.world.generation.*;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
@Updates(@Facet(SurfaceHeightFacet.class))
public class CraterProvider implements FacetProvider{
    private WhiteNoise noise;
    private Noise craterNoise;

    @Override
    public void setSeed(long seed){
        noise = new WhiteNoise(seed);
        craterNoise = new SubSampledNoise(new SimplexNoise(seed), new Vector2f(0.01f, 0.01f), 100);
    }

    @Override
    public void process(GeneratingRegion region){
        //Border3D border = region.getBorderForFacet(CraterFacet.class).extendBy(100, 100, 100);
        //CraterFacet craterFacet = new CraterFacet(region.getRegion(), border);
        SurfaceHeightFacet facet = region.getRegionFacet(SurfaceHeightFacet.class);
        Rect2i worldRegion = facet.getWorldRegion();
            for (BaseVector2i position : worldRegion.contents()) {
                float craterDepth = craterNoise.noise(position.x(), position.y()) * 20;
                craterDepth = TeraMath.clamp(craterDepth, -20, 0);
                facet.setWorld(position, facet.getWorld(position) + craterDepth);
            }
    }
}
