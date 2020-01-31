import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Timer;

public class BejeweledGrid {
	private Jewel[][] jewels;
	private Timer timer = new Timer();
	private int clicks = 0;
	private int[] loc = new int[2];
	private int points = 0;
	private int r1 = 0;
	private int c1 = 0;
	/** Offsets for drawing the grid of Jewels*/
	public static final int OFFSET_X = 40, OFFSET_Y = 80;
	private static final double JEWEL_CHOICES = 7;
	private static final int POINTS_INCREMENT = 1; 

	public BejeweledGrid() {
		jewels = new Jewel[8][8];
		refill();
		clearMatches();
	}

	public void clearMatches() {
		int size = 0;
		size = _3InARow().size();
		drop();
		refill();
		if(size > 2)
			clearMatches();
	}

	public void clearSame(ArrayList<Jewel> list) {
		System.out.println(list);
		list = new ArrayList<Jewel>(new LinkedHashSet<Jewel>(list));
		System.out.println(list);
	}

	/** directs each Jewel to draw itself.  The Jewel knows its row
	 * and column and can ask the grid for the offset info
	 * @param g Graphics context onto which the Jewels will draw themselves
	 */
	public void draw(Graphics g) {
		Font f = new Font("Arial Bold", Font.PLAIN, 50);
		for(Jewel[] r : jewels) {
			for(Jewel j : r) {
				j.draw(g);
			}
		}
		g.setColor(Color.YELLOW);
		g.setFont(f);
		g.drawString("Points: "+points, 50, 50);
	}

	/** swaps the Jewels at the specified locations.  Also must
	 * tell the Jewels to set their rows and cols appropriately.
	 * @param r1 row of Jewel 1
	 * @param c1 col of Jewel 1
	 * @param r2 row of Jewel 2
	 * @param c2 col of Jewel 2
	 */
	public void swap(int r1, int c1, int r2, int c2) {
		Jewel temp = jewels[r1][c1];
		jewels[r1][c1] = jewels[r2][c2];
		jewels[r2][c2].moveTo(r1, c1);
		jewels[r2][c2] = temp;
		temp.moveTo(r2, c2);		
	}

	/** Creates an ArrayList<Jewel> of all Jewels that are part of a
	 * three-in-a-row.  The same Jewel may appear in the List more than
	 * once.
	 * @return
	 */
	public ArrayList<Jewel> _3InARow() {
		ArrayList<Jewel> list = _3InARowHor();
		//		ArrayList<Jewel> list = _5InARowHor();
		//		list.addAll(_5InARowHor());
		list.addAll(_3InARowVert());
		clearSame(list);
		for(Jewel j : list) {
			jewels[j.getRow()][j.getCol()] = null;
			points += POINTS_INCREMENT;
		}
		return list;
	}

	/**
	 * 
	 * @return returns an ArrayList of Jewels that make up three or more in 
	 *         a row vertically or an empty list if fewer than 3 in a row exist
	 */
	private ArrayList<Jewel> _3InARowVert() {
		ArrayList<Jewel> list = new ArrayList<Jewel>();
		for(int r=0; r<jewels.length-2; r++) {
			for(int c=0; c<jewels[r].length; c++) {
				Jewel x = jewels[r][c];
				Jewel y = jewels[r+1][c];
				Jewel z = jewels[r+2][c];
				if(x.getColor().equals(y.getColor())&&
						y.getColor().equals(z.getColor())) {
					list.add(x);
					list.add(y);
					list.add(z);
				}
			}
		}
		return list;
	}
	/**  
	 * @return returns an ArrayList of Jewels that make up three or more in 
	 *         a row horizontally or an empty list if fewer than 3 in a row exist
	 */
	private ArrayList<Jewel> _3InARowHor() {
		ArrayList<Jewel> list = new ArrayList<Jewel>();
		for(int r=0; r<jewels.length; r++) {
			for(int c=0; c<jewels[r].length-2; c++) {
				Jewel x = jewels[r][c];
				Jewel y = jewels[r][c+1];
				Jewel z = jewels[r][c+2];
				if(x.equals(y) && y.equals(z)) {
					list.add(x);
					list.add(y);
					list.add(z);
				}
			}
		}
		return list;
	}

