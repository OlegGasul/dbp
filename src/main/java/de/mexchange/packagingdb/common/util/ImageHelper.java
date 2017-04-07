package de.mexchange.packagingdb.common.util;

import java.util.Arrays;
import java.util.List;

/**
 * User: Garik
 * Date: 6/2/16
 * Time: 10:48 PM
 */
public class ImageHelper {

    private static List<String> imageContentTypes = Arrays.asList(
            "image/gif",
            "image/jpg",
            "image/jpeg",
            "image/pjpeg",              // JPEG[8]
            "image/png",
            "image/svg+xml",            // SVG[10]
            "image/tiff",               // TIFF(RFC 3302)
            "image/vnd.microsoft.icon", // ICO[11]
            "image/vnd.wap.wbmp"        //WBM
    );

    public static boolean isImageContentType(String contentType) {
        if (StringHelper.isBlank(contentType)) {
            return false;
        }
        contentType = contentType.trim().toLowerCase();
        return imageContentTypes.contains(contentType);
    }
}
