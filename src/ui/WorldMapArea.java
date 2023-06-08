package ui;

import domain.controllers.Controller;
import domain.player.Player;
import domain.territory.Territory;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;


public class WorldMapArea {
	
	private static HashMap<Territory, Shape> territoryMap = new HashMap<>(); 
	private static HashMap<Shape, Territory> reverseTerritoryMap = new HashMap<>();
	private static HashMap<Territory, JLabel> territoryLabelMap = new HashMap<>();
	private static HashMap<JLabel, Territory> reverseLabelMap = new HashMap<>();
	private static HashMap<JLabel, Shape> labelShapeMap = new HashMap<>();
	private static HashMap<Shape, JLabel> reverseLabelShapeMap = new HashMap<>();
	
	private Territory[] territories = Controller.getInstance().getAllTerritories();
	private Territory fromTerritory = null;
	private Territory toTerritory = null;
	private String mode = "recruit";
	private int armyNum = 0;
	private Area area;
    private ArrayList<Shape> shapeList;
    private static final int SIZE = 650;
    private BufferedImage image;
    private static boolean generated = false;
    
    private JLabel output = new JLabel();
    private JComponent ui = null;
    private JTextArea infoBox = new JTextArea();
    private JList<String> chanceCardList = new JList<>();
    private JList<String> armyCardList = new JList<>();
    private JList<String> territoryCardList = new JList<>();
    private JTextField recruitArmyNumSelectField = new JTextField();
    private JTextField fortifyArmyNumSelectField = new JTextField();
    private JComboBox<String> tradeArmyOptionBox = new JComboBox<>();
    private JButton useCardButton = new JButton("Use card");
    private JButton recruitButton = new JButton("Recruit");
	private JButton attackButton = new JButton("Attack");
	private JButton fortifyButton = new JButton("Fortify");
	private JButton endTurnButton = new JButton("End Turn");
	private JButton nextModeButton = new JButton("Next Mode");
	private JButton exitButton = new JButton("Exit");
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private int[][] coordinateList = {
			{185,74}, //northWestTerritory
			{524,83}, //iceland
			{469,41}, //greenland
			{81,80}, //alaska
			{628,91}, //scandinavia
			{1028,86}, //yakutsk
			{545,123}, //britain
			{814,97}, //ural
			{223,119}, //ontario
			{141,117}, //alberta
			{629,136}, //northernEurope
			{1043,135}, //irkutsk
			{903,93}, //siberia
			{306,138}, //quebec
			{1101,79}, //kamchatka
			{707,103}, //ukraine
			{809,159}, //afghanistan
			{659,166}, //southernEurope
			{559,188}, //westernEurope
			{134,187}, //westernUnitedStates
			{1026,166}, //mongolia
			{1134,207}, //japan
			{221,192}, //easternUnitedStates
			{675,243}, //egypt
			{967,218}, //china
			{756,231}, //middleEast
			{894,255}, //india
			{140,258}, //centralAmerica
			{573,285}, //northAfrica
			{1007,302}, //slam
			{271,351}, //venezuela
			{712,330}, //eastAfrica
			{653,370}, //congo
			{1064,375}, //indonesia
			{1182,405}, //newGuiena
			{258,423}, //peru
			{345,426}, //brazil
			{769,449}, //madagascar
			{668,460}, //southAfrica
			{1081,505}, //westernAustralia
			{1169,498}, //easternAustralia
			{306,538} //argentina		   		 
    };
  
