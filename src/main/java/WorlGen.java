import Providers.*;
import Rasterizers.ExodusHouseRasterizer;
import Rasterizers.WollRasterizer;
import org.terasology.core.world.generator.facetProviders.SeaLevelProvider;
import org.terasology.engine.SimpleUri;
import org.terasology.math.TeraMath;
import org.terasology.registry.In;
import org.terasology.world.generation.BaseFacetedWorldGenerator;
import org.terasology.world.generation.WorldBuilder;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.generator.RegisterWorldGenerator;
import org.terasology.world.generator.plugin.WorldGeneratorPluginLibrary;
import org.terasology.world.zones.Zone;

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
@RegisterWorldGenerator(id = "wolls", displayName = "myWORLDHAH")
public class WorlGen extends BaseFacetedWorldGenerator {
    @In
    private WorldGeneratorPluginLibrary worldGeneratorPluginLibrary;

    public WorlGen(SimpleUri uri) {
            super(uri);
        }

    @Override
    protected WorldBuilder createWorld() {
        WorldBuilder jeff = new WorldBuilder(worldGeneratorPluginLibrary);
        jeff.setSeaLevel(0);//Why does this exist it was just in the tutorial
        jeff.addProvider(new SeaLevelProvider(0));
        jeff.addProvider(new SurfProv());
        jeff.addProvider(new CraterProvider());
        jeff.addProvider(new MntnProv());

        jeff.addProvider(new ExodusHouseProvider());
//        jeff.addZone(new Zone("zone1", (x, y, z, region) -> y >= TeraMath.floorToInt(region.getFacet(SurfaceHeightFacet.class).getWorld(x,z))+5).addProvider(new MntnProv()));
//        jeff.addZone(new Zone("zone2", (x, y, z, region) -> y <= TeraMath.floorToInt(region.getFacet(SurfaceHeightFacet.class).getWorld(x,z))-5).addProvider(new CraterProvider()));
       // jeff.addProvider(new MountainRangeProvider());
        // jeff.addProvider(new HouseProv());
        jeff.addRasterizer(new WollRasterizer());
        jeff.addRasterizer(new ExodusHouseRasterizer());
        //jeff.addRasterizer(new CraterRasterizer());
        //jeff.addRasterizer(new HouseRasterizer());
        //jeff.addPlugins();
        return jeff;
    }
}
