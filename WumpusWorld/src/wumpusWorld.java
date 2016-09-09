import java.util.*;
class wumpusWorld 
{
  static int scream=0;
  static int score=0;
  static int complete=0;
  
  static boolean check(GamePlay t)
  {
    int temp=t.sense();
    if(temp==1 || temp==2)
      return false;
    
    return true;
  }
  public static void main(String args[])
  {
    Scanner scr=new Scanner(System.in);
    BoardFull e=new BoardFull();
    String w[][]=new String[5][5];
    e.accept(w);
    System.out.println("\n\nFinding the solution...");
    
    GamePlay t[]=new GamePlay[17];
    int c=1;
    out:for(int i=1;i<5;++i)
    {
      for(int j=1;j<5;++j)
      {
        if(c>16)
          break out;
        t[c]=new GamePlay(w[i][j],c);
        ++c;
      }
    }
    
    t[13].safe=1;
    t[13].visited=1;
    
    int pos=13;
    int condition;
    int limit=0;
    String temp1,temp2;
    do
    {
      ++limit;
      condition=-1;
      
      if(t[pos].env.contains("G"))
      {
        complete=1;
        System.out.println("Gold Found!!");
        break;
      }
      
      if(t[pos].br!=1 && t[pos].r!=1 && t[pos+1].doubt_pit<1 && t[pos+1].doubt_wump<1 && t[pos+1].pit!=1 && t[pos+1].wump!=1 && !(t[pos].back.contains("r") && (t[pos].l!=1 || t[pos].u!=1 || t[pos].d!=1) && check(t[pos]) ))
      {
        
        temp1="l";
        t[pos].r=1;
        ++pos;
        System.out.println("\nfront pos="+pos);
        ++score;
        t[pos].back+=temp1;
        condition=t[pos].sense();
        if(condition==3)
        {complete=1;break;}
        else
          if(condition==1 && t[pos].visited==0)
        {
          if(t[pos].br!=1 && t[pos+1].safe!=1)
            t[pos+1].doubt_pit+=1;
          if(t[pos].bu!=1 && (pos-4)>=1 && t[pos-4].safe!=1)
            t[pos-4].doubt_pit+=1;
          if(t[pos].bl!=1 && t[pos-1].safe!=1 )
            t[pos-1].doubt_pit+=1;
          if(t[pos].bd!=1 && (pos+4)<=16 && t[pos+4].safe!=1)
            t[pos+4].doubt_pit+=1;
          
          t[pos].safe=1;
        }
        else
          if(condition==2 && t[pos].visited==0)
        {
          if(t[pos].br!=1 && t[pos+1].safe!=1)
            t[pos+1].doubt_wump+=1;
          if(t[pos].bu!=1 && (pos-4)>=1 &&  t[pos-4].safe!=1)
            t[pos-4].doubt_wump+=1;
          if(t[pos].bl!=1 && t[pos-1].safe!=1)
            t[pos-1].doubt_wump+=1;
          if(t[pos].bd!=1 && (pos+4)<=16 &&  t[pos+4].safe!=1)
            t[pos+4].doubt_wump+=1;
          
          t[pos].safe=1;
        }
        else
          if(condition==0)
          t[pos].safe=1;
        
        
        t[pos].visited=1;
      }
      else
        if(t[pos].bl!=1 && t[pos].l!=1 && t[pos-1].doubt_pit<1 && t[pos-1].doubt_wump<1 && t[pos-1].pit!=1 && t[pos-1].wump!=1 && !(t[pos].back.contains("l") && (t[pos].r!=1 || t[pos].u!=1 || t[pos].d!=1) && check(t[pos]) ))
      {
        temp1="r";
        t[pos].l=1;
        pos=pos-1;
        System.out.println("\nback pos= "+pos);
        ++score;
        t[pos].back+=temp1;  
        condition=t[pos].sense();
        if(condition==3)
        {complete=1;break;}
        else
          if(condition==1 && t[pos].visited==0)
        {
          if(t[pos].br!=1 && t[pos+1].safe!=1)
            t[pos+1].doubt_pit+=1;
          if(t[pos].bu!=1 && (pos-4)>=1 &&  t[pos-4].safe!=1)
            t[pos-4].doubt_pit+=1;
          if(t[pos].bl!=1 && t[pos-1].safe!=1)
            t[pos-1].doubt_pit+=1;
          if(t[pos].bd!=1 && (pos+4)<=16 &&  t[pos+4].safe!=1)
            t[pos+4].doubt_pit+=1;
          
          
          t[pos].safe=1;
        }
        else
          if(condition==2 && t[pos].visited==0)
        {
          if(t[pos].br!=1 && t[pos+1].safe!=1)
            t[pos+1].doubt_wump+=1;
          if(t[pos].bu!=1 && (pos-4)>=1 &&  t[pos-4].safe!=1)
            t[pos-4].doubt_wump+=1;
          if(t[pos].bl!=1 && t[pos-1].safe!=1)
            t[pos-1].doubt_wump+=1;
          if(t[pos].bd!=1 && (pos+4)<=16 &&  t[pos+4].safe!=1)
            t[pos+4].doubt_wump+=1;
          
          t[pos].safe=1;
        }
        else
          if(condition==0)
          t[pos].safe=1;
        
        t[pos].visited=1;
        
        
      }
      else
        if(t[pos].bu!=1 && t[pos].u!=1 && (pos-4)>=1 &&  t[pos-4].doubt_pit<1 && t[pos-4].doubt_wump<1 && t[pos-4].pit!=1 && t[pos-1].wump!=1 && !(t[pos].back.contains("u") && (t[pos].l!=1 || t[pos].r!=1 || t[pos].d!=1) && check(t[pos])  ))
      {
        temp1="d";
        t[pos].u=1;
        pos=pos-4;
        System.out.println("\nUp pos= "+pos);
        ++score;
        t[pos].back+=temp1;
        condition=t[pos].sense();
        if(condition==3)
        {complete=1;break;}
        else
          if(condition==1 && t[pos].visited==0)
        {
          if(t[pos].br!=1 && t[pos+1].safe!=1)
            t[pos+1].doubt_pit+=1;
          if(t[pos].bu!=1 && (pos-4)>=1 &&  t[pos-4].safe!=1)
            t[pos-4].doubt_pit+=1;
          if(t[pos].bl!=1 && t[pos-1].safe!=1)
            t[pos-1].doubt_pit+=1;
          if(t[pos].bd!=1 && (pos+4)<=16 &&  t[pos+4].safe!=1)
            t[pos+4].doubt_pit+=1;
          
          
          t[pos].safe=1;
        }
        else
          if(condition==2 && t[pos].visited==0)
        {
          if(t[pos].br!=1 && t[pos+1].safe!=1)
            t[pos+1].doubt_wump+=1;
          if(t[pos].bu!=1 && (pos-4)>=1 &&  t[pos-4].safe!=1)
            t[pos-4].doubt_wump+=1;
          if(t[pos].bl!=1 && t[pos-1].safe!=1)
            t[pos-1].doubt_wump+=1;
          if(t[pos].bd!=1 && (pos+4)<=16 &&  t[pos+4].safe!=1)
            t[pos+4].doubt_wump+=1;
          
          t[pos].safe=1;
        }
        else
          if(condition==0)
          t[pos].safe=1;
        
        t[pos].visited=1;
      }
      else
        if(t[pos].bd!=1 && t[pos].d!=1 && (pos+4)<=16 &&  t[pos+4].doubt_pit<1 && t[pos+4].doubt_wump<1 && t[pos+4].pit!=1 && t[pos+4].wump!=1)
      {
        temp1="u";
        t[pos].d=1;
        pos=pos+4;
        System.out.println("\ndown pos= "+pos);
        ++score;
        t[pos].back+=temp1;
        condition=t[pos].sense();
        if(condition==3)
        {complete=1;break;}
        else
          if(condition==1 && t[pos].visited==0)
        {
          if(t[pos].br!=1 && t[pos+1].safe!=1)
            t[pos+1].doubt_pit+=1;
          if(t[pos].bu!=1 && (pos-4)>=1 &&  t[pos-4].safe!=1)
            t[pos-4].doubt_pit+=1;
          if(t[pos].bl!=1 && t[pos-1].safe!=1)
            t[pos-1].doubt_pit+=1;
          if(t[pos].bd!=1 && (pos+4)<=16 &&  t[pos+4].safe!=1)
            t[pos+4].doubt_pit+=1;
          
          t[pos].safe=1;
        }
        else
          if(condition==2 && t[pos].visited==0)
        {
          if(t[pos].br!=1 && t[pos+1].safe!=1)
            t[pos+1].doubt_wump+=1;
          if(t[pos].bu!=1 && (pos-4)>=1 &&  t[pos-4].safe!=1)
            t[pos-4].doubt_wump+=1;
          if(t[pos].bl!=1 && t[pos-1].safe!=1)
            t[pos-1].doubt_wump+=1;
          if(t[pos].bd!=1 && (pos+4)<=16 &&  t[pos+4].safe!=1)
            t[pos+4].doubt_wump+=1;
          
          t[pos].safe=1;
        }
        else
          if(condition==0)
          t[pos].safe=1;
        
        t[pos].visited=1;
      }
      else
        if(limit>50)
      {
        int temp3=pos;
        int flag_1=0,flag2=0,flag3=0,flag4=0;
        
        System.out.println("\nCurrently at position "+temp3+".\nThinking....");
        
        while(t[pos].visited==1 && t[pos].br!=1)
        {
          ++pos;
          ++score;
        }
        
        
        if(t[pos].pit==1 || t[pos].wump==1 || (t[pos].br==1 && t[pos].visited==1 && t[pos].safe!=1))
        {
          pos=temp3;
          flag_1=1;
        } 
        
        if(flag_1==0)
          t[pos].back+="l";
        while(pos+4>=1 && t[pos].bu!=1 && t[pos].visited==1)
        {
          pos-=4;
          ++score;
        }
        
        if(t[pos].pit==1 || t[pos].wump==1 || (t[pos].bu==1 && t[pos].visited==1  && t[pos].safe!=1))
        {
          pos=temp3;
          flag3=1;
        } 
        
        if(flag3==0)
          t[pos].back+="d";
        
        while(t[pos].visited==1 && t[pos].bl!=1)
        {
          --pos;
          ++score;
        }
        
        if(t[pos].pit==1 || t[pos].wump==1 || (t[pos].bl==1 && t[pos].visited==1 && t[pos].safe!=1))
        {
          pos=temp3;
          flag2=1;
        } 
        
        if(flag2==0)
          t[pos].back+="r";
        
        
        
        
        while(pos+4<=16 && t[pos].bd!=1 && t[pos].visited==1)
        {
          pos+=4;
          ++score;
        }
        
        if(t[pos].pit==1 || t[pos].wump==1 || (t[pos].bd==1 && t[pos].visited==1 && t[pos].safe!=1))
        {
          
          pos=temp3;
          
          flag4=1;
        } 
        
        if(flag4==0)
          t[pos].back+="u";
        
        t[pos].safe=1;
        t[pos].visited=1;
        System.out.println("reached at position "+pos);
        limit=0;
      }
      if(t[pos].env.contains("W") && scream!=1)
      {
        score+=100;
        scream=1;
        t[pos].safe=1;
        System.out.println("\n\nWumpus killed >--0-->");
          String replace = t[pos].env.replace("W"," ");
        for(int l=1;l<=16;++l)
        {
          t[l].doubt_wump=0;
          t[l].env.replace("S"," ");
        }
      }
      
      if(t[pos].env.contains("P"))
      {
        score+=50;
        t[pos].pit=1;
        System.out.println("\n\nFallen in pit of position "+pos+".");
      }
      
      for(int k=1;k<=16;++k)
      {
        if(t[k].doubt_pit==1 && t[k].doubt_wump==1)
        {
          t[k].doubt_pit=0;
          t[k].doubt_wump=0;
          t[k].safe=1;
        }
      }
      
      for(int y=1;y<=16;++y)
      {
        if(t[y].doubt_wump>1)
        {
          t[y].wump=1;
          for(int h=1;h<=16;++h)
          {
            if(h!=y)
            {
              t[h].doubt_wump=0;
              t[h].env.replace("S"," ");
            }
          }
          
        }
        
      }
      
      
      for(int y=1;y<=16;++y)
      {
        if(t[y].doubt_pit>1)
        {t[y].pit=1;
          
        }
      }
      
      
      
      try{Thread.sleep(200);}catch(Exception p){}
      
    }
    while(complete==0);
    
    if(complete==1)
    {
      
      score*=-1;
      score+=1000;
    }
    System.out.println("The score of the agent till he reaches gold is "+score);
    
  }
}
