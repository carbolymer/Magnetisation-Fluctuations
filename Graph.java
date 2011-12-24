import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
/*
 * Klasa odpowiedzialna za rysowanie wykresu rozkladu spinow
 */
public class Graph extends JPanel
{
	public int table[][] = null;
	public void paintComponent(Graphics g)
	{
		int width = getWidth();
		int height = getHeight();
		int n = table.length;
		super.paintComponent(g);

		if(table == null)
			return;
		// wymiary pojedynczego kwadratu dx*dy
		int dx = (int) Math.round(width/n);
		int dy = (int) Math.round(height/n);

		g.setColor(Color.BLACK);
		
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < n; ++j)
				if(table[i][j] == -1)
					g.fillRect(i*dx,j*dy,dx,dy);
	} 
}
