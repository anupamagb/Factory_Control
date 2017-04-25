package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;
import com.agb.factorycontrol.management.SyncGroup;

/**
 * Audible alarm with an LED on the back that indicates if the siren is active.
 * Settings:
 *      true:  Alarm is on
 *      false: Alarm is off
 */
public class AlarmSiren {    
    private final Actuator<Boolean>   mSirenActuator;

    public AlarmSiren(String name, SyncGroup syncGroup) {
        mSirenActuator = new Actuator<>(name, false);
        syncGroup.add(mSirenActuator);
    }
    public void setOn(Boolean on){    
        mSirenActuator.setValue(on);
    }        
}