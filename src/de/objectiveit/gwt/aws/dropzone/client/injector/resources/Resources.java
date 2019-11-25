package de.objectiveit.gwt.aws.dropzone.client.injector.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Interface that provides the Dropzone resources.
 *
 */
public interface Resources extends ClientBundle {

	@Source("js/dropzone.min.js")
	TextResource dropzoneJs();

}
