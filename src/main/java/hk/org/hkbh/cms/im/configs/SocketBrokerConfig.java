package hk.org.hkbh.cms.im.configs;

//import static com.baeldung.springsecuredsockets.Constants.SECURED_CHAT;
//import static com.baeldung.springsecuredsockets.Constants.SECURED_CHAT_HISTORY;
//import static com.baeldung.springsecuredsockets.Constants.SECURED_CHAT_ROOM;
//import static com.baeldung.springsecuredsockets.Constants.SECURED_CHAT_SPECIFIC_USER;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import hk.org.hkbh.cms.im.constants.Constants;
@Configuration
@EnableWebSocketMessageBroker
@ComponentScan("hk.org.hkbh.cms.im.controllers")
public class SocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(Constants.SECURED_CHAT_HISTORY, Constants.SECURED_CHAT_SPECIFIC_USER);
        config.setApplicationDestinationPrefixes("/spring-security-mvc-socket");
        config.setUserDestinationPrefix("/secured/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(Constants.SECURED_CHAT_ROOM).withSockJS();
        registry.addEndpoint(Constants.SECURED_CHAT).withSockJS();
    }
}

