package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
	
	private static String fileName;

	public static void createFile() {
	    try {
	      File myObj = new File("logs\\"+fileName+".csv");
	      if (myObj.createNewFile()) {
	        System.out.println("File created: " + myObj.getName());
	        writeToFile("timestamp", "sensor", "value"); //write .csv column headers
	      } else {
	        System.out.println("File already exists.");
	      }
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}
	
	public static void writeToFile(String timestamp, String sensorName, String value) {
	    try {
	      FileWriter myWriter = new FileWriter("logs\\"+fileName+".csv", true); //the true will append the new data instead of overwriting
	      myWriter.write(timestamp + "," + sensorName + "," + value + "\n");
	      myWriter.close();
	      System.out.println("Logged: " + timestamp + ", " + sensorName + ", " + value);
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}
	
	
	//Accessors and Mutators
	public static void setFileName(String fileName) {
		Log.fileName = fileName;
	}
}
