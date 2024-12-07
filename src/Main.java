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

        UserInterface ui = new UserInterface();
        FileHandler fileHandler = new FileHandler();
        ArrayList<Member> members = new ArrayList<>();
        Cashier cashier = new Cashier(members);


        Member member1 = new Member("Rikke", "Hansen1", LocalDate.of(1967, 06, 05), "RikkeSnabelA", "50505050", "Vægterparken", "thha0006", MembershipStatus.PASSIVE);
        Member member2 = new Member("Rikke", "Hansen2", LocalDate.of(1930, 06, 05), "RikkeSnabelA", "50505050", "Vægterparken", "55", MembershipStatus.ACTIVE);
        Member member3 = new Member("Rikke", "Hansen3", LocalDate.of(1999, 06, 05), "RikkeSnabelA", "50505050", "Vægterparken", "55", MembershipStatus.ACTIVE);

        // Competitive swimmers:
        CompetitiveSwimmer swimmer1 = new CompetitiveSwimmer("RikkeComp", "HansenComp", LocalDate.of(1967, 06, 05), "RikkeSnabelA", "50505050", "Vægterparken", "thha0006",MembershipStatus.PASSIVE, null, null);
        CompetitiveSwimmer swimmer2 = new CompetitiveSwimmer("Peter", "Lausen", LocalDate.of(2019, 06, 05), "PetLauk", "50503333", "Vægterparken", "thha0006",MembershipStatus.ACTIVE, null, null);
        CompetitiveSwimmer swimmer3 = new CompetitiveSwimmer("Rikmp", "narar", LocalDate.of(1967, 06, 05), "RikkeSnabelA", "50505050", "Vægterparken", "thha0006",MembershipStatus.ACTIVE, null, null);
        CompetitiveSwimmer swimmer4 = new CompetitiveSwimmer("Lars", "HansenComp", LocalDate.of(1955, 06, 05), "RikkeSnabelA", "50505050", "Vægterparken", "thha0006",MembershipStatus.PASSIVE, null, null);
        CompetitiveSwimmer swimmer5 = new CompetitiveSwimmer("Kaus", "KOOO", LocalDate.of(1933, 06, 05), "PetLauk", "50503333", "Vægterparken", "thha0006",MembershipStatus.ACTIVE, null, null);
        CompetitiveSwimmer swimmer6 = new CompetitiveSwimmer("NBROOO", "narar", LocalDate.of(2001, 12, 04), "RikkeSnabelA", "50505050", "Vægterparken", "thha0006",MembershipStatus.ACTIVE, null, null);


        System.out.println(cashier.calculateMembershipFee(swimmer5));

        // Created results for each swimmer
        swimmer1.addResult(new Result(SwimmingDiscipline.BUTTERFLY, 50.3, LocalDate.now(), swimmer1));
        swimmer1.addResult(new Result(SwimmingDiscipline.BACK_CRAWL, 50.4, LocalDate.now(), swimmer1));

        swimmer2.addResult(new Result(SwimmingDiscipline.BREAST_STROKE, 20.3, LocalDate.now(), swimmer2));
        swimmer2.addResult(new Result(SwimmingDiscipline.BACK_CRAWL, 54.3, LocalDate.now(), swimmer2));

        swimmer3.addResult(new Result(SwimmingDiscipline.BREAST_STROKE, 51.4 , LocalDate.now(), swimmer3));
        swimmer3.addResult(new Result(SwimmingDiscipline.BACK_CRAWL, 140.6, LocalDate.now(), swimmer3));
        swimmer3.addResult(new Result(SwimmingDiscipline.BUTTERFLY, 25.4 , LocalDate.now(), swimmer3));

        swimmer4.addResult(new Result(SwimmingDiscipline.BREAST_STROKE, 1.4 , LocalDate.now(), swimmer3));
        swimmer4.addResult(new Result(SwimmingDiscipline.BACK_CRAWL, 14.6, LocalDate.now(), swimmer3));
        swimmer4.addResult(new Result(SwimmingDiscipline.BUTTERFLY, 26.4 , LocalDate.now(), swimmer3));


        swimmer5.addResult(new Result(SwimmingDiscipline.BREAST_STROKE, 50.4 , LocalDate.now(), swimmer3));
        swimmer5.addResult(new Result(SwimmingDiscipline.BACK_CRAWL, 111.6, LocalDate.now(), swimmer3));
        swimmer5.addResult(new Result(SwimmingDiscipline.BUTTERFLY, 21.4 , LocalDate.now(), swimmer3));


        swimmer6.addResult(new Result(SwimmingDiscipline.BREAST_STROKE, 512.4 , LocalDate.now(), swimmer3));
        swimmer6.addResult(new Result(SwimmingDiscipline.BACK_CRAWL, 10.6, LocalDate.now(), swimmer3));
        swimmer6.addResult(new Result(SwimmingDiscipline.BUTTERFLY, 200.4 , LocalDate.now(), swimmer3));


        swimmer3.sortDisciplinesByPerformance();

        System.out.println(swimmer3.getDisciplinesArray());


        System.out.println(swimmer3.getDisciplinesArray().get(0).getShortName());

        System.out.println(swimmer3);

        List<CompetitiveSwimmer> swimmers = Arrays.asList(swimmer1,swimmer2,swimmer3, swimmer4, swimmer5, swimmer6);


        CompetitiveSwimmer.printAllCompSwimmersBestDiscipline(swimmers);

        System.out.println();

        CompetitiveSwimmer.printTop5SwimmersByDiscipline(swimmers);


        System.out.println();


        Team competitveTeamSenior = new Team("Competitive Team Senior", AgeCategory.SENIOR);
        Team competitveTeamJunior = new Team("Competitive Team Junior", AgeCategory.JUNIOR);
        Team regularTeam = new Team("Casual Swimmers");


        regularTeam.addSwimmersToTeam(swimmer1);
        regularTeam.addSwimmersToTeam(swimmer2);
        regularTeam.addSwimmersToTeam(swimmer3);



        competitveTeamJunior.addSwimmersToTeam(swimmer4);


        competitveTeamJunior.addSwimmersToTeam(swimmer1);


        competitveTeamJunior.addSwimmersToTeam(swimmer2);


        competitveTeamSenior.addSwimmersToTeam(swimmer1);


        competitveTeamSenior.addSwimmersToTeam(swimmer2);

        competitveTeamSenior.addSwimmersToTeam(swimmer3);


        System.out.println(competitveTeamSenior.getTeamMembersComp());

        System.out.println(competitveTeamSenior.getTeamMembersRegular());

        System.out.println(regularTeam.getTeamMembersRegular());
        System.out.println(regularTeam.getTeamMembersComp());

        ArrayList<Member> regularSwimmerArrayList = regularTeam.getTeamMembersRegular();

        for(Member member : regularSwimmerArrayList) {
            System.out.println(member.getFirstName());
        }

        String fileName = "members.txt";
        fileHandler.saveMembersToFile(members, fileName);

        ArrayList<Member> loadedMembers = fileHandler.loadMembersFromFile(fileName);

        for (Member member : loadedMembers){
            System.out.println(member);
        }


        //Denne metode tilføjer hvert member til cashierens arrayliste - så cashieren kan få fat i den totale mængde af penge som alle medlemmerne betaler
        cashier.addMember(swimmer1);
        cashier.addMember(swimmer2);
        cashier.addMember(swimmer3);
        cashier.addMember(swimmer4);

        // Members der er i restance skal tilføjes via addMemberInArrear og IKKE addMember -
        // swimmer5.setInArrears(true) behøves ikke da den ikke rigtig bliver brugt til noget, man kan direkte tilføje memebersne til arraylisten i cashier og den vile tælle deres totale debt
        swimmer5.setInArrears(true);
        cashier.addMemberInArrear(swimmer5);
        cashier.addMemberInArrear(swimmer6);

        //Udregner de individiuelle svømmere's kontigent baseret på metoden calculatememebershipfee - det er baseret på alder, rabat og passive/active - man kan også vælge at printe den ud
        System.out.println(swimmer1.getFirstName() + " membership fee: " + cashier.calculateMembershipFee(swimmer1));
        System.out.println(swimmer2.getFirstName() + " membership fee: " + cashier.calculateMembershipFee(swimmer2));
        System.out.println(swimmer3.getFirstName() + " membership fee: " + cashier.calculateMembershipFee(swimmer3));
        System.out.println(swimmer4.getFirstName() + " membership fee: " + cashier.calculateMembershipFee(swimmer4));
        System.out.println(swimmer5.getFirstName() + " membership fee: " + cashier.calculateMembershipFee(swimmer5));





        //Denne metode tager fat i alle medlemmernes kontigent og tilføjer dem til en total pris som så returneres via metoden.
        System.out.println("\nTotal money generated: " + cashier.getTotalGeneratedIncome());
        System.out.println("\nTotal debt from members " + cashier.getTotalOfDebt());

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