package gui;

import java.util.Deque;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Peg;
import model.TowerOfHanoi;

/**
 * This view supports the rendering of all game elements 
 * in a Tower of Hanoi simulation. The game and canvas objects
 * are supplied to the constructor, and this object is called
 * upon anytime the game elements need to be redrawn.
 * 
 * @author James
 */
public class TowerOfHanoiView {

	private static final Color OUTLINE_COLOR = Color.BLACK;
	private static final double OUTLINE_WIDTH = 1.5;
	private static final Color BASE_COLOR = Color.SIENNA;
	private static final Color PEG_COLOR = Color.SILVER;
	private static final Color DISK_1_COLOR = Color.CRIMSON;
	private static final Color DISK_2_COLOR = Color.ORANGERED;
	private static final Color DISK_3_COLOR = Color.YELLOW;
	private static final Color DISK_4_COLOR = Color.GREEN;
	private static final Color DISK_5_COLOR = Color.BLUE;
	private static final Color DISK_6_COLOR = Color.PURPLE;
	private static final Color DISK_7_COLOR = Color.TEAL;
	private static final Color DISK_8_COLOR = Color.MIDNIGHTBLUE;
	private static final Color DEFAULT_DISK_COLOR = Color.WHITE;
	
	// Dimensions are specified as fractions of the canvas width and height, 
	// not including the margins.
	private static final double MARGIN = 0.05;
	private static final double BASE_WIDTH = 1;
	private static final double BASE_HEIGHT = 0.1;
	private static final double BASE_X = 0;
	private static final double BASE_Y = 1 - BASE_HEIGHT;
	private static final double PEG_WIDTH = 0.03;
	private static final double PEG_HEIGHT = 1 - BASE_HEIGHT;
	private static final double PEG_LEFT_X = 1.0 / 6.0;
	private static final double PEG_MIDDLE_X = 3.0 / 6.0;
	private static final double PEG_RIGHT_X = 5.0 / 6.0;
	private static final double PEG_Y = 0;
	private static final double DISK_HEIGHT = 0.11;
	private static final double MIN_DISK_RADIUS = PEG_WIDTH;
	private static final double MAX_DISK_RADIUS = (PEG_MIDDLE_X-PEG_LEFT_X) / 2;
	private static final double DISK_ARC_WIDTH = 0.6;

	private GraphicsContext graphicsContext;
	private double horizontalMargin;
	private double verticalMargin;
	private double contentWidth;
	private double contentHeight;
	
	private TowerOfHanoi game;
	private double[] diskRadii;
	
	/**
	 * Explicit Constructor, requires game instance 
	 * as well as canvas from main view class.
	 * 
	 * @param game game model instance
	 * @param canvas canvas object
	 */
	public TowerOfHanoiView(TowerOfHanoi game, Canvas canvas) {
		graphicsContext = canvas.getGraphicsContext2D();
		double canvasWidth = canvas.getWidth();
		double canvasHeight = canvas.getHeight();
		horizontalMargin = MARGIN * canvasWidth;
		verticalMargin = MARGIN * canvasHeight;
		contentWidth = canvasWidth - 2*horizontalMargin;
		contentHeight = canvasHeight - 2*verticalMargin;
		
		this.game = game;
		int numDisks = calcNumDisks(game);
		diskRadii = calcDiskRadii(numDisks, contentWidth);
	}
	
	private static int calcNumDisks(TowerOfHanoi game) {
		int numDisks = 0;
		for (Peg peg : Peg.values()) {
			numDisks += game.getDiskStack(peg).size();
		}
		return numDisks;
	}
	
	private static double[] calcDiskRadii(int numDisks, double contentWidth) {
		double[] radii = new double[numDisks];
		double delta = (MAX_DISK_RADIUS - MIN_DISK_RADIUS) / numDisks;
		for (int idx = 0; idx < radii.length; ++idx) {
			radii[idx] = contentWidth * (MIN_DISK_RADIUS + (idx+1)*delta);
		}
		return radii;
	}
	
