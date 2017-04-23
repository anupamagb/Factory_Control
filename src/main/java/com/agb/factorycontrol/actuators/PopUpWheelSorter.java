package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;

enum PopUpWheelSorterDirection {
    LEFT,
    CENTER,
    RIGHT
}

enum PopUpWheelSorterRotation {
    OFF,
    FORWARD,
    BACKWARD
}

public class PopUpWheelSorter {  
    private final Actuator<Boolean>   mForwardActuator;
    private final Actuator<Boolean>   mBackwardActuator;
    private final Actuator<Boolean>   mLeftActuator;
    private final Actuator<Boolean>   mRightActuator;
    
    public PopUpWheelSorter(String name) {
        mForwardActuator = new Actuator<>(name + " Forward", false);
        mBackwardActuator = new Actuator<>(name + " Backward", false);
        mLeftActuator = new Actuator<>(name + " Left", false);
        mRightActuator = new Actuator<>(name + " Right", false);
    }    
    public void setRotation(PopUpWheelSorterRotation rotation){    
        if (rotation == PopUpWheelSorterRotation.FORWARD) {
           mForwardActuator.setValue(true);
           mBackwardActuator.setValue(false);
        }
        else if (rotation == PopUpWheelSorterRotation.BACKWARD) {
           mForwardActuator.setValue(false);
           mBackwardActuator.setValue(true);
        }
        else { 
           mForwardActuator.setValue(false);
           mBackwardActuator.setValue(false);
//           if (rotation != PopUpWheelSorterRotation.OFF)
//               LOG invalid value
        }
    }
    public void setDirection(PopUpWheelSorterDirection direction){
        if (direction == PopUpWheelSorterDirection.LEFT) {
           mLeftActuator.setValue(true);
           mRightActuator.setValue(false);
        }
        else if (direction == PopUpWheelSorterDirection.RIGHT) {
           mLeftActuator.setValue(false);
           mRightActuator.setValue(true);
        }
        else { 
           mLeftActuator.setValue(false);
           mRightActuator.setValue(false);
//           if (rotation != PopUpWheelSorterDirection.CENTER)
//               LOG invalid value
        }
    }
}

