package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;
import com.agb.factorycontrol.management.SyncGroup;

public class Emitter {    
    private final Actuator<Boolean>   mEmitterActuator;

    public Emitter(String name, Boolean value, SyncGroup syncGroup) {
        mEmitterActuator = new Actuator<>(name, false);
        syncGroup.add(mEmitterActuator);
    }
    public void setOn(Boolean on){
        mEmitterActuator.setValue(on);
    }        
}