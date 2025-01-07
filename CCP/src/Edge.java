// Muskan Zehra 62553
public class Edge {
	Node source;
    Node destination;
    int distance; 
    int trafficLoad; 

    public Edge(Node source, Node destination, int distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.trafficLoad = 0;
    }
}