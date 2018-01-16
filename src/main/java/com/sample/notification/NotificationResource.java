package com.sample.notification;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author fmoriguchi
 *
 */
@RestController
@RequestMapping("notifications")
public class NotificationResource {
	
	private SseEmitter notifier = new SseEmitter();
	
	@PostMapping("/{m}")
	@ResponseStatus(value=HttpStatus.OK)
	public void notify(@PathVariable("m") String message) throws IOException {
		
		notifier.send(new Notification(message));
	}
	
	@GetMapping
	public SseEmitter handle(){
		
	   return notifier;
	}

}
