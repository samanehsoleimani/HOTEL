import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Stack;

public class CalculatorUI extends JFrame implements ActionListener {
    private JTextArea display;
    private JButton[] numberButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[10];
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decButton, equButton, delButton, clrButton;
    private JButton powButton, factButton;
    private JPanel panel;
   //دو متغیر خصوصی (private) از نوع BasicOperations و AdvancedOperations تعریف شده‌اند.
   //احتمالاً BasicOperations شامل عملیات ریاضی ساده مثل جمع، تفریق، ضرب و تقسیم است.
   //AdvancedOperations ممکن است شامل عملیات پیچیده‌تر مانند توابع مثلثاتی، توان، جذر و غیره باشد.
    private BasicOperations basicOps;
    private AdvancedOperations advancedOps;

    public CalculatorUI() {
       //در این قسمت، دو شیء از BasicOperations و AdvancedOperations ساخته می‌شوند.
        //این اشیا برای انجام محاسبات ریاضی در ماشین‌حساب استفاده خواهند شد.
        basicOps = new BasicOperations();
        advancedOps = new AdvancedOperations();

        setupUI();
    }

    private void setupUI() {
        setTitle(" Calculator");
        setSize(420, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        //این متد تعیین می‌کند که کاربر نتواند اندازه پنجره را تغییر دهد (نمی‌تواند پنجره را با ماوس بزرگ یا کوچک کند).
        setResizable(false);

        getContentPane().setBackground(new Color(240, 240, 240));

        setupDisplay();
        setupButtons();
        setupLayout();
    }

    private void setupDisplay() {
        display = new JTextArea();//ایحاد یک ناخیه برای نمایش
        display.setEditable(false);
        display.setLineWrap(true);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setBackground(new Color(255, 255, 255));
        display.setForeground(new Color(50, 50, 50));
        // تنظیم حاشیه برای فیلد نمایش
        display.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(9, 9, 9), 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        JScrollPane scroll = new JScrollPane(display);
        scroll.setBounds(30, 20, 360, 100);
        scroll.setBorder(null);
        add(scroll);
    }

    private void setupButtons() {
        Color numberColor = new Color(51, 51, 51);
        Color operatorColor = new Color(47, 85, 128);
        Color functionColor = new Color(147, 113, 66);
        Color equalColor = new Color(87, 158, 105);
        Color clearColor = new Color(188, 122, 118);

        addButton = createStyledButton("+", operatorColor);
        subButton = createStyledButton("-", operatorColor);
        mulButton = createStyledButton("×", operatorColor);
        divButton = createStyledButton("÷", operatorColor);
        decButton = createStyledButton(".", numberColor);
        equButton = createStyledButton("=", equalColor);
        delButton = createStyledButton("D", clearColor);
        clrButton = createStyledButton("C", clearColor);
        powButton = createStyledButton("^", functionColor);
        factButton = createStyledButton("!", functionColor);

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


        //دکمه هارو توی یک حلقه قرار میدهد و روی هر دکمه actinlestener  متصل میشود
        for (int i = 0; i < 10; i++) {
            functionButtons[i].addActionListener(this);
        }
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createStyledButton(String.valueOf(i), numberColor);
            numberButtons[i].addActionListener(this);
        }
    }
   //مختصات دکمه ها
    private void setupLayout() {
        delButton.setBounds(30, 500, 170, 60);
        clrButton.setBounds(220, 500, 170, 60);

        panel = new JPanel();
        panel.setBounds(30, 140, 360, 340);
        panel.setLayout(new GridLayout(5, 4, 12, 12));
        panel.setBackground(new Color(255, 255, 255));
        //اضافه کردن دکمه ها به پنل
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
        panel.add(powButton);
        panel.add(factButton);

        add(panel);
        add(delButton);
        add(clrButton);
    }
    //text → متن روی دکمه (مانند "7", "+", "=")
    //bgColor → رنگ پس‌زمینه دکمه
    private JButton createStyledButton(String text, Color bgColor) {
        // طراحی گرافیکی با paintComponent
        JButton button = new JButton(text) {
            @Override

            protected void paintComponent(Graphics g) {
                //ویژگی دکمه ها
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15));
                super.paintComponent(g);
                g2.dispose();
            }
        };
        // تنظیمات ظاهری دکمه
        button.setFont(new Font("Arien", Font.BOLD, 24));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //این بخش افکت تغییر رنگ هنگام حرکت موس را پیاده‌سازی می‌کند.
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.brighter());
                button.repaint();
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
                button.repaint();
            }
        });

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                display.append(String.valueOf(i));
            }
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
            display.append("^");
        }
        if (e.getSource() == factButton) {
            display.append("!");
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
    // عبارت ریاضی رو به‌صورت رشته (string) دریافت می‌کنه و اون رو ارزیابی (evaluate) می‌کنه.
    private String evaluateExpression(String expression) {
        Stack<String> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int i = 0;

        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                numbers.push(numBuilder.toString());
                continue;
            }

            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '!') {
                while (!operators.isEmpty() && hasPrecedence(c, operators.peek())) {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.isEmpty() ? "0" : numbers.pop()));
                }
                operators.push(c);
            }
            i++;
        }

        while (!operators.isEmpty()) {
            numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.isEmpty() ? "0" : numbers.pop()));
        }

        return numbers.pop();
    }

    private boolean hasPrecedence(char op1, char op2) {
        if ((op1 == '*' || op1 == '/' || op1 == '^' || op1 == '!') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private String applyOperation(char operator, String b, String a) {
        switch (operator) {
            case '+':
                return basicOps.add(a, b);
            case '-':
                return basicOps.subtract(a, b);
            case '*':
                return basicOps.multiply(a, b);
            case '/':
                return basicOps.divide(a, b);
            case '^':
                return advancedOps.power(a, b);
            case '!':
                return advancedOps.factorial(b);
        }
        return "0";
    }
} 