package com.sist.onecard;

import java.util.Scanner;

class Players extends CardSetIG{
	int playerType;	//플레이어의 타입
	int payNum;	//카드 낼땐 카드를 골라서 냄. 낼 카드 번호
	int turnPlayer;
	String payCard;	//내는 카드
	String payCardShape;
	String payCardNum;
	int select7;	//7카드 냈을 때 
	String select7Shape;
	boolean turnDirection;

	ComPlayer[] com;
	UserPlayer user;
	
	public Players() {
		turnDirection=false;
		user=new UserPlayer();
		com=new ComPlayer[comPlayerNum];	//ComPlayer의 배열생성
		for(int i=0;i<comPlayerNum;i++) {	
			com[i]=new ComPlayer(i+1);	//입력한 컴퓨터 수대로 ComPlayer 객체 생성
		}
	}
	
	public void cardPay() {
			payCard=playerSet[playerType].get(payNum);
			payCardShape=payCard.substring(0,payCard.indexOf(" ")).trim();
			payCardNum=payCard.substring(payCard.indexOf(" ")+1).trim();
			splitCard(cardTop);
			/*System.out.println(playerType+"의payCard :"+payCard);
			System.out.println("payCardNum:"+payCardNum);
			System.out.println("payCardShape:"+payCardShape);
			System.out.println("현재 CardTop:"+cardTop);
			System.out.println("cardTopShape:"+cardTopShape);
			System.out.println("cardTopNum:"+cardTopNum);
			System.out.println("cardStack:"+cardStackNum);
			*/
			if(cardStackNum>0) {	//앞턴에서 공격 카드가 들어올 때
				switch(cardTopNum) {	//공격카드가 들어올 때 (맨위 카드의 숫자가 공격일 때(1,2,jocker))
				case "1":
					if(payCardNum.equals("1") || 	//1,조커,모양이같은 2일때 카드를 낼 수 있다.
							payCardShape.equals("jocker")|| 
							(payCardShape.equals(cardTopShape) && payCardNum.equals("2"))) {
//						System.out.println("case 1에서 통과됨");
						payingCard();
					}
					break;
				case "2":
					if(payCardNum.equals("2") || 	//2,조커,모양이같은 1일때 카드를 낼 수 있다.
							payCardShape.equals("jocker")|| 
							(payCardShape.equals(cardTopShape) && payCardNum.equals("1"))) {
						payingCard();
//						System.out.println("case 2에서 통과됨");

					}	
					break;
				case "color":
					if(payCardNum.equals("black")) {  //흑색조커만 낼 수 있다.
						payingCard();
					}	
					break;
				case "black":
					if(payCardNum.equals("color")|| 
							(payCardNum.equals("1")&&payCardShape.equals("spade"))) {	//컬러 혹은 스페이드 A만 낼 수 있다.
						payingCard();
					}	
				break;
				}
			}else {	//공격카드가 아닌 일반카드가 들어올때(공격력 사라진(cardStackNum=0) 공격 카드들 포함)
				if(cardTopNum.equals("7")&&(turnCount!=0)) {	//탑카드가 7일 때(카드 모양바뀜)
					if(payCardShape.equals(select7Shape)||payCardNum.equals(cardTopNum)) {	//바뀐모양과 낼 카드의 모양이 같을 때,낼 카드가 7일 때
						payingCard();
					};
				}else {//탑카드가 7이 아닐 때
					if(cardTopNum.equals("color")) {//앞 카드가 컬러조커일때, 아무카드나 낼 수 있음
						payingCard();
					}else if((payCardShape.equals("spade")||payCardShape.equals("clover"))&&cardTopNum.equals("black")) {//앞카드가 흑색조커일때
						payingCard();
					}else if(payCardNum.equals(cardTopNum)
						||payCardShape.equals(cardTopShape)
							||payCardShape.equals("jocker")) {//탑 카드와 숫자나 모양이 같은 카드나 조커를 낼 수 있다.
						payingCard();
//						System.out.println("case 일반 에서 통과됨");
					}
					
			/*		else if((cardTopShape.equals("spade")||cardTopShape.equals("clover"))&&payCardNum.equals("black")) {
						//앞카드가 스페이드나 클로버일 때(흑백) 흑색조커를 낼 수 있다.
						payingCard();
					}*/
				}
			}	
	}//cardPay 메소드 종료
	
	
	void payingCard() {
		System.out.println("현재 꼭대기 카드 :"+cardTop);
		cardDeckIG.add(cardTop);//기존의 카드탑이 덱으로 들어감
		cardTop=payCard;	//낸 카드가 카드 탑이됨(카드를 냄)
		System.out.println("낸 카드 :"+cardTop);
		playerSet[playerType].remove(payNum);	//낸 카드를 플레이어 덱에서 지움

		sevenCardRule();	//카드 7을 냈을 때 룰
//		System.out.println("바꾼 카드 모양:"+select7Shape);
		cardStackRule();
		turn=cardTurnRule();

		payCard="0";
//		System.out.println("payCard 리셋:"+payCard);
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
		//턴 역순으로 만드는 카드 Q의 규칙
		if(payCardNum.equals("Q")) {	//turnDirection의 원래값 false(정방향)
			turnDirection=!turnDirection;	//false면 true(역방향) 가 됨. true 였으면 false		
			return turnDirection;
		}else return turnDirection;
	}
	void sevenCardRule() {
		//다음 낼 카드를 원하는 모양+7 카드로 변경한다
		if(payCardNum.equals("7")) {
			if(playerType==0) {	//유저가 7을 낼 경우
				Scanner scan=new Scanner(System.in);
				while(true) {
					System.out.println();
					System.out.println("\t§카드 모양 바꾸기§\t");
					System.out.println("0.바꾸지 않음 1.♣ clover 2.♠ spade 3.♡ heart 4.◇ diamond");
					System.out.print("바꿀 모양을 입력해 주세요:");
					select7=scan.nextInt();
					switch(select7) {
					case 0:
						select7Shape=payCardShape;
						System.out.println("모양이 바뀌지 않습니다.");
						break;
					case 1:
						select7Shape="clover";
						System.out.println("▶ ♣ clover 로 모양을 바꿉니다.");
						break;
					case 2:
						select7Shape="spade";
						System.out.println("▶ ♠ spade 로 모양을 바꿉니다.");
						break;
					case 3:
						select7Shape="heart";
						System.out.println("▶ ♡ heart 로 모양을 바꿉니다.");
						break;
					case 4:
						select7Shape="diamond";
						System.out.println("▶ ◇ diamond 로 모양을 바꿉니다.");
						break;
					default:
						System.out.println("잘못된 입력입니다.");
						continue;
					}
					break;
				}
				
			}else {//컴퓨터가 7을 낸경우
				select7=(int)(Math.random()*5);//0~4까지의 값 출력
				switch(select7) {
				case 0:
					select7Shape=payCardShape;
					System.out.println("모양이 바뀌지 않습니다.");
					break;
				case 1:
					select7Shape="clover";
					System.out.println("▶ ♣ clover 로 모양을 바꿉니다.");
					break;
				case 2:
					select7Shape="spade";
					System.out.println("▶ ♠ spade 로 모양을 바꿉니다.");
					break;
				case 3:
					select7Shape="heart";
					System.out.println("▶ ♡ heart 로 모양을 바꿉니다.");
					break;
				case 4:
					select7Shape="diamond";
					System.out.println("▶ ◇ diamond 로 모양을 바꿉니다.");
					break;
				default:
					System.out.println("잘못된 입력입니다.");
				}
			}
		}
	}
	int cardTurnRule() {
		qCardRule();
//		System.out.println("턴방향:"+turnDirection);
		if(turnDirection==true) {	//역방향으로 턴 뺴줌
			if(payCardNum.equals("J")) {	
				turn-=2;	//한명 뛰어넘기
			}else if(payCardNum.equals("K")) {
				turn-=totalPlayerN;	//자기자신으로 돌아오기
			}
				else turn--;	//다음 텀으로 넘기기
			return turn;
			
		}else {
			if(payCardNum.equals("J")) {	
				turn+=2;	//한명 뛰어넘기
			}else if(payCardNum.equals("K")) {
				turn+=totalPlayerN;	//자기자신으로 돌아오기
			}
				else turn++;	//다음 텀으로 넘기기
			return turn;
		}
	}
	
