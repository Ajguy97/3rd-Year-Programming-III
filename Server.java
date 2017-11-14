package assignment8_Andre_Godinez;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;



public class Server extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//port the server listens on
	private static int PORT = 9001;
	//arraylist to store all names of clients 
	//need to make sure to check arraylist before adding the name
	// -> no duplicates
	private static ArrayList<String> nameList = new ArrayList<String>();
	//array of printwriters per client
	private static ArrayList<PrintWriter> writerList = new ArrayList<PrintWriter>();
	
	//for uploading
	private static String userdir = System.getProperty("user.dir");
	
	//for number of active windows
	static int numWindows = 0;
	
	//GUI variables
	public static JTextArea messages = new JTextArea(8,60);
	private JFrame frame;
	

	//main method
	//listens on port and spawn handler threads
	public static void main(String args[]) throws Exception{
		Server s = new Server();
		s.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.frame.setVisible(true);
		s.frame.setSize(400,600);
		s.runServer(PORT);
	}
	
	public Server (){
		super("Server Window");
		frame = this;
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c,BoxLayout.PAGE_AXIS));
		
		JPanel messagePane = new JPanel();
		messagePane.setLayout(new BoxLayout(messagePane,BoxLayout.LINE_AXIS));
		
		messagePane.add(Box.createRigidArea(new Dimension(10,10)));
		messagePane.add(messages);
		messagePane.add(Box.createRigidArea(new Dimension(10,10)));
		messages.setEditable(false);
		messages.setPreferredSize(new Dimension(400, 400));
		
		c.add(Box.createRigidArea(new Dimension(10,10)));
		c.add(messagePane);
		c.add(Box.createRigidArea(new Dimension(400,100)));
		
		messagePane.setBackground(Color.GRAY);
		c.setBackground(Color.GRAY);
		messages.append("Server Started. \n" );
		setSize(400,600);
	}
	
	public void runServer(int port) throws Exception {
		
		ServerSocket listener = new ServerSocket(port);
		
		try {
			while(true) {
				new Handler(listener.accept()).start();
				numWindows++;
				System.out.println(numWindows);
			}
		}finally {
			listener.close();
		}
		
	}
	
	
	
	//handler thread class 
	//deals with clients and broadcasting its messages
	
	private static class Handler extends Thread{
		//clients name
		private String name;
		//socket client is connecting to
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		
		//constructor 
		public Handler(Socket s) {
			socket = s;
		}
		//image upload byte arrays
		private InputStream is;
		byte[] imageArray;
		BufferedImage image;
		
		//handler's run method
		//Handler asks client for a suitable name ie. not already in static arraylist
		//adds a new output stream for client 
		//while server is running or client closes
		//keep accepting messages from client and broadcast them to the server
		
	public void run() {
		is = null;
		try {
			//reading and writing to a socket
			//we get new input and output streams for this client
			in = new BufferedReader(new InputStreamReader
					(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			
			is = socket.getInputStream();
			
			while(true) {
				
				//writing to client that we're in submitting name stage
				out.println("SubmitName");
				name = in.readLine();
				//if name is null return -> start of loop again
				if(name == null) {
					return;
				}
				//adding to an arraylist is a critical section 
				//so this needs to be synchronized
				//at this stage name is not null
				//start adding the name
				synchronized(nameList) {
					if(!nameList.contains(name)) {
						
					
						nameList.add(name);
						break;
					}
				}
			}//end while loop
		
			//correct name submitted 
			//we can add this clients outputstream to list of writers
			//client can now add and broadcast messages
			out.println("FinishSubmitName");
			writerList.add(out);
			String address = in.readLine();
			if(address.startsWith("/")) {
				messages.append(address + " has connected. \n");
			}
			
			
			//while loop for adding messages and broadcasting them
			while(true) {
			
				String input = in.readLine();
				if(input != null) {
					
					if(input.startsWith("StoreImage")){
						//store the image into variable but dont save into server yet
						imageArray = new byte[50000000]; //max image 50 megabytes for this server 
														//or else bytes are going to be read with 
						is.read(imageArray);
						image = ImageIO.read(new ByteArrayInputStream(imageArray));
						
					}
					else if(input.startsWith("Upload")) {
						//upload button pressed
						//save image stored into server
							messages.append("Received " + image.getHeight() + "x" + image.getWidth() + "\n");
							ImageIO.write(image, "jpg", new File("image"+System.currentTimeMillis()+".jpg"));
							
							//Setting imageArray and image as null so we can upload new images
							imageArray = null;
							image = null;
					}
					
					else {
						for(PrintWriter writer: writerList) {
							writer.println("Message "+ name + ": " + input);
						}
					}
				}
				
				else {
					return;
				}
			}
		}catch(IOException e) {
			System.out.println(e);
		
			
		}finally {
			if(name!=null) {
				nameList.remove(name);
			}
			if(out!=null) {
				writerList.remove(out);
			}
			try {
				socket.close();
			}catch (IOException e) {}
			}
		}
		
	}
		
	}