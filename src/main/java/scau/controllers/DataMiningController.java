package scau.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import scau.services.DataMiningService;

@Controller
public class DataMiningController {
	@Autowired
	DataMiningService dataMiningService;

	@RequestMapping("/CBA")
	public @ResponseBody String CBA(HttpServletRequest request, HttpServletResponse response) {
		return dataMiningService.CBA();
	}
}
