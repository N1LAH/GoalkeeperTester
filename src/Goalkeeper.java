import java.util.Random;

// Base Goalkeeper class
public class Goalkeeper {
    protected String name;
    protected String team;
    protected double psxgGa;
    protected double goalsAgainst;
    protected double savePercentage;
    protected double psxgPerShotOnTarget;
    protected double cleanSheetPercentage;
    private Random random = new Random();


    // Constructor
    public Goalkeeper(String name, String team, double psxgGa, double goalsAgainst,
                      double savePercentage, double psxgPerShotOnTarget, double cleanSheetPercentage) {
        this.name = name;
        this.team = team;
        this.psxgGa = psxgGa;
        this.goalsAgainst = goalsAgainst;
        this.savePercentage = savePercentage;
        this.psxgPerShotOnTarget = psxgPerShotOnTarget;
        this.cleanSheetPercentage = cleanSheetPercentage;
    }

    // Method to display goalkeeper stats
    public void displayStats() {
        System.out.println("Goalkeeper: " + name + " (" + team + ")");
        System.out.println("PSxG-GA: " + psxgGa);
        System.out.println("Goals Against per 90: " + goalsAgainst);
        System.out.println("Save Percentage: " + savePercentage + "%");
        System.out.println("PSxG per Shot on Target: " + psxgPerShotOnTarget);
        System.out.println("Clean Sheet Percentage: " + cleanSheetPercentage + "%");
    }


}


