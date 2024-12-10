package datasource;

import domainmodel.Member;
import domainmodel.MembershipStatus;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileHandler {
    private final String fileName = "members.csv";

    public void saveMembersToFile(ArrayList<Member> membersArrayList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Member members : membersArrayList) {
                writer.write(members.getFirstName() + ", " + members.getLastName() + ", " +  members.getDateOfBirth()
                        + ", " + members.getEmail()
                        + ", " + members.getPhoneNumber()
                        + ", " + members.getAddress() + ", " + members.getMemberID()
                        + ", " + members.getMembershipStatus());
                writer.newLine();
            }
            System.out.println("Members have been added to " + fileName);

        } catch (IOException e) {
            System.err.println("Input fejl ved indskrivning i fil" + e.getMessage());
        }
    }

    public ArrayList<Member> loadMembersFromFile() {
        ArrayList<Member> membersArrayList = new ArrayList<>();
        File file = new File(fileName);

//        if (!file.exists()) {
//            System.out.println("Filen findes ikke endnu. Ingen medlemmer er indlæst.");
//            return membersArrayList;
//        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");

                //if (data.length == 8) {
                String firstName = data[0].trim();
                String lastName = data[1].trim();
                LocalDate dateOfBirth = LocalDate.parse((data[2].trim()));
                String memberEmail = data[3].trim();
                String phoneNumber = data[4].trim();
                String address = data[5].trim();
                String memberId = data[6].trim();
                MembershipStatus memberShipStatus = MembershipStatus.valueOf(data[7].trim());

                Member members = new Member(firstName, lastName, dateOfBirth,
                        memberEmail, phoneNumber, address, memberId, memberShipStatus);
                membersArrayList.add(members);
                //} else {
                //    System.out.println("Datatype not accepted in line: " + line);
                //}
            }
            System.out.println("Members have been loaded from " + fileName);
            for (Member members : membersArrayList){
                System.out.println(members);
            }
        } catch (IOException e) {
            System.err.println("fejl ved læsning af fil" + e.getMessage());
        }
        return membersArrayList;
    }
}
