void setup() {
  size(400, 400); // set screen size to 400 x 400
  background(240);
  strokeWeight(5);
  stroke(0);
}
void keyReleased() {
  if (keyCode == UP) {
    up = false;
  }
  if (keyCode == DOWN) {
    down = false;
  }
  if (keyCode == RIGHT) {
    right = false;
  }
  if (keyCode == LEFT) {
    left = false;
  }
}
boolean up = false;
boolean down = false;
boolean right = false;
boolean left = false;
boolean b = false;
boolean g = false;
int fib = 1;
int fibp = 1;
int fibpp = 0;
int x = 1;
int y = -1;
void draw() {
  
  noFill();
  
  arc(x + 200, y + 200, fib * QUARTER_PI, fib * QUARTER_PI, 0, QUARTER_PI);
  fibpp = fibp;
  fibp = fib;
  fib = fibp + fibpp;
 
  System.out.println("x" + x);
  System.out.println("y" + y);
  x = fibp;
  y = fibpp + 1; 
  
  
}

