import java.util.Random;


public class Player{
    
    String name;
    int health = 100; //will update file to so that we can have an interface that directly interacts with these variables.
    int savePoint = 0;
    int burn = 0;
    int poison = 0;
    int peaShooterAmmo = 20;
    
    
    
    public static void main(String[] args){
	Random random = new Random();
	System.out.print(peaShooter(100, "chicken", 5, random));
    }


    // weapons programs without objects 
    public static int peaShooter( String targetName, int targetHealth, Random random){ 
	//initializes variables needed for the code to work
	int damage = 45;
	int hitNumber = random.nextInt(100);
	
	if peaShooterAmmo > 0{
	    if (hitNumber < 50){
		System.out.println("You dealt " + damage + " damage to the " + targetName);
		targetHealth = targetHealth - damage;
	    } else System.out.println("You missed.");

	    peaShooterAmmo -= 1;
	} else System.out.println("You're out of ammo")
	
	return targetHealth;
	
    }
    
    public static int wand ( String targetName, int targetHealth, HashMap targetEffects){
	System.out.println("Will work on soon");
    }

    public static int sword(String targetName, int targetHealth){ //possibly add something interesting to this part of the code?
	int damage = 35;
	System.out.println("You dealt " + damage + " damage to the " + targetName);
	return targetHealth - damage;
    }

    // Object-oriented methods(for whoever wants to use them.)
    public static void peaShooter(Enemy target, Random random){
    	int damage = 45;
	int hitNumber = random.nextInt(100);
	
	if peaShooterAmmo > 0{
	    if (hitNumber < 50){
		System.out.println("You dealt " + damage + " damage to the " + target.name);
		target.health = target.health - damage;
	    } else System.out.println("You missed.");

	    peaShooterAmmo -= 1;
	} else System.out.println("You're out of ammunition")//possibly add this to the player attack function.

    }
    
}
