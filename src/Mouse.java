import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Window;
import java.util.Random;



public class Mouse extends GameObject {
    Random r = new Random();
    Handler handler;

    //int hp = 3;
    int cheeseEat = 0;

    public Mouse(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        //velX = r.nextInt(5);
        //velY = r.nextInt(5);

    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 20, 20);
    }

    public void tick() {
        move();
        //outOfWall();
        collision();
    }

    public void move() {
        x += velX;
        y += velY;
    }

    public void outOfWall() {
        x = Game.clamp(x, 0, Game.WIDTH - 38);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);
    }

    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Wall){
                if(getBounds().intersects(tempObject.getBounds())){
                   x += velX * -1;
                   y += velY * -1;
                }
            }
            if(tempObject.getId() == ID.Cat){
                if(getBounds().intersects(tempObject.getBounds())){
                    //hp -= 1;
                    handler.removeObject(this);
                }
            }
            if(tempObject.getId() == ID.Cheese){
                if(getBounds().intersects(tempObject.getBounds())){
                    //hp -= 1;
                    cheeseEat++;
                    System.out.println(cheeseEat);
                }
            }
            
                
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 20, 20);
    }
}