	public void cardGet() {	//낼 카드가 없을 때 cardGet 메소드를 불러옴
		if(turnDirection==true) {
			if(cardStackNum>0) {	//누적된 벌칙카드가 0보다 클때
				for(int i=0;i<cardStackNum;i++) {//누적 벌칙카드만큼 카드 덱에서 뽑아 먹음
					pickCard();
				}	turn--;//턴 증가
			}
			else {	//한장 먹는 상황
				pickCard();
				turn--;//턴 증가
			}
		}else {
			if(cardStackNum>0) {	//누적된 벌칙카드가 0보다 클때
				for(int i=0;i<cardStackNum;i++) {//누적 벌칙카드만큼 카드 덱에서 뽑아 먹음
					pickCard();
				}	turn++;//턴 증가
			}
			else {	//한장 먹는 상황
				pickCard();
				turn++;//턴 증가
			}
		}	
		cardStackNum=0;//누적된 벌칙카드 수를 0으로 변경
	}
	void pickCard() {
		pickNum=(int)(Math.random()*cardDeckIG.size());
		playerSet[playerType].add(cardDeckIG.get(pickNum));//덱에서 뽑은 카드를 플레이어 덱에 추가
		cardDeckIG.remove(pickNum);//뽑은카드를 카드 덱에서 삭제
	}
	
