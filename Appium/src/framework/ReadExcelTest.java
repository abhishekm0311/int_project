package framework;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelTest {
	public static void main(String[] args) {
		try(FileInputStream fis = new FileInputStream(new File("Controller.xlsx"))){
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.getSheet("RunControl");
			
			System.out.println(sheet.getFirstRowNum());

			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				System.out.println("class path: "+ row.getCell(2));
				System.out.println("flag: "+ row.getCell(3));
				/*Cell cellKey = row.getCell(2);
				Cell cellValue = row.getCell(3);
				hm.put(cellKey.getStringCellValue(), cellValue.getStringCellValue());*/
			}
			workbook.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
