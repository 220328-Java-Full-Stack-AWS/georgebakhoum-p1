package com.revature;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import com.revature.util.ConnectionFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Driver {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, IOException {
        try{
            ConnectionFactory.getConnection();
        }
        catch(SQLException | IOException e){
            e.printStackTrace();
        }

        UserService us = new UserService();
        ReimbursementService rd = new ReimbursementService();
        User user = null;

        int choice = 0;

        String firstname = "", lastname = "", email = "", username = "", password = "";

        while(true){
            if (user != null) {
                if(user.getRole().equals("Employee")) {
                    System.out.println("What would you like to do today?\n");
                    System.out.println("Press 1 to submit a request for reimbursement.");
                    System.out.println("Press 2 to cancel a pending request for reimbursement.");
                    System.out.println("Press 3 to view your pending and completed past requests for reimbursement.");
                    System.out.println("Press 4 to edit your pending requests for reimbursement.");
                    System.out.println("Press 0 if you wish to log out.");

                    choice = scan.nextInt();
                    scan.nextLine();
                    System.out.println();

                    switch (choice) {
                        case 1:
                            System.out.println("What type of reimbursement do you wish to submit request for?");
                            System.out.println("Lodging - Press 1");
                            System.out.println("Food - Press 2");
                            System.out.println("Travel - Press 3");

                            choice = scan.nextInt();
                            scan.nextLine();
                            System.out.println();

                            Reimbursement r = new Reimbursement();
                            r.setStatus("Pending");

                            switch (choice) {
                                case 1:
                                    r.setType("Lodging");
                                    break;
                                case 2:
                                    r.setType("Food");
                                    break;
                                case 3:
                                    r.setType("Travel");
                                    break;
                                default:
                                    System.out.println("Invalid input.");
                                    break;
                            }

                            System.out.println("Enter your description for this reimbursement request. Maximum characters allowed: 8000");
                            String desc = scan.nextLine();
                            r.setDescription(desc);
                            r.setUserID(user.getId());
                            ReimbursementService.create(r);
                            break;
                        case 2:
                            System.out.println("Enter the ID for your pending reimbursement request:");

                            choice = scan.nextInt();
                            scan.nextLine();
                            System.out.println();

                            if(ReimbursementService.find(user.getId(), choice)){
                                ReimbursementService.delete(choice);
                            } else {
                                System.out.println("You entered an invalid reimbursement request ID.");
                            }
                            break;
                        case 3:
                            ReimbursementService.viewRequestsUser(user.getId());
                            break;
                        case 4:
                            System.out.println("Enter the ID of the request you want to edit:");

                            choice = scan.nextInt();
                            scan.nextLine();
                            System.out.println();

                            if (ReimbursementService.find(user.getId(), choice)) {
                                r = ReimbursementService.read(choice);

                                System.out.println("If you wish to change...");
                                System.out.println("Type - press 1");
                                System.out.println("Description - press 2");
                                System.out.println("Both - press 3");
                                System.out.println("Press any other key to cancel editing your request");

                                choice = scan.nextInt();
                                scan.nextLine();
                                System.out.println();

                                if(choice == 1 || choice == 2 || choice == 3) {
                                    if (choice == 1 || choice == 3) {
                                        System.out.println("Press 1 to change reimbursement type to lodging");
                                        System.out.println("Press 2 to change reimbursement type to food");
                                        System.out.println("Press 3 to change reimbursement type to travel");
                                        choice = scan.nextInt();
                                        scan.nextLine();
                                        System.out.println();

                                        switch (choice) {
                                            case 1:
                                                r.setType("Lodging");
                                                break;
                                            case 2:
                                                r.setType("Food");
                                                break;
                                            case 3:
                                                r.setType("Travel");
                                                break;
                                            default:
                                                System.out.println("Invalid input.");
                                                break;
                                        }
                                    }
                                    if (choice == 2 || choice == 3) {
                                        System.out.println("Enter the new description for your reimbursement request:");
                                        desc = scan.nextLine();
                                        r.setDescription(desc);
                                    }
                                    ReimbursementService.update(r);
                                }
                            } else {
                                System.out.println("You entered an invalid reimbursement request ID.");
                            }
                            break;
                        case 0:
                            System.out.println("You are now logged out.");
                            user = null;
                            break;
                        default:
                            break;
                    }
                }
                else if (user.getRole().equals("Admin")) {
                    System.out.println("Press 1 if you would like to view reimbursement requests.");
                    System.out.println("Press 2 if you would like to approve or deny a request.");
                    System.out.println("Press 0 if you wish to log out.");

                    choice = scan.nextInt();
                    scan.nextLine();
                    System.out.println();

                    switch(choice){
                        case 1:
                            System.out.println("Press 1 to view pending requests.");
                            System.out.println("Press 2 to view approved requests.");
                            System.out.println("Press 3 to view denied requests.");
                            System.out.println("Press 4 to view all requests.");

                            choice = scan.nextInt();
                            scan.nextLine();
                            System.out.println();

                            //ReimbursementService.viewRequestsAdmin(choice);
                            break;
                        case 2:
                            System.out.println("What is the ID of the reimbursement request you wish to make changes to?");

                            choice = scan.nextInt();
                            scan.nextLine();
                            System.out.println();

                            Reimbursement r = ReimbursementService.read(choice);

                            System.out.println("Press 1 to approve the request, press 2 to deny the request.");
                            System.out.println("Press any other key to make no changes to this request.");

                            choice = scan.nextInt();
                            scan.nextLine();
                            System.out.println();

                            switch(choice){
                                case 1:
                                    r.setStatus("Approved");
                                    ReimbursementService.update(r);
                                    break;
                                case 2:
                                    r.setStatus("Denied");
                                    ReimbursementService.update(r);
                                    break;
                                default:
                                    break;
                            }

                            break;
                        case 0:
                            System.out.println("You are now logged out.");
                            user = null;
                            break;
                        default:
                            break;
                    }
                }
            } else {
                System.out.println("Press 1 to register, press 2 to login");
                choice = scan.nextInt();
                scan.nextLine();

                if (choice == 1) {
                    System.out.println("\nEnter your first name:");
                    firstname = scan.nextLine();

                    System.out.println("\nEnter your last name:");
                    lastname = scan.nextLine();

                    System.out.println("\nEnter your email:");
                    email = scan.nextLine();

                    System.out.println("\nEnter your username:");
                    username = scan.nextLine();

                    System.out.println("\nEnter your password:");
                    password = scan.nextLine();

                    User temp = new User();
                    temp.setFirstName(firstname);
                    temp.setLastName(lastname);
                    temp.setEmail(email);
                    temp.setPassword(password);
                    temp.setUsername(username);

                    UserService.create(temp);
                    System.out.println("\nRegistration successful!\n");
                } else if (choice == 2) {
                    System.out.println("\nEnter your username:");
                    username = scan.nextLine();

                    System.out.println("\nEnter your password:");
                    password = scan.nextLine();
                    System.out.println();

                    user = UserService.login(username, password);
                } else {
                    System.out.println("\nInvalid input, please try again.");
                }
            }
        }
    }
}
