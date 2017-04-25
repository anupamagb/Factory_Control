package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;
import com.agb.factorycontrol.management.SyncGroup;

/**
 * Infeed/outfeed multi-belt conveyor, commonly used for an accurate merging of loads into belt 
 * conveyors. Digital and analog values can control the conveyor.
 * 
 */
public class StraightSpurConveyor {
    private final Actuator<Float> mStraightSpurConveyorActuator;
    
    public StraightSpurConveyor(String name, Float value, SyncGroup syncGroup) {
        mStraightSpurConveyorActuator = new Actuator(name, value);
        syncGroup.add(mStraightSpurConveyorActuator);
    }
    public void setSpeed(float speed) {
        mStraightSpurConveyorActuator.setValue(speed);
    }
    public float getSpeed() {
        return mStraightSpurConveyorActuator.getValue();
    }  
}
