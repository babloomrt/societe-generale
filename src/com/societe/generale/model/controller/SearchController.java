package com.societe.generale.model.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.societe.generale.model.formbean.MemberDetailBean;
import com.societe.generale.service.serviceIF.MemberDetailServiceIF;


@Controller
public class SearchController {
	private Logger log = Logger.getLogger(SearchController.class.getName());
	private String CLASS_NAME = "SearchController";
	@Autowired
	private MemberDetailServiceIF memberDetailServiceIF;
	@RequestMapping(value = "/searchMemberDetails.societegenerale", method = RequestMethod.POST, params={"searchText"})
	public @ResponseBody String searchMemberDetails(@RequestParam("searchText") String searchText)
	{
		String METHOD_NAME = "searchMemberDetails";
		log.entering(CLASS_NAME, METHOD_NAME);
		log.log(Level.INFO,"Search Text for member detail table is :"+searchText);
		String wrapperString = "";
		ObjectMapper objectMapper = new ObjectMapper();
		try{
			List<MemberDetailBean> adoptedTgBeanList = memberDetailServiceIF.searchDetail(searchText);
			if(adoptedTgBeanList.size()>0)
				wrapperString = objectMapper.writeValueAsString(adoptedTgBeanList);
			else{
				Map<String,String> errorMap = new HashMap<String, String>();
				errorMap.put("NORESULTFOUND", "No Result Found. Please Search Again.");
				wrapperString = objectMapper.writeValueAsString(errorMap);
			}
		}catch(Exception exp){
		   log.log(Level.SEVERE,"Exception in retrieving Tg Details and original exception message is :"+exp.getMessage(),exp);
		   wrapperString = createAjaxErrorString("Error in retrieving Member Details",objectMapper);
	    }
		log.exiting(CLASS_NAME, METHOD_NAME);
		return wrapperString;
	}
	
	private String createAjaxErrorString(String errorMsg, ObjectMapper objectMapper){
		String stringWrapper = "";
		try{
			Map<String,String> errorMap = new HashMap<String, String>();
			errorMap.put("EXCEPTIONMSG", errorMsg);
			stringWrapper = objectMapper.writeValueAsString(errorMap);
		} catch (JsonGenerationException e) {
			log.log(Level.SEVERE,"Exception in retrieving Member Details and original exception message is :"+e.getMessage(),e);
		} catch (JsonMappingException e) {
			log.log(Level.SEVERE,"Exception in retrieving Member Details and original exception message is :"+e.getMessage(),e);
		} catch (IOException e) {
			log.log(Level.SEVERE,"Exception in retrieving Member Details and original exception message is :"+e.getMessage(),e);	
		}
		return stringWrapper;
	}
}
