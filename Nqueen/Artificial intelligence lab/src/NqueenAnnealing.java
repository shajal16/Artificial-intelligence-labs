/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shajal
 */
public class NqueenAnnealing 
{

    int[] x;
    private int combinationNumber = 1;

    public NqueenAnnealing(int board) 
    {
        x = new int[board];
    }

    public boolean placequeenAnnealing(int r, int cl) //column and r place
    {
       for (int i = 0; i < r; i++) 
       {
         if (x[i] == cl  
                  || (i - r) == (x[i] - cl) 
                  ||(i - r) == (cl - x[i])) 
            {
                return false;
            }
        }
        return true;
    }

    public void printqueenAnnealing(int[] c) 
    {
        int board = c.length;
        for (int i = 0; i < board; i++)
        {
            for (int j = 0; j < board; j++) 
            {
                if (c[i] == j) 
                {
                    System.out.print(" Q ");
                } else
                {
                    System.out.print(" * ");
                }
            }
            System.out.println();
        }
        System.out.println(combinationNumber);
        combinationNumber++;
    }

    public void queenPlacing(int r, int number) 
    {
        for (int c = 0; c < number; c++) 
        {
            if (placequeenAnnealing(r, c))
            {
                x[r] = c;
                if (r == number - 1) 
                {
                    printqueenAnnealing(x);
                } else 
                {
                    queenPlacing(r + 1, number);
                }
            }
        }
    }

    public void tenqueen() 
    {
        queenPlacing(0, x.length);
    }

    public static void main(String args[]) 
    {
        NqueenAnnealing Q = new NqueenAnnealing(10);
        Q.tenqueen();
     
    }
}