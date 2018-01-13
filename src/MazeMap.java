public class MazeMap{
    
    public Tile[][] maze;
    Handler handler;
    public MazeMap(int[][] newMaze){

        maze = new Tile[30][30];
        handler = new Handler();

        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j< maze[i].length; j++){
                if(newMaze[j][i] == 0){
                   maze[j][i] =  new Tile(i*20, j*20, ID.Wall);
                    
                }
                if(newMaze[j][i] == 1){
                   maze[j][i] = new Tile(i*20, j*20, ID.Floor);
                    
                }
            }
        }
        
    }

    
}