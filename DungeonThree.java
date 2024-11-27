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



    }
    // current dungeon
    // enter--sudden death pathway or continue
    // spider
    // hornet hive
    // possibly other monsters
}
