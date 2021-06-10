package xyz.acacian.test;



public class TestMain {
	public static void main(String[] args) {
		TestA ab = new TestB();
		TestA ac = new TestC();
		
		TestB bb = new TestB();
		TestB bc = new TestC();
		
		TestC cc = new TestC();
		
		System.out.println("---ab---");
		ab.methodA();
		ab.methodB();

		System.out.println("---ac---");
		ac.methodA();
		ac.methodB();

		System.out.println("---bb---");
		bb.methodA();
		bb.methodB();

		System.out.println("---bc---");
		bc.methodA();
		bc.methodB();

		System.out.println("---cc---");
		cc.methodA();
		cc.methodB();
		
	}
}
