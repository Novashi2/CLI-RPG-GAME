import java.util.Random;
import java.util.HashMap;


public class Player{
    
    int health = 100; //will update file to so that we can have an interface that directly interacts with these variables.
    int savePoint = 0;
    
    public static void main(String[] args){
	Random random = new Random();
	System.out.print(peaShooter(100, "chicken", 5, random));
    }
    
    public static int peaShooter( String targetName, int targetHealth, int rounds, Random random){ 
	//initializes variables needed for the code to work
	int damage = 45;
	int hitNumber = random.nextInt(100);

	if (hitNumber < 50){
	    System.out.println("You dealt " + damage + " damage to the " + targetName);
	    targetHealth = targetHealth - damage;
	} else System.out.println("You missed.");
	
	return targetHealth;//We need to set this up to return the rounds and health or have rounds processed outside the function.
	
    }
    
    public static int wand ( String targetName, int targetHealth, HashMap targetEffects){
	System.out.println("Will work on soon");
    }

    public static int sword(String targetName, int targetHealth){ //possibly add something interesting to this part of the code?
	int damage = 35;
	System.out.println("You dealt " + damage + " damage to the " + targetName);
	return targetHealth - damage;
    }
    
}
