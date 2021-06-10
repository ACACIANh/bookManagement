package xyz.acacian.test;

public class TestC extends TestB{
	@Override
	public void methodA() {
		System.out.println("C.methodA() call");
		super.methodA();
	}
	@Override
	public void methodB() {
		System.out.println("C.methodB() call");
		super.methodB();
	}
}
