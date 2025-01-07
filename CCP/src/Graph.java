// Areesha Magsi 65362
import java.util.*;

public class Graph {
	private Map<Node, List<Edge>> adjList;
	
	public Graph() {
		this.adjList = new HashMap<>();
	}
	
	public void addNode(Node node) {
		adjList.putIfAbsent(node, new ArrayList<>());
	}
	
	public void addEdge(Node source, Node destination, int distance) {
		Edge edge = new Edge(source, destination, distance);
		adjList.get(source).add(edge);
	}
	
	public List<Edge> getEdges(Node node) {
		return adjList.getOrDefault(node, new ArrayList<>());
	}
	
	public Set<Node> getNodes() {
		return adjList.keySet();
	}
}
