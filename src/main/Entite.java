package main;

import main.statut.Force;
import main.statut.Vulnerabilite;

public abstract class Entite {
    private int pv;
    private int pvMax;
    private int block;
    public Vulnerabilite vulnerabilite;
    public Force force;

    public Entite(int pvMax) {
        this.pvMax = pvMax;
        this.pv = pvMax;
        this.block = 0;
        this.vulnerabilite = new Vulnerabilite(0);
        force = new Force(0);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "pv: " + pv + "/" + pvMax + ", def: " + block;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getPv() {
        return pv;
    }

    public int getBlock() {
        return block;
    }

    public boolean alive() {
        return (getPv() > 0);
    }

    public Vulnerabilite getVulnerabilite() {
        return vulnerabilite;
    }

}
