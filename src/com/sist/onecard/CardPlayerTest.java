package com.sist.onecard;

import java.util.Scanner;

class Players extends CardSetIG{
	int playerType;	//�÷��̾��� Ÿ��
	int payNum;	//ī�� ���� ī�带 ��� ��. �� ī�� ��ȣ
	int turnPlayer;
	String payCard;	//���� ī��
	String payCardShape;
	String payCardNum;
	int select7;	//7ī�� ���� �� 
	String select7Shape;
	boolean turnDirection;

	ComPlayer[] com;
	UserPlayer user;
	
	public Players() {
		turnDirection=false;
		user=new UserPlayer();
		com=new ComPlayer[comPlayerNum];	//ComPlayer�� �迭����
		for(int i=0;i<comPlayerNum;i++) {	
			com[i]=new ComPlayer(i+1);	//�Է��� ��ǻ�� ����� ComPlayer ��ü ����
		}
	}
	
	public void cardPay() {
			payCard=playerSet[playerType].get(payNum);
			payCardShape=payCard.substring(0,payCard.indexOf(" ")).trim();
			payCardNum=payCard.substring(payCard.indexOf(" ")+1).trim();
			splitCard(cardTop);
			/*System.out.println(playerType+"��payCard :"+payCard);
			System.out.println("payCardNum:"+payCardNum);
			System.out.println("payCardShape:"+payCardShape);
			System.out.println("���� CardTop:"+cardTop);
			System.out.println("cardTopShape:"+cardTopShape);
			System.out.println("cardTopNum:"+cardTopNum);
			System.out.println("cardStack:"+cardStackNum);
			*/
			if(cardStackNum>0) {	//���Ͽ��� ���� ī�尡 ���� ��
				switch(cardTopNum) {	//����ī�尡 ���� �� (���� ī���� ���ڰ� ������ ��(1,2,jocker))
				case "1":
					if(payCardNum.equals("1") || 	//1,��Ŀ,����̰��� 2�϶� ī�带 �� �� �ִ�.
							payCardShape.equals("jocker")|| 
							(payCardShape.equals(cardTopShape) && payCardNum.equals("2"))) {
//						System.out.println("case 1���� �����");
						payingCard();
					}
					break;
				case "2":
					if(payCardNum.equals("2") || 	//2,��Ŀ,����̰��� 1�϶� ī�带 �� �� �ִ�.
							payCardShape.equals("jocker")|| 
							(payCardShape.equals(cardTopShape) && payCardNum.equals("1"))) {
						payingCard();
//						System.out.println("case 2���� �����");

					}	
					break;
				case "color":
					if(payCardNum.equals("black")) {  //�����Ŀ�� �� �� �ִ�.
						payingCard();
					}	
					break;
				case "black":
					if(payCardNum.equals("color")|| 
							(payCardNum.equals("1")&&payCardShape.equals("spade"))) {	//�÷� Ȥ�� �����̵� A�� �� �� �ִ�.
						payingCard();
					}	
				break;
				}
			}else {	//����ī�尡 �ƴ� �Ϲ�ī�尡 ���ö�(���ݷ� �����(cardStackNum=0) ���� ī��� ����)
				if(cardTopNum.equals("7")&&(turnCount!=0)) {	//žī�尡 7�� ��(ī�� ���ٲ�)
					if(payCardShape.equals(select7Shape)||payCardNum.equals(cardTopNum)) {	//�ٲ���� �� ī���� ����� ���� ��,�� ī�尡 7�� ��
						payingCard();
					};
				}else {//žī�尡 7�� �ƴ� ��
					if(cardTopNum.equals("color")) {//�� ī�尡 �÷���Ŀ�϶�, �ƹ�ī�峪 �� �� ����
						payingCard();
					}else if((payCardShape.equals("spade")||payCardShape.equals("clover"))&&cardTopNum.equals("black")) {//��ī�尡 �����Ŀ�϶�
						payingCard();
					}else if(payCardNum.equals(cardTopNum)
						||payCardShape.equals(cardTopShape)
							||payCardShape.equals("jocker")) {//ž ī��� ���ڳ� ����� ���� ī�峪 ��Ŀ�� �� �� �ִ�.
						payingCard();
//						System.out.println("case �Ϲ� ���� �����");
					}
					
			/*		else if((cardTopShape.equals("spade")||cardTopShape.equals("clover"))&&payCardNum.equals("black")) {
						//��ī�尡 �����̵峪 Ŭ�ι��� ��(���) �����Ŀ�� �� �� �ִ�.
						payingCard();
					}*/
				}
			}	
	}//cardPay �޼ҵ� ����
	
	
	void payingCard() {
		System.out.println("���� ����� ī�� :"+cardTop);
		cardDeckIG.add(cardTop);//������ ī��ž�� ������ ��
		cardTop=payCard;	//�� ī�尡 ī�� ž�̵�(ī�带 ��)
		System.out.println("�� ī�� :"+cardTop);
		playerSet[playerType].remove(payNum);	//�� ī�带 �÷��̾� ������ ����

		sevenCardRule();	//ī�� 7�� ���� �� ��
//		System.out.println("�ٲ� ī�� ���:"+select7Shape);
		cardStackRule();
		turn=cardTurnRule();

		payCard="0";
//		System.out.println("payCard ����:"+payCard);
	}
	void cardStackRule() {
		switch(payCardNum) {
		case "1":
			cardStackNum+=3; 
			break;
		case "2":
			cardStackNum+=2;
			break;
		case "black":
			cardStackNum+=5;
			break;
		case "color":
			cardStackNum+=7;
			break;
		}
	}
	boolean qCardRule() {
		//�� �������� ����� ī�� Q�� ��Ģ
		if(payCardNum.equals("Q")) {	//turnDirection�� ������ false(������)
			turnDirection=!turnDirection;	//false�� true(������) �� ��. true ������ false		
			return turnDirection;
		}else return turnDirection;
	}
	void sevenCardRule() {
		//���� �� ī�带 ���ϴ� ���+7 ī��� �����Ѵ�
		if(payCardNum.equals("7")) {
			if(playerType==0) {	//������ 7�� �� ���
				Scanner scan=new Scanner(System.in);
				while(true) {
					System.out.println();
					System.out.println("\t��ī�� ��� �ٲٱ��\t");
					System.out.println("0.�ٲ��� ���� 1.�� clover 2.�� spade 3.�� heart 4.�� diamond");
					System.out.print("�ٲ� ����� �Է��� �ּ���:");
					select7=scan.nextInt();
					switch(select7) {
					case 0:
						select7Shape=payCardShape;
						System.out.println("����� �ٲ��� �ʽ��ϴ�.");
						break;
					case 1:
						select7Shape="clover";
						System.out.println("�� �� clover �� ����� �ٲߴϴ�.");
						break;
					case 2:
						select7Shape="spade";
						System.out.println("�� �� spade �� ����� �ٲߴϴ�.");
						break;
					case 3:
						select7Shape="heart";
						System.out.println("�� �� heart �� ����� �ٲߴϴ�.");
						break;
					case 4:
						select7Shape="diamond";
						System.out.println("�� �� diamond �� ����� �ٲߴϴ�.");
						break;
					default:
						System.out.println("�߸��� �Է��Դϴ�.");
						continue;
					}
					break;
				}
				
			}else {//��ǻ�Ͱ� 7�� �����
				select7=(int)(Math.random()*5);//0~4������ �� ���
				switch(select7) {
				case 0:
					select7Shape=payCardShape;
					System.out.println("����� �ٲ��� �ʽ��ϴ�.");
					break;
				case 1:
					select7Shape="clover";
					System.out.println("�� �� clover �� ����� �ٲߴϴ�.");
					break;
				case 2:
					select7Shape="spade";
					System.out.println("�� �� spade �� ����� �ٲߴϴ�.");
					break;
				case 3:
					select7Shape="heart";
					System.out.println("�� �� heart �� ����� �ٲߴϴ�.");
					break;
				case 4:
					select7Shape="diamond";
					System.out.println("�� �� diamond �� ����� �ٲߴϴ�.");
					break;
				default:
					System.out.println("�߸��� �Է��Դϴ�.");
				}
			}
		}
	}
	int cardTurnRule() {
		qCardRule();
//		System.out.println("�Ϲ���:"+turnDirection);
		if(turnDirection==true) {	//���������� �� ����
			if(payCardNum.equals("J")) {	
				turn-=2;	//�Ѹ� �پ�ѱ�
			}else if(payCardNum.equals("K")) {
				turn-=totalPlayerN;	//�ڱ��ڽ����� ���ƿ���
			}
				else turn--;	//���� ������ �ѱ��
			return turn;
			
		}else {
			if(payCardNum.equals("J")) {	
				turn+=2;	//�Ѹ� �پ�ѱ�
			}else if(payCardNum.equals("K")) {
				turn+=totalPlayerN;	//�ڱ��ڽ����� ���ƿ���
			}
				else turn++;	//���� ������ �ѱ��
			return turn;
		}
	}
	
