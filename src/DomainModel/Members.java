package DomainModel;

public class Members extends Person {
    private boolean isActive; // Aktiv/passiv medlemskab
    private boolean inArrears; // Restancestatus

    public Members(String name, int age, String email, int userid, String gender, boolean isActive, boolean inArrears) {
        super(name, age, email, userid, gender);
        this.isActive = isActive;
        this.inArrears = inArrears;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isInArrears() {
        return inArrears;
    }

    // Setters
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setInArrears(boolean inArrears) {
        this.inArrears = inArrears;
    }

    @Override
    public String toString() {
        return "Medlem: " + getName() + ", Alder: " + getAge() + ", Aktiv: " + isActive + ", I restance: " + inArrears;
    }
}