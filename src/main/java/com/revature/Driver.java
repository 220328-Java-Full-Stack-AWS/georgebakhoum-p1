package com.revature;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.ConnectionFactory;
import java.sql.Connection;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        try{
            Connection conn = ConnectionFactory.getConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        User loggedin = null;
        Scanner scan = new Scanner(System.in);
        boolean done = false;

        while(!done){
            if(loggedin == null) {
                System.out.println("Do you want to register? Press 1 for register, 2 for login");
                int choice = scan.nextInt();
                scan.nextLine();
                if (choice == 1) {
                    System.out.println("Choose your first name below:") '
                    String first = scan.nextLine();
                    System.out.println("Choose your last name below:") '
                    String last = scan.nextLine();
                    System.out.println("Choose your email below:") '
                    String email = scan.nextLine();
                    System.out.println("Choose your password below:") '
                    String password = scan.nextLine();
                    UserService.register(first, last, email, password);
                } else {
                    System.out.println("Enter your username below:") '
                    String email = scan.nextLine();
                    System.out.println("Enter your password below:") '
                    String password = scan.nextLine();
                }
            }
            else{
                System.out.println("Welcome to the lamest social media app!");
                System.out.println("What would you like to do today?");
                System.out.println("Choose 1 to view feed, Choose 2 to follow users, Choose 3 to create a post");
                int choice = scan.nextInt();
                scan.nextLine();
                switch(choice){
                    case 1:
                        break;
                    case 2:
                        System.out.println("Here are our top users you may want to follow");
                }
                done = true;
            }
        }
    }
}
