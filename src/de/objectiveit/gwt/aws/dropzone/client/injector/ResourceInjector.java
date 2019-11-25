package de.objectiveit.gwt.aws.dropzone.client.injector;

import com.google.gwt.resources.client.TextResource;

import de.objectiveit.gwt.aws.dropzone.client.injector.resources.Resources;

/**
 * Responsible for injecting the resources within the application
 *
 */
public class ResourceInjector {

	private static boolean configured;

	public static void configure(Resources resources) {
		if (!configured) {
			injectJs(resources.dropzoneJs());
			configured = true;
		}
	}

	private static void injectJs(TextResource r) {
		JavaScriptInjector.inject(r.getText());
	}

	private ResourceInjector() {
	}

}
