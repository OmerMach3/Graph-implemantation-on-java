package dsa_project3;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author nurha
 */
public class DSA_Project3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListGraph graph = null;
        
        System.out.println("Menu, Choose action:\n\n");
        System.out.println("(1)Read file and return a graph");
        System.out.println("(2)Print closest characters to a certain character by given threshold");
        System.out.println("(3)Print farthest character to a certain character by given threshold");
        System.out.println("(4)Check if two characters connected");
        System.out.println("(5)observe the shortest path from one character to another");
        System.out.println("(6)Delete a certain character from the edge of a certain character");
        System.out.println("(7)Change weight of a certain character from the edge of a certain character");
        System.out.println("(8)print the graph");
        System.out.println("(9) exit");
        System.out.println("Enter action:");
        int input = sc.nextInt();
        while (true) {
            switch (input) {
                case 1:
                    graph = new ListGraph(400);
                    ReadFileGotGraph(graph);
                    System.out.println("Successfully returned!");
                    input = sc.nextInt();
                    break;
                case 2:
                    System.out.println("Enter first character's name and then the threshold:");
                    printClosestCharacters(sc.next(), sc.nextInt(), graph);
                    input = sc.nextInt();
                    break;
                case 3:
                    System.out.println("Enter first character's name and then the threshold:");
                    printFartherCharacters(sc.next(), sc.nextInt(), graph);
                    input = sc.nextInt();
                    break;
                case 4:
                    System.out.println("Enter the first character's name and then the second accordingly:");
                    isConnected(sc.next(), sc.next(), graph);
                    input = sc.nextInt();
                    break;
                case 5:
                    System.out.println("Enter the first character and then the second:");
                    pathTo(sc.next(), sc.next(), graph);
                    input = sc.nextInt();
                    break;
                case 6:
                    System.out.println("Enter the first character and then the second:");
                    delete(sc.next(), sc.next(), graph);
                    input = sc.nextInt();
                    break;
                case 7:
                    System.out.println("Enter first character's name then second and then the new weight:");
                    change(sc.next(), sc.next(), sc.nextInt(), graph);
                    input = sc.nextInt();
                    break;
                case 8:
                    NumberOfCharacterGroups(graph);
                    input = sc.nextInt();
                    break;
                case 9:
                    System.exit(1);
            }
        }
    }

    public static void ReadFileGotGraph(ListGraph graph) {
        try (Scanner sc = new Scanner(new File("Gameof.txt"))) {
            String[] line = null;
            while (sc.hasNextLine()) {
                line = sc.nextLine().split(",");
                graph.addEdge(new SeriesCharacter(line[0]), new SeriesCharacter(line[1]), Integer.valueOf(line[2]));

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void printClosestCharacters(String name, int threshold, ListGraph graph) {
        System.out.println("The closest characters are:");
        for (int i = 1; i < graph.edges[hashCodeGenerator(name, graph)].size(); i++) {

            if (Edge.class.cast(graph.edges[hashCodeGenerator(name, graph)].get(i)).weight < threshold) {
                System.out.print(graph.edges[hashCodeGenerator(name, graph)].get(i).toString() + ",");
            }

        }
    }

    public static void printFartherCharacters(String name, int threshold, ListGraph graph) {
        System.out.println("The closest characters are:");
        for (int i = 1; i < graph.edges[hashCodeGenerator(name, graph)].size(); i++) {

            if (Edge.class.cast(graph.edges[hashCodeGenerator(name, graph)].get(i)).weight > threshold) {
                System.out.print(graph.edges[hashCodeGenerator(name, graph)].get(i).toString() + ",");
            }

        }
    }

    public static void isConnected(String ch1, String ch2, ListGraph graph) {
        boolean is_connected = false;
        if (graph.edges[hashCodeGenerator(ch1, graph)] != null) {

            for (int i = 1; i < graph.edges[hashCodeGenerator(ch1, graph)].size(); i++) {
                if (Edge.class.cast(graph.edges[hashCodeGenerator(ch1, graph)].get(i)).to.name.equals(ch2)) {
                    System.out.println("Yes they are connected");
                    is_connected = true;
                }
            }
            if (!is_connected) {
                System.out.println("no they are not");
            }
        } else if (graph.edges[hashCodeGenerator(ch2, graph)] != null) {
            for (int i = 1; i < graph.edges[hashCodeGenerator(ch2, graph)].size(); i++) {
                if (Edge.class.cast(graph.edges[hashCodeGenerator(ch2, graph)].get(i)).to.name.equals(ch1)) {
                    System.out.println("Yes they are connected");
                    is_connected = true;
                }
            }
            if (!is_connected) {
                System.out.println("no they are not");
            }

        }

    }

    public static void pathTo(String ch1, String ch2, ListGraph g) {
        DepthFirstPaths src = new DepthFirstPaths(g, ch1);
        src.printPathTo(ch2, g);
    }

    public static void change(String ch1, String ch2, int weight, ListGraph graph) {
        System.out.print("Before:   ");
        for (int i = 1; i < graph.edges[hashCodeGenerator(ch1, graph)].size(); i++) {
            if (Edge.class.cast(graph.edges[hashCodeGenerator(ch1, graph)].get(i)).to.name.equals(ch2)) {
                System.out.println(Edge.class.cast(graph.edges[hashCodeGenerator(ch1, graph)].get(i)).weight);
            }
        }
        System.out.print("After:    ");
        for (int i = 1; i < graph.edges[hashCodeGenerator(ch1, graph)].size(); i++) {
            if (Edge.class.cast(graph.edges[hashCodeGenerator(ch1, graph)].get(i)).to.name.equals(ch2)) {
                Edge.class.cast(graph.edges[hashCodeGenerator(ch1, graph)].get(i)).weight = weight;
                System.out.println(weight);
            }

        }

    }

    public static void delete(String ch1, String ch2, ListGraph graph) {
        System.out.print("Before:  ");
        System.out.println(graph.edges[hashCodeGenerator(ch1, graph)].toString());
        for (int i = 1; i < graph.edges[hashCodeGenerator(ch1, graph)].size(); i++) {

            if (Edge.class.cast(graph.edges[(hashCodeGenerator(ch1, graph))].get(i)).to.name.equals(ch2)) {

                graph.edges[(hashCodeGenerator(ch1, graph))].remove((graph.edges[(hashCodeGenerator(ch1, graph))].get(i)));

            }

        }
        System.out.print("After:    ");
        System.out.println(graph.edges[hashCodeGenerator(ch1, graph)].toString());
    }

    //Custom method 1
    public static int hashCodeGenerator(String ch, ListGraph graph) {

        return (ch.hashCode() & 0x7fffffff) % graph.getNumV();
    }
    public static void NumberOfCharacterGroups(ListGraph graph){
        System.out.println(graph.toString());
    }
    public static int numberOfEdgesOfCharacter(String a,ListGraph g){
        return g.neighborsList(a).size();
        
    }
}

class SeriesCharacter {

    String name;

    SeriesCharacter(String name) {
        this.name = name;
    }

    public int hashCode(ListGraph graph) {
        return ((name.hashCode() & 0x7fffffff) % graph.getNumV());

    }

    public String toString() {
        return ("the name is:" + name);

    }

}

class ListGraph {

    LinkedList<Object>[] edges;
    private int numV;
    private int numE;

    public ListGraph(int V) {
        this.numV = V;
        edges = (LinkedList<Object>[]) new LinkedList[numV];
        for (int i = 0; i < numV; i++) {
            edges[i] = new LinkedList<Object>();
        }
    }

    public int getNumE() {
        return numE;
    }

    public int getNumV() {
        return numV;
    }

    public String getSeriesCharacter(SeriesCharacter ch1) {
        return edges[ch1.hashCode(this)].toString();
    }

    public void addEdge(SeriesCharacter from, SeriesCharacter to, int weight) {
        if (from.hashCode(this) >= 0 && from.hashCode(this) < numV && to.hashCode(this) >= 0 && to.hashCode(this) < numV) {
            if (edges[from.hashCode(this)].size() == 0) {
                edges[from.hashCode(this)].add(from);
            }

            edges[from.hashCode(this)].add(new Edge(to, weight));

            if (edges[to.hashCode(this)].size() == 0) {
                edges[to.hashCode(this)].add(to);
            }

            edges[to.hashCode(this)].add(new Edge(from, weight));
            numE++;
        } else {
            System.out.println("Vertex out of bounds!");

        }
    }

    public void removeEdge(int from, int to) {
        if (edges[from].contains(to)) {
            edges[from].remove(to);
        } else {
            System.out.println("Edge not found!");
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < numV; i++) {
            if (edges[i].size() != 0) {
                sb.append(i + " " + edges[i].toString() + "\n");
            }
        }

        return sb.toString();
    }

   
    public boolean isAdjacent(int from, int to) {
        return edges[from].contains(to);
    }

    public LinkedList<Object> neighborsList(String from) {
        return (LinkedList<Object>) edges[hashCode(this, from)].clone();
    }

    public int hashCode(ListGraph graph, String a) {
        return ((a.hashCode() & 0x7fffffff) % graph.getNumV());

    }

    public Object[] neighborsArray(String from) {
        Object[] ar = new Object[edges[((from.hashCode() & 0x7fffffff) % this.numV)].size()];
        edges[((from.hashCode() & 0x7fffffff) % this.numV)].toArray(ar);
        return ar;
    }

    
    public int degree(int from) {
        return edges[from].size();
    }

    public int maxDegree() {
        int maxDegree = edges[0].size();
        int node = 0;
        for (int i = 0; i < edges.length; i++) {
            if (maxDegree < edges[i].size()) {
                maxDegree = edges[i].size();
                node = i;
            }

        }

        System.out.println("Vertex with maximum degree is " + node);
        return maxDegree;
    }
}

class Edge {

    SeriesCharacter to;
    int weight;

    Edge(SeriesCharacter to, int weight) {
        this.to = to;
        this.weight = weight;

    }

    @Override
    public String toString() {
        return "to:" + to.name + " with weight of: " + weight;

    }
}

class DepthFirstPaths {

    boolean[] marked;
    int[] edgeTo;
    String from;

    public boolean hasPathTo(String w, ListGraph g) {
        return marked[DSA_Project3.hashCodeGenerator(w, g)];
    }

    public Integer[] pathTo(String w, ListGraph g) {
        int k = edgeTo[DSA_Project3.hashCodeGenerator(w, g)];
        java.util.Stack<Integer> st = new java.util.Stack<Integer>();
        st.push(k);
        while (k != DSA_Project3.hashCodeGenerator(from, g)) {
            k = edgeTo[k];
            st.push(k);
        }
        st.push(DSA_Project3.hashCodeGenerator(from, g));

        Integer[] path = new Integer[st.size()];
        for (int i = 0; i < path.length; i++) {
            path[i] = st.pop();
        }
        return path;

    }

    public void printPathTo(String w, ListGraph g) {

        Integer[] path = pathTo(w, g);
        //  StringBuilder bld =new StringBuilder("");
        for (int i = 1; i < path.length; i++) {

            System.out.print(SeriesCharacter.class.cast(g.edges[path[i]].get(0)).name + "->");
        }
        System.out.println("->" + w);

    }

    public DepthFirstPaths(ListGraph g, String from) {
        edgeTo = new int[g.getNumV()];
        marked = new boolean[g.getNumV()];
        this.from = from;
        dfs(g, from);
    }

    public void dfs(ListGraph g, String source) {
        marked[DSA_Project3.hashCodeGenerator(source, g)] = true;
        Object[] a = (Object[]) g.neighborsArray(source);
        for (int i = 1; i < a.length; i++) {
            String neighbor = Edge.class.cast(a[i]).to.name;
            if (!marked[DSA_Project3.hashCodeGenerator(neighbor, g)]) {
                dfs(g, neighbor);

                edgeTo[DSA_Project3.hashCodeGenerator(neighbor, g)] = DSA_Project3.hashCodeGenerator(source, g);
            }
        }
    }

}
