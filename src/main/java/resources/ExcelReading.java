package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReading {
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	
	public ExcelReading(String FilePath) {
		
		
		File f = new File(FilePath);
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			wb = new XSSFWorkbook(fis);
			wb.getSheet("Sheet1");	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
			
		
	public int FindNumberofRows(String SheetName) {	
		int index = wb.getSheetIndex(SheetName);
		if(index==-1) {
		System.out.println("Sheet does not exist");	
		return 0;
		}
		sheet = wb.getSheetAt(index);
		int rowCount = sheet.getLastRowNum();
		return 	rowCount;
	}
	
	public int FindNumberofCells(String SheetName) {	
		int index = wb.getSheetIndex(SheetName);
		if(index==-1) {
		System.out.println("Sheet does not exist");	
		return 0;
		}
		sheet = wb.getSheetAt(index);
		int cellCount = sheet.getRow(0).getLastCellNum();
		return 	cellCount;
	}
	
	public String getCellData(String SheetName, int rowNo , int colNo) {	
		int index = wb.getSheetIndex(SheetName);
		if(index==-1) {
		System.out.println("Sheet does not exist");	
		return null;
		}
		sheet = wb.getSheetAt(index);
		row = sheet.getRow(rowNo);
		cell = row.getCell(colNo);
		String cellData=null;
		if (cell.getCellType()==CellType.NUMERIC) {			
			cellData = String.valueOf(cell.getNumericCellValue());
			return cellData;
		}else if(cell.getCellType()==CellType.STRING) {
			cellData = cell.getStringCellValue();
			return cellData;
		}else if(DateUtil.isCellDateFormatted(cell)) {
			Date d = cell.getDateCellValue();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY") ;
			cellData = sdf.format(d);	
			return cellData;
		}
			return cellData;		
		}
		
	public String getCellData(String SheetName, int rowNo , String colName) {
		
		
		int x = wb.getSheetIndex(SheetName);
		if(x==-1)
			return null;
		int columnNumber = 0;
		sheet = wb.getSheetAt(x);
		//find index of column 
		int numberofCells = sheet.getRow(0).getLastCellNum();
		for(int i=0; i<numberofCells;i++) {
			if(sheet.getRow(0).getCell(i).getStringCellValue().equals(colName)) {
				
				columnNumber= i;
			}
		}
		row = sheet.getRow(rowNo);
		cell= sheet.getRow(rowNo).getCell(columnNumber);
		
		String cellData=null;
		if (cell.getCellType()==CellType.NUMERIC) {			
			cellData = String.valueOf(cell.getNumericCellValue());
			return cellData;
		}else if(cell.getCellType()==CellType.STRING) {
			cellData = cell.getStringCellValue();
			return cellData;
		}else if(DateUtil.isCellDateFormatted(cell)) {
			Date d = cell.getDateCellValue();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY") ;
			cellData = sdf.format(d);	
			return cellData;
		}
			return cellData;		
	
	}
		
}
