
package talabat_final;

import static java.lang.Character.isLetter;
import java.util.Scanner;

//---------------------Karma's---------------------------
public abstract class User {
    String name;
    String pw;
    
    User(String name, String pw) {
        this.name = name;
        this.pw = pw;
        
    }
    
   User(){};
    Scanner input = new Scanner(System.in);
    
    abstract void Logout( Customer activeCustomer, Owner activeOwner, String activeUSER_IS);
    
    boolean PW_Validation(String pw) {
        
        boolean PW_Length = true, PW_valid = true, PW_confirmation = true;
        
        String confirm_pw;
        
        try {
            Integer.parseInt(pw);
            PW_valid = true;
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid password, please enter numbers only ");
            PW_valid = false;
            
        }
        
        if (pw.length() < 4) {
            PW_Length = false;
            
            System.out.println("Invalid password, please enter four or more numbers");
        } else {
            PW_Length = true;
            
        }
        
        if (PW_valid == true & PW_Length == true) {
            System.out.println("Please confirm your pw:");
            confirm_pw = input.next();
            if (confirm_pw.trim().equalsIgnoreCase(pw)) {
                PW_confirmation = true;
                
            } else {
                System.out.println("Password confirmation does not match\n"
                        + "Please try again");
                PW_confirmation = false;
                
            }
        }
        
        if (PW_confirmation == false || PW_Length == false || PW_valid == false) {
            return false;
        } else {
            return true;
        }
        
    }
    
    boolean MobileNo_Validation(String Mn) {
        boolean Mn_valid = true, Mn_length = true;
        
        if (!Mn.matches("[0-9]+")) {
            System.out.println("Invalid entry, please enter numbers only");
            Mn_valid = false;
        }
        if (Mn.length() != 11) {
            System.out.println("Invalid entry, please enter 11 number");
            Mn_length = false;
        } else {
            Mn_length = true;
        }
        if (Mn_valid == false || Mn_length == false) {
            return false;
        } else {
            return true;
        }
    }
    boolean name_validation (String name){
         
    boolean valid=true;
       for ( int i=0 ; i < name.length();i++){
           if (isLetter(name.charAt(i))){
               valid  = true;
           }
           else {
               valid=false;
             System.out.println("Invalid entry, please try again {Characters only}");
             break;
       }
       }
       return valid;
    }
    
    
    boolean isEmpty(String Empty) {
        if (Empty.isEmpty()) {
            System.out.println("please fill in all required fields");
            return true;
        } else {
            return false;
        }
    }
}
