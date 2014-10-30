package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class DataPanel extends JPanel {
	private Dimension size;
	private Font buttonFont;
	private Border panelBorder;

	public DataPanel() {
		buttonFont = new Font(Font.SERIF, Font.PLAIN, 16);
		panelBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLUE);
		setPreferredSize(new Dimension(700, 550));
		setLayout(null);
		setBackground(new Color(230, 230, 250));
		setBorder(panelBorder);
	}
	
	public void drawInitialSetup() {
		JLabel label = new JLabel("HELLO");
		label.setFont(buttonFont);
		label.setForeground(Color.BLUE);
		add(label);
		
		size = label.getPreferredSize();
		label.setBounds(100, 100, size.width, size.height);
	}
}
