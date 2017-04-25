package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;
import com.agb.factorycontrol.actuators.core.Rotation;
import com.agb.factorycontrol.management.SyncGroup;

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
    
    public PivotArmSorter(String name, Boolean value, SyncGroup syncGroup) {
        mForwardActuator = new Actuator(name + " Forward",  value);
        mBackwardActuator = new Actuator(name + " Backward", value);
        mExtendedActuator = new Actuator(name + " Extended", value);
       syncGroup.add(mForwardActuator).add(mBackwardActuator).add(mExtendedActuator);
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
    public void setExtended(Boolean enable){    
        mExtendedActuator.setValue(enable);
    }     
}
