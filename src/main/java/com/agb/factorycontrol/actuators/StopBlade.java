package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;
import com.agb.factorycontrol.management.SyncGroup;

/**
 * The stop blade is a pneumatically actuated device used to stop or accumulate material.
 * 
 */
public class StopBlade {
    private final Actuator<Boolean> mStopBladeActuator;
    
    public StopBlade(String name, Boolean value, SyncGroup syncGroup) {
        mStopBladeActuator = new Actuator(name, value);
        syncGroup.add(mStopBladeActuator);
    }
    public void setRaised(boolean raised) {
        mStopBladeActuator.setValue(raised);
    }
}