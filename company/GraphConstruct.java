package com.company;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GraphConstruct extends JPanel implements ActionListener, KeyListener {
private int space;
private int width1 = 80; //width for many cars
private int height1 = 70; //height for many cars
private int speed;
private int Width = 500; //Width of main car
private int Heigth = 700; //Height of main car
private int move = 20;   //Limit for frame
private int count = 0;
private ArrayList <Rectangle> cars;
private Random random;
private Rectangle car;
Timer t;

public GraphConstruct(){  //Constructor
    cars = new ArrayList<Rectangle>();
    car = new Rectangle(Width/2-90,Heigth-100,80,60);
    space = 250;
    speed = 4;
    random = new Random();
    addCar(true);
    addCar(true);
    addCar(true);
    addCar(true);
    addCar(true);
    addCar(true);
    addCar(true);
    addKeyListener(this);
    setFocusable(true);
    t = new Timer(15,this);
    t.start();
}

public void addCar(boolean first){    // add more cars
    int positionX = random.nextInt()%2;
    int x = 0;
    int y = 0;
    int width = width1;
    int height = height1;
    if(positionX ==0){
        x = 500/2-90; //Width of main car/2-90 and position for other car
    }
    else{
        x = 500/2+10;
    }
    if(first){
        cars.add(new Rectangle(x,y-100-(cars.size()*space),width,height));
        }
    else{
        cars.add(new Rectangle(x,cars.get(cars.size()-1).x+600,width,height));
    }
}

@Override
public void paint(Graphics g){  //Design for cars, Override from javax.Graphs
    super.paintComponent(g);
    g.setColor(Color.green);  //Gazon
    g.fillRect(0,0,Width,Heigth);
    g.setColor(Color.darkGray); //road
    g.fillRect(Width/2-100,0,200,Heigth);
    g.setColor(Color.red);  //main car
    g.fillRect(car.x,car.y,car.width,car.height);
    g.setColor(Color.white);  //Line between road
    g.drawLine(Width/2,0,Width/2,Heigth);
    g.setColor(Color.blue);  //other cars

    for(Rectangle rect:cars){
        g.fillRect(rect.x,rect.y,rect.width,rect.height);
    }
}
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    Rectangle rectangle;
    count++;        //From count moment it depend to click of buttons, with time the speed of main car will rise,but I could't rise road height
    if(count%1000 == 0){
    speed++;
    if(move < 50){
       move+=10;
    }
}

for(int i=0;i<cars.size();i++){
    rectangle=cars.get(i);
    rectangle.y+=speed;
}
//Crashing avaria
        for(Rectangle rectangle1:cars){
            if(rectangle1.intersects(car)){
                car.y=rectangle1.y+height1;
            }
        }
        for(int i=0;i<cars.size();i++){
           rectangle = cars.get(i);
           if(rectangle.y+rectangle.height > 700){
              cars.remove(rectangle);
              addCar(false);
           }
        }
repaint();
    }
///For moving with buttons in device

    public void moveUP() {
        if (car.y - move < 0) {
            System.out.println("Go!");
        } else {
            car.y -= move;      //For limit motion of car where it meet border
        }
    }
    public void moveDOWN(){
    if(car.y + move + car.height > Heigth-1){
        System.out.println("Go!");
    }
    else{
        car.y+=move;         //For limit motion of car where it meet border
    }
    }

    public void moveRIGHT(){
    if(car.x+ move > Width/2+10){
        System.out.println("Go!");
    }
    else{
        car.x+=move;     // For not passing border from right and left side
    }
    }

    public void moveLEFT(){
    if(car.x - move<Width/2-90){
        System.out.println("Go!");
    }
    else{
        car.x-=move;     // For not passing border from right and left side
    }
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {   //Function user action by buttons
     int key = keyEvent.getKeyCode();
     switch (key){
         case KeyEvent.VK_UP:
             moveUP();
             break;
             case KeyEvent.VK_DOWN:
                 moveDOWN();
                 break;
                 case KeyEvent.VK_LEFT:
                     moveLEFT();
                     break;
                     case KeyEvent.VK_RIGHT:
                         moveRIGHT();
                         break;
         default:
             System.out.println("Only direction functions!");
             break;
     }
    }
}
