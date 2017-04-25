package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;
import com.agb.factorycontrol.management.SyncGroup;

/**
 * Pneumatic pusher sorter equipped with two reed sensors indicating the front and the back limits. 
 * It also includes a servo valve which can be used to set and measure the rod position. 
 * Control can be done by digital or analog values according to the selected configuration.
 * 
 */

public class Pusher {
    private final Actuator<Float> mPusherActuator;
    
    public Pusher(String name, Float value, SyncGroup syncGroup) {
        mPusherActuator = new Actuator(name, value);
        syncGroup.add(mPusherActuator);
    }
    public void setPosition(float speed) {
        mPusherActuator.setValue(speed);
    }
    public float getPosition() {
        return mPusherActuator.getValue();
    }  
}
