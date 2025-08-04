package com.demo.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ExcelReader {
    private static final String EXCEL_PATH = "src/test/resources/testdata.xlsx";

    public static Object[][] getTestData(String sheetName, String testCaseName) {
        List<Object[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(EXCEL_PATH));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String tcName = getCellValue(row.getCell(0));
                if (tcName.equalsIgnoreCase(testCaseName)) {
                    String element = getCellValue(row.getCell(1));
                    String action = getCellValue(row.getCell(2));
                    String xpath = getCellValue(row.getCell(3));
                    String report = getCellValue(row.getCell(4));
                    data.add(new Object[]{element, action, xpath, report});
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data.toArray(new Object[0][]);
    }

    public static Object[][] getUIValidationData(String sheetName) {
        List<Object[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(EXCEL_PATH));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String testCase = getCellValue(row.getCell(0));
                String element = getCellValue(row.getCell(1));
                String action = getCellValue(row.getCell(2));
                String xpath = getCellValue(row.getCell(3));
                String report = getCellValue(row.getCell(4));

                if (testCase.isEmpty() || testCase.startsWith("#") || testCase.startsWith("^^")) {
                    System.out.println("🔃 Skipping commented/empty test case in row: " + i);
                    continue;
                }

                if (xpath.isEmpty()) {
                    System.out.println("⚠️ Skipping row " + i + " due to empty XPath (Test case: " + testCase + ")");
                    continue;
                }

                data.add(new Object[]{testCase, element, action, xpath, report});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data.toArray(new Object[0][]);
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula().trim();
            case BLANK -> "";
            default -> "";
        };
    }
}
