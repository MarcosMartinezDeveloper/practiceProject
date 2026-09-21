package com.marcosmartinezdeveloper.practiceProject.persistence.entities.Utils;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SuperBuilder;
import lombok.ToString;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@ToString
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class AuditableEntity extends AuditableDates{

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(nullable = false)
    private String updatedBy;

}
