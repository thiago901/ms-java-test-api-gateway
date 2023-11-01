package com.api.gateway.producers;



import org.springframework.amqp.rabbit.core.RabbitTemplate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import com.api.gateway.dtos.PlayerModel;
import com.api.gateway.dtos.RequestCreatePlayerDto;
import com.api.gateway.dtos.RequestDeletePlayerDto;
import com.api.gateway.dtos.RequestShowPlayerDto;
import com.api.gateway.dtos.RequestUpdatePlayerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PlayerProducer {
  @Autowired
  RabbitTemplate rabbitTemplate;

  @Value("${brocker.queue.player.create.name}")
  private String createPlayerQueue;
  
  @Value("${brocker.queue.player.list.name}")
  private String listPlayerQueue;
  
  @Value("${brocker.queue.player.update.name}")
  private String updatePlayerQueue;
  
  @Value("${brocker.queue.player.delete.name}")
  private String deletePlayerQueue;
  
  @Value("${brocker.queue.player.show.name}")
  private String showPlayerQueue;

  public void createPlayer(RequestCreatePlayerDto payload){
    rabbitTemplate.convertAndSend("",this.createPlayerQueue,payload);

  }
  public PlayerModel[] listPlayer(
    
  ) throws JsonMappingException, JsonProcessingException{
    // var result = 
    var responseMessage =  (String) rabbitTemplate.convertSendAndReceive("",this.listPlayerQueue,"");
    
    ObjectMapper objectMapper = new ObjectMapper();
    var result = objectMapper.readValue(responseMessage, PlayerModel[].class);

    return result;

  }
  public void showPlayer(RequestShowPlayerDto payload){
    rabbitTemplate.convertAndSend("",this.showPlayerQueue,payload);

  }
  public void deletePlayer(RequestDeletePlayerDto payload){
    rabbitTemplate.convertAndSend("",this.deletePlayerQueue,payload);

  }
  public void updatePlayer(RequestUpdatePlayerDto payload){
    rabbitTemplate.convertAndSend("",this.updatePlayerQueue,payload);

  }
  
  
}
