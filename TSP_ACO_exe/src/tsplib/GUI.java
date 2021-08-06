package tsplib;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;

//import tsplib.Grafic.Node;
//import tsplib.Grafic.edge;

import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class GUI {

	private JFrame frame;
	public static String tspPath;
	public static String tspFile;
	public static File file;
	private JTextField textFAlpha;
	private JTextField textFBeta;
	private JTextField textFRHO;
	private JTextField textFM;
	private JTextField textFIT;
	private JTextField textFNN;
	private JTextField textFcautareOras;
	private int autoIncrement = 1;
	private JTextField txtNumeFisier;
	private JTextField txtComentariu;
	private JTextField textFJudet;
	private JTextArea  textFArea;
	private JLabel lblDist;
	private JPanel panelGraf;
	private JPanel panel_3;
	private JRadioButton rdbtnNearestneighbor;
	private JRadioButton rdbtnOtherAlgorythm;
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}
	
	public void start() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			
			@Override
			protected Void doInBackground() throws Exception {
				//Main app = new Main();
				startApp(tspPath, tspFile);
				
				
				
				return null;
			}
		
		
		};
		worker.execute();
		
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 903, 558);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Parametrii param = new Parametrii();
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 249, 519);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		

		
		rdbtnNearestneighbor = new JRadioButton("Nearest-Neighbor");
		rdbtnNearestneighbor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnNearestneighbor.isSelected())
				{
					rdbtnOtherAlgorythm.setSelected(false);
				}
			}
		});
		rdbtnNearestneighbor.setBounds(54, 378, 161, 23);
		panel.add(rdbtnNearestneighbor);
		
		
		
		rdbtnOtherAlgorythm = new JRadioButton("ASrank");
		rdbtnOtherAlgorythm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnOtherAlgorythm.isSelected())
				{
					rdbtnNearestneighbor.setSelected(false);
				}
			}
		});
		rdbtnOtherAlgorythm.setBounds(54, 404, 143, 23);
		panel.add(rdbtnOtherAlgorythm);
		
		JLabel lblAlpha = new JLabel("\u03B1 :");
		JTextArea textArea = new JTextArea();
		textArea.setBackground(UIManager.getColor("Button.background"));
		
		JLabel show_validation_here = new JLabel("");
		JLabel show_validation_here1 = new JLabel("");
		JLabel show_validation_here2 = new JLabel("");
		JLabel show_validation_here3 = new JLabel("");
		JLabel show_validation_here4 = new JLabel("");
		JLabel show_validation_here5 = new JLabel("");
		JLabel show_validation_here6 = new JLabel("");
		
		String valRHO = String.valueOf(param.getRHO());
		String valAlpha = String.valueOf(param.getAlpha());
		String valBeta = String.valueOf(param.getBeta());
		String valNrFurnici = String.valueOf(param.getNrFurnici());
		String valNrIt = String.valueOf(param.getNrIt());
		String valNN = String.valueOf(param.getNN());
		
		panelGraf = new JPanel();
		

		
		
		JPanel panel_3 = new JPanel();
		

		//panelGraf.add(panelDraw);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 33, 617, 482);
		panel_3.setLayout(null);
		
		JButton btnIncarcaFisier = new JButton("Incarca fisier");
		btnIncarcaFisier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					show_validation_here.setText("");
				Double alphaText = Double.parseDouble(textFAlpha.getText());
				Double betaText = Double.parseDouble(textFBeta.getText());
				Double rhoText = Double.parseDouble(textFRHO.getText());
				Integer mText = Integer.parseInt(textFM.getText());
				Integer itText = Integer.parseInt(textFIT.getText());
				Integer nnText = Integer.parseInt(textFNN.getText());
				
				param.setAlpha(alphaText);
				param.setBeta(betaText);
				param.setNrFurnici(mText);
				param.setNrIT(itText);
				param.setRHO(rhoText);
				param.setMaxListVecini(nnText);
				
				String var = "S-au ales parametrii : \nalfa = " + alphaText + ",  beta = " + betaText + ",  rho = " + rhoText + ",\nnr Furnici = " + mText + ",  nr Iteratii = " + itText +
													",  nr Vecini = " + nnText;
				
				textArea.setText(String.valueOf(var));
				


				
				
				
				if(e.getSource() == btnIncarcaFisier) {
					JFileChooser fileChooser = new JFileChooser();
					
					fileChooser.setCurrentDirectory(new File("."));
					int response = fileChooser.showOpenDialog(null);
					
					if(response == JFileChooser.APPROVE_OPTION)
					{
						file = new File(fileChooser.getSelectedFile().getAbsolutePath());
						tspPath = file.getParent();
						tspFile = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\")+1);
						//grafic = new Grafic(TspFileReader.getCoordinates(tspPath, tspFile));
						//panelDraw = new DrawPanel();
						//grafic.setBounds(0, 0, 617, 482);
						//panelGraf.add(grafic);
						//panel_3.add(grafic);
						panelGraf.add(panel_3);

						start();
					}
				}
				} catch(Throwable e7)
				{
					show_validation_here.setText("Format parametrii invalid");
					
				}
			}
		});
		btnIncarcaFisier.setBounds(54, 91, 128, 23);
		panel.add(btnIncarcaFisier);
		
		JLabel lblVizualizare = new JLabel("Vizualizare");
		lblVizualizare.setBounds(72, 221, 78, 14);
		panel.add(lblVizualizare);
		
		JRadioButton rdbtnTehnica = new JRadioButton("Tehnica");
		JRadioButton rdbtnGrafica = new JRadioButton("Grafica");
		JRadioButton rdbtnParametrii = new JRadioButton("Parametrii");
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		
		rdbtnGrafica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnGrafica.isSelected())
				{
					rdbtnParametrii.setSelected(false);
					rdbtnTehnica.setSelected(false);
					tabbedPane.setSelectedIndex(1);
				}
			}
		});
		rdbtnGrafica.setBounds(54, 248, 109, 23);
		panel.add(rdbtnGrafica);
		
		//JRadioButton rdbtnTehnica = new JRadioButton("Tehnica");
		rdbtnTehnica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnTehnica.isSelected())
				{			
					rdbtnGrafica.setSelected(false);
					rdbtnParametrii.setSelected(false);
					tabbedPane.setSelectedIndex(2);
				}
			}
		});
		rdbtnTehnica.setBounds(54, 274, 109, 23);
		panel.add(rdbtnTehnica);
		
		JLabel lblAlgoritm = new JLabel("Algoritm");
		lblAlgoritm.setBounds(79, 349, 71, 14);
		panel.add(lblAlgoritm);
		
		//JRadioButton rdbtnParametrii = new JRadioButton("Parametrii");
		rdbtnParametrii.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnParametrii.isSelected())
				{
					rdbtnGrafica.setSelected(false);
					rdbtnTehnica.setSelected(false);
					tabbedPane.setSelectedIndex(3);
				}
			}
		});
		rdbtnParametrii.setBounds(58, 155, 109, 23);
		panel.add(rdbtnParametrii);
		
		
		show_validation_here.setForeground(Color.RED);
		show_validation_here.setBounds(24, 149, 161, 14);
		panel.add(show_validation_here);
		
		JTextArea txtrPanouDeComanda = new JTextArea();
		txtrPanouDeComanda.setBackground(UIManager.getColor("Button.background"));
		txtrPanouDeComanda.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtrPanouDeComanda.setText("Panou de comanda");
		txtrPanouDeComanda.setBounds(33, 29, 174, 51);
		panel.add(txtrPanouDeComanda);
		
		JButton btnRutaOptima = new JButton("Ruta optima \n- Traseu Personalizat");
		btnRutaOptima.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnRutaOptima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnGrafica.setSelected(false);
				rdbtnTehnica.setSelected(false);
				rdbtnParametrii.setSelected(false);
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnRutaOptima.setBounds(24, 475, 195, 33);
		panel.add(btnRutaOptima);
		
		//JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setBounds(247, -14, 653, 573);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelDefault = new JPanel();
		tabbedPane.addTab("Default", null, panelDefault, null);
		panelDefault.setLayout(null);
		
		JTextArea txtrTravelingSalesmanProblem = new JTextArea();
		txtrTravelingSalesmanProblem.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtrTravelingSalesmanProblem.setBackground(UIManager.getColor("Button.background"));
		txtrTravelingSalesmanProblem.setText("          PROBLEMA COMIS VOIAJORULUI (PCV) -\r\nOPTIMIZARE BAZATA PE COLONII DE FURNICI (OCF)");
		txtrTravelingSalesmanProblem.setBounds(53, 243, 563, 96);
		panelDefault.add(txtrTravelingSalesmanProblem);
		
		
		

		tabbedPane.addTab("Grafica", null, panelGraf, null);
		panelGraf.setLayout(null);
		
		

		

		
		JPanel panelTehn = new JPanel();
		tabbedPane.addTab("Tehnica", null, panelTehn, null);
		panelTehn.setLayout(null);
		
		JLabel lblRutaOptima = new JLabel("Ruta optima :");
		lblRutaOptima.setBounds(74, 54, 95, 39);
		panelTehn.add(lblRutaOptima);
		
		JLabel lblDistanta = new JLabel("Distanta : ");
		lblDistanta.setBounds(83, 447, 66, 39);
		panelTehn.add(lblDistanta);
		
		lblDist = new JLabel("");
		lblDist.setBounds(159, 459, 46, 14);
		panelTehn.add(lblDist);
		
		JLabel lblTimp = new JLabel("");
		lblTimp.setBounds(169, 494, 46, 14);
		panelTehn.add(lblTimp);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(74, 119, 524, 329);
				//scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

				
		textFArea = new JTextArea ();
		textFArea.setBounds(1, 1, 504, 316);
		textFArea.setWrapStyleWord(true);
		textFArea.setRows(26);
		textFArea.setLineWrap(true);
		textFArea.setEditable(false);
		panel_2.add(textFArea);
				
		textFArea.setColumns(100);
						
		panelTehn.add(panel_2);
		panel_2.setLayout(null);
		

		JScrollPane scrollPane_1 = new JScrollPane(textFArea);
		scrollPane_1.setBounds(9, 5, 506, 318);
		
		panel_2.add(scrollPane_1);
		
		JPanel panelParam = new JPanel();
		tabbedPane.addTab("Param", null, panelParam, null);
		panelParam.setLayout(null);
		
		
		lblAlpha.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAlpha.setBounds(243, 74, 46, 14);
		panelParam.add(lblAlpha);
		
		JLabel label = new JLabel("\u03B2 :");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label.setBounds(243, 135, 46, 14);
		panelParam.add(label);
		
		JLabel lblRho = new JLabel("rho :");
		lblRho.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblRho.setBounds(230, 193, 46, 14);
		panelParam.add(lblRho);
		
		JLabel lblNewLabel = new JLabel("m : ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(243, 250, 46, 14);
		panelParam.add(lblNewLabel);
		
		JLabel lblIteratii = new JLabel("it :");
		lblIteratii.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblIteratii.setBounds(243, 311, 46, 14);
		panelParam.add(lblIteratii);
		
		textFAlpha = new JTextField();
		textFAlpha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ev1) {
				try {
					Double i1 = Double.parseDouble(textFAlpha.getText() +ev1.getKeyChar());
					show_validation_here1.setText("");
				}
				catch (NumberFormatException e1)
				{
					show_validation_here1.setText("Format invalid");
				}
				
			}
		});
		textFAlpha.setText(valAlpha);
		textFAlpha.setBounds(326, 74, 86, 20);
		panelParam.add(textFAlpha);
		textFAlpha.setColumns(10);
		
		textFBeta = new JTextField();
		textFBeta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ev2) {
			
					try {
						Double i2 = Double.parseDouble(textFBeta.getText() +ev2.getKeyChar());
						show_validation_here2.setText("");
					}
					catch (NumberFormatException e2)
					{
						show_validation_here2.setText("Format invalid");
					}
					
				
			}
		});
		textFBeta.setText(valBeta);
		textFBeta.setBounds(326, 135, 86, 20);
		panelParam.add(textFBeta);
		textFBeta.setColumns(10);
		
		textFRHO = new JTextField();
		

		textFRHO.setText(valRHO);
		textFRHO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ev3) {
				
					try {
						Double i3 = Double.parseDouble(textFRHO.getText() +ev3.getKeyChar());
						show_validation_here3.setText("");
					}
					catch (NumberFormatException e3)
					{
						show_validation_here3.setText("Format invalid");
					}
					
				
			}
		});
		textFRHO.setBounds(326, 193, 86, 20);
		panelParam.add(textFRHO);
		textFRHO.setColumns(10);
		
		textFM = new JTextField();
		textFM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ev4) {

				try {
					int i4 = Integer.parseInt(textFM.getText() +ev4.getKeyChar());
					show_validation_here4.setText("");
				}
				catch (NumberFormatException e4)
				{
					show_validation_here4.setText("Format invalid");
				}
				
			}
		});
		textFM.setText(valNrFurnici);
		textFM.setBounds(326, 250, 86, 20);
		panelParam.add(textFM);
		textFM.setColumns(10);
		
		textFIT = new JTextField();
		textFIT.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ev5) {

				try {
					int i5 = Integer.parseInt(textFIT.getText() +ev5.getKeyChar());
					show_validation_here5.setText("");
				}
				catch (NumberFormatException e5)
				{
					show_validation_here5.setText("Format invalid");
				}
				
			}
		});
		textFIT.setText(valNrIt);
		textFIT.setBounds(326, 311, 86, 20);
		panelParam.add(textFIT);
		textFIT.setColumns(10);
		
		JLabel lblNn = new JLabel("NN :");
		lblNn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNn.setBounds(230, 376, 46, 14);
		panelParam.add(lblNn);
		
		textFNN = new JTextField();
		textFNN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ev6) {

				try {
					int i6 = Integer.parseInt(textFNN.getText() +ev6.getKeyChar());
					show_validation_here6.setText("");
				}
				catch (NumberFormatException e6)
				{
					show_validation_here6.setText("Format invalid");
				}
				
			}
		});
		textFNN.setText(valNN);
		textFNN.setBounds(326, 376, 86, 20);
		panelParam.add(textFNN);
		textFNN.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(77, 431, 474, 63);
		panelParam.add(panel_1);
		panel_1.setLayout(null);
		
		
		textArea.setBounds(10, 0, 454, 63);
		panel_1.add(textArea);
		
		//JLabel show_validation_here1 = new JLabel("");
		show_validation_here1.setForeground(Color.RED);
		show_validation_here1.setBounds(431, 74, 141, 14);
		panelParam.add(show_validation_here1);
		
		//JLabel show_validation_here2 = new JLabel("");
		show_validation_here2.setForeground(Color.RED);
		show_validation_here2.setBounds(431, 135, 109, 14);
		panelParam.add(show_validation_here2);
		
		//JLabel show_validation_here3 = new JLabel("");
		show_validation_here3.setForeground(Color.RED);
		show_validation_here3.setBounds(431, 193, 122, 14);
		panelParam.add(show_validation_here3);
		
		//JLabel show_validation_here4 = new JLabel("");
		show_validation_here4.setForeground(Color.RED);
		show_validation_here4.setBounds(431, 250, 146, 14);
		panelParam.add(show_validation_here4);
		
		//JLabel show_validation_here5 = new JLabel("");
		show_validation_here5.setForeground(Color.RED);
		show_validation_here5.setBounds(431, 311, 142, 14);
		panelParam.add(show_validation_here5);
		
		//JLabel show_validation_here6 = new JLabel("");
		show_validation_here6.setForeground(Color.RED);
		show_validation_here6.setBounds(431, 376, 151, 14);
		panelParam.add(show_validation_here6);
		
		JLabel lblNewLabel_1 = new JLabel("Importanta Feromonului");
		lblNewLabel_1.setBounds(37, 77, 141, 14);
		panelParam.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Importanta Euristica");
		lblNewLabel_2.setBounds(37, 138, 141, 14);
		panelParam.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Rata evaporarii feromonului");
		lblNewLabel_3.setBounds(37, 199, 160, 14);
		panelParam.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Numarul de furnici");
		lblNewLabel_4.setBounds(37, 253, 122, 14);
		panelParam.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Numarul de iteratii");
		lblNewLabel_5.setBounds(37, 314, 122, 14);
		panelParam.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Numarul de vecini");
		lblNewLabel_6.setBounds(37, 379, 141, 14);
		panelParam.add(lblNewLabel_6);
		
		JPanel panelPersonalizat = new JPanel();
		tabbedPane.addTab("New tab", null, panelPersonalizat, null);
		panelPersonalizat.setLayout(null);
		
		JLabel lblCalculeazaRutaOptima = new JLabel("Calculeaza ruta optima pentru propriul traseu!");
		lblCalculeazaRutaOptima.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblCalculeazaRutaOptima.setBounds(74, 41, 463, 31);
		panelPersonalizat.add(lblCalculeazaRutaOptima);
		
		JLabel lblNewLabel_7 = new JLabel("Adauga oras:");
		lblNewLabel_7.setBounds(74, 106, 99, 14);
		panelPersonalizat.add(lblNewLabel_7);
		
		JPanel panelListOrase = new JPanel();
		panelListOrase.setBounds(70, 179, 521, 277);
		panelPersonalizat.add(panelListOrase);
		panelListOrase.setLayout(null);
		
		
		
		DefaultListModel demoList = new DefaultListModel();
		DefaultListModel validator = new DefaultListModel();
		DefaultListModel validatorJudet = new DefaultListModel();

			
		
		textFcautareOras = new JTextField();
		textFcautareOras.setBounds(168, 103, 117, 20);
		panelPersonalizat.add(textFcautareOras);
		textFcautareOras.setColumns(10);
		
		JLabel lblErrorOras = new JLabel("");
		
		JButton btnConfirma = new JButton("Confirma");
		btnConfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblErrorOras.setText("");
					String word = textFcautareOras.getText();
					String judet = textFJudet.getText();
					//System.out.println(word);
					boolean flag = false;
				    int count = 0;
				    
				    String spacer = "_";
				    
				    Scanner sc1 = new Scanner(new FileInputStream("C:\\Users\\crist\\OneDrive\\Desktop\\Lucrare Licenta 2021\\TSP_ACO\\TSP_ACO_exe\\db\\orase.txt"));

				   
				    while(sc1.hasNextLine()) {
				         String line = sc1.nextLine();
				         //System.out.println(line);
				         String delimiter = ",";

				         String[] tokensVal = line.split(delimiter);
				         //System.out.println("Account type = " + tokensVal[2]);
				         //System.out.println("cuvant = " + word);
				         
				         if(tokensVal[2].equals(word)) {
				            flag = true;
				            count = count+1;
				         }
				         
				      }
				    if(flag) {

				    		if(!validatorJudet.contains(word+judet) ) {
				    			//validator.addElement(word);
				    			validator.addElement(word+judet);
				    			demoList.addElement(autoIncrement+spacer+word+spacer+judet);
						    	autoIncrement++;
				    		}
				    		else {
				    			lblErrorOras.setText("Ati introdus deja orasul " + word + " in lista!");
				    		}
				    	

				    	
				      } else {
				         throw new Exception("ex");
				      }
				    
				}
				catch(Exception ex) {
					lblErrorOras.setText("Nu s-a gasit orasul in baza de date");
				}
			}
		});
		btnConfirma.setBounds(502, 102, 89, 23);
		panelPersonalizat.add(btnConfirma);
		
		JList list = new JList(demoList);
		list.setBounds(0, 0, 1, 1);
		panelListOrase.add(list);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(0, 0, 521, 277);
		panelListOrase.add(scrollPane);
		
		

		lblErrorOras.setForeground(Color.RED);
		lblErrorOras.setBounds(121, 147, 398, 14);
		panelPersonalizat.add(lblErrorOras);
		
		JButton btnCalculeaza = new JButton("Genereaza Fisier TSP");
		btnCalculeaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String tempFileName = "C:\\Users\\crist\\OneDrive\\Desktop\\Lucrare Licenta 2021\\TSP_ACO\\TSP_ACO_exe\\tsp\\" + txtNumeFisier.getText() + ".tsp";
				      File myObj = new File(tempFileName);
				      if (myObj.createNewFile()) {
				        System.out.println("File created: " + myObj.getName());
				      } else {
				        System.out.println("File already exists.");
				      }
				      
				      try {
				    	  BufferedWriter myWriter = new BufferedWriter(new FileWriter(tempFileName));
				          myWriter.write("NAME : "+ txtNumeFisier.getText());
				          myWriter.newLine();
				          myWriter.write("TYPE : TSP");
				          myWriter.newLine();
				          myWriter.write("COMMENT : "+ txtComentariu.getText());
				          myWriter.newLine();
				          myWriter.write("DIMENSION : "+ demoList.getSize());
				          myWriter.newLine();
				          myWriter.write("EDGE_WEIGHT_TYPE : EUC_2D");
				          myWriter.newLine();
				          myWriter.write("NODE_COORD_SECTION");
				          myWriter.newLine();
				          
				          for(int i=0; i < demoList.getSize(); i++)
				          {
				        	  String tempCreateFile = demoList.getElementAt(i).toString();
				        	  String[] splitTemp = tempCreateFile.split("_");
				        	  
								boolean flag1 = false;
					
							    
							    Scanner sc2 = new Scanner(new FileInputStream("C:\\Users\\crist\\OneDrive\\Desktop\\Lucrare Licenta 2021\\TSP_ACO\\TSP_ACO_exe\\db\\orase.txt"));

							   
							    while(sc2.hasNextLine()) {
							         String line = sc2.nextLine();
							         //System.out.println(line);
							         String delimiter = ",";

							         String[] tokensValx = line.split(delimiter);
							         //System.out.println("Account type = " + tokensVal[2]);
							         //System.out.println("cuvant = " + word);
							         
							         if((tokensValx[2].equals(splitTemp[1]) && (tokensValx[3].equals(splitTemp[2])))) {
							            flag1 = true;
							            //System.out.println("1= "+tokensValx[1] + "3= " + tokensValx[3] + "0= " +tokensValx[0]);
							            myWriter.write(i+1 + " " + tokensValx[0] + " " + tokensValx[1]);
								        myWriter.newLine();
							            
							         }
							         
							      }
							    if(flag1) {
							    	
							    	//System.out.println("1= "+tokensValx[1] + "3= " + tokensValx[3] + "0= " +tokensValx[0]);
							    	
							      } else {
							         //throw new Exception("ex");
							      }
				        	  
							  
				          }
				          
				         // myWriter.flush();
				          myWriter.write("EOF");
				          myWriter.close();
				          System.out.println("Successfully wrote to the file.");
				        } catch (IOException e1) {
				          System.out.println("An error occurred.");
				          e1.printStackTrace();
				        }
				      
				    } catch (IOException e1) {
				      System.out.println("Eroare la crearea fisierului");
				      e1.printStackTrace();
				    }
				
				
				
			}
		});
		btnCalculeaza.setBounds(315, 482, 276, 23);
		panelPersonalizat.add(btnCalculeaza);
		
		txtNumeFisier = new JTextField();
		txtNumeFisier.setText("Nume fisier");
		txtNumeFisier.setBounds(70, 469, 193, 20);
		panelPersonalizat.add(txtNumeFisier);
		txtNumeFisier.setColumns(10);
		
		txtComentariu = new JTextField();
		txtComentariu.setText("Comentariu");
		txtComentariu.setBounds(70, 501, 193, 20);
		panelPersonalizat.add(txtComentariu);
		txtComentariu.setColumns(10);
		
		JLabel lblJudet = new JLabel("Judet: ");
		lblJudet.setBounds(306, 106, 46, 14);
		panelPersonalizat.add(lblJudet);
		
		textFJudet = new JTextField();
		textFJudet.setBounds(345, 103, 86, 20);
		panelPersonalizat.add(textFJudet);
		textFJudet.setColumns(10);
		
		
	     
	}

	

	
	public void startApp(String path, String file) {
		Utils utils = new Utils(TspFileReader.getDistances(path, file));
		Result result = new Result(file, utils, TspFileReader.getCoordinates(path, file));
		Parametrii parametrii = new Parametrii();

		utils.genereazaListaOraseVecine();
		utils.genereazaPopulatieFurnici();
		utils.genereazaMediu();
		
		
		int repetari = 0;
		while(repetari < Parametrii.nrIt) {
			
			if(rdbtnNearestneighbor.isSelected()) {
			utils.construiesteSolutia();
			utils.actualizareFeromon();
			result.calculeazaResult(repetari);
			}
			else if(rdbtnOtherAlgorythm.isSelected()) {
				utils.solutiaRankBased();
				result.calculeazaResult(repetari);
			    utils.actualizeazaFermonRankBased();    
			}
			else { 
				utils.BackTracking();
			}
			

			textFArea.setText(parametrii.getResult());
			lblDist.setText(String.valueOf(parametrii.getDistantaFinala()));


			repetari++;
		}
		try { Thread.sleep(3000); } catch (Exception ex) {}

		Parametrii param = new Parametrii();
		System.out.println("Final");
		System.out.println("Cel mai bun traseu= " + param.getDistantaFinala());
		
	}
	
	
	
}
