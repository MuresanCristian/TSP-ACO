package tsplib;

public class TSPBackTracking {
	static double tsp(double [][] graph, boolean[] vizitat, int curr, int n, int count, double cost , double bt) {

		if(count == n && graph[curr][0] > 0) {
			bt = Math.min(bt, cost + graph[curr][0]);
			return bt;
		}

		for(int i = 0; i<n ; i++)
		{
			if(vizitat[i] == false && graph[curr][i] > 0)
			{
				vizitat[i] = true;
				bt = tsp(graph, vizitat, i ,n ,count +1, cost+graph[curr][i], bt);
				
				vizitat[i]= false;
			}
		}
	
		
		return bt;
	}
}
