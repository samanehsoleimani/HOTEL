public class BasicOperations extends CalculatorOperations {
    @Override
    protected String performOperation(String a, String b) {
        return null;
    }

    public String add(String s1, String s2) {
        int temp1 = s1.length();
        int temp2 = s2.length();

        int[] arr1 = new int[temp1];
        int[] arr2 = new int[temp2];
        int a1 = 0;
        int a2 = 0;

        for (int i = temp1 - 1; i >= 0; i--) {
            arr1[i] = s1.charAt(a1) - '0';
            a1++;
        }

        for (int i = temp2 - 1; i >= 0; i--) {
            arr2[i] = s2.charAt(a2) - '0';
            a2++;
        }

        int max_ = Math.max(temp1, temp2);
        int[] arr3 = new int[max_ + 1];
        int q = 0;

        for (int i = 0; i < max_; i++) {
            int sum = q;
            if (i < temp1) sum += arr1[i];
            if (i < temp2) sum += arr2[i];

            arr3[i] = sum % 10;
            q = sum / 10;
        }

        while (q > 0) {
            arr3[max_++] = q % 10;
            q = q / 10;
        }

        StringBuilder result = new StringBuilder();
        boolean leadingZero = true;
        for (int i = arr3.length - 1; i >= 0; i--) {
            if (arr3[i] != 0) {
                leadingZero = false;
            }
            if (!leadingZero) {
                result.append(arr3[i]);
            }
        }

        if (leadingZero) {
            result.append(0);
        }
        return result.toString();
    }

    public String subtract(String s1, String s2) {
        boolean isNegative = false;
        if (s1.length() < s2.length() || (s1.length() == s2.length() && s1.compareTo(s2) < 0)) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
            isNegative = true;
        }

        int[] arr1 = new int[s1.length()];
        int[] arr2 = new int[s2.length()];

        for (int i = 0; i < s1.length(); i++) {
            arr1[i] = s1.charAt(s1.length() - 1 - i) - '0';
        }

        for (int i = 0; i < s2.length(); i++) {
            arr2[i] = s2.charAt(s2.length() - 1 - i) - '0';
        }

        int maxSize = Math.max(arr1.length, arr2.length);
        int[] temp = new int[maxSize + 1];

        for (int i = 0; i < temp.length; i++) {
            int sub1 = (i < arr1.length) ? arr1[i] : 0;
            int sub2 = (i < arr2.length) ? arr2[i] : 0;

            if (sub1 < sub2) {
                sub1 += 10;
                if (i + 1 < arr1.length) {
                    arr1[i + 1] -= 1;
                }
            }

            temp[i] = sub1 - sub2;
        }

        StringBuilder result = new StringBuilder();
        boolean leadingZero = true;
        if (isNegative) {
            result.append("-");
        }

        for (int i = maxSize - 1; i >= 0; i--) {
            if (temp[i] != 0) leadingZero = false;
            if (!leadingZero) {
                result.append(temp[i]);
            }
        }
        if (leadingZero) {
            result.append(0);
        }
        return result.toString();
    }

    public String multiply(String s1, String s2) {
        int temp1 = s1.length();
        int temp2 = s2.length();

        int[] arr1 = new int[temp1];
        int[] arr2 = new int[temp2];
        int a1 = 0;
        int a2 = 0;

        for (int i = temp1 - 1; i >= 0; i--) {
            arr1[i] = s1.charAt(a1) - '0';
            a1++;
        }

        for (int i = temp2 - 1; i >= 0; i--) {
            arr2[i] = s2.charAt(a2) - '0';
            a2++;
        }

        int[] arr3 = new int[temp1 + temp2 + 2];
        int carry = 0;

        for (int i = 0; i < temp1; i++) {
            for (int j = 0; j < temp2; j++) {
                int product = (arr1[i] * arr2[j]) + arr3[i + j] + carry;
                arr3[i + j] = product % 10;
                carry = product / 10;
            }
            arr3[i + temp2] += carry;
            carry = 0;
        }

        StringBuilder result = new StringBuilder();
        boolean leadingZero = true;
        for (int i = arr3.length - 1; i >= 0; i--) {
            if (arr3[i] != 0) {
                leadingZero = false;
            }
            if (!leadingZero) {
                result.append(arr3[i]);
            }
        }

        if (leadingZero) {
            result.append(0);
        }
        return result.toString();
    }

    public String divide(String dividend, String divisor) {
        StringBuilder result = new StringBuilder();
        StringBuilder currentDividend = new StringBuilder();

        for (int i = 0; i < dividend.length(); i++) {
            currentDividend.append(dividend.charAt(i));

            while (currentDividend.length() > 1 && currentDividend.charAt(0) == '0') {
                currentDividend.deleteCharAt(0);
            }

            int currentDividendNum = Integer.parseInt(currentDividend.toString());
            int divisorNum = Integer.parseInt(divisor);

            if (currentDividendNum < divisorNum) {
                result.append("0");
            } else {
                int quotient = currentDividendNum / divisorNum;
                result.append(quotient);

                int remainder = currentDividendNum % divisorNum;
                currentDividend = new StringBuilder(Integer.toString(remainder));
            }
        }

        return removeLeadingZeros(result.toString());
    }
}