package datasource;
import domainmodel.Member;
import domainmodel.MembershipStatus;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileHandler {

File fileName = new File("members.csv");

    public void saveMembersToFile (ArrayList<Member> membersArrayList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for (Member members : membersArrayList){
                writer.write(members.getFirstName() + ", " + members.getLastName() + ", " + members.getMemberID() + ", "
                        + members.getMembershipStatus() + ", " + members.getPhoneNumber() + ", " + members.getEmail()
                        + ", " + members.getDateOfBirth());
                writer.newLine();
            }
            System.out.println("Members have been added to" + fileName);

    } catch (IOException e){
            System.err.println("Input not accepted" + e.getMessage());
        }
    }
//    public ArrayList<Member> loadMembersFromFile(String fileName) {
//        ArrayList<Member> membersArrayList = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] data = line.split(", ");
//                if (data.length == 8) {
//                    String firstName = data[0].trim();
//                    String lastName = data[1].trim();
//                    String memberId = data[2].trim();
//                    MembershipStatus memberShipStatus = MembershipStatus.valueOf(data[3].trim());
//                    String phoneNumber = data[4].trim();
//                    String address = data[5].trim();
//                    String memberEmail = data[6].trim();
//                    LocalDate dateOfBirth = LocalDate.parse((data[7].trim()));
////                    boolean isActive = Boolean.parseBoolean(data[8].trim());
////                    boolean isPassive = Boolean.parseBoolean(data[9].trim());
//
//                    Member members = new Member(firstName, lastName, dateOfBirth,
//                            memberEmail, phoneNumber, address, memberId, memberShipStatus);
//
//                    membersArrayList.add(members);
//
//                } else {
//                    System.out.println("Datatype not accepted in line: " + line);
//                }
//            }
//            System.out.println("Members have been loaded from " + fileName);
//        } catch (IOException e) {
//            System.err.println("Loading file not possible" + e.getMessage());
//        }
//
//        return membersArrayList;
//    }

    public ArrayList<Member> loadMembersFromFile(String fileName) {
        ArrayList<Member> loadedMembers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            reader.readLine(); // Skip the header line if it's present
            while ((line = reader.readLine()) != null) {
                // Split the line by commas and create a Member object
                Member member = parseMemberFromCsv(line); // Parse CSV line to Member
                if (member != null) {
                    loadedMembers.add(member);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while loading members from CSV: " + e.getMessage());
        }
        return loadedMembers;
    }

    private Member parseMemberFromCsv(String line) {
        String[] parts = line.split(",");
        if (parts.length == 8) {
            String firstName = parts[0];
            String lastName = parts[1];
            String memberId = parts[2];
            MembershipStatus memberShipStatus = MembershipStatus.valueOf(parts[3]);
            String phoneNumber = parts[4];
            String address = parts[5];
            String memberEmail = parts[6];
            LocalDate dateOfBirth = LocalDate.parse((parts[7]));


            return new Member(firstName, lastName, dateOfBirth, memberEmail, phoneNumber, address, memberId, memberShipStatus); // Adjust constructor based on your Member class
        }
        return null; // Return null if the CSV line is not in the expected format
    }

}
