package csd.uoc.gr.A22;

import csd.uoc.gr.A21.Sensor;
import csd.uoc.gr.A21.SensorLine;
import org.jfugue.Player;

public class HomeSecurityADT implements HomeSecurityADTInterface{
    private Sensor[] ExternalSensors, InternalSensors;
    private SensorLine[] externalLine,internalLine;
    private String State;
    private String password;

    public HomeSecurityADT(SensorLine[] externalLine, SensorLine[] internalLine) {
        this.externalLine = externalLine;
        this.internalLine = internalLine;
        password = "1111";
        State = "Disabled";
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getState() {
        return State;
    }

    @Override
    public void Arm() throws ExternalSensorViolationException, InternalSensorViolationException {
        for(int i=0;i<externalLine.length;i++){
            if(externalLine[i].isViolated()){
                throw new ExternalSensorViolationException();
            }
        }
        for(int i=0;i<internalLine.length;i++){
            if(internalLine[i].isViolated()){
                throw new InternalSensorViolationException();
            }
        }
        Player p = new Player();
        p.play("[80]");
        State = "Armed";
    }

    @Override
    public void StayMode() throws ExternalSensorViolationException, NotDisarmedException {
        if(State.compareTo("Disabled")==0){
            for(int i=0;i<externalLine.length;i++){
                if(externalLine[i].isViolated()){
                    throw new ExternalSensorViolationException();
                }
            }
            Player p = new Player();
            p.play("[80]");
            State = "Stay Mode";
        }else{
            throw new NotDisarmedException();
        }
    }

    @Override
    public void Disable(String pass) throws WrongPasswordException {
        if(pass.compareTo(password) != 0){
            throw new WrongPasswordException();
        }
        Player p = new Player();
        p.play("[80]");
        State = "Disabled";
    }

    @Override
    public void changePassword(String pass) throws NotDisarmedException {
        if(State.compareTo("Disabled") == 0) {
            password = pass;
        }else{
            throw new NotDisarmedException();
        }
    }
}
