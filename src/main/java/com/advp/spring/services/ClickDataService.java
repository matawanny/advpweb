package com.advp.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advp.spring.dao.ClickDataDao;
import com.advp.spring.entities.ClickData;

@Service("clickDataService")
public class ClickDataService {
	
	private ClickDataDao clickDataDao;
	
	@Autowired
	public void setclickDataDao(ClickDataDao clickDataDao) {
		this.clickDataDao = clickDataDao;
	}

	public List<ClickData> getCurrent() {
		return clickDataDao.getClickDatas();
	}

	public void create(ClickData clickData) {
		clickDataDao.create(clickData);
	}

	public void throwTestException() {
		clickDataDao.getClickData(99999);
	}
}
