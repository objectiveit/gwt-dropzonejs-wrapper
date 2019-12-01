package de.objectiveit.gwt.aws.dropzone.client.event;

import com.google.gwt.core.client.JavaScriptObject;

public class FileAcceptEventCallbackHandler implements FileAcceptEventCallback {

	private JavaScriptObject done;

	@Override
	public native void onSuccess() /*-{
		var done = this.@de.objectiveit.gwt.aws.dropzone.client.event.FileAcceptEventCallbackHandler::getDone()();
		done();
	}-*/;

	@Override
	public native void onError(String errorMessage) /*-{ 
	var done = this.@de.objectiveit.gwt.aws.dropzone.client.event.FileAcceptEventCallbackHandler::getDone()();
		done(errorMessage);
	}-*/;

	public JavaScriptObject getDone() {
		return done;
	}

	public void setDone(JavaScriptObject done) {
		this.done = done;
	}

}
