import DataSource.Controller;
import DomainModel.Member;

import DomainModel.*;
import ENUMS.MembershipStatus;


import java.time.LocalDate;

import ENUMS.MembershipStatus;
import UI.UserInterface;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller();
        MembershipRegistrationService hgg = new MembershipRegistrationService(controller);
        UserInterface ui = new UserInterface(hgg);


        Member member1 = new Member("Rikke", "Hansen", LocalDate.of(1967, 6, 5), "RikkeSnabelA", "50505050", "Vægterparken", "thha0006",MembershipStatus.PASSIVE);
        Member member2 = new Member("Rikke", "Hansen", LocalDate.of(1930, 6, 5), "RikkeSnabelA", "50203040", "Vægterparken", "55",MembershipStatus.ACTIVE);
        Member member3 = new Member("Rikke", "Hansen", LocalDate.of(2020, 6, 5), "RikkeSnabelA", "50554322", "Vægterparken", "55",MembershipStatus.ACTIVE);
        hgg.addMember(member1);
        hgg.addMember(member2);
        hgg.addMember(member3);

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


        System.out.println(member1);
        System.out.println(member2);
        System.out.println(member3);





    }
}