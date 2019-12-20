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

import org.terasology.utilities.procedural.Noise;
import org.terasology.utilities.procedural.SimplexNoise;
import org.terasology.world.generation.FacetProvider;
import org.terasology.world.generation.GeneratingRegion;

public class CaveProvider implements FacetProvider {
    private Noise baseCaveNoise;
    private Noise baseFadeCaveNoise;

    @Override
    public void setSeed(long seed){
        baseCaveNoise = new SimplexNoise(seed + 2);
        baseFadeCaveNoise = new SimplexNoise(seed + 3);
    }

    @Override
    public void process(GeneratingRegion region) {

    }
}
