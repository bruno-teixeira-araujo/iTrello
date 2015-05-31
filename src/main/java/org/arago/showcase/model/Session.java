package org.arago.showcase.model;

import javax.ws.rs.client.Client;

import org.glassfish.jersey.client.oauth1.AccessToken;

public interface Session {
	void startAuthorization(String authKey, String authSecret, String callbackURL);
	void createtClientSession(String oauthVerifier);
	String getAuthFlowURL();
	Client getClient();
	AccessToken getAccessToken();
}
