package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPOI2Excel {

	@Test
	public void testWrite2Excel() throws IOException{
		//创建工作簿对象
		HSSFWorkbook workbook=new HSSFWorkbook();
		//创建工作表
		HSSFSheet sheet=workbook.createSheet("工作表1");
		//创建指定的单元行
		HSSFRow row=sheet.createRow(0);
		//创建指定的单元格
		HSSFCell cell=row.createCell(2);
		cell.setCellValue("hello world");
		//将创建的工作簿输出到指定的位置
		FileOutputStream output=new FileOutputStream("c:/users/dell/desktop/1.xls");
		workbook.write(output);
		output.close();
	}
	
	@Test
	public void testReadExcel() throws IOException{
		//读取指定的文件
		FileInputStream input =new FileInputStream("c:/users/dell/desktop/1.xls");
		//读取工作簿
		HSSFWorkbook workbook=new HSSFWorkbook(input);
		//读取工作表
		HSSFSheet sheet=workbook.getSheetAt(0);
		//读取工作行
		HSSFRow row=sheet.getRow(0);
		//读取单元格
		HSSFCell cell=row.getCell(2);
		//输出单元格的值
		System.out.println(cell.getStringCellValue());
	}
	
	
	@Test
	public void write2Excel07() throws IOException{
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet();
		XSSFRow row=sheet.createRow(0);
		XSSFCell cell=row.createCell(2);
		cell.setCellValue("hello world");
		FileOutputStream output=new FileOutputStream("c:/users/dell/desktop/2.xlsx");
		workbook.write(output);
		output.close();
	}
	
	@Test
	public void readExcel07() throws IOException{
		FileInputStream input =new FileInputStream("c:/users/dell/desktop/2.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(input);
		XSSFSheet sheet=workbook.getSheet("工作表1");
		XSSFRow row=sheet.getRow(0);
		XSSFCell cell=row.getCell(2);
		System.out.println(cell.getStringCellValue());
	}
    
	
	//自动判断excel文件的版本类型
	@Test
	public void readExcel() throws IOException{
		String filePath="c:\\users\\dell\\desktop\\2.xlsx";
		if(filePath.matches("^.+\\.(?i)((xls)|(xlsx))$")){
			boolean flag=filePath.matches("^.+\\.(?i)(xls)$");
			//创建输入流
			FileInputStream input=new FileInputStream(filePath);
			//读取工作簿
			Workbook workbook=flag ? new HSSFWorkbook(input) : new XSSFWorkbook(input);
			Sheet sheet = workbook.getSheetAt(0);
			Row row=sheet.getRow(0);
			Cell cell=row.getCell(2);
			System.out.println("单元格的内容是:"+cell.getStringCellValue());
		}
	}
	
	//测试合并单元格对象
	@Test
	public void mergedRange() throws IOException{
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet=workbook.createSheet();
		//创建合并单元格对象
		CellRangeAddress cellRange=new CellRangeAddress(1, 1, 1, 4);
		//将合并对象加入表格中
		sheet.addMergedRegion(cellRange);
		HSSFRow row = sheet.createRow(1);
		HSSFCell cell = row.createCell(1);
		cell.setCellValue("合并后的单元格");
		FileOutputStream output=new FileOutputStream("c:/users/dell/desktop/3.xls");
		workbook.write(output);
		output.close();
	}

	
	//测试工作簿的样式
	@Test
	public void testExcelStyle() throws IOException{
		//1.1创建工作簿
		HSSFWorkbook workbook=new HSSFWorkbook();
		
		//1.2创建单元格样式
		HSSFCellStyle style=workbook.createCellStyle();
		//设置单元格样式的相关属性
/*		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);*/
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		
		//1.3创建字体样式
		HSSFFont font=workbook.createFont();
		font.setBold(true);
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short)16);
		//将字体加载到样式中
		style.setFont(font);
		
		//1.4创建合并单元格
		CellRangeAddress cellRange=new CellRangeAddress(2, 3, 2, 4);
		
		//单元格的其它样式设置
//		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//		style.setFillBackgroundColor(HSSFColor.YELLOW.index);
		style.setFillForegroundColor(HSSFColor.RED.index);
		
		//2.创建工作表
		HSSFSheet sheet = workbook.createSheet();
		sheet.addMergedRegion(cellRange);
		
		//3.创建单元行
		HSSFRow row = sheet.createRow(2);
		
		//4.创建单元格
		HSSFCell cell = row.createCell(2);
		cell.setCellValue("hello world");
		//将样式加入到指定的单元格中
		cell.setCellStyle(style);
		
		FileOutputStream output = new FileOutputStream("c:/users/dell/desktop/4.xls");
		workbook.write(output);
		output.close();
	}
	
	
}
