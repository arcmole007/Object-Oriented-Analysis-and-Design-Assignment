import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Cheese extends GameObject {
    Handler handler;

    public Cheese(int x, int y, ID id,Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }

    public void tick() {
        collision();
    }

    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Mouse){
                if(getBounds().intersects(tempObject.getBounds())){
                   handler.removeObject(this);
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, 20, 20);
    }
}