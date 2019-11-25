package de.objectiveit.gwt.aws.dropzone.client.event;

public interface FileAcceptEventCallback {

	public void onSuccess(String url);
	public void onError(String errorMessage);
}
