int BRICK_WIDTH = 30;
int BRICK_HEIGHT = 12;
int BRICKS_IN_BASE = 14;
int rowx = 290;
int rowy = 1000;
void setup(){
size(1000,1000);
  for(int i = BRICKS_IN_BASE; i >= 0; i--){
  drawrow(i, rowx, rowy);
  rowx+=15;
  rowy-=12;
  }
}

void draw(){}

void drawrow(int numberboxes, int x, int y){
  for(int j = numberboxes; j>=0; j--){
rect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
x+=30;
}}