
package talabat_final;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


                         //------------------------------------------Karma's--------------------------------------------------------
public class Owner extends User {
    String Restaurant_name;
    Restaurant ownerrestaurant;

    static int nOwners = 0;
   Scanner scan = new Scanner(System.in);
    Scanner c = scan.useDelimiter("\n");
    
    public Owner(String name, String pw, String Restaurant_name) {
        super(name, pw);
        this.name = name;
        this.pw = pw;
        this.Restaurant_name = Restaurant_name;
    }
    public Owner() {
    }
    Scanner input = new Scanner(System.in);
    boolean Login(Owner ologin[], String loginName, String loginPW, Owner ao) {
        for (int i = 0; i < Owner.nOwners; i++) {
            if (ologin[i].name.trim().equals(loginName) && ologin[i].pw.trim().equalsIgnoreCase(loginPW)) {
                ao.name = ologin[i].name;
                ao.pw = ologin[i].pw;
                 ao.ownerrestaurant=ologin[i].ownerrestaurant;
                ao.Restaurant_name = ologin[i].Restaurant_name;
                return true;
            }
        }
        return false;
    }
    public boolean Register(Owner oR[]) throws IOException {
        boolean newUSER = true;
        System.out.println("To create a new account , please fill the folloing data");
        do {
            System.out.println("PLEASE , Type required  Restaurant name:  ");
            Restaurant_name = scan.next();
        } while (super.name_validation(Restaurant_name) == false);
        for (int i = 0; i < nOwners; i++) {
            if (oR[i].Restaurant_name.trim().equalsIgnoreCase(Restaurant_name)) {
                newUSER = false;
            }
        }
        if (newUSER == true) {
            nOwners++;
            if (nOwners < 40) {
                File Register = new File("C:\\Users\\bradl\\Documents\\NetBeansProjects\\Project\\owners.txt");
                FileWriter ow = new FileWriter(Register, true);
                do {
                    System.out.println("Enter your name: ");
                    name = input.next();

                } while (super.name_validation(name) == false);
                do {
                    System.out.println("Enter your Password [Must be 4 or more numbers] :");
                    pw = input.next();

                } while (super.PW_Validation(pw) == false);
                ow.write("\n");
                ow.write(name + "#");
                ow.write(pw + "#");
                ow.write(Restaurant_name);
                oR[nOwners - 1] = new Owner(name, pw, Restaurant_name);
                ow.close();
            }
        }
        return newUSER;
    }
    
    @Override
    void Logout( Customer activeCustomer, Owner activeOwner, String activeUSER_IS) {
        System.out.println("\n \n Successfully logged out \n"
                + "Thanks," + activeOwner.name + "for using Talabat \n  +"
                + "____________________________________________________\n\n ");
        activeOwner = null;
        activeUSER_IS = null;
       
    }
    
    
    
    
                                        // -------------------------------------HASNAA'S ---------------------------------------------
    public void displayTheOrders_toMyResturant() {
        String userName;
        String resturant_Name;
        int numOfMeals;
        String Mname[] = new String[50];
        int[] quantity = new int[50];
        String date;
        String notes;
        int orderNumber = 0;
        try {
            File ordersFile = new File("C:\\Users\\bradl\\Documents\\NetBeansProjects\\Project\\OrderFiles\\Orders.txt");
            Scanner scan = new Scanner(ordersFile);
            while (scan.hasNextLine()) {
                Scanner temp = new Scanner(scan.nextLine());
                temp.useDelimiter("#");
                userName = temp.next();
                resturant_Name = temp.next();
                if (resturant_Name.equals(Restaurant_name)) {
                    numOfMeals = temp.nextInt();
                    for (int i = 0; i < numOfMeals; i++) {
                        Mname[i] = temp.next();
                        quantity[i] = temp.nextInt();
                    }
                    date = temp.next();
                    notes = temp.next();
                    orderNumber++;
                    System.out.println("*********");
                    System.out.println("ORDER NUMBER (" + orderNumber + ") :- ");
                    System.out.println("*********");
                    System.out.println("USERNAME : " + userName);
                    System.out.println("______________________________");
                    System.out.println("RESTURANT NAME : " + resturant_Name);
                    System.out.println("______________________________");
                    for (int j = 0; j < numOfMeals; j++) {
                        System.out.println("MEAL (" + (j + 1) + ") :- ");
                        System.out.println("**************");
                        System.out.println("MEAL NAME : " + Mname[j]);
                        System.out.println("MEAL QUANTITY : " + quantity[j]);
                    }
                    System.out.println("DATE OF ORDER : " + date);
                    System.out.println("_______________________________");
                    System.out.println("NOTES : " + notes);
                    System.out.println("_______________________________");
                }
            }
            if (orderNumber == 0) {
                System.out.println("----------------");
                System.out.println("NO ORDERS YET. ");
                System.out.println("----------------");
            }
        } catch (FileNotFoundException exp) {
            System.out.println(exp.getMessage());
        }
    }
    
    //---------------------------------Mark-----------------------------------------
        public static void edit_owner_data(Owner ao) throws IOException{
          String userName;
    String filepath="C:\\Users\\souq\\Documents\\NetBeansProjects\\Mark\\owners.txt";
    String newPassword;
 
    Scanner s= new Scanner(System.in);
    
     userName=ao.name;
 
     String tempFile="temp.txt";
     
     File oldFile = new File(filepath);
     File newFile = new File(tempFile);
     String Uname="";
     String password= "";
     String Rname="";
     
          try{
       
           FileWriter fw=new FileWriter(tempFile);    
                       
         Scanner scan= new Scanner (new File(filepath));
         scan.useDelimiter("[#\n]");
         
     
          
     System.out.println("please enter the new password: ");
     newPassword=s.next();      
         while(scan.hasNext()){
             Uname=scan.next();
             password=scan.next();
             
             Rname= scan.next();
           
             
             
         if(userName.equals(Uname)){
            
            fw.write(Uname+"#"+newPassword+"#"+Rname+"\n");
         }
             else{
               
                 fw.write(Uname+"#"+password+"#"+Rname+"\n");
             }
         }
         
         scan.close();
        fw.close();
       oldFile.delete();
       File rename= new File(filepath);
       newFile.renameTo(rename);
         
        }
     catch(Exception e){
      System.out.println("error"); 
     }
     
   
     }
    
    
}