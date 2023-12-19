package asm;

public class CC {
	
	public void m1() throws Exception {
		
		System.out.println("now in method m1----->");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int a = 55;
		System.out.println("now a===="+a);
	}
}
