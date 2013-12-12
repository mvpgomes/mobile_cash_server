package com.sirs.mobilecashserver.conn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.net.ssl.HttpsURLConnection;

public class ConnectionManager {

	private static ConnectionManager instance;

	public ConnectionManager() {
	}

	public void sendDELETE(HttpsURLConnection conn, String input)
			throws IOException {
		conn.setDoOutput(true);
		conn.setRequestMethod("DELETE");
		conn.setRequestProperty("Content-Type", "application/json");
		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
	}

	public void sendPUT(HttpsURLConnection conn, String input)
			throws IOException {
		conn.setDoOutput(true);
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Content-Type", "application/json");
		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
		os.close();
	}

	public void sendGET(HttpsURLConnection conn) throws IOException {
		conn.setRequestMethod("GET");
		conn.setRequestProperty("accept", "application/json");
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
	}

	public void sendPOST(HttpsURLConnection conn, String input)
			throws IOException {
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
	}

	public String readConnection(HttpsURLConnection conn) throws IOException {
		String inputLine;
		StringBuffer response = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));

		while ((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}

		br.close();

		return response.toString();
	}

	public static ConnectionManager getInstance() {
		if (ConnectionManager.instance == null) {
			ConnectionManager.instance = new ConnectionManager();
		}
		return ConnectionManager.instance;
	}
}