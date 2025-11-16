import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomXGSet {
    private String matchName;
    private String finalScore;
    private List<Double> shotXGs;   // List to store xG values
    private List<Double> shotXGOTs; // List to store xGOT values (if available)

    // Constructor
    public CustomXGSet() {
        this.shotXGs = new ArrayList<>();
        this.shotXGOTs = new ArrayList<>();
    }

    // Add a shot with xG only (for cases where xGOT is unavailable)
    public void addShotXG(double xg) {
        shotXGs.add(xg);
    }

    // Add a shot with both xG and xGOT
    public void addShotXGAndXGOT(double xg, double xgot) {
        shotXGs.add(xg);
        shotXGOTs.add(xgot);
    }

    // Set match metadata (name + final score)
    public void setMatchInfo(String name, String score) {
        this.matchName = name;
        this.finalScore = score;
    }

    // Simulate when only xG data is available
    public void simulateWithXGOnly(Goalkeeper gk, int timesRan, int realGoalsConceded) {
        if (shotXGs.isEmpty()) {
            System.out.println("No xG values added. Please add shots before simulating.");
            return;
        }

        Random rand = new Random();
        int totalSaves = 0, totalGoalsConceded = 0, cleanSheets = 0;
        double totalPsxg = 0.0;
        int shotsOnTarget = shotXGs.size();
        int realOutcomeCount = 0; // Tracks how often the real score occurs

        System.out.println("\nSimulating match: " + matchName + " (" + finalScore + ")");
        System.out.println("Shots on Target: " + shotsOnTarget);

        double difficultyFactor = 50; // Adjusts difficulty impact

        // Run the same match scenario multiple times
        for (int t = 1; t <= timesRan; t++) {
            int saves = 0;
            int goalsConceded = 0;
            double matchPsxg = 0.0;

            for (double shotXG : shotXGs) {
                matchPsxg += shotXG;

                // Adjust save probability using only xG
                double adjustedSaveChance = gk.savePercentage - (shotXG * difficultyFactor);

                // Ensure save chance stays between 0% and 100%
                adjustedSaveChance = Math.max(0, Math.min(100, adjustedSaveChance));

                boolean save = (rand.nextDouble() < (adjustedSaveChance / 100));

                if (save) {
                    saves++;
                } else {
                    goalsConceded++;
                }
            }

            // Track how often the real outcome occurs
            if (goalsConceded == realGoalsConceded) {
                realOutcomeCount++;
            }

            // Update cumulative stats
            totalSaves += saves;
            totalGoalsConceded += goalsConceded;
            totalPsxg += matchPsxg;

            // Apply clean sheet logic
            if (goalsConceded == 0 && rand.nextDouble() < (gk.cleanSheetPercentage / 100)) {
                cleanSheets++;
            }
        }

        // Calculate averages
        double avgSaves = (double) totalSaves / timesRan;
        double avgGoalsConceded = (double) totalGoalsConceded / timesRan;
        double avgPsxg = totalPsxg / timesRan;
        double cleanSheetPercentage = ((double) cleanSheets / timesRan) * 100;
        double realOutcomeProbability = ((double) realOutcomeCount / timesRan) * 100;

        // Display results
        System.out.println("\nSimulation Results (Using xG Only, Averaged over " + timesRan + " matches):");
        System.out.println("Average Saves per Match: " + String.format("%.2f", avgSaves));
        System.out.println("Average Goals Conceded per Match: " + String.format("%.2f", avgGoalsConceded));
        System.out.println("Average Expected Goals Against (PSxG): " + String.format("%.2f", avgPsxg));
        System.out.println("Clean Sheet Percentage (Simulated): " + String.format("%.2f", cleanSheetPercentage) + "%");
        System.out.println("Probability of Real Match Outcome (" + finalScore + "): "
                + String.format("%.2f", realOutcomeProbability) + "%");
    }

    public void simulateWithXGAndXGOT(Goalkeeper gk, int timesRan, int realGoalsConceded) {
        if (shotXGs.isEmpty() || shotXGOTs.isEmpty() || shotXGs.size() != shotXGOTs.size()) {
            System.out.println("Mismatch or missing xG/xGOT values. Ensure equal numbers of xG and xGOT shots.");
            return;
        }

        Random rand = new Random();
        int totalSaves = 0, totalGoalsConceded = 0, cleanSheets = 0;
        double totalPsxg = 0.0;
        int shotsOnTarget = shotXGs.size();
        int realOutcomeCount = 0; // Tracks how often the real score occurs

        System.out.println("\nSimulating match: " + matchName + " (" + finalScore + ")");
        System.out.println("Shots on Target: " + shotsOnTarget);

        double difficultyFactor = 60; // Adjusts impact of xGOT

        // Run the same match scenario multiple times
        for (int t = 1; t <= timesRan; t++) {
            int saves = 0;
            int goalsConceded = 0;
            double matchPsxg = 0.0;

            for (int i = 0; i < shotsOnTarget; i++) {
                double shotXG = shotXGs.get(i);
                double shotXGOT = shotXGOTs.get(i);
                matchPsxg += shotXG;

                // Adjust save probability using xGOT
                double adjustedSaveChance = gk.savePercentage - (shotXGOT * difficultyFactor);

                // Ensure adjusted save chance stays between 0% and 100%
                adjustedSaveChance = Math.max(0, Math.min(100, adjustedSaveChance));

                boolean save = (rand.nextDouble() < (adjustedSaveChance / 100));

                if (save) {
                    saves++;
                } else {
                    goalsConceded++;
                }
            }

            // Track how often the real outcome occurs
            if (goalsConceded == realGoalsConceded) {
                realOutcomeCount++;
            }

            // Update cumulative stats
            totalSaves += saves;
            totalGoalsConceded += goalsConceded;
            totalPsxg += matchPsxg;

            // Apply clean sheet logic
            if (goalsConceded == 0 && rand.nextDouble() < (gk.cleanSheetPercentage / 100)) {
                cleanSheets++;
            }
        }

        // Calculate averages
        double avgSaves = (double) totalSaves / timesRan;
        double avgGoalsConceded = (double) totalGoalsConceded / timesRan;
        double avgPsxg = totalPsxg / timesRan;
        double cleanSheetPercentage = ((double) cleanSheets / timesRan) * 100;
        double realOutcomeProbability = ((double) realOutcomeCount / timesRan) * 100;

        // Display results
        System.out.println("\nSimulation Results (Using xG + xGOT, Averaged over " + timesRan + " matches):");
        System.out.println("Average Saves per Match: " + String.format("%.2f", avgSaves));
        System.out.println("Average Goals Conceded per Match: " + String.format("%.2f", avgGoalsConceded));
        System.out.println("Average Expected Goals Against (PSxG): " + String.format("%.2f", avgPsxg));
        System.out.println("Clean Sheet Percentage (Simulated): " + String.format("%.2f", cleanSheetPercentage) + "%");
        System.out.println("Probability of Real Match Outcome (" + finalScore + "): "
                + String.format("%.2f", realOutcomeProbability) + "%");
    }

}
