package com.api.gateway.dtos;



import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonProperty;


public class PlayerModel implements Serializable {
  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("email")
  private String email;

  
  
}
