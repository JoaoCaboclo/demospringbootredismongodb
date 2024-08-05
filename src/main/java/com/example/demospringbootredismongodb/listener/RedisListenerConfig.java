package com.example.demospringbootredismongodb.listener;

import com.example.demospringbootredismongodb.entity.Person;
import com.example.demospringbootredismongodb.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
@NoArgsConstructor
@AllArgsConstructor
public class RedisListenerConfig {

    private PersonRepository personRepository;

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new ChannelTopic("personCreated"));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter(new RedisMessageSubscriber());
    }

    public class RedisMessageSubscriber {

        public void handleMessage(Person person) {
            // Aqui você pode definir a lógica para atualizar o MongoDB com a entidade recebida
            personRepository.save(person);
        }
    }
}