package com.sample.sse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 
 * @author fmoriguchi
 *
 */
public final class SseEmitterManager implements Emitable {

	private final Collection<SseEmitter> emmiters = new ArrayList<>();

	@Override
	public SseEmitter subscribe() {

		SseEmitter emmiter = new SseEmitter();
		
		emmiter.onCompletion(() -> emmiters.remove(emmiter));
		emmiter.onTimeout(() -> emmiters.remove(emmiter));

		emmiters.add(emmiter);

		return emmiter;
	}

	@Override
	public void send(Object message) {

		final Collection<SseEmitter> deadEmmiters = new ArrayList<>();

		emmiters.forEach(o -> send(o, message, deadEmmiters));

		emmiters.removeAll(deadEmmiters);
	}

	private void send(SseEmitter emmiter, Object message, Collection<SseEmitter> deadEmmiters) {

		try {
			emmiter.send(message);
			
		} catch (IOException e) {
			deadEmmiters.add(emmiter);
		}
	}
}
