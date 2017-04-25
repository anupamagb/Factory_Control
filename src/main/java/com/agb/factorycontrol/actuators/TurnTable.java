/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;
import com.agb.factorycontrol.actuators.core.Rotation;
import com.agb.factorycontrol.management.SyncGroup;
import com.agb.factorycontrol.sensors.core.Sensor;

/**
 *
 * @author balla
 */
public class TurnTable {
    private final Actuator<Boolean>   mClockwiseRotationActuator;
    private final Actuator<Boolean>   mCounterClockwiseRotationActuator;
    private final Actuator<Boolean>   mForwardRollerActuator;
    private final Actuator<Boolean>   mBackwardRollerActuator;
    private final Sensor<Boolean>     mFrontSensor;
    private final Sensor<Boolean>     mBackSensor;
    
    public TurnTable(String name, SyncGroup syncGroup) {
        mClockwiseRotationActuator = new Actuator<>(name + " Clockwise", false);
        mCounterClockwiseRotationActuator = new Actuator<>(name + " Counter Clockwise", false);
        mForwardRollerActuator = new Actuator<>(name + " Forward", false);
        mBackwardRollerActuator = new Actuator<>(name + " Backward", false);
        mFrontSensor   = new Sensor<>(name + " Front", false);
        mBackSensor   = new Sensor<>(name + " Back", false);
        syncGroup.add(mClockwiseRotationActuator).add(mCounterClockwiseRotationActuator)
                 .add(mForwardRollerActuator).add(mBackwardRollerActuator)
                 .add(mFrontSensor).add(mBackSensor);
    }    
    public void rotateClockwise(Boolean enable){    
        mClockwiseRotationActuator.setValue(enable);
    }
    public void rotateCounterClockwise(Boolean enable){    
        mCounterClockwiseRotationActuator.setValue(enable);
    }
    public void setRollerRotation(Rotation rotation){    
        if (rotation == Rotation.FORWARD) {
           mForwardRollerActuator.setValue(true);
           mBackwardRollerActuator.setValue(false);
        }
        else if (rotation == Rotation.BACKWARD) {
           mForwardRollerActuator.setValue(false);
           mBackwardRollerActuator.setValue(true);
        }
        else { 
           mForwardRollerActuator.setValue(false);
           mBackwardRollerActuator.setValue(false);
//           if (rotation != PopUpWheelSorterRotation.OFF)
//               LOG invalid value
        }
    }     
    // at top or bottom
    public Boolean itemPresentFront() {
        return mFrontSensor.getValue();
    }    
    public Boolean itemPresentBack() {
        return mBackSensor.getValue();
    }  
}
