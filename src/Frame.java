import javax.swing.*;
import java.util.*;
public class Frame extends JFrame {
      Frame(){
            this.setTitle("Snake Game");
            this.setVisible(true);
            this.add(new Panel());
            this.setResizable(false);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.pack();
            this.setLocationRelativeTo(null);
      }
}
