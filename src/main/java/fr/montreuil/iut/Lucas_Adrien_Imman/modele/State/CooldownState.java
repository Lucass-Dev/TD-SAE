package fr.montreuil.iut.Lucas_Adrien_Imman.modele.State;

public abstract class CooldownState {
    private final int startTick;
    private int actualTick;
    private final int delay;
    private final int amount;

    public CooldownState(int startTick, int delay, int amount){
        this.startTick = startTick;
        this.delay = delay;
        this.amount = amount;
    }

    public boolean stillHasState(){
        return this.actualTick - this.startTick >= this.delay;
    }

    public void actualizeState(int turn){
        this.actualTick = turn;
    }

    abstract void doEffect(/*Actor a*/);

    //GETTER
    public int getStartTick() {
        return startTick;
    }

    public int getActualTick() {
        return actualTick;
    }

    public int getDelay() {
        return delay;
    }

    public int getAmount() {
        return amount;
    }
}