    public WorldMapArea() {
        try {
        	initUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static final Color[] PLAYER_COLORS = {
            Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN
            // Actually we do not need to color continents, we need to assign colors to players so that they can place armies
            // and make territories their colors.
    };
    
    public final void initUI() throws Exception {

    	generateMap();
        String filePath = "map_with_black_outline_resized2.png";
        File imageFile = new File(filePath);
        
        image = ImageIO.read(imageFile);
        long then = System.currentTimeMillis();
        System.out.println("" + then);
        area = getOutline(Color.WHITE, image, 12);
        long now = System.currentTimeMillis();
        System.out.println("Time in mins: " + (now - then) / 60000d);
        shapeList = separateShapeIntoRegions(area);
        
        generateLabels();
        
        for(int i = 0; i<shapeList.size(); i++) { 
        	if(i<territories.length) {
        		territoryMap.put(territories[i], shapeList.get(i));	
        		reverseTerritoryMap.put(shapeList.get(i), territories[i]);       	
        		if(territories[i] != null) {
        			labelShapeMap.put(territoryLabelMap.get(territories[i]), shapeList.get(i));
        			reverseLabelShapeMap.put(shapeList.get(i), territoryLabelMap.get(territories[i]));          	
        		}
        	}
        	
        }
        
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridBagLayout());
        
        JPanel deckPanel = new JPanel();
        deckPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new BorderLayout());
        
        playerPanel.setBackground(Color.LIGHT_GRAY);
        infoPanel.setBackground(Color.LIGHT_GRAY);
        deckPanel.setBackground(Color.LIGHT_GRAY);
        mapPanel.setBackground(Color.LIGHT_GRAY);
        actionPanel.setBackground(Color.LIGHT_GRAY);
        
        
        //Action panel which has the buttons.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 10, 20, 10);
        
        JLabel recruitArmyNumSelectLabel = new JLabel("Select army num:");
        JLabel fortifyArmyNumSelectLabel = new JLabel("Select army num:");
                
        recruitArmyNumSelectLabel.setPreferredSize(new Dimension(100,20));
        fortifyArmyNumSelectLabel.setPreferredSize(new Dimension(100,20));
        recruitArmyNumSelectField.setPreferredSize(new Dimension(100,20));
        fortifyArmyNumSelectField.setPreferredSize(new Dimension(100,20));
        recruitButton.setPreferredSize(new Dimension(100,20));
        attackButton.setPreferredSize(new Dimension(100,20));
        fortifyButton.setPreferredSize(new Dimension(100,20));
        nextModeButton.setPreferredSize(new Dimension(100,20));
        endTurnButton.setPreferredSize(new Dimension(100,20));
        
        actionPanel.add(recruitArmyNumSelectLabel,gbc);
        actionPanel.add(recruitArmyNumSelectField,gbc);
        actionPanel.add(recruitButton,gbc);
        actionPanel.add(attackButton,gbc);  
        actionPanel.add(fortifyArmyNumSelectLabel,gbc);
        actionPanel.add(fortifyArmyNumSelectField,gbc);
        actionPanel.add(fortifyButton,gbc);
        actionPanel.add(nextModeButton,gbc);
        actionPanel.add(endTurnButton,gbc);

        
        
        //Player panel that has the names. It doesn't work.
        playerPanel.setPreferredSize(new Dimension(500,25));
        for(int i = 0; i<Controller.getInstance().getNicknameList().size();i++) {
        	JLabel label = new JLabel(Controller.getInstance().getNicknameList().get(i));
        	label.setPreferredSize(new Dimension(30,20));
        	playerPanel.add(label);
        }
        
        
        //Map panel that contains the map. I wouldn't bother changing it.
        mapPanel.setPreferredSize(new Dimension(1465,750));
        mapPanel.add(output,BorderLayout.NORTH);
        
        //Info panel on the bottom. Need to implement to print game events here.
        infoPanel.add(infoBox,Alignment.CENTER);  
        infoPanel.setPreferredSize(new Dimension(500,100));
        infoBox.setPreferredSize(new Dimension(1000,150));
        
        //Deck panel on the left.
        tradeArmyOptionBox.addItem("1");
        tradeArmyOptionBox.addItem("2");
        tradeArmyOptionBox.addItem("3");
        tradeArmyOptionBox.addItem("4");
        tradeArmyOptionBox.addItem("5");
        
        tradeArmyOptionBox.setPreferredSize(new Dimension(100,50));
        chanceCardList.setPreferredSize(new Dimension(150,140));
        territoryCardList.setPreferredSize(new Dimension(150,140));
        armyCardList.setPreferredSize(new Dimension(150,140));
        useCardButton.setPreferredSize(new Dimension(150,30));
        
        JLabel tradeOptionLabel = new JLabel("Trade option");
        JLabel armyCardLabel = new JLabel("Your army cards");
        JLabel territoryCardLabel = new JLabel("Your territory cards");
        JLabel chanceCardLabel = new JLabel("Your chance cards");
        
        tradeOptionLabel.setPreferredSize(new Dimension(150,15));
        armyCardLabel.setPreferredSize(new Dimension(150,15));
        territoryCardLabel.setPreferredSize(new Dimension(150,15));
        chanceCardLabel.setPreferredSize(new Dimension(150,15));

        deckPanel.add(tradeOptionLabel);
        deckPanel.add(tradeArmyOptionBox);
        deckPanel.add(armyCardLabel);
        deckPanel.add(armyCardList);
        deckPanel.add(territoryCardLabel);
        deckPanel.add(territoryCardList);
        deckPanel.add(chanceCardLabel);
        deckPanel.add(chanceCardList);
        deckPanel.add(useCardButton);
        deckPanel.setPreferredSize(new Dimension(150,1080));

        //Main panel holds all of them inside.   
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(infoPanel, BorderLayout.SOUTH);       
        mainPanel.add(playerPanel, BorderLayout.NORTH);
        mainPanel.add(actionPanel, BorderLayout.EAST);
        mainPanel.add(deckPanel, BorderLayout.WEST);
        mainPanel.add(mapPanel,BorderLayout.CENTER);
        
        ui = new JPanel(new BorderLayout());
        ui.setPreferredSize(new Dimension(screenSize.width,screenSize.height));       
        ui.add(mainPanel);
       
        output.addMouseMotionListener(new MousePositionListener());
        output.addMouseListener(new MouseClickListener());
        addListeners(new BoardActionListener());
        refresh();
        
        System.out.println(shapeList.size());
        

    }

