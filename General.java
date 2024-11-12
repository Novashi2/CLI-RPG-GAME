import java.util.Hashmap;
import java.util.Scanner;


public class General {
    public static void main(String[] args) {
        
    }
    
:    //this is the effects function used for NPCs 
    public static int dealEffects( String targetName, int targetHealth, HashMap<String, Integer> targetEffects){
	if (targetEffects.contains("burn") || targetEffects.get("burn") > 0){
	    targetHealth =  burn("The " + targetName, targetHealth, targetEffects);
	}
	if (targetEffects.contains("poison") || targetEffects.get("poison") > 0){
	    targerHealth = poison("The" + targetName, targetHealth, targetEffects);
	}
	    
	return targetHealth;
    }
    

// methods needed to make other functions work. 
    public static int burn(int counter, String name){
	int damage = 3;
	int targetHealth -= damage; 
	targetEfffects.replace("burn", targetEffects.get("burn") -1);
	System.out.print(target + " got burnt for " + damage + " damage.");
	return targetHealth;
    }

    public static int poison(int counter, String name){
	int poisonCounter = targetEffects.get("poison");
	int damage = 5 * poisonCounter;
	targetHealth -= damage;
	System.out.print(target + " got poisoned for " + damage + " damage.");
	targetEffects.replace("poison", poisonCounter - 1);
	return targetHealth;
    }
}
