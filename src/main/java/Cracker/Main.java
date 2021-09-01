package Cracker;

import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.structure.*;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.rand.seed.StructureSeed;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.util.pos.RPos;
import kaptainwutax.mcutils.version.MCVersion;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
    public static MCVersion VERSION;
    public static DesertPyramid DESERT_PYRAMID;
    public static SwampHut SWAMP_HUT;
    public static Igloo IGLOO;
    public static JunglePyramid JUNGLE_TEMPLE;
    public static BuriedTreasure BURIED_TREASURE;
    public static PillagerOutpost PILLAGER_OUTPOST;
    public static Shipwreck SHIPWRECK;
    public static ChunkRand cr = new ChunkRand();
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        List<OldStructure.Data<?>> dataList = new ArrayList<>();
        System.out.println("Welcome to the structure and biome seed cracker made by Toadytop with lifting brute force made by Hube12");
        System.out.println("To start you will have to provide your version and 10 old structures. These structures are desert pyramids, swamp huts, igloos, or jungle temples.");

        boolean validVersion = false;
        while(!validVersion) {
            System.out.println("Please provide your version");
            String version = br.readLine();
            switch (version) {
                case "1.17", "1.17.1" -> {
                    VERSION = MCVersion.v1_17;
                    validVersion = true;
                }
                case "1.16.5" -> {
                    VERSION = MCVersion.v1_16_5;
                    validVersion = true;
                }
                case "1.16.4" -> {
                    VERSION = MCVersion.v1_16_4;
                    validVersion = true;
                }
                case "1.16.3" -> {
                    VERSION = MCVersion.v1_16_3;
                    validVersion = true;
                }
                case "1.16.2" -> {
                    VERSION = MCVersion.v1_16_2;
                    validVersion = true;
                }
                case "1.16.1" -> {
                    VERSION = MCVersion.v1_16_1;
                    validVersion = true;
                }
                case "1.16" -> {
                    VERSION = MCVersion.v1_16;
                    validVersion = true;
                }
                case "1.15.2" -> {
                    VERSION = MCVersion.v1_15_2;
                    validVersion = true;
                }
                case "1.15.1" -> {
                    VERSION = MCVersion.v1_15_1;
                    validVersion = true;
                }
                case "1.15" -> {
                    VERSION = MCVersion.v1_15;
                    validVersion = true;
                }
                case "1.14.4" -> {
                    VERSION = MCVersion.v1_14_4;
                    validVersion = true;
                }
                case "1.14.3" -> {
                    VERSION = MCVersion.v1_14_3;
                    validVersion = true;
                }
                case "1.14.2" -> {
                    VERSION = MCVersion.v1_14_2;
                    validVersion = true;
                }
                case "1.14.1" -> {
                    VERSION = MCVersion.v1_14_1;
                    validVersion = true;
                }
                case "1.14" -> {
                    VERSION = MCVersion.v1_14;
                    validVersion = true;
                }
                case "1.13.2" -> {
                    VERSION = MCVersion.v1_13_2;
                    validVersion = true;
                }
                case "1.13.1" -> {
                    VERSION = MCVersion.v1_13_1;
                    validVersion = true;
                }
                case "1.13" -> {
                    VERSION = MCVersion.v1_13;
                    validVersion = true;
                }
                case "1.12.2" -> {
                    VERSION = MCVersion.v1_12_2;
                    validVersion = true;
                }
                case "1.12.1" -> {
                    VERSION = MCVersion.v1_12_1;
                    validVersion = true;
                }
                case "1.12" -> {
                    VERSION = MCVersion.v1_12;
                    validVersion = true;
                }
                case "1.11.2" -> {
                    VERSION = MCVersion.v1_11_2;
                    validVersion = true;
                }
                case "1.11.1" -> {
                    VERSION = MCVersion.v1_11_1;
                    validVersion = true;
                }
                case "1.11" -> {
                    VERSION = MCVersion.v1_11;
                    validVersion = true;
                }
                case "1.10.2" -> {
                    VERSION = MCVersion.v1_10_2;
                    validVersion = true;
                }
                case "1.10.1" -> {
                    VERSION = MCVersion.v1_10_1;
                    validVersion = true;
                }
                case "1.10" -> {
                    VERSION = MCVersion.v1_10;
                    validVersion = true;
                }
                case "1.9.4" -> {
                    VERSION = MCVersion.v1_9_4;
                    validVersion = true;
                }
                case "1.9.3" -> {
                    VERSION = MCVersion.v1_9_3;
                    validVersion = true;
                }
                case "1.9.2" -> {
                    VERSION = MCVersion.v1_9_2;
                    validVersion = true;
                }
                case "1.9.1" -> {
                    VERSION = MCVersion.v1_9_1;
                    validVersion = true;
                }
                case "1.9" -> {
                    VERSION = MCVersion.v1_9;
                    validVersion = true;
                }
                case "1.8.9" -> {
                    VERSION = MCVersion.v1_8_9;
                    validVersion = true;
                }
                case "1.8.8" -> {
                    VERSION = MCVersion.v1_8_8;
                    validVersion = true;
                }
                case "1.8.7" -> {
                    VERSION = MCVersion.v1_8_7;
                    validVersion = true;
                }
                case "1.8.6" -> {
                    VERSION = MCVersion.v1_8_6;
                    validVersion = true;
                }
                case "1.8.5" -> {
                    VERSION = MCVersion.v1_8_5;
                    validVersion = true;
                }
                case "1.8.4" -> {
                    VERSION = MCVersion.v1_8_4;
                    validVersion = true;
                }
                case "1.8.3" -> {
                    VERSION = MCVersion.v1_8_3;
                    validVersion = true;
                }
                case "1.8.2" -> {
                    VERSION = MCVersion.v1_8_2;
                    validVersion = true;
                }
                case "1.8.1" -> {
                    VERSION = MCVersion.v1_8_1;
                    validVersion = true;
                }
                case "1.8" -> {
                    VERSION = MCVersion.v1_8;
                    validVersion = true;
                }
                case "1.7.10" -> {
                    VERSION = MCVersion.v1_7_10;
                    validVersion = true;
                }
                case "1.7.9" -> {
                    VERSION = MCVersion.v1_7_9;
                    validVersion = true;
                }
                case "1.7.8" -> {
                    VERSION = MCVersion.v1_7_8;
                    validVersion = true;
                }
                case "1.7.7" -> {
                    VERSION = MCVersion.v1_7_7;
                    validVersion = true;
                }
                case "1.7.6" -> {
                    VERSION = MCVersion.v1_7_6;
                    validVersion = true;
                }
                case "1.7.5" -> {
                    VERSION = MCVersion.v1_7_5;
                    validVersion = true;
                }
                case "1.7.4" -> {
                    VERSION = MCVersion.v1_7_4;
                    validVersion = true;
                }
                case "1.7.3" -> {
                    VERSION = MCVersion.v1_7_3;
                    validVersion = true;
                }
                case "1.7.2" -> {
                    VERSION = MCVersion.v1_7_2;
                    validVersion = true;
                }
                case "1.6.4" -> {
                    VERSION = MCVersion.v1_6_4;
                    validVersion = true;
                }
                case "1.6.2" -> {
                    VERSION = MCVersion.v1_6_2;
                    validVersion = true;
                }
                case "1.6.1" -> {
                    VERSION = MCVersion.v1_6_1;
                    validVersion = true;
                }
                case "1.5.2" -> {
                    VERSION = MCVersion.v1_5_2;
                    validVersion = true;
                }
                case "1.5.1" -> {
                    VERSION = MCVersion.v1_5_1;
                    validVersion = true;
                }
                case "1.4.2" -> {
                    VERSION = MCVersion.v1_4_2;
                    validVersion = true;
                }
                case "1.4.4" -> {
                    VERSION = MCVersion.v1_4_4;
                    validVersion = true;
                }
                case "1.4.5" -> {
                    VERSION = MCVersion.v1_4_5;
                    validVersion = true;
                }
                case "1.4.6" -> {
                    VERSION = MCVersion.v1_4_6;
                    validVersion = true;
                }
                case "1.4.7" -> {
                    VERSION = MCVersion.v1_4_7;
                    validVersion = true;
                }
                case "1.3.2" -> {
                    VERSION = MCVersion.v1_3_2;
                    validVersion = true;
                }
                case "1.3.1" -> {
                    VERSION = MCVersion.v1_3_1;
                    validVersion = true;
                }
                case "1.2.5" -> {
                    VERSION = MCVersion.v1_2_5;
                    validVersion = true;
                }
                case "1.2.4" -> {
                    VERSION = MCVersion.v1_2_4;
                    validVersion = true;
                }
                case "1.2.3" -> {
                    VERSION = MCVersion.v1_2_3;
                    validVersion = true;
                }
                case "1.2.2" -> {
                    VERSION = MCVersion.v1_2_2;
                    validVersion = true;
                }
                case "1.2.1" -> {
                    VERSION = MCVersion.v1_2_1;
                    validVersion = true;
                }
                case "1.1" -> {
                    VERSION = MCVersion.v1_1;
                    validVersion = true;
                }
                case "1.0" -> {
                    VERSION = MCVersion.v1_0;
                    validVersion = true;
                }
                default -> System.out.println("Invalid version please try again, format should be 1.[version].[subversion]. Versions older than 1.0 and snapshots are not supported");
            }
        }
        DESERT_PYRAMID = new DesertPyramid(VERSION);
        SWAMP_HUT = new SwampHut(VERSION);
        IGLOO = new Igloo(VERSION);
        JUNGLE_TEMPLE = new JunglePyramid(VERSION);
        BURIED_TREASURE = new BuriedTreasure(VERSION);
        PILLAGER_OUTPOST = new PillagerOutpost(VERSION);
        SHIPWRECK = new Shipwreck(VERSION);
        FileWriter fw = new FileWriter("./src/main/java/file.txt");
        for(int z = 0; z < 4; z++) {
            dataList.add(getOldStructure());
        }

        List<Long> seeds = crack(dataList);
        List<Long> worldSeedsAfterInit = new ArrayList<>();
        System.out.println("Been there");
        for(long seed : seeds){
            worldSeedsAfterInit.addAll(StructureSeed.toRandomWorldSeeds(seed));
        }
        System.out.println("Done that");
        for(long seed : worldSeedsAfterInit){
            OverworldBiomeSource bs = new OverworldBiomeSource(VERSION, seed);
            boolean notFound = false;
            for(OldStructure.Data<?> data : dataList){
                if (!data.feature.canSpawn(data.chunkX, data.chunkZ, bs)){
                    notFound = true;
                    break;
                }
            }
            if(!notFound){
                System.out.println(seed);
                fw.write(seed+"\n");
            }
        }
        //boolean validOption = false;
        /*while(!false) {
            System.out.println("Found " + worldSeedsAfterInit.size() + " world seeds. Write them to a file (F), provide another structure (S), provide a biome (B), or exit? (E)");
            String option = br.readLine();
            if (option.equals("F")){
                //file shit
            } else if (option.equals("S")){
                System.out.println("Please provide a structure (D for desert pyramid, S for swamp hut, I for igloo, J for jungle temple, B for buried treasure, P for pillager outpost, R for ruined portal, and W for shipwreck. Other structures are not supported)");
                String structure = br.readLine();
                switch (structure) {
                    case "D" -> {
                        int x = -10;
                        int z = -10;
                        boolean validX = false;
                        while (!validX){
                            System.out.println("Please provide the x coordinate");
                            try{
                                x = Integer.parseInt(br.readLine());
                                if(x%512>383){
                                    System.out.println("X coordinate is in an impossible region.");
                                }
                                else validX = true;
                            } catch(Exception e){
                                System.out.println("Invalid coordinates please provide an integer coordinate.");
                            }
                        }
                        boolean validZ = false;
                        while (!validZ){
                            System.out.println("Please provide the z coordinate");
                            try{
                                z = Integer.parseInt(br.readLine());
                                if(z%512>383){
                                    System.out.println("Z coordinate is in an impossible region.");
                                }
                                else validZ = true;
                            } catch(Exception e){
                                System.out.println("Invalid coordinates please provide an integer coordinate.");
                            }
                        }
                        for(long worldSeed : worldSeedsAfterInit){
                            CPos pos = DESERT_PYRAMID.getInRegion(worldSeed, x>>9, z>>9, cr);
                            if(pos!=null){
                                if (pos.getX()!=x>>4 || pos.getZ()!=z>>4){
                                    worldSeedsAfterInit.remove(worldSeed);
                                }
                            }
                            else{
                                worldSeedsAfterInit.remove(worldSeed);
                            }
                        }
                    }
                    case "S" -> {
                        int x = -10;
                        int z = -10;
                        boolean validX = false;
                        while (!validX){
                            System.out.println("Please provide the x coordinate");
                            try{
                                x = Integer.parseInt(br.readLine());
                                if(x%512>383){
                                    System.out.println("X coordinate is in an impossible region.");
                                }
                                else validX = true;
                            } catch(Exception e){
                                System.out.println("Invalid coordinates please provide an integer coordinate.");
                            }
                        }
                        boolean validZ = false;
                        while (!validZ){
                            System.out.println("Please provide the z coordinate");
                            try{
                                z = Integer.parseInt(br.readLine());
                                if(z%512>383){
                                    System.out.println("Z coordinate is in an impossible region.");
                                }
                                else validZ = true;
                            } catch(Exception e){
                                System.out.println("Invalid coordinates please provide an integer coordinate.");
                            }
                        }
                        for(long worldSeed : worldSeedsAfterInit){
                            CPos pos = SWAMP_HUT.getInRegion(worldSeed, x>>9, z>>9, cr);
                            if(pos!=null){
                                if (pos.getX()!=x>>4 || pos.getZ()!=z>>4){
                                    worldSeedsAfterInit.remove(worldSeed);
                                }
                            }
                            else{
                                worldSeedsAfterInit.remove(worldSeed);
                            }
                        }
                    }
                    case "I" -> {
                        int x = -10;
                        int z = -10;
                        boolean validX = false;
                        while (!validX){
                            System.out.println("Please provide the x coordinate");
                            try{
                                x = Integer.parseInt(br.readLine());
                                if(x%512>383){
                                    System.out.println("X coordinate is in an impossible region.");
                                }
                                else validX = true;
                            } catch(Exception e){
                                System.out.println("Invalid coordinates please provide an integer coordinate.");
                            }
                        }
                        boolean validZ = false;
                        while (!validZ){
                            System.out.println("Please provide the z coordinate");
                            try{
                                z = Integer.parseInt(br.readLine());
                                if(z%512>383){
                                    System.out.println("Z coordinate is in an impossible region.");
                                }
                                else validZ = true;
                            } catch(Exception e){
                                System.out.println("Invalid coordinates please provide an integer coordinate.");
                            }
                        }
                        for(long worldSeed : worldSeedsAfterInit){
                            CPos pos = IGLOO.getInRegion(worldSeed, x>>9, z>>9, cr);
                            if(pos!=null){
                                if (pos.getX()!=x>>4 || pos.getZ()!=z>>4){
                                    worldSeedsAfterInit.remove(worldSeed);
                                }
                            }
                            else{
                                worldSeedsAfterInit.remove(worldSeed);
                            }
                        }
                    }
                    case "J" -> {
                        int x = -10;
                        int z = -10;
                        boolean validX = false;
                        while (!validX){
                            System.out.println("Please provide the x coordinate");
                            try{
                                x = Integer.parseInt(br.readLine());
                                if(x%512>383){
                                    System.out.println("X coordinate is in an impossible region.");
                                }
                                else validX = true;
                            } catch(Exception e){
                                System.out.println("Invalid coordinates please provide an integer coordinate.");
                            }
                        }
                        boolean validZ = false;
                        while (!validZ){
                            System.out.println("Please provide the z coordinate");
                            try{
                                z = Integer.parseInt(br.readLine());
                                if(z%512>383){
                                    System.out.println("Z coordinate is in an impossible region.");
                                }
                                else validZ = true;
                            } catch(Exception e){
                                System.out.println("Invalid coordinates please provide an integer coordinate.");
                            }
                        }
                        for(long worldSeed : worldSeedsAfterInit){
                            CPos pos = JUNGLE_TEMPLE.getInRegion(worldSeed, x>>9, z>>9, cr);
                            if(pos!=null){
                                if (pos.getX()!=x>>4 || pos.getZ()!=z>>4){
                                    worldSeedsAfterInit.remove(worldSeed);
                                }
                            }
                            else{
                                worldSeedsAfterInit.remove(worldSeed);
                            }
                        }
                    }
                    case "B" -> {
                        int x = -10;
                        int z = -10;
                        boolean validX = false;
                        while (!validX){
                            System.out.println("Please provide the x coordinate");
                            try{
                                x = Integer.parseInt(br.readLine());
                                if(x%512>383){
                                    System.out.println("X coordinate is in an impossible region.");
                                }
                                else validX = true;
                            } catch(Exception e){
                                System.out.println("Invalid coordinates please provide an integer coordinate.");
                            }
                        }
                        boolean validZ = false;
                        while (!validZ){
                            System.out.println("Please provide the z coordinate");
                            try{
                                z = Integer.parseInt(br.readLine());
                                if(z%512>383){
                                    System.out.println("Z coordinate is in an impossible region.");
                                }
                                else validZ = true;
                            } catch(Exception e){
                                System.out.println("Invalid coordinates please provide an integer coordinate.");
                            }
                        }
                        for(long worldSeed : worldSeedsAfterInit){
                            CPos pos = BURIED_TREASURE.getInRegion(worldSeed, x>>4, z>>4, cr);
                            if (pos == null) {
                                worldSeedsAfterInit.remove(worldSeed);
                            }
                        }
                    }
                    case "P" -> {

                    }
                    case "R" -> {

                    }
                    case "W" -> {

                    }
                    default -> System.out.println("Invalid structure, please use the capital letter of an old structure.");
                }
            }
        }*/
    }

    private static RegionStructure.Data<?> getOldStructure() throws IOException {
        while (true) {
            System.out.println("Provide the type of old structure (D for desert pyramid, S for swamp hut, I for igloo, and J for jungle temple)");
            String struc = br.readLine();
            switch (struc) {
                case "D" -> {
                    boolean validX = false;
                    int x = -32;
                    while (!validX) {
                        validX = true;
                        System.out.println("Please provide the x coordinate");
                        try {
                            x = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinates please try again. Please use integer form");
                            validX = false;
                        }
                        if (x % 512 > 383) {
                            System.out.println("X coordinate is outside of the valid squares of the sub region please try again");
                            validX = false;
                        }
                    }
                    boolean validY = false;
                    int y = -32;
                    while (!validY) {
                        validY = true;
                        System.out.println("Please provide the y coordinate");
                        try {
                            y = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinate please try again. Please use integer form");
                            validY = false;
                        }
                        if (y % 512 > 383) {
                            System.out.println("Y coordinate is outside of the valid squares of the sub region please try again");
                            validY = false;
                        }
                    }
                    return DESERT_PYRAMID.at(x >> 4, y >> 4);
                }
                case "S" -> {
                    boolean validX = false;
                    int x = -32;
                    while (!validX) {
                        validX = true;
                        System.out.println("Please provide the x coordinate");
                        try {
                            x = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinates please try again. Please use integer form");
                            validX = false;
                        }
                        if (x % 512 > 383) {
                            System.out.println("X coordinate is outside of the valid squares of the sub region please try again");
                            validX = false;
                        }
                    }
                    boolean validY = false;
                    int y = -32;
                    while (!validY) {
                        validY = true;
                        System.out.println("Please provide the y coordinate");
                        try {
                            y = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinate please try again. Please use integer form");
                            validY = false;
                        }
                        if (y % 512 > 383) {
                            System.out.println("Y coordinate is outside of the valid squares of the sub region please try again");
                            validY = false;
                        }
                    }
                    return SWAMP_HUT.at(x >> 4, y >> 4);
                }
                case "I" -> {
                    boolean validX = false;
                    int x = -32;
                    while (!validX) {
                        validX = true;
                        System.out.println("Please provide the x coordinate");
                        try {
                            x = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinates please try again. Please use integer form");
                            validX = false;
                        }
                        if (x % 512 > 383) {
                            System.out.println("X coordinate is outside of the valid squares of the sub region please try again");
                            validX = false;
                        }
                    }
                    boolean validY = false;
                    int y = -32;
                    while (!validY) {
                        validY = true;
                        System.out.println("Please provide the y coordinate");
                        try {
                            y = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinate please try again. Please use integer form");
                            validY = false;
                        }
                        if (y % 512 > 383) {
                            System.out.println("Y coordinate is outside of the valid squares of the sub region please try again");
                            validY = false;
                        }
                    }
                    return IGLOO.at(x >> 4, y >> 4);
                }
                case "J" -> {
                    boolean validX = false;
                    int x = -32;
                    while (!validX) {
                        validX = true;
                        System.out.println("Please provide the x coordinate");
                        try {
                            x = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinates please try again. Please use integer form");
                            validX = false;
                        }
                        if (x % 512 > 383) {
                            System.out.println("X coordinate is outside of the valid squares of the sub region please try again");
                            validX = false;
                        }
                    }
                    boolean validY = false;
                    int y = -32;
                    while (!validY) {
                        validY = true;
                        System.out.println("Please provide the y coordinate");
                        try {
                            y = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinate please try again. Please use integer form");
                            validY = false;
                        }
                        if (y % 512 > 383) {
                            System.out.println("Y coordinate is outside of the valid squares of the sub region please try again");
                            validY = false;
                        }
                    }
                    return JUNGLE_TEMPLE.at(x >> 4, y >> 4);
                }
                default -> System.out.println("Invalid structure, please use the capital first letter of an old structure.");
            }
        }
    }

    public static List<Long> crack(List<OldStructure.Data<?>> dataList) {
        Stream<Long> lowerEighteen = LongStream.range(0, 1L << 18).boxed().filter(lower -> {
            ChunkRand cr = new ChunkRand();
            for (OldStructure.Data<?> data : dataList){
                cr.setRegionSeed(lower, data.regionX, data.regionZ, data.feature.getSalt(), VERSION);
                if(cr.nextInt(24)%2 != data.offsetX %2 || cr.nextInt(24)%2 != data.offsetZ % 2) return false;
            }
            return true;
        });
        Stream<Long> lowerBitsStream = LongStream.range(0, 1L << 19).boxed().filter(lowerBits -> {
            ChunkRand rand= new ChunkRand();
            for (OldStructure.Data<?> data : dataList) {
                rand.setRegionSeed(lowerBits, data.regionX, data.regionZ, data.feature.getSalt(), VERSION);
                if (rand.nextInt(24) % 4 != data.offsetX % 4 || rand.nextInt(24) % 4 != data.offsetZ % 4) {
                    return false;
                }
            }
            return true;
        });
        System.out.println("Did this");
        Stream<Long> seedStream = lowerBitsStream.flatMap(lowerBits -> LongStream.range(0, 1L << (48 - 19)).boxed().map(upperBits -> (upperBits << 19) | lowerBits));
        System.out.println("Did that");
        Stream<Long> structureSeedStream = seedStream.filter(seed -> {
            ChunkRand rand= new ChunkRand();
            for (RegionStructure.Data<?> data : dataList) {
                rand.setRegionSeed(seed, data.regionX, data.regionZ, data.feature.getSalt(), VERSION);
                if (rand.nextInt(24) != data.offsetX || rand.nextInt(24) != data.offsetZ) {
                    return false;
                }
            }
            return true;
        });
        return structureSeedStream.parallel().collect(Collectors.toList());
    }

}