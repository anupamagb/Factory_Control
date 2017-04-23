package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;
import com.agb.factorycontrol.management.SyncGroup;

public class BeltConveyor {
    private final Actuator<Float> mBeltConveyorActuator;
    
    public BeltConveyor(String name, Float value, SyncGroup syncGroup) {
        mBeltConveyorActuator = new Actuator(name, value);
        syncGroup.add(mBeltConveyorActuator);
    }
    
    public void setSpeed(float speed) {
        mBeltConveyorActuator.setValue(speed);
    }
}
