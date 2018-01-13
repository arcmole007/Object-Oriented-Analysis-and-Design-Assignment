import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    Random r = new Random();

    private BufferedImage maze = null;

    public Game(){
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        new Window(WIDTH, HEIGHT, "OOAD Assignment!",this);
        
        BufferedImageLoader loader = new BufferedImageLoader();
        maze = loader.loadImage("test.png");

        loadLevel(maze);
        //handler.addObject(new Mouse(0, 0, ID.Mouse, handler));
       // handler.addObject(new Cat(0, 40, ID.Cat, handler));
        //handler.addObject(new Cheese(r.nextInt(600), r.nextInt(600), ID.Cheese));
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
        this.requestFocus();
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
    /**Method to clamp the object from moving outside the wall */
    public static int clamp(int var, int min, int max){
        if(var >= max){
            return var = max;
        }
        else if(var <= min){
            return var = min;
        }
        else{
            return var;
        }
    }
    
    public void loadLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                int pixel = image.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 255){
                    handler.addObject(new Wall(i*20, j*20, ID.Wall, handler));
                    handler.addObject(new Cat(i*20, j*20, ID.Cat, handler));
                }
                if(blue == 255)
                    handler.addObject(new Mouse(i*20, j*20, ID.Mouse, handler));
            }
        }
    }

    public static void main(String[] args){
        new Game();
    }
}