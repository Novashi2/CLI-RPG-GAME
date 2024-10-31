import java.util.Random;


public class Weapons{

    public static void main(String[] args){
	Random random = new Random();
	System.out.print(peaShooter(100, "chicken", 5, random));
    }
    
    public static int peaShooter(int targetHealth, String targetName, int rounds, Random random){
	//initializes variables needed for the code to work
	int damage = 45;
	int hitNumber = random.nextInt(100);

	if (hitNumber < 50){
	    System.out.println("You dealt " + damage + " damage to the " + targetName);
	    targetHealth = targetHealth - damage;
	} else System.out.println("You missed.");
	
	return targetHealth;//We need to set this ip to return the rounds and health. 
	
    }
    
    public static int wand (int targetHealth, String targetName, String[] targetEffects){
	System.out.println("Will work on soon");
    }
    
}
