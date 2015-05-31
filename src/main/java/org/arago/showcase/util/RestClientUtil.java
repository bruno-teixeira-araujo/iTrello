package org.arago.showcase.util;

import javax.ws.rs.core.Response;

import org.arago.showcase.model.Session;
import org.glassfish.jersey.client.oauth1.OAuth1ClientSupport;

public final class RestClientUtil {
	private RestClientUtil() {}

	public static Response get(Session session, String target, Object... params) {
		Response response = session.getClient().target(target)
				.request()
				.property(OAuth1ClientSupport.OAUTH_PROPERTY_ACCESS_TOKEN, session.getAccessToken())
				.get();
		validate(response);
		return response;
	}

	public static void validate(Response response) {
		if(response.getStatus() != 200) {
			int statusCode = response.getStatus();
			response.close();
			throw new RuntimeException(String.format("An error happened while getting response. Status code: %d", statusCode));
		}	
	}
	
}
