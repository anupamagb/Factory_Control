package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;
import com.agb.factorycontrol.management.SyncGroup;
import com.agb.factorycontrol.sensors.core.Sensor;

/**
 * Heavy duty roller conveyor that is controller by a float actuator, where the magnitude of the
 * number indicates the speed of the conveyor belt and the sign of the number (negative or positive)
 * indicates its direction (backwards or forwards).
 * Settings:
 *      Minimum value: -10.0
 *      Maximum value: 10.0 
 */
public class RollerConveyor {
    private final Actuator<Float>  mRollerConveyorActuator;
    
    public RollerConveyor(String name, Float value, SyncGroup syncGroup) {               
        mRollerConveyorActuator = new Actuator<>(name, value);
        syncGroup.add(mRollerConveyorActuator);
    //    super(name, value);
    }
    public void setRollerConveyorSpeed (Float speed) {
        mRollerConveyorActuator.setValue(speed);
    }
    public Float getRollerConveyorSpeed () {
        return mRollerConveyorActuator.getValue();
    }
}
// the roller conveyer setSpeed