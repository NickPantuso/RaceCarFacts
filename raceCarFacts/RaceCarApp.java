package raceCarFacts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

/**
 * Application for RaceCarFacts Project.
 * Allows the user to learn about 6 different race cars, 3 being rally race cars and 3 being track race cars.
 * The GUI will display page containig a picture of the car, some simple facts, and a description.
 * The user can choose whether or not the GUI will display rally cars or track cars by sing the top two buttons.
 * The next car button will change the page to the next car in the List, respective to which type of car is selected via the top buttons.
 * The user can also save the facts and description of the displayed car to their own file using the save button.
 * 
 * @author Nick Pantuso
 *
 */
public class RaceCarApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int index = 0;
	private JLabel image = new JLabel("");
	private JLabel desc = new JLabel("");
	private JLabel insertFacts = new JLabel("");
	private String line[] = new String[6];
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RaceCarApp frame = new RaceCarApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RaceCarApp() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		readFile();
		List<RallyCar> rallyCars = makeRallyCars();
		List<TrackCar> trackCars = makeTrackCars();
		
		JRadioButton rallyRdbtn = new JRadioButton("Rally Cars");
		JRadioButton trackRdbtn = new JRadioButton("Track Cars");
		JPanel selectCarType = new JPanel();
		createSelectCarType(selectCarType, rallyRdbtn, trackRdbtn);
		
		image.setIcon(new ImageIcon(RaceCarApp.class.getResource(rallyCars.get(0).getImage())));
		insertFacts.setText(rallyCars.get(0).toString());
		JPanel imagePanel = new JPanel();
		createImagePanel(imagePanel, image, insertFacts);
		
		JButton nextButton = new JButton("Next Car");
		JButton saveButton = new JButton("Save Facts & Description");
		desc.setText("<html>" + rallyCars.get(0).getDesc() + "</html>");
		JPanel descPanel = new JPanel();
		createDescPanel(descPanel, nextButton, saveButton, desc);
		
		//if nextButton is clicked, change the car on screen according to which type of car is selected
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					if(rallyRdbtn.isSelected()) {
						changeRallyInfo(rallyCars);
					} else if(trackRdbtn.isSelected()) {
						changeTrackInfo(trackCars);
					}
				}
			});
		
		//if saveButton is clicked, save the facts and description of the car to a file
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					File file = new File("save.txt");
					try(FileOutputStream fos = new FileOutputStream(file, true);
							OutputStreamWriter out = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {
						String saveContent;
						saveContent = insertFacts.getText();
						saveContent = saveContent.replace("<br>", ", ");
						saveContent = saveContent.replace("<html>", "");
						saveContent = saveContent.replace("</html>", "");
						out.write(saveContent + "\n");
						out.flush();
						saveContent = desc.getText();
						saveContent = saveContent.replace("<html>", "");
						saveContent = saveContent.replace("</html>", "");
						out.write(saveContent + "\n");
						out.flush();
						out.close();
					} catch (IOException e1) {
						System.out.println("Oops, we encountered a problem regarding saveDoc.txt.");
						e1.printStackTrace();
					}
				}
			});
		
		}
	
	/**
	 * Creates the JLabel for the image, as well as the JPanel that contains the facts about the car.
	 * 
	 * @param imagePanel
	 * @param image
	 * @param insertFacts
	 */
	private void createImagePanel(JPanel imagePanel, JLabel image, JLabel insertFacts) {
		
		imagePanel.setBackground(Color.BLACK);
		imagePanel.setLayout(new BorderLayout(0,0));
		imagePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		image.setBorder(new EmptyBorder(0, 0, 0, 15));
		
		JPanel factsPanel = new JPanel();
		factsPanel.setPreferredSize(new Dimension(200, 350));
		factsPanel.setLayout(new BorderLayout(0,0));
		factsPanel.setBackground(Color.WHITE);
		
		JLabel factsLabel = new JLabel("Facts:");
		factsLabel.setBorder(new EmptyBorder(5, 5, 0, 0));
		factsLabel.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 18));
		factsLabel.setForeground(Color.BLACK);
		
		insertFacts.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 15));
		insertFacts.setForeground(Color.BLACK);
		insertFacts.setBorder(new EmptyBorder(10, 5, 0, 0));
		insertFacts.setVerticalAlignment(SwingConstants.TOP);
		insertFacts.setHorizontalAlignment(SwingConstants.LEFT);
		
		factsPanel.add(factsLabel, BorderLayout.NORTH);
		factsPanel.add(insertFacts, BorderLayout.CENTER);
		
		imagePanel.add(image, BorderLayout.WEST);
		imagePanel.add(factsPanel, BorderLayout.CENTER);
		contentPane.add(imagePanel, BorderLayout.CENTER);
	}

	/*
	 * Creates the description panel, which contains the description and the next/save buttons.
	 * 
	 * @param descPanel
	 * @param nextButton
	 * @param saveButton
	 * @param desc
	 */
	private void createDescPanel(JPanel descPanel, JButton nextButton, JButton saveButton, JLabel desc) {
		
		descPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		descPanel.setBackground(Color.BLACK);
		descPanel.setLayout(new BorderLayout(0,0));
		
		JPanel saveNextPanel = new JPanel();
		saveNextPanel.setBorder(new EmptyBorder(0, 15, 0, 0));
		saveNextPanel.setPreferredSize(new Dimension(200, 95));
		saveNextPanel.setBackground(Color.BLACK);
		saveNextPanel.setLayout(new GridLayout(2,1));
		
		nextButton.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 16));
		nextButton.setBackground(Color.LIGHT_GRAY);
		nextButton.setForeground(Color.BLACK);
		nextButton.setPreferredSize(new Dimension(80, 42));
		nextButton.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
		saveButton.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 16));
		saveButton.setBackground(Color.LIGHT_GRAY);
		saveButton.setForeground(Color.BLACK);
		saveButton.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
		saveNextPanel.add(nextButton);
		saveNextPanel.add(saveButton);
		
		JPanel description = new JPanel();
		description.setPreferredSize(new Dimension(675, 80));
		description.setLayout(new BorderLayout(0,0));
		description.setBackground(Color.WHITE);
		
		desc.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 15));
		desc.setBorder(new EmptyBorder(0, 10, 0, 0));
		
		description.add(desc, BorderLayout.CENTER);
		
		descPanel.add(description, BorderLayout.WEST);
		descPanel.add(saveNextPanel, BorderLayout.CENTER);
		contentPane.add(descPanel, BorderLayout.SOUTH);
	}

	/**
	 * Creates the top two radio buttons that allows the user to change what type of car they want to view.
	 * 
	 * @param selectCarType
	 * @param rallyRdbtn
	 * @param trackRdbtn
	 */
	private void createSelectCarType(JPanel selectCarType, JRadioButton rallyRdbtn, JRadioButton trackRdbtn) {
		rallyRdbtn.setBackground(Color.LIGHT_GRAY);
		rallyRdbtn.setForeground(Color.BLACK);
		rallyRdbtn.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 18));
		rallyRdbtn.setSelected(true);
		rallyRdbtn.setPreferredSize(new Dimension(450, 23));
		rallyRdbtn.setHorizontalAlignment(SwingConstants.CENTER);
		
		trackRdbtn.setBackground(Color.LIGHT_GRAY);
		trackRdbtn.setForeground(Color.BLACK);
		trackRdbtn.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 18));
		trackRdbtn.setPreferredSize(new Dimension(450, 23));
		trackRdbtn.setHorizontalAlignment(SwingConstants.CENTER);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rallyRdbtn);
		group.add(trackRdbtn);
		
		selectCarType.setBorder(new EmptyBorder(10, 10, 10, 10));
		selectCarType.setLayout(new BorderLayout(0,0));
		selectCarType.setPreferredSize(new Dimension(10, 60));
		selectCarType.setBackground(Color.BLACK);
		
		selectCarType.add(rallyRdbtn, BorderLayout.WEST);
		selectCarType.add(trackRdbtn, BorderLayout.EAST);
		contentPane.add(selectCarType, BorderLayout.NORTH);
	}
	
	/**
	 * Creates the list of rally cars.
	 * 
	 * @return rallyCars
	 */
	private List<RallyCar> makeRallyCars() {
		RallyCar subie = new RallyCar(1993,"Subaru","Impreza WRX STi",line[0],295,345,4,Aspiration.Turbocharged,"/raceCarFacts/carImage/Subaru-555.png",true,Drivetrain.AWD,"A");
		RallyCar evo = new RallyCar(1993,"Mitsubishi","Lancer Evo I",line[1],298,308,4,Aspiration.Turbocharged,"/raceCarFacts/carImage/evo1.png",false,Drivetrain.AWD,"A");
		RallyCar turbo = new RallyCar(1985,"Peugeot","205 Turbo 16",line[2],430,360,4,Aspiration.Turbocharged,"/raceCarFacts/carImage/205.png",false,Drivetrain.AWD,"B");
		List<RallyCar> rallyCars = new ArrayList<>();
		rallyCars.add(subie);
		rallyCars.add(evo);
		rallyCars.add(turbo);
		return rallyCars;
	}
	
	/**
	 * Creates the list of track cars.
	 * 
	 * @return trackCars
	 */
	private List<TrackCar> makeTrackCars() {
		TrackCar mazda = new TrackCar(1990,"Mazda","787B",line[3],700,329,0,Aspiration.Natural,"/raceCarFacts/carImage/787.png",1874,210,"Prototype");
		TrackCar gtr = new TrackCar(1990,"Nissan","R32 GTR",line[4],543,360,6,Aspiration.Turbocharged,"/raceCarFacts/carImage/r32.png",2778,172,"All Japan");
		TrackCar supra = new TrackCar(1997,"Toyota","TOM's Supra",line[5],493,441,4,Aspiration.Turbocharged,"/raceCarFacts/carImage/supra.png",2535,193,"GT Racing");
		List<TrackCar> trackCars = new ArrayList<>();
		trackCars.add(mazda);
		trackCars.add(gtr);
		trackCars.add(supra);
		return trackCars;
	}

	/**
	 * Reads from descriptions.txt to recieve the descriptions of the cars.
	 * 
	 * @return line[]
	 */
	private String[] readFile() {
		try (Scanner scnr = new Scanner(RaceCarApp.class.getResourceAsStream("descriptions.txt"))) {
			for(int i = 0; i < line.length; i++) {
				line[i] = scnr.nextLine();
			}
		}
		return line;
	}
	
	/**
	 * Changes the information on the page to the next rally car.
	 * 
	 * @param car
	 */
	private void changeRallyInfo(List<RallyCar> car) {
		index++;
		if(index > 2) {
			index = 0;
		}
		image.setIcon(new ImageIcon(RaceCarApp.class.getResource(car.get(index).getImage())));
		insertFacts.setText(car.get(index).toString());
		desc.setText("<html>" + car.get(index).getDesc() + "</html>");
	}
	
	/**
	 * Changes the information on the page to the next track car.
	 * 
	 * @param car
	 */
	private void changeTrackInfo(List<TrackCar> car) {
		index++;
		if(index > 2) {
			index = 0;
		}
		image.setIcon(new ImageIcon(RaceCarApp.class.getResource(car.get(index).getImage())));
		insertFacts.setText(car.get(index).toString());
		desc.setText("<html>" + car.get(index).getDesc() + "</html>");
	}
}
