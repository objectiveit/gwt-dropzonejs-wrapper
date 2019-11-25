package de.objectiveit.gwt.aws.dropzone.client;

import java.util.ArrayList;

import de.objectiveit.gwt.aws.dropzone.client.data.FileJS;
import de.objectiveit.gwt.aws.dropzone.client.event.DropzoneEventHandler;
import de.objectiveit.gwt.aws.dropzone.client.injector.ResourceInjector;
import de.objectiveit.gwt.aws.dropzone.client.injector.resources.Resources;
import de.objectiveit.gwt.aws.dropzone.client.interfaces.DropzoneDictonary;
import de.objectiveit.gwt.aws.dropzone.client.interfaces.DropzoneOptions;
import de.objectiveit.gwt.aws.dropzone.client.interfaces.File;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

/**
 * Main class, that is used to interact
 *
 *
 */
public class Dropzone extends Composite {

	/**
	 * Create the object that contains the Dictionary used by {@link Dropzone}
	 *
	 * @return a default (en-us) {@link DropzoneDictonary} instance
	 */
	public static DropzoneDictonary dictionary() {
		return Dictionary.create();
	}

	/**
	 * Create the object that contains the Options used by {@link Dropzone}
	 *
	 * @return a default {@link DropzoneOptions} instance
	 */
	public static DropzoneOptions options() {
		return Options.create();
	}

	private final DropzoneOptions options;

	private final DropzoneEventHandler handler;

	private final DropzoneDictonary dictionary;

	public Dropzone(DropzoneOptions options) {
		this(options, null, null, (Resources) GWT.create(Resources.class));
	}

	public Dropzone(DropzoneOptions options, DropzoneDictonary dictionary) {
		this(options, null, dictionary, (Resources) GWT.create(Resources.class));
	}

	public Dropzone(DropzoneOptions options, DropzoneEventHandler handler) {
		this(options, handler, null, (Resources) GWT.create(Resources.class));
	}

	public Dropzone(DropzoneOptions options, DropzoneEventHandler handler, DropzoneDictonary dictionary) {
		this(options, handler, dictionary, (Resources) GWT.create(Resources.class));
	}

	public Dropzone(DropzoneOptions options, DropzoneEventHandler handler, DropzoneDictonary dictionary,
			Resources resources) {
		this.options = options;
		this.handler = handler;
		this.dictionary = dictionary;
		injectResources(resources);
		initWidget();
		initDropzone(getElement(), options, handler, dictionary);
	}

	/**
	 * Add file information manually to Dropzone. Added file is not uploaded, it is just shown in the dropzone.
	 * This feature is useful for displaying e.g. files that already exists on the server.
	 *
	 * @param fileName     name of file to add
	 * @param fileSize     size of file to add
	 * @param thumbnailUrl thumbnail image for file
	 */
	public void addFile(String fileName, Integer fileSize, String thumbnailUrl) {
		addFileNative(getElement(), fileName, fileSize, thumbnailUrl);
	}

	/**
	 * Native implementation of {@link Dropzone#addFile(String, Integer, String)} method.
	 *
	 * @param e            dropzone element
	 * @param fileName     name of file to add
	 * @param fileSize     size of file to add
	 * @param thumbnailUrl thumbnail image for file
	 */
	private native void addFileNative(Element e, String fileName, Integer fileSize, String thumbnailUrl) /*-{
		var mockFile = {
			name : fileName,
			size : fileSize,
			status : @de.objectiveit.gwt.aws.dropzone.client.data.FileConstants::SUCCESS
		};

		e.dropzone.files.push(mockFile);
		e.dropzone.emit("addedfile", mockFile);
		e.dropzone.emit("thumbnail", mockFile, thumbnailUrl);
		e.dropzone.emit("complete", mockFile);
		e.dropzone.emit("success", mockFile);
    }-*/;

	/**
	 * Return information about particular file added to dropzone.
	 *
	 * @param index of the file
	 * @return file information
	 */
	public File getFile(int index) {
		return getFilesNative(getElement()).get(index);
	}

