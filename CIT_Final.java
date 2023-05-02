import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

/* CIT_Final.java
 *
 * Adrian Anguiano 04/26/23
 * CIT-63 Java programming class
 * References:
 *
 * input: Reads animal information from file
 * processing: Organizes animals into habitats using Animal class
 * output: outputs data into new text file, zooPopulation.txt
 */

class Animal
{
    // member variables
    private String sex, species, season, weight, color, origin, uniqueID, name, dateOfBirth;
    private int age;
    static int hyenaCount = 0, lionCount = 0, tigerCount = 0, bearCount = 0;


    // constructors
    Animal(int age, String sex, String species, String season, String color, String weight, String origin)
    {
        // increase animal count everytime a new animal is created
        switch (species)
        {
            case "hyena" -> hyenaCount++;
            case "lion" -> lionCount++;
            case "tiger" -> tigerCount++;
            default -> bearCount++;
        }

        // initialize all variables
        this.age = age;
        this.sex = sex;
        this.species = species;
        this.season = season;
        this.color = color;
        this.weight = weight;
        this.origin = origin;

        // default initializations
        uniqueID = "An_00";
        name = "Animal";
        dateOfBirth = "00/00/0000";
    }

    // methods
    void genBirthday()
    {
        // variables
        int year = 2023 - age;
        String monthDay;

        // assigns a month and day depending on the birth season. defaults to jan 1st for unknown birth seasons
        if (season.equals("spring"))
            monthDay = "03-14";
        else if (season.equals("summer"))
            monthDay = "06-15";
        else if (season.equals("fall"))
            monthDay = "09-16";
        else if (season.equals("winter"))
            monthDay = "12-17";
        else
            monthDay = "01-01";

        // concatenates strings and saves the final dateOfBirth
        dateOfBirth = Integer.toString(year) + "-" + monthDay;
    }
    void genUniqueId()
    {
        switch (species)
        {
            case "hyena" -> uniqueID = "Hy" + hyenaCount;
            case "lion" -> uniqueID = "Li" + lionCount;
            case "tiger" -> uniqueID = "Ti" + tigerCount;
            default -> uniqueID = "Be" + bearCount;
        }
    }
    void genName()
    {
        // animal names as arrays
        String[] hyenaNames = {"Shenzi", "Banzai", "Ed", "Zig", "Bud", "Lou", "Kamari", "Wema", "Nne", "Madoa", "Prince Nevarah"},
                lionNames = {"Scar", "Mufasa", "Simba", "Kiara", "King", "Drooper", "Kimba", "Nala", "Leo", "Samson", "Elsa", "Cecil"},
                bearNames = {"Yogi", "Smokey", "Paddington", "Lippy", "Bungle", "Baloo", "Rupert", "Winnie the Pooh", "Snuggles", "Bert"},
                tigerNames = {"Tony", "Tigger", "Amber", "Cosimia", "Cuddles", "Dave", "Jiba", "Rajah", "Rayas", "Ryker"};

        // assign the animal a name
        switch (species)
        {
            case "hyena" -> name = hyenaNames[hyenaCount];
            case "lion" -> name = lionNames[lionCount];
            case "bear" -> name = bearNames[bearCount];
            default -> name = tigerNames[tigerCount];
        }
    }

    int getAge (){ return age; }
    String getSeason (){ return season; }
    String getDateOfBirth (){ return dateOfBirth; }
    String getUniqueID (){ return uniqueID; }
    String getName() { return name;}
    String getSpecies() { return species; }
    String getColor() { return color; }
    String getSex() { return sex; }
    String getWeight() { return weight; }
    String getOrigin() { return origin; }
}

