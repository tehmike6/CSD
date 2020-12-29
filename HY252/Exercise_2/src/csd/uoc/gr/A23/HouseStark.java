package csd.uoc.gr.A23;

public class HouseStark extends GreatHouse {
    private static HouseStark instance = null;

    public HouseStark() {
        super( "Stark", "A grey direwolf on a white field", "Winter Is Coming", "Eddard Stark");
    }
    public static HouseStark getInstance(){
        if(instance == null) {
            instance = new HouseStark();
        }
        return instance;
    }
}
