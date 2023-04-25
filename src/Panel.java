import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Panel extends JPanel implements ActionListener {
   static int width=1200;
   static int height=600;
   static int unit=50;
   Random random;
   boolean flag=false;
   Timer timer;
   int fx,fy;
   int score=0;
   int length=3;
   char dir='R';
   int xSnake[]=new int[288];
   int ySnake[]=new int[288];
   Panel(){
       this.setPreferredSize(new Dimension(width,height));
       this.setBackground(Color.black);
       random=new Random();
       this.setFocusable(true);
       this.addKeyListener(new key());
      gameStart();
   }

    private void gameStart() {
       snakeFood();
       flag=true;
       timer =new Timer(160,this);
       timer.start();
    }

    private void snakeFood() {
    fx=random.nextInt(width/unit)*unit;
    fy=random.nextInt(height/unit)*unit;
    }
    public void paintComponent(Graphics graphics){
       super.paintComponent(graphics);
       draw(graphics);
    }

    private void draw(Graphics graphics) {
      if(flag) {
          graphics.setColor(Color.red);
          graphics.fillOval(fx, fy, unit, unit);
          for(int i = 0; i < length; i++){
              if(i!=0) {
                  graphics.setColor(Color.green);
              }
              else graphics.setColor(Color.pink);
              graphics.fillOval(xSnake[i], ySnake[i], 50, 50);
          }


          graphics.setColor(Color.white);
          graphics.setFont(new Font("Comic Sans",Font.BOLD,40));
          FontMetrics fm=getFontMetrics(getFont()) ;

          graphics.drawString("Score:"+score,(width- fm.stringWidth("Score:"+score))/2,graphics.getFont().getSize());
      }else{
       gameOver(graphics);
      }
    }

    private void gameOver(Graphics graphics) {
       //final score
        graphics.setColor(Color.cyan);
        graphics.setFont(new Font("Comic Sans", Font.BOLD, 40));
        FontMetrics fme = getFontMetrics(graphics.getFont());
        graphics.drawString("Score:"+score, (width - fme.stringWidth("Score:"+score))/2, graphics.getFont().getSize());

        //gameover text
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Comic Sans", Font.BOLD, 80));
        fme = getFontMetrics(graphics.getFont());
        graphics.drawString("GAME OVER", (width - fme.stringWidth("GAME OVER"))/2, height/2);

        //to display the replay prompt
        graphics.setColor(Color.green);
        graphics.setFont(new Font("Comic Sans", Font.BOLD, 40));
        fme = getFontMetrics(graphics.getFont());
        graphics.drawString("Press R to replay", (width - fme.stringWidth("Press R to Replay"))/2, 3*height/4);


}
  public void eat(){
       if((fx==xSnake[0])&&(fy==ySnake[0])){
           length++;
           score++;
           snakeFood();
       }
  }
  public void hit(){
       for(int i=length;i>0;i--){
           if((xSnake[0]==xSnake[i])&&(ySnake[0]==ySnake[i])){
                flag=false;
           }
       }
       if(xSnake[0]<0){
           flag=false;
       }else if(xSnake[0]>width){
           flag=false;
       }else if(ySnake[0]<0){
           flag=false;
       }else if(ySnake[0]>height){
           flag=false;
       }
       if(flag==false){
           timer.stop();
       }
  }
  public void Move(){
       for(int i=length;i>0;i--){
           xSnake[i]=xSnake[i-1];
           ySnake[i]=ySnake[i-1];

       }
       switch (dir){
           case 'U':
               ySnake[0]=ySnake[0]-unit;
               break;
           case 'D':
               ySnake[0]=ySnake[0]+unit;
               break;
           case 'R':
               xSnake[0]=xSnake[0]+unit;
               break;
           case 'L':
               xSnake[0]=xSnake[0]-unit;
               break;
       }
  }
  public class key extends KeyAdapter{
      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                if(dir!='D'){
                    dir='U';
                }
                    break;
            case KeyEvent.VK_DOWN:
                if(dir!='U'){
                    dir='D';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(dir!='L'){
                    dir='R';
                }
                break;
            case KeyEvent.VK_LEFT:
                if(dir!='R'){
                    dir='L';
                }
                break;
            case KeyEvent.VK_R:
                if(flag==false){
                    score=0;
                    length=3;
                    dir='R';
                    Arrays.fill(xSnake,0);
                    Arrays.fill(ySnake,0);
                     gameStart();
                }
                break;
        }
      }
  }
    public void actionPerformed(ActionEvent e) {
      if(flag){
          Move();
          hit();
          eat();
      }
      repaint();
    }
}
