int latchPin = 4;
int clockPin = 7;
int dataPin = 8; // Pinii SSD
int reading;
int reading2;
int reading3;
int choose = 0;

boolean start = false;   //buton de toggle intre setare si start ora

const int buttonPin = A1;
const int buttonPin2 = A2;// the number of the pushbutton pin
const int buttonPin3 = A3;
const int ledPin1 = 13;
const int ledPin2 = 12;
const int ledPin3 = 11;
const int ledPin4 = 10;

unsigned long test;

unsigned long currentTime = 0;
unsigned long resetTime = 0;
unsigned long startedTime = 0;


int buttonState;
int buttonState2;// the current reading from the input pin
int buttonState3;

int lastButtonState = LOW;
int lastButtonState2 = LOW;
int lastButtonState3 = LOW;

unsigned long lastDebounceTime = 0;
unsigned long lastDebounceTime2 = 0;
unsigned long lastDebounceTime3 = 0;
unsigned long debounceDelay = 10;


unsigned long sec = 0;
unsigned long mins = 0;
unsigned long hours = 0;


const unsigned char ssdlut[] = {0b00111111, 0b00000110, 0b01011011, 0b01001111,
                                0b01100110, 0b01101101, 0b01111101, 0b00000111, 0b01111111, 0b01101111, 0b01110111, 0b01111100, 0b00111001, 0b01011110, 0b01111001, 0b01110001
                               };
const unsigned char anodelut[] = {0b00000001, 0b00000010, 0b00000100, 0b00001000};
unsigned char digits[] = {0, 0, 0, 0}; // Numărul afişat va fi 1234. ModificaŃi aici pt alt număr
void setup ()
{
  pinMode(buttonPin, INPUT_PULLUP);
  pinMode(buttonPin2, INPUT_PULLUP);
  Serial.begin(9600);
  pinMode(ledPin1, OUTPUT);
  pinMode(ledPin2, OUTPUT);
  pinMode(ledPin3, OUTPUT);
  pinMode(ledPin4, OUTPUT);

  pinMode(latchPin, OUTPUT);
  pinMode(clockPin, OUTPUT);
  pinMode(dataPin, OUTPUT); // Cei trei pini pentru registrii de deplasare, configuraŃi ca iesire

}
void loop()
{

  currentTime = millis();          ///primul buton este butonul de start sau setare ora(toggle)imlicit vei putea sa iti setezi ceasul cand pornesti programul(cand termini se setat ceasul apesi din nou butonul)
  reading = digitalRead(buttonPin); //pentru a incepe
  if (reading != lastButtonState) {
    lastDebounceTime = millis();
  }
  if ((millis() - lastDebounceTime) > debounceDelay) {
    if (reading != buttonState) {
      buttonState = reading;
      if (buttonState == LOW) {
        start = !start;
        startedTime = millis();
      }
    }
  }
  lastButtonState = reading;



  reading2 = digitalRead(buttonPin2);
  if (reading2 != lastButtonState2) {
    lastDebounceTime2 = millis();
  }
  if ((millis() - lastDebounceTime2) > debounceDelay) {
    if (reading2 != buttonState2) {
      buttonState2 = reading2;


      if (buttonState2 == LOW) {
        choose = (choose + 1) % 3;



      }
    }
  }
  lastButtonState2 = reading2;






  reading3 = digitalRead(buttonPin3);
  if (reading3 != lastButtonState3) {
    lastDebounceTime3 = millis();
  }
  if ((millis() - lastDebounceTime3) > debounceDelay) {
    if (reading3 != buttonState3) {
      buttonState3 = reading3;

      if (buttonState3 == LOW)
      {
        if (choose == 0)
        {
          sec = (sec + 1) % 60;

        }
        if (choose == 1)
          mins = (mins + 1) % 60;
        if (choose == 2)
          hours = (hours + 1) % 24;
      }

    }
  }
  lastButtonState3 = reading3;

  if (start == true && choose == 0)
  {
    digitalWrite(ledPin1, LOW);
    digitalWrite(ledPin2, HIGH);
    digitalWrite(ledPin3, HIGH);
    digits[0] = ( (currentTime - startedTime + sec * 1000) / 10000) % 6;
    digits[1] = ( (currentTime - startedTime + sec * 1000) / 1000) % 10;
    digits[2] = ((currentTime - startedTime) / 100) % 10;
    digits[3] = ((currentTime - startedTime) / 10) % 10;
    if (((currentTime - startedTime + sec * 1000 + mins * 60000 + hours * 3600000) / 3600000) == 24)
    {
      mins = 0;
      sec = 0;
      hours = 0;
      currentTime = millis();

    }
  }
  if (start == true && choose == 1 || start == true && choose == 2)
  {
    if(choose==1)
    {
    digitalWrite(ledPin1, HIGH);
    digitalWrite(ledPin2, LOW);
    digitalWrite(ledPin3, HIGH);
    }
    if (choose==2)
    {
     digitalWrite(ledPin1, HIGH);
    digitalWrite(ledPin2, HIGH);
    digitalWrite(ledPin3, LOW);
    }
    digits[0] = ((currentTime - startedTime + sec * 1000 + mins * 60000 + hours * 3600000) / 36000000) % 3;
    digits[1] = ((currentTime - startedTime + sec * 1000 + mins * 60000 + hours * 3600000) / 3600000) % 10;
    digits[2] = ((currentTime - startedTime + sec * 1000 + mins * 60000) / 600000) % 6;
    digits[3] = ((currentTime - startedTime + sec * 1000 + mins * 60000) / 60000) % 10;
    if (((currentTime - startedTime + sec * 1000 + mins * 60000 + hours * 3600000) / 3600000) == 24)
    {
      mins = 0;
      sec = 0;
      hours = 0;
      currentTime = millis();
    }
  }

  if (start == false && choose == 0)
  {
    digitalWrite(ledPin1, LOW);
    digitalWrite(ledPin2, HIGH);
    digitalWrite(ledPin3, HIGH);
    digits[0] = sec / 10 % 10;
    digits[1] = sec % 10;
    digits[2] = 0;
    digits[3] = 0;
  }
  if (start == false && choose == 1)
  {
    digitalWrite(ledPin1, HIGH);
    digitalWrite(ledPin2, LOW);
    digitalWrite(ledPin3, HIGH);
    digits[0] = mins / 10 % 10;
    digits[1] = mins % 10;
    digits[2] = 0;
    digits[3] = 0;
  }
  if (start == false && choose == 2)
  {
    digitalWrite(ledPin1, HIGH);
    digitalWrite(ledPin2, HIGH);
    digitalWrite(ledPin3, LOW);
    
    digits[0] = hours / 10 % 10;
    digits[1] = hours % 10;
    digits[2] = 0;
    digits[3] = 0;
  }

 if (start==true)
 {
  digitalWrite(ledPin4, LOW);
 }
 else
 {
  digitalWrite(ledPin4, HIGH);
 }








  for (char i = 0; i <= 3; i++) // pentru fiecare din cele 4 cifre
  {

    unsigned char digit = digits[i]; // cifra curentă
    unsigned char cathodes = ~ssdlut[digit]; // catozii cifrei curente, vom nega valoarea din LUT

    digitalWrite(latchPin, LOW); // vom activa semnalul latch pentru a permite scrierea
    shiftOut(dataPin, clockPin, MSBFIRST, cathodes); // serializam octetul anozilor
    shiftOut(dataPin, clockPin, MSBFIRST, anodelut [i] ); // serializam octetul anozilor
    digitalWrite(latchPin, HIGH); // dezactivam semnalul latch
    delay(2); // asteptare
  }

}
