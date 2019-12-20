package Providers;

import Facets.HouseFacet;
import Facets.MountainFacet;
import org.terasology.math.Region3i;
import org.terasology.math.TeraMath;
import org.terasology.math.geom.*;
import org.terasology.utilities.procedural.*;
import org.terasology.world.generation.*;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.generation.facets.base.SparseObjectFacet3D;

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
@Requires(@Facet(value = SurfaceHeightFacet.class, border = @FacetBorder(sides = 300, bottom = 300, top = 300)))
@Produces(MountainFacet.class)
public class MountainRangeProvider implements FacetProviderPlugin{
    private WhiteNoise noise;
    private Noise mountainNoise;

    @Override
    public void setSeed(long seed){
        noise = new WhiteNoise(seed);
        mountainNoise = new SubSampledNoise(new BrownianNoise(new PerlinNoise(seed + 2), 2), new Vector2f(0.001f, 0.001f), 1);
    }

    @Override
    public void process(GeneratingRegion region) {
        SurfaceHeightFacet facet = region.getRegionFacet(SurfaceHeightFacet.class);
        float mountainHeight = 550;
        Rect2i processRegion = facet.getWorldRegion();
        for(BaseVector2i position: processRegion.contents()){
            float additiveMountainHeight = mountainNoise.noise(position.x(), position.y()) * mountainHeight;
            additiveMountainHeight = TeraMath.clamp(additiveMountainHeight, 0, mountainHeight);
            facet.setWorld(position, facet.getWorld(position) + additiveMountainHeight);
        }
    }

    public class Mountain{
        private float mountainHeight = 500;
    }
}
