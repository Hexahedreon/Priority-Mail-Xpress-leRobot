package roborave.linefollowing.core;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.*;
import lejos.robotics.*;
//import lejos.utility.*;
import lejos.utility.Delay;

public class Core {
	public static void main(String[] args) {
		RegulatedMotor lm = Motor.D;		// Left motor
		RegulatedMotor rm = Motor.A;	// Right motor
		EV3ColorSensor lc = new EV3ColorSensor(SensorPort.S3);		// Left color sensor
		EV3ColorSensor mc = new EV3ColorSensor(SensorPort.S2);		// Middle color sensor
		EV3ColorSensor rc = new EV3ColorSensor(SensorPort.S1);		// Right color sensor
		EV3TouchSensor ts = new EV3TouchSensor(SensorPort.S4);	// Touch sensor
		//Button button = new Button(3);
		
		SimpleTouch touch = new SimpleTouch(ts);	// Make object with touch sensor as arg
		//Work on...
		while(!(Button.ENTER.isDown())) {		// While touch sensor isn't pressed...
			int lColorID = lc.getColorID();		// Get color ID on lc
			int mColorID = mc.getColorID();	// Get color ID on mc
			int rColorID = rc.getColorID();	// Get color ID om rc
			
			// If all are black...
			// If right is black...turn left sharply
			// If right and mid is black...turn left slightly
			// If left is black..turn right sharply
			// If left and mid is black...turn right slightly
			// If all are white...go forward?
			// Add wait times
			Delay.msDelay(100);
			if(mColorID >= 7) {	// If only middle sensor sees black, go forward
				System.out.printf("Moving forward, [LC: " + lColorID + ", MC: " + mColorID + ", RC: " + rColorID + "]\n");
				lm.setSpeed(100);
				rm.setSpeed(100);
				lm.forward();
				rm.forward();
				
			}
			else if(lColorID >= 7) {		// If left sensor sees black, turn left sharply
				System.out.printf("Moving right sharply, [LC: " + lColorID + ", MC: " + mColorID + ", RC: " + rColorID + "]\n");
				lm.stop();
				rm.setSpeed(700);
				rm.forward();
				
			}
			else if(rColorID >= 7) {		// If right sensor sees black, turn right sharply
				System.out.printf("Moving left sharply, [LC: " + lColorID + ", MC: " + mColorID + ", RC: " + rColorID + "]\n");
				rm.stop();
				lm.setSpeed(700);
				lm.forward();
				
			}
			 else if(lColorID >= 7 && mColorID >= 7 && rColorID >= 7) {		// If all sensors see black, go ...
				System.out.printf("Moving forward, [LC: " + lColorID + ", MC: " + mColorID + ", RC: " + rColorID + "]\n");
				lm.setSpeed(100);
				rm.setSpeed(100);
				lm.forward();
				rm.forward();
				
			}
		}
		// Dump pong balls
		
		
		while(Button.ENTER.isUp()) {
			
		}
		// Close resource leaks
		//lc.close(); mc.close(); rc.close();
	}
}
