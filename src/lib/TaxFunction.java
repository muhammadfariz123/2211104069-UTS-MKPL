public class TaxFunction {

	private static final int BASIC_NON_TAXABLE_INCOME = 54000000;
	private static final int MARRIED_ADDITION = 4500000;
	private static final int CHILD_ADDITION = 1500000;
	private static final int MAX_CHILDREN_COUNT = 3;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		validateInputs(numberOfMonthWorking);

		int cappedChildren = Math.min(numberOfChildren, MAX_CHILDREN_COUNT);
		int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
		int nonTaxableIncome = calculateNonTaxableIncome(isMarried, cappedChildren);
		int taxableIncome = totalIncome - deductible - nonTaxableIncome;

		return calculateFinalTax(taxableIncome);
	}

	private static void validateInputs(int numberOfMonthWorking) {
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
	}

	private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
		int nonTaxable = BASIC_NON_TAXABLE_INCOME;
		if (isMarried) {
			nonTaxable += MARRIED_ADDITION + (numberOfChildren * CHILD_ADDITION);
		}
		return nonTaxable;
	}

	private static int calculateFinalTax(int taxableIncome) {
		if (taxableIncome <= 0) return 0;
		return (int) Math.round(0.05 * taxableIncome);
	}
}
