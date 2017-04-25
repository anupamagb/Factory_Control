/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agb.factorycontrol.sensors;

import com.agb.factorycontrol.management.SyncGroup;
import com.agb.factorycontrol.sensors.core.Sensor;

/**
 *
 * @author balla
 */
public class LightArraySensor {
    private final Sensor<Integer> mLightArraySensor;    

    public LightArraySensor(String name, SyncGroup syncGroup) {
        mLightArraySensor = new Sensor<>(name, 0);
        syncGroup.add(mLightArraySensor);
    }           
    public int getBeamInterruptedMask() {
        return mLightArraySensor.getValue();
    }          
}
