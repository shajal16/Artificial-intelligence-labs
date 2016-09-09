/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shajal
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;





class Board {

    List<Point> availablePoints;
    Scanner k = new Scanner(System.in);
    int[][] board = new int[3][3]; 
     // int [][] board = {{0,1,0},{1,2,0},{2,1,0}}; //pre decided moves start play fromhere
    List<PointsAndScores> baseChildPoint = new ArrayList();

    
    
    public int alphaBetaMinimax(int alpha, int beta, int depth, int turn){
        
        if(beta<=alpha){ 
          //System.out.println("Pruning at depth = "+depth);
          if(turn == 1) return Integer.MAX_VALUE; else return Integer.MIN_VALUE; 
        }
        
        List<Point> pointsAvailable = getAvailableStates();
        
        if(pointsAvailable.isEmpty()) return 0;
        
        if(depth==0) baseChildPoint.clear(); 
        
        int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;
        
        for(int i=0;i<pointsAvailable.size(); ++i){
            Point point = pointsAvailable.get(i);
            
            int currentScore = 0;
            
            if(turn == 1){
                giveTurn(point, 1); 
                currentScore = alphaBetaMinimax(alpha, beta, depth+1, 2);
                maxValue = Math.max(maxValue, currentScore); 
                
                //Set alpha
                alpha = Math.max(currentScore, alpha);
                
                if(depth == 0) baseChildPoint.add(new PointsAndScores(currentScore, point));
               }else if(turn == 2){
                giveTurn(point, 2);
                currentScore = alphaBetaMinimax(alpha, beta, depth+1, 1); 
                minValue = Math.min(minValue, currentScore);
                
                //Set beta
                beta = Math.min(currentScore, beta);
            }
            //reset board
            board[point.x][point.y] = 0; 
            
            //If a pruning has been done, left all else
            if(currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) break;
        }
        return turn == 1 ? maxValue : minValue;
    }  

    public boolean isGameOver() {
      
        return (winX()|| winY() || getAvailableStates().isEmpty());
    }

    public boolean winX() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 1) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 1)) {
            //computer jitbe digonally
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 1)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 1))) {
               //computer jitbe row othoba column e
                return true;
            }
        }
        return false;
    }

    public boolean winY(){
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 2) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 2)) {
            // System.out.println("O Diagonal Win");
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 2)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 2)) {
                //  System.out.println("O Row or Column win");
                return true;
            }
        }

        return false;
    }

    public List<Point> getAvailableStates() {
        availablePoints = new ArrayList();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    public void giveTurn(Point point, int player) {
        board[point.x][point.y] = player;   //player = 1 for X, 2 for O
    }

    public Point placeBest() {
        int MAX = -100000;
        int best = -1;

        for (int i = 0; i < baseChildPoint.size(); ++i) {
            if (MAX < baseChildPoint.get(i).score) {
                MAX = baseChildPoint.get(i).score;
                best = i;
            }
        }

        return baseChildPoint

.get(best).point;
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
    
    public void resetBoard() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                board[i][j] = 0;
            }
        }
    } 
}

