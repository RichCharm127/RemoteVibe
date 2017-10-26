#define motorPin 11

void setup() {
  pinMode(motorPin, OUTPUT);
  Serial.begin(19200);
  analogWrite(motorPin, 0);
}

void loop() {
  if(Serial.available() > 0){
    analogWrite(motorPin, Serial.read());
  }
}
