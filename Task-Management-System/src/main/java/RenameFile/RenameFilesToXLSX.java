package RenameFile;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RenameFilesToXLSX {
	public static void main(String[] args) {
        String sourceFolderPath = "C:/Users/Suavisindia/Downloads/S01";
        String destFolderPath = "C:/Users/Suavisindia/OneDrive/Desktop/S01_Extracted";

        File sourceFolder = new File(sourceFolderPath);
        File destFolder = new File(destFolderPath);
        if (!destFolder.exists()) destFolder.mkdirs();

        File[] files = sourceFolder.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                String newFileName = fileName.replaceAll("\\..*$", "") + ".xlsx"; // remove old extension
                File newFile = new File(destFolder, newFileName);

                try (BufferedReader br = new BufferedReader(new FileReader(file));
                     Workbook workbook = new XSSFWorkbook()) {

                    Sheet sheet = workbook.createSheet("Sheet1");
                    String line;
                    int rowNum = 0;

                    while ((line = br.readLine()) != null) {
                        String[] values = line.split("\t"); // or "," depending on your file
                        Row row = sheet.createRow(rowNum++);
                        for (int i = 0; i < values.length; i++) {
                            row.createCell(i).setCellValue(values[i]);
                        }
                    }

                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        workbook.write(fos);
                    }

                    System.out.println("Converted: " + fileName + " -> " + newFileName);
                } catch (IOException e) {
                    System.out.println("Error processing file: " + fileName);
                    e.printStackTrace();
                }
            }
        }
    }
}
