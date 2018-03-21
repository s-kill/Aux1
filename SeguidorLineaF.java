import lejos.nxt.*;
import lejos.nxt.addon.*;
import lejos.robotics.*;
import lejos.util.Delay;


public class SeguidorLineaF {
	
	public static void main (String[] args){
		new SeguidorLinea().seguidor();
	}
	public void seguidor(){
		TouchSensor touch = new TouchSensor(SensorPort.S2);
		LightSensor light = new LightSensor(SensorPort.S4);
		NXTRegulatedMotor mb = Motor.B;//Derecha
		NXTRegulatedMotor mc = Motor.C;//Izquierda
		light.setFloodlight(true);
		//
		Button.ENTER.waitForPress();
		int w = light.getNormalizedLightValue();
		Button.ENTER.waitForPress();
		int b = light.getNormalizedLightValue();
		//
		float mean = ((w+b)/2); 
		float v_max= 118;
		float m = (v_max/(w-b));
		
		
		while(!touch.isPressed())
		{
			int luz= light.getNormalizedLightValue();
			int v_motor= (int)(m*(luz-mean));
			if (v_motor>0){
				mb.setSpeed(v_motor);
				mb.forward();
				mc.setSpeed((v_max-v_motor));
				mc.forward();
				
			}else{
				mc.setSpeed(-v_motor);
				mc.forward();
				mb.setSpeed((v_max+v_motor));
				mb.forward();
			}
			
		//Button.ENTER.waitForPress();
		}
		//Gira a la izquierda hasta n
		
		int i=0
		while(i<20)
		{
			mc.flt();
			mb.setSpeed(70);
			mb.forward();
			i=++;
		}
		
		//Vuelve a seguir la linea
		while(!touch.isPressed())
		{
			int luz= light.getNormalizedLightValue();
			int v_motor= (int)(m*(luz-mean));
			if (v_motor>0){
				mb.setSpeed(v_motor);
				mb.forward();
				mc.setSpeed((v_max-v_motor));
				mc.forward();
				
			}else{
				mc.setSpeed(-v_motor);
				mc.forward();
				mb.setSpeed((v_max+v_motor));
				mb.forward();
			}
		}
	}
}