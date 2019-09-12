package com.example.assignment.dto;

import lombok.Data;

@Data
public class PostQueryDTO {
private String search;
private Integer page;
private Integer size;
}
