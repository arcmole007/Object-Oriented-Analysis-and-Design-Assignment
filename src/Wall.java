import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Wall extends GameObject{

    Handler handler;
    Random r = new Random();

    public Wall(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
    }

    public void tick(){
       //spawn();
    }

    public void spawn(){
        handler.addObject(new Cat(r.nextInt(this.x), r.nextInt(this.y), ID.Cat, handler));
    }
    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x, y, 20, 20);
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 20, 20);
    }
}