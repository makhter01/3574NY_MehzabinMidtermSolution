package math;

import databases.ConnectDB;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {

	public int[] array1 = new int[78498]; // total # of primes

	void sieveOfEratosthenes(int n) throws Exception {
		// Create a boolean array "prime[0..n]" and initialize
		// all entries it as true. A value in prime[i] will
		// finally be false if i is Not a prime, else true.
		boolean prime[] = new boolean[n + 1];
		int j = 0;
		ConnectDB connectDB = new ConnectDB();
		List<String> storePattern = new ArrayList<String>();
		final long startTime = System.currentTimeMillis();

		for (int i = 0; i < n; i++)
			prime[i] = true;

		for (int p = 2; p * p <= n; p++) {
			// If prime[p] is not changed, then it is a prime
			if (prime[p] == true) {
				// Update all multiples of p
				for (int i = p * 2; i <= n; i += p)
					prime[i] = false;
			}
		}
		// Print all prime numbers
		for (int i = 2; i <= n; i++) {
			if (prime[i] == true) {
				array1[j] = i;
				System.out.print(i + " ");
				j++;
			}
		}
		final long endTime = System.currentTimeMillis();
		final long executionTime = endTime - startTime;
		System.out.println("\nExecution time for calculating prime number from 1,000,000 numbers: "+executionTime+" milliseconds");

		connectDB.InsertDataFromArryToMySql(array1, "tbl_prime_number", "column_prime_number");
		storePattern = connectDB.readDataBase("tbl_prime_number", "column_prime_number");
		System.out.println("Data is reading from the Table (tbl_prime_number) and displaying to the console");
		for (String st : storePattern) {
			System.out.print(st + " ");
		}
	}

	public static void main(String[] args) throws Exception {
        /*
         * Find list of Prime numbers from number 2 to 1 million.
         * Try the best solution as possible.Which will take less CPU life cycle.
         * Out put number of Prime numbers on the given range.
         *
         *
         * Use any databases[MongoDB, Oracle, MySql] to store data and retrieve data.
         *
         */
		int n = 1000000;

		System.out.print("Following are the prime numbers ");
		System.out.println("smaller than or equal to " + n);

		PrimeNumber g = new PrimeNumber();
		g.sieveOfEratosthenes(n);
	}
}
