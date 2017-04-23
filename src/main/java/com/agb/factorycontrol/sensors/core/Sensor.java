package com.agb.factorycontrol.sensors.core;

import com.agb.factorycontrol.communications.Communications;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;

/**
 * An output device that is read by a user to obtain information about a factory
 * component.  The "device present" sensor of a "pick is place" is an example of
 * a sensor.  It's data type is boolean, where a value of true indicates that a
 * box is present and false indicates that one is not.
 * @param <T>
 */
public class Sensor<T> implements JsonParser {
    private final String mName;   // Name of the sensor
    private T            mValue;  // Value of the sensor

    /**
     * Initialize an sensor with a name and a default value.
     * 
     * @param name      Name of the sensor
     * @param value     Sensor's default value
     */
    public Sensor(String name, T value) {
        mName = name; 
        mValue = value;
    }   
    
    /**
     * Gets the value of the sensor.
     * 
     * @return  The value of the sensor.
     */
    public synchronized T getValue() {
        return mValue;
    }
 
    /**
     * Sets the value of the sensor.
     * 
     * @param value     Sensor's new value
     */
    public synchronized void setValue(T value) {
        mValue = value;
    }
     
    /**
     * Gets the name of the sensor.
     * 
     * @return  The name of the sensor.
     */
    public String getName(){
        return mName;
    }
    @Override
    public synchronized boolean parseJson(JsonObject jsonObject) {
        try {
            if (jsonObject.containsKey(mName)) {
                if (mValue instanceof Boolean) {
                    Boolean oldValue = (Boolean)mValue;
                    Boolean newValue = jsonObject.getBoolean(mName);
                    mValue = (T)newValue;
                    return !Objects.equals(oldValue, newValue);
                }
                else if (mValue instanceof Integer) {
                    Integer oldValue = (Integer)mValue;
                    Integer newValue = jsonObject.getInt(mName);
                    mValue = (T)newValue;
                    return !Objects.equals(oldValue, newValue);
                }
                else if (mValue instanceof Float) {
                    Float oldValue = (Float)mValue;
                    Float newValue = (float)jsonObject.getJsonNumber(mName).doubleValue();
                    mValue = (T)newValue;
                    return !Objects.equals(oldValue, newValue);
                }
            }
        }
        catch (Exception ex) {
            Logger.getLogger(Communications.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}