import java.math.BigInteger;

public class IbanChecker {

	public boolean validator(String IBAN) {
		try {
			validateLength(IBAN);
			validateCountry(IBAN);
			validateChecksum(IBAN);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static void validateLength(String IBAN) {
		int length = IBAN.length();
		if (length < 15 || length > 34)
			throw new RuntimeException("Invalid IBAN Length");
	}

	private static String getCountryCode(String IBAN) {
		return IBAN.substring(0, 2);
	}

	private static void validateCountry(String IBAN) {
		try {
			Countries.valueOf(getCountryCode(IBAN));
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invalid IBAN Country code");
		}
	}
	private static BigInteger convertToInt(String IBAN) {

		String newIBAN = IBAN.substring(4) + IBAN.substring(0, 4);
		StringBuilder accountNumber = new StringBuilder();

		for (int i = 0; i < newIBAN.length(); i++) {
			accountNumber.append(Character.getNumericValue(newIBAN.charAt(i)));
		}
		return new BigInteger(accountNumber.toString());
	}

	private static void validateChecksum(String IBAN) {
		BigInteger ibanInt = convertToInt(IBAN);
		BigInteger mod = ibanInt.mod(BigInteger.valueOf(97));
		if (mod.intValue() != 1)
			throw new RuntimeException("Invalid IBAN Checksum");
	}
}
