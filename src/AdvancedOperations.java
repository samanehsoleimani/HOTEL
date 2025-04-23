public class AdvancedOperations extends CalculatorOperations {
    @Override
    protected String performOperation(String a, String b) {
        return null;
    }

    public String power(String base, String exponent) {
        int b = Integer.parseInt(base);
        int e = Integer.parseInt(exponent);
        int[] arr = new int[10000000];
        arr[0] = 1;
        int cA = 1;

        for (int i = 1; i <= e; i++) {
            int carry = 0;
            for (int j = 0; j < cA; j++) {
                int sum = arr[j] * b + carry;
                arr[j] = sum % 10;
                carry = sum / 10;
            }
            while (carry > 0) {
                arr[cA++] = carry % 10;
                carry = carry / 10;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = cA - 1; i >= 0; i--) {
            result.append(arr[i]);
        }
        return result.toString();
    }

    public String factorial(String num) {
        int n = Integer.parseInt(num);
        int[] arr = new int[10000000];
        arr[0] = 1;
        int cA = 1;

        for (int i = 1; i <= n; i++) {
            int carry = 0;
            for (int j = 0; j < cA; j++) {
                int sum = arr[j] * i + carry;
                arr[j] = sum % 10;
                carry = sum / 10;
            }
            while (carry > 0) {
                arr[cA++] = carry % 10;
                carry = carry / 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = cA - 1; i >= 0; i--) {
            result.append(arr[i]);
        }
        return result.toString();
    }
}