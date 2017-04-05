package framework;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RunnerMethods {
	static Class[] createClassList(Class[] classList) {
		Class[] finalClassList = new Class[1];
		int counter = 0;
		for (Class cls : classList){
			String flag = readRunFlag(cls.getSimpleName());
			if(flag == "Y"){
				finalClassList[counter] = cls;
				counter++;
			}
			else
				continue;
		}
		
		return finalClassList;
	}
	private static String readRunFlag(String className){
		String runFlag = null;
		try(FileInputStream fis = new FileInputStream(new File("C:\\Users\\Q2435\\Desktop\\RunControl.xlsx"))){
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			int rowNumber = findRow(sheet, className);
			Row row = sheet.getRow(rowNumber);
			Cell cell = row.getCell(1);
			runFlag = cell.toString();
			workbook.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return runFlag;
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
}
