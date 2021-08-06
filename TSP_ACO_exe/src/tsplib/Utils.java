package tsplib;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class Utils {
	
	private double rutaInitiala;
	private double[][] graph;
	private int[][] matriceVecini;
	private double[][] feromon;
	private double[][] infoOrase;
	
	private Furnica[] furnici;
	private Furnica[] furnicitop5;
	private Result result;
	public Furnica ceaMaiBunaFurnica;
	public Furnica a2aCeaMaiBunaFurnica;
	
	public Utils (double[][] graph) {
		super();
		this.graph = graph;
	}
	
	public void BackTracking() {
	int n = graph.length;
	boolean[] vizitat = new boolean[n];
	vizitat[0] = true ;
	double bt = Integer.MAX_VALUE;

	bt = TSPBackTracking.tsp(graph, vizitat, 0, n, 1, 0, bt);

	System.out.println(bt);
	}
	
	
	public void genereazaListaOraseVecine() {
		matriceVecini = new int[marimeOrase()][marimeListaVecini()];
		
		for(int i=0; i< marimeOrase(); i++)
		{
			Integer[] orasIndex = new Integer[marimeOrase()];
			Double[] orasData = new Double[marimeOrase()];
			for(int j = 0; j<marimeOrase(); j++)
			{
				orasIndex[j] = j;
				orasData[j] = cost(i, j);
			}
			orasData[i] = Collections.max(Arrays.asList(orasData));
			Arrays.sort(orasIndex, new Comparator<Integer>() {
				public int compare(final Integer o1, final Integer o2) {
					return Double.compare(orasData[o1], orasData[o2]);
				}
			});
			for(int r=0; r<marimeListaVecini();r++) {
				matriceVecini[i][r] = orasIndex[r];
			}
		}
		

	}
	
	public void genereazaPopulatieFurnici() {
		furnici = new Furnica[nrFurnici()];
		for(int k = 0; k < nrFurnici(); k++)
		{
			furnici[k] = new Furnica(marimeOrase(), this);
			//System.out.println(furnici[k]);
		}
	}
	
	public void genereazaMediu() {
		feromon = new double[marimeOrase()][marimeOrase()];
		infoOrase = new double[marimeOrase()][marimeOrase()];
		
		rutaInitiala = 1.0 / (Parametrii.rho * furnici[0].calcTurCelMaiApropiatVecin());
		
		System.out.println("S-au setat parametrii: " + Parametrii.nrFurnici);
		
		for(int i = 0; i < marimeOrase(); i++)
		{
			for(int j = i;  j < marimeOrase(); j++)
			{
				feromon[i][j] = rutaInitiala;
				feromon[j][i] = rutaInitiala;
				infoOrase[i][j] = rutaInitiala;
				infoOrase[i][j] = rutaInitiala;
			}
		}
		formulaParam();
	}
	
	public void formulaParam() {
		for(int i = 0; i < marimeOrase(); i++)
		{
			for(int j = 0; j < i; j++)
			{
				double euristica = (1.0 / (cost(i,j) + 0.1));
				infoOrase[i][j] = Math.pow(feromon[i][j], Parametrii.alpha) * Math.pow(euristica, Parametrii.beta);
				infoOrase[j][i] = infoOrase[i][j];
			}
		}
	}
	
	public void construiesteSolutia() {
		int faza = 0;
		for(int i = 0;  i < nrFurnici(); i++)
		{
			furnici[i].resetFurnica();
			furnici[i].pozitieRandomFurnica(faza);
		}
		
		while(faza < marimeOrase() - 1) {
			faza++;
			for(int i = 0; i < nrFurnici(); i++)
			{
				furnici[i].regulaDecizie(faza);
			}

		}
		for(int i = 0; i < nrFurnici(); i++)
		{
			furnici[i].circuitComplet();
		}
	}
	
	public void actualizareFeromon() {
		evaporareFeromon();
		for(int i = 0; i < nrFurnici(); i++)
		{
			depuneFeromon(furnici[i]);
		}
		formulaParam();
		
	}
	
	public void evaporareFeromon() {
		for(int i=0; i < marimeOrase(); i++)
		{
			for(int j=i; j< marimeOrase(); j++)
			{
				feromon[i][j] = (1-Parametrii.rho) * feromon[i][j];
				feromon[j][i] = feromon[i][j];
			}
		}
	}
	
	public void depuneFeromon(Furnica furnica) {
		double x = 1.0 / furnica.costTur();
		for(int i =0; i<marimeOrase(); i++)
		{
			int j = furnica.fazaRuta(i);
			int k = furnica.fazaRuta(i+1);
			feromon[j][k] = feromon[j][k] + x;
			feromon[k][j] = feromon[j][k];
		}
		
	}
	
	public void depuneFeromonRB(Furnica furnica) {
		double min = Double.MAX_VALUE;
		int counter;
		furnicitop5 = new Furnica[5];
		
		int helper=0;
		for(Furnica furnica1 : returnFurnici()) {
			
			if(furnica1.costTur() < min) {
				a2aCeaMaiBunaFurnica = furnica1;
				min = furnica1.costTur();
				ceaMaiBunaFurnica = furnica1;
				furnicitop5[helper] = furnica1;
				helper++;
				if(helper > 4) {
					helper = 0;
				}
			}
		}

		counter = 0;
		for(int i=0; i<furnicitop5.length; i++)
		{
			if(furnicitop5[i] != null)
			{
				counter++;
			}
		}
		for(int i=0; i<counter ; i++) {
			furnica = furnicitop5[i];
			
			double x = 5*(0.0005 / furnica.costTur()) ;
			for(int o =0; o<marimeOrase(); o++)
			{
				int j = furnica.fazaRuta(o);
				int k = furnica.fazaRuta(o+1);
				feromon[j][k] = feromon[j][k] + x;
				feromon[k][j] = feromon[j][k];
			}
		}

		/*furnica = a2aCeaMaiBunaFurnica;
		x = 5*(0.0005 / furnica.costTur()) ;
		for(int i =0; i<marimeOrase(); i++)
		{
			int j = furnica.fazaRuta(i);
			int k = furnica.fazaRuta(i+1);
			feromon[j][k] = feromon[j][k] + x;
			feromon[k][j] = feromon[j][k];
			
		}
		*/
		
		
		
	}
	
	public int marimeOrase() {
		return graph.length;
	}
	
	public int marimeListaVecini() {
		return Parametrii.maxListVecini;
	}
	
	public double cost(int i, int j) {
		return graph[i][j];
	}
	
	public int nrFurnici() {
		return Parametrii.nrFurnici;
	}
	
	public int celMaiApropiatVecin(int i, int j) {
		return this.matriceVecini[i][j];
	}
	
	public Furnica[] returnFurnici() {
		return furnici;
	}
	
	public double costInfo(int i, int j) {
		return infoOrase[i][j];
	}

	public void list() {
		for(int i=0; i<marimeOrase()-1; i++) {
			System.out.println("DIstanta orase :" + graph[i][i+1]);
		}		
	}
	
	public void solutiaRankBased() {
		int faza = 0;
		for(int i = 0;  i < nrFurnici(); i++)
		{
			furnici[i].resetFurnica();
			furnici[i].pozitieRandomFurnica(faza);
		}
		
		while(faza < marimeOrase() - 1) {
			faza++;
			for(int i = 0; i < nrFurnici(); i++)
			{
				furnici[i].regulaDecizie(faza);
			}

		}
		for(int i = 0; i < nrFurnici(); i++)
		{
			furnici[i].circuitComplet();
		}
	}
	
	public void actualizeazaFermonRankBased() {
		evaporareFeromon();
		for(int i = 0; i < nrFurnici(); i++)
		{
			depuneFeromonRB(furnici[i]);
		}
		formulaParam();
	}

}
