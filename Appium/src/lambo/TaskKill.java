package lambo;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

public class TaskKill {
	
	/*private int appium_port = 4724;
	private int bootstrap_port = 5724;
	private int chromedriver_port = 6724;*/
	
	static String appium_path = System.getenv("appium_path");
	static String appium_ip = System.getenv("appium_ip");
	static String appium_port = System.getenv("appium_port");
	static String boot_port = System.getenv("bootstrap_port");
	static String chromedriver_port = System.getenv("chromedriver_port");
	
	public static void adb() throws ExecuteException, IOException{
		CommandLine verify = new CommandLine("taskkill");
		verify.addArgument("/F");
		verify.addArgument("/IM");
		verify.addArgument("adb.exe");
		DefaultExecuteResultHandler taskkill = new DefaultExecuteResultHandler();
		DefaultExecutor killer = new DefaultExecutor();
		killer.setExitValue(1);
		killer.execute(verify, taskkill);
	}
	
	public static void stopappium() throws ExecuteException, IOException, InterruptedException{
		Process p = Runtime.getRuntime().exec("cmd /c echo off & FOR /F \"usebackq tokens=5\" %a in (`netstat -nao ^| findstr /R /C:\""+4723+"\"`) do (FOR /F \"usebackq\" %b in (`TASKLIST /FI \"PID eq %a\" ^| findstr /I node.exe`) do taskkill /F /PID %a)");
		Thread.sleep(2000);
		p.destroy();
	}
	
	public static void javaw() throws ExecuteException, IOException{
		CommandLine verify = new CommandLine("taskkill");
		verify.addArgument("/F");
		verify.addArgument("/IM");
		verify.addArgument("java.exe");
		DefaultExecuteResultHandler taskkill = new DefaultExecuteResultHandler();
		DefaultExecutor killer = new DefaultExecutor();
		killer.setExitValue(1);
		killer.execute(verify, taskkill);
	}
	
	public static void startappium() throws ExecuteException, IOException{
	
		Runtime.getRuntime().exec("appium");
		
//		CommandLine command = new CommandLine(appium_path+"/node.exe");
//		CommandLine command = new CommandLine("C:/Users/q2435/AppData/Roaming/npm/");
//		command.addArgument(appium_path+"/node_modules/appium/bin/Appium.js", false);
//		command.addArgument("C:/Program Files (x86)/Appium/node_modules/appium/bin/Appium.js", false);
//		command.addArgument("--address", false);
//		command.addArgument("http://127.0.0.1");
//		command.addArgument("--port", false);
//		command.addArgument("4723");
//		command.addArgument("--bootstrap-port", false);
//		command.addArgument("5723");
//		command.addArgument("--chromedriver-port", false);
//		command.addArgument("6723");
		/*DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		executor.execute(command, resultHandler);*/
	
	}
	
	public static void run() throws ExecuteException, IOException {
	
		CommandLine verify = new CommandLine("npm");
		verify.addArgument("appium");
		DefaultExecuteResultHandler taskkill = new DefaultExecuteResultHandler();
		DefaultExecutor killer = new DefaultExecutor();
		killer.setExitValue(1);
		killer.execute(verify, taskkill);
	
	}
	
	public static void clearTemp() throws ExecuteException, IOException{
		CommandLine command = new CommandLine("DEL /F /S /Q %TEMP%");
		DefaultExecuteResultHandler taskkill = new DefaultExecuteResultHandler();
		DefaultExecutor killer = new DefaultExecutor();
		killer.setExitValue(1);
		killer.execute(command, taskkill);
	}
}