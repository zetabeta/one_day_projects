import org.jbox2d.util.nonconvex.*;
import org.jbox2d.dynamics.contacts.*;
import org.jbox2d.testbed.*;
import org.jbox2d.collision.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.joints.*;
import org.jbox2d.p5.*;
import org.jbox2d.dynamics.*;


//Physics and Objects
Physics physics;
Vec2 startPoint;
Bubble[] bubbles;

// Sound
Maxim maxim;
AudioPlayer clink;

// Graphics
PImage background;

// Sensors
Accelerometer accel;

// detects collisions
//CollisionDetector detector;


void setup() {
  
  frameRate(60);
  imageMode(CENTER);
  orientation(PORTRAIT); 
  background = loadImage("background.jpg");
  
  //sensors and audio init
  accel = new Accelerometer();
  maxim = new Maxim(this);
  clink = maxim.loadFile("clink.wav");
  
  
  // physics init
  startPoint = new Vec2(200, height - 150);
  physics = new Physics(this, width, height, 0, -10, width*2, height*2, width, height, 100);
  physics.setCustomRenderingMethod(this, "myCustomRenderer");
  physics.setDensity(0.2f);
  physics.getWorld().setGravity(new Vec2(3,3));
  bubbles = new Bubble[12];
  //detector = new CollisionDetector (physics, this); //TODO
  
  // bubble images
  PImage sBubbleImage = loadImage("bubbleS.png");
  PImage sBubbleBlast = loadImage("blastS.png");
  PImage smBubbleImage = loadImage("bubbleSM.png");
  PImage smBubbleBlast = loadImage("blastSM.png");
  PImage mBubbleImage = loadImage("bubbleM.png");
  PImage mBubbleBlast = loadImage("blastM.png");
  PImage mlBubbleImage = loadImage("bubbleML.png");
  PImage mlBubbleBlast = loadImage("blastML.png");
  PImage lBubbleImage = loadImage("bubbleL.png");
  PImage lBubbleBlast = loadImage("blastL.png");
  PImage xlmBubbleImage = loadImage("bubbleXLM.png");
  PImage xlmBubbleBlast = loadImage("blastXLM.png");
  PImage xlBubbleImage = loadImage("bubbleXL.png");
  PImage xlBubbleBlast = loadImage("blastXL.png");
  
  // sounds init
  AudioPlayer waterdropS = maxim.loadFile("waterdrop.wav");
  waterdropS.setLooping(false);
  waterdropS.speed(3.0f);
  AudioPlayer waterdropSM = maxim.loadFile("waterdrop.wav");
  waterdropSM.setLooping(false);
  waterdropSM.speed(2.75f);
  AudioPlayer waterdropM = maxim.loadFile("waterdrop.wav");
  waterdropM.setLooping(false);
  waterdropM.speed(2.5f);
  AudioPlayer waterdropML = maxim.loadFile("waterdrop.wav");
  waterdropML.setLooping(false);
  waterdropML.speed(2.2f);
  AudioPlayer waterdropL = maxim.loadFile("waterdrop.wav");
  waterdropL.setLooping(false);
  waterdropL.speed(2.0f);
  AudioPlayer waterdropXLM = maxim.loadFile("waterdrop.wav");
  waterdropXLM.setLooping(false);
  waterdropXLM.speed(1.5f);
  AudioPlayer waterdropXL = maxim.loadFile("waterdrop.wav");
  waterdropXL.setLooping(false);
  waterdropXL.speed(1.0f);
  waterdropXL.volume(1.0f);
  
  // bubble sizes
  int sizeS = 100;
  int sizeSM = 120;
  int sizeM = 170;
  int sizeML = 185;
  int sizeL = 200;
  int sizeXLM = 215;
  int sizeXL = 230;
  
  // bubble size M 
  Body bodyM = physics.createCircle(width/2, 100, sizeM/2);
  Bubble bubbleM = new Bubble(sizeM, mBubbleImage, mBubbleBlast, waterdropM, bodyM);
  bubbles[0] = bubbleM;
  
  // bubble size S 
  Body bodyS = physics.createCircle(width/2, 100, sizeS/2);
  Bubble bubbleS = new Bubble(sizeS, sBubbleImage, sBubbleBlast, waterdropS, bodyS);
  bubbles[1] = bubbleS;
  
  // bubble size M 
  Body bodyM2 = physics.createCircle(width/2, 100, sizeM/2);
  Bubble bubbleM2 = new Bubble(sizeM, mBubbleImage, mBubbleBlast, waterdropM, bodyM2);
  bubbles[2] = bubbleM2;
  
  // bubble size L 
  Body bodyL = physics.createCircle(width/2, 100, sizeL/2);
  Bubble bubbleL = new Bubble(sizeL, lBubbleImage, lBubbleBlast, waterdropL, bodyL);
  bubbles[3] = bubbleL;
  
  // bubble size XL 
  Body bodyXL = physics.createCircle(width/2, 100, sizeXL/2);
  Bubble bubbleXL = new Bubble(sizeXL, xlBubbleImage, xlBubbleBlast, waterdropXL, bodyXL);
  bubbles[4] = bubbleXL;
  
  // bubble size S 
  Body bodyS1 = physics.createCircle(width/2, 100, sizeS/2);
  Bubble bubbleS1 = new Bubble(sizeS, sBubbleImage, sBubbleBlast, waterdropS, bodyS1);
  bubbles[5] = bubbleS1;
  
  // bubble size S 
  Body bodyS2 = physics.createCircle(width/2, 100, sizeS/2);
  Bubble bubbleS2 = new Bubble(sizeS, sBubbleImage, sBubbleBlast, waterdropS, bodyS2);
  bubbles[6] = bubbleS2;
  
  // bubble size L 
  Body bodyL2 = physics.createCircle(width/2, 100, sizeL/2);
  Bubble bubbleL2 = new Bubble(sizeL, lBubbleImage, lBubbleBlast, waterdropL, bodyL2);
  bubbles[7] = bubbleL2;
  
  // bubble size S 
  Body bodyS3 = physics.createCircle(width/2, 100, sizeS/2);
  Bubble bubbleS3 = new Bubble(sizeS, sBubbleImage, sBubbleBlast, waterdropS, bodyS3);
  bubbles[8] = bubbleS3;
  
  // bubble size SM 
  Body bodySM = physics.createCircle(width/2, 100, sizeSM/2);
  Bubble bubbleSM = new Bubble(sizeSM, smBubbleImage, smBubbleBlast, waterdropSM, bodySM);
  bubbles[9] = bubbleSM;
  
  // bubble size XLM 
  Body bodyXLM = physics.createCircle(width/2, 100, sizeXLM/2);
  Bubble bubbleXLM = new Bubble(sizeXLM, xlmBubbleImage, xlmBubbleBlast, waterdropXLM, bodyXLM);
  bubbles[10] = bubbleXLM;
  
  // bubble size ML 
  Body bodyML = physics.createCircle(width/2, 100, sizeML/2);
  Bubble bubbleML = new Bubble(sizeML, mlBubbleImage, mlBubbleBlast, waterdropML, bodyML);
  bubbles[11] = bubbleML;
}

