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
public class RetroreflectiveSensor {
    private final Sensor<Boolean> mRetroreflectiveSensor;    

    public RetroreflectiveSensor(String name, SyncGroup syncGroup) {
        mRetroreflectiveSensor = new Sensor<>(name, false);
        syncGroup.add(mRetroreflectiveSensor);
    }           
    public Boolean beamInterrupted() {
        return mRetroreflectiveSensor.getValue();
    }      
}
