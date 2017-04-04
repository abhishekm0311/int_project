package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import org.apache.commons.exec.ExecuteException;
import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

//import CardCenter.SC018_ChangePIN;

public class ExecutionListener extends TestListenerAdapter {

	private String sheetName = null;
//	public static boolean statusFlag = true;
	
	@Override
	public void onTestStart(ITestResult tr) {
		log("Test Started....");
	}
	
	@Override
	public void onTestSuccess(ITestResult tr) {
		
		log("Test '" + tr.getName() + "' PASSED");
		
		String rsSheetName = tr.getTestClass().getName();
		sheetName = rsSheetName.substring(0, rsSheetName.indexOf("."));
		
		System.out.println("#=====Success=====#");
		System.out.println(rsSheetName.substring(0, rsSheetName.indexOf(".")));
		
//		ExcelBean.updateResultsExcel(tr.getName(), "Pass");
		
		ExcelBean.updateResultsExcel(tr.getName(), "Pass", rsSheetName.substring(0, rsSheetName.indexOf(".")));
		
		System.out.println("#====#===#====#====#+==#====#====#====#====#");
		System.out.println(getTestClassField(tr.getInstance()));
//		ExcelBean.updateEvidenceExcel(tr.getName(), getTestClassField(tr.getInstance()));
		
		System.out.println("#====#===#====#====#+==#====#====#====#====#");
		System.out.println(getTestClassField(tr.getInstance()));
		ExcelBean.updateEvidenceExcel(tr.getName(), getTestClassField(tr.getInstance()), rsSheetName.substring(0, rsSheetName.indexOf(".")));
		
//		ExcelBean.updateEvidenceExcel(tr.getName(), getTestClassField(tr.get));
		// This will print the class name in which the method is present
		log(tr.getTestClass());

		// This will print the priority of the method.
		// If the priority is not defined it will print the default priority as
		// 'o'

		log("Priority of this method is " + tr.getMethod().getPriority());

//		System.out.println(".....");
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		
		log("Test '" + tr.getName() + "' FAILED");
		
//		ExcelBean.updateResultsExcel(tr.getName(), "Fail");
		
		String rsSheetName = tr.getTestClass().getName();
		sheetName = rsSheetName.substring(0, rsSheetName.indexOf("."));
		System.out.println("#=====Failure=======#");
		System.out.println(rsSheetName);
		System.out.println(rsSheetName.substring(0, rsSheetName.indexOf(".")));
		
//		statusFlag = false;
		
		ExcelBean.updateResultsExcel(tr.getName(), "Fail", rsSheetName.substring(0, rsSheetName.indexOf(".")));
		
		log("Priority of this method is " + tr.getMethod().getPriority());
		System.out.println(".....");
	}
	
	@Override
	public void onTestSkipped(ITestResult tr) {
		log("Test '" + tr.getName() + "' SKIPPED");
//		ExcelBean.updateResultsExcel(tr.getName(), "Skipped");

		String rsSheetName = tr.getTestClass().getName();
		sheetName = rsSheetName.substring(0, rsSheetName.indexOf("."));
		
		System.out.println("#=====#=======#");
		System.out.println(rsSheetName.substring(0, rsSheetName.indexOf(".")));
		
		ExcelBean.updateResultsExcel(tr.getName(), "Skipped", rsSheetName.substring(0, rsSheetName.indexOf(".")));
		
		System.out.println(".....");
	}
	
	public void onFinish(ITestContext testContext){
		
		/*String appCode = testContext.getClass().getSimpleName();
		String deviceName = ExcelBean.getDeviceName(appCode);
		try {
			ExcelBean.CopyExcel(deviceName, appCode);
			Class<?> c = testContext.getClass();
			Field clientField = c.getDeclaredField("client");
			clientField.setAccessible(true);
			Object thisHelperInstance = clientField.get(testContext.getClass());
			Client client = (Client) thisHelperInstance;
			client.applicationClose("com.btpn.jenius.dc.sit.inhouse");
			client.generateReport(false);
			client.releaseClient();
		} catch (IOException|NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
//		ExcelBean.clearExcelColumn(testContext.getName());
		
		/*System.out.println("#=====#-On Finish=======#");
		System.out.println(sheetName);*/
//		System.out.println(rsSheetName.substring(0, rsSheetName.indexOf(".")));
		
		ExcelBean.clearExcelColumn(testContext.getName(), sheetName);
		
		/*try {
			TaskKill.javaw();
		} catch (ExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	private void log(String methodName) {
//		System.out.println(methodName);
	}

	private void log(IClass testClass) {
//		System.out.println(testClass);
	}
	private String getTestClassField(Object testClass){
		Class<?> c = testClass.getClass();
		String value = null;
		try {
			//get the field "h" declared in the test-class.
            //getDeclaredField() works for protected members.
			Field assertValueField = c.getDeclaredField("assertValue");
			assertValueField.setAccessible(true);
//			System.out.println("$$$$$$   "+assertValueField.getName()+ "    $$$$$$");
			
//			String name = assertValueField.getName();
            Object thisHelperInstance = assertValueField.get(testClass);
//          System.out.print(name + ":" + thisHelperInstance.toString() + "\n");
			
			/*Class<?> targetType = assertValueField.getType();
			Object objectValue = targetType.newInstance();*/
			value = thisHelperInstance.toString();
		} catch (NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e) {
			e.printStackTrace();
		}
		return value;
	}
}

