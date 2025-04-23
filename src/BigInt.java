import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class BigInt extends JFrame implements ActionListener {
    private JTextArea display;//display یک شیء از نوع JTextArea است که برای نمایش اعداد و نتایج محاسبات استفاده می‌شود.
    private JButton[] numberButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[10];
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decButton, equButton, delButton, clrButton;
    private JButton powButton, factButton;
    private JPanel panel;

    public BigInt() {
        setTitle("Calculator");
        setSize(420, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


        getContentPane().setBackground(new Color(240, 240, 240, 255)); //تنظیم رنگ پس‌زمینه کلی

        display = new JTextArea();
        display.setLineWrap(true);  // فعال‌سازی خط‌بندی
        display.setFont(new Font("Arial", Font.BOLD, 24)); // فونت بزرگ‌تر
        display.setBackground(new Color(255, 255, 255)); // پس‌زمینه سفید
        display.setForeground(new Color(9, 9, 9)); // متن تیره
        JScrollPane scroll = new JScrollPane(display); // ایجاد اسکرول پنل
        scroll.setBounds(50, 0, 300, 100); // تنظیم موقعیت و اندازه
        add(scroll); // افزودن اسکرول پنل به فریم

        // ایجاد دکمه‌ها با ظاهر جذاب
        addButton = createStyledButton("+", new Color(100, 150, 200));
        subButton = createStyledButton("-", new Color(100, 150, 200));
        mulButton = createStyledButton("*", new Color(100, 150, 200));
        divButton = createStyledButton("/", new Color(100, 150, 200));
        decButton = createStyledButton(".", new Color(50, 50, 50));
        equButton = createStyledButton("=", new Color(50, 180, 100));
        delButton = createStyledButton("Del", new Color(200, 100, 100));
        clrButton = createStyledButton("Clr", new Color(200, 100, 100));
        powButton = createStyledButton("^", new Color(150, 100, 200));
        factButton = createStyledButton("!", new Color(150, 100, 200));

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = powButton;
        functionButtons[9] = factButton;
        //این حلقه ActionListener را به تمام دکمه‌های عملیاتی متصل می‌کند.
        //این کار باعث می‌شود که وقتی روی دکمه‌ها کلیک شود، متد actionPerformed اجرا شود.
        for (int i = 0; i < 10; i++) {
            functionButtons[i].addActionListener(this);
        }
        //این حلقه ۱۰ دکمه برای اعداد ۰ تا ۹ ایجاد می‌کند و به هرکدام ActionListener متصل می‌کند.
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createStyledButton(String.valueOf(i), new Color(50, 50, 50));
            numberButtons[i].addActionListener(this);
        }
        //مختصات دگمه ها
        delButton.setBounds(50, 430, 145, 50);
        clrButton.setBounds(205, 430, 145, 50);
        //ایجاد یک پنل برای چیدمان دکمه‌ها.
        panel = new JPanel();
        //تنظیم موقعیت و اندازه
        panel.setBounds(50, 100, 300, 300);
        //چیدمان5در4
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBackground(new Color(240, 240, 240));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);
        panel.add(powButton); // اضافه کردن دکمه توان
        panel.add(factButton); // اضافه کردن دکمه فاکتوریل

        //این دستورها دکمه‌ها را به پنجره اضافه می‌کنند.
        add(panel);
        add(delButton);
        add(clrButton);

        setVisible(true);
    }

    // تابع برای ایجاد دکمه‌های با ظاهر جذاب
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);// با متن مشخص‌ شده ایجاد می‌کند.
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setBackground(bgColor);//رنگ پس‌زمینه دکمه را به مقدار ورودی bgColor تغییر می‌دهد.
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                display.append(String.valueOf(i));  // به جای setText از append استفاده کنید
            }
        }
        if (e.getSource() == decButton) {
            display.append(".");
        }
        if (e.getSource() == addButton) {
            display.append("+");
        }
        if (e.getSource() == subButton) {
            display.append("-");
        }
        if (e.getSource() == mulButton) {
            display.append("*");
        }
        if (e.getSource() == divButton) {
            display.append("/");
        }
        if (e.getSource() == powButton) {
            display.append("^"); // اضافه کردن نماد توان
        }
        if (e.getSource() == factButton) {
            display.append("!"); // اضافه کردن نماد فاکتوریل
        }
        if (e.getSource() == equButton) {
            String expression = display.getText();
            try {
                String result = evaluateExpression(expression);
                display.setText(result);
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
        if (e.getSource() == clrButton) {
            display.setText("");
        }
        if (e.getSource() == delButton) {
            String string = display.getText();
            if (string.length() > 0) {
                display.setText(string.substring(0, string.length() - 1));
            }
        }
    }

    // تابع برای ارزیابی عبارات ریاضی
    private String evaluateExpression(String expression) {
        // استک برای ذخیره اعداد به‌صورت رشته (String)
        Stack<String> numbers = new Stack<>();
        //استک برای ذخیره عملگرها (+, -, *, /, ^, !)
        Stack<Character> operators = new Stack<>();
        int i = 0;

        while (i < expression.length()) {
            char c = expression.charAt(i);

            // اگر کاراکتر عدد بود
            if (Character.isDigit(c) || c == '.') {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                numbers.push(numBuilder.toString()); // ذخیره عدد به صورت String
                continue;
            }

            // اگر کاراکتر عملگر بود
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '!') {
                while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.isEmpty() ? "0" : numbers.pop()));
                }
                operators.push(c);
            }
            i++;
        }

        // پردازش باقی‌مانده عملگرها
        while (!operators.isEmpty()) {
            numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.isEmpty() ? "0" : numbers.pop()));
        }

        return numbers.pop();
    }

    // بررسی اولویت عملگرها
    private boolean hasPrecedence(char op1, char op2) {
        if ((op1 == '*' || op1 == '/' || op1 == '^' || op1 == '!') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    // اعمال عملیات ریاضی
    private String applyOperation(char operator, String b, String a) {
        switch (operator) {
            case '+':
                return add_(a, b);
            case '-':
                return sub_(a, b);
            case '*':
                return mul_(a, b);
            case '/':
                return divide(a, b);
            case '^':
                return power(a, b);
            case '!':
                return factorial(b);
        }
        return "0";
    }

    // متد جمع
    public static String add_ (String s1 , String s2) {
        int temp1 = s1.length();// طول اعداد //temp طول اعداد را ذخیره میکن
        int temp2 = s2.length();

        int[] arr1 = new int[temp1];//ارقام معکوس
        int[] arr2 = new int[temp2];
        int a1 = 0;
        int a2 = 0;
        //ذخیره ارقام
        for (int i = temp1 - 1; i >= 0; i--) {
            arr1[i] = s1.charAt(a1) - '0';
            a1++;
        }

        for (int i = temp2 - 1; i >= 0; i--) {
            arr2[i] = s2.charAt(a2) - '0';
            a2++;
        }
        //عملیات جمع ار کم ارزش ترین به پر ارزش ترین میرود
        int max_ = Math.max(temp1, temp2);

        int[] arr3 = new int[max_ + 1];
        int q = 0;
        //جمع رقم به رقم
        for (int i = 0; i < max_; i++) {
            int sum = q;
            if (i < temp1) sum += arr1[i];
            if (i < temp2) sum += arr2[i];

            arr3[i] = sum% 10;
            q = sum / 10;
        }

        while (q > 0) {
            arr3[max_++] = q % 10;
            q = q / 10;
        }
        //تبدیل ارایه به رشته
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

    // تفریق
    public static String sub_ (String s1 , String s2) {
        //معکوس کردن اعداد و تنظیم پرچم منفی
        boolean isNegative = false;
        if (s1.length() < s2.length() || (s1.length() == s2.length() && s1.compareTo(s2) < 0)) {
            String temp = s1;
            s1 = s2;
            s2 = temp;
            isNegative = true;
        }

        int[] arr1 = new int[s1.length()];
        int[] arr2 = new int[s2.length()];
        //تبدیل ارقام s1 , s2 به ارایه معکوس
        for (int i = 0; i < s1.length(); i++) {
            arr1[i] = s1.charAt(s1.length() - 1 - i) - '0';
        }

        for (int i = 0; i < s2.length(); i++) {
            arr2[i] = s2.charAt(s2.length() - 1 - i) - '0';
        }

        //اجرای تفریق رقم به رقم
        int maxSize = Math.max(arr1.length, arr2.length);
        int[] temp = new int[maxSize + 1];

        //اجرای تفریق
        for (int i = 0; i < temp.length; i++) {
            int sub1 = (i < arr1.length) ? arr1[i] : 0;
            int sub2 = (i < arr2.length) ? arr2[i] : 0;

            if (sub1 < sub2) {
                sub1 += 10;
                if (i + 1 < arr1.length) {
                    arr1[i + 1] -= 1;
                } else {
                    System.out.println("Result is negative or invalid.");
                }
            }

            temp[i] = sub1 - sub2;
        }

        //تبدیل خروجی به رشته
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

    // ضرب
    public static String mul_ (String s1 , String s2) {
        int temp1 = s1.length();
        int temp2 = s2.length();

        int[] arr1 = new int[temp1];
        int[] arr2 = new int[temp2];
        int a1 = 0;
        int a2 = 0;

        //ذخیره ارقام معکوس
        for (int i = temp1 - 1; i >= 0; i--) {
            arr1[i] = s1.charAt(a1) - '0';
            a1++;
        }

        for (int i = temp2 - 1; i >= 0; i--) {
            arr2[i] = s2.charAt(a2) - '0';
            a2++;
        }

        //ارایه برای نتیجه ضرب
        int[] arr3 = new int[temp1 + temp2 + 2];
        int carry = 0;

        for (int i = 0 ; i < temp1 ; i++) {
            for (int j = 0 ; j < temp2; j++){
                int product = (arr1[i] * arr2[j]) + arr3[i + j] + carry;
                arr3[i + j] = product % 10;
                carry = product / 10;
            }
            arr3[i + temp2] += carry;
            carry = 0;
        }

        //تبدیل رشته به خروجی
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

    // تقسیم
    public static String divide(String dividend, String divisor) {
        StringBuilder result = new StringBuilder();
        StringBuilder currentDividend = new StringBuilder();

        //بررسی تمام ارقامdividend
        for (int i = 0; i < dividend.length(); i++) {
            // ر بار، یک رقم جدید از dividend به currentDividend اضافه می‌شود.
            currentDividend.append(dividend.charAt(i));

            while (currentDividend.length() > 1 && currentDividend.charAt(0) == '0') {
                currentDividend.deleteCharAt(0);
            }


            int currentDividendNum = Integer.parseInt(currentDividend.toString());
            int divisorNum = Integer.parseInt(divisor);
            //اگر currentDividendNum کوچکتر از divisorNum باشد، عدد جاری را نمی‌توان تقسیم کرد و 0 به result اضافه می‌شود
            if (currentDividendNum < divisorNum) {
                result.append("0");
                // محاسبه‌ی خارج قسمت و باقی‌مانده
            } else {

                int quotient = currentDividendNum / divisorNum;
                result.append(quotient);


                int remainder = currentDividendNum % divisorNum;
                currentDividend = new StringBuilder(Integer.toString(remainder));
            }
        }

        while (result.length() > 1 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        return result.toString();
    }

    // توان
    private String power(String base, String exponent) {
        int b = Integer.parseInt(base);
        int e = Integer.parseInt(exponent);
        int[] arr = new int[10000000];
        arr[0] = 1;
        int cA = 1;

        //ضرب متوالی
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

    // فاکتوریل
    private String factorial(String num) {
        int n = Integer.parseInt(num);
        int[] arr = new int[10000000];
        arr[0] = 1;
        int cA = 1;

        for (int i = 1 ; i <= n ; i++) {
            int carry = 0;
            for (int j = 0 ; j < cA ; j++) {
                int sum = arr[j] * i + carry;
                arr[j] = sum % 10;
                carry = sum / 10;
            }
            while (carry > 0) {
                arr[cA++] = carry % 10;
                carry = carry / 10;
            }
        }

        String result = "";
        for (int i = cA - 1 ; i >=0 ; i--) {
            result += arr[i];
        }
        return result;
    }

}