package tsplib;

public class Parametrii {
	public static double rho = 0.50; //Rata evaporare feromon
	public static double alpha = 1.0; //Importanta feromonului
	public static double beta = 2.0; //Importanta euristica
	public static int nrFurnici = 15; //Numarul de furnici utilizate
	public static int nrIt = 1000; //Numarul de iteratii
	public static int maxListVecini = 5; //Numarul de vecini pentru fiecare oras
	public static String result;
	public static double distantaFinala;
	public static int[] tur;
	
	public void setResult(String result) {
		Parametrii.result = result;
	}
	public String getResult() {
		return Parametrii.result;
	}
	public void setRHO(double rho) {
		Parametrii.rho = rho;
	}
	public void setAlpha(double alpha) {
		Parametrii.alpha = alpha;
	}
	public void setBeta(double beta) {
		Parametrii.beta = beta;
	}
	public void setNrFurnici(int nrFurnici) {
		Parametrii.nrFurnici = nrFurnici;
	}
	public void setNrIT(int nrIt) {
		Parametrii.nrIt = nrIt;
	}
	public void setMaxListVecini(int maxListVecini) {
		Parametrii.maxListVecini = maxListVecini;
	}
	public double getRHO() {
		return Parametrii.rho;
	}
	public double getAlpha() {
		return Parametrii.alpha;
	}
	public double getBeta() {
		return Parametrii.beta;
	}
	public int getNrIt() {
		return Parametrii.nrIt;
	}
	public int getNrFurnici() {
		return Parametrii.nrFurnici;
	}
	public int getNN() {
		return Parametrii.maxListVecini;
	}
	public void setDistantaFinala(double distantaFinala) {
		Parametrii.distantaFinala = distantaFinala;
	}
	public double getDistantaFinala() {
		return Parametrii.distantaFinala;
	}
	public int[] getTur() {
		return Parametrii.tur;
	}
	public void setTur(int[] tur) {
		Parametrii.tur = tur;
	}
}

