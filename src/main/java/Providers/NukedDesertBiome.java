package Providers;

import org.terasology.biomesAPI.Biome;

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
public enum NukedDesertBiome implements Biome {
    PLAINS("Plains"), //The generic flat desert(flattened by bombs)
    MOUNTAINRANGE("MountainRange"),//Rare but large mountains that avoided being flattened by bombs
    CRATER("Crater"),
    /*I want to try and make it common for a bunch  of craters of all sizes to be around the area so it's not just boring flat terrain because that
    would be boring. Later on I want to add very large craters that have good materials and can act as like their own special biome because they'll be huge
    */
    EXODUS("Exodus"),//This will be a mountain range containing the ruins of the Disciples of Exodus(not to be implemented yet I'm still struggling with basic stuff)
    REJUVENATOR("Rejuvenator");//This will be a cave containing the vegetation, livestock, and ruins of the Rejuvenators(also not to be implemented yet because boy am I strugglin)
    //Possibly add regular caves that weren't colonized
    private final String id;
    private final String name;

    NukedDesertBiome(String n){
        id = "NukedDesertBiome:" + name().toLowerCase();
        this.name = n;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return name;
    }
}
