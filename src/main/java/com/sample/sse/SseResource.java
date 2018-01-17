/**
 * 
 */
package com.sample.sse;

import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author fmoriguchi
 *
 */
@RestController
@RequestMapping("sse")
public class SseResource {

	private final Emitable channel = new SseEmitterManager();

	@GetMapping("subscribe")
	public SseEmitter subscribe() {

		return channel.subscribe();
	}

	@PostMapping("{message}")
	public void send(@PathVariable("message") String message) {

		channel.send(message);
	}

	/**
	 * Send random messages to channel
	 */
	@Scheduled(fixedRate = 5000)
	public void randonSendMessage() {

		UUID uuid = UUID.randomUUID();

		channel.send(uuid);
	}
}