	//내부클래스 ComPlayer
	public class ComPlayer{
		public ComPlayer(int num) {
			playerType=num;
		}
		public void comCardPay(int num) {
			playerType=num;
			for(int i=0;i<playerSet[playerType].size();i++){	//카드 덱의 왼쪽부터 카드를 찾음
				payNum=i;
				cardPay();
//				System.out.println("컴퓨터의 payCard: "+payCard);
			if(payCard.equals("0")) {//payingCard 메소드 참고. (카드를 내는 경우 payCard "0"이됨.)
				System.out.println("▶컴퓨터가 카드를 냈습니다.");
				break;//내고 for문을 빠져나옴
			}
			if(i==(playerSet[playerType].size()-1)) {
				cardGet();
				System.out.println("▶컴퓨터가 카드를 먹습니다.");
//				System.out.println("컴퓨터 카드먹음");
//				System.out.println("현재 탑카드:"+cardTop);
				break;
			}	
			}
		}
	}//내부 클래스 Complayer의 끝

	public class UserPlayer{
		public UserPlayer() {
			playerType=0;
		}
		public void userCardPay() {
			playerType=0;	//유저는 playerType를 0으로
			Scanner scan=new Scanner(System.in);	//스캐너로 payNum 받음(낼 카드의 번호)
			//카드테스트용
			/*for(String i:playerSet[0]) {
			System.out.println("유저카드:"+i);}
			*/
			breakOut : //반복문 탈출위해 넣음
			while(true) {	//값 제대로 입력할 때 까지 무한 반복
				try {
					System.out.print("카드 번호를 입력하세요: ");
					payNum=scan.nextInt()-1;	//사용자가 0을 입력하면 카드 먹는 것
//					System.out.println("payNum:"+payNum);
					if(payNum>playerSet[playerType].size()){
						System.out.println("잘못된 입력입니다. ");
						continue;
					}else if(payNum==-1) {//사용자가 0입력 했을 때
						cardGet();
						break breakOut;
					}
					else if((payNum<=playerSet[playerType].size())&&(payNum>=0)){	
						cardPay();
						if(payCard!="0") {	//낼 수있는 카드가 아닐 때
							if(cardTopNum.equals("7")) {
								System.out.println("▶"+select7Shape+"모양의 카드를 내야합니다.");
							}
							if(cardStackNum>0){
								System.out.println("▶"+cardStackNum+"장의 카드를 먹어야 합니다.");
							}
							System.out.println("이 카드는 낼 수 없음^^;;");
							continue;
						}
						else {
							break breakOut;	//여기오면 while문에서 탈출
						}		
					}
				}catch(Exception e) {
					System.out.println("잘못된 입력입니다. ");
					continue;
				}
			}
		}//userCardPay의 끝
	}
}

	
public class CardPlayerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
