package com.agb.factorycontrol.sensors.core;

import javax.json.JsonObject;

public interface JsonParser {
    public boolean parseJson(JsonObject jsonObject);
}
