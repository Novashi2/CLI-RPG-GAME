// This file contains the inventory, its items, and various functions
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
import java.io.FileNotFoundException;



public class Inventory{
    
    String[] items = new String[100];
    int size = 0; // stores data of first null instance in the
    public final int MAX_UNIQUE_ITEMS = 11; // maximum number of item types identified by the game 

    // Adds an item given an item name. It is assumed that the correct item name is entered.
    public void addItem(String itemName, Scanner console, Player player, Random random) throws FileNotFoundException{
	if (size == items.length){
	    System.out.println("You must use an item to add the " + itemName + "to your inventory.");
	    useItem(console, player, random);
	}
	items[size] = itemName;
	size ++; //fix to add inventory cap
    }

    // This function modifies 3 arrays: one that carrys the unique items in the inventory, another that records the index of the last
    // time this item was seen in the items array, and how many of that item were in the items array. 
    public void read(String[] itemsFound, int[] lastItemIndexSeen, int[] itemAmount){
	for (int i = 0; i < size; i++){
	    // below are the flags needed for the while loop to run.
	    boolean itemFound = false;
	    boolean atEndOfItemsFound = false;
	    int j = -1;
	   
	    while (!itemFound && !atEndOfItemsFound){
		j++;
		atEndOfItemsFound = itemsFound[j] == null || j == itemsFound.length; 
		if (!atEndOfItemsFound) itemFound = itemsFound[j].equals(items[i]);
	    }
	    // the conditional below checks to see if the item was not found in the itemsFound array. If it was not found, then the
	    // item is added to the next empty element in the array.
	    if (!itemFound) itemsFound[j] = items[i];

	    itemAmount[j] ++;
	    lastItemIndexSeen[j] = i; 
	}
    }


    // This function prints the inventory options
    public void print(){
	String[] itemsFound = new String[MAX_UNIQUE_ITEMS];
	int[] lastItemIndexSeen = new int[MAX_UNIQUE_ITEMS];
	int[] itemAmount = new int[MAX_UNIQUE_ITEMS];

	read(itemsFound, lastItemIndexSeen, itemAmount);

	System.out.println("#\tItem\t\tQTY");
	System.out.println("-\t------------\t---");
	int i = 0;
	while(i != MAX_UNIQUE_ITEMS && itemsFound[i] != null){
	    System.out.printf("%1d\t%-12s\t%-2d\n", i+1, itemsFound[i], itemAmount[i]);
	    i++;
	}
	System.out.println();
    }


    public void useItem(Scanner console, Player player, Random random) throws FileNotFoundException{
	String[] itemsFound = new String[MAX_UNIQUE_ITEMS];	
	int[] lastItemIndexSeen = new int[MAX_UNIQUE_ITEMS];
	int[] itemAmount = new int[MAX_UNIQUE_ITEMS];
	
	read(itemsFound, lastItemIndexSeen, itemAmount);
	System.out.println("Here are your items:\n");
	print();
	System.out.print("Print the number of the item that you want to use: ");

	int uniqueItems = 0;
	// counts the amount of non-null in the itemsFound array.
	while (itemsFound[uniqueItems] != null) uniqueItems++;
	
	int choice = General.getInt(console, 1, uniqueItems) - 1;
	
	String item = itemsFound[choice];
	
	// removes item selected from the index
	for (int i = lastItemIndexSeen[choice]; i < items.length -1; i++){
	    items[i] = items[i + 1];
	}
	items[items.length - 1] = null;

	// figures out which item function to use
	if (item.equals("health potion")) potion("health", player);
	else if(item.equals("poison potion")) potion("health", player);
	else if (item.equals("fire potion")) potion("fire", player);
	else if (item.equals("regeneration potion")) potion("regeneration", player);
	else if (item.equals("general potion")) potion("everything", player);
	else if (item.equals("dragon egg")) servantToken("dragon", player, random);
	else if (item.equals("skull")) servantToken("skeleton", player, random);
	else if (item.equals("spider egg sack")) for (int i = 0; i < random.nextInt(2, 10); i++) servantToken("spider", player, random);
	else if (item.equals("spider token")) leech(player);
	else if (item.equals("dragon crystal")) player.addScales(100);
	else if (item.equals("temporary curse cure")) curseCure(player);

	size--;
	System.out.println();
    }
	
	
/*-----------------------------------------Below this point are item functions-----------------------------------------------------*/
    public void potion(String type, Player player){
	int healthIncrement = 40;
	int effectsIncrement = 20;
	boolean doEverything = type.equals("everything");
	if (type.equals("health") || doEverything){
	    player.health += healthIncrement;
	    System.out.println("You were healed for " + healthIncrement + " health");
	}
	if (type.equals("poison") || doEverything){
	    player.poison -= effectsIncrement;
	    if (player.poison > 0) System.out.println("You were cured fo some of your poison");
	    else System.out.println("You gained poison resistance");
	}
	if (type.equals("burn") || doEverything){
	    player.burn -= effectsIncrement;
	    if (player.burn > 0) System.out.println("Some of the flames on you body were quenched");
	    else System.out.println("You have gained fire resistance.");
	}
	if (type.equals("regeneration") || doEverything){
	    player.regeneration += effectsIncrement;
	    System.out.println("You have gain some regeneration");
        }
        System.out.println();
    }
    
    // This function is ran for item that add items to the player's 
    public void servantToken(String type, Player player, Random random) throws FileNotFoundException{
	if (type.equals("dragon")){
	    String[] elements = {"fire", "earth", "poison", "air", "lightning"};
	    String element = elements[random.nextInt(elements.length)];
	    player.servants.addServant(type, null, element, true);
	    System.out.println("You have summoned a " + element + " dragon.");
	}else {
	    player.servants.addServant(type, null, null, true);
	    System.out.println("You have summoned a " + type + ".");
	}
    }

    public void leech(Player player){
	int i = 0;
	while (player.abilities[i] != null){
	    i++;
	}
	player.abilities[i] = "Leech";
	System.out.println("You have gained the leech ability");
    }
    
    public void curseCure(Player player){
	System.out.println("You have lost 50 dragon scales.");
	player.scales -= 50;
    }
}
