package salesforce.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriting {

	
	public ExcelWriting() {
		
		
	}
		
		
	public void CreateNewSheetinExistingExcel() {
		
		try {
			FileInputStream fis = new FileInputStream(new File ("C:\\Selenium Files\\temp1.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			//System.out.println(wb.getSheetAt(0).getRow(0).getCell(0).getStringCellValue());
			//wb.close();
			FileOutputStream fos = new FileOutputStream("C:\\Selenium Files\\temp1.xlsx");
			wb.createSheet("nadd12");
			wb.write(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		
		
	}
}
