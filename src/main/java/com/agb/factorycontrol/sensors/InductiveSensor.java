package com.agb.factorycontrol.sensors;

import com.agb.factorycontrol.management.SyncGroup;
import com.agb.factorycontrol.sensors.core.Sensor;

/**
 * Proximity sensor used for close detection of conductive materials. It is equipped with   * an LED, which indicates the presence of an object within its range. The output value **  * can   be digital or analog, according to the selected configuration.
 *   LED: red (detecting)
 *    Detectable materials: conductive
 *    Sensing range: 0 - 0.1 m
 */

public class InductiveSensor {
    private final Sensor<Float> mDistanceSensor;
    private final Sensor<Boolean> mItemDetectedSensor;    

    public InductiveSensor(String name, SyncGroup syncGroup) {
        mDistanceSensor     = new Sensor<>(name + " Distance", 0F);
        mItemDetectedSensor = new Sensor<>(name + " Item Detected", false);
        syncGroup.add(mDistanceSensor).add(mItemDetectedSensor);
    }          
    public float getDistance() {
        return mDistanceSensor.getValue();
    }  
    public Boolean itemDetected() {
        return mItemDetectedSensor.getValue();
    }  
}

