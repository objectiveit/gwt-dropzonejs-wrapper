package de.objectiveit.gwt.aws.dropzone.client.event;

import de.objectiveit.gwt.aws.dropzone.client.Dropzone;

/**
 * A simple eventHandler for the Fallback Event
 *
 */
public interface DropzoneFallbackEvent {

  /**
   * Called when the {@link Dropzone} dispatch the onFallback event
   */
  public void onFallback();

}
