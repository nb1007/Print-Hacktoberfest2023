
import java.util.*;

public class BellmanFord {
	//method to add edge
	public void addEdge(int source, int destination, int weight, ArrayList<Edge> list) {
		list.add(new Edge(source, destination, weight));
	}
	
	//print path method
	public void printPath(int src, int vertex, int[] previous) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		int currentVertex = vertex;
		while (currentVertex != src) {
			path.add(currentVertex);
			currentVertex = previous[currentVertex];
		}
		path.add(src);
		for (int i = path.size() - 1; i >= 0; i--) {
			System.out.print(path.get(i));
			if (i > 0) {
				System.out.print(" -> ");
			}
		}
	}

	public static void main(String[] args) {
		int numVertices = 5;
		int distance[] = new int[numVertices];
		int previous[] = new int[numVertices];
		int source = 0;
		ArrayList<Edge> list = new ArrayList<Edge>();
		BellmanFord graph = new BellmanFord();
		for (int i = 0; i < numVertices; i++) {
			//initialize distance matrix to infinite value
			distance[i] = Integer.MAX_VALUE;
			previous[i] = source;
		}
		
		//set distance of source matrix to 0
		distance[source] = 0;

		//add all the edges in graph
		graph.addEdge(0, 1, -1, list);
		graph.addEdge(0, 2, 4, list);
		graph.addEdge(1, 2, 3, list);
		graph.addEdge(1, 3, 2, list);
		graph.addEdge(1, 4, 2, list);
		graph.addEdge(3, 2, 5, list);
		graph.addEdge(3, 1, 1, list);
		graph.addEdge(4, 3, -3, list);
		graph.bellmanFord(source, graph, distance, previous, numVertices, list);

		System.out.println();
		System.out.println("Distance from each vertex to source");
		for (int i = 0; i < numVertices; i++) {
			System.out.println(" " + i + " --> " + distance[i]);
		}

		System.out.println();
		System.out.println("Path from each vertex to source");
		for (int i = 0; i < numVertices; i++) {
			graph.printPath(source, i, previous);
			System.out.println();
		}

	}

	public void bellmanFord(int source, BellmanFord graph, int[] distance, int[] previous, int numVertices,
			ArrayList<Edge> list) {
		distance[source] = 0;
		int iter = 0;
		for (int i = 0; i < numVertices - 1; i++) {
			boolean update = false;
			for (Edge edge : list) {
				int src = edge.source;
				int dest = edge.destination;
				if (distance[dest] > distance[src] + edge.weight) {
					distance[dest] = distance[src] + edge.weight;
					previous[dest] = src;
					update = true;
				}

			}
			
			//to detect negative edge cycle
			if (update == false) {
				break;
			}

			System.out.println();
			System.out.println("Iteration Number: " + iter);
			System.out.println("Distance Array");
			display(distance);
			System.out.println();
			System.out.println("Previous Array");
			display(previous);

			System.out.println();
			System.out.println();
			iter++;

		}
		boolean negativeCycle = false;
		for (Edge edge : list) {
			int src = edge.source;
			int dest = edge.destination;
			if (distance[dest] > distance[src] + edge.weight) {
				negativeCycle = true;
			}
		}
		if (negativeCycle == true) {
			System.out.println("There exists a negatice edge cycle");
		} else {
			System.out.println("There is no negative edge cycle");
		}
	}

	public static void display(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print("" + arr[i] + " ");
		}
	}

}

class Edge {
	int source;
	int destination;
	int weight;

	Edge(int source, int destination, int weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
}
