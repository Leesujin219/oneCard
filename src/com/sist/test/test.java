package com.sist.test;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class test extends JFrame {
	test(){
		setTitle("�������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���� ������ ���α׷� ����ǰ��ϴ� �ڵ�. �ȳ����� â�� ������ ���α׷� ���� �ȵ�
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
