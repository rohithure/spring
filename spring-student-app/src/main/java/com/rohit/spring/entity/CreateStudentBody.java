package com.rohit.spring.entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateStudentBody {

  @NotNull
  @Size(min = 2, max = 10, message = "Invalid length")
  private String name;

  @NotNull
  private String email;
}
