/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shajal
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;
public class Ai_chatbot extends JFrame implements KeyListener{

	JPanel p=new JPanel();
	JTextArea dialog=new JTextArea(20,50);
	JTextArea input=new JTextArea(5,50);
	JScrollPane scroll=new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);
	
	String[][] chatBot={
		//greeting groups
		{"what","but why","what i have done","what happened to you","sure"},
		{"what?","you here me?","I dont want you."},
		
		{"where do you live","where do u live"},
		{"I am just in front of you"},
		
		{"yes"},
		{"no","I said NO","SOPT!!! I said no thats it !"},
                
                {"stop"},
                {"you can just close me"},
                
                {"are you happy","are you mad",},
                {"what you think?"},
                
                {"how are you","how r u","how r you","how are u"},
                {"fine what about you?"},
                
                {"can you talk","can you speak","can u talk","can u speak"},
                {"I would have already if i can"},

		
		{"Whats your problem ?","Leave me alone","You dumb","Stop!",
		"you are abusing me"}
	};
	
	String[][] verbs={
		{"is","are"},		
                {"was","were"},	
                {"think"," think"},		
                 {"am","'re"},
		{"are","were"} 
	};
		
	public static void main(String[] args){
		new Ai_chatbot();
	}
	
	public Ai_chatbot(){
		super("Gadhagas 1.0 by Kamal Hossain");
		setSize(600,470);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		input.addKeyListener(this);
	
		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(0,0,0));
		add(p);
		
		setVisible(true);
                addText("\n-->Gadha\t"+"hello I am gadha!"+"\n");
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(false);
			
			String quote=input.getText();
			input.setText("");
			addText("-->You:\t"+quote);
			quote=quote.trim();
			while(
				quote.charAt(quote.length()-1)=='!' ||
				quote.charAt(quote.length()-1)=='.' ||
				quote.charAt(quote.length()-1)=='?'
			){
				quote=quote.substring(0,quote.length()-1);
			}
			quote=quote.trim();
			byte response=0;
			
			int j=0;   //checking group
			while(response==0){
				if(inArray(quote.toLowerCase(),chatBot[j*2])){
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					addText("\n-->Gadha\t"+chatBot[(j*2)+1][r]);
				}
				j++;
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}
			
			if(response==1){
				String quoteWords[]=quote.split("[ ']");
				int c=counter(quoteWords);
				if(c!=-1){
					String ext=quote.split(verbs[c][0])[1];
					addText("\n-->Gadha:\tMeh "+"you"+verbs[c][1]+ext);
					response=2;
				}
			}
			
			if(response==1){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\n-->Gadha\t"+chatBot[chatBot.length-1][r]);
			}
			addText("\n");
		}
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}
	
	public void keyTyped(KeyEvent e){}
	
	public void addText(String str){
		dialog.setText(dialog.getText()+str);
	}
	
	public boolean inArray(String in,String[] str){
		boolean match=false;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(in)){
				match=true;
			}
		}
		return match;
	}
	
	public int counter(String str[]){
		int verbID=-1;
		for(int i=0;i<str.length;i++){
			for(int j=0;j<verbs.length;j++){
				if(str[i].equals(verbs[j][0])){
					verbID=j;
				}
			}
		}
		return verbID;
	}
}