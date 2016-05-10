package sample;

/**
 * Created by User on 09.05.2016.
 */
/**
 * Created by User on 09.05.2016.
 */

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.Scene;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class Border {
    // public static final String DEST = "results/events/paragraph_with_border.pdf";
    //FileChooser fileChooser = new FileChooser();

    static class ParagraphBorder extends PdfPageEventHelper {
        public boolean active = false;
        public void setActive(boolean active) {
            this.active = active;
        }

        public float offset = 5;
        public float startPosition;

        @Override
        public void onParagraph(PdfWriter writer, Document document, float paragraphPosition) {
            this.startPosition = paragraphPosition;
        }

        @Override
        public void onParagraphEnd(PdfWriter writer, Document document, float paragraphPosition) {
            if (active) {
                PdfContentByte cb = writer.getDirectContentUnder();
                cb.rectangle(document.left(), paragraphPosition - offset,
                        document.right() - document.left(), startPosition - paragraphPosition);
                cb.stroke();
            }
        }
    }


    public static void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        ParagraphBorder border = new ParagraphBorder();
        writer.setPageEvent(border);
        document.open();
         final String FONT = "C:\\Windows\\Fonts\\Pushkin.otf";

        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        com.itextpdf.text.Font font = FontFactory.getFont(FONT, "Cp1251", BaseFont.EMBEDDED);
        document.add(new Paragraph("Здравствуйте, " +Person.name, font));
        border.setActive(true);

        document.add(new Paragraph("Вы успешно прошли обучение русскому языку в группе  -" + Person.group,font));
        document.add(new Paragraph("Ваш результат: "+ReadMark.student_marks +"баллов",font));
       /* document.add(new Paragraph("This paragraph now has a border. Isn't that fantastic? By changing the event, we can even provide a background color, change the line width of the border and many other things. Now let's deactivate the event."));
        border.setActive(false);
        document.add(new Paragraph("This paragraph no longer has a border."));*/
        document.close();
    }
}
