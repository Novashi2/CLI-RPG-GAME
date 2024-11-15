package Model;

public class MonsterTester {
    public static void main(String[] args) {
        // Create an instance of TestMonster with healing range and chance parameters
        TestMonster testMonster = new TestMonster("Goblin", 100, 15, 3, 10, 0.5);

        // Display initial values
        System.out.println("Initial HP: " + testMonster.getHp()); // Expected: 100
        System.out.println("Damage: " + testMonster.getDamage()); // Expected: 15
        System.out.println("Is Alive? " + testMonster.isAlive()); // Expected: true

        // Test takeDamage method
        testMonster.takeDamage(20);
        System.out.println("HP after taking 20 damage: " + testMonster.getHp()); // Expected: 80

        // Test heal method with random chance and variable healing amount
        System.out.println("\nHealing attempts:");
        for (int i = 0; i < 5; i++) { // Test healing over multiple rounds
            System.out.println("Round " + (i + 1) + ":");
            testMonster.heal();
            System.out.println("HP after possible healing: " + testMonster.getHp());
            System.out.println();
        }

        // Test attack method
        TestMonster anotherMonster = new TestMonster("Orc", 120, 20, 2, 8, 0.3);
        testMonster.attack(anotherMonster);
        System.out.println("Orc HP after attack: " + anotherMonster.getHp()); // Expected: 120 - 15 = 105

        // Test isAlive after taking lethal damage
        anotherMonster.takeDamage(105);
        System.out.println("Is Orc Alive? " + anotherMonster.isAlive()); // Expected: false
        System.out.println();
    }
}

// Subclass of Monster to allow instantiation
class TestMonster extends Monster {
    public TestMonster(String theChrName, int theHp, int theDamage, int healMin, int healMax, double healChance) {
        super(theChrName, theHp, theDamage, healMin, healMax, healChance);
    }
}
