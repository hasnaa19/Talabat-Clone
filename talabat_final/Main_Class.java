package talabat_final;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main_Class {

    public static void main(String[] args) throws IOException {
        // ----------------------------------Karma's-------------------------------------

        Scanner input = new Scanner(System.in);

//Declaring arrays and filling them from text files
        ReadingFiles R = new ReadingFiles();

        Customer Customers_data[] = new Customer[20];
        R.ReadCustomer(Customers_data);

        Owner Owners_data[] = new Owner[20];
        R.ReadOwner(Owners_data);
       // R.ReadOrders(Customers_data, Owners_data);
        R.ReadRestaurant(Owners_data);

        char goBACK = 'L';

        while (goBACK == 'L' || goBACK == 'l') {

            String activeUSER_IS = null;

            Customer activeCustomer = new Customer();
            Owner activeOwner = new Owner();

            int mainCHOICE = 0;
            System.out.println("\t \t \t \t \t \t Welcome to Talabat \n\n"
                    + "1.Login\n"
                    + "2.Register\n"
                    + "Type your choice in the form of number [1/2] \n");
            mainCHOICE = input.nextInt();

            if (mainCHOICE == 1) {
                char login = 'y';
                boolean isA_member = false;

                while (login == 'y') {

                    System.out.println("Enter your name: ");
                    String user_name = input.next();
                    System.out.println("Enter your password: ");
                    String password = input.next();

                    if (activeCustomer.Login(Customers_data, user_name, password, activeCustomer) == true) {
                        activeUSER_IS = "Customer";
                        login = 'n';
                        isA_member = true;
                        System.out.println("\t \t Successfully logged in");
                    } else if (activeOwner.Login(Owners_data, user_name, password, activeOwner) == true) {
                        activeUSER_IS = "Owner";
                        login = 'n';
                        isA_member = true;
                        System.out.println("\t \t Successfully logged in");
                    }

                    if (isA_member == false) {
                        System.out.println("Password or User name maybe incorrect\n"
                                + "would you like to try again[y/n]");
                        login = input.next().charAt(0);
                        if (login != 'y') {
                            System.out.println("Type L to go back to main menu\n"
                                    + "Or E to Exit");
                            goBACK = input.next().charAt(0);
                        }
                    }

                }

            } else if (mainCHOICE == 2) {
                char register = 'y';
                System.out.println("1.Register as a Customer\n"
                        + "2.Register as an Owner\n"
                        + "Type your choice in the form of numbers[1/2]");
                int registerCHOICE = input.nextInt();

                //As a customer
                if (registerCHOICE == 1) {

                    do {
                        if (activeCustomer.Register(Customers_data) == true) {

                            System.out.println("\t \t Successfully registered");
                            activeUSER_IS = "Customer";
                            register = 'n';

                        } else {
                            System.out.println("Invalid username, Would you like to try again?[y/n]");
                            register = input.next().charAt(0);
                            if (register != 'y') {
                                System.out.println("Type L to go back to main menu \n"
                                        + "Or E to Exit");
                                goBACK = input.next().charAt(0);
                            }
                        }
                    } while (register == 'y');
//As an owner
                } else if (registerCHOICE == 2) {
                    do {
                        if (activeOwner.Register(Owners_data) == true) {
                            System.out.println("\t \t Successfully registered");

                            activeUSER_IS = "Owner";
                            register = 'n';
                        } else {
                            System.out.println("Invalid Restaurant name, Would you like to try again?[y/n]");
                            register = input.next().charAt(0);
                            if (register != 'y') {
                                System.out.println("Type E to go back to main menu");
                                goBACK = input.next().charAt(0);
                            }
                        }
                    } while (register == 'y');
                }

            } else {
                System.out.println("Invailed entry, Please type 1 or 2");
                goBACK = 'l';
            }

            goBACK = 'H';
            int homeCHOICE = 0;
            while (goBACK == 'H' || goBACK == 'h') {
                if ("Customer".equals(activeUSER_IS)) {

                    System.out.println("Hello, " + activeCustomer.name
                            + "\n 1.Edit MY_Info \n"
                            + "2.Restaurants \n"
                            + "3.Make an Order\n"
                            + "4.Browse my orders\n"
                            + "Type your choice in the form of number [1/2/3/4]");

                    homeCHOICE = input.nextInt();

                    if (homeCHOICE == 1) {
                        Customer.Customerdata_edit(activeCustomer);
                        R.ReadCustomer(Customers_data);

                        System.out.println("Type H to go back to Home\n"
                                + "Type L to logout\n"
                                + "Or E to Exit");
                        goBACK = input.next().charAt(0);
                        if (goBACK == 'L' || goBACK == 'l') {
                            activeCustomer.Logout(activeCustomer, activeOwner, activeUSER_IS);
                        }
                    } else if (homeCHOICE == 2) {
                        activeCustomer.Browse_meals(Owners_data);
                        //************Fatma's Code*******************
                        System.out.println("Type H to go back to Home\n"
                                + "Type L to logout\n"
                                + "Or E to Exit");
                        goBACK = input.next().charAt(0);
                        if (goBACK == 'L' || goBACK == 'l') {
                            activeCustomer.Logout(activeCustomer, activeOwner, activeUSER_IS);
                        }
                    } else if (homeCHOICE == 3) {
                      //  activeCustomer.MakeAnOrder(activeCustomer, Owners_data, Customers_data);
                        System.out.println("Type H to go back to Home\n"
                                + "Type L to logout\n"
                                + "Or E to Exit");
                        goBACK = input.next().charAt(0);
                        if (goBACK == 'L' || goBACK == 'l') {
                            activeCustomer.Logout(activeCustomer, activeOwner, activeUSER_IS);
                        }

                    } else if (homeCHOICE == 4) {
                        activeCustomer.BrowseCustomerOrders(activeCustomer, Customers_data);
                        System.out.println("Type H to go back to Home\n"
                                + "Type L to logout\n"
                                + "Or E to Exit");
                        goBACK = input.next().charAt(0);
                        if (goBACK == 'L' || goBACK == 'l') {
                            activeCustomer.Logout(activeCustomer, activeOwner, activeUSER_IS);
                        }
                    } else {
                        System.out.println("Invalid entry. Please Type a number from 1 to 4");
                        System.out.println("Type H to go back to Home\n"
                                + "Type L to logout\n"
                                + "Or E to Exit");
                        goBACK = input.next().charAt(0);
                    }

                } else if ("Owner".equals(activeUSER_IS)) {
                    System.out.println("Hello, " + activeOwner.name
                            + " Owner of Restaurant: " + activeOwner.Restaurant_name
                            + "\n Display all Resturant meals\n"
                            + "1.Add meal \n"
                            + "2.Edit Meal\n"
                            + "3.Delete Meal\n"
                            + "4.Browse orders\n"
                            + "5.Edit my profile\n"
                            + "+ \"Type your choice in the form of number [1/2/3/4]");
                    homeCHOICE = input.nextInt();
                    if (homeCHOICE == 1) {
                        activeOwner.ownerrestaurant.add_meal();

                        System.out.println("Type H to go back to Home\n"
                                + "Type L to logout\n"
                                + "Or E to Exit");
                        goBACK = input.next().charAt(0);

                        if (goBACK == 'L' || goBACK == 'l') {
                            activeCustomer.Logout(activeCustomer, activeOwner, activeUSER_IS);
                        }
                    } else if (homeCHOICE == 2) {

                        Restaurant.editMeals( activeOwner);
                        R.ReadRestaurant(Owners_data);//to refill the array after the change

                        System.out.println("Type H to go back to Home\n"
                                + "Type L to logout\n"
                                + "Or E to Exit");
                        goBACK = input.next().charAt(0);

                        if (goBACK == 'L' || goBACK == 'l') {
                            activeCustomer.Logout(activeCustomer, activeOwner, activeUSER_IS);
                        }

                    } else if (homeCHOICE == 3) {

                        //Delete --Fatma--
                        try {
                            Restaurant am = new Restaurant();
                            am.DeleteMeal();
                        } catch (IOException ex) {
                            Logger.getLogger(Restaurant.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        Scanner in = new Scanner(System.in);
                        char choice;

                        for (int i = 0; i < 30; i++) {

                            System.out.println("Do you want to delete another meal ? (y/n)");
                            choice = in.next().charAt(0);

                            if (choice == 'y') {

                                try {
                                    Restaurant am = new Restaurant();
                                    am.DeleteMeal();
                                } catch (IOException ex) {
                                    Logger.getLogger(Main_Class.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            } else {
                                break;
                            }

                        }

                        R.ReadRestaurant(Owners_data);
                        System.out.println("Type H to go back to Home\n"
                                + "Type L to logout\n"
                                + "Or E to Exit");
                        goBACK = input.next().charAt(0);

                        if (goBACK == 'L' || goBACK == 'l') {
                            activeCustomer.Logout(activeCustomer, activeOwner, activeUSER_IS);
                        }
                    } else if (homeCHOICE == 4) {
                        activeOwner.displayTheOrders_toMyResturant();

                        System.out.println("Type H to go back to Home\n"
                                + "Type L to logout\n"
                                + "Or E to Exit");
                        goBACK = input.next().charAt(0);

                        if (goBACK == 'L' || goBACK == 'l') {
                            activeCustomer.Logout(activeCustomer, activeOwner, activeUSER_IS);
                        }

                    } else if (homeCHOICE == 5) {
                        activeOwner.edit_owner_data(activeOwner);
                        R.ReadOwner(Owners_data);
                    } else {
                        System.out.println("Invalid entry. Please Type a number from 1 to 4");
                        System.out.println("Type H to go back to Home\n"
                                + "Type L to logout\n"
                                + "Or E to Exit");
                        goBACK = input.next().charAt(0);
                    }

                }
            }
        }

    }

}
