package com.mycompany.registration2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_lab
 */
public class Login {
    private String username;
   private String password;
   private String cellNumber;
   private String firstName;
   private String lastName;

 
 public Login(String username,String password, String cellNumber, String firstName, String lastName){     
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }    
    public String getFirstName() {
    return firstName;
}

public String getLastName() {
    return lastName;
}
    public boolean checkUsername(String username){
        //return username.contains("_")&& username.length()<=5;
       Boolean checkUser = username.contains("_")&& username.length()<=5;
       if(checkUser == true){
          System.out.print("") ; 
       }
       else{
          //true or false message
           return false; 
       }
       
       return true; 
    }
     public static boolean isPasswordComplex(String password){
        return password.length()>= 8 &&
               password.matches(".*[A-Z].*")&&
               password.matches(".*[a-z].*")&&
                password.matches(".*[0-9].*")&&  
               password.matches(".*[!@#$%^&*()].*");
}
   public boolean checkCellNumber(){
        return cellNumber.contains("+27")&& cellNumber.length()==12; 
   }
    public void displayFullName(){
        System.out.println(firstName +""+ lastName);
   }
   /*public String registerUser(){          
          
      if (!checkUsername(username)){
          return "The username is incorrectly formatted. ";
      } 
     if(!isPasswordComplex(password)){
       return "The password does not meet the complexity requirements";
   }
     if(!checkCellNumber()){
       return "The cell number is incorrectly formatted.";
   }
    return "The above conditions have been met, and the user has been registered successfully.";
} */
   public boolean loginUser(String enteredUsername, String enteredPassword){
      return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
      
}
  public String returnLoginStatus(boolean isSuccessful){
      if (isSuccessful){
              return "A successfull login. " ;
      }else{
           return "A failed login. ";
           } 
  }
}
