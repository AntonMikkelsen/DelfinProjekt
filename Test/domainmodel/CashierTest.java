package domainmodel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CashierTest {

    @Test
    void calculateMembershipFee() {
        //Arrange
        double expectedCost = 1600;
        LocalDate birthday = LocalDate.of(2000,12,07);
        Member member1 = new Member("Hussain", "Mahmoud", birthday, "hussainomar5@gmail.com", "27280295", "Mølledammen", "492014fas", MembershipStatus.ACTIVE);
        ArrayList<Member> members = new ArrayList<>();
        members.add(member1);
        Cashier cashier = new Cashier(members);



        // Act
        double actualCost = cashier.calculateMembershipFee(member1);


        // Assert - Test  of normal cost Senior
        Assertions.assertEquals(expectedCost, actualCost);
    }


    @Test
    void calculateMembershipFeePassive() {
        //Arrange
        double expectedCost = 500;
        LocalDate birthday = LocalDate.of(2000,12,07);
        Member member1 = new Member("Hussain", "Mahmoud", birthday, "hussainomar5@gmail.com", "27280295", "Mølledammen", "492014fas", MembershipStatus.PASSIVE);
        ArrayList<Member> members = new ArrayList<>();
        members.add(member1);
        Cashier cashier = new Cashier(members);



        // Act
        double actualCost = cashier.calculateMembershipFee(member1);


        // Assert - Test  of passive membership
        Assertions.assertEquals(expectedCost, actualCost);
    }


    @Test
    void calculateMembershipFeeSeniorDiscount() {
        //Arrange
        double expectedCost = 1200;
        LocalDate birthday = LocalDate.of(1900,12,07);
        Member member1 = new Member("Hussain", "Mahmoud", birthday, "hussainomar5@gmail.com", "27280295", "Mølledammen", "492014fas", MembershipStatus.ACTIVE);
        ArrayList<Member> members = new ArrayList<>();
        members.add(member1);
        Cashier cashier = new Cashier(members);



        // Act
        double actualCost = cashier.calculateMembershipFee(member1);


        // Assert - Test  of normal cost with senior discount
        Assertions.assertEquals(expectedCost, actualCost);
    }



    @Test
    void calculateMembershipFeeJuniorCost() {
        //Arrange
        double expectedCost = 1000;
        LocalDate birthday = LocalDate.of(2020,12,07);
        Member member1 = new Member("Hussain", "Mahmoud", birthday, "hussainomar5@gmail.com", "27280295", "Mølledammen", "492014fas", MembershipStatus.ACTIVE);
        ArrayList<Member> members = new ArrayList<>();
        members.add(member1);
        Cashier cashier = new Cashier(members);



        // Act
        double actualCost = cashier.calculateMembershipFee(member1);


        // Assert - Test  of junior cost
        Assertions.assertEquals(expectedCost, actualCost);
    }



}