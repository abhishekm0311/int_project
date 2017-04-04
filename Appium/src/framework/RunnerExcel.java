package framework;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class RunnerExcel {
	public static void main(String[] args) {
		XmlSuite suite = new XmlSuite();
		suite.setName("TmpSuite");
		
		XmlTest test = new XmlTest(suite);
		test.setName("TmpTest");
		List<XmlClass> classes = RunnerExcelBean.createClassList();
		
		test.setXmlClasses(classes);
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		
		TestNG testng = new TestNG();
		ExecutionListener el = new ExecutionListener();
		testng.setXmlSuites(suites);
		testng.addListener(el);
		testng.run();
		
		/*if(ExecutionListener.statusFlag == false)
		{
			System.out.println("False");
			System.exit(1);
		}
		else
		{
			System.out.println("True");
			System.exit(0);
		}*/
	}
}
