package de.objectiveit.gwt.aws.dropzone.client.event;

import com.google.gwt.core.client.JavaScriptObject;

public class FileAcceptEventCallbackHandler implements FileAcceptEventCallback{
	
	private JavaScriptObject done;

	@Override
	public void onSuccess(String url) {
		
	}

	@Override
	public void onError(String errorMessage) {
		
	}

	public JavaScriptObject getDone() {
		return done;
	}

	public void setDone(JavaScriptObject done) {
		this.done = done;
	}

}
