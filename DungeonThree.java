import java.util.Scanner;
import java.util.Random;

public class DungeonThree {
    public static void main(String[] args) {
	Player player = new Player();
	Scanner console = new Scanner(System.in);
	Random random = new Random();
        System.out.println("You have encountered a spider!");
	Enemy spider = new Enemy();
	spider.setSpider();
	General.battle(player, spider, random, console);
    }


    // current dungeon
    // enter--sudden death pathway or continue
    // spider
    // hornet hive
    // possibly other monsters
}
