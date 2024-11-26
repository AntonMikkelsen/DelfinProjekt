package UI;

import java.util.Scanner;

public class UserInterface {
    Scanner scan = new Scanner(System.in);

    public void startMenu(){
        grettingsMSG();
        int userResponse = scan.nextInt();
        boolean menuRunning = true;
        while(menuRunning){
            switch(userResponse){
                case 1 -> {
                    adminstriveMenu();

                }
                case 2 -> {
                    bookingAndTrainingMenu();
                }
                case 3 -> {
                    System.out.println();
                }
            }
        }
    }

    public void adminstriveMenu(){
        boolean administriveMenuRunning = true;
        while(administriveMenuRunning){
            int userResponse = scan.nextInt();
            switch(userResponse) {
                case 1 -> System.out.println("Add or remove members from existing teams");
                case 2 -> System.out.println("Edit info on members");
                case 3 -> {
                System.out.println("Exit menu");
                administriveMenuRunning = false;
                }

            }
        }
    }

    public void bookingAndTrainingMenu(){
        boolean bookingAndTrainingMenu = true;
        while(bookingAndTrainingMenu){
            int userResponse = scan.nextInt();
            switch(userResponse){
                case 1 -> System.out.println("See schedule of the day");
                case 2 -> System.out.println("Edit schedule as a trainer");
                case 3 -> System.out.println("Cancelation of training or booking");
                case 4 -> {
                    System.out.println("Exit bookingAndTrainingMenu");
                    bookingAndTrainingMenu = false;
                }

            }
        }
    }

    public void membershipMenu(){
       boolean membershipMenu = true;
       while(membershipMenu){
           int userResponse = scan.nextInt();
           switch(userResponse){
               case 1 -> System.out.println("See membership details");
               case 2 -> System.out.println("Edit membership details and status");
               case 3 -> {
                   System.out.println("Exit menu");
                   membershipMenu = false;
               }
           }
       }
    }

    public void grettingsMSG(){
        System.out.println("Welcome To Your Own Swimming Park System");
        System.out.println("Press 1 for administrive data");
        System.out.println("Press 2 for data involving booking and training");
        System.out.println("Press 3 to watch and edit membership");
    }
}
