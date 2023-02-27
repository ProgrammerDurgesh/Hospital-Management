/*
 * package com.hospitaltask.controller; import
 * com.google.api.client.googleapis.javanet.GoogleNetHttpTransport; import
 * com.google.api.client.json.JsonFactory; import
 * com.google.api.client.json.jackson2.JacksonFactory; import
 * com.google.api.services.youtube.YouTube; import
 * com.google.api.services.youtube.model.Playlist; import
 * com.google.api.services.youtube.model.PlaylistListResponse;
 * 
 * import java.io.IOException; import java.security.GeneralSecurityException;
 * import java.util.List;
 * 
 * public class PlaylistSize { private static final JsonFactory JSON_FACTORY =
 * JacksonFactory.getDefaultInstance(); private static final String
 * APPLICATION_NAME = "YouTube Data API Java Playlist Size"; private static
 * final String API_KEY = "YOUR_API_KEY";
 * 
 * public static void main(String[] args) throws GeneralSecurityException,
 * IOException { YouTube youtubeService = new
 * YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY,
 * null) .setApplicationName(APPLICATION_NAME) .build();
 * 
 * // Replace the playlist ID with your own String playlistId =
 * "YOUR_PLAYLIST_ID";
 * 
 * YouTube.Playlists.List playlistRequest = youtubeService.playlists()
 * .list("snippet") .setKey(API_KEY) .setId(playlistId);
 * 
 * PlaylistListResponse playlistResponse = playlistRequest.execute();
 * List<Playlist> playlists = playlistResponse.getItems();
 * 
 * // Get the size of the playlist int playlistSize =
 * playlists.get(0).getSnippet().getItemCount();
 * 
 * System.out.println("Playlist size: " + playlistSize); } }
 */