package com.cmpe275.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cmpe275.project.mapper.ClearDataResponse;
import com.cmpe275.project.model.ClearData;
import com.cmpe275.project.services.UserService;

@RestController
@RequestMapping("/clear")
public class ClearDataController {

	@Autowired
	UserService userService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>  clearData(@RequestBody ClearData clearData)
    {
       ClearDataResponse response  = new ClearDataResponse();
       StringBuilder responseString = new StringBuilder();
       ResponseEntity res = null;
       HttpStatus httpStatus = null;
       // Delete user Details Data First
       if(true){   
    	   userService.clearUserTable();  
    	   responseString.append("User Data is Cleaned ");
    	   httpStatus = HttpStatus.OK;
       }
      response.setMessage(responseString.toString()); 
      return new ResponseEntity(response, httpStatus);
    	
    }


	
	
}
