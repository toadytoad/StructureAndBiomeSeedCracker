package CrackerV2;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.structure.*;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.rand.seed.StructureSeed;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.version.MCVersion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
    public static MCVersion VERSION;
    public static DesertPyramid DESERT_PYRAMID; //old
    public static SwampHut SWAMP_HUT; //old
    public static Igloo IGLOO; //1.9
    public static JunglePyramid JUNGLE_TEMPLE; //old
    public static Monument MONUMENT; //old
    public static BuriedTreasure BURIED_TREASURE; //1.13
    public static PillagerOutpost PILLAGER_OUTPOST; //1.14
    public static Shipwreck SHIPWRECK; //1.13
    public static ChunkRand cr = new ChunkRand();
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the structure and biome seed cracker made by Toadytop with lifting brute force made by Hube12");
        System.out.println("To start you will have to provide your version and 10 old structures. These structures are desert pyramids, swamp huts, monuments, or jungle temples.");
        boolean validVersion = false;
        while (!validVersion) {
            System.out.println("Please provide your version");
            String version = br.readLine();
            switch (version) {
                case "1.17", "1.16.5", "1.16.4", "1.16.3", "1.16.2", "1.16.1", "1.16", "1.15.2", "1.15.1", "1.15", "1.14.4", "1.14.3", "1.14.2", "1.14.1", "1.14" -> {
                    VERSION = MCVersion.fromString(version);
                    nine();
                    validVersion = true;
                }
                case "1.13.2", "1.13.1", "1.13" -> {
                    VERSION = MCVersion.fromString(version);
                    twelve();
                    validVersion = true;
                }
                case "1.12.2", "1.12.1", "1.12", "1.11.2", "1.11.1", "1.11", "1.10.2", "1.10.1", "1.10", "1.9.4", "1.9.3", "1.9.2", "1.9.1", "1.9" -> {
                    VERSION = MCVersion.fromString(version);
                    nine();
                    validVersion = true;
                }
                case "1.8.9", "1.8.8", "1.8.7", "1.8.6", "1.8.5", "1.8.4", "1.8.3", "1.8.2", "1.8.1", "1.8" -> {
                    VERSION = MCVersion.fromString(version);
                    old();
                    validVersion = true;
                }
                case "1.17.1" -> {
                    VERSION = MCVersion.v1_17;
                    sixteen();
                    validVersion = true;
                }
                default -> System.out.println("Please provide a valid version, versions below 1.0 are not supported");
            }
        }
    }
    private static void old() throws IOException{

    }
    private static void nine() throws IOException{
        System.out.println("1.9-1.13 version chosen, valid structures are: Desert pyramid, Jungle pyramid, Igloo, Swamp hut");
        System.out.println("Please pick the number of initial structures to provide, 5 structures will give you 1 seed most likely, 4 would give around 8, 3 would give 500 - 50,000 seeds (10 minutes to 2 hours)");
        MONUMENT = new Monument(VERSION);
        IGLOO = new Igloo(VERSION);
        JUNGLE_TEMPLE = new JunglePyramid(VERSION);
        DESERT_PYRAMID = new DesertPyramid(VERSION);
        SWAMP_HUT = new SwampHut(VERSION);
        boolean validTotal = false;
        int totalStructs = 4;
        List<OldStructure.Data<?>> dataList = new ArrayList<>();
        while (!validTotal) {
            try {
                totalStructs = Integer.parseInt(br.readLine());
                switch (totalStructs) {
                    case 3, 4, 5 -> validTotal = true;
                    default -> System.out.println("Please provide either 3, 4, or 5.");
                }
            } catch (Exception e) {
                System.out.println("Please provide either 3, 4, or 5.");
            }
        }
        for(int z = 0; z<totalStructs; z++){
            dataList.add(getOldStructureWithIgloo());
        }
        List<Long> afterInitSeeds = crackInit(dataList);
        List<Long> worldSeedsAfterInit = new ArrayList<>();
        List<Long> seeds = new ArrayList<>();
        for(long seed : afterInitSeeds){
            worldSeedsAfterInit.addAll(StructureSeed.toRandomWorldSeeds(seed));
        }
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
                seeds.add(seed);
            }
        }
        System.out.println("Found " + seeds.size() + " seeds after filter.");
        boolean exit = false;
        while(!exit){
            List<OldStructure.Data<?>> dataAfterInit = new ArrayList<>();
            List<BiomeC> biomes = new ArrayList<>();
            System.out.println("Please choose one of the following: Provide another structure, Provide a biome, Print to console, Write to file, Exit");
            String option = br.readLine();
            switch (option){
                case "Provide a structure", "provide a structure", "Provide another structure", "provide another structure", "Structure", "structure", "S", "s" -> {
                    OldStructure.Data<?> data = getOldStructureWithIgloo();
                    dataAfterInit.add(data);
                    List<Long> temp = new ArrayList<>();
                    for(long seed : seeds){
                        cr.setRegionSeed(seed, data.regionX, data.regionZ, data.feature.getSalt(), VERSION);
                        if (cr.nextInt(24) != data.offsetX || cr.nextInt(24) != data.offsetZ) {
                            temp.add(seed);
                        }
                    }
                    seeds = new ArrayList<>(temp);
                    System.out.println("Found " + seeds.size() + " seeds after filter.");
                } 
                case "Exit", "exit", "E","e", "leave", "Leave", "Escape", "escape" -> exit = true;
                case "Provide a biome", "provide a biome", "Provide another biome", "provide another biome", "Biome", "biome", "B", "b" -> {
                    System.out.println("Please provide your biome, you can provide the name with underscore or spaces, CAPS or no caps, or biome ids. If you'd like to provide a biome category type \"category\"");

                    boolean validBiome = false;
                    while(!validBiome) {
                        String biomeString = br.readLine();
                        switch (biomeString) {
                            case "OCEAN", "ocean", "0" -> {
                                BiomeC b = new BiomeC(0, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "PLAINS", "plains", "1" -> {
                                BiomeC b = new BiomeC(1, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "DESERT", "desert", "2" -> {
                                BiomeC b = new BiomeC(2, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "MOUNTAINS", "mountains", "3" -> {
                                BiomeC b = new BiomeC(3, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "FOREST", "forest", "4" -> {
                                BiomeC b = new BiomeC(4, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "TAIGA", "taiga", "5" -> {
                                BiomeC b = new BiomeC(5, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "SWAMP", "swamp", "6" -> {
                                BiomeC b = new BiomeC(6, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "RIVER", "river", "7" -> {
                                BiomeC b = new BiomeC(7, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "FROZEN_OCEAN", "frozen_ocean", "frozen ocean", "FROZEN OCEAN", "10" -> {
                                BiomeC b = new BiomeC(10, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "FROZEN_RIVER", "frozen_river", "frozen river", "FROZEN RIVER", "11" -> {
                                BiomeC b = new BiomeC(11, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "SNOWY_TUNDRA", "snowy_tundra", "snowy tundra", "SNOWY TUNDRA", "12" -> {
                                BiomeC b = new BiomeC(12, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "SNOWY_MOUNTAINS", "snowy_mountains", "snowy mountains", "SNOWY MOUNTAINS", "13" -> {
                                BiomeC b = new BiomeC(13, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "MUSHROOM_FIELDS", "mushroom_fields", "mushroom fields", "MUSHROOM FIELDS", "14" -> {
                                BiomeC b = new BiomeC(14, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "MUSHROOM_FIELD_SHORE", "mushroom_field_shore", "mushroom field shore", "MUSHROOM FIELD SHORE", "15" -> {
                                BiomeC b = new BiomeC(15, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "BEACH", "beach", "16" -> {
                                BiomeC b = new BiomeC(16, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "DESERT_HILLS", "desert_hills", "desert hills", "DESERT HILLS", "17" -> {
                                BiomeC b = new BiomeC(17, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "WOODED_HILLS", "wooded_hills", "wooded hills", "WOODED HILLS", "18" -> {
                                BiomeC b = new BiomeC(18, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "TAIGA_HILLS", "taiga_hills", "taiga hills", "TAIGA HILLS", "19" -> {
                                BiomeC b = new BiomeC(19, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "MOUNTAIN_EDGE", "mountain_edge", "mountain edge", "MOUNTAIN EDGE", "20" -> {
                                BiomeC b = new BiomeC(20, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "JUNGLE", "jungle", "21" -> {
                                BiomeC b = new BiomeC(21, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "JUNGLE_HILLS", "jungle_hills", "jungle hills", "JUNGLE HILLS", "22" -> {
                                BiomeC b = new BiomeC(22, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "JUNGLE_EDGE", "jungle_edge", "jungle edge", "JUNGLE EDGE", "23" -> {
                                BiomeC b = new BiomeC(23, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "DEEP_OCEAN", "deep_ocean", "deep ocean", "DEEP OCEAN", "24" -> {
                                BiomeC b = new BiomeC(24, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "STONE_SHORE", "stone_shore", "stone shore", "STONE SHORE", "25" -> {
                                BiomeC b = new BiomeC(25, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "SNOWY_BEACH", "snowy_beach", "snowy beach", "SNOWY BEACH", "26" -> {
                                BiomeC b = new BiomeC(26, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "BIRCH_FOREST", "birch_forest", "birch forest", "BIRCH FOREST", "27" -> {
                                BiomeC b = new BiomeC(27, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "BIRCH_FOREST_HILLS", "birch_forest_hills", "birch forest hills", "BIRCH FOREST HILLS", "28" -> {
                                BiomeC b = new BiomeC(28, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "DARK_FOREST", "dark_forest", "dark forest", "DARK FOREST", "29" -> {
                                BiomeC b = new BiomeC(29, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "SNOWY_TAIGA", "snowy_taiga", "snowy taiga", "SNOWY TAIGA", "30" -> {
                                BiomeC b = new BiomeC(30, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "SNOWY_TAIGA_HILLS", "snowy_taiga_hills", "snowy taiga hills", "SNOWY TAIGA HILLS", "31" -> {
                                BiomeC b = new BiomeC(31, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "GIANT_TREE_TAIGA", "giant_tree_taiga", "giant tree taiga", "GIANT TREE TAIGA", "32" -> {
                                BiomeC b = new BiomeC(32, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "GIANT_TREE_TAIGA_HILLS", "giant_tree_taiga_hills", "giant tree taiga hills", "GIANT TREE TAIGA HILLS", "33" -> {
                                BiomeC b = new BiomeC(33, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "WOODED_MOUNTAINS", "wooded_mountains", "wooded mountains", "WOODED MOUNTAINS", "34" -> {
                                BiomeC b = new BiomeC(34, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "SAVANNA", "savanna", "35" -> {
                                BiomeC b = new BiomeC(35, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "SAVANNA_PLATEAU", "savanna_plateau", "savanna plateau", "SAVANNA PLATEAU", "36" -> {
                                BiomeC b = new BiomeC(36, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "BADLANDS", "badlands", "37" -> {
                                BiomeC b = new BiomeC(37, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "WOODED_BADLANDS_PLATEAU", "wooded_badlands_plateau", "wooded badlands plateau", "WOODED BADLANDS PLATEAU", "38" -> {
                                BiomeC b = new BiomeC(38, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "BADLANDS_PLATEAU", "badlands_plateau", "badlands plateau", "BADLANDS PLATEAU", "39" -> {
                                BiomeC b = new BiomeC(39, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "SUNFLOWER_PLAINS", "sunflower_plains", "sunflower plains", "SUNFLOWER PLAINS", "129" -> {
                                BiomeC b = new BiomeC(129, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "DESERT_LAKES", "desert_lakes", "desert lakes", "DESERT LAKES", "130" -> {
                                BiomeC b = new BiomeC(130, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "GRAVELLY_MOUNTAINS", "gravelly_mountains", "gravelly mountains", "GRAVELLY MOUNTAINS", "131" -> {
                                BiomeC b = new BiomeC(131, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "FLOWER_FOREST", "flower_forest", "flower forest", "FLOWER FOREST", "132" -> {
                                BiomeC b = new BiomeC(132, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "TAIGA_MOUNTAINS", "taiga_mountains", "taiga mountains", "TAIGA MOUNTAINS", "133" -> {
                                BiomeC b = new BiomeC(133, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "SWAMP_HILLS", "swamp_hills", "swamp hills", "SWAMP HILLS", "134" -> {
                                BiomeC b = new BiomeC(134, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "ICE_SPIKES", "ice_spikes", "ice spikes", "ICE SPIKES", "140" -> {
                                BiomeC b = new BiomeC(140, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "MODIFIED_JUNGLE", "modified_jungle", "modified jungle", "MODIFIED JUNGLE", "149" -> {
                                BiomeC b = new BiomeC(149, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "MODIFIED_JUNGLE_EDGE", "modified_jungle_edge", "modified jungle edge", "MODIFIED JUNGLE EDGE", "151" -> {
                                BiomeC b = new BiomeC(151, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "TALL_BIRCH_FOREST", "tall_birch_forest", "tall birch forest", "TALL BIRCH FOREST", "155" -> {
                                BiomeC b = new BiomeC(155, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "TALL_BIRCH_HILLS", "tall_birch_hills", "tall birch hills", "TALL BIRCH HILLS", "156" -> {
                                BiomeC b = new BiomeC(156, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "DARK_FOREST_HILLS", "dark_forest_hills", "dark forest hills", "DARK FOREST HILLS", "157" -> {
                                BiomeC b = new BiomeC(157, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "SNOWY_TAIGA_MOUNTAINS", "snowy_taiga_mountains", "snowy taiga mountains", "SNOWY TAIGA MOUNTAINS", "158" -> {
                                BiomeC b = new BiomeC(158, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "GIANT_SPRUCE_TAIGA", "giant_spruce_taiga", "giant spruce taiga", "GIANT SPRUCE TAIGA", "160" -> {
                                BiomeC b = new BiomeC(160, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "GIANT_SPRUCE_TAIGA_HILLS", "giant_spruce_taiga_hills", "giant spruce taiga hills", "GIANT SPRUCE TAIGA HILLS", "161" -> {
                                BiomeC b = new BiomeC(161, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "MODIFIED_GRAVELLY_MOUNTAINS", "modified_gravelly_mountains", "modified gravelly mountains", "MODIFIED GRAVELLY MOUNTAINS", "162" -> {
                                BiomeC b = new BiomeC(162, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "SHATTERED_SAVANNA", "shattered_savanna", "shattered savanna", "SHATTERED SAVANNA", "163" -> {
                                BiomeC b = new BiomeC(163, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "SHATTERED_SAVANNA_PLATEAU", "shattered_savanna_plateau", "shattered savanna plateau", "SHATTERED SAVANNA PLATEAU", "164" -> {
                                BiomeC b = new BiomeC(164, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "ERODED_BADLANDS", "eroded_badlands", "eroded badlands", "ERODED BADLANDS", "165" -> {
                                BiomeC b = new BiomeC(165, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "MODIFIED_WOODED_BADLANDS_PLATEAU", "modified_wooded_badlands_plateau", "modified wooded badlands plateau", "MODIFIED WOODED BADLANDS PLATEAU", "166" -> {
                                BiomeC b = new BiomeC(166, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                validBiome = true;
                                biomes.add(b);
                            }
                            case "MODIFIED_BADLANDS_PLATEAU", "modified_badlands_plateau", "modified badlands plateau", "MODIFIED BADLANDS PLATEAU", "167" -> {
                                BiomeC b = new BiomeC(167, getBiomeCoordinates());
                                seeds = new ArrayList<>(biomeFilter(seeds, b));
                                biomes.add(b);
                                validBiome = true;
                            }
                            case "category" -> {
                                System.out.println("Please provide a biome category");
                                String category = br.readLine();
                                boolean validCategory = false;
                                while(!validCategory){
                                    switch(category){
                                        case "ocean", "OCEAN" -> {
                                            seeds = new ArrayList<>(biomeCategoryFilter(seeds, Biome.Category.OCEAN, getBiomeCoordinates()));
                                            validCategory = true;
                                        }
                                        case "plains", "PLAINS" -> {
                                            seeds = new ArrayList<>(biomeCategoryFilter(seeds, Biome.Category.PLAINS, getBiomeCoordinates()));
                                            validCategory = true;
                                        }
                                        case "desert", "DESERT" -> {
                                            seeds = new ArrayList<>(biomeCategoryFilter(seeds, Biome.Category.DESERT, getBiomeCoordinates()));
                                            validCategory = true;
                                        }
                                        case "extreme hills", "extreme_hills", "EXTREME HILLS", "EXTREME_HILLS" -> {
                                            seeds = new ArrayList<>(biomeCategoryFilter(seeds, Biome.Category.EXTREME_HILLS, getBiomeCoordinates()));
                                            validCategory = true;
                                        }
                                        case "forest", "FOREST" -> {
                                            seeds = new ArrayList<>(biomeCategoryFilter(seeds, Biome.Category.FOREST, getBiomeCoordinates()));
                                            validCategory = true;
                                        }
                                        case "taiga", "TAIGA" -> {
                                            seeds = new ArrayList<>(biomeCategoryFilter(seeds, Biome.Category.TAIGA, getBiomeCoordinates()));
                                            validCategory = true;
                                        }
                                        case "swamp", "SWAMP" -> {
                                            seeds = new ArrayList<>(biomeCategoryFilter(seeds, Biome.Category.SWAMP, getBiomeCoordinates()));
                                            validCategory = true;
                                        }
                                        case "river", "RIVER" -> {
                                            seeds = new ArrayList<>(biomeCategoryFilter(seeds, Biome.Category.RIVER, getBiomeCoordinates()));
                                            validCategory = true;
                                        }
                                        case "icy", "ICY" -> {
                                            seeds = new ArrayList<>(biomeCategoryFilter(seeds, Biome.Category.ICY, getBiomeCoordinates()));
                                            validCategory = true;
                                        }
                                        case "mushroom", "MUSHROOM" -> {
                                            seeds = new ArrayList<>(biomeCategoryFilter(seeds, Biome.Category.MUSHROOM, getBiomeCoordinates()));
                                            validCategory = true;
                                        }
                                        case "beach", "BEACH" -> {
                                            seeds = new ArrayList<>(biomeCategoryFilter(seeds, Biome.Category.BEACH, getBiomeCoordinates()));
                                            validCategory = true;
                                        }
                                        case "jungle", "JUNGLE" -> {
                                            seeds = new ArrayList<>(biomeCategoryFilter(seeds, Biome.Category.JUNGLE, getBiomeCoordinates()));
                                            validCategory = true;
                                        }
                                        case "savanna", "SAVANNA" -> {
                                            seeds = new ArrayList<>(biomeCategoryFilter(seeds, Biome.Category.SAVANNA, getBiomeCoordinates()));
                                            validCategory = true;
                                        }
                                        case "mesa", "MESA", "badlands", "BADLANDS" -> {
                                            seeds = new ArrayList<>(biomeCategoryFilter(seeds, Biome.Category.MESA, getBiomeCoordinates()));
                                            validCategory = true;
                                        }
                                    }
                                }
                                validBiome = true;
                                System.out.println("Found "+seeds.size()+" seeds after filter.");
                            }
                            default -> System.out.println("Invalid biome, please try again.");
                        }
                    }
                }
                case "print", "Print", "p", "P" -> {
                    for(long seed : seeds){
                        System.out.println(seed);
                    }
                }
            }
        }
    }
    
    public static void twelve(){

    }
    public static void sixteen(){

    }
    public static BPos getBiomeCoordinates(){
        boolean validX = false;
        int x = 0;
        while (!validX) {
            validX = true;
            System.out.println("Please provide the x coordinate");
            try {
                x = Integer.parseInt(br.readLine());
            } catch (Exception e) {
                System.out.println("Invalid coordinates please try again. Please use integer form");
                validX = false;
            }
        }
        boolean validZ = false;
        int z = 0;
        while (!validZ) {
            validZ = true;
            System.out.println("Please provide the z coordinate");
            try {
                z = Integer.parseInt(br.readLine());
            } catch (Exception e) {
                System.out.println("Invalid coordinate please try again. Please use integer form");
                validZ = false;
            }
        }
        return new BPos(x, 0, z);
    }
    public static List<Long> biomeFilter(List<Long> seeds, BiomeC b){
        List<Long> temp = new ArrayList<>();
        for(long seed : seeds){
            OverworldBiomeSource bs = new OverworldBiomeSource(VERSION, seed);
            if(bs.getBiome(b.getPos()).getId()==b.getId()){
                temp.add(seed);
            }
        }
        return temp;
    }
    public static List<Long> biomeCategoryFilter(List<Long> seeds, Biome.Category category, BPos pos){
        List<Long> temp = new ArrayList<>();
        for(long seed : seeds){
            OverworldBiomeSource bs = new OverworldBiomeSource(VERSION, seed);
            if(bs.getBiome(pos).getCategory().equals(category)){
                temp.add(seed);
            }
        }
        return temp;
    }
    public static RegionStructure.Data<?> getOldStructureNoIgloo() throws IOException {
        while (true) {
            System.out.println("Provide the type of old structure (D for desert pyramid, S for swamp hut, M for monument, and J for jungle temple)");
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
                    boolean validZ = false;
                    int z = -32;
                    while (!validZ) {
                        validZ = true;
                        System.out.println("Please provide the z coordinate");
                        try {
                            z = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinate please try again. Please use integer form");
                            validZ = false;
                        }
                        if (z % 512 > 383) {
                            System.out.println("z coordinate is outside of the valid squares of the sub region please try again");
                            validZ = false;
                        }
                    }
                    return DESERT_PYRAMID.at(x >> 4, z >> 4);
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
                    boolean validZ = false;
                    int z = -32;
                    while (!validZ) {
                        validZ = true;
                        System.out.println("Please provide the z coordinate");
                        try {
                            z = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinate please try again. Please use integer form");
                            validZ = false;
                        }
                        if (z % 512 > 383) {
                            System.out.println("z coordinate is outside of the valid squares of the sub region please try again");
                            validZ = false;
                        }
                    }
                    return SWAMP_HUT.at(x >> 4, z >> 4);
                }
                case "M" -> {
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
                    boolean validZ = false;
                    int z = -32;
                    while (!validZ) {
                        validZ = true;
                        System.out.println("Please provide the z coordinate");
                        try {
                            z = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinate please try again. Please use integer form");
                            validZ = false;
                        }
                        if (z % 512 > 383) {
                            System.out.println("z coordinate is outside of the valid squares of the sub region please try again");
                            validZ = false;
                        }
                    }
                    return MONUMENT.at(x >> 4, z >> 4);
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
                    boolean validZ = false;
                    int z = -32;
                    while (!validZ) {
                        validZ = true;
                        System.out.println("Please provide the z coordinate");
                        try {
                            z = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinate please try again. Please use integer form");
                            validZ = false;
                        }
                        if (z % 512 > 383) {
                            System.out.println("z coordinate is outside of the valid squares of the sub region please try again");
                            validZ = false;
                        }
                    }
                    return JUNGLE_TEMPLE.at(x >> 4, z >> 4);
                }
                default -> System.out.println("Invalid structure, please use the capital first letter of an old structure.");
            }
        }
    }
    public static RegionStructure.Data<?> getOldStructureWithIgloo() throws IOException {
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
                    boolean validZ = false;
                    int z = -32;
                    while (!validZ) {
                        validZ = true;
                        System.out.println("Please provide the z coordinate");
                        try {
                            z = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinate please try again. Please use integer form");
                            validZ = false;
                        }
                        if (z % 512 > 383) {
                            System.out.println("z coordinate is outside of the valid squares of the sub region please try again");
                            validZ = false;
                        }
                    }
                    return DESERT_PYRAMID.at(x >> 4, z >> 4);
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
                    boolean validZ = false;
                    int z = -32;
                    while (!validZ) {
                        validZ = true;
                        System.out.println("Please provide the z coordinate");
                        try {
                            z = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinate please try again. Please use integer form");
                            validZ = false;
                        }
                        if (z % 512 > 383) {
                            System.out.println("z coordinate is outside of the valid squares of the sub region please try again");
                            validZ = false;
                        }
                    }
                    return SWAMP_HUT.at(x >> 4, z >> 4);
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
                    boolean validZ = false;
                    int z = -32;
                    while (!validZ) {
                        validZ = true;
                        System.out.println("Please provide the z coordinate");
                        try {
                            z = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinate please try again. Please use integer form");
                            validZ = false;
                        }
                        if (z % 512 > 383) {
                            System.out.println("z coordinate is outside of the valid squares of the sub region please try again");
                            validZ = false;
                        }
                    }
                    return IGLOO.at(x >> 4, z >> 4);
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
                    boolean validZ = false;
                    int z = -32;
                    while (!validZ) {
                        validZ = true;
                        System.out.println("Please provide the z coordinate");
                        try {
                            z = Integer.parseInt(br.readLine());
                        } catch (Exception e) {
                            System.out.println("Invalid coordinate please try again. Please use integer form");
                            validZ = false;
                        }
                        if (z % 512 > 383) {
                            System.out.println("z coordinate is outside of the valid squares of the sub region please try again");
                            validZ = false;
                        }
                    }
                    return JUNGLE_TEMPLE.at(x >> 4, z >> 4);
                }
                default -> System.out.println("Invalid structure, please use the capital first letter of an old structure.");
            }
        }
    }
    public static List<Long> crackInit(List<OldStructure.Data<?>> dataList){
        Stream<Long> lowerEighteen = LongStream.range(0, 1L << 18).boxed().filter(lower -> {
            ChunkRand cr = new ChunkRand();
            for (OldStructure.Data<?> data : dataList){
                cr.setRegionSeed(lower, data.regionX, data.regionZ, data.feature.getSalt(), VERSION);
                if(cr.nextInt(24)%2 != data.offsetX %2 || cr.nextInt(24)%2 != data.offsetZ % 2) return false;
            }
            return true;
        });
        Stream<Long> eighteenToNineteen = lowerEighteen.flatMap(lower -> LongStream.range(0, 2).boxed().map(upper -> (upper<<18)|lower));
        Stream<Long> lowerNineteen = eighteenToNineteen.filter(lowerBits -> {
            ChunkRand rand= new ChunkRand();
            for (OldStructure.Data<?> data : dataList) {
                rand.setRegionSeed(lowerBits, data.regionX, data.regionZ, data.feature.getSalt(), VERSION);
                if (rand.nextInt(24) % 4 != data.offsetX % 4 || rand.nextInt(24) % 4 != data.offsetZ % 4) {
                    return false;
                }
            }
            return true;
        });
        Stream<Long> nineteenToTwenty = lowerNineteen.flatMap(lower -> LongStream.range(0, 2).boxed().map(upper -> (upper<<19)|lower));
        Stream<Long> lowerTwenty = nineteenToTwenty.filter(lowerBits -> {
            ChunkRand rand= new ChunkRand();
            for (OldStructure.Data<?> data : dataList) {
                rand.setRegionSeed(lowerBits, data.regionX, data.regionZ, data.feature.getSalt(), VERSION);
                if (rand.nextInt(24) % 8 != data.offsetX % 8 || rand.nextInt(24) % 8 != data.offsetZ % 8) {
                    return false;
                }
            }
            return true;
        });
        Stream<Long> twentyToTotal = lowerTwenty.flatMap(lowerBits -> LongStream.range(0, 1L << 28).boxed().map(upperBits -> (upperBits << 20) | lowerBits));
        Stream<Long> structureSeedStream = twentyToTotal.filter(seed -> {
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