	public void cardGet() {	//�� ī�尡 ���� �� cardGet �޼ҵ带 �ҷ���
		if(turnDirection==true) {
			if(cardStackNum>0) {	//������ ��Ģī�尡 0���� Ŭ��
				for(int i=0;i<cardStackNum;i++) {//���� ��Ģī�常ŭ ī�� ������ �̾� ����
					pickCard();
				}	turn--;//�� ����
			}
			else {	//���� �Դ� ��Ȳ
				pickCard();
				turn--;//�� ����
			}
		}else {
			if(cardStackNum>0) {	//������ ��Ģī�尡 0���� Ŭ��
				for(int i=0;i<cardStackNum;i++) {//���� ��Ģī�常ŭ ī�� ������ �̾� ����
					pickCard();
				}	turn++;//�� ����
			}
			else {	//���� �Դ� ��Ȳ
				pickCard();
				turn++;//�� ����
			}
		}	
		cardStackNum=0;//������ ��Ģī�� ���� 0���� ����
	}
	void pickCard() {
		pickNum=(int)(Math.random()*cardDeckIG.size());
		playerSet[playerType].add(cardDeckIG.get(pickNum));//������ ���� ī�带 �÷��̾� ���� �߰�
		cardDeckIG.remove(pickNum);//����ī�带 ī�� ������ ����
	}
	