	/**
	 * Return array if all files added to dropzone.
	 *
	 * @return all files added to dropzone.
	 */
	public ArrayList<File> getFiles() {
		//TODO Why ArrayList, change to list when in a proper IDE

		final JsArray<FileJS> filesJS = getFilesNative(getElement());
		final ArrayList<File> files = new ArrayList<File>();

		if (filesJS != null) {
			for (int i = 0; i < filesJS.length(); i++) {
				files.add(filesJS.get(i));
			}
		}

		return files;
	}

	/**
	 * Return number of added files.
	 *
	 * @return number of files
	 */
	public int getFilesCount() {
		final JsArray<FileJS> files = getFilesNative(getElement());
		return files != null ? files.length() : 0;
	}

	/**
	 * Native implementation of {@Link Dropzone#getFiles()}
	 *
	 * @param e dropzone element
	 * @return array of {@link FileJS} elements
	 */
	private native JsArray<FileJS> getFilesNative(Element e) /*-{
		return e.dropzone.files;
    }-*/;

	private native void initDropzone(Element e, DropzoneOptions options, DropzoneEventHandler handler,
			DropzoneDictonary dictionary)
			/*-{
		//if there is a dictionary, iterate over it and transfer the values

		if (dictionary) {
			for ( var key in dictionary) {
				if (dictionary.hasOwnProperty(key)) {
					options[key] = dictionary[key];
				}
			}
		}

		var dropzone = new $wnd.Dropzone(e, options);

		//If not loaded, don't add the handlers.
		if (!(dropzone instanceof $wnd.Dropzone)) {
			return;
		}
		//I'm loaded, add the eventHandlers

		//TODO: refactor this to another method

		if (this.@de.objectiveit.gwt.aws.dropzone.client.Dropzone::handler) {

			dropzone
					.on(
							"addedfile",
							function(file) {
								handler.@de.objectiveit.gwt.aws.dropzone.client.event.DropzoneEventHandler::onAddedFile(Lde/objectiveit/gwt/aws/dropzone/client/interfaces/File;)(file);
							});
			dropzone
					.on(
							"removedfile",
							function(file) {
								handler.@de.objectiveit.gwt.aws.dropzone.client.event.DropzoneEventHandler::onRemovedfile(Lde/objectiveit/gwt/aws/dropzone/client/interfaces/File;)(file);
							});
			dropzone
					.on(
							"thumbnail",
							function(file, dataUri) {
								handler.@de.objectiveit.gwt.aws.dropzone.client.event.DropzoneEventHandler::onThumbnail(Lde/objectiveit/gwt/aws/dropzone/client/interfaces/File;Ljava/lang/String;)(file,dataUri);
							});
			dropzone
					.on(
							"error",
							function(file, message, xhrObject) {
								handler.@de.objectiveit.gwt.aws.dropzone.client.event.DropzoneEventHandler::onError(Lde/objectiveit/gwt/aws/dropzone/client/interfaces/File;Ljava/lang/String;Lde/objectiveit/gwt/aws/dropzone/client/interfaces/XHRObjet;)(file,message,xhrObject);
							});
			dropzone
					.on(
							"processing",
							function(file) {
								handler.@de.objectiveit.gwt.aws.dropzone.client.event.DropzoneEventHandler::onProcessing(Lde/objectiveit/gwt/aws/dropzone/client/interfaces/File;)(file);
							});
			dropzone
					.on(
							"uploadprogress",
							function(file, progress, bytesSent) {
								handler.@de.objectiveit.gwt.aws.dropzone.client.event.DropzoneEventHandler::onUploadProgress(Lde/objectiveit/gwt/aws/dropzone/client/interfaces/File;II)(file,progress,bytesSent);
							});
			dropzone
					.on(
							"sending",
							function(file, xhrObject, formData) {
								handler.@de.objectiveit.gwt.aws.dropzone.client.event.DropzoneEventHandler::onSending(Lde/objectiveit/gwt/aws/dropzone/client/interfaces/File;Lde/objectiveit/gwt/aws/dropzone/client/interfaces/FormData;Lde/objectiveit/gwt/aws/dropzone/client/interfaces/XHRObjet;)(file,xhrObject,formData);
							});

			dropzone
					.on(
							"success",
							function(file, response) {
								handler.@de.objectiveit.gwt.aws.dropzone.client.event.DropzoneEventHandler::onSuccess(Lde/objectiveit/gwt/aws/dropzone/client/interfaces/File;Ljava/lang/String;)(file,response);
							});

			dropzone
					.on(
							"complete",
							function(file) {
								handler.@de.objectiveit.gwt.aws.dropzone.client.event.DropzoneEventHandler::onComplete(Lde/objectiveit/gwt/aws/dropzone/client/interfaces/File;)(file);
							});

			dropzone
					.on(
							"canceled",
							function(file) {
								handler.@de.objectiveit.gwt.aws.dropzone.client.event.DropzoneEventHandler::onCancelled(Lde/objectiveit/gwt/aws/dropzone/client/interfaces/File;)(file);
							});

			dropzone
					.on(
							"maxfilesreached",
							function(file) {
								handler.@de.objectiveit.gwt.aws.dropzone.client.event.DropzoneEventHandler::onMaxFilesReached(Lde/objectiveit/gwt/aws/dropzone/client/interfaces/File;)(file);
							});

			dropzone
					.on(
							"maxfilesexceeded",
							function(file) {
								handler.@de.objectiveit.gwt.aws.dropzone.client.event.DropzoneEventHandler::onMaxFilesExceeded(Lde/objectiveit/gwt/aws/dropzone/client/interfaces/File;)(file);
							});

		}

    }-*/;

