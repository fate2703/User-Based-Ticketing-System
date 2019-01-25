package project;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.sql.*;

public class userticket extends Applet implements ActionListener,ItemListener{
	Button proceed1,proceed2,proceed3,proceed4,logout;
	Label wel,cap,luser,lpass,lsource,ldest,lno,lclass,lret,lfare,lfareamount,error1,error2,error3;
	TextField tuser,tpass,tno;
	Checkbox one,two,ret;
	CheckboxGroup cbg=new CheckboxGroup();
	
	Connection mycon;
	Statement mystmt,ststmt2,ststmt;
	ResultSet myrs,strs;
	Double bal,st1,st2,fare,i,j=1.0;
	String user,pass,stname1,stname2;
	Label balfare,balfareamount,label1,label2,lclassval,lretval,lsourceval,ldestval,lno2val,lsource2,ldest2,lno2,lclass2,lret2;
	
	Choice station=new Choice();
	Choice stationd=new Choice();
	
	public void init() {
		
		proceed1=new Button("Proceed");
		proceed1.addActionListener(this);
		add(proceed1);
		
		wel=new Label("Welcome");
		cap=new Label("Click on proceed to continue");
		add(wel);
		add(cap);
		
		luser=new Label("USERNAME :");
		error1=new Label("Username does not exist!");
		error2=new Label("Password does not exist!");
		lpass=new Label("PASSWORD :");
		tuser=new TextField(15);
		tpass=new TextField(15);
		tpass.setEchoChar('*');
		proceed2=new Button("Proceed");
		proceed2.addActionListener(this);
		add(luser);
		add(tuser);
		add(lpass);
		add(tpass);
		add(error1);
		add(error2);
		add(proceed2);
		luser.setVisible(false);
		lpass.setVisible(false);
		tuser.setVisible(false);
		tpass.setVisible(false);
		proceed2.setVisible(false);
		error1.setVisible(false);
		error2.setVisible(false);
		
		lsource=new Label("SOURCE :");
		ldest=new Label("DESTINATION :");
		
		station.add("CST");
		station.add("Masjid");
		station.add("Sandhurst rd");
		station.add("Byculla");
		station.add("Chinchpokli");
		station.add("Currey rd");
		station.add("Parel");
		station.add("Dadar");
		station.add("Matunga");
		station.add("Sion");
		station.add("Kurla");
		station.add("Vidyavihar");
		station.add("Ghatkopar");
		station.add("Vikhroli");
		station.add("Kanjurmarg");
		add(station);
		station.addItemListener(this);
		station.setVisible(false);
		stationd.add("CST");
		stationd.add("Masjid");
		stationd.add("Sandhurst rd");
		stationd.add("Byculla");
		stationd.add("Chinchpokli");
		stationd.add("Currey rd");
		stationd.add("Parel");
		stationd.add("Dadar");
		stationd.add("Matunga");
		stationd.add("Sion");
		stationd.add("Kurla");
		stationd.add("Vidyavihar");
		stationd.add("Ghatkopar");
		stationd.add("Vikhroli");
		stationd.add("Kanjurmarg");
		add(stationd);
		stationd.addItemListener(this);
		stationd.setVisible(false);
		
		lno=new Label("NO OF TICKETS :");
		tno=new TextField(1);
		lclass=new Label("CLASS :");
		one=new Checkbox("1",cbg,false);
		one.addItemListener(this);
		two=new Checkbox("2",cbg,false);
		two.addItemListener(this);
		lret=new Label("RETURN");
		ret=new Checkbox("",false);
		ret.addItemListener(this);
		proceed3=new Button("Proceed");
		proceed3.addActionListener(this);
		add(lsource);
		add(ldest);
		add(lno);
		add(tno);
		add(lclass);
		add(one);
		add(two);
		add(lret);
		add(ret);
		add(proceed3);
		lsource.setVisible(false);
		ldest.setVisible(false);
		lno.setVisible(false);
		tno.setVisible(false);
		lclass.setVisible(false);
		one.setVisible(false);
		two.setVisible(false);
		lret.setVisible(false);
		ret.setVisible(false);
		proceed3.setVisible(false);
		
		error3=new Label("BALANCE TOO LOW!");
		lfare=new Label("FARE");
		lfareamount=new Label("        ");
		proceed4=new Button("Proceed");
		proceed4.addActionListener(this);
		add(proceed4);
		add(lfare);
		add(lfareamount);
		add(error3);
		lfare.setVisible(false);
		lfareamount.setVisible(false);
		error3.setVisible(false);
		proceed4.setVisible(false);
		proceed4.addActionListener(this);
		
		balfare=new Label("BALANCE :");
		balfareamount=new Label("        ");
		label1=new Label("********************************************************");
		label2=new Label("********************************************************");
		lsourceval=new Label("         ");
		ldestval=new Label("         ");
		lclassval=new Label("         ");
		lretval=new Label("No");
		lno2val=new Label("          ");
		lsource2=new Label("SOURCE :");
		ldest2=new Label("DESTINATION :");
		lno2=new Label("NO OF TICKETS :");
		lclass2=new Label("CLASS :");
		lret2=new Label("RETURN :");
		logout=new Button("LOGOUT");
		add(logout);
		logout.addActionListener(this);
		add(balfare);
		add(balfareamount);
		add(label1);
		add(label2);
		add(lsourceval);
		add(ldestval);
		add(lclassval);
		add(lretval);
		add(lno2val);
		add(lno2);
		add(lsource2);
		add(ldest2);
		add(lclass2);
		add(lret2);
		lsourceval.setVisible(false);
		ldestval.setVisible(false);
		lclassval.setVisible(false);
		lretval.setVisible(false);
		lsource2.setVisible(false);
		ldest2.setVisible(false);
		lno2.setVisible(false);
		lclass2.setVisible(false);
		lret2.setVisible(false);
		lno2val.setVisible(false);
		balfare.setVisible(false);
		balfareamount.setVisible(false);
		label1.setVisible(false);
		label2.setVisible(false);
		logout.setVisible(false);
	}
	public void paint(Graphics g) {
		resize(300,300);
		Color c1=new Color(245,245,220);
		Font font=new Font("Times New Roman", Font.BOLD, 30);
		Font font2=new Font("Arial", Font.BOLD, 15);
		Font font3=new Font("Arial",Font.ITALIC,10);
       	setBackground(c1);
       	wel.setBackground(c1);
       	cap.setBackground(c1);
       	wel.setFont (font);
       	wel.setLocation(70,80);
       	cap.setFont (font2);
       	cap.setLocation(45,130);
       	error2.setFont(font3);
       	proceed1.setLocation(110,190);
       	
       	luser.setLocation(15,50);
		tuser.setLocation(120,50);
		lpass.setLocation(15,125);
		tpass.setLocation(120,125);
		proceed2.setLocation(100,200);
		luser.setFont(font2);
		lpass.setFont(font2);
		luser.setBackground(c1);
		lpass.setBackground(c1);
		error1.setBackground(c1);
		error2.setBackground(c1);
       	error1.setFont(font3);
       	error2.setFont(font3);
       	error1.setForeground(Color.RED);
       	error2.setForeground(Color.RED);
       	error1.setLocation(50,100);
       	error2.setLocation(50,175);
		
		lsource.setBackground(c1);
		ldest.setBackground(c1);
		lno.setBackground(c1);
		lclass.setBackground(c1);
		lsource.setLocation(15,25);	
		ldest.setLocation(15,50);
		lno.setLocation(15,75);
		tno.setLocation(125,75);
		lclass.setLocation(15,100);
		one.setLocation(125,100);
		two.setLocation(190,100);
		lret.setLocation(15,125);
		lret.setBackground(c1);
		ret.setLocation(125,125);
		proceed3.setLocation(100,150);
		
		station.setLocation(125,25);
		stationd.setLocation(125,50);
		
		lfare.setBackground(c1);
		lfareamount.setBackground(c1);
		lfare.setFont(new Font("Arial", Font.BOLD, 25));
		lfare.setLocation(100,75);
		lfareamount.setFont(new Font("Arial", Font.BOLD, 25));
		lfareamount.setLocation(100,120);
		proceed4.setLocation(100,180);
		error3.setBackground(c1);
		error3.setFont(font2);
		error3.setLocation(5,150);
		
		balfare.setLocation(15,5);
		balfare.setBackground(c1);
		balfare.setFont(font2);
		balfareamount.setBackground(c1);
		balfareamount.setFont(font2);
		balfareamount.setLocation(150,5);
		label1.setLocation(5,30);
		label1.setBackground(c1);
		lsource2.setBackground(c1);
		lsource2.setLocation(15,75);
		lsource2.setFont(font2);
		lsourceval.setBackground(c1);
		lsourceval.setFont(font2);
		lsourceval.setLocation(150,75);
		ldest2.setFont(font2);
		ldest2.setBackground(c1);
		ldest2.setLocation(15, 100);
		ldestval.setFont(font2);
		ldestval.setBackground(c1);
		ldestval.setLocation(150, 100);
		lno2.setFont(font2);
		lno2.setBackground(c1);
		lno2.setLocation(15,150);
		lno2val.setFont(font2);
		lno2val.setBackground(c1);
		lno2val.setLocation(150,150);
		lclass2.setFont(font2);
		lclass2.setBackground(c1);
		lclass2.setLocation(15,175);
		lclassval.setFont(font2);
		lclassval.setBackground(c1);
		lclassval.setLocation(150,175);
		lret2.setFont(font2);
		lret2.setBackground(c1);
		lret2.setLocation(15,200);
		lretval.setFont(font2);
		lretval.setBackground(c1);
		lretval.setLocation(150,200);
		label2.setBackground(c1);
		label2.setLocation(5,250);
		logout.setLocation(100,280);	
	}
	public void actionPerformed(ActionEvent event){
		if(event.getSource()==proceed1) {
			proceed1.setVisible(false);
			wel.setVisible(false);
			cap.setVisible(false);
			resize(301,301);
			luser.setVisible(true);
			lpass.setVisible(true);
			tuser.setVisible(true);
			tpass.setVisible(true);
			proceed2.setVisible(true);
		}
		if(event.getSource()==proceed2) {
			try {
				
				mycon=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","");
				mystmt=mycon.createStatement();
				myrs=mystmt.executeQuery("select * from details");
				
				while(myrs.next()) {
					user=myrs.getString("username");
					pass=myrs.getString("password");
					if(myrs.getString("username").equals(tuser.getText())&&myrs.getString("password").equals(tpass.getText())) {
						
						luser.setVisible(false);
						lpass.setVisible(false);
						tuser.setVisible(false);
						tpass.setVisible(false);
						proceed2.setVisible(false);
						resize(302,302);
						lsource.setVisible(true);
						ldest.setVisible(true);
						station.setVisible(true);
						stationd.setVisible(true);
						lno.setVisible(true);
						tno.setVisible(true);
						lclass.setVisible(true);
						one.setVisible(true);
						two.setVisible(true);
						lret.setVisible(true);
						ret.setVisible(true);
						proceed3.setVisible(true);
						error1.setVisible(false);
						error2.setVisible(false);
						bal=myrs.getDouble("balance");
						break;
					}
				}
				if(!user.equals(tuser.getText())&&!pass.equals(tpass.getText())){
					resize(304,304);
					error1.setVisible(true);
					error2.setVisible(true);
					tuser.setText("");
					tpass.setText("");
					proceed2.addActionListener(this);
					repaint();
					
				}
				else if(!user.equals(tuser.getText())){
					error1.setVisible(true);
				}
				else if(!pass.equals(tpass.getText())){
					error2.setVisible(true);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				
			}
			
		}
		if(event.getSource()==proceed3) {
			try {
				mycon=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","");
				ststmt=mycon.createStatement();
				ststmt2=mycon.createStatement();
				strs=ststmt.executeQuery("select * from station");
				
				while(strs.next()) {
					if(strs.getString("name").equals(stname1)) {
						lsourceval.setText(stname1);
						st1=strs.getDouble("dist");
					}
					if(strs.getString("name").equals(stname2)) {
						ldestval.setText(stname2);
						st2=strs.getDouble("dist");
					}
				}
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				lno2val.setText(tno.getText());
				if(Math.abs(st1-st2)==1||Math.abs(st1-st2)==2||Math.abs(st1-st2)==3||Math.abs(st1-st2)==4){
					fare=Double.valueOf(tno.getText())*5.0*i*j;
				}
				else if(Math.abs(st1-st2)==5||Math.abs(st1-st2)==6||Math.abs(st1-st2)==7||Math.abs(st1-st2)==8){
					fare=Double.valueOf(tno.getText())*5.0*i*j+5.0;
				}
				else if(Math.abs(st1-st2)==9||Math.abs(st1-st2)==10||Math.abs(st1-st2)==11||Math.abs(st1-st2)==12){
					fare=Double.valueOf(tno.getText())*5.0*i*j+10.0;
				}
				else if(Math.abs(st1-st2)==13||Math.abs(st1-st2)==14||Math.abs(st1-st2)==15||Math.abs(st1-st2)==16){
					fare=Double.valueOf(tno.getText())*5.0*i*j+15.0;
				}
				else if(Math.abs(st1-st2)==17||Math.abs(st1-st2)==18||Math.abs(st1-st2)==19||Math.abs(st1-st2)==20){
					fare=Double.valueOf(tno.getText())*5.0*i*j+20.0;
				}
				if(bal-fare>=0) {
					try {
						mycon=DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","");
						ststmt2=mycon.createStatement();
						String s="update details set balance="+(bal-fare)+"where username='"+user+"' ";
						ststmt2.executeUpdate(s);
					}
					catch(Exception e) {
						
					}
					balfareamount.setText(Double.toString(bal-fare));
					lfareamount.setText(Double.toString(fare));
					lfare.setVisible(true);
					lfareamount.setVisible(true);
					proceed4.setVisible(true);
				}
				else {
					lfare.setVisible(false);
					lfareamount.setVisible(false);
					proceed4.setVisible(false);
					error3.setText("BALANCE TOO LOW! Only "+bal+" in account");
					error3.setVisible(true);
					logout.setVisible(true);
				}
			}
			lsource.setVisible(false);
			ldest.setVisible(false);
			lno.setVisible(false);
			tno.setVisible(false);
			lclass.setVisible(false);
			one.setVisible(false);
			two.setVisible(false);
			lret.setVisible(false);
			ret.setVisible(false);
			station.setVisible(false);
			stationd.setVisible(false);
			proceed3.setVisible(false);
			resize(303,303);
			
		}
		if(event.getSource()==proceed4) {
			resize(304,304);
			lfare.setVisible(false);
			lfareamount.setVisible(false);
			proceed4.setVisible(false);
			lsourceval.setVisible(true);
			ldestval.setVisible(true);
			lclassval.setVisible(true);
			lretval.setVisible(true);
			lsource2.setVisible(true);
			ldest2.setVisible(true);
			lno2.setVisible(true);
			lclass2.setVisible(true);
			lret2.setVisible(true);
			lno2val.setVisible(true);
			balfare.setVisible(true);
			balfareamount.setVisible(true);
			label1.setVisible(true);
			label2.setVisible(true);
			logout.setVisible(true);
		}
		if(event.getSource()==logout) {
			tuser.setText("");
			tpass.setText("");
			tno.setText("");
			resize(305,305);
			error3.setVisible(false);
			lsourceval.setVisible(false);
			ldestval.setVisible(false);
			lclassval.setVisible(false);
			lretval.setVisible(false);
			lsource2.setVisible(false);
			ldest2.setVisible(false);
			lno2.setVisible(false);
			lclass2.setVisible(false);
			lret2.setVisible(false);
			lno2val.setVisible(false);
			balfare.setVisible(false);
			balfareamount.setVisible(false);
			label1.setVisible(false);
			label2.setVisible(false);
			logout.setVisible(false);
			wel.setVisible(true);
			cap.setVisible(true);
			proceed1.setVisible(true);
			
		}
	}
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==one) {
			i=20.0;
			lclassval.setText("1st");
		}
		if(e.getSource()==two) {
			i=1.0;
			lclassval.setText("2nd");
		}
		if(e.getSource()==ret) {
			j=2.0;
			lretval.setText("Yes");
		}
		if(e.getSource()==station) {
			stname1=station.getSelectedItem();
		}
		if(e.getSource()==stationd) {
			stname2=stationd.getSelectedItem();
		}
	}
}
/*<applet code="abc.class" width=600 height=600></applet>*/