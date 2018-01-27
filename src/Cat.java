import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Cat extends GameObject {

    Handler handler;
    Random r = new Random();

    public Cat(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 20, 20);
    }
    public void tick() {
        move();
        outOfBounds();
        collision();
    }

    public void move() {
        x += r.nextInt(5);
        
        y += r.nextInt(5);
    }

    public void outOfBounds() {
        x = Game.clamp(x, 0, Game.WIDTH - 38);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);
    }

    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Mouse){
                if(getBounds().intersects(tempObject.getBounds())){
                    this.x *= -1;
                    this.y *= -1;
                }
            }
            if(tempObject.getId() == ID.Wall){
                if(getBounds().intersects(tempObject.getBounds())){
                   this.setVelX(0); 
                   this.setVelY(0);
                }
            }
        }
    }
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 20, 20);

    }
}