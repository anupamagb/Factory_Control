package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;

/**
 * Pneumatic pusher sorter equipped with two reed sensors indicating the front and the back limits. 
 * It also includes a servo valve which can be used to set and measure the rod position. 
 * Control can be done by digital or analog values according to the selected configuration.
 * 
 */

public class Pusher {
    private final Actuator<Float> actuator;
    
    public Pusher(String name, Float value) {
        actuator = new Actuator(name, value);
    }
    public void setPosition(float speed) {
        actuator.setValue(speed);
    }
    public float getPosition() {
        return actuator.getValue();
    }  
}
