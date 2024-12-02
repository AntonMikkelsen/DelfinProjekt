package domainmodel;

public enum SwimmerType {
    CASUAL("Casual", false),
    COMPETITIVE("Competition", true);

    private final String displayName;
    private final boolean requiresCoach;

    SwimmerType(String displayName, boolean requiresCoach) {
        this.displayName = displayName;
        this.requiresCoach = requiresCoach;
    }

    public String getDisplayName() { return displayName; }
    public boolean requiresCoach() { return requiresCoach; }
}
