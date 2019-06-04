package io.pivotal.pal.tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PalTrackerApplication {


    public static void main(String [] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }

    @Bean
    public ITimeEntryRepository inMemoryTimeEntryRepository() {
        return new InMemoryTimeEntryRepository();
    }

    @Bean
    public ObjectMapper isoDateObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