	private void initWidget() {
		// To conform with the standard stylesheet
		final HTML widget = new HTML();
		widget.setStylePrimaryName("dropzone");
		initWidget(widget);
	}

	private void injectResources(Resources resources) {
		ResourceInjector.configure(resources);
	}

	/**
	 *  Checks how many files are currently uploading,
	 *  and if it’s less than <code>options.parallelUploads</code>,
	 *  <code>.processFile(file)</code> is called.
	 *
	 *  <p>
	 *  If you set autoProcessQueue to <code>false</code>, then <code>.processQueue()</code> is
	 *  never called implicitly.
	 *  This means that you have to call it yourself when
	 *  you want to upload all files currently queued.
	 *  </p>
	 */
	public void processQueue() {
		processQueueNative(getElement());
	}

	private native void processQueueNative(Element e)/*-{
		e.dropzone.processQueue();
    }-*/;

	/**
	 * Removes all files from dropzone.
	 */
	public void removeAllFiles() {
		removeAllFilesNative(getElement());
	}

	/**
	 * Native implementation of {@link Dropzone#removeAllFiles()}
	 *
	 * @param e dropzone element
	 */
	private native void removeAllFilesNative(Element e) /*-{
		e.dropzone.removeAllFiles(true);
    }-*/;

	/**
	 * Removes file at index i.
	 *
	 * @param i index of the file to remove.
	 */
	public void removeFile(int i) {
		removeFileNative(getElement(), i);
	}

	/**
	 * Removes file at index i.
	 *
	 * @param i index of the file to remove.
	 */
	public void removeFile(File file) {
		removeFileNative(getElement(), file);
	}

	private native void removeFileNative(Element e, File file)  /*-{
		e.dropzone.removeFile(file);
    }-*/;

	/**
	 * Native implementation of {@link Dropzone#removeFile(int)}
	 *
	 * @param e dropzone element
	 * @param i index of the file to remove
	 */
	private native void removeFileNative(Element e, int i) /*-{
		if (e.dropzone.files[i] != null) {
			e.dropzone.removeFile(e.dropzone.files[i]);
		}
    }-*/;
	
	/**
	 * Return dropzone options.
	 * 
	 * @return dropzone options.
	 */
	public native Options getOptions() /*-{
		var e = this.@de.objectiveit.gwt.aws.dropzone.client.Dropzone::getElement()();
		return e.dropzone.options;
	}-*/;
}
