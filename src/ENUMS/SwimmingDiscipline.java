package ENUMS;

public enum SwimmingDiscipline {
    BACK_CRAWL("Back Crawl", "Backstroke"),
    BREAST_STROKE("Breast Stroke", "Breaststroke"),
    BUTTERFLY("Butterfly", "Fly"),
    CRAWL("Crawl", "Freestyle");

    private final String fullName;
    private final String shortName;

    SwimmingDiscipline(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public String getFullName() { return fullName; }
    public String getShortName() { return shortName; }
}
