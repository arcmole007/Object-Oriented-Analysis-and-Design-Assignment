import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SuperMouse extends Mouse{

    Handler handler;

    public SuperMouse(int x, int y, ID id, Handler handler){
        super(x, y, id, handler);
        
    }

    public void tick(){
        move();
        outOfWall();
    }

    public void move() {
        x += velX;
        y += velY;
    }

    public void outOfWall() {
        x = Game.clamp(x, 0, Game.WIDTH - 45);
        y = Game.clamp(y, 0, Game.HEIGHT - 70);
    }

    public void render(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, 20, 20);
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 20, 20);
    }
}