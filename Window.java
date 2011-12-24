
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

public class Window extends JFrame
{
	JPanel graph = new Graph(), right =  new JPanel() ,controls = new JPanel(new GridBagLayout());
	String positiveDirection = new String("<html><h1>&#8857;</h1></html>");
	String negativeDirection = new String("<html><h1>&#8855</h1></html>");
	JLabel magFieldDirection = new JLabel(positiveDirection);
	JLabel fluctuation = new JLabel("1.734");
	JSlider magFieldSlider = new JSlider(-10, 10, 3);
	JSpinner magFieldSpinner =  new JSpinner();

	JSlider tempSlider = new JSlider(0, 300,100);
	JSpinner tempSpinner =  new JSpinner();
	
	JSlider sizeSlider = new JSlider(1,200,100);
	JLabel plotDimension = new JLabel("10000");
	
	public Window()
	{
		super();
		createGUI();
	}
	
	public static void main(String[] args)
	{
		new Window();
	}
	
	public void createGUI()
	{
		GridBagConstraints c = new GridBagConstraints();
		
		setSize(800,700);
		setTitle("Fluktuacje magnetyzacji");
		setLayout(new BorderLayout());
		
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
		controls.add(new JLabel("Natężenie pola H:"),c);
		
		c.gridx = 2;
		c.gridwidth = 1;
		magFieldSpinner.setPreferredSize(new Dimension(30, 20));
		magFieldSpinner.addChangeListener(new ModificationListener.SpinnerChange(this));
		controls.add(magFieldSpinner,c);

		magFieldSlider.setPaintLabels(true);
		magFieldSlider.setPaintTicks(true);
		magFieldSlider.setMajorTickSpacing(5);
		magFieldSlider.setMinorTickSpacing(1);
		magFieldSlider.setPreferredSize(new Dimension(290, 50));
		magFieldSlider.addChangeListener(new ModificationListener.SliderChange(this));
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		controls.add(magFieldSlider,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		controls.add(new JLabel("Temperatura:"),c);

		c.gridx = 2;
		c.gridwidth = 1;
		tempSpinner.setPreferredSize(new Dimension(40, 20));
		tempSpinner.addChangeListener(new ModificationListener.SpinnerChange(this));
		controls.add(tempSpinner,c);
		
		tempSlider.setPaintLabels(true);
		tempSlider.setPaintTicks(true);
		tempSlider.setMajorTickSpacing(100);
		tempSlider.setMinorTickSpacing(25);
		tempSlider.setPreferredSize(new Dimension(290, 50));
		tempSlider.addChangeListener(new ModificationListener.SliderChange(this));
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
		sizeSlider.setMajorTickSpacing(50);
		sizeSlider.setMinorTickSpacing(10);
		sizeSlider.setPreferredSize(new Dimension(290, 50));
		sizeSlider.addChangeListener(new ModificationListener.SliderChange(this));
		
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
