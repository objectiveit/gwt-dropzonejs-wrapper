package de.objectiveit.gwt.aws.dropzone.client.event;

import de.objectiveit.gwt.aws.dropzone.client.interfaces.File;

public interface FileAcceptEvent {

	public void accept(File file, FileAcceptEventCallbackHandler callback);
}
