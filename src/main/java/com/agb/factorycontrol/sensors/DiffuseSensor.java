package com.agb.factorycontrol.sensors;

import com.agb.factorycontrol.management.SyncGroup;
import com.agb.factorycontrol.sensors.core.Sensor;

/** 
 * Diffuse photoelectric sensor which can detect any solid objects.
 *     LED: red (detecting)
 *     Detectable materials: solids
 *     Sensing range: 0 - 1.6 m
 */
public class DiffuseSensor {
    private final Sensor<Boolean> mItemDetectedSensor;    

    public DiffuseSensor(String name, SyncGroup syncGroup) {
        mItemDetectedSensor = new Sensor<>(name, false);
        syncGroup.add(mItemDetectedSensor);
    }           
    public Boolean itemDetected() {
        return mItemDetectedSensor.getValue();
    }  
}
