package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

//Excel utilities file to open, read from excel file
public class ExcelUtilities {

	  private Workbook workbook;
	  private Sheet sheet;
	    
	public ExcelUtilities(String excelFilePath, String sheetName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
        workbook = WorkbookFactory.create(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        fileInputStream.close();
    }
	
	 public String getCellData(int rowNumber, int cellNumber) {
	        Row row = sheet.getRow(rowNumber);
	        DataFormatter formatter = new DataFormatter();
	        return formatter.formatCellValue(row.getCell(cellNumber));
	    }
	 
	 public void closeWorkbook() throws IOException {
	        if (workbook != null) {
	            workbook.close();
	        }
	    }
}
