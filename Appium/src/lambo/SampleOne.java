package lambo;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class SampleOne {
		
	public static void main(String[] args) throws Exception {
		
//		Runtime.getRuntime().exec("appium");
		TaskKill.startappium();
		Thread.sleep(20000);
		
		AndroidDriver driver;
		String url = "http://127.0.0.1:4723/wd/hub";
		
//		Thread.sleep(20000);
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("udid", "emulator-5554");
		capabilities.setCapability("platformVersion", "5.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.ubyapp");
		capabilities.setCapability("appActivity","com.ubyapp.MainActivity");
        driver = new AndroidDriver(new URL(url), capabilities);
        
        driver.get("http://www.google.com");
		
		System.out.println("Testing...!!!");
        
	}
}
