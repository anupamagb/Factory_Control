package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;

/**
 * A 45º power face arm diverter, powered by a gearmotor. Equipped with a belt that helps to deviate 
 * the conveyed items onto the next part. The arm can rotate left or right according to the selected 
 * configuration.
 * 
 */
public class PivotArmSorter {
    private final Actuator<Boolean>   mForwardActuator;
    private final Actuator<Boolean>   mBackwardActuator;
    private final Actuator<Boolean>   mExtendedActuator;
    
    public PivotArmSorter(String name, Boolean value) {
        mForwardActuator = new Actuator(name + " Forward",  value);
        mBackwardActuator = new Actuator(name + " Backward", value);
        mExtendedActuator = new Actuator(name + " Extended", value);
    }    
    public void setForward(Boolean enable){    
        mForwardActuator.setValue(enable);
    }
    public void setBackward(Boolean enable){
        mBackwardActuator.setValue(enable);
    }
    public void setExtended(Boolean enable){    
        mExtendedActuator.setValue(enable);
    }     
}
