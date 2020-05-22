import javax.swing.*;

import java.awt.*;
import java.awt.event.*;




public class Setup extends JPanel implements KeyListener, ActionListener{

        private ImageIcon titleImage;
        private int[] xlenght=new int[750];
        private int[] ylenght=new int[750];

        private boolean leftDir=false;
        private boolean rightDir=false;
        private boolean upDir=false;
        private boolean downDir=false;

        private ImageIcon leftMo;
        private ImageIcon rightMo;
        private ImageIcon upMo;
        private ImageIcon downMo;

        private Timer timer;
        private int delay=100;

        private ImageIcon SnakeImg;

        private int slenght=3;
        private int moves=0;

        private int foodx=((int)((Math.random()*33)+1)*25)+25;
        private int foodY=((int)((Math.random()*24)+1)*25)+75;
        private ImageIcon food;

        private int score=0;
        private int newGame=0;
        




    public Setup(){
        System.out.println(foodx+" "+foodY);
        addKeyListener( this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();

    }


    public void paint(Graphics g){

        if(moves==0){
            xlenght[2]=50;
            xlenght[1]=75;
            xlenght[0]=100;

            ylenght[2]=100;
            ylenght[1]=100;
            ylenght[0]=100;


        }

        //head title
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);

        //draw title image
        titleImage=new ImageIcon("res/snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);


        //boarder for gameplay
        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);

        //
        g.setColor(Color.BLACK);
        g.fillRect(24, 75, 850, 575);

        //score
        g.setColor(Color.white);
        g.drawString("Score : "+score  ,780, 30);

        //length
        g.setColor(Color.white);
        g.drawString("length : "+slenght ,780, 45);



        rightMo=new ImageIcon("res/rightmouth.png");
        rightMo.paintIcon(this, g, xlenght[0], ylenght[0]);

        for (int a=0;a<slenght;a++){
            if(a==0 && rightDir){
                rightMo=new ImageIcon("res/rightmouth.png");
                rightMo.paintIcon(this, g, xlenght[a], ylenght[a]);      
            }

            if(a==0 && leftDir){
                leftMo=new ImageIcon("res/leftmouth.png");
                leftMo.paintIcon(this, g, xlenght[a], ylenght[a]);      
            }

            if(a==0 && upDir){
                upMo=new ImageIcon("res/upmouth.png");
                upMo.paintIcon(this, g, xlenght[a], ylenght[a]);      
            }

            if(a==0 && downDir){
                downMo=new ImageIcon("res/downmouth.png");
                downMo.paintIcon(this, g, xlenght[a], ylenght[a]);      
            }

            if(a!=0){
                SnakeImg=new ImageIcon("res/snakeimage.png");
                SnakeImg.paintIcon(this, g, xlenght[a], ylenght[a]);      
            }



        }

        food=new ImageIcon("res/enemy.png");
        food.paintIcon(this, g, foodx, foodY);

        if(foodx==xlenght[0] && foodY==ylenght[0]){
            score+=10;
            slenght++;
            foodx=((int)((Math.random()*32))*25)+25;
            foodY=((int)((Math.random()*21))*25)+75;
          System.out.println(foodx+" "+foodY);

         food.paintIcon(this, g, foodx, foodY);


        }

        for(int i=1;i<slenght;i++){
            if(xlenght[i]==xlenght[0] && ylenght[i]==ylenght[0]){
              rightDir=false;
              leftDir=false;
              upDir=false;
              downDir=false;  
              newGame++;

            g.setColor(Color.white);
            g.setFont(new Font("Arial",Font.BOLD,50));
            g.drawString("Game Over!", 300, 300);
            g.setFont(new Font("Arial",Font.BOLD,20));
            g.drawString("press space to restart", 350, 340);


            };
        }

        g.dispose();


    }

 @Override
  public void keyPressed(KeyEvent e){

        
    if(newGame!=0){
    if(e.getKeyCode()==KeyEvent.VK_SPACE){
       
        moves=0;
        slenght=3;
        score=0;
        newGame=0;
        repaint();
       
    }
}

if(newGame==0){
      if(e.getKeyCode()==KeyEvent.VK_RIGHT){
          moves++;
          if(!leftDir){
              rightDir=true;
          }
          else{
            rightDir=false;
            leftDir=true;
          }

          
          upDir=false;
          downDir=false;
      }

      if(e.getKeyCode()==KeyEvent.VK_LEFT){
        moves++;
        if(!rightDir){
            leftDir=true;
        }
        else{
          rightDir=true;
          leftDir=false;
        }

        
        upDir=false;
        downDir=false;
    }

    if(e.getKeyCode()==KeyEvent.VK_UP){
        moves++;
        if(!downDir){
            upDir=true;
        }
        else{
          upDir=false;
          downDir=true;
        }

        
        rightDir=false;
        leftDir=false;
    }

    if(e.getKeyCode()==KeyEvent.VK_DOWN){
        moves++;
        if(!upDir){
            downDir=true;
        }
        else{
          upDir=true;
          downDir=false;
        }

        
        rightDir=false;
        leftDir=false;
    }
}
    
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    timer.start();
    if(rightDir){
        for(int i=slenght-1;i>=0;i--){
            ylenght[i+1]=ylenght[i];
        }

        for(int i=slenght-1;i>=0;i--){
            if(i==0){
                xlenght[i]=xlenght[i]+25;
            }
            else{
                xlenght[i]=xlenght[i-1];
                
            }
            if(xlenght[i]>850){
                xlenght[i]=25;
            }
        }
        repaint();
    }

    if(leftDir){
        for(int i=slenght-1;i>=0;i--){
            ylenght[i+1]=ylenght[i];
        }

        for(int i=slenght-1;i>=0;i--){
            if(i==0){
                xlenght[i]=xlenght[i]-25;
            }
            else{
                xlenght[i]=xlenght[i-1];
                
            }
            if(xlenght[i]<25){
                xlenght[i]=850;
            }
        }
        repaint();

    }

    if(upDir){
        for(int i=slenght-1;i>=0;i--){
            xlenght[i+1]=xlenght[i];
        }

        for(int i=slenght-1;i>=0;i--){
            if(i==0){
                ylenght[i]=ylenght[i]-25;
            }
            else{
                ylenght[i]=ylenght[i-1];
                
            }
            if(ylenght[i]<75){
                ylenght[i]=625;
            }
        }
        repaint();
    }

    if(downDir){
        for(int i=slenght-1;i>=0;i--){
            xlenght[i+1]=xlenght[i];
        }

        for(int i=slenght-1;i>=0;i--){
            if(i==0){
                ylenght[i]=ylenght[i]+25;
            }
            else{
                ylenght[i]=ylenght[i-1];
                
            }
            if(ylenght[i]>625){
                ylenght[i]=75;
            }
        }
        repaint();
    }



  }
  @Override
  public void keyReleased(KeyEvent e) {
  // TODO Auto-generated method stub
  
  }

  @Override
  public void keyTyped(KeyEvent e) {
  // TODO Auto-generated method stub
  
  }

}