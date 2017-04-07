package de.mexchange.packagingdb.common.util.export.pdf.exporter;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;

import de.mexchange.packagingdb.common.exception.ReportGenerationException;
import de.mexchange.packagingdb.domain.Container;
import de.mexchange.packagingdb.service.MessageService;

/**
 * User: Garik
 * Date: 6/7/16
 * Time: 10:33 PM
 */
public abstract class PDFExporter {

    private static final String FONT_PATH = "static/";
    private static final String FONT_LOCATION = "css/fonts/FiraSans-Regular.ttf";
    private static final String IMAGE_LOCATION = "image/logo_small.png";

    protected Font font;

    protected Image imgLogo;

    protected MessageService messageService;

    public PDFExporter(MessageService messageService) {
        init();
        this.messageService = messageService;
    }

    /**
     * initializes generic data
     */
    private void init() {

        // initializes font
        URL url = getClass().getClassLoader().getResource(FONT_PATH);
        if (url == null) {
            ClassLoader.getSystemClassLoader().getResource(FONT_PATH);
        }
        if (url != null && url.getPath() != null) {
            File resourcePath = new File(url.getPath());
            try {
                BaseFont baseFont = BaseFont.createFont(
                        resourcePath.getAbsolutePath() + FONT_LOCATION, BaseFont.IDENTITY_H, true);
                font = new Font(baseFont);

                imgLogo = Image.getInstance(resourcePath.getAbsolutePath() + IMAGE_LOCATION);
                imgLogo.scaleToFit(new Rectangle(175, 50));
            } catch (BadElementException e) {
                throw new RuntimeException("Bad element while retrieving font or image", e);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid URL", e);
            } catch (DocumentException e) {
                throw new RuntimeException("General DocumentException while retrieving font or image", e);
            } catch (IOException e) {
                throw new RuntimeException("IO‚Exception while retrieving font or image", e);
            }
        } else {
            throw new RuntimeException("Failed to init PDF-generator from classpath");
        }
    }

    public abstract void export(Container container) throws ReportGenerationException;

    protected abstract void initReportHeader(Document document) throws DocumentException ;


}
