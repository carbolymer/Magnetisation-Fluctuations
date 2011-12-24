
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ModificationListener
{
	public static class SpinnerChange implements ChangeListener
	{
		private Window frontend;
	
		public SpinnerChange(Window fe)
		{
			frontend = fe;
		}
	
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
						frontend.magFieldDirection.setText(frontend.positiveDirection);
					else
						frontend.magFieldDirection.setText(frontend.negativeDirection);
				}
				else if(spinner == frontend.tempSpinner)
					slider = frontend.tempSlider;
				else 
					return;
				value = (int)(Float.parseFloat(spinner.getValue().toString().replace(",", ".").replace(" ","")));
				if (value > slider.getMaximum()) // TODO zbadac sens ograniczania wartosci do maximum suwakow
					value = slider.getMaximum();
				else if(value < slider.getMinimum())
					value = slider.getMinimum();
				slider.setValue(value);
				spinner.setValue(value);
			}
		}
	};
	
	public static class SliderChange implements ChangeListener
	{

		private Window frontend;

		public SliderChange(Window fe)
		{
			frontend = fe;
		}
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
						frontend.magFieldDirection.setText(frontend.positiveDirection);
					else
						frontend.magFieldDirection.setText(frontend.negativeDirection);
				}
				else if(slider == frontend.tempSlider)
					spinner = frontend.tempSpinner;
				else if(slider == frontend.sizeSlider)
				{
					frontend.plotDimension.setText(Integer.toString(slider.getValue()*slider.getValue()));
					return;
				}
				else
					return;
				spinner.setValue(slider.getValue());
			}
		}
	};
	
	public static void update()
	{
		;
	}
}