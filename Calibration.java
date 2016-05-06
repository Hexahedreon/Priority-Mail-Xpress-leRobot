package roborave.linefollowing.core;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;

public class Calibration {
	static EV3ColorSensor lc = new EV3ColorSensor(SensorPort.S3);		// Left color sensor
	static EV3ColorSensor mc = new EV3ColorSensor(SensorPort.S2);		// Middle color sensor
	static EV3ColorSensor rc = new EV3ColorSensor(SensorPort.S1);		// Right color sensor
	static RegulatedMotor rotm = Motor.B;
	
	public static void main(String[] args) {
		while(!(Button.ENTER.isDown())) {
		System.out.printf("Press UP for new values, or DOWN for rotation test");
		Button.waitForAnyPress();
		if(Button.UP.isDown()) {
			
		} else if(Button.DOWN.isDown()) {
			while(Button.ESCAPE.isUp()) {
			System.out.printf("Press ESCAPE to leave this");
			rotm.setSpeed(100); // Set speed
			rotm.forward();
		}
			rotm.stop();
			break;
		}
		
		int lColorID = lc.getColorID();		// Get color ID on lc
		int mColorID = mc.getColorID();		// Get color ID on mc
		int rColorID = rc.getColorID();		// Get color ID om rc
		
		System.out.printf("Moving forward, [LC: " + lColorID + ", MC: " + mColorID + ", RC: " + rColorID + "]\n");
		}
	}
}

