package CrackerV2;

import kaptainwutax.featureutils.structure.*;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.version.MCVersion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static MCVersion VERSION;
    public static DesertPyramid DESERT_PYRAMID; //old
    public static SwampHut SWAMP_HUT; //old
    public static Igloo IGLOO; //old
    public static JunglePyramid JUNGLE_TEMPLE; //old
    public static BuriedTreasure BURIED_TREASURE; //1.13
    public static PillagerOutpost PILLAGER_OUTPOST; //1.14
    public static Shipwreck SHIPWRECK; //1.13
    public static ChunkRand cr = new ChunkRand();
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the structure and biome seed cracker made by Toadytop with lifting brute force made by Hube12");
        System.out.println("To start you will have to provide your version and 10 old structures. These structures are desert pyramids, swamp huts, igloos, or jungle temples.");
        boolean validVersion = false;
        while (!validVersion) {
            System.out.println("Please provide your version");
            String version = br.readLine();
            switch (version) {
                case "1.17", "1.16.5", "1.16.4", "1.16.3", "1.16.2", "1.16.1", "1.16", "1.15.2", "1.15.1", "1.15", "1.14.4", "1.14.3", "1.14.2", "1.14.1", "1.14" -> {
                    VERSION = MCVersion.fromString(version);
                    sixteen();
                    validVersion = true;
                }
                case "1.13.2", "1.13.1", "1.13" -> {
                    VERSION = MCVersion.fromString(version);
                    twelve();
                    validVersion = true;
                }
                case "1.12.2", "1.12.1", "1.12", "1.11.2", "1.11.1", "1.11", "1.10.2", "1.10.1", "1.10", "1.9.4", "1.9.3", "1.9.2", "1.9.1", "1.9", "1.8.9", "1.8.8", "1.8.7", "1.8.6", "1.8.5", "1.8.4", "1.8.3", "1.8.2", "1.8.1", "1.8", "1.7.10", "1.7.9", "1.7.8", "1.7.7", "1.7.6", "1.7.5", "1.7.4", "1.7.3", "1.7.2", "1.6.4", "1.6.2", "1.6.1", "1.5.2", "1.5.1", "1.4.7", "1.4.6", "1.4.5", "1.4.4", "1.4.2", "1.3.2", "1.3.1", "1.2.5", "1.2.4", "1.2.3", "1.2.2", "1.2.1", "1.1", "1.0" -> {
                    VERSION = MCVersion.fromString(version);
                    old();
                    validVersion = true;
                }
                default -> {
                    System.out.println("Please provide a valid version, versions below 1.0 are not supported");
                }
            }
        }
    }

    private static void old() {
        System.out.println("Pre 1.13 version chosen, valid structures are: Desert pyramid, Jungle pyramid, Igloo, Swamp hut");
    }
    public static void twelve(){

    }
    public static void sixteen(){

    }
}
