
class VRServo
{
    //Servo servo;
    int pinP;
    int pinN;
    int pos; // Текущее положение сервы
    char mode; // F, R, S - Forward, Reverse, Stop

  public:
    VRServo(int pinPositive, int pinNegative)  // Конструктор. pinPositive, pinNegative
    {
      pinP = pinPositive;
      pinN = pinNegative;
      pinMode(pinPositive, OUTPUT);
      pinMode(pinNegative, OUTPUT);

      mode = 1;
    }

    void Update()
    {
      // К этому моменту режим работу уже получен от ВТ и записан в переменную mode.
      // Просто выполним его

      switch (mode)
      {
        case 'F':
          pinP = 1;
          pinN = 0;
          break;

        case 'R':
          pinP = 0;
          pinN = 1;
          break;

        case 'S':
          pinP = 0;
          pinN = 0;
          break;  // Возможно, стоит добавить активное торможение
      }
    }
};









void setup() {
  // put your setup code here, to run once:

}

void loop() {
  // put your main code here, to run repeatedly:

}
