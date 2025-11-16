public class GoalkeeperSimulation {
    public static void main(String[] args) {
        // Create a Goalkeeper (Alisson Becker example)
        Goalkeeper terStegen = new Goalkeeper("Marc-André ter Stegen (24/25", "Barcelona",
                0.05, .82, 74.3, 0.47, 54.5);
        Goalkeeper courtois = new Goalkeeper("Thibaut Courtois", "Barcelona",
                0.15, 0.90, 78.7, 0.27, 35.1);

        Goalkeeper bravo = new Goalkeeper("Claudio Bravo", "Real Betis",
                0.05, 1.59, 78.3, 0.19, 0.0);
        Goalkeeper cillessen = new Goalkeeper("Jasper Cillessen", "NEC Nijmegen",
                004, 1.29, 78.0, 0.20, 21.8);

        // Create Wojciech Szczęsny as a GK
        Goalkeeper szczesny = new Goalkeeper("Wojciech Szczęsny", "FC Barcelona",
                0.11, 0.89, 75.4, 0.28, 47.4);




        // Create match scenario for UCL 2020 (Bayern 8-2 Barcelona)


        // Create match scenario for Barcelona vs Liverpool (4-0 at Anfield)
        CustomXGSet Anfield = new CustomXGSet();
        Anfield.setMatchInfo("Barcelona vs Liverpool", "Liverpool 4-0 Barcelona");;

        CustomXGSet BarcaBenfica = new CustomXGSet();
        BarcaBenfica.setMatchInfo("Benfica vs FC Barcelona", "Benfica 0 - 1 FC Barcelona");

        CustomXGSet RomaBarca = new CustomXGSet();
        RomaBarca.setMatchInfo("Roma vs FC Barcelona", "Roma 3 - 0 FC Barcelona");


        // Anfield
        Anfield.addShotXG(0.43);  // Salah (16')
        Anfield.addShotXG(0.52);  // Thiago (16')
        Anfield.addShotXG(0.04);  // Salah (17')
        Anfield.addShotXG(0.58);  // Mané (21')
        Anfield.addShotXG(0.04);  // Salah (34')
        Anfield.addShotXG(0.08);  // Salah (64')
        Anfield.addShotXG(0.07);  // Salah (69')// Jota (80')
        Anfield.addShotXG(0.31);  // Salah (82')

        BarcaBenfica.addShotXG(0.05);  // Aktürkoğlu (1') - Saved
        BarcaBenfica.addShotXG(0.12);  // Kökçü (25') - Saved
        BarcaBenfica.addShotXG(0.09);  // Aktürkoğlu (43') - Saved
        BarcaBenfica.addShotXG(0.04);  // Kökçü (47') - Saved
        BarcaBenfica.addShotXG(0.17);
        BarcaBenfica.addShotXG(0.04);
        BarcaBenfica.addShotXG(0.14);
        BarcaBenfica.addShotXG(0.1);

        RomaBarca.addShotXG(0.49);
        RomaBarca.addShotXG(0.04);
        RomaBarca.addShotXG(0.79);
        RomaBarca.addShotXG(0.07);
        RomaBarca.addShotXG(0.46);
        RomaBarca.addShotXG(0.04);









        System.out.println("Simulation for Szczęsny as FC Barcelona's GK: ");


        RomaBarca.simulateWithXGOnly(szczesny, 100, 3);




    }
}
