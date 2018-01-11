import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    public Game(){
        handler = new Handler();
        //this.addKeyListener();
        new Window(WIDTH, HEIGHT, "OOAD Assignment!",this);

        handler.addObject(new Mouse(0, 0, ID.Mouse));
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    public synchronized void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/ amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    public void tick(){
        handler.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args){
        new Game();
    }
}