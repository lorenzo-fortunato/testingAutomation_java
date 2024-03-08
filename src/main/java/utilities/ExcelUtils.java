package utilities;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtils {
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    private static XSSFCell cell;

    public void setExcelFile(String excelFilePath, String sheetName) throws IOException {
        File file = new File(excelFilePath);
        FileInputStream fileInputStream = new FileInputStream(file);

        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
    }

    public String getCellData(int rowNum, int colNum) {
        cell = sheet.getRow(rowNum).getCell(colNum);
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    public int getRowCountInSheet() {
        int totalRowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        return totalRowCount;
    }

    public void setCellValue(int rowNum, int colNum, String value, String excelFilePath) throws IOException {
        sheet.getRow(rowNum).createCell(colNum).setCellValue(value);

        FileOutputStream fileOutputStream = new FileOutputStream(excelFilePath);
        workbook.write(fileOutputStream);
    }
}
