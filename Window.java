
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;

/*
 * Glowna klasa rysujaca okno
 */
public class Window extends JFrame
{
	// panel na ktorym znajduje sie wykres
	Graph graph = new Graph();
	// panel zawierajacy kontrolki
	JPanel right =  new JPanel() ,controls = new JPanel(new GridBagLayout());
	// oznaczenia "przed" i "za" ekranem
	static String positiveDirection = new String("<html><h1>&#8857;</h1></html>");
	static String negativeDirection = new String("<html><h1>&#8855</h1></html>");
	// tekst informujacy o kierunku pola magnetycznego
	JLabel magFieldDirection = new JLabel(positiveDirection);
	// wartosc fluktuacji
	JLabel fluctuation = new JLabel("1.734");
	// suwak i spinner od regulacji natezenia zewnetrznego pola magnetycznego
	JSlider magFieldSlider = new JSlider(-1000, 1000, 0);
	JSpinner magFieldSpinner =  new JSpinner();
	// suwak i spinner od regulacji temperatury
	JSlider tempSlider = new JSlider(0, 300,100);
	JSpinner tempSpinner =  new JSpinner();
	// 18-krokowa regulacja zlozonosci wykresu
	JSlider sizeSlider = new JSlider(1,18,14);
	JLabel plotDimension = new JLabel("10000");
	
	GraphUpdater gu;
	
	public Window()
	{
		super();
		// obiekt aktualizujacy dane
		gu = new GraphUpdater(graph, this);
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
            public void run()
            {
            	createGUI();
            	gu.update();
            }
        });
	}
	
	public static void main(String[] args)
	{
		new Window();
	}
	
	public void createGUI()
	{
		// interfejs
		GridBagConstraints c = new GridBagConstraints();
		ModificationListener.SpinnerChange spinnerChange = new ModificationListener.SpinnerChange(this);
		ModificationListener.SliderChange sliderChange = new ModificationListener.SliderChange(this);
		
		setSize(800,600);
		setTitle("Fluktuacje magnetyzacji");
		setLayout(new BorderLayout());
		setResizable(false);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		c.gridx = 0;
		controls.add(new JLabel("Zwrot wektora H: "),c);
		
		c.gridx = 1;
		c.gridwidth = 2;
		controls.add(magFieldDirection,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		controls.add(new JLabel("Natężenie pola H [MT]:"),c);
		
		c.gridx = 2;
		c.gridwidth = 1;
		magFieldSpinner.setPreferredSize(new Dimension(30, 20));
		magFieldSpinner.addChangeListener(spinnerChange);
		controls.add(magFieldSpinner,c);

		magFieldSlider.setPaintLabels(true);
		magFieldSlider.setPaintTicks(true);
		magFieldSlider.setMajorTickSpacing(500);
		magFieldSlider.setMinorTickSpacing(100);
		magFieldSlider.setPreferredSize(new Dimension(290, 50));
		magFieldSlider.addChangeListener(sliderChange);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		controls.add(magFieldSlider,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		controls.add(new JLabel("Temperatura [K]:"),c);

		c.gridx = 2;
		c.gridwidth = 1;
		tempSpinner.setPreferredSize(new Dimension(40, 20));
		tempSpinner.addChangeListener(spinnerChange);
		controls.add(tempSpinner,c);
		
		tempSlider.setPaintLabels(true);
		tempSlider.setPaintTicks(true);
		tempSlider.setMajorTickSpacing(100);
		tempSlider.setMinorTickSpacing(20);
		tempSlider.setPreferredSize(new Dimension(290, 50));
		tempSlider.addChangeListener(sliderChange);
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 3;
		controls.add(tempSlider,c);

		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		controls.add(new JLabel("Rozmiar:"),c);
		
		c.gridx = 1;
		c.gridwidth = 2;
		plotDimension.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		controls.add(plotDimension,c);
		
		sizeSlider.setPaintLabels(false);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setMajorTickSpacing(3);
		sizeSlider.setMinorTickSpacing(1);
		sizeSlider.setPreferredSize(new Dimension(290, 50));
		sizeSlider.addChangeListener(sliderChange);
		
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 3;
		controls.add(sizeSlider,c);
		
		c.gridy = 7;
		c.gridwidth = 1;
		controls.add(new JLabel("<html>Fluktuacje &#963;<sub>M</sub><sup>2</sup>: </html>"),c);
		c.gridx = 1;
		controls.add(fluctuation,c);

		controls.setPreferredSize(new Dimension(300, 300));
		graph.setPreferredSize(new Dimension(600, 600));

		graph.setBackground(Color.WHITE);
		right.add(controls);
		add(right,BorderLayout.EAST);
		add(graph,BorderLayout.CENTER);
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
