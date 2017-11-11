package assignment8_Andre_Godinez;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Client extends JFrame{
	//Client variables
	private final String serverAddress = "192.168.6.233";
	private static int PORT = 9001;
	//client in/out streams that we init with serversocket handlers
	public BufferedReader in;
	public PrintWriter out;
	
	//GUI Variables
	private JFrame frame;
	private JTextField inputField;
	private JButton send;
	private JTextArea messages;
	private JLabel label;
	private Icon icon = new ImageIcon(new ImageIcon ("image1.jpg").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
	private JRadioButton setting;
	private JRadioButton image;
	private JButton upload;
	
	//constructor for client
	//
	//client with GUI
	public Client() {
		super("Chat Window");
		frame = this;
		Container c = getContentPane();
		
		messages = new JTextArea(30,30);
		
		JPanel inputsPanel1 = new JPanel();
		inputField = new JTextField(20);
		send = new JButton("Send");
		label = new JLabel(icon);
		inputsPanel1.add(label);
		inputsPanel1.add(inputField);
		inputsPanel1.add(send);
		
		
		JPanel inputsPanel2 = new JPanel();
		setting = new JRadioButton("Setting");
		image = new JRadioButton("Image");
		upload = new JButton("Upload");
		inputsPanel2.add(setting);
		inputsPanel2.add(image);
		inputsPanel2.add(upload);
		
		
		JPanel inputsPanel = new JPanel();
		inputsPanel.setLayout(new BorderLayout());
		inputsPanel.add(inputsPanel1,BorderLayout.NORTH);
		inputsPanel.add(inputsPanel2,BorderLayout.SOUTH);
		
		
		JPanel temp = new JPanel();
		temp.setLayout(new BorderLayout());
		temp.add(messages,BorderLayout.CENTER);
		temp.add(inputsPanel,BorderLayout.PAGE_END);
		
		temp.setBorder(new EmptyBorder(40, 40, 40, 40));
		
		c.add(temp);
		//need to set editable flase until we enter correct name
		messages.setEditable(false);
		inputField.setEditable(false);

		
		//add Listeners
		inputField.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				out.println(inputField.getText());
				inputField.setText("");
			}
		});
		
		send.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				out.println(inputField.getText());
				inputField.setText("");
			}
		});
		
		setting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				Color c=new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),r.nextInt(256));
				temp.setBackground(c);
			}
			
			
		});
		
		setSize(400,600);
		//pack();
	}
	
	// method for joption pop up to enter name
	private String getClientName() {
		return JOptionPane.showInputDialog(frame,
				"Enter Name: ",
				"",
				JOptionPane.PLAIN_MESSAGE);
	}
	
	//run method
	//client connects to server 
	//start loops of entering valid name
	//once succeeded add clients name and out printwriter to servers namelist and outlist
	
	private void run() throws IOException{
		//connect to socket and init streams that server made for this client
		Socket socket = new Socket(serverAddress,PORT);
	    in = new BufferedReader(new InputStreamReader(
	    socket.getInputStream()));
	    out = new PrintWriter(socket.getOutputStream(), true);
	    
	    //adding and reading messages to/from the server with message types
	    while (true) {
            String line = in.readLine();
            if (line.startsWith("SubmitName")) {
                out.println(getClientName());
                messages.append("Welcome to the Chat Room >.> \n");
                out.println("");
                
            } else if (line.startsWith("FinishSubmitName")) {
                inputField.setEditable(true);
                
            } else if (line.startsWith("Message")) {
                messages.append(line.substring(8) + "\n");
            }
            
         
        }
	    
	   
	}
	
	public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}
