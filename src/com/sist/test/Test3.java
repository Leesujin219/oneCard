package com.sist.test;

class Com extends Test3{
	public Com(){
		System.out.println("com������");
	}
	public void comPlay() {
		System.out.println("com�޼ҵ����");
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
		System.out.println("user������");
	}
	public void userPlay() {
		System.out.println("user�޼ҵ����");
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
		System.out.println("test������");
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
