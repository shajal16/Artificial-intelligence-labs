/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shaja
 */
import java.util.*;
public class TicTacToe {
    public static void main (String [] args){
      Board b =new Board();
      boolean tryToOverite=false;
      Random rand = new Random();
        
        b.displayBoard();

        System.out.println("Who will play first? (1)computer (2)User: ");
        int choice = b.k.nextInt();
        if(choice == 1){ 
            
            b.callMinimax(0, 1);
            b.giveTurn(b.placeBest(), 1);
           
            b.displayBoard();
        }
        
        while (!b.isGameOver()) {
            System.out.println("Your move: ");
            int x= b.k.nextInt();
             int y= b.k.nextInt();
            Point userMove = new Point(x, y);
            if((b.board[userMove.x][userMove.y]==1)){
               System.out.println("Wrong move");
               tryToOverite=true;
               break;
            }
            if((b.board[userMove.x][userMove.y]==2)){
               System.out.println("Wrong move");
               tryToOverite=true;
               break;
            }
            b.giveTurn(userMove, 2); //2 for O and O is the user
            b.displayBoard();
            if (b.isGameOver()) {
                break;
            } 
            b.callMinimax(0, 1);
           
            
            b.giveTurn(b.placeBest(), 1);
            b.displayBoard();
        }
        if (b.winX()) {
            System.out.println("You lost!");
        } else if (b.winY()) {
            System.out.println("You won!");
        }else if(tryToOverite==true){
            System.out.println("You tried to do cheat!");
        }else {
            System.out.println("It's a draw!");
        }
      
    }
}

