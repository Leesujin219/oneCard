package com.sist.onecard;

import java.util.Scanner;

class PlayerPrint{
	int talkNum;
	int menuNum;
	String comTalk;
	String printCard;
	String printShape;
	String printNum;
	Players players;
	boolean comEnd;
	boolean menuEnd;
	
	public PlayerPrint() {
		comEnd=false;
		menuEnd=true;
	}
	int menuPut() {
		Scanner scan=new Scanner(System.in);
		while(true) {//��ȣ �Է�
			try {
				System.out.print("����ȣ�� �Է��ϼ���:");
				menuNum=scan.nextInt();
				if(menuNum<=3&&menuNum>=1) {//������ ���ڰ� 1~3������ ��
					break;
				}else {
					System.out.println("�߸��� �Է��Դϴ�.");
					continue;
				}
			}catch(Exception e) {
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
		}
		return menuNum;
	}
		
	public void menuBoard() {
		comEnd=false;
		System.out.println("����������������������������������������������������������������������������������������������������������������������������������������");
		System.out.println("\t\t�����ޢ���\t\t");
		System.out.println("\t\t�� ī ��\t\t");
		System.out.println("\t\t�����ޢ���\t\t");
		System.out.println("1.���� ����");
		System.out.println("2.���� ����");
		System.out.println("3.����");
		menuNum=menuPut();
		System.out.println();
		System.out.println();
		
		//�޴��� ���� �� ����
		switch(menuNum) {
		case 1:
			ruleInfo();
			break;
		case 2:
			System.out.println("����������������������������������������������������������������������������������������������������������������������������������������");
			gameStart();
			break;
		case 3:
			break;
		}

	}
	
	public void ruleInfo() {
		System.out.println("����������������������������������������������������������������������������������������������������������������������������������������");
		System.out.println("\t\t�����ޢ������ޢ�\t\t");
		System.out.println("\t\t���� �� ����\t\t");
		System.out.println("\t\t�����ޢ������ޢ�\t\t");
		System.out.println();
		System.out.println("�⺻ ��");
		System.out.println("- �÷��̾�� ���� 5���� ī�带 ������ ����");
		System.out.println("- ���� ī��� �������, ������ ī��� ���� ����");
		System.out.println("- ����� ���ų� ���ڰ� ���� ī�带 �� �� �ִ�.");
		System.out.println("- ���� ������ ���߾� �� �� �ִ� ī�带 ��");
		System.out.println("- �� �� �ִ� ī�尡 ���� ��� ī�带 ���� ����");
		System.out.println("- ��� ī�带 �� ����� �¸�");
		System.out.println();
		System.out.println("Ư��ī��");
		System.out.println("- Jī�� : �ѻ���� �ǳʶڴ�");
		System.out.println("- Qī�� : ��������� �������� ����");
		System.out.println("- Kī�� : Kī�带�� �÷��̾�� ī�带 �ѹ� �� �� �� �ִ�");
		System.out.println("- 7ī��: �� �÷��̾�� ���� �� �÷��̾  �� ī���� ����� ������ �� �ִ�.");
		System.out.println();
		System.out.println("����ī��");
		System.out.println("- Aī�� : ��Ģ ī�� 3��");
		System.out.println("- 2ī�� : ��Ģī�� 2��");
		System.out.println("- black Jocker : ��Ģī�� 5�� ");
		System.out.println("- color Jocker : ��Ģī�� 10��");
		System.out.println("- �÷��̾ ����ī�带 ���� ���� �÷��̾�� �̾ ���ڳ� ����� ���� ����ī�带 ���ų� ī�带 �Ծ����");
		System.out.println("- ��Ŀ�� �ƹ����� �� �� �ֽ��ϴ�.");
		System.out.println("- �÷��̾ ��Ģī�带 �԰���  ���� �÷��̾��� ������ �Ѿ�� �⺻�꿡 �°� ī�带 �� �� ����.");
		System.out.println("- ��Ģī�带 �԰� �� �� ���ݷ��� ����� ��Ŀ ������ �� �� �ִ� ī��� ������ �����ϴ�.");
		System.out.println("- black Jocker : �� �� �� �� �� ����");
		System.out.println("- color Jocker : ��� ī�带 �� �� ����");
		System.out.println();
		System.out.println("1. �޴��� ���ư���");
		System.out.println("2. ����");
		menuNum=menuPut();		
		System.out.println();
		System.out.println();
		
		switch(menuNum) {
		case 1:
			menuBoard();
			break;
		case 2:
			break;
		}
	}
	
	public void gameStart() {
		players=new Players();
		Thread thread=new Thread();
		
		backGroundPrint();	
		System.out.println();
		
		breakOut:
		while(true) {
			players.turnPlayer=players.turn%players.totalPlayerN;
			if(players.turnPlayer<0) {
				players.turnPlayer=players.totalPlayerN+players.turnPlayer;	//������ ��������� �־���
			}
			if(players.playerSet[0].size()==0) {//������ �̱�� ���
				computerPrint();
				break breakOut;
			}
			for(int k=0;k<players.comPlayerNum;k++) {	//�Ѹ��� ī�� ���� 0�� �Ǿ��� ��
				if(players.playerSet[k+1].size()==0) {	//��ǻ�͵��� ī�尡 0�� �Ǿ��� ��
					computerPrint();
					break breakOut;
				}
			}
			if(players.turnPlayer==0) {//���� ����
				System.out.println("����:"+players.turnCount+" ����");
				computerPrint();
				cardTopPrint();
				userPrint();
				players.user.userCardPay();
			}else {
				System.out.println("����:"+players.turnCount+" ��ǻ��"+players.turnPlayer);
				computerPrint();
//				computerTurnPrint();
				cardTopPrint();
				userPrint();
				try {
					System.out.println("������ ��ǻ��"+players.turnPlayer+"�� ī�带 �����ֽ��ϴ�..");
					thread.sleep(2000);	
					players.com[players.turnPlayer-1].comCardPay(players.turnPlayer);
					thread.sleep(2000);
					
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println();
			players.turnCount++;
		}gameEnd();
	}
	
	void gameEnd() {
		System.out.println("������ ����Ǿ����ϴ�");
		System.out.println("1.�޴��� ���ư���");
		System.out.println("2.���� ����");
		menuNum=menuPut();
		
		switch(menuNum) {
		case 1:
			break;
		case 2:
			menuEnd=false;
			break;
		}
	}

	public void backGroundPrint() {
		System.out.println("~��ī�带 �����մϴ�~ �� 0:ī�� �Ա�");
	}
	void computerTalkPrint() { //��ǻ�Ͱ� �ϴ� ��
		talkNum=(int)(Math.random()*3);
		if(players.playerSet[0].size()<4) {
			switch(talkNum) {
			case 0:
				comTalk="..��";
				break;
			case 1:
				comTalk="��εǳ�..";
				break;
			case 2:
				comTalk="��ƴ�..��";
				break;
			}
		}else {
			switch(talkNum) {
			case 0:
				comTalk="����";
				break;
			case 1:
				comTalk="���ٰ� ��� ����~";
				break;
			case 2:
				comTalk="�ų���~";
				break;
			}
		}
	}
	public void computerPrint() {
			computerTalkPrint();
		if(players.playerSet[0].size()>0){//������ ī�� ������ 0���� ũ�� ��ǻ�Ͱ� ī�带 ��� ���� ��
			for(int k=0;k<players.comPlayerNum;k++) {
				if(players.playerSet[k+1].size()==0) {//��ǻ�Ͱ� ��� ī�带 ���� ��	
					System.out.println("�Ф�..LOSE");
					System.out.println("��ǻ�� : �޷� ����");
					for(int i=0;i<players.comPlayerNum;i++) {//�Ӹ�
						System.out.print(" (((( \t\t");
					}System.out.println();
					for(int i=0;i<players.comPlayerNum;i++) {//��
						System.out.print(" (^��^) \t\t");
					}System.out.println();
					for(int i=0;i<players.comPlayerNum;i++) {//��
						System.out.print("/(   )\\\t\t");
					}System.out.println();
					System.out.print("��������������������������������������������������������������������������������������");
					System.out.println();
					computerCardPrint();
					System.out.println();
					comEnd=true;
					break;
				}
			}	
			if(comEnd==true) {
				return;
			}
			} 
		if((players.playerSet[0].size()<4)&&(players.playerSet[0].size()>0)) {	//���� ī�� ���� 4���� ���� ��
			System.out.println("��ǻ�� : "+comTalk);
			for(int i=0;i<players.comPlayerNum;i++) {//�Ӹ�
				System.out.print(" (((( \t\t");
				}System.out.println();
			
			for(int i=0;i<players.comPlayerNum;i++) {//��
				System.out.print(" (^��^) \t\t");
				}System.out.println();
			
			for(int i=0;i<players.comPlayerNum;i++) {//��
				System.out.print("/(   )>\t\t");
				}System.out.println();
				
		}else if(players.playerSet[0].size()==0) {//������ ��� ī�带 ���� ��
			System.out.println("�١� WIN�ڡ�  ");
			System.out.println("��ǻ�� : ����..�̤�");
			for(int i=0;i<players.comPlayerNum;i++) {//�Ӹ�
				System.out.print(" (((( \t\t");
				}System.out.println();
			
			for(int i=0;i<players.comPlayerNum;i++) {//��
				System.out.print(" (T��T) \t\t");
				}System.out.println();
			
			for(int i=0;i<players.comPlayerNum;i++) {//��
				System.out.print("/(   )\\\t\t");
				}System.out.println();
				
		}else {
			System.out.println("��ǻ�� : "+comTalk);

			for(int i=0;i<players.comPlayerNum;i++) {//�Ӹ�
				System.out.print(" (((( \t\t");
				}System.out.println();
		
			for(int i=0;i<players.comPlayerNum;i++) {//��
				System.out.print(" (^��^) \t\t");
				}System.out.println();
		
			for(int i=0;i<players.comPlayerNum;i++) {//��
				System.out.print("/(   )>\t\t");
				}System.out.println();
			}
		System.out.print("��������������������������������������������������������������������������������������");
		System.out.println();
		computerCardPrint();
		System.out.println();
	}
	void computerCardPrint() {
		for(int j=0;j<players.comPlayerNum;j++) {	//�÷��̾� �� ī�� ���� ���
			System.out.print("��ǻ��"+(j+1)+" ī�� �� :"+players.playerSet[j+1].size());
			for(int i=0;i<players.playerSet[j+1].size();i++) {
				System.out.print(" ��");
			}System.out.println();
		}
	}
/*	public void computerTurnPrint(int turnPlayer) {
		for(int i=0;i<turnPlayer;i++) {
			System.out.print("\t");
		}
		System.out.print("��ī�� ���� �ߡ�");
		System.out.println();
	}*/
	
	public void cardTopPrint() {
		cardDetailPrint(players.cardTop);	//ž ī�� �־���
		System.out.println("\t������������������");				
		System.out.println("\t��"+printNum+"      "+"��");	
		System.out.println("\t��"+"   "+printShape+"   "+"��");
		System.out.println("\t��"+"      "+printNum+"��");	
		System.out.println("\t������������������");
	}
	void cardDetailPrint(String card) {	//ī�� ����̶� ���� ���� �̱�
		printCard=card;	//ī�� �޾ƿ�
		printShape=printCard.substring(0,printCard.indexOf(" "));	//�պκи� �ڸ�
		printNum=printCard.substring(printCard.indexOf(" ")+1);	//�޺κи� �ڸ�
		
		switch(printShape) {
		case "spade" :
			printShape="��";
			break;
		case "diamond":
			printShape="��";
			break;
		case "heart":
			printShape="��";
			break;
		case "clover":
			printShape="��";
			break;
		case "jocker":
			printShape="��";
			break;
		}
		switch(printNum) {
		case "color":
			printNum="C";
			break;
		case "black":
			printNum="B";
			break;
		
		}
		
	}
	public void userPrint() {
		System.out.println("ī���ȣ:");
		for(int i=0;i<players.playerSet[0].size();i++) {
			cardDetailPrint(players.playerSet[0].get(i));
			System.out.print((i+1)+": ["+printNum+" "+printShape+" "+printNum+"] ");
			if(i>0&&i%7==0) {
				System.out.println();
			}
		}
		System.out.println();
	}
}

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlayerPrint p=new PlayerPrint();
		while(p.menuEnd) {
			p.menuBoard();	
		}
		
		
		
	/*	Thread thread=new Thread();
		
		PlayerPrint p=new PlayerPrint();
		p.backGroundPrint();	
		System.out.println();
		
		breakOut:
		while(true) {
			p.players.turnPlayer=p.players.turn%p.players.totalPlayerN;
			if(p.players.turnPlayer<0) {
				p.players.turnPlayer=p.players.totalPlayerN+p.players.turnPlayer;	//������ ��������� �־���
			}
			if(p.players.playerSet[0].size()==0) {//������ �̱�� ���
				p.computerPrint();
				break breakOut;
			}
			for(int k=0;k<p.players.comPlayerNum;k++) {	//�Ѹ��� ī�� ���� 0�� �Ǿ��� ��
				if(p.players.playerSet[k+1].size()==0) {	//��ǻ�͵��� ī�尡 0�� �Ǿ��� ��
					p.computerPrint();
					break breakOut;
				}
			}
			if(p.players.turnPlayer==0) {//���� ����
				System.out.println("����:"+p.players.turnCount+" ����");
				p.computerPrint();
				p.cardTopPrint();
				p.userPrint();
				p.players.user.userCardPay();
			}else {
				System.out.println("����:"+p.players.turnCount+" ��ǻ��"+p.players.turnPlayer);
				p.computerPrint();
//				p.computerTurnPrint();
				p.cardTopPrint();
				p.userPrint();
				try {
					System.out.println("������ ��ǻ��"+p.players.turnPlayer+"�� ī�带 �����ֽ��ϴ�..");
					thread.sleep(2000);	
					p.players.com[p.players.turnPlayer-1].comCardPay(p.players.turnPlayer);
					thread.sleep(2000);
					
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println();
			p.players.turnCount++;
		}*/
	}
}
