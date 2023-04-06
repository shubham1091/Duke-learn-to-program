package SolvingProblems.week1;

import edu.duke.*;

public class Hello {
	public static void main(String[] args) {
		runHello();
	}

	public static void runHello() {
		FileResource res = new FileResource();
		for (String line : res.lines()) {
			System.out.println(line);
		}
	}

}
