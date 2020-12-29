package csd.uoc.gr.A24;

import csd.uoc.gr.A21.LaserSensor;
import csd.uoc.gr.A21.Sensor;
import csd.uoc.gr.A21.SensorLine;
import csd.uoc.gr.A22.ExternalSensorViolationException;
import csd.uoc.gr.A22.HomeSecurityADT;
import csd.uoc.gr.A22.InternalSensorViolationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Takes as input an array of sensorlines and for each one of them
 * it shows each sensor as a button that toggles their violation status
 * @author tzitzik
 *
 */
class SensorsConsole extends JFrame {
    SensorLine[] sensorLines;
    void fill() {
        for (int i=0;i<sensorLines.length;i++) { // for each sensor line
            final SensorLine sl = sensorLines[i];
            JPanel sensorlinePanel = new JPanel(new GridLayout(0,4,5,5)); // rows, columns, int hgap, int vgap)
            sensorlinePanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), sl.getName()));
                    //add(new JLabel(sl.getName()));
            for (final Sensor s: sl.getSensors()) { // gets the array of active sensors
                final JButton t = new JButton(s.getId()); // creation of button
                t.setToolTipText(s.toString());
                t.setOpaque(true);
                t.setBackground(s.isViolation()? Color.RED: Color.GREEN);
                t.addActionListener( // addition of the behavior of the button
                        new ActionListener() {
                            public void actionPerformed
                                    (java.awt.event.ActionEvent e) {
                                s.setOn(true); // optional: sets the sensor on
                                s.setViolation(!s.isViolation()); // toggles the violation status
                                t.setLabel(s.getId()); // updates the button's label
                                t.setBackground(s.isViolation()? Color.RED:
                                        Color.GREEN);
                                t.setToolTipText(s.toString());
                            }
                        }
                );
                sensorlinePanel.add(t); // adds the button to the panel
                add(sensorlinePanel); // adds the panel to the jframe
            }
        }
        setVisible(true); // makes the jframe visible
    }
    SensorsConsole(String title, SensorLine[] sensorLines) {
        this.sensorLines = sensorLines;
        this.setTitle(title);
        setBounds(200,100,800,600); //x, y, width, height)
        setLayout(new GridLayout(0,1)); // rows, columns
        setVisible(true);
        fill();
    }
}
/**
 * Graphical User Interface for the Alarm Controller
*/
public class AlarmConsole extends JFrame {
    HomeSecurityADT homeSecurityController ;
    JLabel labelStatus;
    JLabel labelLog ;
    JButton buttonArm  = new JButton("ARM");
    JButton buttonStay = new JButton("STAY");
    JButton buttonDisarm = new JButton("DISARM");
    JButton buttonChangeCode = new JButton("Change Password");
    JTextField inputField = new JTextField("Input");
    AlarmConsole(HomeSecurityADT homeSecurityController ) {
        this.homeSecurityController = homeSecurityController;
        setBounds(200,100,400,600); //x, y, width, height)
        setLayout(new GridLayout(0,1)); // rows, columns
        this.setTitle("ALARM CONSOLE");
        setVisible(true);
        fill();
    }

    void fill(){
        labelStatus = new JLabel(homeSecurityController.getState());
        labelLog = new JLabel("Outpout messages");
        labelLog.setHorizontalTextPosition(2);
        JPanel sensorlinePanel = new JPanel(new GridLayout(7,0,5,5));
        //sensorlinePanel.setBorder(BorderFactory.createTitledBorder(
        //        BorderFactory.createEtchedBorder(), "ALARM CONSOLE"));

        buttonArm.addActionListener(
                new ActionListener() {
                    public void actionPerformed
                            (java.awt.event.ActionEvent e) {
                        try {
                            homeSecurityController.Arm();
                            labelStatus.setText("Armed");
                            labelLog.setText("Armed Successfully");
                        } catch (Exception ex) {
                            System.out.println(ex);
                            labelLog.setText("A Sensor is violated unable to Arm");
                        }
                    }
                }
        );
        buttonStay.addActionListener(
                new ActionListener() {
                    public void actionPerformed
                            (java.awt.event.ActionEvent e) {
                        try {
                            homeSecurityController.StayMode();
                            labelStatus.setText("Stay");
                            labelLog.setText("Stay Activated");
                        } catch (Exception ex) {
                            System.out.println(ex);
                            labelLog.setText(ex.toString());
                        }
                    }
                }
        );
        buttonDisarm.addActionListener(
                new ActionListener() {
                    public void actionPerformed
                            (java.awt.event.ActionEvent e) {
                        try {
                            homeSecurityController.Disable(inputField.getText());
                            labelStatus.setText("Disarmed");
                            labelLog.setText("Disarmed Successfully");
                        } catch (Exception ex) {
                            System.out.println(ex);
                            labelLog.setText(ex.toString());
                        }
                    }
                }
        );
        buttonChangeCode.addActionListener(
                new ActionListener() {
                    public void actionPerformed
                            (java.awt.event.ActionEvent e) {
                        try {
                            homeSecurityController.changePassword(inputField.getText());
                            //labelStatus.setText("Changed Code Successfully");
                            labelLog.setText("Changed Code Successfully");
                        } catch (Exception ex) {
                            System.out.println(ex);
                            labelLog.setText(ex.toString());
                        }
                    }
                }
        );
        sensorlinePanel.add(labelStatus);
        sensorlinePanel.add(buttonArm); // adds the button to the panel
        sensorlinePanel.add(buttonStay);
        sensorlinePanel.add(buttonDisarm);
        sensorlinePanel.add(buttonChangeCode);
        sensorlinePanel.add(inputField);
        sensorlinePanel.add(labelLog);
        add(sensorlinePanel); // adds the panel to the jframe

        setVisible(true);
    }
}
//===================
/**
 * The entire application comprising sensor simulator and graphical alarm console
 * @author tzitzik
 *
 */
class SensorsGUIapp {
    public static void main(String[] args) {
        System.out.println("SENSORS GUI");
// A. CREATION OF SENSORS
        int K = 3; // number of sensor lines to be created
        int M = 4; // number of sensors per line
        SensorLine[] internallines = new SensorLine[K]; // internal sensor lines
        SensorLine[] externallines = new SensorLine[K]; // external sensor lines
// creation of internal and sensor lines
        try {
            for (int i = 0; i < K; i++) {
                internallines[i] = new SensorLine();
                externallines[i] = new SensorLine();
                for (int j = 0; j < M; j++) {
                    internallines[i].add(new Sensor("SID" + i + j, false, true));
                    externallines[i].add(new LaserSensor("SID"+i+j,false,true,10F));
                }

            }
        }catch (Exception e){
            System.out.println(e);
        }
// B. PASSES THE ARRAY OF SENSORS TO TWO SensorConsoles
        SensorsConsole internalSensorsConsole = new SensorsConsole("Internal Sensor Lines", internallines);
        SensorsConsole externalSensorsConsole = new SensorsConsole("External Sensor Lines", externallines);
// C. CREATION OF HomeSecurityController (from Askisi 2)
        HomeSecurityADT homeSecurityController = new HomeSecurityADT(internallines,externallines);
// D. PASSES HomeSecurityController TO AlarmConsole
        AlarmConsole alarmConsole = new AlarmConsole(homeSecurityController);
    }
}
