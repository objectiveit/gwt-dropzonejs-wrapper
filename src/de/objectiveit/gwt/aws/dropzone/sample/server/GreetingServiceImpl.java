package de.objectiveit.gwt.aws.dropzone.sample.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.objectiveit.gwt.aws.dropzone.sample.client.GreetingService;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		return input;

	}

}