public class CIT_Final
{
    public static void main(String[] args)
    {
        try {
            // open file
            Scanner scanner = new Scanner(new File("C:\\Users\\angui\\Documents\\a_code\\Java\\CIT_Final\\src\\arrivingAnimals.txt"));

            // List of habitats
            LinkedList<Animal> hyenaHabitat = new LinkedList<>();
            LinkedList<Animal> lionHabitat = new LinkedList<>();
            LinkedList<Animal> tigerHabitat = new LinkedList<>();
            LinkedList<Animal> bearHabitat = new LinkedList<>();

            while (scanner.hasNextLine()) {
                // reads a line from the input file
                String[] inputFileLine = scanner.nextLine().split(", ");

                // save animal age, sex, species, birth season, color, weight , origin
                int age = parseInt(inputFileLine[0].split(" ")[0]);
                String sex = inputFileLine[0].split(" ")[3];
                String species = inputFileLine[0].split(" ")[4];
                String season = inputFileLine[1].split(" ")[2];
                String color = inputFileLine[2];
                String weight = inputFileLine[3];
                String origin = inputFileLine[4] + ", " + inputFileLine[5];

                // create an instance of Animal class and call its methods
                Animal animal = new Animal(age, sex, species, season, color, weight, origin);
                animal.genUniqueId();
                animal.genBirthday();
                animal.genName();

                // use animal species to assign it a habitat
                String currSpecies = animal.getSpecies();

                switch (currSpecies)
                {
                    case "hyena" -> hyenaHabitat.add(animal);
                    case "lion" -> lionHabitat.add(animal);
                    case "tiger" -> tigerHabitat.add(animal);
                    default -> bearHabitat.add(animal);
                }
            }

            // output final habitats into zooPopulation.txt
            try
            {
                FileWriter file = new FileWriter("C:\\Users\\angui\\Documents\\a_code\\Java\\CIT_Final\\src\\zooPopulation.txt");
                BufferedWriter output = new BufferedWriter(file);

                // output hyena habitat
                output.write("Hyena Habitat:\n");
                for (int i = 0; i < 4; i++)
                {
                    output.write(hyenaHabitat.get(i).getUniqueID() + "; " +
                        hyenaHabitat.get(i).getName() + "; " +
                        hyenaHabitat.get(i).getAge() + " years old; " +
                        hyenaHabitat.get(i).getDateOfBirth() + "; " +
                        hyenaHabitat.get(i).getColor() + "; " +
                        hyenaHabitat.get(i).getSex() + "; " +
                        hyenaHabitat.get(i).getWeight() + "; " +
                        hyenaHabitat.get(i).getOrigin());
                    output.write("\n");
                }

                output.write("\n");

                // output lion habitat
                output.write("Lion Habitat:\n");
                for (int i = 0; i < 4; i++)
                {
                    output.write(lionHabitat.get(i).getUniqueID() + "; " +
                            lionHabitat.get(i).getName() + "; " +
                            lionHabitat.get(i).getAge() + " years old; " +
                            lionHabitat.get(i).getDateOfBirth() + "; " +
                            lionHabitat.get(i).getColor() + "; " +
                            lionHabitat.get(i).getSex() + "; " +
                            lionHabitat.get(i).getWeight() + "; " +
                            lionHabitat.get(i).getOrigin());
                    output.write("\n");
                }

                output.write("\n");

                // output tiger habitat
                output.write("Tiger Habitat:\n");
                for (int i = 0; i < 4; i++)
                {
                    output.write(tigerHabitat.get(i).getUniqueID() + "; " +
                            tigerHabitat.get(i).getName() + "; " +
                            tigerHabitat.get(i).getAge() + " years old; " +
                            tigerHabitat.get(i).getDateOfBirth() + "; " +
                            tigerHabitat.get(i).getColor() + "; " +
                            tigerHabitat.get(i).getSex() + "; " +
                            tigerHabitat.get(i).getWeight() + "; " +
                            tigerHabitat.get(i).getOrigin());
                    output.write("\n");
                }

                output.write("\n");

                // output bears habitat
                output.write("Bear Habitat:\n");
                for (int i = 0; i < 4; i++)
                {
                    output.write(bearHabitat.get(i).getUniqueID() + "; " +
                            bearHabitat.get(i).getName() + "; " +
                            bearHabitat.get(i).getAge() + " years old; " +
                            bearHabitat.get(i).getDateOfBirth() + "; " +
                            bearHabitat.get(i).getColor() + "; " +
                            bearHabitat.get(i).getSex() + "; " +
                            bearHabitat.get(i).getWeight() + "; " +
                            bearHabitat.get(i).getOrigin());
                    output.write("\n");
                }
                output.close();
            }
            catch (Exception e)
            {
                e.getStackTrace();
            }

            // close file
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
        }
    }
}