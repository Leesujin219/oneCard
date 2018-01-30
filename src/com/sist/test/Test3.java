package com.sist.test;

class Com extends Test3{
	public Com(){
		System.out.println("com积己磊");
	}
	public void comPlay() {
		System.out.println("com皋家靛角青");
		int i=(int)(Math.random()*9);
		com=a[i];
		a[i]=32;
		System.out.println(com);
		for(int i1:a) {
			System.out.print(i1+" ");
		}System.out.println();
	}
}
class User extends Test3{
	public User(){
		System.out.println("user积己磊");
	}
	public void userPlay() {
		System.out.println("user皋家靛角青");
		while(true) {
		int i=(int)(Math.random()*9);
		System.out.println(i);
		if(a[i]==32) {
			continue;
		}
		user=a[i];
		a[i]=32;
		System.out.println(user);
		for(int i1:a) {
			System.out.print(i1+" ");
		}System.out.println();
		break;
		}
	}
}

public class Test3 {
	int[] a= {0,1,2,3,4,5,6,7,8};
	int com;
	int user;
	public Test3() {
		System.out.println("test积己磊");
		for(int i:a) {
			System.out.print(i+" ");
		}System.out.println();
	}		
	public static void main(String[] args) {
		Test3 test=new Test3();
		Com com=new Com();
		User user=new User();
		com.comPlay();
		user.userPlay();
		
	}
}
