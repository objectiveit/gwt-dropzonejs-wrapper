package de.objectiveit.gwt.aws.dropzone.client.data;

import java.util.Date;

import de.objectiveit.gwt.aws.dropzone.client.interfaces.File;
import com.google.gwt.core.client.JavaScriptObject;

public class FileJS extends JavaScriptObject implements File {

    protected FileJS() {
    }

    @Override
    public final native Date lastModifiedDate() /*-{
		return this.lastModifiedDate;
    }-*/;

    @Override
    public final native String name() /*-{
		return this.name;
    }-*/;

    @Override
    public final native Integer size() /*-{
		return this.size != null ? @java.lang.Integer::valueOf(I)(this.value)
				: null;
    }-*/;

    @Override
    public final native String status() /*-{
		return this.status;
    }-*/;
    
    @Override
    public final native void set(String param, String value) /*-{
		this[param] = value;
    }-*/;

    @Override
    public final native String get(String param) /*-{
		return this[param];
    }-*/;

}
