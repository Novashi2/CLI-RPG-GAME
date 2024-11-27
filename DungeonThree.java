import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;

public class DungeonThree {
    public static void main(String[] args) {
    }

    public static void run(Player player, Random random, Scanner console) throws FileNotFoundException{
	System.out.println("As you continue down the cave, the pathway becomes cleaner and narrower. Eventually, you see a mine");
	System.out.println("shaft to the side of the path. There is a sign on the shaft that says \"Only danger lies below.\"");
	System.out.println("Another sign hanging from the path's ceiling says \"Turn back before it is too late - The guardian\"");
	System.out.print("Do you want to go down the mine shaft (1) or continue walking foward (2)? ");

	int choice = General.pickPath(console);
	
	Enemy spider = new Enemy("Spider", null, null);
	if (choice == 2){ // kills you for going foward and ignoring the warning
	    System.out.println("As you continue down the path, the ground falls out from underneath you, and you get stuck in a");
	    System.out.println("web. Immediately, a spider starts to attack you out of nowhwere");
	    player.kill(spider);
	} 

	System.out.println("As you continue down the mineshaft, you start to see an increase of webs and eventualy enter a cavern.");
	System.out.println("There is a vague path in the cavern, but most of it is covered in a thick layer of webs.");
	System.out.println("You eventually get stuck in the webs and free yourself just in time to respond to a giant spider fast ");
	System.out.println("approaching you.");

	General.battle(player, spider, random, console);





    }
    // current dungeon
    // enter--sudden death pathway or continue
    // spider
    // hornet hive
    // possibly other monsters
}
