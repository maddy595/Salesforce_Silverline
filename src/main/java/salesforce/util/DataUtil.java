package salesforce.util;

import java.util.Hashtable;

public class DataUtil 
{ }
   /* public static Object[][] getTestData(Xls_Reader xls, String testCaseName){
    	String sheetName="Data";
		
		// reads data for only testCaseName
		
		int testStartRowNum=1;
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(testCaseName)){
			testStartRowNum++;
		}
		System.out.println("Test starts from row - "+ testStartRowNum);
		int colStartRowNum=testStartRowNum+1;
		int dataStartRowNum=testStartRowNum+2;
		
		// calculate rows of data
		int rows=0;
		while(!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals("")){
			rows++;
		}
		System.out.println("Total rows are  - "+rows );
		
		//calculate total cols
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals("")){
			cols++;
		}
		System.out.println("Total cols are  - "+cols );
		
		Object[][] data = new Object[rows][1];
		int dataRow=0;
		Hashtable table = null;
		
		//read the data
		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++){
			
			table=new Hashtable<String, String>();
			
			for(int cNum=0;cNum<cols;cNum++){
				String Key=xls.getCellData(sheetName, cNum, colStartRowNum);
				//System.out.println(xls.getCellData(sheetName, cNum, rNum));
				String value= xls.getCellData(sheetName, cNum, rNum);
				table.put(Key, value);	
			}
			data[dataRow][0]=table;
			dataRow++;
		} 
		return data;
    }
    
    public static boolean isRunnable(String testName,Xls_Reader xls) {
		String sheet="TestCases";
		int rows=xls.getRowCount(sheet);
		for(int r=0;r<=rows;r++) {
			String tname=xls.getCellData(sheet, "TCID", r);
				if(tname.equals(testName)){
					String runmode=xls.getCellData(sheet, "Runmodes", r);
				if(runmode.equals("Y"))
					return true;
				else
					return false;
			}
		}
    	return false;
    }
    
}
*/