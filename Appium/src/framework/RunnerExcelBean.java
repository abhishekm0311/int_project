package framework;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.xml.XmlClass;

public class RunnerExcelBean {

	public static List<XmlClass> createClassList(){
		HashMap<String, String> hm = getClassNameExcel();
		System.out.println(hm.size());

		Set set = hm.entrySet();

		//Get an iterator
		Iterator i = set.iterator();
		List<XmlClass> classList = new ArrayList<XmlClass>();

		while(i.hasNext()){
			Map.Entry me = (Map.Entry)i.next();

			if(me.getValue().equals("Yes")&& me!=null)
			{
//				System.out.println(me.getKey());
				classList.add(new XmlClass(me.getKey().toString()));
			}
		}
		System.out.println(classList);
		return classList;
	}
	
	private static HashMap<String, String> getClassNameExcel()
	{
		HashMap<String, String> hm = new HashMap<String, String>();
		
		try(FileInputStream fis = new FileInputStream(new File("src/resource/Controller.xlsm"))){
			
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("RunControl");
			
			System.out.println(sheet.getLastRowNum());
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				Cell cellKey = row.getCell(3);
				Cell cellValue = row.getCell(4);
				
				System.out.print(cellKey.getStringCellValue() + ": ");
				System.out.println(cellValue.getStringCellValue());
				
				hm.put(cellKey.getStringCellValue(), cellValue.getStringCellValue());
			}
			workbook.close();
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return hm;
	}

	/*public static void main(String[] args) {
		createClassList();
	}*/
}