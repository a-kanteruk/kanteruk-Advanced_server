package net.dunice.newsFeed.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class PublicUserView {
    private UUID id;
    private String avatar;
    private String email;
    private String name;
    private String role;
}
