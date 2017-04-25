package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.sensors.core.Sensor;
import com.agb.factorycontrol.actuators.core.Actuator;
import com.agb.factorycontrol.management.SyncGroup;

public class PositioningBars {  
    private final Actuator<Boolean>   mRaiseActuator;
    private final Actuator<Boolean>   mClampActuator;
    private final Sensor<Boolean>     mLimitSensor;
    private final Sensor<Boolean>     mClampSensor;
    
    public PositioningBars(String name, SyncGroup syncGroup) {
        mRaiseActuator = new Actuator<>(name + " Raise", false);
        mClampActuator = new Actuator<>(name + " Clamp", false);
        mLimitSensor   = new Sensor<>(name + " Limit", false);
        mClampSensor   = new Sensor<>(name + " Clamp", false);
        syncGroup.add(mRaiseActuator).add(mClampActuator).add(mLimitSensor).add(mClampSensor);        
    }    
    public void setRaised(Boolean enable){    
        mRaiseActuator.setValue(enable);
    }
    public void setClamped(Boolean enable){
        mClampActuator.setValue(enable);
    }   
    // at top or bottom
    public Boolean isAtLimit() {
        return mLimitSensor.getValue();
    }    
    public Boolean isClamped() {
        return mClampSensor.getValue();
    }  
}

