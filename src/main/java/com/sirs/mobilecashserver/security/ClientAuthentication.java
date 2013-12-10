package com.sirs.mobilecashserver.security;

/**
 *  Just an example for client authentication, need some adjustments
 *  for our purpose.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import com.sirs.mobilecashserver.conn.ConnectionFactory;

public class ClientAuthentication {

    private static String url = "sodamachine.herokuapp.com";

    public static void main(String[] args) {

        // instancia da fabrica de conexões
        ConnectionFactory connFactory = ConnectionFactory.getInstance();

        // altera as propriedades do sistema para usar a chave publica do servidor    
        Properties systemProperties = System.getProperties();
        systemProperties.put("javax.net.ssl.trustStore", "path da chave publica");
        System.setProperties(systemProperties);

        try {

            // altera os parametros do sistema com o username e password
            String requestParams = "uid=adds&password=aAsS22.q&active=y&type=F";
            // abre uma conexão segura
            HttpsURLConnection conn = connFactory.createConnection(url);

            // altera as configurações da conexão
            conn.setRequestProperty("Connection", "close");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            // configura o request mode
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", Integer.toBinaryString(requestParams.length()));

            // configura a autenticação por parte do cliente com a chave privada do cliente
            File pKeyFile = new File("path da chave privada");
            String pKeyPassword = "UB#20abba"; // opcional
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("x509"); // verificar o argumento
            KeyStore keyStore = KeyStore.getInstance("PKCS12"); // verificar o argumento
            InputStream keyInput = new FileInputStream(pKeyFile);
            keyStore.load(keyInput, pKeyPassword.toCharArray());
            // abre o contexto SSL
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
            SSLSocketFactory sockFact = context.getSocketFactory();

            // envia o pedido
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(requestParams.getBytes("UTF-8"));
            outputStream.close();

            // verifica se ocorreram erros
            int responseCode = conn.getResponseCode();
            InputStream inputStream;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = conn.getInputStream();
            } else {
                inputStream = conn.getErrorStream();
            }

            // Processa a resposta
            BufferedReader reader;
            String line = null;
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
