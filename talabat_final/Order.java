
package talabat_final;


public class Order {
      Meals[] M = new Meals[20];
    float price = 0;
    String notes;
    String date;
    int mealsize = 0;
    String Restaurantname;//l restaurant l hy3ml l order
   public Order() {
    }
    public Order(String Restaurantname) {
        this.Restaurantname = Restaurantname;
    }
  /*  

    public float orderprice() {
        for (int i = 0; i < mealsize; i++) {
            
          price += Float.parseFloat(M[i].price);
         
                 }
        return price;
    }*/
    
}