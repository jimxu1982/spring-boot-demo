
package com.jimxu.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jimxu.boot.kafka.KafkaMsgProducer;

/**
 * @author xuzhijing
 *
 */

@RestController
public class KafkaController {
	
	
	@Autowired  
	private KafkaMsgProducer kafkaMsgProducer;
	
	@RequestMapping(value = "/api/send", method = RequestMethod.GET)
    public String findOneCity(@RequestParam(value = "message", required = true) String message) {
        kafkaMsgProducer.send(message);
        
        return "send success！";
    }
}
