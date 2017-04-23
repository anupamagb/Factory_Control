package com.agb.factorycontrol.actuators.core;

import javax.json.JsonObjectBuilder;

/**
 * An input device that is set by a user to control a factory component.  A
 * conveyor belt is an example of an actuator, whose value's data type is float.
 * The magnitude of the value indicates the speed of the conveyor belt and the
 * sign of the value (negative or positive) indicates its direction (backwards
 * or forwards).
 */
public class Actuator<T> implements JsonBuilder {
    private final String mName;         // Name of the actuator
    private T            mValue;        // Value of the actuator
    private boolean      mDataChanged;  // True if the value has changed without having buildJson called

    /**
     * Initialize an actuator with a name and a default value.
     * 
     * @param name      Name of the actuator
     * @param value     Actuator's default value
     */
    public Actuator(String name, T value) {
        mName = name; 
        mValue = value;
        mDataChanged = false;
    }   
    
    /**
     * Gets the value of the actuator.
     * 
     * @return  The value of the actuator.
     */
    public synchronized T getValue() {
        return mValue;
    }
    
    /**
     * Gets the name of the actuator.
     * 
     * @return  The name of the actuator.
     */
    public String getName(){
        return mName;
    }
    
    /**
     * Sets the value of the actuator.
     * 
     * @param value     The new value of the actuator.
     */
    public synchronized void setValue(T value) {
        if (mValue != value) {
            mValue = value;
            mDataChanged = true;
        }
    }
    
    public synchronized boolean dataChanged() {
        return mDataChanged;
    }
    
    public synchronized void buildJson(JsonObjectBuilder builder) {
        if (mValue instanceof Boolean)
            builder.add(mName, (Boolean)mValue);
        else if (mValue instanceof Integer)
            builder.add(mName, (Integer)mValue);
        else if (mValue instanceof Float)
            builder.add(mName, (Float)mValue);
        mDataChanged = false;
    }
}