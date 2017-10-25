package assignment6_Andre_Godinez;

//Java core packages
import java.awt.*;
import java.awt.event.*;

// Java extension packages
import javax.swing.*;

public class Slideshow extends JFrame {
	private JComboBox imagesComboBox;
	private JLabel label;
	private JButton animalButton,flowerButton,foodButton;
	private JPanel buttonPanel;
	Timer timer;
	private int counter = 0;
	private int selected = 0;
	
	private String names[] =
		{"Animal", "Flower","Food"};
	
	private Icon icons[][] = {
	    {new ImageIcon(new ImageIcon("animal1.jpg").getImage().getScaledInstance(550, 400, Image.SCALE_DEFAULT)),
			new ImageIcon(new ImageIcon("animal2.jpg").getImage().getScaledInstance(550, 400, Image.SCALE_DEFAULT)),
			new ImageIcon(new ImageIcon("animal3.jpg").getImage().getScaledInstance(550, 400, Image.SCALE_DEFAULT))},
		{new ImageIcon(new ImageIcon("flowers1.jpg").getImage().getScaledInstance(550, 400, Image.SCALE_DEFAULT)),
			new ImageIcon(new ImageIcon("flowers2.jpg").getImage().getScaledInstance(550, 400, Image.SCALE_DEFAULT)),
			new ImageIcon(new ImageIcon("flowers3.jpg").getImage().getScaledInstance(550, 400, Image.SCALE_DEFAULT))},
		{new ImageIcon(new ImageIcon("food1.jpg").getImage().getScaledInstance(550, 400, Image.SCALE_DEFAULT)),
			new ImageIcon(new ImageIcon("food2.jpg").getImage().getScaledInstance(550, 400, Image.SCALE_DEFAULT)),
			new ImageIcon(new ImageIcon("food3.jpg").getImage().getScaledInstance(550, 400, Image.SCALE_DEFAULT))}
	};
	
//setup gui
	public Slideshow() {
		super("Assignment 6");
		
		Container c = getContentPane();
		c.setBackground(Color.black);
		
		
		JPanel cbPanel = new JPanel();
		cbPanel.setLayout(new FlowLayout());
	
		imagesComboBox = new JComboBox(names);
		imagesComboBox.setMaximumRowCount(3);
		
		cbPanel.add(imagesComboBox, new FlowLayout());
		c.add(cbPanel, BorderLayout.PAGE_START);
		
		animalButton = new JButton("Animal Background Colour");
		flowerButton = new JButton("Flower Background Colour");
		foodButton = new JButton("Food Background Colour");
		
		
		animalButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.setBackground(Color.CYAN);
				}
			});
		
		flowerButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						c.setBackground(Color.RED);
					}
				});
		
		foodButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						c.setBackground(Color.YELLOW);
					}
				});
		
		imagesComboBox.addItemListener(
		new ItemListener() {
			public void itemStateChanged(ItemEvent e) 
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					
					selected = imagesComboBox.getSelectedIndex();
					counter = 0;
					c.repaint();
				}
			}
		} //end anonymous innerclass
        );// end call to additemlistener
		
		
		
		
		
		//setup jlabel to display image icons
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				label.setIcon(icons[selected][counter]);
				counter++;
				
				if(counter>2)
					counter=0;
				
				c.repaint();
			}
		});
		
		timer.start();
		
		label = new JLabel(icons[0][counter]);
		c.add(label);
		
		buttonPanel = new JPanel();
		buttonPanel.add(animalButton);
		buttonPanel.add(flowerButton);
		buttonPanel.add(foodButton);
		c.add(buttonPanel,BorderLayout.SOUTH);
			
		setSize(600,600);
		setVisible(true);
}
	
public static void main(String args[]) {
	//creating slideshow class
	Slideshow ss = new Slideshow();
	
	
	System.out.println(System.getProperty("user.dir"));
	ss.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}	
	
}

