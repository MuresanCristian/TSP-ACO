package tsplib;

public class Result {
	
	private Utils utils;
	
	private double celMaiMicCost = Double.MAX_VALUE;
	
	private int [] celMaiBunTraseu;
	
	private String tspFile;
	
	public 	String cloneResult ;
	
	public Furnica ceaMaiBunaFurnica;
	

	
	private Visual grafic;
	
	public Result(String tspFile, Utils utils, double[][] coord)
	{
		this.utils = utils;
		this.tspFile= tspFile;
		this.grafic = new Visual(coord);
	}
	
	public void calculeazaResult(int faza) {
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		double total = 0.0;
		
		ceaMaiBunaFurnica = null;
		Parametrii param = new Parametrii();

		
		for(Furnica furnica : utils.returnFurnici()) {
			if(furnica.costTur() < min) {
				min = furnica.costTur();
				ceaMaiBunaFurnica = furnica;
			}
			if(furnica.costTur() > max)
			{
				max = furnica.costTur();
			}
			
			total += furnica.costTur();
			
		}
		
		if(min < celMaiMicCost)
		{
			celMaiMicCost = min;
			celMaiBunTraseu = ceaMaiBunaFurnica.getTraseu().clone();
			
			String print = "(" + celMaiBunTraseu[0];
			for(int i = 1; i<celMaiBunTraseu.length -1; i++)
			{
				print += "->" + celMaiBunTraseu[i];
			}
			print += ")";
			param.setResult(print);
			param.setDistantaFinala(ceaMaiBunaFurnica.calcTraseuCost());
			param.setTur(celMaiBunTraseu);
			grafic.draw(celMaiBunTraseu);

			
			System.out.println(ceaMaiBunaFurnica.calcTraseuCost());
			try {Thread.sleep(1000);} catch (Exception ex) {}
		}
		
		
	}

}
