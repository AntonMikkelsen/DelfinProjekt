package UI;

import DomainModel.*;
import ENUMS.AgeCategory;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private MembershipRegistrationService membershipService;

    public UserInterface(MembershipRegistrationService membershipService) {
        this.membershipService = membershipService;
        this.scanner = new Scanner(System.in);
    }
    // Startmenu der gør brugeren kan komme ind på andre menuer bla administrivemenu osv.
    public void startMenu() {
        boolean menuRunning = true;
        while (menuRunning) {
            greetingsMSG();
            int userResponse = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (userResponse) {
                case 1 -> administrativeMenu();
                case 2 -> bookingAndTrainingMenu();
                case 3 -> membershipMenu();
                case 4 -> {
                    System.out.println("Exiting program...");
                    menuRunning = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    // Administrive menu, with administrive abilities
    public void administrativeMenu() {
        boolean administrativeMenuRunning = true;
        while (administrativeMenuRunning) {
            System.out.println("\n=== Administrative Menu ===");
            System.out.println("1. Add or remove members from existing teams");
            System.out.println("2. Edit info on members");
            System.out.println("3. Show member overview");
            System.out.println("4. Exit menu");

            int userResponse = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (userResponse) {
                case 1 -> System.out.println("Add or remove members from existing teams");
                case 2 -> System.out.println("Edit info on members");
                case 3 -> showMemberOverviewMenu();
                case 4 -> administrativeMenuRunning = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    // BookingAndTrainingMenu
    public void bookingAndTrainingMenu() {
        boolean bookingAndTrainingMenuRunning = true;
        while (bookingAndTrainingMenuRunning) {
            System.out.println("\n=== Booking and Training Menu ===");
            System.out.println("1. See schedule of the day");
            System.out.println("2. Edit schedule as a trainer");
            System.out.println("3. Cancelation of training or booking");
            System.out.println("4. Exit menu");

            int userResponse = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (userResponse) {
                case 1 -> System.out.println("See schedule of the day");
                case 2 -> System.out.println("Edit schedule as a trainer");
                case 3 -> System.out.println("Cancelation of training or booking");
                case 4 -> bookingAndTrainingMenuRunning = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void membershipMenu() {
        boolean membershipMenuRunning = true;
        while (membershipMenuRunning) {
            System.out.println("\n=== Membership Menu ===");
            System.out.println("1. See membership details");
            System.out.println("2. Edit membership details and status");
            System.out.println("3. Exit menu");

            int userResponse = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (userResponse) {
                case 1 -> System.out.println("See membership details");
                case 2 -> System.out.println("Edit membership details and status");
                case 3 -> membershipMenuRunning = false;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showMemberOverviewMenu() {
        boolean overviewMenuRunning = true;
        while (overviewMenuRunning) {
            System.out.println("\n=== Member Overview ===");
            System.out.println("1. View all members");
            System.out.println("2. View team members");
            System.out.println("0. Back to main menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> displayAllMembers();
                case 2 -> displayAllTeamMembers();
                case 0 -> overviewMenuRunning = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    // Method to show all members,
    private void displayAllMembers() {
        List<Person> members = membershipService.getAllMembers();
        System.out.println("\n=== All Members Overview ===");
        printHeaderLine();

        for (Person person : members) {
            if (person instanceof CompetitiveSwimmer) {
                printSwimmerInfo((CompetitiveSwimmer) person);
            } else if (person instanceof Member) {
                printMemberInfo((Member) person);
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
        scanner.nextLine(); // Consume newline

        Team selectedTeam = switch (choice) {
            case 1 -> new Team("Junior Team", AgeCategory.JUNIOR);
            case 2 -> new Team("Senior Team", AgeCategory.SENIOR);
            default -> {
                System.out.println("Invalid team selection");
                yield null;
            }
        };

        if (selectedTeam != null) {
            List<CompetitiveSwimmer> teamMembers = membershipService.getTeamMembers(selectedTeam);
            System.out.println("\n=== Team " + selectedTeam.getTeamName() + " Members ===");
            printHeaderLine();

            for (CompetitiveSwimmer swimmer : teamMembers) {
                printSwimmerInfo(swimmer);
            }
            System.out.println("Total Team Members: " + teamMembers.size());
        }
        waitForEnter();
    }

    // Method to greet the user, and save sout's.
    private void greetingsMSG() {
        System.out.println("\n=== Welcome To Your Swimming Park System ===");
        System.out.println("1. Administrative data");
        System.out.println("2. Booking and training");
        System.out.println("3. Membership management");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    //
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

    private void printMemberInfo(Member member) {
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