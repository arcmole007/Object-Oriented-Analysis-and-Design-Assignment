import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Cheese extends GameObject{

    public Cheese(int x, int y, ID id){
        super(x, y, id);
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 20, 20);
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect(x, y, 20, 20);
    }
}