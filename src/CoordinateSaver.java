public class CoordinateSaver{

    private int x;
    private int y;

    public CoordinateSaver(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setCoordinateX(int x){
        this.x = x;
    }

    public void setCoordinateY(int y){
        this.y = y;
    }

    public int getCoordinateX(){
        return x;
    }

    public int getCoordinateY(){
        return y;
    }
}