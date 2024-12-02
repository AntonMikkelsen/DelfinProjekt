package ui;

import datasource.Controller;
import domainmodel.*;
import domainmodel.AgeCategory;
import domainmodel.MembershipStatus;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class UserInterface {
    private Scanner scanner;
    private Controller.MembershipRegistrationService membershipService;
    Controller controller = new Controller();

    public UserInterface(Controller.MembershipRegistrationService membershipService) {
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
            System.out.println("1. Add members from existing teams");
            System.out.println("2. Remove existing members");
            System.out.println("3. Edit info on members");
            System.out.println("4. Show member overview");
            System.out.println("5. Exit menu");

            int userResponse = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (userResponse) {
                case 1 -> {
                    System.out.println("Add members from existing teams");
                    addNewMember();
                }
                case 2 -> {
                    System.out.println("Remove existing members");
                    String memberId = scanner.nextLine(); // Indhent ID fra brugeren
                    removeMember(memberId); // Kald metoden til at fjerne medlemmet
                }
                case 3 -> {
                    System.out.println("What is the users member ID");
                    String memberID = scanner.nextLine();
                    administatorEditInfo(memberID);
                }
                case 4 -> showMemberOverviewMenu();
                case 5 -> administrativeMenuRunning = false;
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
                case 1 -> {
                    System.out.println("Write your member id");
                    String userMemberID = scanner.nextLine();
                    showMemberInfo(userMemberID);
                }
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
                swimmer.getMemberID(),
                swimmer.getFirstName(),
                swimmer.getLastName(),
                swimmer.calculateAge(),
                swimmer.getMembershipStatus(),
                swimmer.getTeam().getTeamName());
    }

    private Member printMemberInfo(Member member) {
        System.out.printf("%-10s %-15s %-15s %-5d %-10s %-15s%n",
                member.getMemberID(),
                member.getFirstName(),
                member.getLastName(),
                member.calculateAge(),
                member.getMembershipStatus(),
                "No Team");
        return member;
    }

    private void removeMember(String memberID) {

        System.out.println("enter the a membersID to remove the member");

        List<Person> members = membershipService.getAllMembers();
        Member toRemove = null;

        for (Person person : members) {
            if (person instanceof Member && ((Member) person).getMemberID().equals(memberID)) {
                toRemove = (Member) person;
                break;
            }
        }
        if (toRemove != null && members.remove(toRemove)) {
            members.remove(memberID);
            System.out.println("Member removed successfully: " + toRemove.getFirstName() + " " + toRemove.getLastName());
        } else {
            System.out.println("Member does not exist");
        }

    }


    private void addNewMember() {
        System.out.println("------------------------");
        System.out.println("\nEnter first name: ");
        String firstName = scanner.nextLine();
        System.out.println("First name: " + firstName);

        System.out.println("\nEnter last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Last name: " + lastName);

        LocalDate dob = null;
        while (dob == null) {
            System.out.println("\nEnter date of birth (YYYY-MM-DD): ");
            try {
                dob = LocalDate.parse(scanner.nextLine());
                System.out.println("Date of birth: " + dob);
            } catch (Exception e) {
                System.out.println("An error has occured: " + e.getMessage() + " - Please try again");
            }
        }

        System.out.println("\nEnter email: ");
        String email = scanner.nextLine();
        while (!email.contains("@") && !email.contains(".")) {
            if (email.contains("@")) {
                System.out.println("Email: " + email);
            } else {
                System.out.println("Please enter a valid email");
                email = scanner.nextLine();
            }
        }

        System.out.println("\nEnter phone number: ");
        String phone = scanner.nextLine();
        System.out.println("Phone number: " + phone);

        System.out.println("\nEnter address: ");
        String address = scanner.nextLine();
        System.out.println("address: " + address);

        MembershipStatus status = null;
        while (status == null) {
            try {
                System.out.println("\nEnter membership status *Enter Passive/Active*: ");
                status = MembershipStatus.valueOf(scanner.nextLine().toUpperCase());
                System.out.println("membership stauts: " + status);
            } catch (IllegalArgumentException e) {
                System.out.println("An error has occured: " + e.getMessage() + " - Please try again");
            }
        }


        String memberId = "ID" + (membershipService.getAllMembers().size() + 1);

        Member newMember = new Member(firstName, lastName, dob, email, phone, address, memberId, status);
        membershipService.addMember(newMember);

        System.out.println("Member added successfully: " + newMember.getFirstName() + " " + newMember.getLastName());

    }

    private void waitForEnter() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public Member showMemberInfo(String memberID) {
        List<Person> Members = membershipService.getAllMembers();
        Member toSearch = null;
        for (Person person : Members) {
            if (person instanceof Member && ((Member) person).getMemberID().equals(memberID)) ;
            {
                toSearch = (Member) person;
                break;
            }
        }
        if (toSearch != null) {
            System.out.println(printMemberInfo(toSearch));
        } else {
            System.out.println("Member does not exist");
        }
        return null;
    }

    public Member administatorEditInfo(String memberID) {
        List<Person> Members = membershipService.getAllMembers();
        Member toSearch = null;
        for (Person person : Members) {
            if (person instanceof Member && ((Member) person).getMemberID().equals(memberID)) ;
            toSearch = (Member) person;
            break;
        }
        if (toSearch != null) {
            System.out.println(toSearch);
            return toSearch;
        }
        return null;
    }

    private void AdministrativeEditMember() {
        boolean administrativeEditMemberRunning = true;
        while (administrativeEditMemberRunning) {
            int userInput = scanner.nextInt();
            switch (userInput) {
                case 1 -> {
                    System.out.println("Enter new first name");
                    String newFirstName = scanner.nextLine();
                }
                case 2 -> {
                    System.out.println("Enter new last name ");
                    String newLastName = scanner.nextLine();
                }
                case 3 -> {
                    System.out.println("enter new date of birth ");
                }
                case 4 -> {
                    System.out.println("Enter new email (yes/no): ");
                    String newEmail = scanner.nextLine();
                }
                case 5 -> {
                    System.out.println("Enter new  phone number ");
                    String newPhonenumber = scanner.nextLine();
                }
                case 6 -> {
                    System.out.println("Enter new address");
                    String newAddress = scanner.nextLine();
                }
                case 7 -> {
                    System.out.println("Enter new memberID");
                    String newMemberID = scanner.nextLine();
                }
                case 8 -> {
                    System.out.println("Change membership status, write either passive or active");
                    MembershipStatus newMembershipStatus;
                    String toSearch = scanner.nextLine();
                    if (toSearch.equalsIgnoreCase("Passive")) {
                        newMembershipStatus = MembershipStatus.PASSIVE;
                    } else {
                        newMembershipStatus = MembershipStatus.ACTIVE;
                    }
                }
                case 9 -> {
                    System.out.println("Change membershipteam, write either Casual or Competetive");
                    String toSearch = scanner.nextLine();
                    if (toSearch.equalsIgnoreCase("Casual")) {

                    }
                }
            }
        }
    }

    private int validateInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                scanner.nextLine();
            }
        }
    }



  /*  private void removeMember(String memberID) {

        System.out.println("enter the a membersID to remove the member");

        List<Person> members = membershipService.getAllMembers();
        Member toRemove = null;

        for (Person person : members) {
            if (person instanceof Member && ((Member) person).getMemberID().equals(memberID)) {
                toRemove = (Member) person;
                break;
            }
        }
        if (toRemove != null && members.remove(toRemove)) {
            members.remove(memberID);
            System.out.println("Member removed successfully: " + toRemove.getFirstName() + " " + toRemove.getLastName());
        } else {
            System.out.println("Member does not exist");
        }

    } */

    private void editMember() {
        System.out.println("\n=== Edit Member Information ===");
        System.out.println("Enter member ID to edit: ");
        String memberID = scanner.nextLine();

        List<Person> members = membershipService.getAllMembers();
        Member memberToEdit = null;

        // Find the member
        for (Person person : members) {
            if (person instanceof Member && ((Member) person).getMemberID().equals(memberID)) {
                memberToEdit = (Member) person;
                break;
            }
        }

        if (memberToEdit == null) {
            System.out.println("Member not found.");
            return;
        }

        boolean editing = true;
        while (editing) {
            System.out.println("\nCurrent Member Information:");
            printMemberInfo(memberToEdit);

            System.out.println("\nWhat would you like to edit?");
            System.out.println("1. Member ID (Current: " + memberToEdit.getMemberID() + ")");
            System.out.println("2. First Name (Current: " + memberToEdit.getFirstName() + ")");
            System.out.println("3. Last Name (Current: " + memberToEdit.getLastName() + ")");
            System.out.println("4. Email (Current: " + memberToEdit.getEmail() + ")");
            System.out.println("5. Phone Number (Current: " + memberToEdit.getPhoneNumber() + ")");
            System.out.println("6. Address (Current: " + memberToEdit.getAddress() + ")");
            System.out.println("7. Membership Status (Current: " + memberToEdit.getMembershipStatus() + ")");
            System.out.println("8. Save and Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {

                case 1 -> {
                    System.out.println("Enter new member ID: ");
                    String newMemberID = scanner.nextLine();

                    // Check if the new member ID already exists
                    boolean idExists = false;
                    for (Person person : members) {
                        if (person instanceof Member) {
                            Member member = (Member) person;
                            if (member.getMemberID().equals(newMemberID) && member != memberToEdit) {
                                idExists = true;
                                break;
                            }
                        }
                    }

                    if (idExists) {
                        System.out.println("This member ID already exists. Please choose a different one.");
                    } else {
                        memberToEdit.setMemberID(newMemberID);
                        System.out.println("Member ID updated successfully.");
                    }

                }
                case 2 -> {
                    System.out.println("Enter new first name: ");
                    String firstName = scanner.nextLine();
                    memberToEdit.setFirstName(firstName);
                    System.out.println("First name updated successfully.");
                }
                case 3 -> {
                    System.out.println("Enter new last name: ");
                    String lastName = scanner.nextLine();
                    memberToEdit.setLastName(lastName);
                    System.out.println("Last name updated successfully.");
                }
                case 4 -> {
                    System.out.println("Enter new email: ");
                    String email = scanner.nextLine();
                    while (!email.contains("@") || !email.contains(".")) {
                        System.out.println("Please enter a valid email address: ");
                        email = scanner.nextLine();
                    }
                    memberToEdit.setEmail(email);
                    System.out.println("Email updated successfully.");
                }
                case 5 -> {
                    System.out.println("Enter new phone number: ");
                    String phone = scanner.nextLine();
                    memberToEdit.setPhoneNumber(phone);
                    System.out.println("Phone number updated successfully.");
                }
                case 6 -> {
                    System.out.println("Enter new address: ");
                    String address = scanner.nextLine();
                    memberToEdit.setAddress(address);
                    System.out.println("Address updated successfully.");
                }
                case 7 -> {
                    MembershipStatus newStatus = null;
                    while (newStatus == null) {
                        try {
                            System.out.println("Enter new membership status (ACTIVE/PASSIVE): ");
                            String statusInput = scanner.nextLine().toUpperCase();
                            newStatus = MembershipStatus.valueOf(statusInput);
                            memberToEdit.setMembershipStatus(newStatus);
                            System.out.println("Membership status updated successfully.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid status. Please enter either ACTIVE or PASSIVE.");
                        }
                    }
                }
                case 8 -> {
                    System.out.println("Changes saved successfully.");
                    editing = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

}