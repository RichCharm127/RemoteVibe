import g4p_controls.*;
import java.net.Socket;
import java.io.OutputStream;

Socket sendTo;
OutputStream sendData;

int level = 0;

void setup(){
  size(500, 200);
  try{
    sendTo = new Socket("www.sparkyjohn.tech", 59595);
    
    sendData = sendTo.getOutputStream();
  }catch(IOException e){}
  
  createGUI();
}

void draw(){
  background(0);
} 
