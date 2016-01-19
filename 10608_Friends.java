import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        Main m = new Main();
        m.run(args);
    }

    private void run (String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine().trim());

        for (int i = 0; i < cases; i++) {
            String[] instream = (in.readLine()).split(" ");
            int citizen=Integer.parseInt(instream[0]);
            int pairs=Integer.parseInt(instream[1]);
            DisjointSet disjointSet = new DisjointSet(citizen);
            if(pairs == 0){
                break;
            }
            for (int j = 0; j < pairs; j++) {
                instream = (in.readLine()).split(" ");
                int a=Integer.parseInt(instream[0]);
                int b=Integer.parseInt(instream[1]);
                disjointSet.union(a, b);
            }
            System.out.println(disjointSet.getBiggestGroup());
        }
    }

    private class DisjointSet {

        private int[] parents;
        private int[] size;


        public int find(int i) {

            int p = parents[i];
            if (i == p) {
                return i;
            }
            return parents[i] = find(p);

        }


        public void union(int i, int j) {
            int root1 = find(i);
            int root2 = find(j);

            if (root2 == root1) return;

            parents[root2] = root1;
            size[root1] += size[root2];
        }

        public int getBiggestGroup(){
            int max = 0;
            for (int i = 0; i < size.length; i++) {
                max = (max < size[i]) ? size[i] : max;
            }
            return max;
        }


        public DisjointSet(int max) {
            max++;
            parents = new int[max];
            size = new int[max];

            for (int i = 0; i < max; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }
    }
}
