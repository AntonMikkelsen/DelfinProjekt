import datasource.Controller;
import datasource.FileHandler;
import domainmodel.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


        // Competitive swimmers:
        CompetitiveSwimmer swimmer1 = new CompetitiveSwimmer("RikkeComp", "HansenComp", LocalDate.of(1967, 06, 05), "RikkeSnabelA", "50505050", "Vægterparken", "thha0006",MembershipStatus.PASSIVE, null, null);
        CompetitiveSwimmer swimmer2 = new CompetitiveSwimmer("Peter", "Lausen", LocalDate.of(1955, 06, 05), "PetLauk", "50503333", "Vægterparken", "thha0006",MembershipStatus.ACTIVE, null, null);
        CompetitiveSwimmer swimmer3 = new CompetitiveSwimmer("Rikmp", "narar", LocalDate.of(1967, 06, 05), "RikkeSnabelA", "50505050", "Vægterparken", "thha0006",MembershipStatus.ACTIVE, null, null);

        // D
        swimmer1.addResult(new Result(SwimmingDiscipline.BUTTERFLY, 50.3, LocalDate.now()));
        swimmer1.addResult(new Result(SwimmingDiscipline.BACK_CRAWL, 50.4, LocalDate.now()));

        swimmer2.addResult(new Result(SwimmingDiscipline.BREAST_STROKE, 20.3, LocalDate.now()));
        swimmer2.addResult(new Result(SwimmingDiscipline.BACK_CRAWL, 54.3, LocalDate.now()));

        swimmer3.addResult(new Result(SwimmingDiscipline.BREAST_STROKE, 51.4 , LocalDate.now()));
        swimmer3.addResult(new Result(SwimmingDiscipline.BACK_CRAWL, 100.6, LocalDate.now()));
        swimmer3.addResult(new Result(SwimmingDiscipline.BUTTERFLY, 25.4 , LocalDate.now()));

        List<CompetitiveSwimmer> swimmers = Arrays.asList(swimmer1);


        CompetitiveSwimmer.printAllCompSwimmersBestDiscipline(swimmers);

        System.out.println();



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