	//����Ŭ���� ComPlayer
	public class ComPlayer{
		public ComPlayer(int num) {
			playerType=num;
		}
		public void comCardPay(int num) {
			playerType=num;
			for(int i=0;i<playerSet[playerType].size();i++){	//ī�� ���� ���ʺ��� ī�带 ã��
				payNum=i;
				cardPay();
//				System.out.println("��ǻ���� payCard: "+payCard);
			if(payCard.equals("0")) {//payingCard �޼ҵ� ����. (ī�带 ���� ��� payCard "0"�̵�.)
				System.out.println("����ǻ�Ͱ� ī�带 �½��ϴ�.");
				break;//���� for���� ��������
			}
			if(i==(playerSet[playerType].size()-1)) {
				cardGet();
				System.out.println("����ǻ�Ͱ� ī�带 �Խ��ϴ�.");
//				System.out.println("��ǻ�� ī�����");
//				System.out.println("���� žī��:"+cardTop);
				break;
			}	
			}
		}
	}//���� Ŭ���� Complayer�� ��

	public class UserPlayer{
		public UserPlayer() {
			playerType=0;
		}
		public void userCardPay() {
			playerType=0;	//������ playerType�� 0����
			Scanner scan=new Scanner(System.in);	//��ĳ�ʷ� payNum ����(�� ī���� ��ȣ)
			//ī���׽�Ʈ��
			/*for(String i:playerSet[0]) {
			System.out.println("����ī��:"+i);}
			*/
			breakOut : //�ݺ��� Ż������ ����
			while(true) {	//�� ����� �Է��� �� ���� ���� �ݺ�
				try {
					System.out.print("ī�� ��ȣ�� �Է��ϼ���: ");
					payNum=scan.nextInt()-1;	//����ڰ� 0�� �Է��ϸ� ī�� �Դ� ��
//					System.out.println("payNum:"+payNum);
					if(payNum>playerSet[playerType].size()){
						System.out.println("�߸��� �Է��Դϴ�. ");
						continue;
					}else if(payNum==-1) {//����ڰ� 0�Է� ���� ��
						cardGet();
						break breakOut;
					}
					else if((payNum<=playerSet[playerType].size())&&(payNum>=0)){	
						cardPay();
						if(payCard!="0") {	//�� ���ִ� ī�尡 �ƴ� ��
							if(cardTopNum.equals("7")) {
								System.out.println("��"+select7Shape+"����� ī�带 �����մϴ�.");
							}
							if(cardStackNum>0){
								System.out.println("��"+cardStackNum+"���� ī�带 �Ծ�� �մϴ�.");
							}
							System.out.println("�� ī��� �� �� ����^^;;");
							continue;
						}
						else {
							break breakOut;	//������� while������ Ż��
						}		
					}
				}catch(Exception e) {
					System.out.println("�߸��� �Է��Դϴ�. ");
					continue;
				}
			}
		}//userCardPay�� ��
	}
}

	
public class CardPlayerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
