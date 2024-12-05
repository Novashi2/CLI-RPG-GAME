import java.util.Scanner;
import java.util.Random;
import java.lang.InterruptedException;
import java.io.FileNotFoundException;


public class DungeonOne {

        public static void firstDungeon(Player player, Random random, Scanner console)throws FileNotFoundException, InterruptedException{
            System.out.println("You proceed down a slimey hallway, accidently stepping into puddles of sludge along the way.");
            System.out.println("As you keep walking you come across a table with a fuming glass of a green liquid.. maybe its tea..?");
            System.out.print("\n Do you drink the tea?(1) Or continue down the path?(2) ");

            int choice = General.pickPath(console);

            Enemy Slime = new Enemy("Slime",null,null,false);
            if (choice == 1){
                System.out.println("You drink the fuming liquid from the mug that has \"world's best dungeon crawler\" written on it!");
                System.out.println("Seconds after you feel sick to your stomache and fall to the floor.");
                player.kill(Slime); /*Don't drink yucky juice you find laying around :< */
            }
            System.out.println("As you near a clearing you make out something shiny at the end of the corridor");
            System.out.println("A large slime blocks your path!");

            General.battle(player, Slime, random, console);
            String[] slimeDrops = {"amulet", "poison potion", "dragon tooth", "health potion"};
            Slime.drop(player, random, console,slimeDrops,false,2);
            General.Continue(console);

            System.out.println("You step past the puddle the slime has become as you continue forward.");
            System.out.println("The dungeon is lit by moss mimicking moonlight as you advance.");
            System.out.println("A ragged man staring at the glowing moss stands hunched over in the middle of the room.");
            System.out.println("He notices you quickly, his eyes flash red for a moment, his hands clenched and shaking.");
            System.out.print("\n Do you... talk to him about gas prices?(1) Or... Give him a high five?(2) ");


        }
}