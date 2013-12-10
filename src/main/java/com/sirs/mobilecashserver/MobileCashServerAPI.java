package com.sirs.mobilecashserver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;

import com.sirs.mobilecashserver.conn.ConnectionFactory;

public class MobileCashServerAPI {

    private static HttpsURLConnection conn;

    private static String sodaMachineURL = "https://sodamachine.herokuapp.com";

    private static String path = "delivery";

    public static void main(String[] args) throws MalformedURLException, IOException {

        /* Estabilish a secure connection with the server. */
        conn = ConnectionFactory.getInstance().createConnection(sodaMachineURL);

        /* Get the server certificates. */
        Certificate[] certs = conn.getServerCertificates();

        /* Perform the server authentication. */
        /**
         * 1- Criar par de chaves para o SodaMachine e MobileCashServer
         * 2- Distribuir chave publica para os clientes
         * 3- Implementar mecanismo de autenticação.
         */

        while (true) {
            // Do stuffs
        }

    }

}
