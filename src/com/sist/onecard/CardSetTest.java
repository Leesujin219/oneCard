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
//게임안에서 카드 세팅
class CardSetIG extends CardDeck{
	
	ArrayList<String>[] playerSet;
	ArrayList<String> cardDeckIG;	//게임 실행중 카드덱
	String cardTop;	//꼭대기 카드.(임시저장용도)
	String cardTopShape;//꼭대기 카드 무늬
	String cardTopNum;//꼭대기 카드 숫자
	int cardStackNum;	// 판돌면서 쌓이는 벌칙 카드 수
	int turn; //순서 카운트(JQK 반영)
	public int turnCount;	//턴 카운트
	int pickNum;	//카드 뽑을 때 쓰는 변수
	int comPlayerNum;	//컴퓨터의 수
	int totalPlayerN;	//총플레이어 수
	
	public CardSetIG() { 
		cardDeckIG=new ArrayList<>();
		 for(int i=0;i<cardDeck.length;i++) { //카드덱을 Arraylist에 옮겨 담기
				cardDeckIG.add(cardDeck[i]);
		 }
		 

		 numPlayers();
		 System.out.println("컴퓨터 수:"+comPlayerNum);
		 totalPlayerN=comPlayerNum+1;//총플레이어 수 (컴퓨터 수+유저)
		 playerSet=new ArrayList[totalPlayerN];	//컴퓨터+유저 카드팩담을 ArrayList
		 for(int i=0;i<totalPlayerN;i++) {	//총 플레이어 수 만큼 카드 팩(playerSet생성)
			 playerSet[i]=new ArrayList<>();
		 }
		 //플레이어의 카드 덱을 세팅 해줌
		 for(int i=0;i<totalPlayerN;i++) {
			 for(int j=0;j<5;j++) {
				int num=(int)(Math.random()*cardDeckIG.size());	//0~cardDeckIG-1 까지의 난수추출. 
				playerSet[i].add(cardDeckIG.get(num));//cardDeckIG에서 카드 하나 뽑아서 플레이어 set에 넣음
				cardDeckIG.remove(num);//뽑은 카드를 덱에서 지움				
			 }
		 }
		pickNum=(int)(Math.random()*cardDeckIG.size());//카드뽑을 때 필요한 랜덤넘버
		cardTop=cardDeckIG.get(pickNum);
		cardDeckIG.remove(pickNum);//임시저장용도의 카드는 덱에 들어갈 수 없다
		splitCard(cardTop);
	}	
	public void numPlayers() {
		Scanner scan=new Scanner(System.in);
		while(true) {
			try {
				System.out.print("대적할 컴퓨터 수 입력(1~3명): ");
				comPlayerNum=scan.nextInt();	
			
				if(comPlayerNum<=3&&comPlayerNum>=1) {
					break;
				}else {
					System.out.println("잘못된 입력입니다.");
					continue;
				}
			}catch(Exception e) {
				System.out.println("잘못된 입력입니다.");		
				comPlayerNum=2;	
				System.out.println("디폴트 2명으로 설정합니다.");
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