package Main;

import javax.swing.*;
import java.sql.*;

public class Register extends javax.swing.JFrame {
    private static final String MYSQL_JDBC_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_DB_URL = "jdbc:mysql://localhost:3306/my_uat";
    private static final String MYSQL_DB_USER = "root";
    private static final String MYSQL_DB_USER_PASSWORD = "";

    public Register() {
        initComponents();
    }

    private void initComponents() {

        usernameReg = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Register = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        passwordReg = new javax.swing.JPasswordField();
        Cpassword = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Password");

        Register.setBackground(new java.awt.Color(255, 48, 0));
        Register.setForeground(new java.awt.Color(255, 255, 255));
        Register.setText("Register");
        Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterActionPerformed(evt);
            }
        });

        jLabel5.setText("Already have an account?");

        jButton2.setForeground(new java.awt.Color(255, 48, 0));
        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Confirm Password");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 48, 0));
        jLabel1.setText("Register");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Register your account");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Username");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Cpassword, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(passwordReg, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(usernameReg, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Register, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addComponent(Cpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Register)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        Login lg = new Login();
        lg.setVisible(true);
        lg.pack();
        lg.setLocationRelativeTo(null);
        this.dispose();
    }

    private void RegisterActionPerformed(java.awt.event.ActionEvent evt) {
        String nama, password, query;

        try {
            Class.forName(MYSQL_JDBC_DRIVER_CLASS);
            Connection con = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_DB_USER, MYSQL_DB_USER_PASSWORD);
            Statement st = con.createStatement();
            String user = usernameReg.getText();
            ResultSet rs1 = st.executeQuery("SELECT nama FROM data_user");
            while (rs1.next()) {
                if (user.equals(rs1.getString(1))) {
                    JOptionPane.showMessageDialog(new JFrame(), "Username already registered", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if ("".equals(usernameReg.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Username cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(passwordReg.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(Cpassword.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Confirm Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!passwordReg.getText().equals(Cpassword.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Password and Confirm Password is not same", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                nama = usernameReg.getText();
                password = passwordReg.getText();
                query = "INSERT INTO data_user(nama, password)" + " VALUES ('" + nama + "', '" + password + "')";
                st.execute(query);
                usernameReg.setText("");
                passwordReg.setText("");
                Cpassword.setText("");
                JOptionPane.showMessageDialog(new JFrame(), "Register Success", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }

    }

    private javax.swing.JPasswordField Cpassword;
    private javax.swing.JButton Register;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField passwordReg;
    private javax.swing.JTextField usernameReg;
}
