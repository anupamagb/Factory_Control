package com.agb.factorycontrol.management;

import com.agb.factorycontrol.communications.Communications;
import com.agb.factorycontrol.communications.ReceiveMessageHandler;
import com.agb.factorycontrol.actuators.core.JsonBuilder;
import com.agb.factorycontrol.sensors.core.JsonParser;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

public class SyncGroup {
    private final Communications         mCommunications;
    private final ArrayList<JsonBuilder> mActuators;
    private final ArrayList<JsonParser>  mSensors;
    private       boolean                mChanged;
    
    public SyncGroup() {
        mActuators = new ArrayList<>();
        mSensors = new ArrayList<>();
        mCommunications = new Communications(new MessageHandler());
        mChanged = false;
    }
    
    public SyncGroup add(JsonBuilder jsonBuilder) {
        mActuators.add(jsonBuilder);
        return this;
    }
    
    public SyncGroup add(JsonParser jsonParser) {
        mSensors.add(jsonParser);
        return this;
    }

    public void commit() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        for (JsonBuilder actuator : mActuators)
            if (actuator.dataChanged())
                actuator.buildJson(builder);

        mCommunications.sendMessage(builder.build().toString());
    }
    
    public synchronized void waitForChange() {
        try {
            if (!mChanged)
                SyncGroup.this.wait();
            setChanged(false);
        } catch (InterruptedException ex) {
            Logger.getLogger(SyncGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private synchronized void setChanged(boolean changed) {
        if (mChanged != changed) {
            SyncGroup.this.notifyAll();
            mChanged = changed;
        }
    }
    
    class MessageHandler implements ReceiveMessageHandler {
        public synchronized void handleMessage(String message) {
            System.out.println("handle message enter");
            JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();;
            for (JsonParser sensor : mSensors)
                if (sensor.parseJson(jsonObject))
                    setChanged(true);
            System.out.println("handle message exit");
        }
    }
}
