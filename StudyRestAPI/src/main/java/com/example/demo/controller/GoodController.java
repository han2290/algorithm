package com.example.demo.controller;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.RestAPI;


@Controller
@RequestMapping("/")
public class GoodController {
	
	@RequestMapping("")
	public String index() {
		return "main";
	}
	
	@RequestMapping("/request")
	public ModelAndView requestData(
				@RequestParam("methodtype") String methodtype,
				@RequestParam("resturl") String url,
				@RequestParam(value="jsonstring", defaultValue="") String jsonstring
			){
		
		ModelAndView view = new ModelAndView();
		
		view.setViewName("main");
		
		view.addObject("preString", jsonstring.trim());
		view.addObject("preUrl", url);
		try {
			
			JSONObject json =
					RestAPI.readJsonFromUrl(
							url
							,jsonstring.trim().equals("")?null:jsonstring
							,methodtype
							,RestAPI.getCookie().equals("")?true:false);
			
			view.addObject("json", json.toString());
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			view.addObject("error", e.toString());
			e.printStackTrace();
		}
		
		return view;
		
	}
}
