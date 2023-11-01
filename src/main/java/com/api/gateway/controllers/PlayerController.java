package com.api.gateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.gateway.dtos.RequestCreatePlayerDto;
import com.api.gateway.dtos.RequestDeletePlayerDto;
import com.api.gateway.dtos.RequestShowPlayerDto;
import com.api.gateway.dtos.RequestUpdatePlayerDto;
import com.api.gateway.producers.PlayerProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


@RestController
public class PlayerController {

  @Autowired
  PlayerProducer playerProducer;


  @PostMapping("/")
  public ResponseEntity<Object> createPlayer(
    @RequestBody RequestCreatePlayerDto body
  ){
    playerProducer.createPlayer(body);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @GetMapping("/")
  public ResponseEntity<Object> listPlayer() throws JsonMappingException, JsonProcessingException{
    var response = playerProducer.listPlayer();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
  @GetMapping("/{id}")
  public ResponseEntity<Object> showPlayer(
    @PathVariable("id") String player_id
  ){
    
    playerProducer.showPlayer(new RequestShowPlayerDto(player_id));
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }
  @PutMapping("/{id}")
  public ResponseEntity<Object> updatePlayer(
    @PathVariable("id") String player_id,
    @RequestParam(name = "name") String name
  ){
    playerProducer.updatePlayer(new RequestUpdatePlayerDto(player_id, name));
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deletePlayer(
    @PathVariable("id") String player_id
  ){
    playerProducer.deletePlayer(new RequestDeletePlayerDto(player_id));
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
