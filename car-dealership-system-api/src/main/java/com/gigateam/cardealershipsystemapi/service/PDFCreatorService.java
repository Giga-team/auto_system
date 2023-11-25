package com.gigateam.cardealershipsystemapi.service;

import java.io.FileNotFoundException;

public interface PDFCreatorService {

  void textToPDF(String text) throws FileNotFoundException;

}