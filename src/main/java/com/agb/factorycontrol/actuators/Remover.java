package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;
import com.agb.factorycontrol.management.SyncGroup;

public class Remover {    
    private final Actuator<Boolean>   mRemoverActuator;

    public Remover(String name, Boolean value, SyncGroup syncGroup) {
        mRemoverActuator = new Actuator<>(name, false);
        syncGroup.add(mRemoverActuator);
    }
    public void setOn(Boolean on){
        mRemoverActuator.setValue(on);
    }        
}