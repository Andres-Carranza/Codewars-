/*
Programmer: Andres Carranza
This class acts as a checker for a Battle Ship game
The promt for this class can be found at https://www.codewars.com/kata/52bb6539a4cf1b12d90005b7
*/

public class BattleField {
	private static int shipval[][] = new int[10][10];
	private static int bval[][] = new int[12][12];
	private static int battleshipx,battleshipy;
	private static boolean battleship;//true means vertical
	private static int[] cruiserx = new int[2],cruisery = new int[2];
	private static boolean[] cruiser = new boolean[2];
	private static int[] destroyerx = new int[3],destroyery = new int[3];
	private static boolean[] destroyer = new boolean[3];
	private static int[] submarinex = new int[4],submariney = new int[4];
	public static boolean fieldValidator(int[][] field) {
		if(!shipValidator(field))
			return false;
		if(!border(field))
			return false;
		return true;
	}
	public static boolean border(int[][] field) {
		for(int i =0; i< 12; i++)
			bval[0][i]=0;
		for(int i =0; i< 12; i++)
			bval[11][i]=0;
		for(int i =0; i< 12; i++)
			bval[i][0]=0;
		for(int i =0; i< 12; i++)
			bval[i][11]=0;
		for(int y=0; y<field.length;y++) {
			for(int x =0; x<field[0].length;x++) {
				bval[y+1][x+1]= field[y][x];
			}
		}
		if(!btlshp())
			return false;
		if(!crsr(0))
			return false;
		if(!crsr(1))
			return false;
		if(!dstryr(0))
			return false;
		if(!dstryr(1))
			return false;
		if(!dstryr(2))
			return false;
		if(!sbmrn(0))
			return false;
		if(!sbmrn(1))
			return false;
		if(!sbmrn(2))
			return false;
		if(!sbmrn(3))
			return false;
		return true;
	}
	public static boolean btlshp() {
		if(battleship) {
			if(bval[battleshipy-1][battleshipx]==1)
				return false;
			else if(bval[battleshipy-1][battleshipx+1]==1)
				return false;
			else if(bval[battleshipy][battleshipx+1]==1)
				return false;
			else if(bval[battleshipy+1][battleshipx+1]==1)
				return false;
			else if(bval[battleshipy+2][battleshipx+1]==1)
				return false;
			else if(bval[battleshipy+3][battleshipx+1]==1)
				return false;
			else if(bval[battleshipy+4][battleshipx+1]==1)
				return false;
			else if(bval[battleshipy+4][battleshipx]==1)
				return false;
			else if(bval[battleshipy+4][battleshipx-1]==1)
				return false;
			else if(bval[battleshipy+3][battleshipx-1]==1)
				return false;
			else if(bval[battleshipy+2][battleshipx-1]==1)
				return false;
			else if(bval[battleshipy+1][battleshipx-1]==1)
				return false;
			else if(bval[battleshipy][battleshipx-1]==1)
				return false;
			else if(bval[battleshipy-1][battleshipx-1]==1)
				return false;
		}
		else {
			if(bval[battleshipy][battleshipx-1]==1)
				return false;
			else if(bval[battleshipy-1][battleshipx-1]==1)
				return false;
			else if(bval[battleshipy-1][battleshipx]==1)
				return false;
			else if(bval[battleshipy-1][battleshipx+1]==1)
				return false;
			else if(bval[battleshipy-1][battleshipx+2]==1)
				return false;
			else if(bval[battleshipy-1][battleshipx+3]==1)
				return false;
			else if(bval[battleshipy-1][battleshipx+4]==1)
				return false;
			else if(bval[battleshipy][battleshipx+4]==1)
				return false;
			else if(bval[battleshipy+1][battleshipx+4]==1)
				return false;
			else if(bval[battleshipy+1][battleshipx+3]==1)
				return false;
			else if(bval[battleshipy+1][battleshipx+2]==1)
				return false;
			else if(bval[battleshipy+1][battleshipx+1]==1)
				return false;
			else if(bval[battleshipy+1][battleshipx]==1)
				return false;
			else if(bval[battleshipy+1][battleshipx-1]==1)
				return false;
		}
		return true;
	}
	public static boolean crsr(int i) {
		if(cruiser[i]) {
			if(bval[cruisery[i]-1][cruiserx[i]]==1)
				return false;
			else if(bval[cruisery[i]-1][cruiserx[i]+1]==1)
				return false;
			else if(bval[cruisery[i]][cruiserx[i]+1]==1)
				return false;
			else if(bval[cruisery[i]+1][cruiserx[i]+1]==1)
				return false;
			else if(bval[cruisery[i]+2][cruiserx[i]+1]==1)
				return false;
			else if(bval[cruisery[i]+3][cruiserx[i]+1]==1)
				return false;
			else if(bval[cruisery[i]+3][cruiserx[i]]==1)
				return false;
			else if(bval[cruisery[i]+3][cruiserx[i]-1]==1)
				return false;
			else if(bval[cruisery[i]+2][cruiserx[i]-1]==1)
				return false;
			else if(bval[cruisery[i]+1][cruiserx[i]-1]==1)
				return false;
			else if(bval[cruisery[i]][cruiserx[i]-1]==1)
				return false;
			else if(bval[cruisery[i]-1][cruiserx[i]-1]==1)
				return false;
		}
		else {
			if(bval[cruisery[i]][cruiserx[i]-1]==1)
				return false;
			else if(bval[cruisery[i]-1][cruiserx[i]-1]==1)
				return false;
			else if(bval[cruisery[i]-1][cruiserx[i]]==1)
				return false;
			else if(bval[cruisery[i]-1][cruiserx[i]+1]==1)
				return false;
			else if(bval[cruisery[i]-1][cruiserx[i]+2]==1)
				return false;
			else if(bval[cruisery[i]-1][cruiserx[i]+3]==1)
				return false;
			else if(bval[cruisery[i]][cruiserx[i]+3]==1)
				return false;
			else if(bval[cruisery[i]+1][cruiserx[i]+3]==1)
				return false;
			else if(bval[cruisery[i]+1][cruiserx[i]+2]==1)
				return false;
			else if(bval[cruisery[i]+1][cruiserx[i]+1]==1)
				return false;
			else if(bval[cruisery[i]+1][cruiserx[i]]==1)
				return false;
			else if(bval[cruisery[i]+1][cruiserx[i]-1]==1)
				return false;
		}
		return true;
	}
	public static boolean dstryr(int i) {
		if(destroyer[i]) {
			if(bval[destroyery[i]-1][destroyerx[i]]==1)
				return false;
			else if(bval[destroyery[i]-1][destroyerx[i]+1]==1)
				return false;
			else if(bval[destroyery[i]][destroyerx[i]+1]==1)
				return false;
			else if(bval[destroyery[i]+1][destroyerx[i]+1]==1)
				return false;
			else if(bval[destroyery[i]+2][destroyerx[i]+1]==1)
				return false;
			else if(bval[destroyery[i]+2][destroyerx[i]]==1)
				return false;
			else if(bval[destroyery[i]+2][destroyerx[i]-1]==1)
				return false;
			else if(bval[destroyery[i]+1][destroyerx[i]-1]==1)
				return false;
			else if(bval[destroyery[i]][destroyerx[i]-1]==1)
				return false;
			else if(bval[destroyery[i]-1][destroyerx[i]-1]==1)
				return false;
		}
		else {
			if(bval[destroyery[i]][destroyerx[i]-1]==1)
				return false;
			else if(bval[destroyery[i]-1][destroyerx[i]-1]==1)
				return false;
			else if(bval[destroyery[i]-1][destroyerx[i]]==1)
				return false;
			else if(bval[destroyery[i]-1][destroyerx[i]+1]==1)
				return false;
			else if(bval[destroyery[i]-1][destroyerx[i]+2]==1)
				return false;
			else if(bval[destroyery[i]][destroyerx[i]+2]==1)
				return false;
			else if(bval[destroyery[i]+1][destroyerx[i]+2]==1)
				return false;
			else if(bval[destroyery[i]+1][destroyerx[i]+1]==1)
				return false;
			else if(bval[destroyery[i]+1][destroyerx[i]]==1)
				return false;
			else if(bval[destroyery[i]+1][destroyerx[i]-1]==1)
				return false;
		}
		return true;	
	}
	public static boolean sbmrn(int i) {
			if(bval[submariney[i]-1][submarinex[i]]==1)
				return false;
			else if(bval[submariney[i]-1][submarinex[i]+1]==1)
				return false;
			else if(bval[submariney[i]][submarinex[i]+1]==1)
				return false;
			else if(bval[submariney[i]+1][submarinex[i]+1]==1)
				return false;
			else if(bval[submariney[i]+1][submarinex[i]]==1)
				return false;
			else if(bval[submariney[i]+1][submarinex[i]-1]==1)
				return false;
			else if(bval[submariney[i]][submarinex[i]-1]==1)
				return false;
			else if(bval[submariney[i]-1][submarinex[i]-1]==1)
				return false;
		return true;
	}
	public static boolean shipValidator(int[][] field) {
		for(int y =0; y<field.length; y++) {
			for(int x =0; x<field[0].length;x++) {
				shipval[y][x]= field[y][x];
			}
		}
		if(!battleship())
			return false;
		if(!cruiser(0))
			return false;
		if(!cruiser(1))
			return false;
		if(!destroyer(0))
			return false;
		if(!destroyer(1))
			return false;
		if(!destroyer(2))
			return false;
		if(!submarine(0))
			return false;
		if(!submarine(1))
			return false;
		if(!submarine(2))
			return false;
		if(!submarine(3))
			return false;
		for(int y =0; y<shipval.length;y++) {
			for(int x =0; x<shipval[0].length;x++) {
				if(shipval[y][x]==1)
					return false;
			}
		}
		return true;
	}
	public static boolean battleship() {
		for(int y =0; y<shipval.length;y++) {
			for(int x =0; x<shipval[0].length; x++) {
				if(shipval[y][x]==1) {
					if(y<7&&shipval[y+1][x]==1&&shipval[y+2][x]==1&&shipval[y+3][x]==1) {
						battleship = true;
						battleshipy = y+1;
						battleshipx = x+1;
						deleteShip(true,y,x,4);
						return true;
					}
					if(x<7&&shipval[y][x+1]==1&&shipval[y][x+2]==1&&shipval[y][x+3]==1) {
						battleship = false;
						battleshipy = y+1;
						battleshipx = x+1;
						deleteShip(false,y,x,4);
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean cruiser(int i) {
		for(int y =0; y<shipval.length;y++) {
			for(int x =0; x<shipval[0].length; x++) {
				if(shipval[y][x]==1) {
					if(y<7&&shipval[y+1][x]==1&&shipval[y+2][x]==1) {
						cruiser[i] = true;
						cruisery[i] = y+1;
						cruiserx[i] = x+1;
						deleteShip(true,y,x,3);
						return true;
					}
					if(x<7&&shipval[y][x+1]==1&&shipval[y][x+2]==1) {
						cruiser[i] = false;
						cruisery[i] = y+1;
						cruiserx[i] = x+1;
						deleteShip(false,y,x,3);
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean destroyer(int i) {
		for(int y =0; y<shipval.length;y++) {
			for(int x =0; x<shipval[0].length; x++) {
				if(shipval[y][x]==1) {
					if(y<7&&shipval[y+1][x]==1) {
						destroyer[i] = true;
						destroyery[i] = y+1;
						destroyerx[i] = x+1;
						deleteShip(true,y,x,2);
						return true;
					}
					if(x<7&&shipval[y][x+1]==1) {
						destroyer[i] = false;
						destroyery[i] = y+1;
						destroyerx[i] = x+1;
						deleteShip(false,y,x,2);
						return true;
					}
				}
			}
		}
		return false;

	}
	public static boolean submarine(int i) {
		for(int y =0; y<shipval.length;y++) {
			for(int x =0; x<shipval[0].length; x++) {
				if(shipval[y][x]==1) {
					submariney[i] = y+1;
					submarinex[i] = x+1;
					deleteShip(true,y,x,1);
					return true;
				}
			}
		}
		return false;

	}
	public static void deleteShip(boolean ver, int starty, int startx, int length) {
		for(int i =0; i<length;i++) {
			if(ver)
				shipval[starty+i][startx]= 0;
			else
				shipval[starty][startx+i]= 0;

		}
	}
}
