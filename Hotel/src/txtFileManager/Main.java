package txtFileManager;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import Manager.*;
import Common.*;

public class Main extends JFrame {

    private GuestsManager guestsManager;
    private RoomManager roomManager;

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public Main() {
        guestsManager = new GuestsManager();
        roomManager = new RoomManager();
        RoomManager rm = new RoomManager();
        rm.initializeRooms(); // فقط یک بار اجرا بشه – مقداردهی اولیه

        setTitle("سیستم مدیریت هتل");
        setSize(700, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // تنظیم CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // اضافه کردن پنل‌ها به CardLayout
        cardPanel.add(createGuestPanel(), "guestPanel");
        cardPanel.add(createRoomPanel(), "roomPanel");

        // منوی ناوبری
        JPanel navigationPanel = createNavigationPanel();

        // پنل ناوبری و کارت‌ها را به فریم اضافه می‌کنیم
        add(navigationPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

    // ایجاد پنل ناوبری برای تغییر بین کارت‌ها
    private JPanel createNavigationPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JButton guestButton = new JButton("مدیریت مهمان");
        JButton roomButton = new JButton("مدیریت اتاق");

        guestButton.addActionListener(e -> cardLayout.show(cardPanel, "guestPanel"));
        roomButton.addActionListener(e -> cardLayout.show(cardPanel, "roomPanel"));
       
        panel.add(guestButton);
        panel.add(roomButton);
       

        return panel;
    }

    private JPanel createGuestPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("📘 ثبت و نمایش مهمان‌ها"));

        // فرم ورود اطلاعات
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        // فیلدهای ورودی
        JTextField name = new JTextField();
        JTextField melli = new JTextField();
        JTextField phone = new JTextField();
        JTextField room = new JTextField();

        // استایل فیلدها
        Dimension fieldSize = new Dimension(200, 30);
        Font font = new Font("SansSerif", Font.PLAIN, 14);

        JTextField[] fields = {name, melli, phone, room};
        for (JTextField f : fields) {
            f.setPreferredSize(fieldSize);
            f.setFont(font);
        }

        // افزودن فیلدها به فرم
        formPanel.add(new JLabel("👤 نام کامل:"));
        formPanel.add(name);
        formPanel.add(new JLabel("🆔 کد ملی:"));
        formPanel.add(melli);
        formPanel.add(new JLabel("📞 تلفن:"));
        formPanel.add(phone);
        formPanel.add(new JLabel("🚪 شماره اتاق:"));
        formPanel.add(room);

        // دکمه‌ها
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        JButton addGuest = new JButton("➕ ثبت مهمان");
        JButton listGuests = new JButton("📋 لیست مهمان‌ها");
        buttonPanel.add(addGuest);;
        buttonPanel.add(listGuests);

        // خروجی
        JTextArea output = new JTextArea(10, 30);
        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 13));
        output.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setBorder(BorderFactory.createTitledBorder("📄 لیست مهمان‌ها"));

        // رویداد ثبت
        addGuest.addActionListener(e -> {
            try {
                Guests g = new Guests();
                g.setFullName(name.getText().trim());
                g.setMellicode(Integer.parseInt(melli.getText().trim()));
                g.setPhoneNumber(phone.getText().trim());
                g.setRoomNumber(Integer.parseInt(room.getText().trim()));

                guestsManager.insert(g);
                JOptionPane.showMessageDialog(panel, "✅ مهمان با موفقیت ثبت شد");

                // پاکسازی فیلدها
                name.setText(""); melli.setText(""); phone.setText(""); room.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "⚠ لطفاً اطلاعات را صحیح وارد کنید", "خطا", JOptionPane.ERROR_MESSAGE);
            }
        });

        // رویداد لیست مهمان‌ها
        listGuests.addActionListener(e -> {
            output.setText("");
            String[] data = guestsManager.fn.getArrayFromFile();
            for (String row : data) {
                output.append(row + "\n");
            }
        });

        // چیدمان نهایی
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createRoomPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createTitledBorder("🏨 مدیریت اتاق‌ها"));

        // فرم اطلاعات اتاق
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        // فیلدها
        JTextField roomNumber = new JTextField();
        JTextField bed = new JTextField();
        JTextField guestName = new JTextField();
        JComboBox<String> statusBox = new JComboBox<>(new String[]{"Available", "Occupied"});

        // تنظیم اندازه و فونت
        Dimension fieldSize = new Dimension(200, 30);
        Font font = new Font("SansSerif", Font.PLAIN, 14);

        JTextField[] fields = {roomNumber, bed, guestName};
        for (JTextField field : fields) {
            field.setPreferredSize(fieldSize);
            field.setFont(font);
        }
        statusBox.setPreferredSize(fieldSize);
        statusBox.setFont(font);

        // اضافه کردن به فرم
        formPanel.add(new JLabel("🔢 شماره اتاق:"));
        formPanel.add(roomNumber);
        formPanel.add(new JLabel("🛏️ تعداد تخت:"));
        formPanel.add(bed);
        formPanel.add(new JLabel("👤 نام مهمان:"));
        formPanel.add(guestName);
        formPanel.add(new JLabel("📌 وضعیت:"));
        formPanel.add(statusBox);

        // دکمه‌ها
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton addRoom = new JButton("➕ ثبت اتاق");
        JButton showRooms = new JButton("📋 نمایش اتاق‌ها");
        buttonPanel.add(addRoom);
        buttonPanel.add(showRooms);

        // خروجی
        JTextArea output = new JTextArea(10, 30);
        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 13));
        output.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setBorder(BorderFactory.createTitledBorder("📄 وضعیت اتاق‌ها"));

        // رویداد ثبت اتاق
        addRoom.addActionListener(e -> {
            try {
                Room1 r = new Room1();
                r.setRoomNumber(Integer.parseInt(roomNumber.getText().trim()));
                r.setBed(bed.getText().trim());
                r.setGuestsName(guestName.getText().trim());
                r.setStatus((String) statusBox.getSelectedItem());

                roomManager.saveOrUpdateRoom(r);
                JOptionPane.showMessageDialog(mainPanel, "✅ اتاق ثبت شد");

                // پاک‌سازی
                roomNumber.setText("");
                bed.setText("");
                guestName.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainPanel, "❗ لطفاً شماره اتاق را به درستی وارد کنید");
            }
        });

        // رویداد نمایش اتاق‌ها
        showRooms.addActionListener(e -> {
            output.setText(roomManager.showAllRoomsStatus());
        });

        // اضافه کردن بخش‌ها به پنل اصلی
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
