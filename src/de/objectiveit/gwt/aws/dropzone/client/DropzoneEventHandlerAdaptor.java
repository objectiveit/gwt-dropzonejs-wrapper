package de.objectiveit.gwt.aws.dropzone.client;

import de.objectiveit.gwt.aws.dropzone.client.event.DropzoneEventHandler;
import de.objectiveit.gwt.aws.dropzone.client.interfaces.File;
import de.objectiveit.gwt.aws.dropzone.client.interfaces.FormData;
import de.objectiveit.gwt.aws.dropzone.client.interfaces.XHRObjet;

/**
 * Empty implementation for the events dispatched by the Dropzone
 *
 */
public class DropzoneEventHandlerAdaptor implements DropzoneEventHandler {

  @Override
  public void onAddedFile(File file) {
    // NOOP
  }

  @Override
  public void onCancelled(File file) {
    // NOOP
  }

  @Override
  public void onComplete(File file) {
    // NOOP
  }

  @Override
  public void onError(File file, String message, XHRObjet xhrObject) {
    // NOOP
  }

  @Override
  public void onMaxFilesExceeded(File file) {
    // NOOP
  }

  @Override
  public void onMaxFilesReached(File file) {
    // NOOP
  }

  @Override
  public void onProcessing(File file) {
    // NOOP
  }

  @Override
  public void onRemovedfile(File file) {
    // NOOP
  }

  @Override
  public void onSending(File file, FormData formData, XHRObjet xhrObject) {
    // NOOP
  }

  @Override
  public void onSuccess(File file, String response) {
    // NOOP
  }

  @Override
  public void onThumbnail(File file, String dataURI) {
    // NOOP
  }

  @Override
  public void onUploadProgress(File file, int progressSent, int bytesSent) {
    // NOOP
  }

}
