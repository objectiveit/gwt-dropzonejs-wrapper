package de.objectiveit.gwt.aws.dropzone.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A simple overlay for a javascript dictionary
 *
 */
class MapOverlay extends JavaScriptObject {

    protected final static native MapOverlay create()/*-{
		return {};
    }-*/;

    protected MapOverlay() {

    }

    public native final void put(String key, String value)/*-{
		this[key] = value;
    }-*/;

}
