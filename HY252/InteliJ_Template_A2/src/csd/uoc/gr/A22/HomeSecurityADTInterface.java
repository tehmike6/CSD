package csd.uoc.gr.A22;

import csd.uoc.gr.A21.Sensor;

/**
 * @author Mike Bastakis csd4406
 * */

public interface HomeSecurityADTInterface {
    /**
     * @return String
     * This is an accessor that tells as the state our system is.(Armed/Disabled/Stay Mode)
     * Accessor
     * */
    String getState();

    /**
     * @pre isViolated= false
     * @post state = Armed
     * @throws InternalSensorViolationException,ExternalSensorViolationException
     * Arms the alarms.
     * Transformers
     * */
    void Arm() throws ExternalSensorViolationException, InternalSensorViolationException;

    /**
     * @pre State = disabled AND isViolated(External) = false
     * @post State = Stay Mode
     * @throws ExternalSensorViolationException,NotDisarmedException
     * Changes the state of the alarm system to Stay
     * */
    void StayMode() throws ExternalSensorViolationException, NotDisarmedException;

    /**
     * @param {String} pass
     * @pre pass = Password
     * @post State = disabled
     * @throws WrongPasswordException
     * Disables the alarm system
     * */
    void Disable(String pass) throws WrongPasswordException;

    /**
     * @param {String} pass
     * @pre State = disabled
     * @post password = pass
     * @throws NotDisarmedException
     * Changes the password
     * */
    void changePassword(String pass) throws NotDisarmedException;
}
