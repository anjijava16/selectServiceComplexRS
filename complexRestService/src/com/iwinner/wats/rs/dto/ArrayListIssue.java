package com.iwinner.wats.rs.dto;

import java.util.ArrayList;

public class ArrayListIssue {
	public static void main(String args[]) {
		int n = 5;
		int k = 100000;

		ArrayList<String> list = new ArrayList<String>(n);
		char[] array = new char[3];

		for (int i = 0; i < n; i++) {

			array[0] = 'a';
			array[1] = 'b';
			array[2] = 'c';
			list.add(new String(array));

			if (i % k == 0) {
				long m1 = Runtime.getRuntime().totalMemory()
						- Runtime.getRuntime().freeMemory();
				m1 /= (1024 * 1024);
				System.out.println("memory used  when " + i + " key inserted ="
						+ m1 + " MBytes.");
			}
		}

	}
}
