package com.iwinner.wats.rs.dto;

public class SpotifyCodesDTO {

	private String spotifyCodeID;
	private String spotifyMessage;
	private String spotifyIssueSystem;
	private String spotifyReturnMessage;

	public String getSpotifyCodeID() {
		return spotifyCodeID;
	}

	public void setSpotifyCodeID(String spotifyCodeID) {
		this.spotifyCodeID = spotifyCodeID;
	}

	public String getSpotifyMessage() {
		return spotifyMessage;
	}

	public void setSpotifyMessage(String spotifyMessage) {
		this.spotifyMessage = spotifyMessage;
	}

	public String getSpotifyIssueSystem() {
		return spotifyIssueSystem;
	}

	public void setSpotifyIssueSystem(String spotifyIssueSystem) {
		this.spotifyIssueSystem = spotifyIssueSystem;
	}

	public String getSpotifyReturnMessage() {
		return spotifyReturnMessage;
	}

	public void setSpotifyReturnMessage(String spotifyReturnMessage) {
		this.spotifyReturnMessage = spotifyReturnMessage;
	}

	@Override
	public String toString() {
		return "SpotifyCodesDTO [spotifyCodeID=" + spotifyCodeID
				+ ", spotifyMessage=" + spotifyMessage
				+ ", spotifyIssueSystem=" + spotifyIssueSystem
				+ ", spotifyReturnMessage=" + spotifyReturnMessage + "]";
	}

}
