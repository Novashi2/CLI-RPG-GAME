// This file contains the inventory, its items, and various functions
import java.util.Scanner;
import java.util.Arrays;



public class Inventory{
    
    String[] items = new String[100];
    int size = 0; // stores data of first null instance in the
    public final int MAX_UNIQUE_ITEMS = 20; // maximum number of item types identified by the game 

    // Adds an item given an item name. It is assumed that the correct item name is entered.
    public void addItem(String itemName, Scanner console){
	if (size == items.length){
	    System.out.println("You must use an item to add the " + itemName + "to your inventory.");
	    useItem(console);
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


    public void useItem(Scanner console, Player player){
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


	size--;
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
}
