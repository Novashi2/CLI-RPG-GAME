package Model;

// Tester doen't work for now

public class DungeonEntityTest {
    public static void main(String[] args) {
        // Create two adventurer objects
        DungeonEntity hero = new DungeonEntity("Hero", 100, 15);
        DungeonEntity monster = new DungeonEntity("Monster", 80, 10);

        // Test initial HP and damage values
        System.out.println("Initial HP of hero: " + hero.getHp());       // Expected: 100
        System.out.println("Initial damage of hero: " + hero.getDamage()); // Expected: 15
        System.out.println("Initial HP of monster: " + monster.getHp());   // Expected: 80
        System.out.println("Initial damage of monster: " + monster.getDamage()); // Expected: 10

        // Test attack and takeDamage methods
        System.out.println("\nHero attacks Monster:");
        hero.attack(monster); // Expected: Monster's HP should decrease by 15

        System.out.println("Monster's HP after attack: " + monster.getHp()); // Expected: 65

        System.out.println("\nMonster attacks Hero:");
        monster.attack(hero); // Expected: Hero's HP should decrease by 10

        System.out.println("Hero's HP after attack: " + hero.getHp()); // Expected: 90

        // Test isAlive method
        System.out.println("\nIs hero alive? " + hero.isAlive());   // Expected: true
        System.out.println("Is monster alive? " + monster.isAlive()); // Expected: true

        // Simulate combat until one adventurer is defeated
        System.out.println("\nSimulating combat until one adventurer is defeated...");
        while (hero.isAlive() && monster.isAlive()) {
            hero.attack(monster);
            if (monster.isAlive()) {
                monster.attack(hero);
            }
        }

        // Final state check
        System.out.println("\nCombat Ended:");
        System.out.println("Hero's final HP: " + hero.getHp());
        System.out.println("Monster's final HP: " + monster.getHp());
        System.out.println("Hero is alive: " + hero.isAlive());
        System.out.println("Monster is alive: " + monster.isAlive());

        // Determine the winner
        if (hero.isAlive()) {
            System.out.println("Hero wins!");
        } else if (monster.isAlive()) {
            System.out.println("Monster wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}