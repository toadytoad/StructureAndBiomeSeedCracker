package CrackerV2;

import kaptainwutax.mcutils.util.pos.BPos;

public class BiomeC {
    int id;
    BPos pos;
    public BiomeC(int id, BPos pos){
        this.id = id;
        this.pos = pos;
    }

    public BPos getPos() {
        return pos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPos(BPos pos) {
        this.pos = pos;
    }
}
