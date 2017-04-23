package com.agb.factorycontrol.actuators.core;

import javax.json.JsonObjectBuilder;

public interface JsonBuilder {
    public boolean dataChanged();
    public void buildJson(JsonObjectBuilder builder);
}
