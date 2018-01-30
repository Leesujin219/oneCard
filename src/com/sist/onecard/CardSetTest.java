package com.sist.onecard;

import java.util.ArrayList;
import java.util.Scanner;

class CardDeck {
	String[] type= {"spade","diamond","clover","heart","jocker"};
	String[] cardDeck=new String[54];
	public CardDeck() {
		int k=0;	
		for(int i=0;i<5;i++) {
				for(int j=1;j<14;j++) {
					cardDeck[k]=type[i]+" "+j;
					++k;
					if(i==4 && j==2) {
						break;
					}
				}
		}
		for(int i=0;i<cardDeck.length;i++) {
			//11>J 12>Q 13>K 
			switch(cardDeck[i].substring(cardDeck[i].indexOf(" ")+1)) {
			case "11":
				cardDeck[i]=cardDeck[i].replace("11","J");
				break;
			case "12":
				cardDeck[i]=cardDeck[i].replace("12","Q");
				break;
			case "13":	
				cardDeck[i]=cardDeck[i].replace("13","K");
				break;
			}//jocker 1>color jocker 2>black
			if(cardDeck[i].substring(0,cardDeck[i].indexOf(" ")).equals("jocker")) {
				if(cardDeck[i].substring(cardDeck[i].indexOf(" ")+1).equals("1"))
					cardDeck[i]=cardDeck[i].replace("1","color");
				else 
					cardDeck[i]=cardDeck[i].replace("2","black");
			}
		}

	}
}
//���Ӿȿ��� ī�� ����
class CardSetIG extends CardDeck{
	
	ArrayList<String>[] playerSet;
	ArrayList<String> cardDeckIG;	//���� ������ ī�嵦
	String cardTop;	//����� ī��.(�ӽ�����뵵)
	String cardTopShape;//����� ī�� ����
	String cardTopNum;//����� ī�� ����
	int cardStackNum;	// �ǵ��鼭 ���̴� ��Ģ ī�� ��
	int turn; //���� ī��Ʈ(JQK �ݿ�)
	public int turnCount;	//�� ī��Ʈ
	int pickNum;	//ī�� ���� �� ���� ����
	int comPlayerNum;	//��ǻ���� ��
	int totalPlayerN;	//���÷��̾� ��
	
	public CardSetIG() { 
		cardDeckIG=new ArrayList<>();
		 for(int i=0;i<cardDeck.length;i++) { //ī�嵦�� Arraylist�� �Ű� ���
				cardDeckIG.add(cardDeck[i]);
		 }
		 

		 numPlayers();
		 System.out.println("��ǻ�� ��:"+comPlayerNum);
		 totalPlayerN=comPlayerNum+1;//���÷��̾� �� (��ǻ�� ��+����)
		 playerSet=new ArrayList[totalPlayerN];	//��ǻ��+���� ī���Ѵ��� ArrayList
		 for(int i=0;i<totalPlayerN;i++) {	//�� �÷��̾� �� ��ŭ ī�� ��(playerSet����)
			 playerSet[i]=new ArrayList<>();
		 }
		 //�÷��̾��� ī�� ���� ���� ����
		 for(int i=0;i<totalPlayerN;i++) {
			 for(int j=0;j<5;j++) {
				int num=(int)(Math.random()*cardDeckIG.size());	//0~cardDeckIG-1 ������ ��������. 
				playerSet[i].add(cardDeckIG.get(num));//cardDeckIG���� ī�� �ϳ� �̾Ƽ� �÷��̾� set�� ����
				cardDeckIG.remove(num);//���� ī�带 ������ ����				
			 }
		 }
		pickNum=(int)(Math.random()*cardDeckIG.size());//ī����� �� �ʿ��� �����ѹ�
		cardTop=cardDeckIG.get(pickNum);
		cardDeckIG.remove(pickNum);//�ӽ�����뵵�� ī��� ���� �� �� ����
		splitCard(cardTop);
	}	
	public void numPlayers() {
		Scanner scan=new Scanner(System.in);
		while(true) {
			try {
				System.out.print("������ ��ǻ�� �� �Է�(1~3��): ");
				comPlayerNum=scan.nextInt();	
			
				if(comPlayerNum<=3&&comPlayerNum>=1) {
					break;
				}else {
					System.out.println("�߸��� �Է��Դϴ�.");
					continue;
				}
			}catch(Exception e) {
				System.out.println("�߸��� �Է��Դϴ�.");		
				comPlayerNum=2;	
				System.out.println("����Ʈ 2������ �����մϴ�.");
				break;
			}
		}
	
	}
	public void splitCard(String cardTop) {
		cardTopShape=cardTop.substring(0,cardTop.indexOf(" ")).trim();
		cardTopNum=cardTop.substring(cardTop.indexOf(" ")+1).trim();
	}
}
public class CardSetTest{
	public static void main(String[] args) {
		CardSetIG cardSet=new CardSetIG();
		for(int i=0;i<cardSet.totalPlayerN;i++) {
			for(String j:cardSet.playerSet[i]) {
				System.out.print(j+" ");	
			}
				System.out.println();
		}
		for(int i=0;i<cardSet.cardDeckIG.size();i++) {
			System.out.println(cardSet.cardDeckIG.get(i));
		}
	}
}