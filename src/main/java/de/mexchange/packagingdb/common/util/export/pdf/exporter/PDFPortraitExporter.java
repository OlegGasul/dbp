package de.mexchange.packagingdb.common.util.export.pdf.exporter;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import de.mexchange.packagingdb.domain.Container;
import de.mexchange.packagingdb.service.MessageService;

/**
 * User: Garik
 * Date: 6/7/16
 * Time: 10:34 PM
 */
public class PDFPortraitExporter extends PDFExporter {


    public PDFPortraitExporter(MessageService messageService) {
        super(messageService);
    }

    @Override
    public void export(Container container) {

    }

    @Override
    protected void initReportHeader(Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setSpacingBefore(10);
        table.setSpacingAfter(35);
        PdfPCell imgCell = new PdfPCell(imgLogo);
        imgCell.setBorder(Rectangle.NO_BORDER);
        table.addCell(imgCell);

        PdfPCell titleCell = new PdfPCell(new Phrase("Text And LOGO",  new Font(font.getBaseFont(), 10)));
        titleCell.setBorder(Rectangle.NO_BORDER);
        titleCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(titleCell);
        document.add(table);
    }
}
