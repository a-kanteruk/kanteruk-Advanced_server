package net.dunice.newsFeed.response;

import lombok.Data;

import java.util.UUID;

@Data
public class PutUserDtoResponse {
    private UUID id;
    private String avatar;
    private String email;
    private String name;
    private String role;
}
