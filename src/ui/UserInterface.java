package ui;

import datasource.Controller;
import domainmodel.*;
import domainmodel.AgeCategory;
import domainmodel.MembershipStatus;

import java.time.LocalDate;
import java.util.*;


public class UserInterface {
    private Scanner scanner;
    private Controller controller = new Controller();

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    // Startmenu der gør brugeren kan komme ind på andre menuer bla administrivemenu osv.
    public void startMenu() {
        System.out.println("\n**Press 4 to create test Members in the club** (Must do first, to test program with existing members/teams/Income\n");
        boolean menuRunning = true;
        while (menuRunning) {
            greetingsMSG();
            int userResponse = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (userResponse) {
                case 1 -> administrativeMenu();
                case 2 -> membershipMenu();
                case 3 -> displayFinancialOverview();
                case 4 -> {
                    createNewTestMembers();
                    createTestCompSwimmers();
                }
                case 5 -> {
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
                    administatorEditInfo();
                }
                case 4 -> showMemberOverviewMenu();
                case 5 -> administrativeMenuRunning = false;
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
            System.out.println("4. Exit menu");

            int userResponse = scanner.nextInt();
            scanner.nextLine();

            switch (userResponse) {
                case 1 -> {
                    System.out.println("Write your member id");
                    String userMemberID = scanner.nextLine();
                    showMemberInfo(userMemberID);
                }
                case 2 -> {
                    System.out.println("Edit membership details and status");
                    editMember();
                }
                case 3 -> {
                    System.out.println("\n=== Financial Overview Menu ===");

                }
                case 4 -> membershipMenuRunning = false;

                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showMemberOverviewMenu() {
        boolean overviewMenuRunning = true;
        while (overviewMenuRunning) {
            System.out.println("\n=== Member Overview ===");
            System.out.println("1. View all members");
            System.out.println("2. View all team members Competitive swimmers");
            System.out.println("3. Configure Casual Team");
            // System.out.println("4. View best competitive swimmers sorted by discipline"); // New
            System.out.println("0. Back to main menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> controller.displayMembers();
                case 2 -> displayAllTeamCompMembers();
                case 3 ->  displayAllTeamMembersRegularNew();// CompetitiveSwimmer.printAllCompSwimmersBestDiscipline(controller.getCompSwimmers()); // New
                case 0 -> overviewMenuRunning = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private void displayFinancialOverview(){
        System.out.println("1. View total income expected with all members");
        System.out.println("2. View total debt from all members in arrears");
        System.out.println("3. View total net income earned (Excluding members in arrears)");

        int choice = validateInt();

        switch(choice){
            case 1 -> controller.cashierTotalIncomeGenerated();
            case 2 -> controller.cashierTotalDebtFromArrears();
            case 3 -> controller.cashierNetIncomeNoArrears();
            default -> System.out.println("Wrong input - try again...");
        }
    }

    //Ny display members huss arbejder på
    public void displayAllTeamMembersRegularNew(){

        System.out.println("\n--1. Add all non competitive swimmers to casual team--");
        System.out.println("--2. View casual team members--\n");
        int choice = scanner.nextInt();

        Team casualTeam = new Team("Casual Team");


        casualTeam.addSwimmersToTeam(controller.getMembers().get(0));
        casualTeam.addSwimmersToTeam(controller.getMembers().get(1));
        casualTeam.addSwimmersToTeam(controller.getMembers().get(2));
        casualTeam.addSwimmersToTeam(controller.getMembers().get(controller.getMembers().size() -1));

        switch (choice) {
            case 1 -> {
                casualTeam.addSwimmersToTeam(controller.getMembers().get(0));
                casualTeam.addSwimmersToTeam(controller.getMembers().get(1));
                casualTeam.addSwimmersToTeam(controller.getMembers().get(2));
                casualTeam.addSwimmersToTeam(controller.getMembers().get(controller.getMembers().size() -1));
            }
            case 2 -> {
                System.out.println("\n=== Casual Team Members ===");
                for (Member member : casualTeam.getTeamMembersRegular()) {
                    System.out.println(member.getFirstName());
                }
            }
        }
    }

    private void displayAllTeamCompMembers() {
        // Create the teams first
        Team juniorTeam = new Team("Junior Team", AgeCategory.JUNIOR);
        Team seniorTeam = new Team("Senior Team", AgeCategory.SENIOR);

        // Create the coaches for each team
        Coach juniorCoach = new Coach(
                "Michael",
                "Williams",
                LocalDate.of(1985, 7, 20), // Corrected the date (Year, Month, Day)
                "michael.williams@example.com",
                "12345678",
                "123 Junior Street, Cityville", // Example address
                "JC123", // Coach ID for Junior Coach
                juniorTeam // Assign to Junior Team
        );

        Coach seniorCoach = new Coach(
                "Sarah",
                "Taylor",
                LocalDate.of(1980, 1, 1), // Corrected the date (Year, Month, Day)
                "sarah.taylor@example.com",
                "12345678",
                "456 Senior Avenue, Townsville", // Example address
                "SC456", // Coach ID for Senior Coach
                seniorTeam // Assign to Senior Team
        );

        System.out.println("\n1. Junior Team");
        System.out.println("2. Senior Team");
        System.out.print("Select team: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Team selectedTeam = switch (choice) {
            case 1 -> juniorTeam;
            case 2 -> seniorTeam;
            default -> {
                System.out.println("Invalid team selection");
                yield null;
            }
        };

        if (selectedTeam != null) {
            // ArrayList to hold the competitive swimmers for the selected team
            ArrayList<CompetitiveSwimmer> teamMembers = new ArrayList<>();

            // Hardcoding Junior Team Members if the selected team is Junior
            if (selectedTeam.getTeamName().equals("Junior Team")) {
                CompetitiveSwimmer swimmer1 = new CompetitiveSwimmer(
                        "John", "Doe", LocalDate.of(2005, 6, 15),
                        "john.doe@example.com", "12345678", "123 Main St", "id001", MembershipStatus.ACTIVE, juniorTeam, juniorCoach);
                swimmer1.setSwimmerType(SwimmerType.COMPETITIVE);

                CompetitiveSwimmer swimmer2 = new CompetitiveSwimmer(
                        "Jane", "Smith", LocalDate.of(2006, 8, 22),
                        "jane.smith@example.com", "12345678", "456 Elm St", "id002", MembershipStatus.ACTIVE, juniorTeam, juniorCoach);
                swimmer2.setSwimmerType(SwimmerType.COMPETITIVE);

                teamMembers.add(swimmer1);
                teamMembers.add(swimmer2);
            }

            // Hardcoding Senior Team Members if the selected team is Senior
            if (selectedTeam.getTeamName().equals("Senior Team")) {
                CompetitiveSwimmer swimmer3 = new CompetitiveSwimmer(
                        "Emily", "Johnson", LocalDate.of(1995, 4, 10),
                        "emily.johnson@example.com", "12345678", "789 Oak St", "id003", MembershipStatus.ACTIVE, seniorTeam, seniorCoach);
                swimmer3.setSwimmerType(SwimmerType.COMPETITIVE);

                CompetitiveSwimmer swimmer4 = new CompetitiveSwimmer(
                        "Chris", "Lee", LocalDate.of(1994, 2, 5),
                        "chris.lee@example.com", "12345678", "101 Pine St", "id004", MembershipStatus.ACTIVE, seniorTeam, seniorCoach);
                swimmer4.setSwimmerType(SwimmerType.COMPETITIVE);

                teamMembers.add(swimmer3);
                teamMembers.add(swimmer4);
            }

            // Display the swimmers in the selected team
            if (!teamMembers.isEmpty()) {
                System.out.println("\n=== " + selectedTeam.getTeamName() + " Competitive Swimmers ===");
                printHeaderLine();

                for (CompetitiveSwimmer swimmer : teamMembers) {
                    printSwimmerInfo(swimmer);
                }
                System.out.println("Total Team Members: " + teamMembers.size());
            } else {
                System.out.println("No competitive swimmers found in the selected team.");
            }
        }

        waitForEnter();
    }






    // Method to greet the user, and save sout's.
    private void greetingsMSG() {
        System.out.println("\n=== Welcome To Your Swimming Park System ===");
        System.out.println("1. Administrative data");
        System.out.println("2. Membership management");
        System.out.println("3. Financial Overview");
        System.out.println("4. Create 4 already pre-existing members to test the program ");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }



    private void printHeaderLine() {
        System.out.printf("%-10s %-15s %-15s %-5s %-10s %-15s %-25s%n",
                "ID", "First Name", "Last Name", "Age", "Status", "Team", "Active Disciplines");
        System.out.println("=".repeat(130)); // Adjust the separator to match the width of the header line.
    }


    public void printSwimmerInfo(CompetitiveSwimmer swimmer) {
        System.out.printf("%-10s %-15s %-15s %-5d %-10s %-15s %s%n",
                swimmer.getMemberID(),
                swimmer.getFirstName(),
                swimmer.getLastName(),
                swimmer.calculateAge(),
                swimmer.getMembershipStatus(),
                swimmer.getTeam().getTeamName(),
                String.join(", ", swimmer.getDisciplinesString()));
    }

    public Member printMemberInfo(Member member) {
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

        ArrayList<Member> members = controller.getMembers();
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

        Random random = new Random();
        int randomNum = random.nextInt(8999) + 1000;
        String memberId = "ID" + (randomNum);

        Member newMember = new Member(firstName, lastName, dob, email, phone, address, memberId, status);

        controller.addMember(firstName, lastName, dob, email, phone, address, memberId, status);
        controller.displayMembers();

        System.out.println("Member added successfully: " + newMember.getFirstName() + " " + newMember.getLastName());

    }

    public void createNewTestMembers(){
        LocalDate birthdate = LocalDate.of(2000,07,12);
        LocalDate birthdate1 = LocalDate.of(2020,07,12);
        LocalDate birthdate2 = LocalDate.of(1800,07,12);
        LocalDate birthdate3 = LocalDate.of(1999,07,12);
        LocalDate birthdate4 = LocalDate.of(2001,07,12);

        Member member1 = new Member("Hussain", "Mahmoud", birthdate, "hus.@", "27282728", "husdus", "id1", MembershipStatus.ACTIVE);
        Member member2 = new Member("Muzaffer", "Mikkelsen", birthdate1, "muz.@", "27282728", "husdus", "id2", MembershipStatus.ACTIVE);
        Member member3 = new Member("thomas", "Mtimm", birthdate2, "timm.@", "27282728", "husdus", "id3", MembershipStatus.ACTIVE);
        Member member4 = new Member("anton", "Mmikjkkelsen", birthdate3, "ant.@", "27282728", "husdus", "id4", MembershipStatus.ACTIVE);
        Member member5 = new Member("Peter", "Jakobsen", birthdate4, "pet.@", "27282728", "husdus", "id5", MembershipStatus.PASSIVE);


        controller.addMember(member1.getFirstName(), member1.getLastName(), birthdate, member1.getEmail(), member1.getPhoneNumber(), member1.getAddress(), member1.getMemberID(), MembershipStatus.ACTIVE);
        controller.addMember(member2.getFirstName(), member2.getLastName(), birthdate1, member2.getEmail(), member2.getPhoneNumber(), member2.getAddress(), member2.getMemberID(), MembershipStatus.ACTIVE);
        controller.addMember(member3.getFirstName(), member3.getLastName(), birthdate2, member3.getEmail(), member3.getPhoneNumber(), member3.getAddress(), member3.getMemberID(), MembershipStatus.ACTIVE);
        controller.addMember(member4.getFirstName(), member4.getLastName(), birthdate3, member4.getEmail(), member4.getPhoneNumber(), member4.getAddress(), member4.getMemberID(), MembershipStatus.ACTIVE);
        controller.addMembersToArrearsController(member4, true);
        controller.addMember(member5.getFirstName(), member5.getLastName(), birthdate4, member5.getEmail(), member5.getPhoneNumber(), member5.getAddress(), member5.getMemberID(), MembershipStatus.PASSIVE);


    }

    public void createTestCompSwimmers(){
        LocalDate birthdate = LocalDate.of(2000,07,12);
        LocalDate birthdate1 = LocalDate.of(2020,07,12);

        Member member1 = new Member("HussainComp", "MahmoudCOMP", birthdate, "hus.@", "27282728", "husdus", "id4", MembershipStatus.ACTIVE);
        Member member2 = new Member("MuzafferCOMP", "MikkelsenCOMP", birthdate1, "muz.@", "27282728", "husdus", "id5", MembershipStatus.ACTIVE);

        member1.setSwimmerType(SwimmerType.COMPETITIVE);
        member2.setSwimmerType(SwimmerType.COMPETITIVE);

        controller.addMember(member1.getFirstName(), member1.getLastName(), birthdate, member1.getEmail(), member1.getPhoneNumber(), member1.getAddress(), member1.getMemberID(), MembershipStatus.ACTIVE);
        controller.addMember(member2.getFirstName(), member2.getLastName(), birthdate1, member2.getEmail(), member2.getPhoneNumber(), member2.getAddress(), member2.getMemberID(), MembershipStatus.ACTIVE);



    }

    private void waitForEnter() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public Member showMemberInfo(String memberID) {
        ArrayList<Member> Members = controller.getMembers();
        Member toSearch = null;
        for (Person person : Members) {
            if (person instanceof Member && ((Member) person).getMemberID().equals(memberID)) ;{
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

    public Member administatorEditInfo() {
        System.out.println("\n=== Edit Member Information ===");
        System.out.println("Enter member ID to edit: ");
        String memberID = scanner.nextLine();

        ArrayList<Member> members = controller.getMembers();
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
            return null;
        }

        boolean editing = true;
        while (editing) {
            System.out.println("\nCurrent Member Information:");
            printMemberInfo(memberToEdit);

            System.out.println("\nWhat would you like to edit?");
            System.out.println("1. First Name (Current: " + memberToEdit.getFirstName() + ")");
            System.out.println("2. Last Name (Current: " + memberToEdit.getLastName() + ")");
            System.out.println("3. Email (Current: " + memberToEdit.getEmail() + ")");
            System.out.println("4. Phone Number (Current: " + memberToEdit.getPhoneNumber() + ")");
            System.out.println("5. Address (Current: " + memberToEdit.getAddress() + ")");
            System.out.println("6. MemberID(Current: " + memberToEdit.getMemberID() + ")");
            System.out.println("7. Membership Status (Current: " + memberToEdit.getMembershipStatus() + ")");
            System.out.println("8. Save and Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {

                case 1 -> {
                    System.out.println("Enter new first name: ");
                    String firstName = scanner.nextLine();
                    memberToEdit.setFirstName(firstName);
                    System.out.println("First name updated successfully.");
                }
                case 2 -> {
                    System.out.println("Enter new last name: ");
                    String lastName = scanner.nextLine();
                    memberToEdit.setLastName(lastName);
                    System.out.println("Last name updated successfully.");
                }
                case 3 -> {
                    System.out.println("Enter new email: ");
                    String email = scanner.nextLine();
                    while (!email.contains("@") || !email.contains(".")) {
                        System.out.println("Please enter a valid email address: ");
                        email = scanner.nextLine();
                    }
                    memberToEdit.setEmail(email);
                    System.out.println("Email updated successfully.");
                }
                case 4 -> {
                    System.out.println("Enter new phone number: ");
                    String phone = scanner.nextLine();
                    memberToEdit.setPhoneNumber(phone);
                    System.out.println("Phone number updated successfully.");
                }
                case 5 -> {
                    System.out.println("Enter new address: ");
                    String address = scanner.nextLine();
                    memberToEdit.setAddress(address);
                    System.out.println("Address updated successfully.");
                }
                case 6 -> {
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
                case 7 -> {
                    System.out.println("Enter new MemberID: ");
                    String newMemberID = scanner.nextLine();

                    // Check if the new MemberID is already in use
                    boolean idExists = false;
                    for (Person person : members) {
                        if (person instanceof Member && ((Member) person).getMemberID().equals(newMemberID)) {
                            idExists = true;
                            break;
                        }
                    }
                    if (!idExists) {
                        memberToEdit.setMemberID(newMemberID);
                        System.out.println("MemberID updated successfully.");
                    } else {
                        System.out.println("The MemberID is already in use. Please try a different one.");
                    }
                }
                case 8 -> {
                    System.out.println("Changes saved successfully.");
                    editing = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
        return null;
    }

    private int validateInt(){
        while(true){
            try {
                return scanner.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter a valid number");
                scanner.nextLine();
            }
        }
    }

    private void editMember() {
        System.out.println("\n=== Edit Member Information ===");
        System.out.println("Enter member ID to edit: ");
        String memberID = scanner.nextLine();

        ArrayList<Member> members = controller.getMembers();
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
            System.out.println("1. First Name (Current: " + memberToEdit.getFirstName() + ")");
            System.out.println("2. Last Name (Current: " + memberToEdit.getLastName() + ")");
            System.out.println("3. Email (Current: " + memberToEdit.getEmail() + ")");
            System.out.println("4. Phone Number (Current: " + memberToEdit.getPhoneNumber() + ")");
            System.out.println("5. Address (Current: " + memberToEdit.getAddress() + ")");
            System.out.println("6. Membership Status (Current: " + memberToEdit.getMembershipStatus() + ")");
            System.out.println("7. Save and Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter new email: ");
                    String email = scanner.nextLine();
                    while (!email.contains("@") || !email.contains(".")) {
                        System.out.println("Please enter a valid email address: ");
                        email = scanner.nextLine();
                    }
                    memberToEdit.setEmail(email);
                    System.out.println("Email updated successfully.");
                }
                case 2 -> {
                    System.out.println("Enter new phone number: ");
                    String phone = scanner.nextLine();
                    memberToEdit.setPhoneNumber(phone);
                    System.out.println("Phone number updated successfully.");
                }
                case 3 -> {
                    System.out.println("Enter new address: ");
                    String address = scanner.nextLine();
                    memberToEdit.setAddress(address);
                    System.out.println("Address updated successfully.");
                }
                case 4 -> {
                    System.out.println("Changes saved successfully.");
                    editing = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public void printMSG(String msg){
        System.out.println(msg);
    }




}