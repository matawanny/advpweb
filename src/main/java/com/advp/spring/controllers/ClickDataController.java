package com.advp.spring.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.advp.spring.entities.ClickData;
import com.advp.spring.services.ClickDataService;

@Controller
public class ClickDataController {
	
	private ClickDataService clickDataService;
	
	@Autowired
	public void setClickDatasService(ClickDataService clickDataService) {
		this.clickDataService = clickDataService;
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String id) {
		System.out.println("Id is: " + id);
		return "home";
	}
	
	/*
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex) {
		return "error";
	*/

	@RequestMapping("/clickdatas")
	public String showClickDatas(Model model) {
		
		//clickDataService.throwTestException();
		
		List<ClickData> clickdatas = clickDataService.getCurrent();
		
		model.addAttribute("clickdatas", clickdatas);
		
		return "clickdatas";
	}
	
	@RequestMapping("/createclickdata")
	public String createClickData(Model model) {
	
		model.addAttribute("clickData", new ClickData());
		
		return "createclickdata";
	}
	
	@RequestMapping(value="/docreate", method=RequestMethod.POST)
	public String doCreate(Model model, @Valid ClickData clickdata, BindingResult result) {
		
		if(result.hasErrors()) {
			return "createclickdata";
		}
		
		clickDataService.create(clickdata);
		
		return "clickdatacreated";
	}
}