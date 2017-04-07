package de.mexchange.packagingdb.common.util.export.pdf.exporter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import de.mexchange.packagingdb.common.exception.ReportGenerationException;
import de.mexchange.packagingdb.common.util.export.ExportType;
import de.mexchange.packagingdb.domain.Container;
import de.mexchange.packagingdb.service.MessageService;

/**
 * User: Garik
 * Date: 6/7/16
 * Time: 10:34 PM
 */
public class PDFLanscapeExporter extends PDFExporter {

    public PDFLanscapeExporter(MessageService messageService) {
        super(messageService);
    }

    @Override
    public void export(Container container) throws ReportGenerationException {

        try {
            Document document = new Document(PageSize.A4);
            document.setMargins(5, 5, 5, 5);
            File file = File.createTempFile("report", null);
            PdfWriter.getInstance(document, new FileOutputStream(file))
                    .setStrictImageSequence(true);

            document.open();
            document.addAuthor("Me");

            initReportHeader(document);

            PdfPTable table = new PdfPTable(6);
            table.setWidths(new float[] {5f, 30f, 20f, 20f, 20f, 20f});

            List<GeneralSectionEnum> sectionEnumList = GeneralSectionEnum.getByExportType(ExportType.PDF_LANDSCAPE);
            java.lang.reflect.Method method;
            for (GeneralSectionEnum generalSectionEnum : sectionEnumList) {


                String label = messageService.getMessage(generalSectionEnum.labelKey);

                SectionEnum[] sectionEnums = generalSectionEnum.sectionEnums;
                if (sectionEnums != null) {
                   for (SectionEnum sectionEnum : sectionEnums) {
                       String[] methods = sectionEnum.labelMethod;
                       if (methods == null) {
                        continue;
                    }

                       for (String str : methods) {
                           try {
                               method = container.getClass().getMethod(str);
                               Object obj = method.invoke(container);
                               if (obj != null) {
                                   String val = obj.toString();

                               }
                           } catch (Exception e) {
                               // exception handling omitted for brevity
                           }
                       }
                    }
                }
            }



            document.add(table);

            document.close();


            //return FileUtils.openInputStream(file);
        } catch (Exception e) {
            throw new ReportGenerationException("Failed to generate report for security balance", e);
        }
    }

    /**
     * Initializes report header
     * @param document
     * @throws DocumentException
     */
    @Override
    protected void initReportHeader(Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setSpacingBefore(10);
        table.setSpacingAfter(35);

        PdfPCell titleCell = new PdfPCell(new Phrase("Only Text",  new Font(font.getBaseFont(), 10)));
        titleCell.setBorder(Rectangle.NO_BORDER);
        titleCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(titleCell);
        document.add(table);
    }
}
