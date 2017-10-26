import processing.serial.*;

import java.net.Socket;
import java.io.*;

Serial motor;

Socket commander;
InputStream commands;

void setup(){
  size(300, 300);
  motor = new Serial(this, Serial.list()[1], 19200);
  
  try{
    commander = new Socket("www.sparkyjohn.tech", 59595);
    
    commands = commander.getInputStream();
  }catch(IOException e){}
  
  thread("getData");
}

void draw(){
}

void getData(){
  while(true){
    try{
      println("Waiting for command...");
      int com = commands.read();
      println("Command Received: " + com);
      motor.write(com);
    }catch(IOException e){}
  }
}