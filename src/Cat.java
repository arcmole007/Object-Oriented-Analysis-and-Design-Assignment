import java.awt.Color;
import java.awt.Graphics;

public class Cat extends GameObject{

    public Cat(int x, int y, ID id){
        super(x, y, id);
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect(x, y, 20, 20);

    }
}