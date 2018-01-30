package com.sist.test;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class test extends JFrame {
	test(){
		setTitle("상단제목");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//엑스 누르면 프로그램 종료되게하는 코드. 안넣으면 창만 꺼지고 프로그램 종료 안됨
		setSize(500,500);
		setLayout(new FlowLayout(FlowLayout.CENTER, 30, 40));
		add(new JButton("1"));
		add(new JButton("1"));
		add(new JButton("1"));
		setLayout(new FlowLayout(FlowLayout.CENTER, 30, 40));
		add(new JButton("1"));
		add(new JButton("1"));
		add(new JButton("1"));
		
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		String i="clover 11";
		System.out.println(i.substring(0,i.indexOf(" ")));
		System.out.println(i.substring(i.indexOf(" ")+1));

	
	}
}
