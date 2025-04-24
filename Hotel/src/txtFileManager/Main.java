package txtFileManager;

import Manager.RoomManager;
import Common.Room1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private static final String ERROR_INVALID_ROOM_NUMBER = "شماره اتاق معتبر نیست!";
    private static final String ERROR_ROOM_NOT_FOUND = "❌ اطلاعاتی برای این اتاق پیدا نشد.";
    private static final String SUCCESS_SAVE_UPDATE = "اتاق با موفقیت ذخیره یا بروزرسانی شد.";
    private static final String SUCCESS_DELETE = "اتاق با موفقیت حذف شد.";

    private RoomManager roomManager;
    private JTextArea textArea;
    private JTextField roomNumberField, statusField, guestsField, bedField;

    public Main() {
        roomManager = new RoomManager();  // ساخت یک شی از RoomManager
        setupUI();
    }

    // راه‌اندازی رابط کاربری
    private void setupUI() {
        setTitle("مدیریت اتاق‌ها");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout تنظیم می‌کنیم
        setLayout(new BorderLayout(10, 10));  // اضافه کردن فاصله بین اجزا

        // ناحیه بالایی برای ورودی‌ها
        JPanel inputPanel = createInputPanel();
        // ناحیه دکمه‌ها
        JPanel buttonPanel = createButtonPanel();
        // ناحیه نمایش اطلاعات
        JScrollPane scrollPane = createScrollPane();

        // اضافه کردن کامپوننت‌ها به فریم
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    // ناحیه ورودی‌های کاربر
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));  // فاصله‌های بیشتری برای فیلدها
        inputPanel.setBorder(BorderFactory.createTitledBorder("ورودی‌های اتاق"));

        inputPanel.add(new JLabel("شماره اتاق:"));
        roomNumberField = new JTextField();
        inputPanel.add(roomNumberField);

        inputPanel.add(new JLabel("وضعیت:"));
        statusField = new JTextField();
        inputPanel.add(statusField);

        inputPanel.add(new JLabel("نام مهمان:"));
        guestsField = new JTextField();
        inputPanel.add(guestsField);

        inputPanel.add(new JLabel("تعداد تخت‌ها:"));
        bedField = new JTextField();
        inputPanel.add(bedField);

        return inputPanel;
    }

    // ناحیه دکمه‌ها
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));  // تنظیم چیدمان افقی برای دکمه‌ها
        buttonPanel.setBackground(new Color(240, 240, 240));  // رنگ پس‌زمینه دکمه‌ها

        JButton saveButton = new JButton("ذخیره یا بروزرسانی اتاق");
        JButton deleteButton = new JButton("حذف اتاق");
        JButton showButton = new JButton("نمایش وضعیت اتاق‌ها");

        // تنظیم اندازه دکمه‌ها
        saveButton.setPreferredSize(new Dimension(120, 40));
        deleteButton.setPreferredSize(new Dimension(120, 40));
        showButton.setPreferredSize(new Dimension(120, 40));

        // تغییر رنگ دکمه‌ها
        saveButton.setBackground(new Color(0, 204, 102));
        deleteButton.setBackground(new Color(255, 69, 0));
        showButton.setBackground(new Color(30, 144, 255));

        saveButton.setForeground(Color.WHITE);
        deleteButton.setForeground(Color.WHITE);
        showButton.setForeground(Color.WHITE);

        saveButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        showButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(showButton);

        addActionListeners(saveButton, deleteButton, showButton);

        return buttonPanel;
    }

    // ناحیه نمایش اطلاعات
    private JScrollPane createScrollPane() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(245, 245, 245));  // رنگ پس‌زمینه متن

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(650, 200));  // افزایش اندازه برای نمایش بهتر
        return scrollPane;
    }

    // اضافه کردن شنوندگان به دکمه‌ها
    private void addActionListeners(JButton saveButton, JButton deleteButton, JButton showButton) {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOrUpdateRoom();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRoom();
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllRoomsStatus();
            }
        });
    }

    // ذخیره یا بروزرسانی اتاق
    private void saveOrUpdateRoom() {
        try {
            int roomNumber = Integer.parseInt(roomNumberField.getText());
            String status = statusField.getText();
            String guestsName = guestsField.getText();
            String bed = bedField.getText();

            Room1 room = new Room1();
            room.setRoomNumber(roomNumber);
            room.setStatus(status);
            room.setGuestsName(guestsName);
            room.setBed(bed);

            roomManager.saveOrUpdateRoom(room);
            textArea.setText(SUCCESS_SAVE_UPDATE);
        } catch (NumberFormatException ex) {
            textArea.setText(ERROR_INVALID_ROOM_NUMBER);
        }
    }

    // حذف اتاق
    private void deleteRoom() {
        try {
            int roomNumber = Integer.parseInt(roomNumberField.getText());
            roomManager.DeleteRoom(roomNumber);
            textArea.setText(SUCCESS_DELETE);
        } catch (NumberFormatException ex) {
            textArea.setText(ERROR_INVALID_ROOM_NUMBER);
        }
    }

    // نمایش وضعیت همه اتاق‌ها
    private void showAllRoomsStatus() {
        String allRoomsStatus = roomManager.showAllRoomsStatus();
        textArea.setText(allRoomsStatus);
    }

    public static void main(String[] args) {
        // اجرای برنامه
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
