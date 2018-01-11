import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;
import java.util.Random;

public class Mouse extends GameObject{
    Random r = new Random();

    public Mouse(int x, int y, ID id){
        super(x, y, id);

        velX = r.nextInt(5);
        velY = r.nextInt(5);
        
    }

    
  
    public void tick(){
        x += velX;
        y += velY;
    }

    public void render(Graphics g){
        g.setColor(Color.white);

        g.fillRect(x, y, 20, 20);
    }
}