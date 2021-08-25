package commonmethods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HTMLReport {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DateFormat format=new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
		Date d=new Date();
		File file=new File("C:\\Users\\rajit\\Documents\\Report.html");
		FileWriter writer=new FileWriter(file);
		writer.write("<html><h1>Execution Summary: "+format.format(d)+"<h1></html>");
		writer.close();
	}

}
