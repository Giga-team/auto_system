package com.gigateam.cardealershipsystemapi.service.impl;

import com.gigateam.cardealershipsystemapi.service.PDFCreatorService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DefaultPDFCreatorService implements PDFCreatorService {

  @Override
  public void textToPDF(String text) {
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    String strDate = dateFormat.format(date);

    try {
      Document document = new Document();
      PdfWriter.getInstance(document, new FileOutputStream("report_" +strDate + ".pdf"));
      document.open();
      Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.BLACK);

      String[] lines = text.split("\\n");
      for (String line : lines) {
        Paragraph paragraph = new Paragraph(line + "\n", font);
        document.add(paragraph);

        System.out.println(line);
      }
      document.close();
    } catch (DocumentException e) {
      throw new RuntimeException(e);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
