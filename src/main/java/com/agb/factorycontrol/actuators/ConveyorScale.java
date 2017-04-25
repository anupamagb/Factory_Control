package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.sensors.core.Sensor;
import com.agb.factorycontrol.actuators.core.Actuator;
import com.agb.factorycontrol.actuators.core.Rotation;
import com.agb.factorycontrol.management.SyncGroup;


public class ConveyorScale {  
    private final Actuator<Boolean>   mForwardActuator;
    private final Actuator<Boolean>   mBackwardActuator;
    private final Sensor<Float>       mWeightSensor;
    
    public ConveyorScale(String name, SyncGroup syncGroup) {
        mForwardActuator = new Actuator<>(name + " Forward", false);
        mBackwardActuator = new Actuator<>(name + " Backward", false);
        mWeightSensor = new Sensor<>(name + " Weight", 0F);
        syncGroup.add(mForwardActuator).add(mBackwardActuator).add(mWeightSensor);
    }    
    public void setRollerRotation(Rotation rotation){    
        if (null != rotation) switch (rotation) {
            case FORWARD:
                mForwardActuator.setValue(true);
                mBackwardActuator.setValue(false);
                break;
            case BACKWARD:
                mForwardActuator.setValue(false);
                mBackwardActuator.setValue(true);
                break;
            default:
                mForwardActuator.setValue(false);
                mBackwardActuator.setValue(false);
//           if (rotation != PopUpWheelSorterRotation.OFF)
//               LOG invalid value
                break;
        }
    }     

    public float getWeight() {
        return mWeightSensor.getValue();
    }         
}

