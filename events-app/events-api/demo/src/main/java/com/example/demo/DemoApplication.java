package com.example.demo;

import com.example.demo.entities.Event;
import com.example.demo.entities.User;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(EventRepository eventRepository, UserRepository userRepository) {
        return args -> {
            eventRepository.saveAll(List.of(
                    Event.builder().title("Friends Dinner").description("Dinner with friends").organizedBy("Alexandru Neacsa")
                            .startDate(LocalDate.of(2022,12,1)).endDate(LocalDate.of(2022,12,02)).location("Pitesti").numberOfSeats(8).build(),
                    Event.builder().title("Summer Party").description("Party with friends").organizedBy("Alexandru Neacsa")
                            .startDate(LocalDate.of(2023,7,10)).endDate(LocalDate.of(2023,7,16)).location("Pitsti").numberOfSeats(12).build(),
                    Event.builder().title("Christmas Party").description("Party with family members").organizedBy("Alexandru Neacsa")
                            .startDate(LocalDate.of(2023,12,15)).endDate(LocalDate.of(2023,12,17)).location("Pitesti").numberOfSeats(20).build()
            ));

            userRepository.saveAll(List.of(
                    User.builder().firstName("Alexandru").lastName("Neacsa").role("Admin").build(),
                    User.builder().firstName("Eduard").lastName("Neacsa").role("User").build(),
                    User.builder().firstName("Alin").lastName("Nita").role("User").build()
            ));
        };
    }
}
