package cdmservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Profile("producer")
@Service
public class JmsMessageProducer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(JmsMessageProducer.class);

    @Autowired
    private JmsTemplate producerJmsTemplate;

    @Value("${solace.jms.producerQueueJndiName}")
    private String queueJndiName;

    public void run(String... strings) throws Exception {
        String msg = "Hello World";
        logger.info("============= Sending " + msg);
        this.producerJmsTemplate.convertAndSend(queueJndiName, msg);
    }
}
