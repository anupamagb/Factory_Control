package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;
import com.agb.factorycontrol.management.SyncGroup;

/**
 * Removes one or more Items from the scene (e.g. cardboard box, pallet, product lid) when they 
 * the remover's volume. You can enable or disable a remover by switching its tag on/off.
 */

public class Remover {    
    private final Actuator<Boolean>   mRemoverActuator;

    public Remover(String name, SyncGroup syncGroup) {
        mRemoverActuator = new Actuator<>(name, false);
        syncGroup.add(mRemoverActuator);
    }
    public void setOn(Boolean on){
        mRemoverActuator.setValue(on);
    }        
}