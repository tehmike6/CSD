package csd.uoc.gr.A23;

import java.util.Random;

public class GoT {
    public static int getRandomVal(int min, int max) {
        Random rand = new Random();
        int n = rand.nextInt(max - min + 1) + min;
        return n;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HouseStark houseStark = null;
        HouseLannister houseLannister = null;
        try {
            //Get the instances of two houses
            houseStark = HouseStark.getInstance();
            houseLannister = HouseLannister.getInstance();
            //Construct Swordmen and Swords
            Swordman swordman_1 = new Swordman("Eddard", "Stark", 10);
            Swordman swordman_2 = new Swordman("Robb", "Stark");
            Swordman swordman_3 = new Swordman("Tywin", "Lannister", 10);
            Swordman swordman_4 = new Swordman("Jaime", "Lannister");
            Sword sword_1 = new Sword(4);
            Sword sword_2 = new Sword();
            //Swordmen get their swords
            swordman_1.setWeapon(sword_1);
            swordman_3.setWeapon(sword_2);
            //Construct Archers and Arcs
            Archer archer_1 = new Archer("Arya", "Stark", 4);
            Archer archer_2 = new Archer("Tyrion", "Lannister");
            Arc arc_1 = new Arc(1);
            Arc arc_2 = new Arc();
            //Archers get their arcs
            archer_1.setWeapon(arc_1);
            archer_2.setWeapon(arc_2);
            //Add some swordmen and archers to houseStark army
            houseStark.addSoldier(swordman_1);
            houseStark.addSoldier(swordman_2);
            houseStark.addSoldier(archer_1);
            //Add some swordmen and archers to houseLannister army
            houseLannister.addSoldier(swordman_3);
            houseLannister.addSoldier(swordman_4);
            houseLannister.addSoldier(archer_2);
            Soldier soldierStark = null;
            Soldier soldierLannister = null;
            while (!houseStark.isDefeated() && !houseLannister.isDefeated()) {
                if (soldierStark == null || soldierStark.isDefeated()) {
                    if (soldierStark != null) {
                        System.out.println("Stark Army: " + soldierStark.getCallSign() + " died in the battlefield.");
                    }
                    soldierStark = houseStark.getSoldier();
                    System.out.println("Stark Army: " + soldierStark.toString() + " Entering the battlefield!");
                    if (soldierStark.hasWeapon()) {
                        System.out.println("\tWeapon info: " + soldierStark.getWeapon().toString());
                    }
                    System.out.println("\n");
                }
                if (soldierLannister == null || soldierLannister.isDefeated()) {
                    if (soldierLannister != null) {
                        System.out.println("Lannister Army: " + soldierLannister.getCallSign() + " died in the battlefield.");
                    }
                    soldierLannister = houseLannister.getSoldier();
                    System.out.println("Lannister Army: " + soldierLannister.toString() + " Entering the battlefield!");
                    if (soldierLannister.hasWeapon()) {
                        System.out.println("\tWeapon info: " + soldierLannister.getWeapon().toString());
                    }
                    System.out.println("\n");
                }
                int turn = getRandomVal(0, 1);
                if (turn == 0) {
                    //Stark's soldier starts first!
                    soldierStark.attack(soldierLannister);
                    if (!soldierLannister.isDefeated()) {
                        soldierLannister.attack(soldierStark);
                    }
                } else {
                    //Lannisters's soldier starts first!
                    soldierLannister.attack(soldierStark);
                    if (!soldierStark.isDefeated()) {
                        soldierStark.attack(soldierLannister);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println(houseStark.isDefeated() ? houseLannister.toString() : houseStark.toString());
            System.out.println("Won the Iron Throne!");
        }
    }
}

