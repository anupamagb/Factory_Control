package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.sensors.core.Sensor;
import com.agb.factorycontrol.actuators.core.Actuator;
import javax.json.JsonObjectBuilder;

public class ConveyorScale {  
    private final Actuator<Boolean>   mForwardActuator;
    private final Actuator<Boolean>   mBackwardActuator;
    private final Sensor<Float>       mWeightSensor;
    
    public ConveyorScale(String name) {
        mForwardActuator = new Actuator<>(name + " Forward", false);
        mBackwardActuator = new Actuator<>(name + " Backward", false);
        mWeightSensor = new Sensor<>(name + " Weight", 0.0F);
    }    
    public void setForward(Boolean enable){    
        mForwardActuator.setValue(enable);
    }
    public void setBackward(Boolean enable){
        mBackwardActuator.setValue(enable);
    }
    public float getWeight() {
        return mWeightSensor.getValue();
    }     
    public void buildJson(JsonObjectBuilder builder) {
        builder.add(mForwardActuator.getName(), mForwardActuator.getValue());
        builder.add(mBackwardActuator.getName(), mBackwardActuator.getValue());
    }
}

