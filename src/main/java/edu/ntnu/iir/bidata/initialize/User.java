package edu.ntnu.iir.bidata.initialize;


public class User {
    
    final private String name;
    
    public User(String inputName) {
        name = inputName;
    }

    public void getUsers() {

    }

    public boolean checkUser(String inputName) {
        return name.equals(inputName);
    }
    
    // might want to personalize the experience a little, and add a password for safety
    // private String password;
    // private String favoriteFood;
    // 
    // public User(String inputName, String inputPassword, String inputFavoriteFood) {
    //     name = inputName;
    //     password = inputPassword;
    //     favoriteFood = inputFavoriteFood;
    // }
   
}
