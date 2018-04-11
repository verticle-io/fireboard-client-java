package io.verticle.oss.fireboard.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FireboardAPIAccessor connects to the API endpoint and posts messages.
 * @author Jens Saade
 */
public class FireboardAPIAccessor {

    protected final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private boolean debugMode = "true".equalsIgnoreCase(System.getProperty("fireboard.debug"));

    private FireboardAccessConfig config;


    protected FireboardAPIAccessor(FireboardAccessConfig config) {
        logger.info("Initializing Fireboard API Client");
        this.config = config;
        checkConfiguration();
    }


    protected int sendStatusEvent(Status status) {

        if (debugMode){
            logger.log(Level.FINEST, "Fireboard: sending status " + status.getIdent());
        }

        if (config == null || !config.isConfigurationValid()){
            if (debugMode){
                logger.log(Level.FINEST, "Fireboard configuration not validated, skipping send status event.");
            }
            return 0;
        }

        int responseCode = 200; // default

        String body = new StatusJSONConverter().convert(status);

        try {

            URL url = new URL(config.getApiEndpointUrl());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");

            con.setRequestProperty("Authorization", "Bearer " + config.getAuthToken());
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-type", "application/json");

            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            con.setDoOutput(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.getOutputStream());
            outputStreamWriter.write(body);
            outputStreamWriter.flush();

            responseCode = con.getResponseCode();

            if (debugMode){
                logger.log(Level.FINEST, "Fireboard: response from service " + responseCode);
            }

            if (responseCode >= 400 || debugMode){
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                if (debugMode){
                    logger.log(Level.FINEST, "Fireboard: response from service ... \n" + content);
                }

                in.close();
            }

            con.disconnect();

        } catch (MalformedURLException e) {
            System.out.println("[ERROR] FireboardAPIAccessor - " + e.getMessage());
        } catch (ProtocolException e) {
            System.out.println("[ERROR] FireboardAPIAccessor - " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[ERROR] FireboardAPIAccessor - " + e.getMessage());
        }

        return responseCode;

    }

    protected void lazyInit(FireboardAccessConfig config) {
        this.config = config;
        checkConfiguration();
    }

    private void checkConfiguration(){
        if (config.isConfigurationValid()){
            logger.info("Configuration validated:");
            config.dumpConfig();
        }
    }

}