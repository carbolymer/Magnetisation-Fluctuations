/*
 * Klasa odpowiedzialna za wyliczanie wartosci spinow w poszczegolnych miejscach wykresu
 */
public class GraphUpdater
{
	private Graph graph;
	private Window window;
	private static double mub = 9.27e-24; 			// magneton bohra
	private static double kb = 1.38e-23;			// stala boltzmana
	private static double mu0 = 1.2566e-6;			// przenikalnosc magnetyczna prozni
	
	public GraphUpdater(Graph g, Window w)
	{
		graph = g;
		window = w;
	}
	
	public void update()
	{
		// wymiar obszaru wykresu, ilosc punktow: n*n
		int n = (int) Math.sqrt(Double.parseDouble(window.plotDimension.getText()));
		// natezenie pola
		int H = window.magFieldSlider.getValue();
		// temperatura
		int T = window.tempSlider.getValue();
		// wartosc oczekiwana spinu
		double es = Math.tanh(mu0*mub*H*1e6/kb/T);
		// parametr alfa
		double alpha = (es+1)/2;
		
		graph.table = new int[n][n];
		for(int i=0;i<n;++i)
			for(int j=0;j<n;++j)
				if(Math.random() < alpha)
					graph.table[i][j] = 1;
				else
					graph.table[i][j] = -1;
		System.out.println("H: "+ H +"\tT: " + T +"\tes: "+ es + "\ta: "+alpha);
		graph.repaint();
	}
}