	private ArrayList<Jewel> _5InARowHor() {
		ArrayList<Jewel> list = new ArrayList<Jewel>();
		for(int r=0; r<jewels.length; r++) {
			for(int c=0; c<jewels[r].length-4; c++) {
				Jewel i = jewels[r][c];
				Jewel j = jewels[r][c+1];
				Jewel k = jewels[r][c+2];
				Jewel l = jewels[r][c+3];
				Jewel m = jewels[r][c+4];
				if(i.getColor().equals(j.getColor())&& j.getColor().equals(k.getColor()) 
						&& k.getColor().equals(k.getColor())&& k.getColor().equals(m.getColor())) {
					list.add(i);
					list.add(j);
					list.add(l);
					list.add(m);
					jewels[k.getRow()][k.getCol()]= new Hypercube(r,c);
				}
			}
		}
		return list;
	}
	/**
	 * This method drops all Jewels that should drop...
	 * Basically, any Jewel that has no Jewel below it needs to 
	 * be in a higher row.  Lots of ways to approach this.
	 */
	public void drop() {
		for(int r=jewels.length-2; r>-1; r--) {
			for(int c=jewels[r].length-1; c>0; c--) {
				if(jewels[r][c] != null) {
					int x = r;
					while(inbounds(x+1,c) && jewels[x+1][c] == null) {
						jewels[x][c].moveDown();
						jewels[x+1][c] = jewels[x][c];
						jewels[x][c] = null;
						x++;
					}
				}
			}
		}
	}

	/*Fill in any empty positions in the grid with randomly selected Jewel
	 */
	public void refill() {
		for(int r=0; r<jewels.length; r++) {
			for(int c=0; c<jewels[r].length; c++) {
				if(jewels[r][c] == null)
					jewels[r][c] = randomJewel(r,c);
			}
		}
	}

	/**Generates a random Jewel that will have its starting location
	 * at the specified row and col.
	 * @param r row where the Jewel will be placed
	 * @param c col where the Jewel will be placed
	 * @return random type of Jewel constructed at r,c
	 */
	private Jewel randomJewel(int r, int c) {
		int x = (int) (Math.random() * JEWEL_CHOICES);
		if(x==0) 
			return new Emerald(r,c);
		if(x==1) 
			return new Ruby(r,c);
		if(x==2) 
			return new Topaz(r,c);
		if(x==3) 
			return new Sapphire(r,c);
		if(x==4) 
			return new Diamond(r,c);
		if(x==5) 
			return new Garnet(r,c);
		if(x==6)
			return new Amethyst(r,c);
		return null;
	}
	/**This method is called by the game when a click has been made 
	 * on the panel.  Must determine if the click makes sense (is it 
	 * a valid location, is there a Jewel at the location, can that 
	 * Jewel be clicked).  If it is the first click, then note it and
	 * the next click will attempt a move (maybe).
	 * @param me
	 */
	public void justClicked(MouseEvent me) {
		clicks++;
		int r = (me.getY()-BejeweledGrid.OFFSET_Y)/Jewel.SQUARE_SIZE;
		int c = (me.getX()-BejeweledGrid.OFFSET_X)/Jewel.SQUARE_SIZE;
		if(inbounds(r,c)) {
			if(clicks == 1) {
				r1 = r;
				c1 = c;
				jewels[r][c].show();
			}
			if(clicks == 2) {
				if(adjacent(r1, c1, r, c)) {	
					swap(r1, c1, r, c);
					clearMatches();
				}
				jewels[r][c].hide();
				clicks = 0;
			}
		}
		else 
			clicks=0;
	}

	private boolean adjacent(int r1, int c1, int r2, int c2) {
		if(r1==r2 && c1==c2-1)
			return true;
		if(r1==r2 && c1==c2+1)
			return true;
		if(c1==c2 && r1==r2-1)
			return true;
		if(c1==c2 && r1==r2+1)
			return true;
		return false;
	}

	public boolean inbounds(int r, int c) {
		if(r<jewels.length && r>=0 && c<jewels[0].length && c>=0)
			return true;
		return false;
	}
}