    public Area getOutline(Color target, BufferedImage bi, int tolerance) {
        // construct the GeneralPath
        GeneralPath gp = new GeneralPath();
        
        boolean cont = false;
        for (int xx = 0; xx < bi.getWidth(); xx++) {
            for (int yy = 0; yy < bi.getHeight(); yy++) {
                if (isIncluded(new Color(bi.getRGB(xx, yy)), target, tolerance)) {
                    //if (bi.getRGB(xx,yy)==targetRGB) {
                    if (cont) {
                        gp.lineTo(xx, yy);
                        gp.lineTo(xx, yy + 1);
                        gp.lineTo(xx + 1, yy + 1);
                        gp.lineTo(xx + 1, yy);
                        gp.lineTo(xx, yy);
                    } else {
                        gp.moveTo(xx, yy);
                    }
                    cont = true;
                } else {
                    cont = false;
                }
            }
            cont = false;
        }
        gp.closePath();

        // construct the Area from the GP & return it
        return new Area(gp);
    }

    public static ArrayList<Shape> separateShapeIntoRegions(Shape shape) {
        ArrayList<Shape> regions = new ArrayList<>();

        PathIterator pi = shape.getPathIterator(null);
        GeneralPath gp = new GeneralPath();
        while (!pi.isDone()) {
            double[] coords = new double[6];
            int pathSegmentType = pi.currentSegment(coords);
            int windingRule = pi.getWindingRule();
            gp.setWindingRule(windingRule);
            if (pathSegmentType == PathIterator.SEG_MOVETO) {
                gp = new GeneralPath();
                gp.setWindingRule(windingRule);
                gp.moveTo(coords[0], coords[1]);
            } else if (pathSegmentType == PathIterator.SEG_LINETO) {
                gp.lineTo(coords[0], coords[1]);
            } else if (pathSegmentType == PathIterator.SEG_QUADTO) {
                gp.quadTo(coords[0], coords[1], coords[2], coords[3]);
            } else if (pathSegmentType == PathIterator.SEG_CUBICTO) {
                gp.curveTo(
                        coords[0], coords[1],
                        coords[2], coords[3],
                        coords[4], coords[5]);
            } else if (pathSegmentType == PathIterator.SEG_CLOSE) {
                gp.closePath();
                regions.add(new Area(gp));
            } else {
                System.err.println("Unexpected value! " + pathSegmentType);
            }

            pi.next();
        }

        return regions;
    }

