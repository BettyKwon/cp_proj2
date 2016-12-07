package chess;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
//======================================================Don't modify below===============================================================//
enum PieceType {king, queen, bishop, knight, rook, pawn, none}
enum PlayerColor {black, white, none}
	
public class ChessBoard {
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	private JPanel chessBoard;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private Piece[][] chessBoardStatus = new Piece[8][8];
	private ImageIcon[] pieceImage_b = new ImageIcon[7];
	private ImageIcon[] pieceImage_w = new ImageIcon[7];
	private JLabel message = new JLabel("Enter Reset to Start");

	ChessBoard(){
		initPieceImages();
		initBoardStatus();
		initializeGui();
	}
	
	public final void initBoardStatus(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) chessBoardStatus[j][i] = new Piece();
		}
	}
	
	public final void initPieceImages(){
		pieceImage_b[0] = new ImageIcon(new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[1] = new ImageIcon(new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[2] = new ImageIcon(new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[3] = new ImageIcon(new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[4] = new ImageIcon(new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[5] = new ImageIcon(new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		
		pieceImage_w[0] = new ImageIcon(new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[1] = new ImageIcon(new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[2] = new ImageIcon(new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[3] = new ImageIcon(new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[4] = new ImageIcon(new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[5] = new ImageIcon(new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
		pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	}
	
	public ImageIcon getImageIcon(Piece piece){
		if(piece.color.equals(PlayerColor.black)){
			if(piece.type.equals(PieceType.king)) return pieceImage_b[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_b[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_b[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_b[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_b[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_b[5];
			else return pieceImage_b[6];
		}
		else if(piece.color.equals(PlayerColor.white)){
			if(piece.type.equals(PieceType.king)) return pieceImage_w[0];
			else if(piece.type.equals(PieceType.queen)) return pieceImage_w[1];
			else if(piece.type.equals(PieceType.bishop)) return pieceImage_w[2];
			else if(piece.type.equals(PieceType.knight)) return pieceImage_w[3];
			else if(piece.type.equals(PieceType.rook)) return pieceImage_w[4];
			else if(piece.type.equals(PieceType.pawn)) return pieceImage_w[5];
			else return pieceImage_w[6];
		}
		else return pieceImage_w[6];
	}

	public final void initializeGui(){
		gui.setBorder(new EmptyBorder(5, 5, 5, 5));
	    JToolBar tools = new JToolBar();
	    tools.setFloatable(false);
	    gui.add(tools, BorderLayout.PAGE_START);
	    JButton startButton = new JButton("Reset");
	    startButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		initiateBoard();
	    	}
	    });
	    
	    tools.add(startButton);
	    tools.addSeparator();
	    tools.add(message);

	    chessBoard = new JPanel(new GridLayout(0, 8));
	    chessBoard.setBorder(new LineBorder(Color.BLACK));
	    gui.add(chessBoard);
	    ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
	    Insets buttonMargin = new Insets(0,0,0,0);
	    for(int i=0; i<chessBoardSquares.length; i++) {
	        for (int j = 0; j < chessBoardSquares[i].length; j++) {
	        	JButton b = new JButton();
	        	b.addActionListener(new ButtonListener(i, j));
	            b.setMargin(buttonMargin);
	            b.setIcon(defaultIcon);
	            if((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) b.setBackground(Color.WHITE);
	            else b.setBackground(Color.gray);
	            b.setOpaque(true);
	            b.setBorderPainted(false);
	            chessBoardSquares[j][i] = b;
	        }
	    }

	    for (int i=0; i < 8; i++) {
	        for (int j=0; j < 8; j++) chessBoard.add(chessBoardSquares[j][i]);
	        
	    }
	}

	public final JComponent getGui() {
	    return gui;
	}
	
	public static void main(String[] args) {
	    Runnable r = new Runnable() {
	        @Override
	        public void run() {
	        	ChessBoard cb = new ChessBoard();
                JFrame f = new JFrame("Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
	}
		
			//================================Utilize these functions========================================//	
	
	class Piece{
		PlayerColor color;
		PieceType type;
		
		Piece(){
			color = PlayerColor.none;
			type = PieceType.none;
		}
		Piece(PlayerColor color, PieceType type){
			this.color = color;
			this.type = type;
		}
	}
	
	public void setIcon(int x, int y, Piece piece){
		chessBoardSquares[y][x].setIcon(getImageIcon(piece));
		chessBoardStatus[y][x] = piece;
	}
	
	public Piece getIcon(int x, int y){
		return chessBoardStatus[y][x];
	}
	
	public void markPosition(int x, int y){
		chessBoardSquares[y][x].setBackground(Color.pink);
	}
	
	public void unmarkPosition(int x, int y){
		if((y % 2 == 1 && x % 2 == 1)|| (y % 2 == 0 && x % 2 == 0)) chessBoardSquares[y][x].setBackground(Color.WHITE);
		else chessBoardSquares[y][x].setBackground(Color.gray);
	}
	
	public void setStatus(String inpt){
		message.setText(inpt);
	}
	
	public void initiateBoard(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) setIcon(i, j, new Piece());
		}
		setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
		setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
		setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
		setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
		setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
		setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
		for(int i=0;i<8;i++){
			setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
			setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
		}
		setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
		setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
		setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
		setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
		setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
		setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++) unmarkPosition(i, j);
		}
		onInitiateBoard();
	}
//======================================================Don't modify above==============================================================//	
	
//======================================================Implement below=================================================================//		
	class ButtonListener implements ActionListener{
		int x;
		int y;
		ButtonListener(int x, int y){
			this.x = x;
			this.y = y;
		}
		public void actionPerformed(ActionEvent e) {	// Only modify here
			System.out.println(x+" "+y);
			//isCheck = false;
			Piece selectedPiece;
			if(chessBoardSquares[y][x].getBackground() == Color.PINK){
				movePiece(xPrev, yPrev, x, y);
				selectedPiece = getIcon(x,y);
				unmarkAll();
				checkTest = true;
				System.out.println(selectedPiece.type);
				System.out.println(selectedPiece.color);
				isCheck = false;
				markPossibleMoves(selectedPiece.type, selectedPiece.color, x, y);
				//checkStatus(selectedPiece.type, selectedPiece.color, x, y);
				if(isCheck) checkMate();
				unmarkAll();
				toggleTurn();
			}
			else {
				selectedPiece = getIcon(x, y);
				checkTest = false;
				unmarkAll();
				if(selectedPiece.color == turn) 
					markPossibleMoves(selectedPiece.type, selectedPiece.color, x, y);
			}
			xPrev = x;
			yPrev = y;
		}
		public void checkStatus(PieceType type, PlayerColor color, int x, int y) {
			markPossibleMoves(type, color, x, y);
		}
		public void movePiece(int xPrev, int yPrev, int x, int y) {
			Piece target = getIcon(xPrev, yPrev);
			setIcon(xPrev, yPrev, new Piece());
			setIcon(x, y, target);
		}
		public void markPossibleMoves(PieceType type, PlayerColor color, int x, int y) {
			System.out.println("--Mark Possible Moves--");
			Vector<Tuple<Integer, Integer> > coords = new Vector<Tuple<Integer, Integer> >();
			boolean isMoveAttackSame = true; // Set to true if attack range == move range
			boolean jump = false; // Set to true for Knight
			if(type == PieceType.pawn) {
				isMoveAttackSame = false;
				if(color == PlayerColor.black) {
					coords.addElement(new Tuple<Integer, Integer>(x+1, y));
					if(x == 1)
						coords.addElement(new Tuple<Integer, Integer>(x+2, y));
					/* mark possible attacks */
					if(isAttackable(x+1, y+1)) markPositionInRange(x+1, y+1);
					if(isAttackable(x+1, y-1)) markPositionInRange(x+1, y-1);
				}
				else if(color == PlayerColor.white) {
					coords.addElement(new Tuple<Integer, Integer>(x-1, y));
					if(x == 6)
						coords.addElement(new Tuple<Integer, Integer>(x-2, y));
					/* mark possible attacks */
					if(isAttackable(x-1, y-1)) markPositionInRange(x-1, y-1);
					if(isAttackable(x-1, y+1)) markPositionInRange(x-1, y+1);
				}
				markPositionsInRange(coords, isMoveAttackSame, jump);
			} // END OF PAWN
			else if(type == PieceType.rook) {
				for(int i = x-1; i >= 0; i--) coords.addElement(new Tuple<Integer, Integer>(i, y));
				markPositionsInRange(coords, isMoveAttackSame, jump);
				coords.clear();
				for(int i = x+1; i < 8; i++) coords.addElement(new Tuple<Integer, Integer>(i, y));
				markPositionsInRange(coords, isMoveAttackSame, jump);
				coords.clear();
				for(int i = y-1; i >= 0; i--) coords.addElement(new Tuple<Integer, Integer>(x, i));
				markPositionsInRange(coords, isMoveAttackSame, jump);
				coords.clear();
				for(int i = y+1; i < 8; i++) coords.addElement(new Tuple<Integer, Integer>(x, i));
				markPositionsInRange(coords, isMoveAttackSame, jump);
				coords.clear();
			}
			else if(type == PieceType.knight) {
				jump = true;
				coords.addElement(new Tuple<Integer, Integer>(x-2, y-1));
				coords.addElement(new Tuple<Integer, Integer>(x-1, y-2));
				coords.addElement(new Tuple<Integer, Integer>(x-2, y+1));
				coords.addElement(new Tuple<Integer, Integer>(x-1, y+2));
				coords.addElement(new Tuple<Integer, Integer>(x+2, y+1));
				coords.addElement(new Tuple<Integer, Integer>(x+1, y+2));
				coords.addElement(new Tuple<Integer, Integer>(x+2, y-1));
				coords.addElement(new Tuple<Integer, Integer>(x+1, y-2));
				markPositionsInRange(coords, isMoveAttackSame, jump);
			}
			else if(type == PieceType.bishop) {
				for(int i = 1; i < 7; i++) coords.addElement(new Tuple<Integer, Integer>(x+i, y+i));
				markPositionsInRange(coords, isMoveAttackSame, jump);
				coords.clear();
				for(int i = 1; i < 7; i++) coords.addElement(new Tuple<Integer, Integer>(x+i, y-i));
				markPositionsInRange(coords, isMoveAttackSame, jump);
				coords.clear();
				for(int i = 1; i < 7; i++) coords.addElement(new Tuple<Integer, Integer>(x-i, y+i));
				markPositionsInRange(coords, isMoveAttackSame, jump);
				coords.clear();
				for(int i = 1; i < 7; i++) coords.addElement(new Tuple<Integer, Integer>(x-i, y-i));
				markPositionsInRange(coords, isMoveAttackSame, jump);
				coords.clear();
			}
			else if(type == PieceType.queen) {
				markPossibleMoves(PieceType.rook, color, x, y);
				markPossibleMoves(PieceType.bishop, color, x, y);
			}
			else if(type == PieceType.king) {
				jump = true;
				coords.addElement(new Tuple<Integer, Integer>(x-1, y-1));
				coords.addElement(new Tuple<Integer, Integer>(x-1, y));
				coords.addElement(new Tuple<Integer, Integer>(x-1, y+1));
				coords.addElement(new Tuple<Integer, Integer>(x, y-1));
				coords.addElement(new Tuple<Integer, Integer>(x, y+1));
				coords.addElement(new Tuple<Integer, Integer>(x+1, y-1));
				coords.addElement(new Tuple<Integer, Integer>(x+1, y));
				coords.addElement(new Tuple<Integer, Integer>(x+1, y+1));
				markPositionsInRange(coords, isMoveAttackSame, jump);
			}
		}
	}
		
	void markPositionsInRange(Vector<Tuple<Integer, Integer> > coords, boolean isMoveAttackSame, boolean jump) {
		for(Tuple<Integer, Integer> t : coords) {
			int x = t.first();
			int y = t.second();
			System.out.println("--" + x + y);
			if(isInRange(x, y) 
					&& (getIcon(x, y).type != PieceType.none)) {
				if(!isMoveAttackSame || getIcon(x, y).color == turn) {
					if(!jump)
						break;
				}
				else if(getIcon(x, y).color != turn) {
					markPositionInRange(x, y);
					if(!jump)
						break;
				}
			}
			else
				markPositionInRange(x, y);
		}
	}
	void markPositionInRange(int x, int y) {
		if(isInRange(x, y)) {
			//if(!checkTest)
			markPosition(x, y);
			System.out.println("MartPostionInRange " + getIcon(x, y).color + getIcon(x,y).type);
			if(checkTest && getIcon(x, y).type == PieceType.king) {
				isCheck = true;
				System.out.println("CHECK!!");
			}
//			else if(checkTest && getIcon(x, y).color != turn && getIcon(x, y).type == PieceType.king) {
//				isCheck = true;
//			}
		}
	}
	boolean isAttackable(int x, int y) {
		if(isInRange(x, y)
			&& getIcon(x, y).type != PieceType.none
			&& getIcon(x, y).color != turn) {
			return true;
		}
		else
			return false;
	}
	boolean isInRange(int a, int b) {
		if(a >=0 && a < 8 && b >= 0 && b < 8)
			return true;
		else
			return false;
	}
	void unmarkAll() {
		// run through two times because of the delay
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++) unmarkPosition(j%8, i%8);
		}
	}
	/* member variables */
	PlayerColor turn = PlayerColor.black;
	boolean isCheck = false;
	boolean checkTest = false;
	int xPrev;
	int yPrev;
	
	void toggleTurn() {
		if(turn == PlayerColor.black) {
			turn = PlayerColor.white;
			setStatus("WHITE's TURN");
		}
		else {
			turn = PlayerColor.black;
			setStatus("BLACK's TURN");
		}
		if(isCheck) addStatus("CHECK");

	}
	void onInitiateBoard(){
		setStatus("BLACK's TURN");
	}
	public void addStatus(String inpt){
		message.setText(message.getText() + " / " + inpt);
	}
	class Tuple<T, U> {
		T x;
		U y;
		public Tuple(T a, U b) {
			super();
			this.x = a;
			this.y = b;
		}
		public T first() { return x; }
		public U second() { return y; }
	}
}