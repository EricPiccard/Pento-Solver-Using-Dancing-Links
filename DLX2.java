import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DLX2 {
    
    class DataNode {
        DataNode L, R, U, D;
        ColumnNode C;
        int I = -1;
        public DataNode() {
            L = R = U = D = this;
        }

        public DataNode(ColumnNode c, int i) {
            this();
            C = c;
            I = i;
        }
        
        DataNode linkDown(DataNode node) {
            node.D = this.D;
            node.D.U = node;
            node.U = this;
            this.D = node;
            return node;
        }
        
        DataNode linkRight(DataNode node) {
            node.R = this.R;
            node.R.L = node;
            node.L = this;
            this.R = node;
            return node;
        }
        
        void linkLR() {
            this.L.R = this.R.L = this;
        }
        
        void unlinkLR() {
            this.L.R = this.R;
            this.R.L = this.L;
        }
        
        void linkUD() {
            this.U.D = this.D.U = this;
        }
        
        void unlinkUD() {
            this.U.D = this.D;
            this.D.U = this.U;
        }
    }

    class ColumnNode extends DataNode {
        int columnSize;
        String name;
        
        public ColumnNode(String name) {
            this.name = name;
            columnSize = 0;
            C = this;
        }
        
        void cover() {
          //System.out.println("test");
            unlinkLR();
            for (DataNode i = this.D; i != this; i = i.D) {
                for (DataNode j = i.R; j != i; j = j.R) {
                    j.unlinkUD();
                    j.C.columnSize--;
                }
            }
        }
        
        void uncover() {
            for (DataNode i = this.U; i != this; i = i.U) {
                for (DataNode j = i.L; j != i; j = j.L) {
                    j.linkUD();
                    j.C.columnSize++;
                }
            }
            linkLR();
        }
    }

    private ColumnNode head;
    private List<ColumnNode> columnNodesList;
    private int solutions;
    private LinkedList<DataNode> solution;
    
    private DLX2() {
        head = new ColumnNode("head");
        columnNodesList = new ArrayList<ColumnNode>();
        solution = new LinkedList<DataNode>();
    }
    
    public DLX2(int numberOfCols, ArrayList<ArrayList<Integer>> matrix) {
        this();
        buildColumnHeaders(numberOfCols);
        buildDancingLinks(matrix);
    }
    
    public DLX2(ArrayList<String> columnHeaders, ArrayList<ArrayList<Integer>> matrix) {
        this();
        buildColumnHeaders(columnHeaders);
        buildDancingLinks(matrix);
    }

    public void run() {
      
        solutions = 0;
        search(0);
        System.out.println(solutions);
    }
    
    public int getNumberOfSolutions() {
        return solutions;
    }
    
    // implementation of D. Knuth's Algorithm X
    private void search(int k) 
    {
      //System.out.println(head.R.C.name + " h.top " + k);
      //System.out.println(k);
      if(head.R.C == head.C)
      {
        //System.out.println("test");
        solutions++;
        return;
      }
      else
      {
        //System.out.println(head.R.C.name + " h.r");
        ColumnNode c = selectColumn();
        //System.out.println(c.name + " c");
        c.cover();
       // System.out.println(head.R.C.name + " c.cover " + k);
        for(DataNode r = c.D; r!= c; r= r.D)
        {
          solution.add(r);
          for(DataNode j = r.R; j!= r; j = j.R)
          {
            //System.out.println(j.C.name + " j1");
            j.C.cover();
          }
          search(k + 1);
          r = solution.remove(solution.size() - 1);
          c = r.C;
          for(DataNode j = r.L; j!= r; j = j.L)
          {
            //System.out.println(j.C.name + " j2");
            j.C.uncover();
          }
          
        }
        c.uncover();
      }
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      //cant remove current cover
      /*ColumnNode c = head;
      ColumnNode h = c.R.C; 
      //h.cover();
      for(ColumnNode n = head.R.C; n != head; n= n.R.C)
      {
        System.out.println(n.name);
      }
      
  System.out.println(h.R.D.R.I);
  System.out.println(c.R.C.I);*/
      
      
       
    }
    
    // select the column with least dataNodes per p. 6 of D. Knuth's 2000 paper
    private ColumnNode selectColumn() {
        int min = Integer.MAX_VALUE;
        ColumnNode colNode = null;
        for (ColumnNode node = (ColumnNode) head.R; node != head; node = (ColumnNode) node.R) {
            if (node.columnSize < min) {
                min = node.columnSize;
                colNode = node;
            }
        }
        return colNode;
    }
    
    // build headers for generic exact cover problem
    private void buildColumnHeaders(int numberOfCols) {
        ColumnNode temp = head;
        
        for (int i = 0; i < numberOfCols; i++) {
            ColumnNode node = new ColumnNode(Integer.toString(i));
            columnNodesList.add(node);
            temp = (ColumnNode) temp.linkRight(node);
        }
    }
    
    // build custom headers for exact cover problem
    private void buildColumnHeaders(ArrayList<String> colHeaders) {
        int nCols = colHeaders.size();

        ColumnNode temp = head;
        for (int i = 0; i < nCols; i++) {
            ColumnNode node = new ColumnNode(colHeaders.get(i));
            columnNodesList.add(node);
            temp = (ColumnNode) temp.linkRight(node);
        }
    }

    // create dancing links data structure from sparse matrix
    //code fixed will now create dancing links
    private void buildDancingLinks(ArrayList<ArrayList<Integer>> matrix) {
        int nRows = matrix.size();
        
        for (int r = 0; r < nRows; r++) {
            DataNode temp = null;
            //System.out.println(matrix.get(r));
            for (int i = 0; i< matrix.get(r).size(); i++) {
              //System.out.println(i + " " + matrix.get(r).get(i));
              if(matrix.get(r).get(i) != 0)
              {
                ColumnNode colHead = columnNodesList.get(i);
                DataNode newNode = new DataNode(colHead, matrix.get(r).get(i));
                
                if (temp == null)
                    temp = newNode;
                colHead.U.linkDown(newNode);
                temp = temp.linkRight(newNode);
                if(newNode.I ==  1)
                {
                  colHead.columnSize++; 
                }
              }
                //System.out.print(colHead.columnSize+ " |" + colHead.name + "| " );
                //System.out.println();
            }
           // System.out.println();
        }
    }
   
    private void printSolution() {
        for (DataNode row : solution) {
            while (columnNodesList.indexOf(row.C) > 11)
                row = row.R;
            StringBuilder sb = new StringBuilder(row.C.name + " ");
            for (DataNode node = row.R; node != row; node = node.R) {
              
                sb.append(node.C.name + " ");
            }
            System.out.println(sb);
        }
        System.out.println();
    }
}