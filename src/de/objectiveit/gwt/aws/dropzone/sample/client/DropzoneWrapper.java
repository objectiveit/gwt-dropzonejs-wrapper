package de.objectiveit.gwt.aws.dropzone.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import de.objectiveit.gwt.aws.dropzone.client.Dropzone;
import de.objectiveit.gwt.aws.dropzone.client.DropzoneEventHandlerAdaptor;
import de.objectiveit.gwt.aws.dropzone.client.interfaces.DropzoneOptions;
import de.objectiveit.gwt.aws.dropzone.client.interfaces.File;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DropzoneWrapper implements EntryPoint {
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	public void onModuleLoad() {

		DropzoneOptions options = Dropzone.options();
		options.setUrl("upload");

		Dropzone dropzone = new Dropzone(options, new DropzoneEventHandlerAdaptor() {
			@Override
			public void onAddedFile(File file) {
				Window.alert("onAddedFile : " + file.name());
			}

			@Override
			public void onProcessing(File file) {
				Window.alert("onProcessing : " + file.name());
			}

		});

		SimplePanel dropContainer = new SimplePanel();
		dropContainer.setWidth("50%");
		dropContainer.getElement().getStyle().setProperty("margin", "0 auto");
		dropContainer.add(dropzone);

		RootPanel.get().add(dropContainer);

	}
}
