package com.liseri.anprj.web.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private HeaderUtil(){
    }
    public static HttpHeaders createUploadResultHead(String result, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-anprjApp-upload-result", result);
        headers.add("X-anprjApp-upload-message", message);
        return headers;
    }
    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-anprjApp-alert", message);
        headers.add("X-anprjApp-params", param);
        return headers;
    }
    public static HttpHeaders createEntityOperationAlert(String entityName, String operateName, String param) {
        return createAlert("anprjApp." + entityName + "." + operateName, param);
    }
    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createEntityOperationAlert(entityName, "created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createEntityOperationAlert(entityName, "updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createEntityOperationAlert(entityName, "deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        log.error("Entity creation failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-anprjApp-error", "error." + errorKey);
        headers.add("X-anprjApp-params", entityName);
        return headers;
    }
}
