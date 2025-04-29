import common.*;
import manager.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Main extends JFrame {
    private InventoryManager inventoryManager = new InventoryManager();
    private EmployeeManagementSystem empManager = new EmployeeManagementSystem();
    private TrainingSystem trainingSystem = new TrainingSystem();

    public Main() {
        setTitle("سیستم جامع مدیریت هتل");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // تب‌های اصلی
        JTabbedPane mainTabs = new JTabbedPane(JTabbedPane.RIGHT);

        // تب مدیریت انبار
        mainTabs.addTab("مدیریت انبار", createInventoryTab());

        // تب مدیریت کارمندان
        mainTabs.addTab("مدیریت کارمندان", createEmployeeTab());

        // تب سیستم آموزشی
        mainTabs.addTab("سیستم آموزشی", createTrainingTab());

        add(mainTabs);
    }

    private JPanel createInventoryTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // جدول محصولات
        String[] columns = {"نام کالا", "ویژگی‌ها", "مدت موجودی (ماه)", "تعداد"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        updateInventoryTable(model);

        // پنل دکمه‌ها
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JButton addBtn = new JButton("افزودن کالا");
        addBtn.addActionListener(e -> showAddProductDialog(model));

        JButton updateBtn = new JButton("به‌روزرسانی تعداد");
        updateBtn.addActionListener(e -> showUpdateQuantityDialog(table, model));

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void updateInventoryTable(DefaultTableModel model) {
        model.setRowCount(0);
        for (Product p : inventoryManager.getAllProducts()) {
            model.addRow(new Object[]{
                    p.getName(),
                    p.getFeatures(),
                    p.getMonthsInStock(),
                    p.getQuantity()
            });
        }
    }

    private void showAddProductDialog(DefaultTableModel model) {
        JDialog dialog = new JDialog(this, "ثبت کالای جدید", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField featuresField = new JTextField();
        JSpinner monthsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 36, 1));
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

        dialog.add(new JLabel("نام کالا:"));
        dialog.add(nameField);
        dialog.add(new JLabel("ویژگی‌ها:"));
        dialog.add(featuresField);
        dialog.add(new JLabel("مدت موجودی (ماه):"));
        dialog.add(monthsSpinner);
        dialog.add(new JLabel("تعداد:"));
        dialog.add(quantitySpinner);

        JButton saveBtn = new JButton("ذخیره");
        saveBtn.addActionListener(e -> {
            inventoryManager.addProduct(
                    nameField.getText(),
                    featuresField.getText(),
                    (int)monthsSpinner.getValue(),
                    (int)quantitySpinner.getValue()
            );
            updateInventoryTable(model);
            dialog.dispose();
        });

        dialog.add(new JLabel());
        dialog.add(saveBtn);
        dialog.setVisible(true);
    }

    private void showUpdateQuantityDialog(JTable table, DefaultTableModel model) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "لطفاً یک کالا را انتخاب کنید", "خطا", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String productName = (String)model.getValueAt(row, 0);
        int currentQty = (int)model.getValueAt(row, 3);

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(currentQty, 0, 1000, 1));
        int result = JOptionPane.showConfirmDialog(
                this,
                spinner,
                "به‌روزرسانی تعداد برای " + productName,
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            int newQty = (int)spinner.getValue();
            inventoryManager.updateProductQuantity(productName, newQty);
            updateInventoryTable(model);
        }
    }

    private JPanel createEmployeeTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // جدول کارمندان
        String[] columns = {"نام", "نام خانوادگی", "کد ملی", "سابقه کار", "سمت"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        updateEmployeeTable(model);

        // پنل دکمه‌ها
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JButton addBtn = new JButton("افزودن کارمند");
        addBtn.addActionListener(e -> showAddEmployeeDialog(model));

        JButton searchExpBtn = new JButton("جستجو بر اساس سابقه");
        searchExpBtn.addActionListener(e -> showSearchByExperienceDialog(model));

        buttonPanel.add(addBtn);
        buttonPanel.add(searchExpBtn);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void updateEmployeeTable(DefaultTableModel model) {
        model.setRowCount(0);
        for (Employee emp : empManager.getAllEmployees()) {
            model.addRow(new Object[]{
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getNationalId(),
                    emp.getWorkExperience() + " سال",
                    emp.getPosition()
            });
        }
    }

    private void showAddEmployeeDialog(DefaultTableModel model) {
        JDialog dialog = new JDialog(this, "ثبت کارمند جدید", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new GridLayout(8, 2, 10, 10));

        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField nationalIdField = new JTextField();
        JTextField phoneField = new JTextField();
        JSpinner experienceSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        JTextField assetsField = new JTextField();
        JTextField positionField = new JTextField();

        dialog.add(new JLabel("نام:"));
        dialog.add(firstNameField);
        dialog.add(new JLabel("نام خانوادگی:"));
        dialog.add(lastNameField);
        dialog.add(new JLabel("کد ملی:"));
        dialog.add(nationalIdField);
        dialog.add(new JLabel("شماره همراه:"));
        dialog.add(phoneField);
        dialog.add(new JLabel("سابقه کار (سال):"));
        dialog.add(experienceSpinner);
        dialog.add(new JLabel("مهارت‌ها:"));
        dialog.add(assetsField);
        dialog.add(new JLabel("سمت:"));
        dialog.add(positionField);

        JButton saveBtn = new JButton("ذخیره");
        saveBtn.addActionListener(e -> {
            empManager.addEmployee(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    nationalIdField.getText(),
                    phoneField.getText(),
                    (int)experienceSpinner.getValue(),
                    assetsField.getText(),
                    positionField.getText()
            );
            updateEmployeeTable(model);
            dialog.dispose();
        });

        dialog.add(new JLabel());
        dialog.add(saveBtn);
        dialog.setVisible(true);
    }

    private void showSearchByExperienceDialog(DefaultTableModel model) {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(5, 0, 50, 1));
        int result = JOptionPane.showConfirmDialog(
                this,
                spinner,
                "حداقل سابقه کار (سال)",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            model.setRowCount(0);
            for (Employee emp : empManager.searchByExperience((int)spinner.getValue())) {
                model.addRow(new Object[]{
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getNationalId(),
                        emp.getWorkExperience() + " سال",
                        emp.getPosition()
                });
            }
        }
    }

    private JPanel createTrainingTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // تب‌های داخلی
        JTabbedPane innerTabs = new JTabbedPane();

        // تب نمایش دوره‌ها
        innerTabs.addTab("لیست دوره‌ها", createCoursesPanel());

        // تب ثبت‌نام
        innerTabs.addTab("ثبت‌نام در دوره", createRegistrationPanel());

        // تب تخفیف گروهی
        innerTabs.addTab("تخفیف گروهی", createDiscountPanel());

        panel.add(innerTabs, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCoursesPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < trainingSystem.getCategories().length; i++) {
            listModel.addElement("--- " + trainingSystem.getCategories()[i] + " ---");
            for (String[] course : trainingSystem.getCoursesByCategory(i)) {
                listModel.addElement(course[0] + " (" + Utils.formatPrice(Integer.parseInt(course[1])) + ")");
            }
        }

        JList<String> courseList = new JList<>(listModel);
        panel.add(new JScrollPane(courseList), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createRegistrationPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();

        JComboBox<String> courseCombo = new JComboBox<>();
        for (int i = 0; i < trainingSystem.getCategories().length; i++) {
            for (String[] course : trainingSystem.getCoursesByCategory(i)) {
                courseCombo.addItem(course[0] + " (" + Utils.formatPrice(Integer.parseInt(course[1])) + ")");
            }
        }

        panel.add(new JLabel("نام و نام خانوادگی:"));
        panel.add(nameField);
        panel.add(new JLabel("شماره تماس:"));
        panel.add(phoneField);
        panel.add(new JLabel("دوره آموزشی:"));
        panel.add(courseCombo);

        JButton registerBtn = new JButton("ثبت‌نام");
        registerBtn.addActionListener(e -> {
            if (nameField.getText().isEmpty() || phoneField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "لطفاً تمام فیلدها را پر کنید", "خطا", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int courseId = courseCombo.getSelectedIndex() + 1;
            trainingSystem.registerCourse(nameField.getText(), phoneField.getText(), courseId);

            JOptionPane.showMessageDialog(panel, "ثبت‌نام با موفقیت انجام شد", "پیام", JOptionPane.INFORMATION_MESSAGE);
            nameField.setText("");
            phoneField.setText("");
        });

        panel.add(new JLabel());
        panel.add(registerBtn);

        return panel;
    }

    private JPanel createDiscountPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JSpinner countSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 50, 1));

        JComboBox<String> courseCombo = new JComboBox<>();
        for (int i = 0; i < trainingSystem.getCategories().length; i++) {
            for (String[] course : trainingSystem.getCoursesByCategory(i)) {
                courseCombo.addItem(course[0] + " (" + Utils.formatPrice(Integer.parseInt(course[1])) + ")");
            }
        }

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        panel.add(new JLabel("تعداد افراد:"));
        panel.add(countSpinner);
        panel.add(new JLabel("دوره آموزشی:"));
        panel.add(courseCombo);
        panel.add(new JLabel("نتایج:"));
        panel.add(new JScrollPane(resultArea));

        JButton calculateBtn = new JButton("محاسبه تخفیف");
        calculateBtn.addActionListener(e -> {
            int count = (int)countSpinner.getValue();
            int courseId = courseCombo.getSelectedIndex() + 1;

            String result = trainingSystem.calculateGroupDiscount(count, courseId);
            resultArea.setText(result);
        });

        panel.add(new JLabel());
        panel.add(calculateBtn);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            Main app = new Main();
            app.setVisible(true);
        });
    }
}