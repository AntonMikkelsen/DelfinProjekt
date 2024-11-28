package DomainModel;

import java.util.ArrayList;

public class Teams {
    private ArrayList<Person> Juniorhold = new ArrayList<>();
    private ArrayList<Person> Seniorhold = new ArrayList<>();

    public void addToTeam(Person person){
        if (isJunior(person)) {
            Juniorhold.add(person);
            System.out.println(person + "has been added to the Junior Team");
        }
        else {
            Seniorhold.add(person);
            System.out.println(person + "has been added to the Senior Team");
        }
    }

    public Boolean isJunior(Person person){
        return person.calculateAge() < 18;
    }




}
