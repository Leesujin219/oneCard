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
		while(true) {//번호 입력
			try {
				System.out.print("▶번호를 입력하세요:");
				menuNum=scan.nextInt();
				if(menuNum<=3&&menuNum>=1) {//선택한 숫자가 1~3사이일 때
					break;
				}else {
					System.out.println("잘못된 입력입니다.");
					continue;
				}
			}catch(Exception e) {
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}
		return menuNum;
	}
		
	public void menuBoard() {
		comEnd=false;
		System.out.println("────────────────────────────────────────────────────────────────────");
		System.out.println("\t\t♡♠◇♣♡\t\t");
		System.out.println("\t\t원 카 드\t\t");
		System.out.println("\t\t♡♠◇♣♡\t\t");
		System.out.println("1.게임 설명");
		System.out.println("2.게임 시작");
		System.out.println("3.종료");
		menuNum=menuPut();
		System.out.println();
		System.out.println();
		
		//메뉴판 선택 후 연결
		switch(menuNum) {
		case 1:
			ruleInfo();
			break;
		case 2:
			System.out.println("────────────────────────────────────────────────────────────────────");
			gameStart();
			break;
		case 3:
			break;
		}

	}
	
	public void ruleInfo() {
		System.out.println("────────────────────────────────────────────────────────────────────");
		System.out.println("\t\t♡♠◇♣♡♠◇♣\t\t");
		System.out.println("\t\t게임 룰 설명\t\t");
		System.out.println("\t\t♡♠◇♣♡♠◇♣\t\t");
		System.out.println();
		System.out.println("기본 룰");
		System.out.println("- 플레이어는 각각 5장의 카드를 나누어 받음");
		System.out.println("- 남은 카드는 엎어놓고, 맨위의 카드로 게임 시작");
		System.out.println("- 모양이 같거나 숫자가 같은 카드를 낼 수 있다.");
		System.out.println("- 진행 순서에 맞추어 낼 수 있는 카드를 냄");
		System.out.println("- 낼 수 있는 카드가 없는 경우 카드를 한장 먹음");
		System.out.println("- 모든 카드를 낸 사람이 승리");
		System.out.println();
		System.out.println("특수카드");
		System.out.println("- J카드 : 한사람을 건너뛴다");
		System.out.println("- Q카드 : 진행방향을 역순으로 변경");
		System.out.println("- K카드 : K카드를낸 플레이어는 카드를 한번 더 낼 수 있다");
		System.out.println("- 7카드: 낸 플레이어는 다음 턴 플레이어가  낼 카드의 모양을 결정할 수 있다.");
		System.out.println();
		System.out.println("공격카드");
		System.out.println("- A카드 : 벌칙 카드 3장");
		System.out.println("- 2카드 : 벌칙카드 2장");
		System.out.println("- black Jocker : 벌칙카드 5장 ");
		System.out.println("- color Jocker : 벌칙카드 10장");
		System.out.println("- 플레이어가 공격카드를 내면 다음 플레이어는 이어서 숫자나 모양이 같은 공격카드를 내거나 카드를 먹어야함");
		System.out.println("- 조커는 아무때나 낼 수 있습니다.");
		System.out.println("- 플레이어가 벌칙카드를 먹고나면  다음 플레이어의 순서로 넘어가고 기본룰에 맞게 카드를 낼 수 있음.");
		System.out.println("- 벌칙카드를 먹고 난 후 공격력이 사라진 조커 다음에 낼 수 있는 카드는 다음과 같습니다.");
		System.out.println("- black Jocker : ♠ ♣ 를 낼 수 있음");
		System.out.println("- color Jocker : 모든 카드를 낼 수 있음");
		System.out.println();
		System.out.println("1. 메뉴로 돌아가기");
		System.out.println("2. 종료");
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
				players.turnPlayer=players.totalPlayerN+players.turnPlayer;	//음수면 양수값으로 넣어줌
			}
			if(players.playerSet[0].size()==0) {//유저가 이기는 경우
				computerPrint();
				break breakOut;
			}
			for(int k=0;k<players.comPlayerNum;k++) {	//한명의 카드 수가 0이 되었을 때
				if(players.playerSet[k+1].size()==0) {	//컴퓨터들의 카드가 0이 되었을 때
					computerPrint();
					break breakOut;
				}
			}
			if(players.turnPlayer==0) {//유저 차례
				System.out.println("▶턴:"+players.turnCount+" 유저");
				computerPrint();
				cardTopPrint();
				userPrint();
				players.user.userCardPay();
			}else {
				System.out.println("▶턴:"+players.turnCount+" 컴퓨터"+players.turnPlayer);
				computerPrint();
//				computerTurnPrint();
				cardTopPrint();
				userPrint();
				try {
					System.out.println("▶현재 컴퓨터"+players.turnPlayer+"은 카드를 내고있습니다..");
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
		System.out.println("게임이 종료되었습니다");
		System.out.println("1.메뉴로 돌아가기");
		System.out.println("2.게임 종료");
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
		System.out.println("~원카드를 시작합니다~ ※ 0:카드 먹기");
	}
	void computerTalkPrint() { //컴퓨터가 하는 말
		talkNum=(int)(Math.random()*3);
		if(players.playerSet[0].size()<4) {
			switch(talkNum) {
			case 0:
				comTalk="..흠";
				break;
			case 1:
				comTalk="고민되네..";
				break;
			case 2:
				comTalk="어렵다..ㅎ";
				break;
			}
		}else {
			switch(talkNum) {
			case 0:
				comTalk="ㅎㅎ";
				break;
			case 1:
				comTalk="진다고 울기 없기~";
				break;
			case 2:
				comTalk="신난다~";
				break;
			}
		}
	}
	public void computerPrint() {
			computerTalkPrint();
		if(players.playerSet[0].size()>0){//유저의 카드 갯수가 0보다 크고 컴퓨터가 카드를 모두 냈을 떄
			for(int k=0;k<players.comPlayerNum;k++) {
				if(players.playerSet[k+1].size()==0) {//컴퓨터가 모든 카드를 냈을 때	
					System.out.println("ㅠㅠ..LOSE");
					System.out.println("컴퓨터 : 메렁 ㅎㅎ");
					for(int i=0;i<players.comPlayerNum;i++) {//머리
						System.out.print(" (((( \t\t");
					}System.out.println();
					for(int i=0;i<players.comPlayerNum;i++) {//얼굴
						System.out.print(" (^ㅠ^) \t\t");
					}System.out.println();
					for(int i=0;i<players.comPlayerNum;i++) {//몸
						System.out.print("/(   )\\\t\t");
					}System.out.println();
					System.out.print("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
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
		if((players.playerSet[0].size()<4)&&(players.playerSet[0].size()>0)) {	//유저 카드 수가 4보다 적을 때
			System.out.println("컴퓨터 : "+comTalk);
			for(int i=0;i<players.comPlayerNum;i++) {//머리
				System.out.print(" (((( \t\t");
				}System.out.println();
			
			for(int i=0;i<players.comPlayerNum;i++) {//얼굴
				System.out.print(" (^△^) \t\t");
				}System.out.println();
			
			for(int i=0;i<players.comPlayerNum;i++) {//몸
				System.out.print("/(   )>\t\t");
				}System.out.println();
				
		}else if(players.playerSet[0].size()==0) {//유저가 모든 카드를 냈을 때
			System.out.println("☆★ WIN★☆  ");
			System.out.println("컴퓨터 : 졌다..ㅜㅜ");
			for(int i=0;i<players.comPlayerNum;i++) {//머리
				System.out.print(" (((( \t\t");
				}System.out.println();
			
			for(int i=0;i<players.comPlayerNum;i++) {//얼굴
				System.out.print(" (T─T) \t\t");
				}System.out.println();
			
			for(int i=0;i<players.comPlayerNum;i++) {//몸
				System.out.print("/(   )\\\t\t");
				}System.out.println();
				
		}else {
			System.out.println("컴퓨터 : "+comTalk);

			for(int i=0;i<players.comPlayerNum;i++) {//머리
				System.out.print(" (((( \t\t");
				}System.out.println();
		
			for(int i=0;i<players.comPlayerNum;i++) {//얼굴
				System.out.print(" (^▽^) \t\t");
				}System.out.println();
		
			for(int i=0;i<players.comPlayerNum;i++) {//몸
				System.out.print("/(   )>\t\t");
				}System.out.println();
			}
		System.out.print("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
		System.out.println();
		computerCardPrint();
		System.out.println();
	}
	void computerCardPrint() {
		for(int j=0;j<players.comPlayerNum;j++) {	//플레이어 의 카드 개수 출력
			System.out.print("컴퓨터"+(j+1)+" 카드 수 :"+players.playerSet[j+1].size());
			for(int i=0;i<players.playerSet[j+1].size();i++) {
				System.out.print(" ▩");
			}System.out.println();
		}
	}
/*	public void computerTurnPrint(int turnPlayer) {
		for(int i=0;i<turnPlayer;i++) {
			System.out.print("\t");
		}
		System.out.print("●카드 내는 중●");
		System.out.println();
	}*/
	
	public void cardTopPrint() {
		cardDetailPrint(players.cardTop);	//탑 카드 넣어줌
		System.out.println("\t┌───────┐");				
		System.out.println("\t│"+printNum+"      "+"│");	
		System.out.println("\t│"+"   "+printShape+"   "+"│");
		System.out.println("\t│"+"      "+printNum+"│");	
		System.out.println("\t└───────┘");
	}
	void cardDetailPrint(String card) {	//카드 모양이랑 숫자 각각 뽑기
		printCard=card;	//카드 받아옴
		printShape=printCard.substring(0,printCard.indexOf(" "));	//앞부분만 자름
		printNum=printCard.substring(printCard.indexOf(" ")+1);	//뒷부분만 자름
		
		switch(printShape) {
		case "spade" :
			printShape="♠";
			break;
		case "diamond":
			printShape="◇";
			break;
		case "heart":
			printShape="♡";
			break;
		case "clover":
			printShape="♣";
			break;
		case "jocker":
			printShape="♬";
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
		System.out.println("카드번호:");
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
				p.players.turnPlayer=p.players.totalPlayerN+p.players.turnPlayer;	//음수면 양수값으로 넣어줌
			}
			if(p.players.playerSet[0].size()==0) {//유저가 이기는 경우
				p.computerPrint();
				break breakOut;
			}
			for(int k=0;k<p.players.comPlayerNum;k++) {	//한명의 카드 수가 0이 되었을 때
				if(p.players.playerSet[k+1].size()==0) {	//컴퓨터들의 카드가 0이 되었을 때
					p.computerPrint();
					break breakOut;
				}
			}
			if(p.players.turnPlayer==0) {//유저 차례
				System.out.println("▶턴:"+p.players.turnCount+" 유저");
				p.computerPrint();
				p.cardTopPrint();
				p.userPrint();
				p.players.user.userCardPay();
			}else {
				System.out.println("▶턴:"+p.players.turnCount+" 컴퓨터"+p.players.turnPlayer);
				p.computerPrint();
//				p.computerTurnPrint();
				p.cardTopPrint();
				p.userPrint();
				try {
					System.out.println("▶현재 컴퓨터"+p.players.turnPlayer+"은 카드를 내고있습니다..");
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
