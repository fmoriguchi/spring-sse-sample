package com.sample.sse;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 
 * @author fmoriguchi
 *
 */
public interface Emitable {

	SseEmitter subscribe();

	void send(Object message);
}