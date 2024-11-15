import java.util.Random;
import java.util.Scanner;


public class Player{
    
    // general data varibles
    public static String name;
    public static int health = 100; 
    public static int savePoint = 0;

    // effects varaibles
    public static int burn = 0;
    public static int poison = 0;

    // abilities variables
    public static String[] abilities = {"Peashooter", "Wand", "Sword", null, null, null, null};// null slots added to expand skills
    public static int peashooterAmmo = 20;
    
    
    
    public static void main(String[] args){
	Random random = new Random();
	Scanner console = new Scanner(System.in);
	Enemy spider = new Enemy();
	spider.setSpider();
	attack(spider, random, console);
    }
    

    // This function prompts the user to enter a number that corresponds to an attack printed in the terminal and then uses that
    // input to determine an attack.
    public static void attack(Enemy enemy, Random random, Scanner console){
	int choice = -1;
	System.out.println("Here are your abilities:\n");
	
	int moveCounter = 0;

	while (moveCounter < abilities.length && !(abilities[moveCounter] == null)){
	    System.out.println((moveCounter + 1) + "." + abilities[moveCounter]);
	    moveCounter++;
	}
	
	System.out.println("\nPeashooter ammo: " + peashooterAmmo);
	System.out.print("Enter the number in front of the attack you want to use: ");
	
	while(!console.hasNextInt()){
	    System.out.print("You must type in one of the corresponding numbers in the list above. Please try again: ");
	    console.next();
	}
	
	choice = console.nextInt() - 1;
	
	while(choice < 0 || choice > moveCounter){
	    System.out.print("You must type in one of the corresponding numbers in the list above. Please try again: ");
	    if (console.hasNextInt()) choice = console.nextInt();
	    else console.next();
	}

	if (abilities[choice].startsWith("Peashooter")) peashooter(enemy, random);
	else if (abilities[choice].startsWith("Wand")) wand(enemy);
	else if (abilities[choice].startsWith("Sword")) sword(enemy);	
    }




/*------------------------------------- Below are weapons functions for the player.------------------------------------------------*/
// weapons are called in the attack method


    // The peashooter function uses a random object to decude whether the player will or won't hit the enemy. 
    public static void peashooter(Enemy enemy, Random random){
    	int damage = 45;
	int hitNumber = random.nextInt(100);
	
	if (peashooterAmmo > 0){
	    if (hitNumber < 50){
		System.out.println("You dealt " + damage + " damage to the " + enemy.name);
		enemy.health -= damage;
	    } else System.out.println("You missed.");

	    peashooterAmmo = peashooterAmmo - 1;
	} else System.out.println("You're out of ammunition");//possibly add this to the player attack function.
    }



    // The wand function adds a random negative effect to the enemy.
    public static void wand (Enemy enemy){
	System.out.println("Will work on soon");
    }
    
    
    public static void sword (Enemy enemy){ //possibly add something interesting to this part of the code?
	int damage = 35;
	System.out.println("You dealt " + damage + " damage to the " + enemy.name);
	enemy.health -= damage;
    }




/*------------------------------------------Here are the effects function for the player-------------------------------------------*/
    // This function processes the effects variables.
    public static void dealEffects(){
	int damage;
	if (burn > 0){
	    damage = 3;
	    burn --;
	    health -= damage;
	    System.out.println("You have been burnt for " + damage + " damage.");
	}
	if (poison > 0){
	    damage = poison * 5;
	    poison --;
	    health -= damage;
	    System.out.println("The poison in your body dealt " + damage + " damage to you.");
	}
	    
    } 

    
    
}
