package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;
import org.testng.xml.XmlClass;

public class ExcelBean {
	
	static Calendar now = Calendar.getInstance();
	private static int hour = now.get(Calendar.HOUR_OF_DAY);
	private static int minute = now.get(Calendar.MINUTE);
	private static int second = now.get(Calendar.SECOND);
	
//	private static String excelFileName = "D:\\Bank_Sinarmas_Docs\\Automation_Test_Cases_BS_v1.xlsx";
	private static String excelFileName = "src/resource/Automation_Test_Cases_BS_v1.xlsx";
	
	public String[] getInputData(String moduleName){
		String[] inputData = null;
		try{
//			FileInputStream file = new FileInputStream(new File("D:\\Bank_Sinarmas_Docs\\BS_Data_Container.xlsx"));
			FileInputStream file = new FileInputStream(new File("src/resource/BS_Data_Container.xlsx"));
			
			//Create Workbook instance
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			//Get Sheet
			XSSFSheet sheet = workbook.getSheet(moduleName);
			
			int numberOfRows = sheet.getLastRowNum();
			inputData = new String[numberOfRows];
			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			int rowCount = 0;
			rowIterator.next();
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				//For each row, iterate through all the columns
				inputData[rowCount++] = row.getCell(3).toString();
			}
			
			file.close();
			workbook.close();
		}
		catch (Exception e) {
		}
		return inputData;
	}
	
	public static void CopyExcel(String ios_device,String appCode) throws IOException{
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy");
		SimpleDateFormat sd1 = new SimpleDateFormat("MMM");
		SimpleDateFormat sd2 = new SimpleDateFormat("dd");
		Calendar c = Calendar.getInstance();
		
		Formatter fmt = new Formatter();
		Calendar cal = Calendar.getInstance();
		fmt = new Formatter();
		fmt.format("%tB", cal, cal, cal);
		
		/*File files = new File("C:\\Logs\\" + sd.format(c.getTime()) + "\\"
				+ sd1.format(c.getTime()) + "\\" + sd2.format(c.getTime())
				+ "\\" + ios_device +"\\"+appCode+"\\"+hour+"_"+minute);*/
		
		File files = new File("src\\resource\\" + sd.format(c.getTime()) + "\\"
				+ sd1.format(c.getTime()) + "\\" + sd2.format(c.getTime())
				+ "\\" + ios_device +"\\"+appCode+"\\"+hour+"_"+minute);
		
		if (!files.exists()) {
			if (files.mkdirs()) {
				System.out.println("Multiple directories are created!");
			} else {
				System.out.println("Failed to create multiple directories!");
			}
		}
		
		InputStream is = null;
		OutputStream os = null;
		
		try {
//			is = new FileInputStream("D:\\Bank_Sinarmas_Docs\\Automation_Test_Cases_BS_v1.xlsx");
			
			is = new FileInputStream("src/resource/Automation_Test_Cases_BS_v1.xlsx");
			/*os = new FileOutputStream("C://Logs//" + sd.format(c.getTime()) + "//"
					+ sd1.format(c.getTime()) + "//" + sd2.format(c.getTime())
					+ "//" + ios_device+"//"+appCode+"//"+hour+"_"+minute+"//Test_Case_Results.xlsx");*/
			
			os = new FileOutputStream("src/resource/" + sd.format(c.getTime()) + "//"
					+ sd1.format(c.getTime()) + "//" + sd2.format(c.getTime())
					+ "//" + ios_device+"//"+appCode+"//"+hour+"_"+minute+"//Test_Case_Results.xlsx");
			
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}
	
	public static void updateEvidenceExcel(String testID, String assertValue, String rsSheetName)
	{
		try{
			
			//******For Maybank Only*******//
//			assertValue="Maybank";
			
			FileInputStream file = new FileInputStream(new File(excelFileName));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(rsSheetName);
			
			int rowNumber = findRow(sheet, testID.substring(7));
			
			Row row = sheet.getRow(rowNumber);
			
			Cell cell = row.getCell(11);
			if (cell == null)
				cell = row.createCell(11);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(assertValue);
			
			FileOutputStream out = new FileOutputStream(new File(excelFileName));
			workbook.write(out);
			out.close();
			file.close();
			workbook.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}	
		
	static void updateResultsExcel(String testID, String resultFlag, String rsSheetName){
		try{

			FileInputStream file = new FileInputStream(new File(excelFileName));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(rsSheetName);
			
			System.out.println("#=====#====#"+workbook+"#====#====#====#====#"+sheet);
			
			int rowNumber = findRow(sheet, testID.substring(7));
			
			Row row = sheet.getRow(rowNumber);
			System.out.println("#-----#-----#----#");
			System.out.println(rowNumber);
			System.out.println(testID.substring(7));
			
			Cell cell = row.getCell(18);
			if (cell == null)
				cell = row.createCell(18);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(resultFlag);
			
			FileOutputStream out = new FileOutputStream(new File(excelFileName));
			workbook.write(out);
			out.close();
			file.close();
			workbook.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	
	private static int findRow(XSSFSheet sheet, String cellContent) {

		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
						return row.getRowNum();  
					}
				}
			}
		}               
		return 0;
	}
	
	public static int clearExcelColumn(String testID, String rsSheetName){
		
		try{
			FileInputStream fis = new FileInputStream(new File(excelFileName));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(rsSheetName);
			
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				Cell cell = row.getCell(18);
//				Cell nextCell = row.getCell(14);
				
				if(cell != null)
				{
					cell.setCellType(CellType.BLANK);
				}
				
//				if(nextCell != null)
//				{
//					nextCell.setCellType(CellType.BLANK);
//				}
			}
			
			Row row = sheet.getRow(0);
            row.getCell(18).setCellValue("QK Actual Result");
			
//			FileOutputStream out = new FileOutputStream(new File("D:\\Bank_Sinarmas_Docs\\Automation_Test_Cases_BS_v1.xlsx"));

			FileOutputStream out = new FileOutputStream(new File("src/resource/Automation_Test_Cases_BS_v1.xlsx"));
            workbook.write(out);
            out.close();
//            System.out.println("written successfully on disk.");
			fis.close();
			workbook.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public static String getDeviceName(String testClassName) {
		String deviceName = null;
		try(FileInputStream fis = new FileInputStream(new File("src/resource/Controller.xlsm"))){
			
//		try(FileInputStream fis = new FileInputStream(new File("D:\\Bank_Sinarmas_Docs\\Controller.xlsm"))){
			
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("RunControl");
			int rowNumber = findRow(sheet, testClassName);
			deviceName = sheet.getRow(rowNumber).getCell(1).getStringCellValue(); 
			System.out.println("#===#===#===#===#===#===#===#");
			System.out.println(deviceName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return deviceName;
	}
		/*public static void main(String[] args) {
		String[] data = new ExcelBean().getInputData("InAndOut");
		for(String str : data){
			System.out.println(str);
		}

		try {
			FileInputStream fis = new FileInputStream(new File(excelFileName));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			System.out.println(findRow(sheet, "QB_01_TC_OQB_01_TS_001"));;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




//		new ExcelBean().updateResultsExcel("D:\\Btpn_Jenius\\Btpn_Jenius_Android\\Automation_Test case_BTPN_v6.xls", "Pass");
		System.out.println("Done");
	}*/

}
