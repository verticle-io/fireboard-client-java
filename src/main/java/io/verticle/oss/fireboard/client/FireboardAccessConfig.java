package io.verticle.oss.fireboard.client;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jens Saade
 */
public class FireboardAccessConfig {

    public static final String FIREBOARD_API_BUCKET = "fireboard.api.bucket";
    public static final String FIREBOARD_API_TENANT = "fireboard.api.tenant";
    public static final String FIREBOARD_API_AUTH_TOKEN = "fireboard.api.auth.token";
    public static final String FIREBOARD_API_ENDPOINT = "fireboard.api.endpoint";
    protected final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    private String apiEndpointUrl = Constants.DEFAULT_API_URL;
    private String authToken;
    private String bucketId;
    private String tenantId;


    public FireboardAccessConfig(){


        // read from system properties by default
        setApiEndpointUrl(System.getProperty(FIREBOARD_API_ENDPOINT, Constants.DEFAULT_API_URL));
        setAuthToken(System.getProperty(FIREBOARD_API_AUTH_TOKEN, ""));
        setTenantId(System.getProperty(FIREBOARD_API_TENANT, ""));
        setBucketId(System.getProperty(FIREBOARD_API_BUCKET, ""));

    }

    protected void dumpConfig(){
        logger.log(Level.INFO, FIREBOARD_API_ENDPOINT + "   : " + getApiEndpointUrl());
        logger.log(Level.INFO, FIREBOARD_API_AUTH_TOKEN + " : length = " + getAuthToken().length());
        logger.log(Level.INFO, FIREBOARD_API_TENANT + "     : " + getTenantId());
        logger.log(Level.INFO, FIREBOARD_API_BUCKET + "    : " + getBucketId());

    }

    protected boolean isConfigurationValid(){
        return getAuthToken()  != null
                && getAuthToken().length() > 0
                && getBucketId() != null
                && getBucketId().length() > 0
                && getTenantId() != null
                && getTenantId().length() > 0
                && getApiEndpointUrl() != null
                && getApiEndpointUrl().length() > 0;
    }

    public String getApiEndpointUrl() {
        return apiEndpointUrl.replace("{{TENANT}}", tenantId).replace("{{BUCKET}}", bucketId);
    }

    public void setApiEndpointUrl(String apiEndpointUrl) {
        this.apiEndpointUrl = apiEndpointUrl;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getBucketId() {
        return bucketId;
    }

    public void setBucketId(String bucketId) {
        this.bucketId = bucketId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
