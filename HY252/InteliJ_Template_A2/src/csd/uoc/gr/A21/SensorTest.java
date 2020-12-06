package csd.uoc.gr.A21;

public class SensorTest {
    public static void main(String[] args) {
        //System.out.println(new Sensor("S01",false,false));
        //System.out.println(new LaserSensor("S02",true,false,4F));

        SensorLine line = new SensorLine();
        try {
            for (int i = 0; i < 100; i++) {
                if (i % 2 == 0) {
                    if (i % 4 == 0) {
                        line.add(new Sensor("SID"+i, true, false));
                        continue;
                    }
                    line.add(new Sensor("SID"+i, false, false));
                }else {
                    line.add(new LaserSensor("SID"+i,false,false,12F));
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println(line.toString());
    }
}
