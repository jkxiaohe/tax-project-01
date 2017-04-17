package cn.itcast.core.utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.itcast.nsfw.user.entity.User;

public class ExcelUtil {

	//将用户的文件输出
	public static void exportUserExcel(List<User> userList,ServletOutputStream outputStream){
		try {
			//1.创建工作簿
			HSSFWorkbook workbook=new HSSFWorkbook();
			//1.1创建合并单元格对象
			CellRangeAddress cellRange=new CellRangeAddress(0, 0, 0, 5);
			//1.2创建头标题的样式
			HSSFCellStyle style1=createCellStyle(workbook,(short)16);
			//1.3创建列标题的样式
			HSSFCellStyle style2=createCellStyle(workbook,(short)12);
			
			//2.创建工作表
			HSSFSheet sheet=workbook.createSheet("用户列表");
			//加载合并单元格对象
			sheet.addMergedRegion(cellRange);
			sheet.setDefaultColumnWidth(20);
			
			//3.创建单元行
			HSSFRow row1=sheet.createRow(0);
			//4.创建单元格
			HSSFCell cell1=row1.createCell(0);
			//加载单元格样式
			cell1.setCellStyle(style1);
			cell1.setCellValue("用户列表");
			
			
			
			//创建列标题
			String[] titles={"用户名","账号","部门","性别","手机号","邮箱","头像"};
			HSSFRow row2=sheet.createRow(1);
			for(int i=0;i<titles.length;i++){
				HSSFCell cell2 = row2.createCell(i);
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			
			//将用户的数据写入到excel文件中
			if(userList!=null){
				for(int i=0;i<userList.size();++i){
					HSSFRow row3=sheet.createRow(2+i);
					User user = userList.get(i);
					
					HSSFCell cell11 = row3.createCell(0);
					cell11.setCellValue(user.getName());
					HSSFCell cell12 = row3.createCell(1);
					cell12.setCellValue(user.getAccount());
					HSSFCell cell13 = row3.createCell(2);
					cell13.setCellValue(user.getDept());
					HSSFCell cell14 = row3.createCell(3);
					cell14.setCellValue(user.getGender() ? "男" : "女");
					HSSFCell cell15 = row3.createCell(4);
					cell15.setCellValue(user.getMobile());
					HSSFCell cell16 = row3.createCell(5);
					cell16.setCellValue(user.getEmail());
					HSSFCell cell17 = row3.createCell(6);
					cell17.setCellValue(user.getHeadImg());
				}
			}
			//输出对象
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//创建单元格的样式（将公共代码抽取出来，用户调用的时候只需要传入少量的值即可）
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook,short size){
		HSSFCellStyle style = workbook.createCellStyle();
		//创建字体样式
		HSSFFont font=workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints(size);
		//将字体加载到样式中
		style.setFont(font);
		//创建样式的对齐方式
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		return style;
	}
	
	
	
}
