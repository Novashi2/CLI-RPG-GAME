package Model;

public class Battle {
    
    private Monster myMonster;

    private DungeonEntity myDungeonEntity;

    public Battle(DungeonEntity theDungeonEntity, Monster theMonster) {
        myDungeonEntity = theDungeonEntity;
        myMonster = theMonster;
    }

    public void startBattle() {
        while (myDungeonEntity.isAlive() && myMonster.isAlive()) {
            /*
             * 
             * use the takeDamage() method here in order to simulate battle
             * as well as the heal, utilize a scanner to take input from terminal
             * to decide what the player will do. make sure to increment rounds (round++) and
             * keep a running track of rounds 
             * 
             */
        }
    }
}