	/**
	 * TODO Draw all components of the game on the canvas.
	 * Drawn components include: 3 pegs, 1 base, and all disks.
	 * Please make use of the "unused" methods below.
	 */
	//I got help from Alexis Munoz
	public void draw() {
		
		drawBase();
		drawPeg(Peg.LEFT);
		drawPeg(Peg.MIDDLE);
		drawPeg(Peg.RIGHT);
		
		//create Deque to store the size.
		Deque<Integer> diskLeft = game.getDiskStack(Peg.LEFT);
		
		for(int i=0; i<game.getDiskStack(Peg.LEFT).size(); ++i) {
			//Draw the current biggest disk in the Deque<Integer> on the left peg.
			//At height i, and remove the last disk.
			drawDisk(Peg.LEFT, i, diskLeft.pollLast());
		}
		Deque<Integer> diskMiddle = game.getDiskStack(Peg.MIDDLE);
		for(int i=0; i<game.getDiskStack(Peg.MIDDLE).size(); ++i) {
			drawDisk(Peg.MIDDLE, i, diskMiddle.pollLast());
		}
		Deque<Integer> diskRight = game.getDiskStack(Peg.RIGHT);
		for(int i=0; i<game.getDiskStack(Peg.RIGHT).size(); ++i) {
			drawDisk(Peg.RIGHT, i, diskRight.pollLast());
		}
		
	}

	
	/**
	 * Draws the base rectangle, where the 3 pegs are attached.
	 */
	private void drawBase() {
		double baseX = horizontalMargin + BASE_X * contentWidth;
		double baseY = verticalMargin + BASE_Y * contentHeight;
		double baseWidth = BASE_WIDTH * contentWidth;
		double baseHeight = BASE_HEIGHT * contentHeight;
		graphicsContext.setFill(BASE_COLOR);
		graphicsContext.fillRect(baseX, baseY, baseWidth, baseHeight);
		graphicsContext.setStroke(OUTLINE_COLOR);
		graphicsContext.setLineWidth(OUTLINE_WIDTH);
		graphicsContext.strokeRect(baseX, baseY, baseWidth, baseHeight);
	}

	
	/**
	 * Draws a given peg at its intended location. Location is
	 * determined based on peg's enum value.
	 * 
	 * @param peg a member of enum Peg; represents the peg being drawn
	 */
	private void drawPeg(Peg peg) {
		double pegX = calcPegX(peg);
		double pegY = verticalMargin + PEG_Y * contentHeight;
		double pegWidth = PEG_WIDTH * contentWidth;
		double pegHeight = PEG_HEIGHT * contentHeight;
		graphicsContext.setFill(PEG_COLOR);
		graphicsContext.fillRect(pegX, pegY, pegWidth, pegHeight);
		graphicsContext.setStroke(OUTLINE_COLOR);
		graphicsContext.setLineWidth(OUTLINE_WIDTH);
		graphicsContext.strokeRect(pegX, pegY, pegWidth, pegHeight);
	}
	
	/**
	 * Calculates the X-coordinate for the top-left pixel
	 * of the specified peg (left, middle, or right).
	 * 
	 * @param peg Peg enum value representing desired peg
	 * @return X-coordinate of top-left pixel of desired peg
	 */
	private double calcPegX(Peg peg) {
		double pegCenter;
		if (peg == Peg.LEFT) {
			pegCenter = PEG_LEFT_X;
		} else if (peg == Peg.MIDDLE) {
			pegCenter = PEG_MIDDLE_X;
		} else {  // peg == Peg.RIGHT
			pegCenter = PEG_RIGHT_X;
		}
		return horizontalMargin + contentWidth*(pegCenter - (PEG_WIDTH/2));
	}

	
	/**
	 * Draw a disk onto the canvas with the specified parameters.
	 * 
	 * @param peg the Peg that this disk belongs to
	 * @param height the number of disks below the one being drawn.
	 * 				 Note that this is in the range [0, numDisks - 1]
	 * @param disk the disk number (analogous to disk size)
	 */
	private void drawDisk(Peg peg, int height, int disk) {
		double diskRadius = diskRadii[disk - 1];
		double diskX = calcDiskX(peg, diskRadius);
		double diskY = calcDiskY(height);
		double diskWidth = 2 * diskRadius;
		double diskHeight = DISK_HEIGHT * contentHeight;
		
		graphicsContext.setFill(getDiskColor(disk));
		graphicsContext.fillRoundRect(diskX, diskY, diskWidth, diskHeight, 
				DISK_ARC_WIDTH*diskWidth, diskHeight);
		graphicsContext.setStroke(OUTLINE_COLOR);
		graphicsContext.setLineWidth(OUTLINE_WIDTH);
		graphicsContext.strokeRoundRect(diskX, diskY, diskWidth, diskHeight, 
				DISK_ARC_WIDTH*diskWidth, diskHeight);
	}

	
	/**
	 * Calculates the X-coordinate value for a disk. The disk
	 * is represented as a combination of a peg and radius, since
	 * this is the only information needed.
	 * 
	 * @param peg peg which is holding the disk
	 * @param diskRadius radius of the disk, computed previously
	 * @return X-coordinate value of the top-left pixel of the disk
	 */
	private double calcDiskX(Peg peg, double diskRadius) {
		double diskCenter;
		if (peg == Peg.LEFT) {
			diskCenter = PEG_LEFT_X;
		} else if (peg == Peg.MIDDLE) {
			diskCenter = PEG_MIDDLE_X;
		} else {  // peg == Peg.RIGHT
			diskCenter = PEG_RIGHT_X;
		}
		return horizontalMargin + contentWidth*diskCenter - diskRadius;
	}

	/**
	 * Calculates the Y-coordinate value for a disk. The height of the
	 * disk is the only determining factor here, so it is also the only
	 * parameter.
	 * 
	 * @param height the height of the disk (analogous to the number of disks below this one
	 * @return Y-coordinate value of the top-left pixel of the disk
	 */
	private double calcDiskY(int height) {
		double aboveBase = (height + 1) * DISK_HEIGHT;
		return verticalMargin + contentHeight*(1 - BASE_HEIGHT - aboveBase);
	}

	
	/**
	 * A simple mapping of the 5 possible disks to their respective colors.
	 * Given a disk number, returns the proper color for that disk.
	 * 
	 * @param disk the number/value of the disk
	 * @return the desired color for this disk
	 */
	private static Color getDiskColor(int disk) {
		switch (disk) {
			case 1: return DISK_1_COLOR;
			case 2: return DISK_2_COLOR;
			case 3: return DISK_3_COLOR;
			case 4: return DISK_4_COLOR;
			case 5: return DISK_5_COLOR;
			case 6: return DISK_6_COLOR;
			case 7: return DISK_7_COLOR;
			case 8: return DISK_8_COLOR;
			default: return DEFAULT_DISK_COLOR;
		}
	}
}
