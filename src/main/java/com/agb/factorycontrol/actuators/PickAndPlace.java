package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.sensors.core.Sensor;
import com.agb.factorycontrol.actuators.core.Actuator;
import com.agb.factorycontrol.management.SyncGroup;

/**
 * Gantry Pick and Place station with three axes controlled by servomotors. Often used to move light
 * load cargo (e.g. cardboard boxes) into other conveyors or pallets.  The Pick and Place has four
 * degrees of freedom, three corresponding to the axes linear movement and another to the gripper
 * rotation. The gripper is enabled by suction cups and includes a proximity sensor.
 * 
 * Setting Values:
 *     X Position:       Minimum = -10.0, Maximum = 10.0
 *     Y Position:       Minimum = -10.0, Maximum = 10.0
 *     Z Position:       Minimum = -10.0, Maximum = 10.0
 *     Rotate Gripper:   Vertical = false, Horizontal = true
 *     Activate Gripper: Suction on = true, Suction off = false
 *     Item Detected:    Detected = true, Not Detected = false
 */
public class PickAndPlace {
    private final Actuator<Float>   mXPositionActuator;
    private final Actuator<Float>   mYPositionActuator;
    private final Actuator<Float>   mZPositionActuator;
    private final Actuator<Boolean> mRotationActuator;
    private final Actuator<Boolean> mGripperActuator;
    private final Sensor<Boolean>   mItemDetectedSensor;
    private final Sensor<Float>     mXPositionSensor;
    private final Sensor<Float>     mYPositionSensor;
    private final Sensor<Float>     mZPositionSensor;
    
    public PickAndPlace(String name, SyncGroup syncGroup) {
        mXPositionActuator     = new Actuator<>(name + " Set X", 0F);
        mYPositionActuator     = new Actuator<>(name + " Set Y", 0F);
        mZPositionActuator     = new Actuator<>(name + " Set Z", 0F);
        mRotationActuator      = new Actuator<>(name + " Rotator", false);
        mGripperActuator       = new Actuator<>(name + " Gripper", false);
        mItemDetectedSensor    = new Sensor<>(name + " Item Detected", false);
        mXPositionSensor       = new Sensor<>(name + " X", 0F);
        mYPositionSensor       = new Sensor<>(name + " Y", 0F);
        mZPositionSensor       = new Sensor<>(name + " Z", 0F);
        syncGroup.add(mXPositionActuator).add(mYPositionActuator).add(mZPositionActuator)
                 .add(mRotationActuator).add(mGripperActuator).add(mItemDetectedSensor)
                 .add(mXPositionSensor).add(mYPositionSensor).add(mZPositionSensor);
                
    }    
    public void setXPosition(Float value){    
        mXPositionActuator.setValue(value);
    }
    public void setYPosition(Float value){    
        mYPositionActuator.setValue(value);
    }
    public void setZPosition(Float value){    
        mZPositionActuator.setValue(value);
    }
    public Float getXPosition(Float value){    
        return mXPositionSensor.getValue();
    }
    public Float getYPosition(Float gevalue){    
        return mYPositionSensor.getValue();
    }
    public Float getZPosition(Float value){    
        return mZPositionSensor.getValue();
    }  
    public void rotateGripper(Boolean value){    
        mRotationActuator.setValue(value);
    }
    public void activateGripper(Boolean value){    
        mGripperActuator.setValue(value);
    }
    public Boolean itemDetected() {
        return mItemDetectedSensor.getValue();
    }  
}
