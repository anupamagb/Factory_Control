package com.agb.factorycontrol;

import org.apache.log4j.BasicConfigurator;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BasicConfigurator.configure();        
        SorterStation sorterStation = new SorterStation();
        sorterStation.run();
        
        
        /*
        Actuator<Integer> integerActuator = new Actuator<>("Integer Actuator", 7);
        System.out.println(integerActuator.getName() + ": " + integerActuator.getValue());

        Actuator<Double> floatActuator = new Actuator<>("Double Actuator", 1.23);
        System.out.println(floatActuator.getName() + ": " + floatActuator.getValue());

        Sensor<Integer> integerSensor = new Sensor<>("Integer Sensor", 2);
        System.out.println(integerSensor.getName() + ": " + integerSensor.getValue());

        Sensor<Boolean> booleanSensor = new Sensor<>("Boolean Sensor", true);
        System.out.println(booleanSensor.getName() + ": " + booleanSensor.getValue());
           
        RollerConveyor rollerConveyor = new RollerConveyor("main", 0.0F);
        System.out.println(rollerConveyor.getName() + ": " + rollerConveyor.getValue());
        */
        
    }
}
