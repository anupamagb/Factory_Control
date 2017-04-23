package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;

/**
 * Infeed/outfeed multi-belt conveyor, commonly used for an accurate merging of loads into belt 
 * conveyors. Digital and analog values can control the conveyor.
 * 
 */
public class StraightSpurConveyor {
    private final Actuator<Float> actuator;
    
    public StraightSpurConveyor(String name, Float value) {
        actuator = new Actuator(name, value);
    }
    public void setSpeed(float speed) {
        actuator.setValue(speed);
    }
    public float getSpeed() {
        return actuator.getValue();
    }  
}
