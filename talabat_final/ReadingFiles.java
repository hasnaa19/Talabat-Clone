
package talabat_final;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//------------------------------Yousra's----------------------------------
public class ReadingFiles {
    
    void ReadCustomer(Customer C[]){
         String name, pw, mn, address;
        try {
            
            File CFile;
            CFile = new File("C:\\Users\\karma\\Downloads\\customers.txt");
            
            try (Scanner user_reader = new Scanner(CFile)) {
                while (user_reader.hasNext()) {
                    Scanner customer_reader = new Scanner(user_reader.nextLine());
                    
                    customer_reader.useDelimiter("#");
                    
                    name = customer_reader.next();
                    pw = customer_reader.next();
                    mn = customer_reader.next();
                    address = customer_reader.next();
                    C[Customer.Csize] = new Customer(name, pw, mn, address);
                    Customer.Csize++;
                }
            }
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
        }
    }
    
    
    void ReadOwner(Owner O[]){
         String RESTName,name,pw;
        try {
            
            File OwnerFile;
            OwnerFile = new File("C:\\Users\\karma\\Downloads\\owners.txt");
            
            try (Scanner user_reader = new Scanner(OwnerFile)) {
                while (user_reader.hasNext()) {
                    Scanner owner_reader = new Scanner(user_reader.nextLine());
                    
                    owner_reader.useDelimiter("#");
                    
                    name = owner_reader.next();
                    pw = owner_reader.next();
                    RESTName = owner_reader.next();
                    O[Owner.nOwners] = new Owner(name, pw, RESTName);
                    O[Owner.nOwners].ownerrestaurant = new Restaurant(RESTName);
                    Owner.nOwners++;
                }
            }
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
        }
    }
    
    void ReadRestaurant(Owner O[]){
        String Mealname, Mealdescription,RESTName;
        String Mealprice;
        try {
            File RFile = new File("C:\\Users\\karma\\Downloads\\Restaurants.txt");
            
            try (Scanner user_reader = new Scanner(RFile)) {
                while (user_reader.hasNext()) {
                    Scanner restaurant_reader = new Scanner(user_reader.nextLine());
                    restaurant_reader.useDelimiter("#");
                    RESTName = restaurant_reader.next();
                    Mealname = restaurant_reader.next();
                    Mealprice = restaurant_reader.next();
                    Mealdescription = restaurant_reader.next();
                    for (int i = 0; i < Owner.nOwners; i++) {
                        if (O[i].Restaurant_name.equals(RESTName)) {
                            O[i].ownerrestaurant.RM[O[i].ownerrestaurant.Mealsize] = new Meals();
                            O[i].ownerrestaurant.RM[O[i].ownerrestaurant.Mealsize].name = Mealname;
                            O[i].ownerrestaurant.RM[O[i].ownerrestaurant.Mealsize].price = Mealprice;
                            O[i].ownerrestaurant.RM[O[i].ownerrestaurant.Mealsize].description = Mealdescription;
                            O[i].ownerrestaurant.Mealsize++;
                            break;
                        }
                    }
                }
            }
            
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
        }
    }
/*
    void ReadOrders(Customer C[],Owner O[]){
             int DiiferentMealsQuantity;
        int SameMealQuantity;
        String MEALNAME,name;
        try {
            
            File OrderFile;
            OrderFile = new File("C:\\Users\\karma\\Downloads\\orders.txt");
            
            try (Scanner user_reader = new Scanner(OrderFile)) {
                while (user_reader.hasNext()) {
                    Scanner Order_reader = new Scanner(user_reader.nextLine());
                    
                    Order_reader.useDelimiter("#");
                    name = Order_reader.next();
                    for (int i = 0; i < Customer.Csize; i++) {
                        if (name.equals(C[i].name)) {
                            C[i].Corders[C[i].orderssize] = new Order();
                            C[i].Corders[C[i].orderssize].Restaurantname = Order_reader.next();
                            DiiferentMealsQuantity = Order_reader.nextInt();
                            for (int j = 0; j < DiiferentMealsQuantity; j++) {
                                MEALNAME = Order_reader.next();
                                int dummy = C[i].Corders[C[i].orderssize].mealsize;
                                C[i].Corders[C[i].orderssize].mealsize += Order_reader.nextInt();
                                for (int k = dummy; k < C[i].Corders[C[i].orderssize].mealsize; k++) {
                                    C[i].Corders[C[i].orderssize].M[k] = new Meals();
                                    C[i].Corders[C[i].orderssize].M[k].name = MEALNAME;
                                    for (int w = 0; w < Owner.nOwners; w++) {
                                        if (C[i].Corders[C[i].orderssize].Restaurantname.equals(O[w].ownerrestaurant.Rname)) {
                                            for (int q = 0; q < O[w].ownerrestaurant.Mealsize; q++) {
                                                if (C[i].Corders[C[i].orderssize].M[k].name.equals(O[w].ownerrestaurant.RM[q].name)) {
                                                    
                                                    C[i].Corders[C[i].orderssize].M[k].description = O[w].ownerrestaurant.RM[q].description;
                                                    C[i].Corders[C[i].orderssize].M[k].price = O[w].ownerrestaurant.RM[q].price;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            C[i].Corders[C[i].orderssize].date = Order_reader.next();
                            C[i].Corders[C[i].orderssize].notes = Order_reader.next();
                            C[i].Corders[C[i].orderssize].price = C[i].Corders[C[i].orderssize].orderprice();
                            C[i].orderssize++;
                        }
                    }
                    
                }
            }
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
        }

        //_________________________________________________
        for (int i = 0; i < Owner.nOwners; i++) {
            for (int k = 0; k < Customer.Csize; k++) {
                for (int f = 0; f < C[k].orderssize; f++) {
                    if (C[k].Corders[f].Restaurantname.equals(O[i].ownerrestaurant.Rname)) {
                        O[i].ownerrestaurant.orders[O[i].ownerrestaurant.ordersize] = new Order();
                        for (int d = 0; d < C[k].Corders[f].mealsize; d++) {
                            O[i].ownerrestaurant.orders[O[i].ownerrestaurant.ordersize].M[O[i].ownerrestaurant.orders[O[i].ownerrestaurant.ordersize].mealsize] = new Meals();
                            O[i].ownerrestaurant.orders[O[i].ownerrestaurant.ordersize].M[O[i].ownerrestaurant.orders[O[i].ownerrestaurant.ordersize].mealsize] = C[k].Corders[f].M[d];

O[i].ownerrestaurant.orders[O[i].ownerrestaurant.ordersize].mealsize++;
                        }
                        O[i].ownerrestaurant.ordersize++;
                    }
                }
            }
        }
    }*/
    
}
