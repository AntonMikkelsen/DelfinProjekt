import datasource.Controller;
import datasource.FileHandler;
import domainmodel.Member;
import domainmodel.MembershipStatus;


import java.time.LocalDate;
import java.util.ArrayList;

import ui.UserInterface;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller();
        Controller.MembershipRegistrationService hgg = new Controller.MembershipRegistrationService(controller);
        UserInterface ui = new UserInterface(hgg);
        FileHandler fileHandler = new FileHandler();
        ArrayList<Member> members = new ArrayList<>();


        Member member1 = new Member("Rikke", "Hansen", LocalDate.of(1967, 6, 5), "RikkeSnabelA", "50505050", "Vægterparken", "thha0006",MembershipStatus.PASSIVE);
        Member member2 = new Member("Rikke", "Hansen", LocalDate.of(1930, 6, 5), "RikkeSnabelA", "50505050", "Vægterparken", "55",MembershipStatus.ACTIVE);
        Member member3 = new Member("Rikke", "Hansen", LocalDate.of(2020, 6, 5), "RikkeSnabelA", "50505050", "Vægterparken", "55",MembershipStatus.ACTIVE);

        // CompetitiveSwimmer swimmer1 = new CompetitiveSwimmer();


        members.add(member1);
        members.add(member2);
        members.add(member3);

        String fileName = "members.csv";
        fileHandler.saveMembersToFile(members);



        ArrayList<Member> loadedMembers = fileHandler.loadMembersFromFile(fileName);

        for (Member member : loadedMembers){
            hgg.addMember(member);
            System.out.println(member);

        }

        ui.startMenu();






       /* //Test til member / ændring af medlemskabsaktivitet osv.
            Member member = new Member(
                    "Hussain",
                    "Ali",
                    LocalDate.of(2000, 12, 7),
                    "hussain.ali@example.com",
                    "12345678",
                    "Main Street 123",
                    "M123",
                    MembershipStatus.ACTIVE
            );

            System.out.println(member.getMemberID());
            System.out.println(member.getMembershipStatus());
            member.changeMembershipToPassive();
            System.out.println(member.getMembershipStatus());
            System.out.println(member.getFirstName());
            System.out.println(member.getDateOfBirth());
            System.out.println(member.calculateAge()); */

    }
}