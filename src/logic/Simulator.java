package logic;

import java.util.concurrent.TimeUnit;

public class Simulator {
	
	private static long NS_PER_TICK = 16000000;
	private static int n = 0;
	
	public void startSimulator() throws InterruptedException {
		while (n<60) {
			long start = System.nanoTime();
			processInput();
			update();
			render();
			TimeUnit.NANOSECONDS.sleep(start + NS_PER_TICK - System.nanoTime());
			System.out.print("E");
			n++;
		}
	}
	
	public void startRenderingSimulator() throws InterruptedException {
		while (n<60) {
			long start = System.nanoTime();
			processInput();
			update();
			render();
			TimeUnit.NANOSECONDS.sleep(start + NS_PER_TICK - System.nanoTime());
			System.out.print("E");
			n++;
		}
	}

	private void processInput() {
		// TODO Auto-generated method stub
		
	}

	private void render() {
		// TODO Auto-generated method stub
		
	}

	private void update() {
		// TODO Auto-generated method stub
		
	}
}
