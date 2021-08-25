package commonmethods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	public static void main(String[] args) throws IOException {

		File f=new File("C:\\Users\\rajit\\Documents\\raji.xlsx");
		FileInputStream fis=new FileInputStream(f);
		XSSFWorkbook wbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=wbook.getSheet("Data");
		int rowcount=sheet.getLastRowNum()+1;
		int colcount=sheet.getRow(0).getLastCellNum();
		String header[]=new String[colcount];

		for(int j=0;j<colcount;j++)
		{
			header[j]=sheet.getRow(0).getCell(j).getStringCellValue();
		}

		List<Map<Object,Object>> al=new ArrayList();

		for(int i=1;i<rowcount;i++)
		{

			String flag=sheet.getRow(i).getCell(2).getStringCellValue();	

			if(flag.equalsIgnoreCase("Y")||flag.equalsIgnoreCase("Yes"))
			{
				Map<Object,Object> map=new HashMap<Object,Object>();

				for(int j=0;j<colcount-1;j++)
				{
					XSSFCell cell=sheet.getRow(i).getCell(j, Row.CREATE_NULL_AS_BLANK);
					int value=cell.getCellType();
					
					if(value==1)
					{
						map.put(header[j], cell.getStringCellValue());
					}
					else if(value==0)
					{
						map.put(header[j], cell.getNumericCellValue());
					}
					else if(value==3)
					{
						map.put(header[j], "");
					}
				}

				al.add(map);
			}

		}
		System.out.println(al);

		 }

	/*File f=new File("C:\\Users\\rajit\\Documents\\raji.txt");

	BufferedReader br=new BufferedReader(new FileReader(f));
	String str=br.readLine();
	ArrayList<String> al=new ArrayList<String>();
	int linenum=0;
	while(str!=null)
	{
		//String[] str1=str.split(" ",2);
		//writeExcel(str,linenum);
		al.add(str);
		//linenum++;
		//System.out.println(str);
		str=br.readLine();
		
	}
	writeExcel(al);


}

public static void writeExcel(ArrayList<String> al) throws IOException
{
	
	File f1=new File("C:\\Users\\rajit\\Documents\\raji1.xlsx");
	FileOutputStream fis=new FileOutputStream(f1);
	XSSFWorkbook wbook=new XSSFWorkbook();
	XSSFSheet sheet=wbook.createSheet("Data");
	//Map<String,String> hm=new LinkedHashMap<String,String>();
	for(int i=0;i<al.size();i++)
	{
	String[] splitline=al.get(i).split(" ", 2);			
	XSSFRow row=sheet.createRow(i);
	for(int j=0;j<splitline.length;j++)
	{
	row.createCell(j).setCellValue(splitline[j]);
	}
	}
	wbook.write(fis);
	fis.close();
	
}

*/



}


