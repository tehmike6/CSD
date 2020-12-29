package csd.uoc.gr.A22;

import csd.uoc.gr.A21.Sensor;
import csd.uoc.gr.A21.SensorLine;
import org.junit.Before;
import org.junit.Test;

public class MyTests {
    private HomeSecurityADT system;
    private HomeSecurityADT ViolatedSystem;

    @Before
    public void setup(){
        SensorLine internal = new SensorLine("Internal");
        SensorLine external = new SensorLine("External");
        SensorLine externalVio = new SensorLine("InternalVio");
        try {
            for (int i = 0; i < 100; i++) {
                internal.add(new Sensor("SID" + i, false, false));
                external.add(new Sensor("SID" + i, false, false));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        try {
            for (int i = 0; i < 100; i++) {
                externalVio.add(new Sensor("SID" + i, true, false));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        SensorLine[] internalArr = new SensorLine[1];
        SensorLine[] externalArr = new SensorLine[1];
        SensorLine[] externalVioArr = new SensorLine[1];
        internalArr[0] = internal;
        externalArr[0] = external;
        externalVioArr[0] = externalVio;

        system = new HomeSecurityADT(internalArr,externalArr);
        ViolatedSystem = new HomeSecurityADT(internalArr,externalVioArr);
    }
    /**
     * Testing if Arm will throw ExternalSensorViolationException
     * */
    @Test (expected = InternalSensorViolationException.class)
    public void testArm(){
        try {
            ViolatedSystem.Arm();
        }catch (Exception e){
            System.out.println("Expected: "+e);
        }
        assert system.getState().compareTo("Disabled") == 0;
    }
    /**
     * Testing if Arm will not throw Exception.
     * */
    @Test
    public void testArm2(){
        try {
            system.Arm();
        }catch (Exception e){
            System.out.println("Unexpected: "+e);
        }
        assert system.getState().compareTo("Armed") == 0;
    }
    /**
     * Expect to throw NotDisarmedException
     * */
    @Test
    public void testChangePass(){
        String pass = "1234";
        try{
            system.Arm();
            system.changePassword(pass);
        }catch (Exception e){
            System.out.println("Expected: "+e);
        }
        assert pass.compareTo(system.getPassword())!=0;
    }
    /**
     * Expect to not throw anything
     * */
    @Test
    public void testChangePass2(){
        String pass = "1234";
        try{
            system.changePassword(pass);
        }catch (Exception e){
            System.out.println("Unexpected: "+e);
        }
        assert pass.compareTo(system.getPassword())==0;
    }
    /**
     * Expect to throw WrongPasswordException
     * */
    @Test
    public void testDisable(){
        String pass = "1234";
        try{
            system.Arm();
            system.Disable(pass);
        }catch (Exception e){
            System.out.println("Expected: "+e);
        }
        assert system.getState().compareTo("Armed")==0;
    }
    /**
     * Expect to not throw WrongPasswordException
     * */
    @Test
    public void testDisable2(){
        String pass = "1111";
        try{
            system.Arm();
            system.Disable(pass);
        }catch (Exception e){
            System.out.println("Expected: "+e);
        }
        assert system.getState().compareTo("Disabled")==0;
    }
    /**
     * Expect to throw ExternalSensorViolationException
     * */
    @Test
    public void testStay(){
        String pass = "1111";
        try{
            ViolatedSystem.StayMode();
        }catch (Exception e){
            System.out.println("Expected: "+e);
        }
        assert system.getState().compareTo("Disabled")==0;
    }
    /**
     * Expect to throw NotDisarmedException
     * */
    @Test
    public void testStay2(){
        String pass = "1111";
        try{
            system.Arm();
            system.StayMode();
        }catch (Exception e){
            System.out.println("Expected: "+e);
        }
        assert system.getState().compareTo("Armed")==0;
    }
    /**
     * Expect not throw anything
     * */
    @Test
    public void testStay3(){
        String pass = "1111";
        try{
            system.StayMode();
        }catch (Exception e){
            System.out.println("Unexpected: "+e);
        }
        assert system.getState().compareTo("Stay Mode")==0;
    }
}
