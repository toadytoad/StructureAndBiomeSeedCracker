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
        while(!validVersion) {
            System.out.println("Please provide your version");
            String version = br.readLine();
            switch (version) {
                case "1.17", "recent", "1.16.5", "1.16.4", "1.16.3", "1.16.2"
            }
    }
}
