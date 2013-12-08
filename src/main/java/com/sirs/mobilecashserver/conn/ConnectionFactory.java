package com.sirs.mobilecashserver.conn;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * A factory for creating Connection objects.
 */
public class ConnectionFactory {

    /** The instance. */
    private static ConnectionFactory instance;

    /**
     * Instantiates a new connection factory.
     */
    private ConnectionFactory() {
    }

    /**
     * Creates a new Connection object.
     * 
     * @param https_url the https_url
     * @return the https url connection
     * @throws MalformedURLException the malformed url exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public HttpsURLConnection createConnection(String https_url) throws MalformedURLException, IOException {
        URL url = new URL(https_url);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        return conn;
    }

    /**
     * Close connection.
     * 
     * @param conn the conn
     */
    public void closeConnection(HttpsURLConnection conn) {
        conn.disconnect();
    }

    /**
     * Gets the single instance of ConnectionFactory.
     * 
     * @return single instance of ConnectionFactory
     */
    public static ConnectionFactory getInstance() {
        if (ConnectionFactory.instance == null) {
            ConnectionFactory.instance = new ConnectionFactory();
        }
        return ConnectionFactory.instance;
    }
}
