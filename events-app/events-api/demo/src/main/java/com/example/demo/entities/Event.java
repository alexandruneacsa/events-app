package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "Events")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EventIdentifier")
    private String eventIdentifier;

    @NotBlank(message = "Title is required")
    @Column(name = "Title", length = 50)
    @Size(min=1, max=50, message = "Please complete the title")
    private String title;

    @NotBlank(message = "Event description is required")
    @Column(name = "Description", length = 50)
    @Size(min=1, max=50, message = "Please complete the title")
    private String description;

    @Column(name = "Organizator")
    private String organizedBy;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DateCreated")
    private LocalDate dateCreated;

    @Column(name = "StartDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "EndDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "DateUpdated")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate dateUpdated;

    @NotBlank(message = "Event location is required")
    @Column(name = "Location")
    private String location;

    @NotNull(message = "Positive number value is required")
    @Min(value = 5, message = "Allocated seats must be greater than 5")
    @Column(name = "NumberOfseats")
    private Integer numberOfSeats;

    @PrePersist
    protected void onCreate() {
        this.dateCreated = LocalDate.now();
        this.eventIdentifier = generateEventIdentifier();
    }

    private String generateEventIdentifier() {
        return this.getTitle().toUpperCase().substring(0, 3)+LocalDate.now()
                .getDayOfMonth()+LocalDate.now()
                .getMonth().toString().substring(0,3)+LocalDate.now().getYear()%2000;
    }

    @PreUpdate
    protected void onUpdate() {
        this.dateUpdated = LocalDate.now();
    }

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "EventsUsers",
            joinColumns = {@JoinColumn(name = "EventId")},
            inverseJoinColumns = {@JoinColumn(name = "UserId")}
    )
    private Set<User> participants = new HashSet<>();

    public void addUserOnEvent(User user) {
        this.participants.add(user);
    }
}
