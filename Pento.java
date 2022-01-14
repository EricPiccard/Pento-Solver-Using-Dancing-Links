import java.io.IOException;
import java.util.ArrayList; 
import java.util.Scanner;

 
public class Pento {
 
    public static void main(String[] args) throws IOException 
    {
       final int[][] F = {{1, -1, 1, 0, 1, 1, 2, 1}, {0, 1, 1, -1, 1, 0, 2, 0},
        {1, 0, 1, 1, 1, 2, 2, 1}, {1, 0, 1, 1, 2, -1, 2, 0}, {1, -2, 1, -1, 1, 0, 2, -1},
        {0, 1, 1, 1, 1, 2, 2, 1}, {1, -1, 1, 0, 1, 1, 2, -1}, {1, -1, 1, 0, 2, 0, 2, 1}};

       final int[][] I = {{0, 1, 0, 2, 0, 3, 0, 4}, {1, 0, 2, 0, 3, 0, 4, 0}};

       final int[][] L = {{1, 0, 1, 1, 1, 2, 1, 3}, {1, 0, 2, 0, 3, -1, 3, 0},
        {0, 1, 0, 2, 0, 3, 1, 3}, {0, 1, 1, 0, 2, 0, 3, 0}, {0, 1, 1, 1, 2, 1, 3, 1},
        {0, 1, 0, 2, 0, 3, 1, 0}, {1, 0, 2, 0, 3, 0, 3, 1}, {1, -3, 1, -2, 1, -1, 1, 0}};

       final int[][] N = {{0, 1, 1, -2, 1, -1, 1, 0}, {1, 0, 1, 1, 2, 1, 3, 1},
        {0, 1, 0, 2, 1, -1, 1, 0}, {1, 0, 2, 0, 2, 1, 3, 1}, {0, 1, 1, 1, 1, 2, 1, 3},
        {1, 0, 2, -1, 2, 0, 3, -1}, {0, 1, 0, 2, 1, 2, 1, 3}, {1, -1, 1, 0, 2, -1, 3, -1}};

       final int[][] P = {{0, 1, 1, 0, 1, 1, 2, 1}, {0, 1, 0, 2, 1, 0, 1, 1},
        {1, 0, 1, 1, 2, 0, 2, 1}, {0, 1, 1, -1, 1, 0, 1, 1}, {0, 1, 1, 0, 1, 1, 1, 2},
        {1, -1, 1, 0, 2, -1, 2, 0}, {0, 1, 0, 2, 1, 1, 1, 2}, {0, 1, 1, 0, 1, 1, 2, 0}};

       final int[][] T = {{0, 1, 0, 2, 1, 1, 2, 1}, {1, -2, 1, -1, 1, 0, 2, 0},
        {1, 0, 2, -1, 2, 0, 2, 1}, {1, 0, 1, 1, 1, 2, 2, 0}};
      
       final int[][] U = {{0, 1, 0, 2, 1, 0, 1, 2}, {0, 1, 1, 1, 2, 0, 2, 1},
        {0, 2, 1, 0, 1, 1, 1, 2}, {0, 1, 1, 0, 2, 0, 2, 1}};
      
       final int[][] V = {{1, 0, 2, 0, 2, 1, 2, 2}};
      
       final int[][] W = {{1, 0, 1, 1, 2, 1, 2, 2}, {1, -1, 1, 0, 2, -2, 2, -1},
        {0, 1, 1, 1, 1, 2, 2, 2}, {0, 1, 1, -1, 1, 0, 2, -1}};
      
       final int[][] X = {{1, -1, 1, 0, 1, 1, 2, 0}};
      
       final int[][] Y = {{1, -2, 1, -1, 1, 0, 1, 1}, {1, -1, 1, 0, 2, 0, 3, 0},
        {0, 1, 0, 2, 0, 3, 1, 1}, {1, 0, 2, 0, 2, 1, 3, 0}, {0, 1, 0, 2, 0, 3, 1, 2},
        {1, 0, 1, 1, 2, 0, 3, 0}, {1, -1, 1, 0, 1, 1, 1, 2}, {1, 0, 2, -1, 2, 0, 3, 0}};
      
       final int[][] Z = {{0, 1, 1, 0, 2, -1, 2, 0}, {1, 0, 1, 1, 1, 2, 2, 2},
        {0, 1, 1, 1, 2, 1, 2, 2}, {1, -2, 1, -1, 1, 0, 2, -2}};

       final int[][][] shapes = {F, I, L, N, P, T, U, V, W, X, Y, Z};
       
       Scanner scan = new Scanner(System.in);
       
       int maxX = scan.nextInt();
       int maxY = 60/maxX;
       //int[][] map = new int[maxX+1][maxY+1];
       //creates and finds numbers
       ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
       //creates a bitmaps for 1s and 0s matrix
       ArrayList<ArrayList<Integer>> bits = new ArrayList<ArrayList<Integer>>();
      // System.out.println(maxX + " x " +maxY);
       scan.close();
       //use i to  insert intial locations of letters
       
      for(int i = 0; i< shapes.length; i++)
      {
        //System.out.println("i: " + i);
        for(int j = 0; j < shapes[i].length; j++)
        {
         // System.out.println("i: " + j);
            for(int y = 1; y < maxX+1; y++)
            {
              for(int x = 1; x < maxY+1; x++)
              {
                int []loc = {-1, -1, -1, -1, -1}; 
                int locI =0;
                if(x <= maxY && x >= 1 && y <= maxX && y >= 1) 
                {
                  //System.out.println(" " + y + " y");
                  int mult = ((y-1)*maxY);
                  //System.out.println(mult);
                  //System.out.println(x);
                  loc[0] =  mult + x;
                  
                  
                }
                //System.out.println(loc[0] + " origin "/* + "x: " + x + " y: " + y*/);
                locI++;
                for(int k = 0; k < shapes[i][j].length; k++)
                {
                  //System.out.println("i: " + k);
                  int tempX = x + shapes[i][j][k]; 
                  
                  int tempY = y + shapes[i][j][k+1];
                  //System.out.println(X[0][k+1] + " + " + y + " = " + tempY);
                  
                  if(tempX <= maxY && tempX >= 1 && tempY <= maxX && tempY >= 1) 
                  {
                    loc[locI] = ((tempY-1)*maxY) + tempX;  
                    
                    //System.out.println(loc[locI] + " loci");  
                  }
                  
                  locI++;  
                  k++;
                }
                // System.out.println(loc[locI - 1] + " loci2");
                boolean testFail = false;
                for(int locTest = 0; locTest < loc.length; locTest++)
                {
                  if(loc[locTest] == (-1))
                  {
                    testFail = true;
                  }
                }
                //Set map
                if(testFail == false)
                {
                  graph.add(new ArrayList<Integer>());
                  bits.add(new ArrayList<Integer>());
                  for(int m = 0; m < 72; m++)
                    {
                      bits.get(bits.size()-1).add(0);
                    }
                  
                  bits.get((bits.size()-1)).set(60+ i, 1);
                  
                  for(int addvalues = 0; addvalues < loc.length; addvalues++)
                  {
                    graph.get(graph.size()-1).add(loc[addvalues]);
        
                    for(int m = 0; m < loc.length; m++)
                    {
                      bits.get((bits.size()-1)).set(loc[m]-1, 1);
                    }
                  }
               
                }
              }
            
            
          }
        }
       // System.out.println(graph.size());
        

        
      }
              for(int m = 0; m < bits.size(); m++)
        {
          for(int n = 0; n < bits.get(m).size(); n++)
          {
            //System.out.print(bits.get(m).get(n));
          }
          //System.out.println();
        }
      /*
       3 × 20..........................2
       4 × 15..........................368
       5 × 12..........................1010
       6 × 10..........................2339
       */
              
     DLX2 dancer = new DLX2(72, bits);
               dancer.run();
         // System.out.println();
        }
            
     
  
                
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
      
    
          public void create()
      {
          
      }
}