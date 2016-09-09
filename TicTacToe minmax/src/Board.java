/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shaja
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {
    List <Point> totalPoints;
     List<PointsAndScores> baseChildPoint;
    Scanner k =new Scanner(System.in);
    int[][] board = new int[3][3];
     //int [][] board = {{0,1,0},{1,2,0},{2,1,0}}; //age theke dewa co-ordinate.
     public Board() {
    }
     
      public boolean isGameOver() {
      return (winX() || winY() || availableStates().isEmpty());
      } //game over conditions 
     public boolean winX(){
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 1) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 1)) {
         return true; //pc win condition
        }
          for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 1)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 1))) {
                //pc win
                return true;
            }
   
       }
          return false;
     }
     
      public boolean winY(){
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 2) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 2)) {
            //0 diagonal win
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 2)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 2)) {
                //0 row column win
                return true;
            }
        }
        return false;
   }
      
    public List<Point> availableStates() {
        totalPoints = new ArrayList(); //available points
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 0) {
                    totalPoints.add(new Point(i, j)); 
                }
            }
        }
        return totalPoints;
    }
     public void giveTurn(Point point, int player) {
      
         board[point.x][point.y] = player;   //player 1:=x , 2:=y
         
      }
    public Point placeBest() {
        int MAX = -200000;
        int best = -1;

        for (int i = 0; i < baseChildPoint.size(); ++i)
        { 
            if (MAX < baseChildPoint.get(i).score) 
            { 
                MAX = baseChildPoint.get(i).score;
                best = i;  //select the best
            }
        }

        return baseChildPoint.get(best).point;
    }
       void takeInput() {
        System.out.println("Place your move: ");
        int x = k.nextInt();
        int y = k.nextInt();
        Point point = new Point(x, y);
        
        giveTurn(point, 2);  
        
    }
      public void displayBoard() {
        System.out.println();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();

        }
    }
        public int returnMin(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }
         public int returnMax(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > max) {
                max = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }
        public void callMinimax(int depth, int turn){
        baseChildPoint = new ArrayList();
        calculateMinimax(depth, turn);
    }
         public int calculateMinimax(int depth, int turn) {

        if (winX()) return +10; //if thers any turn that pc might win will add 10
 
        if (winY()) return -10; //if player about to win in any branch will add -10

        List<Point> pointsAvailable = availableStates();
        if (pointsAvailable.isEmpty()) return 0; 

        List<Integer> scoreboard = new ArrayList(); 

        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);  

            if (turn == 1) { 
                giveTurn(point, 1); 
                int currentScore = calculateMinimax(depth + 1, 2);
                scoreboard.add(currentScore);

                if (depth == 0) 
                    baseChildPoint.add(new PointsAndScores(currentScore, point)); //saves both state and score the board.
                
            } else if (turn == 2) { 
                giveTurn(point, 2); 
                scoreboard.add(calculateMinimax(depth + 1, 1));
            }
            board[point.x][point.y] = 0; //Reset this point
        }
        return turn == 1 ? returnMax(scoreboard) : returnMin(scoreboard);
    }
         
         
}
