package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelBeanBackup {
	
	static Calendar now = Calendar.getInstance();
	private static int hour = now.get(Calendar.HOUR_OF_DAY);
	private static int minute = now.get(Calendar.MINUTE);
	private static int second = now.get(Calendar.SECOND);
	
	private static String excelFileName = "D:\\Btpn_Jenius\\Automation_Test case_BTPN_v6.xls";
	
	public String[] getInputData(String moduleName){
		String[] inputData = null;
		try(FileInputStream file = new FileInputStream(new File("D:\\Btpn_Jenius\\Jenius_Data_Container.xlsx"));){
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

	public static void CopyExcel(String Android_device,String appCode) throws IOException{
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy");
		SimpleDateFormat sd1 = new SimpleDateFormat("MMM");
		SimpleDateFormat sd2 = new SimpleDateFormat("dd");
		Calendar c = Calendar.getInstance();

		Formatter fmt = new Formatter();
		Calendar cal = Calendar.getInstance();
		fmt = new Formatter();
		fmt.format("%tB", cal, cal, cal);

		File files = new File("C:\\Logs\\" + sd.format(c.getTime()) + "\\"
				+ sd1.format(c.getTime()) + "\\" + sd2.format(c.getTime())
				+ "\\" + Android_device +"\\"+appCode+"\\"+hour+"_"+minute);

		if (!files.exists()) {
			if (files.mkdirs()) {
				System.out.println("Multiple Directories are Created!");
			} else {
				System.out.println("Failed to Create Multiple Directories!");
			}
		}

		InputStream is = null;
		OutputStream os = null;

		try {
			is = new FileInputStream("D:\\Btpn_Jenius\\Automation_Test case_BTPN_v6.xls");
			os = new FileOutputStream("C://Logs//" + sd.format(c.getTime()) + "//"
					+ sd1.format(c.getTime()) + "//" + sd2.format(c.getTime())
					+ "//" + Android_device+"//"+appCode+"//"+hour+"_"+minute+"//Test_Case_Results.xls");

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


	//	}

	static void updateResultsExcel(String testID, String resultFlag){
		try{

			FileInputStream file = new FileInputStream(new File(excelFileName));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheet("Sheet1");

			int rowNumber = findRow(sheet, testID.substring(7));

			Row row = sheet.getRow(rowNumber);
			System.out.println("#-----#-----#----#");
			System.out.println(rowNumber);
			System.out.println(testID.substring(7));

			Cell cell = row.getCell(13);
			if (cell == null)
				cell = row.createCell(13);
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

	private static int findRow(HSSFSheet sheet, String cellContent) {

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

	public static int clearExcelColumn(String testID){

		try{
			FileInputStream fis = new FileInputStream(new File(excelFileName));
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheet("Sheet1");
			
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				Cell cell = row.getCell(13);
				System.out.println(cell);
				cell.setCellType(CellType.BLANK);
			}
			FileOutputStream out = new FileOutputStream(new File("D:\\Btpn_Jenius\\Automation_Test case_BTPN_v6.xls"));
            workbook.write(out);
            out.close();
            System.out.println("Written successfully on disk.");
			fis.close();
			workbook.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	
/*		public static void main(String[] args) {
		String[] data = new ExcelBean().getInputData("InAndOut");
		for(String str : data){
			System.out.println(str);
		}

		try {
			FileInputStream fis = new FileInputStream(new File(excelFileName));
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheet("Sheet1");
			System.out.println(findRow(sheet, "SC_004_TC_001_TS002"));;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




//		new ExcelBean().updateResultsExcel("D:\\Btpn_Jenius\\Btpn_Jenius_Android\\Automation_Test case_BTPN_v6.xls", "Pass");
		System.out.println("Done");
	}
*/
}