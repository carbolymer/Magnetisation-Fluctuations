
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * Klasa odpowiedzialna za obslugiwanie zdarzen pochodzacych od interfejsu
 */
public class ModificationListener
{
	/*
	 * Podklasa obslugujaca zdarzenia na spinnerach
	 */
	public static class SpinnerChange implements ChangeListener
	{
		private Window frontend;
	
		public SpinnerChange(Window fe)
		{
			frontend = fe;
		}
	
		/*
		 * Aktualizacja sliderow
		 */
		public void stateChanged(ChangeEvent evt)
		{
			if(evt.getSource().getClass().getName() == "javax.swing.JSpinner")
			{
				JSpinner spinner = (JSpinner) evt.getSource();
				JSlider slider;
				int value;
				if(spinner == frontend.magFieldSpinner)
				{
					slider = frontend.magFieldSlider;
					if(Float.parseFloat(spinner.getValue().toString()) >= 0)
						frontend.magFieldDirection.setText(Window.positiveDirection);
					else
						frontend.magFieldDirection.setText(Window.negativeDirection);
				}
				else if(spinner == frontend.tempSpinner)
					slider = frontend.tempSlider;
				else 
					return;
				value = (int)(Float.parseFloat(spinner.getValue().toString().replace(",", ".").replace(" ","")));
				if (value > slider.getMaximum())
					value = slider.getMaximum();
				else if(value < slider.getMinimum())
					value = slider.getMinimum();
				slider.setValue(value);
				spinner.setValue(value);
				frontend.gu.update();
			}
		}
	};
	/*
	 * Klasa obslugujaca zdarzenia ze sliderow
	 */
	public static class SliderChange implements ChangeListener
	{

		private Window frontend;

		public SliderChange(Window fe)
		{
			frontend = fe;
		}
		/*
		 * Aktualizacja spinnerow
		 */
		public void stateChanged(ChangeEvent evt)
		{
			if(evt.getSource().getClass().getName() == "javax.swing.JSlider")
			{
				JSpinner spinner;
				JSlider slider = (JSlider) evt.getSource();
				if(slider == frontend.magFieldSlider)
				{
					spinner = frontend.magFieldSpinner;
					if(slider.getValue() >= 0)
						frontend.magFieldDirection.setText(Window.positiveDirection);
					else
						frontend.magFieldDirection.setText(Window.negativeDirection);
				}
				else if(slider == frontend.tempSlider)
					spinner = frontend.tempSpinner;
				else if(slider == frontend.sizeSlider)
				{
					int n[] = {2,3,4,5,8,10,15,20,30,40,50,60,75,100,120,150,300,600};
					int i = slider.getValue()-1;
					
					frontend.plotDimension.setText(Integer.toString(n[i]*n[i]));
					frontend.gu.update();
					return;
				}
				else
					return;
				spinner.setValue(slider.getValue());
				frontend.gu.update();
			}
		}
	};
}