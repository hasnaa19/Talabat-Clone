package talabat_final;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Restaurant {

    String Rname;
    Order[] orders = new Order[20];
    Meals[] RM = new Meals[20];
    int Mealsize = 0;
    int ordersize = 0;
    int offerssize = 0;

    public Restaurant(String Rname) {
        this.Rname = Rname;
    }

    public Restaurant() {
    }
    //---------------------------------------------------------HASNAA'S ---------------------------------------------------------//

    void add_meal() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of meals you want to add:- ");
        int number;
        while (true) {
            number = input.nextInt();
            if (number <= 0) {
                System.out.println("Invalid value, please re-enter the number of meals you want to add.");
                continue;
            } else {
                break;
            }
        }
        int counter = 0;
        String name, description;
        String price;
        for (int i = Mealsize; i < Mealsize + number; i++) {
            if (number > 1) {
                System.out.println("Data For Meal Number " + (counter + 1) + " :- ");
                System.out.println("**************************************");
            }
            System.out.println("Meal Name :- ");
            System.out.println("--------------");
            while (true) {
                boolean found = false;
                name = input.next();
                name += input.nextLine();
                for (int j = 0; j < Mealsize + counter; j++) {
                    if (RM[j].name.equals(name)) {
                        found = true;
                        break;
                    }
                }
                if (found == true) {
                    System.out.println("This meal already exists, Please re-enter the name of the meal");
                    continue;
                } else {
                    break;
                }
            }
            System.out.println("Meal Price :- ");
            System.out.println("----------------");
            while (true) {
                boolean thereIs_aChar = false;
                price = input.next();
                for (int j = 0; j < price.length(); j++) {
                    if (!Character.isDigit(price.charAt(j))) {
                        thereIs_aChar = true;
                    }
                }
                if (Integer.parseInt(price) <= 0 || thereIs_aChar == true) {
                    System.out.println("Invalid price, Please enter a valid value. ");
                    continue;
                } else {
                    break;
                }
            }
            System.out.println("Meal Descripition :- ");
            System.out.println("---------------------");
            description = input.next();
            description += input.nextLine();
            RM[i] = new Meals(name, price, description);
            add_meal_toFile(name, price, description);
            System.out.println("__________MEAL'VE BEEN ADDED SUCCESSFULLY__________");
            counter++;
        }
        Mealsize += number;
    }

    void add_meal_toFile(String name, String price, String description) {
        try {
            FileWriter fw = new FileWriter("C:\\Users\\bradl\\Documents\\NetBeansProjects\\Project\\Restaurants.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(Rname + "#" + name + "#" + price + "#" + description);
            pw.flush();
            pw.close();
        } catch (IOException exp) {
            System.out.println(exp.getMessage());
        }
    }

    //---------------------------------------------------Yousra's------------------------------------------------------
    public static void editMeals( Owner oa) {
      
         
        
        String oldMealName;
        String filepath = "C:\\Users\\karma\\Downloads\\Restaurants.txt";
        String newName;
        String Price;
        String discription;
        String RestaurantName = null;
        Scanner s = new Scanner(System.in);
        int choice = 0;
        System.out.println("please enter the name of meal you want to edit: ");
        
        oldMealName = s.nextLine();
        System.out.println("\t \t EDIT\n"
                + "1_Meal Name \n"
                + "2_Meal Price \n"
                + "3_Meal Description \n *type [1/ 2/ 3]");
        choice = s.nextInt();

        String tempFile = "temp.txt";

        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        String Rname = "";
        String NEWNAME = "";
        String PRICE = "";
        String DISCRIPTION = "";

        try {

            FileWriter fw = new FileWriter(tempFile);

            Scanner scan = new Scanner(new File(filepath));
            scan.useDelimiter("[#\n]");

            if (choice == 1) {

                System.out.println("please enter the new name of the meal: ");
                newName = s.next();
                while (scan.hasNext()) {
                    Rname = scan.next();
                    NEWNAME = scan.next();

                    PRICE = scan.next();

                    DISCRIPTION = scan.next();

                    if (NEWNAME.equalsIgnoreCase(oldMealName)) {

                        fw.write(Rname + "#" + newName + "#" + PRICE + "#" + DISCRIPTION + "\n");
                    } else {

                        fw.write(Rname + "#" + NEWNAME + "#" + PRICE + "#" + DISCRIPTION + "\n");
                    }
                }

            } else if (choice == 2) {
                System.out.println("please enter the new price of the meal: ");
                Price = s.next();
                while (scan.hasNext()) {
                    Rname = scan.next();
                    NEWNAME = scan.next();

                    PRICE = scan.next();

                    DISCRIPTION = scan.next();

                    if (NEWNAME.equalsIgnoreCase(oldMealName)) {

                        fw.write(Rname + "#" + NEWNAME + "#" + Price + "#" + DISCRIPTION + "\n");
                    } else {

                        fw.write(Rname + "#" + NEWNAME + "#" + PRICE + "#" + DISCRIPTION + "\n");
                    }
                }

            } else if (choice == 3) {

                System.out.println("please enter the new discription: ");
                discription = s.next();
                while (scan.hasNext()) {
                    Rname = scan.next();
                    NEWNAME = scan.next();

                    PRICE = scan.next();

                    DISCRIPTION = scan.next();

                    if (NEWNAME.equalsIgnoreCase(oldMealName)) {

                        fw.write(Rname + "#" + NEWNAME + "#" + PRICE + "#" + discription + "\n");
                    } else {

                        fw.write(Rname + "#" + NEWNAME + "#" + PRICE + "#" + DISCRIPTION + "\n");
                    }
                }

            }

            scan.close();
            fw.close();
            oldFile.delete();
            File rename = new File(filepath);
            newFile.renameTo(rename);

        } catch (Exception e) {
            System.out.println("Can't open file");
        }
    }
    //--------------------------------------------------Fatma's-------------------------------------------
    
    public void DeleteMeal() throws IOException {
    		Scanner strInput =  new Scanner(System.in);
    		String ID, record;
    		
    		
    		File tempDB = new File("temp.txt");
    		File db = new File("C:\\Users\\karma\\Downloads\\Restaurants.txt");
    		
    		
    		BufferedReader br = new BufferedReader( new FileReader( db ) );
    		BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) );
    		
    		
    		System.out.println(" Delete meal ");
    		
    		System.out.println("Enter the meal: ");
    		ID =  strInput.next();
    		
    		
    		while( ( record = br.readLine() ) != null ) {
    			
    			
    			if( record.contains(ID) ) 
    				continue;
   
    			bw.write(record);
    			bw.flush();
    			bw.newLine();
 
    		}
    		
    		br.close();
    		bw.close();
    		
    		db.delete();
    		
    		tempDB.renameTo(db);
 
    }
}
