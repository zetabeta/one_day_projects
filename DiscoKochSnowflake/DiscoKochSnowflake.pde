int order;
Maxim maxim;
AudioPlayer player;
boolean takt;
int previousOrder;

void setup(){
  background(0);
  size(400, 400);
  order = 3;
  takt = true;
  drawSnowFlakeWithOrder(order, 1); 
  maxim = new Maxim(this);
  player = maxim.loadFile("beat.wav");
  player.setLooping(true);
  player.volume(1);
  player.setAnalysing(true);
  player.play();
  
}

void draw() {
  
  float pow = player.getAveragePower();
  previousOrder = order;
  order = (int) map(pow, 0, 0.31 , 0, 6);
  
  if (order != previousOrder && takt) {
   takt = false;
   background(0);
   drawSnowFlakeWithOrder(order, 6 - order);
   takt = true;   
  }

}

void mousePressed(){ 
  background(0);
  float v = map(mouseY, 0, 400, 0.1 , 1.5);
  float volume = map(mouseX, 0, 400, 0 , 1);
  player.speed(v);
  player.volume(volume);
}

void drawSnowFlakeWithOrder(int order, int weight){
  drawSnowFlakeWithParams(order, width, height, 10, weight);
}

void drawSnowFlakeWithParams(int order, int swidth, int sheight, int startY, int weight){
  
  int side = (int) (min(swidth, sheight) * 0.8);
  int triangleHeight = (int) (side * Math.sin(Math.toRadians(60.0)));
  
  Point p1 = new Point(swidth / 2, startY);
  Point p2 = new Point(swidth / 2 - side / 2, startY + triangleHeight);
  Point p3 = new Point(swidth / 2 + side / 2, startY + triangleHeight);
  
  int r = (int) map(order, 0, 6, 0, 255);
  int g = (int) map(mouseX, 0, width, 0, 255);
  int b = (int) map(mouseY, 0, height, 0, 255);

  drawSnowFlake(order, p1, p2, weight, r, g, b);
  drawSnowFlake(order, p2, p3, weight, r, g, b);
  drawSnowFlake(order, p3, p1, weight, r, g, b);
}

class Point {
  int x;
  int y;
 
  Point(int xp, int yp){
    x = xp;
    y = yp;
  } 
   
  void draw(int weight, int r, int g, int b){
    strokeWeight(weight);
    stroke(r, g, b);
    point(x,y);
  }  
}

void drawSnowFlake(int order, Point p1, Point p2, int weight, int r, int g, int b) {
  
  if (order == 0) {
    strokeWeight(weight);
    stroke(r,g,b);
    line(p1.x, p1.y, p2.x, p2.y);
  } else {
    int deltaX = p2.x - p1.x;
    int deltaY = p2.y - p1.y;
    
    double cosConst = Math.cos(Math.toRadians(30.0));
    int zx = (int)((p1.x + p2.x)/2 + cosConst * (p1.y - p2.y)/3.0);
    int zy = (int)((p1.y + p2.y)/2 + cosConst * (p2.x - p1.x)/3.0);
    
    Point x = new Point(p1.x + deltaX / 3, p1.y + deltaY / 3);
    Point y = new Point(p1.x + deltaX * 2 / 3, p1.y + deltaY * 2 / 3);
    Point z = new Point(zx, zy);

    drawSnowFlake(order - 1, p1, x, weight , r, g, b);
    drawSnowFlake(order - 1, x, z, weight , r, g, b);
    drawSnowFlake(order - 1, z, y, weight , r, g, b);
    drawSnowFlake(order - 1, y, p2, weight , r, g, b);
  }
  
}
