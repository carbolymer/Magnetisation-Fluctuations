/*
 * Klasa odpowiedzialna za wyliczanie wartosci spinow w poszczegolnych miejscach wykresu
 */
public class GraphUpdater
{
	private Graph graph;
	private Window window;
//	private static double mub = 9.27e-24; 			// magneton bohra
//	private static double kb = 1.38e-23;			// stala boltzmana
//	private static double mu0 = 1.2566e-6;			// przenikalnosc magnetyczna prozni
	
	// dla wygody prezentacji danych zostaly przyjete nastepujace wartosci
	private static double mub = 1; 			// magneton bohra
	private static double kb = 1;			// stala boltzmana
	private static double mu0 = 1;			// przenikalnosc magnetyczna prozni
	
	public GraphUpdater(Graph g, Window w)
	{
		graph = g;
		window = w;
	}
	
	public void update()
	{
		double 
			ms = 0,	// rzeczywista srednia wartosc spinu
			es = 0, // teoretyczna srednia wartosc spinu
			fl = 0,	// fluktuacja
			M = 0,	// magnetyzacja
			rfl = 0;// wzgledna fluktuacja
		// wymiar obszaru wykresu, ilosc punktow: n*n
		int n = (int) Math.sqrt(Double.parseDouble(window.plotDimension.getText()));
		// ilosc czastek
		int N = n*n;
		// natezenie pola
		int H = window.magFieldSlider.getValue();
		// temperatura
		int T = window.tempSlider.getValue();
		// licznik dodatnich spinow
		int positive = 0;
		// wartosc oczekiwana spinu
		if(T == 0 && H == 0)
			es = 0;
		else if(T == 0)
		{
			if(H > 0)
				es = 1;
			else
				es = -1;
		}
		else
			es = Math.tanh(mu0*mub*H/kb/T);
		// parametr alfa
		double alpha = (es+1)/2;
		
		graph.table = new int[n][n];
		for(int i=0;i<n;++i)
			for(int j=0;j<n;++j)
				if(Math.random() < alpha)
				{
					graph.table[i][j] = 1;
					++positive;
				}
				else
					graph.table[i][j] = -1;
		ms = 2.0*positive/N-1;
		fl = mu0*mu0*mub*mub*N*(1-ms*ms);
		M = mu0*mub*N*ms;
		rfl = Math.sqrt(fl)/M;
		System.out.println("H: "+ H +"\tT: " + T +"\tes: "+ es + "\tms: "+ ms + "\tpositive: "+ positive +"\ta: "+alpha +"\tN: "+ N);
		graph.repaint();
		window.fluctuation.setText(Double.toString(round(fl,4)));
		window.magnetisation.setText(Integer.toString((int)round(M,0)));
		if(M != 0)
			window.relativeFluctuation.setText(Double.toString(round(rfl,4)));
		else
			window.relativeFluctuation.setText("<html>&infin;</html>");
	}

	/*
	 * Zaokraglanie liczb
	 */
	public static double round(double number, int decimalPlaces)
	{
		double modifier = Math.pow(10.0, decimalPlaces);
		return Math.round(number * modifier) / modifier;
	}
}
