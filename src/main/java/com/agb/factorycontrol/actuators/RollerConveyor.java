package com.agb.factorycontrol.actuators;

import com.agb.factorycontrol.actuators.core.Actuator;

/**
 * Heavy duty roller conveyor that is controller by a float actuator, where the magnitude of the
 * number indicates the speed of the conveyor belt and the sign of the number (negative or positive)
 * indicates its direction (backwards or forwards).
 * Settings:
 *      Minimum value: -10.0
 *      Maximum value: 10.0 
 */
public class RollerConveyor extends Actuator<Float> {
    public RollerConveyor(String name, Float value) {
        super(name, value);
    }
}
// the roller conveyer setSpeed