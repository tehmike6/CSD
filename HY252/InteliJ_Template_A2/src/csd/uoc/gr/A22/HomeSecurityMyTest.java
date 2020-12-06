package csd.uoc.gr.A22;

import csd.uoc.gr.A21.Sensor;
import csd.uoc.gr.A21.SensorLine;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class HomeSecurityMyTest {

    public static void main(String[] args) {
        SensorLine internal = new SensorLine("Internal");
        SensorLine external = new SensorLine("External");
        try {
            for (int i = 0; i < 100; i++) {
                internal.add(new Sensor("SID" + i, false, false));
                external.add(new Sensor("SID" + i, false, false));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        SensorLine[] internalArr = new SensorLine[1];
        SensorLine[] externalArr = new SensorLine[1];
        internalArr[0] = internal;
        externalArr[0] = external;

        HomeSecurityADT system = new HomeSecurityADT(internalArr,externalArr);

        try {
            system.Arm();
            system.Disable("1111");
            system.changePassword("1911");
            system.StayMode();
            system.Disable("1911");
        }catch (ExternalSensorViolationException | InternalSensorViolationException | NotDisarmedException | WrongPasswordException e){
            System.out.println(e);
        }
        System.out.println("Checking with Junit");
        Result result = JUnitCore.runClasses(MyTests.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}
