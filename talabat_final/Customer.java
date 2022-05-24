package talabat_final;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

//-------------------------------Omar's-------------------------------------------------
public class Customer extends User {

    Scanner input = new Scanner(System.in);
    Scanner scan = new Scanner(System.in);
    Scanner c = scan.useDelimiter("\n");
    String CHOOSE;
    String Mobile_number;
    String Address;
    Order[] Corders = new Order[20];
    static int Csize = 0;
    int orderssize = 0;

    public Customer(String Username, String Password) {
        super(Username, Password);
    }

    public Customer(String name, String pw, String Mobile_number, String Address) {
        super(name, pw);
        this.Mobile_number = Mobile_number;
        this.Address = Address;

    }

    public Customer() {
        super();
    }

    public boolean Login(Customer clogin[], String loginName, String loginPW, Customer ac) {
        Boolean check = false;
        int flag = 0;

        for (int i = 0; i < Customer.Csize; i++) {

            if (clogin[i].name.trim().equalsIgnoreCase(loginName) && clogin[i].pw.trim().equalsIgnoreCase(loginPW)) {
                check = true;
                flag = i;
                break;

            }
        }
        if (check == true) {
            ac.name = clogin[flag].name;

            ac.pw = clogin[flag].pw;
            ac.Address = clogin[flag].Address;
            ac.Mobile_number = clogin[flag].Mobile_number;
            return true;

        } else {
            return false;
        }
    }
//----------------------------Karma's-----------------------------------
    public boolean Register(Customer cR[]) throws IOException {
        boolean newUSER = true;
        System.out.println("To create a new account , please fill the folloing data");
        do {
            System.out.println("Enter your name: ");
            name = input.next();
        } while (super.name_validation(name) == false);

        for (int i = 0; i < Csize; i++) {
            if (cR[i].name.trim().equalsIgnoreCase(name) || name.isEmpty()) {

                newUSER = false;
            }
        }

        if (newUSER == true) {
            Csize++;
            if (Csize < 40) {

                File Register = new File("C:\\Users\\bradl\\Documents\\NetBeansProjects\\Project\\customerFile\\customers.txt");

                FileWriter rw = new FileWriter(Register, true);
                rw.write("\n");
                rw.write(name + "#");

                do {

                    System.out.println("Enter your password [Must be at least 4 numbers] :");
                    pw = input.next();
//                    rw.write(pw + "#");

                } while (super.PW_Validation(pw) == false || super.isEmpty(pw));

                do {
                    System.out.println("Enter your Phone number: ");
                    Mobile_number = input.next();
//                    rw.write(Mobile_number + "#");

                } while (super.MobileNo_Validation(Mobile_number) == false || super.isEmpty(Mobile_number));

                do {
                    System.out.println("Enter your address: ");
                    Address = scan.next();
//                    rw.write(Address + "#" + "\n");

                } while (super.isEmpty(Address));
                rw.write(pw + "#");
                rw.write(Mobile_number + "#");
                rw.write(Address);

                cR[Csize - 1] = new Customer(name, pw, Mobile_number, Address);

                rw.close();

            }
        }

        return newUSER;
    }
//-------------------------karma------------------------
    void Logout(Customer activeCustomer, Owner activeOwner, String activeUSER_IS) {

        System.out.println("\n \n Successfully logged out \n"
                + "Thanks," + activeCustomer.name + "for using Talabat \n  +"
                + "__________________\n\n ");
        activeCustomer = null;
        activeOwner = null;
        activeUSER_IS = null;

    }
//--------------------Fatma's----------------------
    public void Browse_meals(Owner[] o) {

        System.out.println("Available Restaurants : ");

        for (int i = 0; i < Owner.nOwners; i++) {
            System.out.println((i + 1) + "-" + o[i].Restaurant_name);
        }

        System.out.println("Choose a restaurant : ");

        Scanner in = new Scanner(System.in);

        int Rnumber = in.nextInt();
        System.out.println("Meals:");

        for (int j = 0; j < o[Rnumber - 1].ownerrestaurant.Mealsize; j++) {
            System.out.println((j + 1) + "-" + o[Rnumber - 1].ownerrestaurant.RM[j].name);
        }

    }
//-----------------------Omar's-----------------------
    /*
    public void MakeAnOrder(Customer ActiveC, Owner[] Owners_data, Customer[] Customers_data) throws IOException {
        File MAKEO = new File("C:\\Users\\bradl\\Documents\\NetBeansProjects\\Project\\OrderFiles\\Orders.txt");
        FileWriter make = new FileWriter(MAKEO, true);
        int RChoice;
        int MChoice;
        int QUANTITY;
        int DIFFMEALS = 0;
        LocalDate date = java.time.LocalDate.now();

        for (int i = 0; i < Customer.Csize; i++) {
            if (ActiveC.name.equals(Customers_data[i].name)) {

                make.write("\n");
                make.write(Customers_data[i].name + "#");
                while (true) {
                    System.out.println("Choose a Restaurant:");
                    for (int j = 0; j < Owner.nOwners; j++) {
                        System.out.println((j + 1) + "-" + Owners_data[j].ownerrestaurant.Rname);
                    }
                    RChoice = input.nextInt();
                    if (RChoice > (Owner.nOwners)) {
                        System.out.println("---------> Invalid entry,Please enter a valid number <---------");
                    } else {
                        break;
                    }
                }
                make.write(Owners_data[RChoice - 1].ownerrestaurant.Rname + "#");

                Owners_data[RChoice - 1].ownerrestaurant.ordersize++;
                Owners_data[RChoice - 1].ownerrestaurant.orders[(Owners_data[RChoice - 1].ownerrestaurant.ordersize) - 1] = new Order();
                Customers_data[i].orderssize++;
                Customers_data[i].Corders[(Customers_data[i].orderssize) - 1] = new Order(Owners_data[RChoice - 1].ownerrestaurant.Rname);

                while (true) {
                    while (true) {
                        System.out.println("Choose a Meal:");
                        for (int j = 0; j < Owners_data[RChoice - 1].ownerrestaurant.Mealsize; j++) {
                            System.out.println((j + 1) + "-" + Owners_data[RChoice - 1].ownerrestaurant.RM[j].name);
                        }
                        MChoice = input.nextInt();
                        if (MChoice > Owners_data[RChoice - 1].ownerrestaurant.Mealsize) {
                            System.out.println("---------> Invalid entry,Please enter a valid number <---------");
                        } else {
                            break;
                        }
                    }
                    DIFFMEALS++;
                    Owners_data[RChoice - 1].ownerrestaurant.orders[(Owners_data[RChoice - 1].ownerrestaurant.ordersize) - 1].M[(Owners_data[RChoice - 1].ownerrestaurant.orders[(Owners_data[RChoice - 1].ownerrestaurant.ordersize) - 1].mealsize)] = Owners_data[RChoice - 1].ownerrestaurant.RM[MChoice - 1];
                    Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].M[(Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].mealsize)] = Owners_data[RChoice - 1].ownerrestaurant.RM[MChoice - 1];
                    System.out.println("Enter desired quantity of this meal:");
                    QUANTITY = input.nextInt();
                    Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].M[(Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].mealsize)].quantity = QUANTITY;
                    for (int j = 0; j < QUANTITY - 1; j++) {
                        Owners_data[RChoice - 1].ownerrestaurant.orders[(Owners_data[RChoice - 1].ownerrestaurant.ordersize) - 1].mealsize++;
                        Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].mealsize++;
                        Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].M[(Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].mealsize)] = Owners_data[RChoice - 1].ownerrestaurant.RM[MChoice - 1];
                        Owners_data[RChoice - 1].ownerrestaurant.orders[(Owners_data[RChoice - 1].ownerrestaurant.ordersize) - 1].M[(Owners_data[RChoice - 1].ownerrestaurant.orders[(Owners_data[RChoice - 1].ownerrestaurant.ordersize) - 1].mealsize)] = Owners_data[RChoice - 1].ownerrestaurant.RM[MChoice - 1];

                    }
                    while (true) {
                        System.out.println("Do u want to choose other meals? ");
                        CHOOSE = input.next();
                        if (CHOOSE.equalsIgnoreCase("n") || CHOOSE.equalsIgnoreCase("y")) {
                            break;
                        } else {
                            System.out.println("---------> Invalid entry,Please enter y or n <---------");
                        }
                    }
                    if (CHOOSE.equalsIgnoreCase("n")) {
                        Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].mealsize++;
                        Owners_data[RChoice - 1].ownerrestaurant.orders[(Owners_data[RChoice - 1].ownerrestaurant.ordersize) - 1].mealsize++;
                        break;
                    } else if (CHOOSE.equalsIgnoreCase("y")) {
                        Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].mealsize++;
                        Owners_data[RChoice - 1].ownerrestaurant.orders[(Owners_data[RChoice - 1].ownerrestaurant.ordersize) - 1].mealsize++;
                        continue;
                    }

                }

                make.write(DIFFMEALS + "#");
                make.write(Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].M[0].name + "#");
                make.write(Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].M[0].quantity + "#");

                String Dummyname = Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].M[0].name;
                for (int j = 0; j < Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].mealsize; j++) {
                    if (Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].M[j].name.equals(Dummyname)) {
                        continue;
                    } else {
                        Dummyname = Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].M[j].name;
                        make.write(Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].M[j].name + "#");
                        make.write(Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].M[j].quantity + "#");
                    }
                }

//              for (int j = 0; j < Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].mealsize; j++) {
//                  System.out.println("Restaurant:" + Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].Restaurantname + "\n" + Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].M[j].name);
//                  System.out.println(R[RChoice - 1].orders[(R[RChoice - 1].ordersize) - 1].M[j].name);
//              }
//              LocalDate date = java.time.LocalDate.now();
                Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].date = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                make.write(Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].date + "#");
                while (true) {
                    System.out.println("Do u want to add some notes?");
                    CHOOSE = input.next();
                    if (CHOOSE.equalsIgnoreCase("n") || CHOOSE.equalsIgnoreCase("y")) {
                        break;
                    } else {
                        System.out.println("---------> Invalid entry,Please enter y or n <---------");
                    }
                }
                if (CHOOSE.equalsIgnoreCase("y")) {
                    Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].notes = scan.next();

                } else if (CHOOSE.equalsIgnoreCase("n")) {
                    Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].notes = "No notes are found";
                }
                make.write(Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].notes);

//              System.out.println(Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].notes);
                Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].price = Customers_data[i].Corders[(Customers_data[i].orderssize) - 1].orderprice();
            }
        }
        make.close();
    }
*/
    public void BrowseCustomerOrders(Customer ActiveC, Customer[] Customers_data) {
        for (int i = 0; i < Customer.Csize; i++) {

            if (ActiveC.name.equals(Customers_data[i].name)) {
                if (Customers_data[i].orderssize == 0) {
                    System.out.println("You have no orders yet");
                    break;
                }
                for (int j = 0; j < Customers_data[i].orderssize; j++) {
//                  if (Customers_data[i].Corders[j] == null) {
//                      System.out.println("You have no orders yet");
//                      break;
//                  }
                    System.out.println("Your Previous Orders:");
                    System.out.println("Order number:" + (j + 1));
                    System.out.println("Ordered from:" + Customers_data[i].Corders[j].Restaurantname);
                    for (int k = 0; k < Customers_data[i].Corders[j].mealsize; k++) {
                        System.out.println(Customers_data[i].Corders[j].M[k].name);
                    }
                    System.out.println("Order's date:" + Customers_data[i].Corders[j].date);
                    System.out.println("Order's notes:" + Customers_data[i].Corders[j].notes);
                    System.out.println("Order's price:" + Customers_data[i].Corders[j].price);

                    System.out.println("---------------------------");

                }
            }
        }
    }
    //------------------------------------------Mark--------------------------------------
 public static void Customerdata_edit(Customer ac) {
        String userName;
        String filepath = "C:\\Users\\karma\\Downloads\\customers.txt";
        String newPassword;
        String newPhone_no;
        String newAdress;

        Scanner s = new Scanner(System.in);
        int choice = 0;
        
        userName = ac.name;
        System.out.println("\t \t EDIT\n"
                + "1_Password \n"
                + "2_phone number \n"
                + "3_Adress \n *type [1/ 2/ 3]");
        choice = s.nextInt();

        String tempFile = "temp.txt";

        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        String Uname = "";
        String password = "";
        String phone_no = "";
        String adress = "";

        try {

            FileWriter fw = new FileWriter(tempFile);

            Scanner scan = new Scanner(new File(filepath));
            scan.useDelimiter("[#\n]");

            if (choice == 1) {

                System.out.println("please enter the new password: ");
                newPassword = s.next();
                while (scan.hasNext()) {
                    Uname = scan.next();
                    password = scan.next();

                    phone_no = scan.next();

                    adress = scan.next();

                    if (userName.equals(Uname)) {

                        fw.write(Uname + "#" + newPassword + "#" + phone_no + "#" + adress + "\n");
                    } else {

                        fw.write(Uname + "#" + password + "#" + phone_no + "#" + adress + "\n");
                    }
                }

            } else if (choice == 2) {
                System.out.println("please enter the phone number: ");
                newPhone_no = s.next();
                while (scan.hasNext()) {
                    Uname = scan.next();
                    password = scan.next();

                    phone_no = scan.next();

                    adress = scan.next();

                    if (userName.equals(Uname)) {

                        fw.write(Uname + "#" + password + "#" + newPhone_no + "#" + adress + "\n");
                    } else {

                        fw.write(Uname + "#" + password + "#" + phone_no + "#" + adress + "\n");
                    }
                }

            } else if (choice == 3) {

                System.out.println("please enter the new adress: ");
                newAdress = s.next();
                while (scan.hasNext()) {
                    Uname = scan.next();
                    password = scan.next();

                    phone_no = scan.next();

                    adress = scan.next();

                    if (userName.equals(Uname)) {

                        fw.write(Uname + "#" + password + "#" + phone_no + "#" + newAdress + "\n");
                    } else {

                        fw.write(Uname + "#" + password + "#" + phone_no + "#" + adress + "\n");
                    }
                }

            }

            scan.close();
            fw.close();
            oldFile.delete();
            File rename = new File(filepath);
            newFile.renameTo(rename);

        } catch (Exception e) {
            System.out.println("error");
        }
 }
}
    

