package de.mexchange.packagingdb.service.util;

import de.mexchange.packagingdb.common.exception.InternalErrorException;
import de.mexchange.packagingdb.service.notification.util.EmailNotificationEvent;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Artur on 6/19/2016.
 */
public class PasswordUtil {

    public static final String MAPPING_RESET_PASSWORD = "/reset-password";
    public static final String MAPPING_FORGET_PASSWORD = "/forget-user-password";

    public static String preparePasswordChangeUrl(HttpServletRequest req, EmailNotificationEvent event) throws InternalErrorException {

        String scheme = req.getScheme();             // http
        String serverName = req.getServerName();     // hostname.com
        int serverPort = req.getServerPort();        // 80
        String servletPath ;
        switch (event) {
            case ACTIVATE_ACCOUNT:
                servletPath = MAPPING_RESET_PASSWORD;
                break;
            case FORGET_PASSWORD:
                servletPath = MAPPING_FORGET_PASSWORD;
                break;

            default:
                throw new InternalErrorException("template not found");
        }

        // Reconstruct original requesting URL
        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);

        if (serverPort != 80 && serverPort != 443) {
            url.append(":").append(serverPort);
        }

        url.append(servletPath);
        url.append("?").append("email=%s&key=%s");

        return url.toString();
    }

    public static String toToken(String password) throws InternalErrorException {

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new InternalErrorException(e);
        }
    }
}
