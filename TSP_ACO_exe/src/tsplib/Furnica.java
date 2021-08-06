package tsplib;

public class Furnica {
	private double costTraseu;
	private int[] traseu;
	private boolean[] orasVizitat;
	
	private Utils utils;
	
	public Furnica(int marimeTraseu, Utils utils)
	{
		super();
		this.traseu = new int[marimeTraseu +1];
		this.orasVizitat = new boolean[marimeTraseu];
		this.utils = utils;
	}
	
	public void resetFurnica() {
		for(int i = 0; i < orasVizitat.length; i++) {
			orasVizitat[i] = false;
		}
	}
	
	public void pozitieRandomFurnica(int faza) {
		traseu[faza] = (int)(Math.random() * utils.marimeOrase());
		//System.out.println("pozitieFurnica: " + traseu[0]);
		orasVizitat[traseu[faza]]=true;
	}
	

	
	public double calcTurCelMaiApropiatVecin() {
		int i=0;
		resetFurnica();
		pozitieRandomFurnica(i);
		while(i < utils.marimeOrase() - 1)
		{
			i++;
			urmatoareaMiscare(i);
		}
		circuitComplet();
		resetFurnica();
		return this.costTraseu;
	}
	

	public void urmatoareaMiscare(int faza) {
		int orasulUrmator = utils.marimeOrase();
		int orasulCurent = traseu[faza - 1];
		double distantaMin = Double.MAX_VALUE;
		for(int i = 0; i < utils.marimeOrase(); i++)
		{
			if(!orasVizitat[i] && utils.cost(orasulCurent, i) < distantaMin)
			{
				orasulUrmator = i;
				distantaMin = utils.cost(orasulCurent, i);
			}
		}
		traseu[faza] = orasulUrmator;
		orasVizitat[orasulUrmator]=true;
		
	}
	
	public void circuitComplet() {
		traseu[utils.marimeOrase()] = traseu[0];
		costTraseu = calcTraseuCost();
	}
	
	public double calcTraseuCost() {
		double costTraseu = 0.0;

		for(int i=0; i<utils.marimeOrase(); i++) {
			costTraseu += utils.cost(traseu[i], traseu[i+1]);
		}
		return costTraseu;
	}


	public void celMaiApropiatVecin(int faza) {
		int oras;
		int orasulUrmator = utils.marimeOrase();
		int orasulCurent = this.traseu[faza -1];
		double bestRezultat = -1.0;
		double help;
		for(int i=0; i<utils.marimeListaVecini(); i++)
		{
			oras = utils.celMaiApropiatVecin(orasulCurent, i);
			if(!this.orasVizitat[oras]) {
				help = utils.costInfo(orasulCurent, oras);
				if(help > bestRezultat) {
					bestRezultat = help;
					orasulUrmator = oras;
				}
			}
		}
		if(orasulUrmator == utils.marimeOrase()) {
			urmatoareaMiscare(faza);
		} else {
			traseu[faza] = orasulUrmator;
			orasVizitat[this.traseu[faza]] = true;
		}
	}
	
	public void regulaDecizie(int faza) {
		int orasCurent = this.traseu[faza - 1];
		
		
		double sumProb = 0.0;

		double[] select = new double[utils.marimeListaVecini() +1];
		for(int i = 0; i < utils.marimeListaVecini(); i++)
		{
			if(orasVizitat[utils.celMaiApropiatVecin(orasCurent, i)])
			{
				select[i] = 0.0;
			} else {
				select[i] = utils.costInfo(orasCurent, utils.celMaiApropiatVecin(orasCurent, i));
				sumProb += select[i];
			}
		}
		if(sumProb <= 0)
		{
			urmatoareaMiscare(faza);
		} else {
			double random = Math.random() * sumProb ;
			int j=0;
			double prob = select[j];
			while((prob <= random) && (j<utils.marimeListaVecini())) {
				j++;
				prob += select[j];
			
			}
			if(j == utils.marimeListaVecini()) {
				celMaiApropiatVecin(faza);
				return;
			}
			traseu[faza] = utils.celMaiApropiatVecin(orasCurent, j);
			orasVizitat[this.traseu[faza]] = true;
		}
	}
	

	
	public int fazaRuta(int faza) {
		return traseu[faza];
	}
	
	public double costTur() {
		return costTraseu;
	}
	

	
	public int[] getTraseu() {
		return traseu;
	}

}
