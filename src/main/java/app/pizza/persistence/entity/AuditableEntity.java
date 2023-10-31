package app.pizza.persistence.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity {
    
    @Column(name="create_date")
    private LocalDateTime createDate;

    @Column(name="modified_date")
    @LastModifiedDate
    
    private LocalDateTime modifiedDate;
}
