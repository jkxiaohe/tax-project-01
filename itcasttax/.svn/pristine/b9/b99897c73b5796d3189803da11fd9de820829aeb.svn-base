package cn.itcast.nsfw.info.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.core.service.impl.BaseServiceImpl;
import cn.itcast.nsfw.info.dao.InfoDao;
import cn.itcast.nsfw.info.entity.Info;
import cn.itcast.nsfw.info.service.InfoService;

//将当前类加入spring容器中，在action类中能够注入值对象
@Service(value="infoService")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService {
	
	private InfoDao infoDao;
	@Resource
	public void setInfoDao(InfoDao infoDao) {
		super.setBaseDao(infoDao);
		this.infoDao = infoDao;
	}

}
