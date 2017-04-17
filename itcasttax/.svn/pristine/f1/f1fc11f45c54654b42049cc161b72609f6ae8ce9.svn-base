package cn.itcast.nsfw.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.stereotype.Service;

import cn.itcast.core.service.impl.BaseServiceImpl;
import cn.itcast.core.utils.ExcelUtil;
import cn.itcast.nsfw.role.entity.Role;
import cn.itcast.nsfw.user.dao.UserDao;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;
import cn.itcast.nsfw.user.entity.UserRoleId;
import cn.itcast.nsfw.user.service.UserService;

//加入springioc容器的注解
@Service(value="userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	//注入持久层的类对象
	
	private UserDao userDao;
	@Resource
    public void setUserDao(UserDao userDao) {
    	super.setBaseDao(userDao);
		this.userDao = userDao;
	}


	@Override
	public void exportExcel(List<User> userList, ServletOutputStream output) {
		//使用工具类直接导出用户的文件
		ExcelUtil.exportUserExcel(userList, output);
	}

	@Override
	public void importExcel(File userExcel, String fileName) {
		try {
			//创建文件读入流
			FileInputStream input=new FileInputStream(userExcel);
			//创建标识符
			boolean flag=fileName.matches("^.+\\.(?i)(xls)$");
			Workbook workbook= flag ? new HSSFWorkbook(input) : new XSSFWorkbook(input);
			//读取工作表
			Sheet sheet=workbook.getSheetAt(0);
			//判断该文件中是否有用户数据
		    if(sheet.getPhysicalNumberOfRows()>2){
				User user=null;
				for(int i=2;i<sheet.getPhysicalNumberOfRows();++i){
					user = new User();
					Row row = sheet.getRow(i);
					//"用户名","账号","部门","性别","手机号","邮箱"
					Cell cell0 = row.getCell(0);
					user.setName(cell0.getStringCellValue());
					Cell cell1 = row.getCell(1);
					user.setAccount(cell1.getStringCellValue());
					Cell cell2 = row.getCell(2);
					user.setDept(cell2.getStringCellValue());
					Cell cell3 = row.getCell(3);
					user.setGender(cell3.getStringCellValue().equals("男"));
					
					Cell cell4 = row.getCell(4);
					user.setMobile(cell4.getStringCellValue());
					Cell cell5 = row.getCell(5);
					user.setEmail(cell5.getStringCellValue());
					Cell cell6=row.getCell(6);
					user.setHeadImg(cell6.getStringCellValue());
					
					//设置用户默认的密码和状态的有效性
					user.setPassword("123456");
					user.setState(User.USER_STATE_VALID);
					//将当前用户保存到数据库中
					userDao.save(user);
				}
			}
			workbook.close();
			input.close();
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	@Test
	public void testImport(){
		File file=new File("C:\\Users\\dell\\Downloads\\用户列表.xls");
		String fileName="用户列表.xls";
/*		System.out.println(file.getName());
		System.out.println(file.exists());*/
		importExcel(file,fileName);
	}

	@Override
	public List<User> findUserByAccountAndId(String id, String account) {
		return userDao.findUserByAccountAndId(id,account);
	}

	@Override
	public void saveUserAndRole(User user, String... roleIds) {
		//用户和角色是分开保存的
		//1.保存用户
		save(user);
		//2.保存用户对应的角色
		if(roleIds!=null){
			//对数组中的每个角色进行迭代保存
			for(String roleId : roleIds){
				//保存用户<-->角色，用户通过 UserRoleId表查出roleid,再通过 RolePriviligeID表 查出角色对应拥有的权限
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId), user.getId())));
			}
		}
	}

	@Override
	public void updateUserAndRole(User user, String... roleIds) {
		//1.首先删除该用户原来的角色列表
		userDao.deleteUserRoleByUserId(user.getId());
		//2.更新用户
		userDao.update(user);
		//3.保存新的用户角色列表
		if(roleIds!=null){
			for(String roleId : roleIds){
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId), user.getId())));
			}
		}
	}

	@Override
	public void deleteUserRoleByUserId(String id) {
		userDao.deleteUserRoleByUserId(id);
	}

	@Override
	public List<UserRole> getUserRolesByUserId(String id) {
		return userDao.getUserRolesByUserId(id);
	}

	@Override
	public List<User> findUsersByAccountAndPass(String account, String password) {
		return userDao.findUsersByAccountAndPass(account, password);
	}


	
}
