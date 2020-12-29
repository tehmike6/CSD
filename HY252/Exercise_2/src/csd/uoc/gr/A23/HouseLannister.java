package csd.uoc.gr.A23;

public class HouseLannister extends GreatHouse{
    private static HouseLannister instance = null;

    private HouseLannister(){
        super("Lannister","A golden lion rampant on a crimson field","A Lannister Always Pays His Debts","Tywin Lannister");
    }
    public static HouseLannister getInstance(){
        if(instance == null) {
            instance = new HouseLannister();
        }
        return instance;
    }
}
