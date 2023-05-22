package net.dunice.newsFeed.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "logs")
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String method;
    private String remoteUser;
    private String uri;
    private Integer responseStatusCode;
    private String contentType;
    @CreationTimestamp
    private LocalDateTime creationTime;

}
