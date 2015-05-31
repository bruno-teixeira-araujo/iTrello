package org.arago.showcase.model;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.oauth1.AccessToken;
import org.glassfish.jersey.client.oauth1.ConsumerCredentials;
import org.glassfish.jersey.client.oauth1.OAuth1AuthorizationFlow;
import org.glassfish.jersey.client.oauth1.OAuth1ClientSupport;

public class TrelloSession implements Session {
	private ConsumerCredentials consumerCredentials;
	private OAuth1AuthorizationFlow authFlow;
	private String authFlowURL;
	private AccessToken accessToken;
	private Client client;
	
	public void startAuthorization(String authKey, String authSecret, String callbackURL) {
    	this.consumerCredentials = new ConsumerCredentials(authKey, authSecret);
    	this.authFlow = OAuth1ClientSupport.builder(consumerCredentials)
    			.authorizationFlow(
    					"https://trello.com/1/OAuthGetRequestToken",
    					"https://trello.com/1/OAuthGetAccessToken",
    					"https://trello.com/1/OAuthAuthorizeToken")
    					.callbackUri(callbackURL)
    					.build();
    	
		this.authFlowURL = authFlow.start();
	}
	
	public void createtClientSession(String oauthVerifier) {
		accessToken = this.authFlow.finish(oauthVerifier);
		client = ClientBuilder.newBuilder()
				.register(this.authFlow.getOAuth1Feature())
				.build();
	}

	public ConsumerCredentials getConsumerCredentials() {
		return consumerCredentials;
	}

	public void setConsumerCredentials(ConsumerCredentials consumerCredentials) {
		this.consumerCredentials = consumerCredentials;
	}

	public OAuth1AuthorizationFlow getAuthFlow() {
		return authFlow;
	}

	public void setAuthFlow(OAuth1AuthorizationFlow authFlow) {
		this.authFlow = authFlow;
	}
	
	public String getAuthFlowURL() {
		return authFlowURL;
	}

	public void setAuthFlowURL(String authFlowURL) {
		this.authFlowURL = authFlowURL;
	}

	public AccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
