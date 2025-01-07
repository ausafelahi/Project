// Ausaf Elahi 65916
import java.util.*;

public class SmartCityTrafficSimulation {
	private Graph cityGraph;
	private PriorityQueue<Vehicle> vehicleQueue;
	private Map<Node, TrafficLight> trafficLight;
	
	public SmartCityTrafficSimulation() {
		this.cityGraph = new Graph();
		this.vehicleQueue = new PriorityQueue<>(Comparator.comparing(v -> v.id));
		this.trafficLight = new HashMap<>();
	}
	
	public List<Node> findShortPath(Node start, Node destination) {
		Map<Node, Integer> dist = new HashMap<>();
		Map<Node, Node> prev = new HashMap<>();
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(dist::get));
		
		for(Node node : cityGraph.getNodes()) {
			dist.put(node, Integer.MAX_VALUE);
			prev.put(node, null);
		}
		
		dist.put(start, 0);
		pq.add(start);
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if(current.equals(destination)) {
				break;
			}
			
			for(Edge edge : cityGraph.getEdges(current)) {
				Node neighbor = edge.destination;
				int newDist = dist.get(current) + edge.distance;
				
				if(newDist < dist.get(neighbor)) {
					dist.put(neighbor, newDist);
					prev.put(neighbor, current);
					pq.add(neighbor);
				}
			}
		}
		
		List<Node> path = new ArrayList<>();
		Node step = destination;
		while (step != null) {
			path.add(0, step);
			step = prev.get(step);
		}
		
		return path;
	}
	
	public void simulateTraffic() {
		for(TrafficLight light : trafficLight.values()) {
			light.updateState();
		}
		
		while(!vehicleQueue.isEmpty()) {
			Vehicle vcl = vehicleQueue.poll();
			List<Node> path = findShortPath(vcl.start, vcl.destination);
			
			System.out.println("Vehicle " + vcl.id + " Path: " + path);
		}
	}
	
	public void addTrafficLight(Node intersection) {
        trafficLight.put(intersection, new TrafficLight());
    }
	
	public List<Node> findPriorityPath(Node start, Node destination) {
		System.out.println("Emergency vehicle detected! Prioritizing route....");
		return findShortPath(start, destination);
	}

	public static void main(String[] args) {
		SmartCityTrafficSimulation scts = new SmartCityTrafficSimulation();
		
		Node a = new Node(1, "A");
        Node b = new Node(2, "B");
        Node c = new Node(3, "C");
        Node d = new Node(4, "D");
        
        scts.cityGraph.addNode(a);
        scts.cityGraph.addNode(b);
        scts.cityGraph.addNode(c);
        scts.cityGraph.addNode(d);
        
        scts.cityGraph.addEdge(a, b, 5);
        scts.cityGraph.addEdge(b, c, 10);
        scts.cityGraph.addEdge(a, c, 15);
        scts.cityGraph.addEdge(c, d, 10);
        
        scts.addTrafficLight(a);
        scts.addTrafficLight(b);
        scts.addTrafficLight(c);
        scts.addTrafficLight(d);
        
        scts.vehicleQueue.add(new Vehicle("V1", a, d));
        scts.vehicleQueue.add(new Vehicle("V2", b, c));
        
        scts.simulateTraffic();
        
        scts.findPriorityPath(a, d);
        
        scts.simulateTraffic();
	}

}
