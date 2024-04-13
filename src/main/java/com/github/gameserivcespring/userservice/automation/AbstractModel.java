package com.github.gameserivcespring.userservice.automation;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @CreatedDate
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;
}