void draw() {
  image(background, width/2, height/2, width, height);
  fill(0);
  
  // make gravity dependent on the accelerometer data
  float aX = accel.getX();
  float aY = accel.getX();
  float aZ = accel.getZ();
  physics.getWorld().setGravity(new Vec2((aX)*5*aZ,(-aY)*5*aZ));
  
  // bubbles blast on touch 
  if (mousePressed){
    for (int i = 0; i < bubbles.length; i++){
     int bubblePosX = (int) physics.worldToScreen(bubbles[i].body.getPosition()).x;
     int bubblePosY = (int) physics.worldToScreen(bubbles[i].body.getPosition()).y;
     int size = bubbles[i].size;
     if ( bubblePosX + size/2 > mouseX && bubblePosX - size/2 <= mouseX && 
          bubblePosY + size/2 > mouseY && bubblePosY - size/2 <= mouseY){
       bubbles[i].blast = true;
     } 
    }
  }
}

// play funny sounds on mouse release, reset blast flags 
// and respawn bubbles somewhere on the screen
void mouseReleased()
{
    for (int i = 0; i < bubbles.length; i++){
     if (bubbles[i].blast) {
       bubbles[i].sound.cue(0);
       bubbles[i].sound.play();
       int randX = 3 + (int) random(width - 4);
       int randY = 3 + (int) random(height - 4);
       bubbles[i].body.setPosition(physics.screenToWorld(new Vec2(randX, randY)));
       bubbles[i].blast = false;
     }
   }
}

//render the bubbles and blasts 
void myCustomRenderer(World world) {
  for (int i = 0; i < bubbles.length; i++){
    Vec2 screenStartPoint = physics.worldToScreen(startPoint);
    Vec2 screenBubblePos = physics.worldToScreen(bubbles[i].body.getWorldCenter());
    float angle = physics.getAngle(bubbles[i].body);
    pushMatrix();
    translate(screenBubblePos.x, screenBubblePos.y);
    rotate(-radians(angle));
    if (bubbles[i].blast) {
      image(bubbles[i].blastImage, 0, 0, bubbles[i].size, bubbles[i].size);
    } else {
      image(bubbles[i].bubbleImage, 0, 0, bubbles[i].size, bubbles[i].size);
    }
    popMatrix();
  }
}

//the bubble class 
class Bubble {
  
  int size;
  PImage bubbleImage;
  PImage blastImage;
  AudioPlayer sound;
  Body body;
  boolean blast;
 
  Bubble(int size, PImage bubbleImage, PImage blastImage, AudioPlayer sound, Body body){
    this.size = size;
    this.bubbleImage = bubbleImage;
    this.blastImage = blastImage;
    this.sound = sound;
    this.body = body;
    this.blast = false;
  }  

}

//void collision(Body b1, Body b2, float impulse) {
 // if ((b2.getMass() > 0) && (b1.getMass() > 0)) {
    //clink.cue(0);
    //clink.speed(impulse / 100); 
    //clink.volume(impulse / 100); 
    //clink.play();
//    Vec2 impulseB1 = new Vec2();
 //   Vec2 screenB1Pos = physics.worldToScreen(b1.getWorldCenter());
 //   impulseB1.set(screenB1Pos);
 //   impulseB1 = impulseB1.sub(b1.getWorldCenter());
 //   impulseB1 = impulseB1.mul(0.003);
 //   b1.applyImpulse(impulseB1, b1.getWorldCenter());
 //   Vec2 impulseB2 = new Vec2();
  //  Vec2 screenB2Pos = physics.worldToScreen(b2.getWorldCenter());
 //   impulseB2.set(screenB2Pos);
 //   impulseB2 = impulseB2.sub(b2.getWorldCenter());
 //   impulseB2 = impulseB2.mul(0.003);
 //   b2.applyImpulse(impulseB2, b2.getWorldCenter());   
 // }

  //if (b1.getMass() == 0 || b2.getMass() == 0) {// b1 or b2 are walls
  //  clink.cue(0);
  //  clink.speed(impulse / 100); 
  //  clink.volume(impulse / 100); 
  //  clink.play();
  //} 
//}




