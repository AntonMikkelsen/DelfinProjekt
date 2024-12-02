package domainmodel;

public enum MembershipStatus {

    ACTIVE("Active Member"),
    PASSIVE("Passive Member");

    private final String displayName;

    MembershipStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}