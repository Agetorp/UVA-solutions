import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private ArrayDeque<Integer[]> nodesToSearch;
    private Integer[][] searchPositions;
    private Stack<Integer[]> alienStack;
    private Node[][] maze;
    private List<Edge> edges;
    private Integer[] start;
    private int aliensLeft;
    private int aliensTotal;
    private int nodesTotal;

    public static void main(String[] args) throws Exception {
        Main m = new Main();
        m.run(args);
    }

    private void run (String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine().trim());

        for (int i = 0; i < cases; i++) {
            String[] instream = (in.readLine()).split(" ");
            int x=Integer.parseInt(instream[0]);
            int y=Integer.parseInt(instream[1]);

            //Bryt om det är en 3x3 eller mindre maze
            if(x+y <= 6){
                for (int j = 0; j < y; j++) {
                    in.readLine();
                }
                System.out.println("0");
                continue;
            }

            nodesToSearch = new ArrayDeque<Integer[]>();
            alienStack = new Stack<Integer[]>();
            maze = new Node[y][x];
            start = new Integer[2];
            aliensTotal = 0;

            for (int j = 0; j < y; j++) { //Create maze
                char[] row = (in.readLine()).toCharArray();
                for (int k = 0; k < x; k++) {

                    if(k >= row.length){
                        maze[j][k] = new Node(-10);
                    } else {
                        switch (row[k]) {
                            case ' ': {
                                maze[j][k] = new Node(0);
                                break;
                            }
                            case '#': {
                                maze[j][k] = new Node(-10);
                                break;
                            }
                            case 'A': {
                                maze[j][k] = new Node(2);
                                aliensTotal++;
                                Integer[] a = {j,k};
                                alienStack.add(a);
                                break;
                            }
                            case 'S': {
                                maze[j][k] = new Node(2);
                                start[0] = j;
                                start[1] = k;
                                break;
                            }
                        }
                    }
                }
            }
            aliensLeft = aliensTotal;
            nodesTotal = aliensTotal+1;
            searchPositions = new Integer[nodesTotal][2];    //Lagrar start och alla aliens pos
            searchPositions[0] = start;                         //Lägg in start
            for (int j = 1; j <= aliensTotal; j++) {            //Lägg in alla aliens
                searchPositions[j] = alienStack.pop();
            }

            edges = new ArrayList<Edge>();

            findDirectPathsToAllFromAll();

            HashMap<Integer,Set<Integer>> forest = new HashMap<Integer,Set<Integer>>();
            for (int j = 0; j < nodesTotal; j++) {
                Set<Integer> vs = new HashSet<Integer>();
                vs.add(j);
                forest.put(j, vs);
            }

            Collections.sort(edges);
            ArrayList<Edge> minSpanTree = new ArrayList<Edge>();
            while(true) //break när alla noder är besökta
            {
                Edge check = edges.remove(0);//samma som pop

                Set<Integer> visited1 = forest.get(check.v1);
                Set<Integer> visited2 = forest.get(check.v2);
                if(visited1.equals(visited2))
                    continue;
                minSpanTree.add(check);
                visited1.addAll(visited2);
                for(Integer k : visited1)
                {
                    forest.put(k, visited1);
                }
                if(visited1.size()==nodesTotal)
                    break;
            }
            int max = 0;
            for (Edge e : minSpanTree) {
                max += e.wt;
            }
            System.out.println(max);

        }
    }
    private void findDirectPathsToAllFromAll() {

        // -10 = '#' = stängd
        // 0 =  ' '= öppen                      --> i
        // 1 =  ' '= besökt                     --> i+1
        // 2 = 'A' 'S' = Alien / start          --> i+2
        // 3 = 'A' 'S' = besökt Alien / start   --> i+3
        // -5 = 'A' 'S' = mål på vägen tillbaka


        for (int i = 0; i <= aliensTotal; i++) {
            aliensLeft = aliensTotal;
            nodesToSearch.clear();
            start = searchPositions[i];             //Börja inte på första start, utan skippa tidigare startpositioner
            nodesToSearch.add(start);
            maze[start[0]][start[1]].setType(i+1);  //Sätt start till besökt

            while(aliensLeft > 0 && !nodesToSearch.isEmpty()){
                Integer[] pos = nodesToSearch.removeFirst();
                int x = pos[0];
                int y = pos[1];
                searchUp(i, x, y);
                searchRight(i, x, y);
                searchDown(i, x, y);
                searchLeft(i, x, y);
            }

            maze[start[0]][start[1]].setType(-5);

            for (int j = i+1; j <= aliensTotal ; j++) {
                Edge edge = new Edge(i, j, getWeightFromPos(searchPositions[j]));
                edges.add(edge);
            }

            maze[start[0]][start[1]].setType(i+1);
        }
    }

    private void searchUp(int i, int x, int y) {
        Integer[] nodeToAdd;

        nodeToAdd = new Integer[2];
        if (maze[x - 1][y].getType() == i) {
            nodeToAdd[0] = x - 1;
            nodeToAdd[1] = y;
            nodesToSearch.addLast(nodeToAdd);
            maze[x - 1][y].setParent(x, y);
            maze[x - 1][y].setType(i+1);
        } else if (maze[x - 1][y].getType() == i+2) {
            nodeToAdd[0] = x - 1;
            nodeToAdd[1] = y;
            nodesToSearch.addLast(nodeToAdd);
            maze[x - 1][y].setParent(x, y);
            maze[x - 1][y].setType(i+3);
            aliensLeft--;
        } else if (maze[x - 1][y].getType() == -6) {
            maze[x - 1][y].setParent(x, y);
        }
    }

    private void searchLeft(int i, int x, int y) {
        Integer[] nodeToAdd;

        nodeToAdd = new Integer[2];
        if (maze[x][y - 1].getType() == i) {
            nodeToAdd[0] = x;
            nodeToAdd[1] = y - 1;
            nodesToSearch.addLast(nodeToAdd);
            maze[x][y - 1].setType(i+1);
            maze[x][y - 1].setParent(x, y);
        } else if (maze[x][y - 1].getType() == i+2) {
            nodeToAdd[0] = x;
            nodeToAdd[1] = y - 1;
            nodesToSearch.addLast(nodeToAdd);
            maze[x][y - 1].setParent(x, y);
            maze[x][y - 1].setType(i+3);
            aliensLeft--;
        } else if (maze[x][y - 1].getType() == -6) {
            maze[x][y - 1].setParent(x, y);
        }
    }

    private void searchDown(int i, int x, int y) {
        Integer[] nodeToAdd;


        nodeToAdd = new Integer[2];
        if (maze[x + 1][y].getType() == i) {
            nodeToAdd[0] = x + 1;
            nodeToAdd[1] = y;
            nodesToSearch.addLast(nodeToAdd);
            maze[x + 1][y].setParent(x, y);
            maze[x + 1][y].setType(i+1);
        } else if (maze[x + 1][y].getType() == i+2) {
            nodeToAdd[0] = x + 1;
            nodeToAdd[1] = y;
            nodesToSearch.addLast(nodeToAdd);
            maze[x + 1][y].setParent(x, y);
            maze[x + 1][y].setType(i+3);
            aliensLeft--;
        } else if (maze[x + 1][y].getType() == -6) {
            maze[x + 1][y].setParent(x, y);
        }
    }

    private void searchRight(int i, int x, int y) {
        Integer[] nodeToAdd = new Integer[2];
        if (maze[x][y + 1].getType() == i) { //Öppen, gå dit
            nodeToAdd[0] = x;
            nodeToAdd[1] = y + 1;
            nodesToSearch.addLast(nodeToAdd);
            maze[x][y + 1].setParent(x, y);
            maze[x][y + 1].setType(i+1);
        } else if (maze[x][y + 1].getType() == i+2) { //Alien, assimilate
            nodeToAdd[0] = x;
            nodeToAdd[1] = y + 1;
            nodesToSearch.addLast(nodeToAdd);
            maze[x][y + 1].setParent(x, y);
            maze[x][y + 1].setType(i+3);
            aliensLeft--;
        } else if (maze[x][y + 1].getType() == -6) {
            maze[x][y + 1].setParent(x, y);
        }
    }

    private class Node{
        int type;
        int parentX;
        int parentY;

        public int getType() {return type;}
        public void setType(int type) {this.type = type;}

        public Integer[] getParent(){
            Integer[] parent = new Integer[2];
            parent[0] = parentX;
            parent[1] = parentY;
            return parent;
        }
        public void setParent(int parentX, int parentY) {
            this.parentX = parentX;
            this.parentY = parentY;
        }

        public Node(int type){
            this.type = type;
        }
    }

    private int getWeightFromPos(Integer[] pos) {
        int x = pos[0];
        int y = pos[1];
        if(maze[x][y].type == -5){ //om denna är målet
            return 0;
        } else {
            return 1 + getWeightFromPos(maze[x][y].getParent());
        }

    }

    private class Edge implements Comparable<Edge>
    {
        final int v1;
        final int v2;
        final int wt;

        Edge(int v1, int v2, int wt)
        {
            this.v1=v1;
            this.v2=v2;
            this.wt=wt;
        }

        @Override
        public int compareTo(Edge o) {
            if(o.wt==this.wt)
                return 0;
            return o.wt < this.wt ? 1 : -1;
        }
    }
}
