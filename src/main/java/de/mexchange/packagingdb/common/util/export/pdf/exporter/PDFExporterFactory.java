package de.mexchange.packagingdb.common.util.export.pdf.exporter;

import de.mexchange.packagingdb.common.util.export.ExportType;
import de.mexchange.packagingdb.service.MessageService;

/**
 * User: Garik
 * Date: 6/7/16
 * Time: 10:32 PM
 */
public class PDFExporterFactory {

    public static PDFExporter getExporter(ExportType exportType, MessageService messageService) {
        if (exportType == ExportType.PDF_LANDSCAPE) {
            return new PDFLanscapeExporter(messageService);
        }

        if (exportType == ExportType.PDF_PORTRAIT) {
            return new PDFPortraitExporter(messageService);
        }

        throw new IllegalArgumentException(String.format("No exporter was found by %s type", exportType));
    }

}
