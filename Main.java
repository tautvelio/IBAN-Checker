import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);
		IbanChecker checker = new IbanChecker();

		System.out.println("IBAN CHECKER");
		System.out.println("Press 1 for IBAN checking from a line");
		System.out.println("Press 2 for IBAN checking from a file");

		String menu = scanner.nextLine();
		switch (menu) {
		case "1":
			System.out.println("Enter IBAN: ");
			String ibanInput = scanner.nextLine();
			if (checker.validator(ibanInput)) {
				System.out.println("IBAN format is correct");
			} else {
				System.out.println("IBAN format is not recognised");
			}
			break;

		case "2":
			System.out.println("Insert file path: ");
			String path = scanner.nextLine();

			System.out.print("Enter file name without file extension: ");
			String file = scanner.nextLine();

			String fullName = path + file;

			BufferedReader input = null;
			BufferedWriter output = null;

			String line = "";
			try {
				input = new BufferedReader(new FileReader(fullName + ".txt"));

				output = new BufferedWriter(new FileWriter(fullName + ".out"));

				while ((line = input.readLine()) != null) {
					if (checker.validator(line)) {
						System.out.println(line + ";true" + "\n");
						output.write(line + ";true" + "\n");
					} else {
						System.out.println(line + ";false" + "\n");
						output.write(line + ";false" + "\n");
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("There is no file named " + fullName + ".txt");
			} finally {

				if (input != null) {
					input.close();
				}
				if (output != null) {
					output.close();
				}
			}
			System.out.println("File is created in directory: " + fullName + ".out");
		}
	}

}
