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
    private static final int SIZE = 750;
    private BufferedImage image;
    private static boolean generated = false;
    
    private JLabel output = new JLabel();
    private JComponent ui = null;
    private JButton recruitButton = new JButton("Recruit");
	private JButton attackButton = new JButton("Attack");
	private JButton fortifyButton = new JButton("Fortify");
	private JButton endTurnButton = new JButton("End Turn");
	private JButton nextModeButton = new JButton("Next Mode");
	private JButton exitButton = new JButton("Exit");
    
	private int[][] coordinateList = {
    		{182,69}, //northWestTerritory
    		{506,83}, //iceland
    		{453,36}, //greenland
    		{78,75}, //alaska
    		{605,88}, //scandinavia
    		{991,84}, //yakutsk
    		{526,120}, //britain
    		{788,92}, //ural
    		{211,112}, //ontario
    		{140,112}, //alberta
    		{603,129}, //northernEurope
    		{1005,131}, //irkutsk
    		{873,84}, //siberia
    		{304,125}, //quebec
    		{1061,77}, //kamchatka
    		{707,103}, //ukraine
    		{783,148}, //afghanistan
    		{631,160}, //southernEurope
    		{535,183}, //westernEurope
    		{119,179}, //westernUnitedStates
    		{983,159}, //mongolia
    		{1095,204}, //japan
    		{207,186}, //easternUnitedStates
    		{649,237}, //egypt
    		{923,208}, //china
    		{724,212}, //middleEast
    		{857,246}, //india
    		{131,250}, //centralAmerica
    		{545,269}, //northAfrica
    		{969,289}, //slam
    		{258,334}, //venezuela
    		{682,309}, //eastAfrica
    		{627,361}, //congo
    		{1023,368}, //indonesia
    		{1141,390}, //newGuiena
    		{245,406}, //peru
    		{328,405}, //brazil
    		{746,449}, //madagascar
    		{648,450}, //southAfrica
    		{1048,487}, //westernAustralia
    		{1123,486}, //easternAustralia
    		{296,514}, //argentina		   		 
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
        String filePath = "C:\\Users\\liqui\\Desktop\\map_with_black_outline.png";
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
        	territoryMap.put(territories[i], shapeList.get(i));	
        	reverseTerritoryMap.put(shapeList.get(i), territories[i]);       	
        	if(territories[i] != null) {
        		if(territoryLabelMap.get(territories[i]) == null) {
        			System.out.println("hereIam");
        		}
        		labelShapeMap.put(territoryLabelMap.get(territories[i]), shapeList.get(i));
            	reverseLabelShapeMap.put(shapeList.get(i), territoryLabelMap.get(territories[i]));          	
        	}
        	
        }
        
        for (Map.Entry<Territory, JLabel> entry : territoryLabelMap.entrySet()) {
            Territory territory = entry.getKey();
            JLabel label = entry.getValue();
            System.out.println("Territory: " + territory + ", Label: " + label);
        }
        
        for (Map.Entry<JLabel, Shape> entry : labelShapeMap.entrySet()) {
            JLabel label = entry.getKey();
            Shape region = entry.getValue();
            System.out.println("Label: " + label + ", Shape: " + region);
        }
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(recruitButton);
        buttonPanel.add(attackButton);
        buttonPanel.add(fortifyButton);
        buttonPanel.add(endTurnButton);
        buttonPanel.add(nextModeButton);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(output,BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);
        
//        JFrame frame = new JFrame("Example UI");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(mainPanel);
//        
//        // Display the frame
//        frame.pack();
//        frame.setVisible(true);
        
        System.out.println(shapeList.size());
        ui = new JPanel(new BorderLayout(4, 4));
        ui.setBorder(new EmptyBorder(4, 4, 4, 4));
        output.addMouseMotionListener(new MousePositionListener());
        output.addMouseListener(new MouseClickListener());
        addListeners(new BoardActionListener());
        output.setLayout(null);
        
        ui.add(output);
        ui.add(buttonPanel, BorderLayout.EAST);
        refresh();
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
        		if (territory != null && !territoryLabelMap.containsKey(territory)) {
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



