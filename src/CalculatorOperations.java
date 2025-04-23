public abstract class CalculatorOperations {
   //این متد انتزاعی (abstract) است، یعنی در این کلاس پیاده‌سازی نمی‌شود و باید در کلاس‌های فرزند Override شود.
   //این متد دو عدد (a و b) را به‌صورت رشته (String) دریافت می‌کند.
    protected abstract String performOperation(String a, String b);
    //این متد صفرهای اضافی در ابتدای عدد را حذف می‌کند.
    protected String removeLeadingZeros(String number) {
        while (number.length() > 1 && number.charAt(0) == '0') {
            number = number.substring(1);
        }
        return number;
    }
}