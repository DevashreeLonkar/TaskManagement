package com.task.management;

import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ConvertToExcel {

//	private static final int MAX_ROWS_PER_FILE = 100000; // 100k rows per file
//
//    public static void main(String[] args) {
//        String sourceFolderPath = "C:/Users/Suavisindia/Downloads/S01";
//        String destFolderPath = "C:/Users/Suavisindia/OneDrive/Desktop/S01_Extracted";
//        String baseFileName = "Merged_Filtered";
//
//        File sourceFolder = new File(sourceFolderPath);
//        File destFolder = new File(destFolderPath);
//        if (!destFolder.exists()) destFolder.mkdirs();
//
//        int fileIndex = 1;
//        int rowCounter = 0;
//
//        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
//        Sheet sheet = workbook.createSheet("MergedData");
//
//        File[] files = sourceFolder.listFiles();
//        if (files == null) return;
//
//        for (File file : files) {
//            if (!file.isFile()) continue;
//
//            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    // Split by comma but ignore commas inside quotes
//                    String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
//
//                    // Remove quotes
//                    for (int i = 0; i < values.length; i++) {
//                        values[i] = values[i].trim();
//                        if (values[i].startsWith("\"") && values[i].endsWith("\"")) {
//                            values[i] = values[i].substring(1, values[i].length() - 1);
//                        }
//                    }
//
//                    // Filter by column 'I' (index 8)
//                    if (values.length > 8 && "S01".equals(values[8])) {
//                        Row row = sheet.createRow(rowCounter++);
//                        for (int i = 0; i < values.length; i++) {
//                            row.createCell(i).setCellValue(values[i]);
//                        }
//
//                        // If row limit reached, write current workbook to file and start new workbook
//                        if (rowCounter >= MAX_ROWS_PER_FILE) {
//                            saveWorkbook(workbook, destFolder, baseFileName, fileIndex++);
//                            workbook.dispose(); // dispose temp files
//                            workbook = new SXSSFWorkbook(100);
//                            sheet = workbook.createSheet("MergedData");
//                            rowCounter = 0;
//                        }
//                    }
//                }
//            } catch (IOException e) {
//                System.out.println("Error processing file: " + file.getName());
//                e.printStackTrace();
//            }
//        }
//
//        // Save the remaining rows
//        if (rowCounter > 0) {
//            saveWorkbook(workbook, destFolder, baseFileName, fileIndex);
//            workbook.dispose();
//        }
//
//        System.out.println("All filtered data merged and split into multiple files.");
//    }
//
//    private static void saveWorkbook(SXSSFWorkbook workbook, File destFolder, String baseFileName, int index) {
//        String fileName = baseFileName + "_" + index + ".xlsx";
//        File file = new File(destFolder, fileName);
//        try (FileOutputStream fos = new FileOutputStream(file)) {
//            workbook.write(fos);
//            System.out.println("Saved: " + fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
	
	private static final int MAX_ROWS_PER_FILE = 100000; // 100k rows per file

    public static void main(String[] args) {
        String sourceFolderPath = "C:/Users/Suavisindia/Downloads/S01";
        String destFolderPath = "C:/Users/Suavisindia/OneDrive/Desktop/S01_Extracted";
        String baseFileName = "Merged_Filtered";

        File sourceFolder = new File(sourceFolderPath);
        File destFolder = new File(destFolderPath);
        if (!destFolder.exists()) destFolder.mkdirs();

        int fileIndex = 1;
        int rowCounter = 0;

        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        Sheet sheet = workbook.createSheet("MergedData");

        // Define your headers
//        String[] headers = {"Column1", "Column2", "Column3", "Column4", "Column5", 
//                            "Column6", "Column7", "Column8", "ORG_NAME", "Column10"}; // adjust names
        String[] headers = {"SERIAL_NUMBER", "INVENTORY_ITEM_ID", "ITEM_NUMBER", "DESCRIPTION", "CURRENT_SUBINVENTORY_CODE", 
        		"CURRENT_LOCATOR_ID", "CURRENT_ORGANIZATION_ID", "INVENTORY_ITEM_STATUS_CODE", "ORG_NAME", "SKU", "CATEGORY_SET_NAME", 
        		"Value Type", "Product Type", "Part", "Model", "Caliber"};
        // Add header row initially
        Row headerRow = sheet.createRow(rowCounter++);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        File[] files = sourceFolder.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (!file.isFile()) continue;

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // Split by comma but ignore commas inside quotes
                    String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                    // Remove quotes
                    for (int i = 0; i < values.length; i++) {
                        values[i] = values[i].trim();
                        if (values[i].startsWith("\"") && values[i].endsWith("\"")) {
                            values[i] = values[i].substring(1, values[i].length() - 1);
                        }
                    }

                    // Filter by column 'I' (index 8)
                    if (values.length > 8 && "S01".equals(values[8])) {
                        Row row = sheet.createRow(rowCounter++);
                        for (int i = 0; i < values.length; i++) {
                            row.createCell(i).setCellValue(values[i]);
                        }

                        // If row limit reached, write current workbook to file and start new workbook
                        if (rowCounter >= MAX_ROWS_PER_FILE) {
                            saveWorkbook(workbook, destFolder, baseFileName, fileIndex++);

                            // Dispose temp files
                            workbook.dispose();

                            // Create new workbook & sheet
                            workbook = new SXSSFWorkbook(100);
                            sheet = workbook.createSheet("MergedData");
                            rowCounter = 0;

                            // Add header to new file
                            Row newHeader = sheet.createRow(rowCounter++);
                            for (int i = 0; i < headers.length; i++) {
                                newHeader.createCell(i).setCellValue(headers[i]);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error processing file: " + file.getName());
                e.printStackTrace();
            }
        }

        // Save the remaining rows
        if (rowCounter > 0) {
            saveWorkbook(workbook, destFolder, baseFileName, fileIndex);
            workbook.dispose();
        }

        System.out.println("All filtered data merged, split, and headers added in every file.");
    }

    private static void saveWorkbook(SXSSFWorkbook workbook, File destFolder, String baseFileName, int index) {
        String fileName = baseFileName + "_" + index + ".xlsx";
        File file = new File(destFolder, fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
            System.out.println("Saved: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//	private static final int MAX_ROWS_PER_FILE = 100000; // 100k rows per file
//
//    public static void main(String[] args) {
//        String sourceFolderPath = "C:/Users/Suavisindia/Downloads/S01";
//        String destFolderPath = "C:/Users/Suavisindia/OneDrive/Desktop/S01_Extracted";
//        String baseFileName = "Merged_Filtered";
//
//        File sourceFolder = new File(sourceFolderPath);
//        File destFolder = new File(destFolderPath);
//        if (!destFolder.exists()) destFolder.mkdirs();
//
//        int fileIndex = 1;
//        int rowCounter = 0;
//
//        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
//        Sheet sheet = workbook.createSheet("MergedData");
//
//        File[] files = sourceFolder.listFiles();
//        if (files == null) return;
//
//        for (File file : files) {
//            if (!file.isFile()) continue;
//
//            String originalFileName = file.getName(); // store original file name
//
//            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    // Split by comma but ignore commas inside quotes
//                    String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
//
//                    // Remove quotes
//                    for (int i = 0; i < values.length; i++) {
//                        values[i] = values[i].trim();
//                        if (values[i].startsWith("\"") && values[i].endsWith("\"")) {
//                            values[i] = values[i].substring(1, values[i].length() - 1);
//                        }
//                    }
//
//                    // Filter by column 'I' (index 8)
//                    if (values.length > 8 && "S01".equals(values[8])) {
//                        Row row = sheet.createRow(rowCounter++);
//                        // Add original file name in first column
//                        row.createCell(0).setCellValue(originalFileName);
//
//                        // Add actual data starting from column 1
//                        for (int i = 0; i < values.length; i++) {
//                            row.createCell(i + 1).setCellValue(values[i]);
//                        }
//
//                        // If row limit reached, write current workbook to file and start new workbook
//                        if (rowCounter >= MAX_ROWS_PER_FILE) {
//                            saveWorkbook(workbook, destFolder, baseFileName, fileIndex++);
//                            workbook.dispose(); // dispose temp files
//                            workbook = new SXSSFWorkbook(100);
//                            sheet = workbook.createSheet("MergedData");
//                            rowCounter = 0;
//                        }
//                    }
//                }
//            } catch (IOException e) {
//                System.out.println("Error processing file: " + file.getName());
//                e.printStackTrace();
//            }
//        }
//
//        // Save the remaining rows
//        if (rowCounter > 0) {
//            saveWorkbook(workbook, destFolder, baseFileName, fileIndex);
//            workbook.dispose();
//        }
//
//        System.out.println("All filtered data merged, split, and original file name added.");
//    }
//
//    private static void saveWorkbook(SXSSFWorkbook workbook, File destFolder, String baseFileName, int index) {
//        String fileName = baseFileName + "_" + index + ".xlsx";
//        File file = new File(destFolder, fileName);
//        try (FileOutputStream fos = new FileOutputStream(file)) {
//            workbook.write(fos);
//            System.out.println("Saved: " + fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
