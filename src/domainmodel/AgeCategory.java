package domainmodel;

import static java.lang.Float.POSITIVE_INFINITY;

public enum AgeCategory {
    JUNIOR(0, 17, "Junior Swimmer"),
    SENIOR(18, (int) POSITIVE_INFINITY, "Senior Swimmer");

    private final int minAge;
    private final int maxAge;
    private final String displayName;

    AgeCategory(int minAge, int maxAge, String displayName) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.displayName = displayName;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static AgeCategory fromAge(int age) {
        if (age >= JUNIOR.minAge && age <= JUNIOR.maxAge) return JUNIOR;
        return SENIOR;
    }
}