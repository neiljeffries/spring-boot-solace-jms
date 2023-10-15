package cdmservice.services;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Profile("consumer")
@Component
public class JmsMessageConsumer {

	private static final Logger logger = LoggerFactory.getLogger(JmsMessageConsumer.class);

	@JmsListener(destination = "${solace.jms.consumerQueueJndiName}", containerFactory = "cFactory")
	public void processMsg(Message<?> msg) {
		StringBuffer msgAsStr = new StringBuffer("============= Received \nHeaders:");
		MessageHeaders hdrs = msg.getHeaders();
		msgAsStr.append("\nUUID: " + hdrs.getId());
		msgAsStr.append("\nTimestamp: " + hdrs.getTimestamp());
		Iterator<String> keyIter = hdrs.keySet().iterator();
		while (keyIter.hasNext()) {
			String key = keyIter.next();
			msgAsStr.append("\n" + key + ": " + hdrs.get(key));
		}
		msgAsStr.append("\nPayload: " + msg.getPayload());
		logger.info(msgAsStr.toString());
	}
}
