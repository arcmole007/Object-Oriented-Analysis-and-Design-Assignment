import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Tile extends GameObject{

    public Tile(int x, int y, ID id){
        super(x, y, id);
    }

    public void tick(){

    }

    public void render(Graphics g){
        if(this.id == ID.Wall){
            g.setColor(Color.black);
            g.fillRect(x, y, 20, 20);
        }
        if(this.id == ID.Floor){
            g.setColor(Color.WHITE);
            g.fillRect(x, y, 20, 20);
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 20, 20);
    }
}