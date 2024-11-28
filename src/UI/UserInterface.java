package UI;

import DomainModel.*;
import ENUMS.AgeCategory;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Scanner scan = new Scanner(System.in);

    public void startMenu() {
        grettingsMSG();
        int userResponse = scan.nextInt();
        boolean menuRunning = true;
        while (menuRunning) {
            switch (userResponse) {
                case 1 -> {
                    adminstriveMenu();

                }
                case 2 -> {
                    bookingAndTrainingMenu();
                }
                case 3 -> {
                    membershipMenu();
                }
            }
        }
    }

    public void adminstriveMenu() {
        boolean administriveMenuRunning = true;
        while (administriveMenuRunning) {
            int userResponse = scan.nextInt();
            switch (userResponse) {
                case 1 -> System.out.println("Add or remove members from existing teams");
                case 2 -> System.out.println("Edit info on members");
                case 3 -> {
                    System.out.println("Exit menu");
                    administriveMenuRunning = false;
                }

            }
        }
    }

    public void bookingAndTrainingMenu() {
        boolean bookingAndTrainingMenu = true;
        while (bookingAndTrainingMenu) {
            int userResponse = scan.nextInt();
            switch (userResponse) {
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

    public void membershipMenu() {
        boolean membershipMenu = true;
        while (membershipMenu) {
            int userResponse = scan.nextInt();
            switch (userResponse) {
                case 1 -> System.out.println("See membership details");
                case 2 -> System.out.println("Edit membership details and status");
                case 3 -> {
                    System.out.println("Exit menu");
                    membershipMenu = false;
                }
            }
        }
    }

    public void grettingsMSG() {
        System.out.println("Welcome To Your Own Swimming Park System");
        System.out.println("Press 1 for administrive data");
        System.out.println("Press 2 for data involving booking and training");
        System.out.println("Press 3 to watch and edit membership");
    }
}
    private Scanner scanner;
    private MembershipRegistrationService membershipService;

    public UserInterface(MembershipRegistrationService membershipService) {
        this.membershipService = membershipService;
    }

    private void showMemberOverviewMenu() {
        System.out.println("===MEMBER OVERVIEW===");
        System.out.println("1. View all members");
        System.out.println("2. View team members");
        System.out.println("0. back to main menu");
        System.out.print("Enter your choice");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                displayAllMembers();
                break;
            case 2:
                displayAllTeamMembers();
                break;
            case 0:
                break;
            default:
                System.out.println("invalid Choice lease try again");

        }

    }

    private void displayAllMembers() {
        List<Person> members = membershipService.getAllMembers();
        System.out.println("\n=== All Members Overview ===");
        printHeaderLine();

        for (Person person : members) {
            if (person instanceof CompetitiveSwimmer) {
                CompetitiveSwimmer swimmer = (CompetitiveSwimmer) person;
                printSwimmerInfo(swimmer);
            } else {
                printMemberInfo((Members) person);
            }
        }
        System.out.println("Total Members: " + members.size());
        waitForEnter();
    }

    private void displayAllTeamMembers() {
        System.out.println("\n1. Junior Team");
        System.out.println("2. Senior Team");
        System.out.print("Select team: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Team selectedTeam;
        switch (choice) {
            case 1:
                selectedTeam = new Team("Junior Team", AgeCategory.JUNIOR);
                break;
            case 2:
                selectedTeam = new Team("Senior Team", AgeCategory.SENIOR);
                break;
            default:
                System.out.println("Invalid team selection");
                return;
        }
        List<CompetitiveSwimmer> teamMembers = membershipService.getTeamMembers(selectedTeam);
        System.out.println("\n=== Team " + selectedTeam.getTeamName() + " Members ===");
        printHeaderLine();

        for (CompetitiveSwimmer swimmer : teamMembers) {
            printSwimmerInfo(swimmer);
        }
        System.out.println("Total Team Members: " + teamMembers.size());
        waitForEnter();
    }


    //Helping methods
    private void printHeaderLine() {
        System.out.printf("%-10s %-15s %-15s %-5s %-10s %-15s%n",
                "ID", "First Name", "Last Name", "Age", "Status", "Team");
        System.out.println("=".repeat(70));
    }

    private void printSwimmerInfo(CompetitiveSwimmer swimmer) {
        System.out.printf("%-10s %-15s %-15s %-5d %-10s %-15s%n",
                swimmer.getMemberId(),
                swimmer.getFirstName(),
                swimmer.getLastName(),
                swimmer.getDateOfBirth(),
                swimmer.getMembershipStatus(),
                swimmer.getTeam().getTeamName());
    }

    private void printMemberInfo(Members member) {
        System.out.printf("%-10s %-15s %-15s %-5d %-10s %-15s%n",
                member.getMemberId(),
                member.getFirstName(),
                member.getLastName(),
                member.getDateOfBirth(),
                member.getMembershipStatus(),
                "No Team");
    }

    private void waitForEnter() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}


