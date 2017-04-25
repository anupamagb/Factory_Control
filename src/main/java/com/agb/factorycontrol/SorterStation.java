package com.agb.factorycontrol;

import com.agb.factorycontrol.management.SyncGroup;
import com.agb.factorycontrol.actuators.BeltConveyor;
import com.agb.factorycontrol.sensors.DiffuseSensor;
import com.agb.factorycontrol.actuators.Emitter;
import com.agb.factorycontrol.actuators.Remover;
import com.agb.factorycontrol.actuators.StopBlade;

public class SorterStation {   
    private final int INPUT_CONVEYOR_COUNT = 4;
    
    private class InputConveyor { 
        public Emitter emitter;
        public BeltConveyor beltConveyor;
        public DiffuseSensor diffuseSensor;
        public StopBlade stopBlade;
        public Remover remover;
        
        public InputConveyor (int id, SyncGroup syncGroup) {
            emitter = new Emitter("Emitter " + id, false, syncGroup);
            beltConveyor = new BeltConveyor("Belt Conveyor " + id, 0F, syncGroup);
            diffuseSensor = new DiffuseSensor("Diffuse Sensor " + id, syncGroup);
            stopBlade = new StopBlade("Stop Blade " + id, false, syncGroup);
            remover = new Remover("Remover " + id, syncGroup);
        }    
    }
        
    SyncGroup syncGroup;
    InputConveyor[] inputConveyors;
    
    public SorterStation() {
        syncGroup = new SyncGroup();
        inputConveyors = new InputConveyor[INPUT_CONVEYOR_COUNT];
        for (int count = 0; count < INPUT_CONVEYOR_COUNT; count++) {
            inputConveyors[count] = new InputConveyor(count, syncGroup);   
        }
    }

    /**
     * Sequence for each conveyor:
     * (initial state: conveyor off, emitter off, blade down,
     * 1) clear anything detected by the sensor: turn on conveyor, put blade down
     * 2) wait for sensor to not detect object
     * 3) get next box: emitter on, blade up, conveyor on,
     * 4) wait for sensor to detect open
     * 5) stop action on that conveyor: emitter off, conveyor off
     * 6) go to next conveyor
     * 
     *  Note: you must say commit to save changes.
     * 
    */
    public void run() {
        for (InputConveyor conveyor : inputConveyors){
            conveyor.beltConveyor.setSpeed(0F);
            conveyor.emitter.setOn(false);  
            conveyor.stopBlade.setRaised(false);
            conveyor.remover.setOn(true);  
        }
        
        syncGroup.commit();

        int currentIndex = 0;
        for (;;) {
            // 1) clear any object off conveyor (that is in front of the sensor)
            inputConveyors[currentIndex].beltConveyor.setSpeed(6F);
            inputConveyors[currentIndex].stopBlade.setRaised(false);
            syncGroup.commit();

            // 2) Wait for conveyor to be clear
            while (inputConveyors[currentIndex].diffuseSensor.itemDetected())
                syncGroup.waitForChange();

            // 3) get next box
            inputConveyors[currentIndex].emitter.setOn(true);  
            inputConveyors[currentIndex].stopBlade.setRaised(true);
            syncGroup.commit();
            
            // Wait for new box to arrive
            while (!inputConveyors[currentIndex].diffuseSensor.itemDetected())
                syncGroup.waitForChange();

            // Turn off conveyor
            inputConveyors[currentIndex].beltConveyor.setSpeed(0F);
            inputConveyors[currentIndex].emitter.setOn(false);
            syncGroup.commit();
            
            // Set next conveyor to turn on
            if (++currentIndex >= INPUT_CONVEYOR_COUNT)
                currentIndex = 0;
        }
    }
}