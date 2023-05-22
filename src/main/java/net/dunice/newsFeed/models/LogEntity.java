package net.dunice.newsFeed.models;


import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

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