    class MousePositionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            // do nothing
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            refresh();
        }
    }

    class MouseClickListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			Point clickPoint = e.getPoint();
			Territory territory = null;
			Player currentPlayer = Controller.getInstance().getCurrentPlayer();
			
			for (Territory trtry : territories) {
				Shape territoryShape = territoryMap.get(trtry);
				if (territoryShape.contains(clickPoint)) {
					territory = trtry;
					break;
				}
			}
			if(territory != null) {
				System.out.println("Selected territory: " + territory.getName());
				System.out.println(clickPoint.getX() +","+clickPoint.getY());
				

				try{
					if(mode == "recruit") {
						toTerritory = territory;
						getRecruitButton().setBackground(Color.GREEN);
					}
					else if(mode == "attack") {
						getNextModeButton().setBackground(null);
						if(fromTerritory == null) {
							fromTerritory = territory;
							System.out.println(fromTerritory.getName());
						}
						else if(toTerritory == null) {
							toTerritory = territory;
							System.out.println(toTerritory.getName());
						}
						getAttackButton().setBackground(Color.GREEN);
					}
					else if(mode == "fortify") {
						getAttackButton().setBackground(null);
						if(fromTerritory == null) {
							fromTerritory = territory;
						}
						else if(toTerritory == null) {
							toTerritory = territory;
						}
						getFortifyButton().setBackground(Color.GREEN);
					}
				}
				catch(NumberFormatException ex){

				}				
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		}

    class BoardActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			Player currentPlayer = Controller.getInstance().getCurrentPlayer();
			
			try{
				
				if(getNextModeButton() == e.getSource()) {
						nextMode();
						System.out.println(mode);
						refreshSelectedTerritories();
				}
				
				else if(getEndTurnButton()==e.getSource()){
					
					getEndTurnButton().setBackground(null);
					
					if(!Controller.getInstance().areTerritoriesOccupied()){
						Controller.getInstance().endTurn();
						refreshSelectedTerritories();
					}
					else if(mode == "fortify") {
						Controller.getInstance().endTurn();
						refreshSelectedTerritories();
						System.out.println("end turn button, currentplayer: "+ currentPlayer.getName());
						mode = "recruit";
						
					}
				}
				else {
					
					if(mode == "recruit" && getRecruitButton() == e.getSource()) {
						
						
						if(Controller.getInstance().areTerritoriesOccupied()) {
							if(Controller.getInstance().recruit(toTerritory,currentPlayer)) {
								getRecruitButton().setBackground(null);
								getNextModeButton().setBackground(Color.GREEN);
								refreshSelectedTerritories();
							};
						}
						else {
							if(Controller.getInstance().recruit(toTerritory,currentPlayer)) {
								getRecruitButton().setBackground(null);
								Controller.getInstance().endTurn();
							}
						
						}
					}
					else if(mode == "attack" && getAttackButton()==e.getSource()) {
						Controller.getInstance().attack(fromTerritory, toTerritory);
						getAttackButton().setBackground(null);
						getNextModeButton().setBackground(Color.GREEN);													
					}
					else if(mode == "fortify" && getFortifyButton()==e.getSource()){
						getFortifyButton().setBackground(null);
						Controller.getInstance().fortify(fromTerritory, toTerritory,armyNum);
						getEndTurnButton().setBackground(Color.GREEN);
					}
				}
			
			}

			catch(NumberFormatException ex){
				
				System.out.println(ex);
				
				
			}
			
		}
		
	}	
    
    public static boolean isIncluded(Color target, Color pixel, int tolerance) {
        int rT = target.getRed();
        int gT = target.getGreen();
        int bT = target.getBlue();
        int rP = pixel.getRed();
        int gP = pixel.getGreen();
        int bP = pixel.getBlue();
        return ((rP - tolerance <= rT) && (rT <= rP + tolerance)
                && (gP - tolerance <= gT) && (gT <= gP + tolerance)
                && (bP - tolerance <= bT) && (bT <= bP + tolerance));
    }

    private void refresh() {
        BufferedImage bi = new BufferedImage(2 * SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.drawImage(image, 0, 0, output);
        g.setColor(Color.WHITE);
        g.fill(area);
        g.setColor(Color.WHITE);
        g.draw(area);

        for (Shape shape : shapeList) {
            Territory territory = reverseTerritoryMap.get(shape);
            Color ownerColor = getOwnerColor(territory);
            if (ownerColor != null) {
                Color filledColor = applyHoverColor(ownerColor, shape);
                g.setColor(filledColor);
                g.fill(shape);
            } else {
                // Unoccupied territory
                Color emptyColor = Color.LIGHT_GRAY;
                Color filledColor = applyHoverColor(emptyColor, shape);
                g.setColor(filledColor);
                g.fill(shape);
            }
        }

        g.dispose();
        output.setIcon(new ImageIcon(bi));
    }

    private Color applyHoverColor(Color color, Shape shape) {
        Color hoverColor = color.darker(); // Apply a darker shade for hover effect
        if (output.isShowing()) {
            Point mousePosition = MouseInfo.getPointerInfo().getLocation();
            Point componentLocation = output.getLocationOnScreen();
            int x = mousePosition.x - componentLocation.x;
            int y = mousePosition.y - componentLocation.y;
            Point pointOnComponent = new Point(x, y);

            if (shape.contains(pointOnComponent)) {
                return hoverColor;
            }
        }
        return color;
    }

    private Color getOwnerColor(Territory territory) {
        if (territory != null && Controller.getInstance().getOwner(territory) != null) {
            int playerIndex = Controller.getInstance().getIndex(Controller.getInstance().getOwner(territory));
            return PLAYER_COLORS[playerIndex];
        }
        return null;
    }
    
    private BufferedImage getImage() {
        BufferedImage bi = new BufferedImage(
                2 * SIZE, SIZE, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bi.createGraphics();
        g.drawImage(image, 0, 0, output);
        g.setColor(Color.WHITE);
        g.fill(area);
        g.setColor(Color.WHITE);
        g.draw(area);
        try {
            Point p = MouseInfo.getPointerInfo().getLocation();
            Point p1 = output.getLocationOnScreen();
            int x = p.x - p1.x;
            int y = p.y - p1.y;
            Point pointOnImage = new Point(x, y);
            for (Shape shape : shapeList) {
                if (shape.contains(pointOnImage)) {
                    Color color = getHoveredColor(shape);
                	g.setColor(color);
                    g.fill(shape);
                    break;
                }
            }
        } catch (Exception doNothing) {
        }
        g.dispose();

        return bi;
    }
   
    public static void updateTerritory (int playerIndex, String territoryName, int armyNum) {
    	BufferedImage bi = new BufferedImage(
                2 * SIZE, SIZE, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bi.createGraphics();
    	Territory territory = Controller.getInstance().findTerritory(territoryName);
    	JLabel territoryLabel = territoryLabelMap.get(territory);
    	Shape region = labelShapeMap.get(territoryLabel);
    	Color color = PLAYER_COLORS[playerIndex];
    	g.setColor(color);
    	g.fill(region);
    	territoryLabel.setText(Integer.toString(armyNum));
    	
    }
    
    public JComponent getUI() {
        return ui;
    }

    public void generateMap() {
        Runnable r = () -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            JFrame f = new JFrame(this.getClass().getSimpleName());
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setLocationByPlatform(true);
            f.setExtendedState(JFrame.MAXIMIZED_BOTH);          
            f.setLocation(0,0);
            f.setContentPane(this.getUI());
            f.setResizable(false);
            f.pack();

            f.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }

    public static Territory getTerritoryFromLabel(JLabel label) {
    	return reverseTerritoryMap.get(label);
    }
    
    public static Territory getTerritoryFromRegion(Shape shape) {
    	return reverseTerritoryMap.get(shape);
    }

    public void nextMode() {
    	if(mode == "recruit") {
    		mode = "attack";	
    	}
    	else if(mode == "attack") {
    		mode = "fortify";
    	}
    	else if(mode == "fortify") {
    		mode = "recruit";
    	}
    }
    
    public void addListeners(ActionListener actionListener) {
    	this.attackButton.addActionListener(actionListener);
    	this.fortifyButton.addActionListener(actionListener);	
    	this.nextModeButton.addActionListener(actionListener);
    	this.endTurnButton.addActionListener(actionListener);
    	this.recruitButton.addActionListener(actionListener);
    }
    
    public void refreshSelectedTerritories() {
		this.fromTerritory=null;
		this.toTerritory=null;
	}

    public Color getHoveredColor(Shape shape) {
    	Color regionColor = Color.LIGHT_GRAY;
    	Territory territory = null;
    	try {
    		territory = reverseTerritoryMap.get(shape);
    		if(Controller.getInstance().getOwner(territory) != null) {
    			regionColor = PLAYER_COLORS[Controller.getInstance().getIndex(Controller.getInstance().getOwner(territory))];
    		}
    	}catch(NullPointerException ne) {
    		
    	}

    	return regionColor;
    }
    
    public String getCurrentMode() {
    	return mode;
    }
    
    public JButton getAttackButton() {
    	return this.attackButton;
    }
    
    public JButton getFortifyButton() {
    	return this.fortifyButton;
    }
    
    public JButton getNextModeButton() {
    	return this.nextModeButton;
    }
    
    public JButton getEndTurnButton() {
    	return this.endTurnButton;
    }
      
    public JButton getRecruitButton() {
    	return this.recruitButton;
    }

    public void generateLabels() {
    	int j = 0;
        if(generated == false) {
        	for (int i = 0; i < territories.length; i++) {
        		Territory territory = territories[i];
        		if (territory != null && !territoryLabelMap.containsKey(territory) && territory.getName().charAt(0) != 't') {
        			JLabel label = new JLabel(Integer.toString(Controller.getInstance().getTerritoryArmyNum(territory)));
        			Point labelPosition = new Point(coordinateList[j][0], coordinateList[j][1]);
        			label.setLocation(labelPosition);
        			label.setSize(new Dimension(10, 10));
        			label.setVisible(true);
        			label.setFont(label.getFont().deriveFont(Font.BOLD, 10));
        			label.setOpaque(false);
        			label.setEnabled(false);

        			territoryLabelMap.put(territory, label);
        			reverseLabelMap.put(label, territory);
        			output.add(label);
        			
        			j++;
        		}
        	}
        	generated = true;
        }
    }
}



