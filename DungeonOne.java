import java.util.Scanner;
import java.util.Random;
import java.lang.InterruptedException;
import java.io.FileNotFoundException;


public class DungeonOne {

        public static void firstDungeon(Player player, Random random, Scanner console)throws FileNotFoundException, InterruptedException{
            System.out.println("You proceed down a slimey hallway, accidently stepping into puddles of sludge along the way.");
            System.out.println("As you keep walking you come across a table with a fuming glass of a green liquid.. maybe its tea..?");
            System.out.print("\n Do you drink the tea?(1) Or continue down the path?(2)");

            int choice = General.pickPath(console);

            Enemy Slime = new Enemy("Slime",null,null,false);
            if (choice == 1){
                System.out.println("You drink the fuming liquid from the mug that has \"world's best dungeon crawler\" written on it!");
                System.out.println("Seconds after you feel sick to your stomache and fall to the floor.");
                player.kill(Slime); /*Don't drink yucky juice you find laying around :< */
            }

            System.out.println("As you near a clearing you make out something shiney at the end of the corridor");
            System.out.println("A large slime blocks your path!");

            General.battle(player, Slime, random, console);
            String[] slimeDrops = {"poison potion", "dragon tooth", "health potion"};
            Slime.drop(player, random, console,slimeDrops,false,2);
            General.Continue(console);

            System.out.println("You step past the puddle the slime has become as you continue forward.");
            System.out.println("The dungeon is lit by moss mimicking moonlight as you advance.");
            System.out.println("A ragged man staring at the glowing moss stands hunched over in the middle of the room.");
            System.out.println("He notices you quickly, his eyes flash red for a moment, his hands clenched and shaking.");
            System.out.println("You stand there awkwardly for a moment unsure how to proceed!");
            System.out.print("\n Do you... talk to him about gas prices?(1) Or... Give him a high five?(2)");

            int choice1 = General.pickPath(console);

            Enemy Werewolf = new Enemy("Werewolf",null,null,false);
            String[] werewolfDrops = {"wolf blood","health potion","draconic wolf claw"};

            if (choice1 ==1) {
                System.out.println("As you blabber on about the growth of fuel costs and the state of the");
                System.out.println("dungeon economy the man looks aggravated. He begins to growl, bearing teeth and growing fur.");
                System.out.println("The man transforms into a werewolf before you as he howls!");
                General.battle(player, Werewolf, random, console);
            }

            else {
                System.out.println("You walk up and throw your hand up smiling at the shivering man, he looks up.");
                General.Continue(console);
                System.out.println("His expression lights up as he raises his hand to meet yours creating a loud echo throughout the chamber");
                System.out.println("You hear a crumbling noise above you as pebbles fall onto you both from the ceiling");
                General.Continue(console);
                System.out.println("A mossy boulder from above lands and crushes the man");
                System.out.println("A hairy hand emerges from under the boulder giving you a thumbs up");
                System.out.println("\"Poor guy\" you think to yourself.");
                General.Continue(console);
            }

            Werewolf.drop(player, random, console,werewolfDrops,false,1);
            General.Continue(console);

            System.out.println("Not losing your stride you proceed down the dungeon coming across a small fountain of blood");
            System.out.println("You take a sip from it... tastes rusty... but you feel...");
            System.out.println("Envigorated!");
            player.health += 150;

            System.out.println("You continue down the corridor");




        }
}