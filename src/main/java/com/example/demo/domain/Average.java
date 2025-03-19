package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "average")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Average {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String endpoint;
    private List<Long> params;
    private String response;


}
