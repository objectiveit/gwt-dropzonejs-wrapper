package de.objectiveit.gwt.aws.dropzone.sample.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import de.objectiveit.gwt.aws.dropzone.client.Dropzone;
import de.objectiveit.gwt.aws.dropzone.client.DropzoneEventHandlerAdaptor;
import de.objectiveit.gwt.aws.dropzone.client.event.FileAcceptEvent;
import de.objectiveit.gwt.aws.dropzone.client.event.FileAcceptEventCallbackHandler;
import de.objectiveit.gwt.aws.dropzone.client.interfaces.DropzoneOptions;
import de.objectiveit.gwt.aws.dropzone.client.interfaces.File;
import de.objectiveit.gwt.aws.dropzone.client.interfaces.Method;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DropzoneWrapper implements EntryPoint {
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	Dropzone dropzone = null;

	public void onModuleLoad() {

		DropzoneOptions options = Dropzone.options();
		options.setUrl("upload");
		options.setMethod(Method.PUT);
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Cache-Control", "");
		options.setHeaders(headers);

		options.setAcceptFile(new FileAcceptEvent() {

			@Override
			public void accept(File file, FileAcceptEventCallbackHandler callback) {
				greetingService.greetServer(file.name(), new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						file.set("uploadUrl", result);
						callback.onSuccess();

					}

					@Override
					public void onFailure(Throwable caught) {
						callback.onError(caught.getMessage());
					}
				});

			}
		});

		DropzoneEventHandlerAdaptor dropzoneEventHandlerAdaptor = new DropzoneEventHandlerAdaptor() {
			@Override
			public void onAddedFile(File file) {
//				Window.alert("onAddedFile : " + file.name());
			}

			@Override
			public void onProcessing(File file) {
//				Window.alert("onProcessing : " + file.get("uploadUrl"));
				dropzone.getOptions().setUrl(file.get("uploadUrl"));
			}

		};

		dropzone = new Dropzone(options, dropzoneEventHandlerAdaptor);

		SimplePanel dropContainer = new SimplePanel();
		dropContainer.setWidth("50%");
		dropContainer.getElement().getStyle().setProperty("margin", "0 auto");
		dropContainer.add(dropzone);

		RootPanel.get().add(dropContainer);

	}
}
