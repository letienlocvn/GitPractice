/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loclt.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import loclt.armor.ArmorDTO;
import loclt.armorInterface.ArmorInterface;

/**
 *
 * @author WIN
 */
public class ArmorClient extends javax.swing.JFrame {

    private ArmorInterface armorInterface;
    private Registry registry;
    ArmorDTO dto;
    boolean find = false;

    public ArmorClient() {
        initComponents();
        txtTimeOfCreate.setText("The current time is automatically filled");
        txtTimeOfCreate.setEditable(false);
        btnRemove.setEnabled(false);
        btnUpdate.setEnabled(false);
        this.tblArmor.setDefaultEditor(Object.class, null);
        tblArmor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        try {
            String rmi = "rmi://localhost:1098/ArmorData";
            registry = LocateRegistry.getRegistry(1098);
            armorInterface = (ArmorInterface) registry.lookup(rmi);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Can't create stub");
        } catch (NotBoundException ex) {
            JOptionPane.showMessageDialog(this, "Can't create Stub");
        }
    }

    private void addNew() {
        btnCreate.setEnabled(true);
        btnRemove.setEnabled(false);
        btnUpdate.setEnabled(false);
        txtArmorID.setText("");
        txtClassfication.setText("");
        txtDefense.setText("");
        txtStatus.setText("");
        txtTimeOfCreate.setText("The current time is automatically filled");
        txtArmorID.setEditable(true);
        lbArmorID.setText("");
        lbClassfi.setText("");
        lbDefense.setText("");
        lbStatus.setText("");
        textArea.setText("");
        lbTimeOfCreate.setText("");
    }

    private void setupModel(List<ArmorDTO> list) {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("Classfication");
        defaultTableModel.addColumn("Time Of Create");
        defaultTableModel.addColumn("Defense");
        if (list.size() > 0) {
            for (ArmorDTO armorDTO : list) {
                defaultTableModel.addRow(armorDTO.toArray());
            }
        }
        tblArmor.setModel(defaultTableModel);
    }

    private void checkConnection() {
        try {
            String rmi = "rmi://localhost:1098/ArmorData";
            if (rmi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cannot find IP");
            } else {
                armorInterface = (ArmorInterface) registry.lookup(rmi);
            }
        } catch (NotBoundException | RemoteException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Server is stopped");
        }
    }

    public boolean checkID() {
        String id = txtArmorID.getText();
        try {
            List<ArmorDTO> list = armorInterface.findArmor();
            for (ArmorDTO armorDTO : list) {
                if (armorDTO.getArmorID().trim().equals(id.trim())) {
                    return true;
                }
            }
        } catch (RemoteException e) {
        }
        return false;
    }

    public boolean validID() {
        String id = txtArmorID.getText();
        if (id.length() <= 10 && id.length() > 0 && id.matches("\\w+")) {
            return true;
        }
        return false;
    }

    public boolean validClass() {
        String Classfi = txtClassfication.getText().trim();
        if (Classfi.length() <= 30 && Classfi.length() > 0) {
            return true;
        }
        return false;
    }

    public boolean validDescription() {
        String Description = textArea.getText().trim();
        return Description.length() <= 300 && Description.length() > 0;
    }

    public boolean validDefense() {
        String Defense = txtDefense.getText();
        if (Defense.length() < 1) {
            return false;
        }
        try {
            int def = Integer.parseInt(Defense);
            if (def > 0) {
                return true;
            }
        } catch (NumberFormatException e) {
        }
        return false;
    }

    public boolean validStatus() {
        String status = txtStatus.getText().trim();
        if (status.length() > 0) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblArmor = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        txtArmorID = new javax.swing.JTextField();
        txtClassfication = new javax.swing.JTextField();
        txtTimeOfCreate = new javax.swing.JTextField();
        txtDefense = new javax.swing.JTextField();
        btnFindArmor = new javax.swing.JButton();
        lbDefense = new javax.swing.JLabel();
        lbTimeOfCreate = new javax.swing.JLabel();
        lbArmorID = new javax.swing.JLabel();
        lbClassfi = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        lbStatus = new javax.swing.JLabel();
        lbDescript = new javax.swing.JLabel();
        btnGetAll = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblArmor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblArmor.setColumnSelectionAllowed(true);
        tblArmor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblArmorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblArmor);
        tblArmor.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jLabel1.setText("ArmorID");

        jLabel2.setText("Classfication");

        jLabel3.setText("TimeOfCreate");

        jLabel4.setText("Defense");

        jLabel5.setText("Description");

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        btnFindArmor.setText("Find");
        btnFindArmor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindArmorActionPerformed(evt);
            }
        });

        lbDefense.setForeground(new java.awt.Color(255, 0, 51));

        lbTimeOfCreate.setForeground(new java.awt.Color(255, 0, 51));

        lbArmorID.setForeground(new java.awt.Color(255, 0, 51));

        lbClassfi.setForeground(new java.awt.Color(255, 0, 51));

        jLabel10.setText("Status");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        lbStatus.setForeground(new java.awt.Color(255, 0, 51));

        lbDescript.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbClassfi)
                    .addComponent(lbArmorID)
                    .addComponent(lbTimeOfCreate)
                    .addComponent(lbDefense)
                    .addComponent(lbDescript)
                    .addComponent(lbStatus)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(35, 35, 35))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel10))
                                .addGap(49, 49, 49)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(txtArmorID)
                            .addComponent(txtClassfication)
                            .addComponent(txtTimeOfCreate)
                            .addComponent(txtDefense)
                            .addComponent(txtStatus)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCreate)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemove)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFindArmor)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtArmorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnFindArmor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbArmorID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtClassfication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbClassfi)
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTimeOfCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTimeOfCreate)
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDefense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDefense)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(lbDescript)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addComponent(jLabel5))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnUpdate)
                    .addComponent(btnRemove))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbStatus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGetAll.setText("Get All");
        btnGetAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 220, Short.MAX_VALUE)
                        .addComponent(btnGetAll)
                        .addGap(195, 195, 195)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGetAll)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (checkID()) {
            lbArmorID.setText("ID is existed");
            return;
        }
        if (!validID()) {
            lbArmorID.setText("max length is 10, not contains special characters");
            return;
        }
        if (!validClass()) {
            lbArmorID.setText("");
            lbClassfi.setText("Max length is 30");
            return;
        }
        if (!validDefense()) {
            lbArmorID.setText("");
            lbClassfi.setText("");
            lbDefense.setText("Defense > 0");
            return;
        }
        if (!validDescription()) {
            lbArmorID.setText("");
            lbClassfi.setText("");
            lbDefense.setText("");
            lbDescript.setText("Max length is 300");
            return;
        }
        if (!validStatus()) {
            lbArmorID.setText("");
            lbClassfi.setText("");
            lbDefense.setText("");
            lbDescript.setText("");
            lbStatus.setText("Not allow empty");
            return;
        }
        String armorID = txtArmorID.getText();
        String classfication = txtClassfication.getText();
        String description = textArea.getText();
        String status = txtStatus.getText();
        Date time = Calendar.getInstance().getTime();
        String defense = txtDefense.getText();
        ArmorDTO armorDTO = new ArmorDTO(armorID, classfication, description, status, time, Integer.parseInt(defense));
        checkConnection();

        try {
            if (armorInterface.createArmor(armorDTO)) {
                List<ArmorDTO> list = armorInterface.findArmor();
                setupModel(list);
                addNew();
                JOptionPane.showMessageDialog(this, "Create Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Create failed");
            }
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (!validClass()) {
            lbArmorID.setText("");
            lbClassfi.setText("Max length is 30");
            return;
        }
        if (!validDefense()) {
            lbArmorID.setText("");
            lbClassfi.setText("");
            lbDefense.setText("Defense > 0");
            return;
        }
        if (!validDescription()) {
            lbArmorID.setText("");
            lbClassfi.setText("");
            lbDefense.setText("");
            lbDescript.setText("Max length is 300");
            return;
        }
        if (!validStatus()) {
            lbArmorID.setText("");
            lbClassfi.setText("");
            lbDefense.setText("");
            lbDescript.setText("");
            lbStatus.setText("Not allow empty");
            return;
        }
        checkConnection();
        String armorID = txtArmorID.getText();
        String classfication = txtClassfication.getText();
        String defense = txtDefense.getText();
        String status = txtStatus.getText();
        String description = textArea.getText();
        Date time = Calendar.getInstance().getTime();
        ArmorDTO armorDTO = new ArmorDTO(armorID, classfication, description, status, time, Integer.parseInt(defense));
        try {
            String rmi = "rmi://localhost:1098/ArmorData";
            armorInterface = (ArmorInterface) registry.lookup(rmi);
            boolean result = armorInterface.updateArmor(armorDTO);
            if (result == true) {
                find = false; //? 
                List<ArmorDTO> list = armorInterface.findArmor();
                setupModel(list);
                addNew();
                JOptionPane.showMessageDialog(this, "Update Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed");
            }
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        checkConnection();
        int result = JOptionPane.showConfirmDialog(this, "Do you want delete", "Delete?", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_NO_OPTION) {
            try {
                String id = txtArmorID.getText();
                if (armorInterface.removeArmor(id)) {
                    List<ArmorDTO> list = armorInterface.findArmor();
                    setupModel(list);
                    addNew();
                    find = false;
                    JOptionPane.showMessageDialog(this, "Remove successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Remove failed");
                }
            } catch (RemoteException e) {
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnGetAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllActionPerformed
        checkConnection();
        try {
            List<ArmorDTO> list = armorInterface.findArmor();
            setupModel(list);
            btnRemove.setEnabled(false);
            btnCreate.setEnabled(true);
            btnUpdate.setEnabled(false);
            txtArmorID.setText("");
            txtClassfication.setText("");
            txtTimeOfCreate.setText("The current time is automatically filled");
            txtTimeOfCreate.setEditable(false);
            txtDefense.setText("");
            txtStatus.setText("");
            textArea.setText("");
            txtArmorID.setEditable(true);
            find = false;
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnGetAllActionPerformed

    private void setMouseClick(ArmorDTO dto) {
        txtArmorID.setEditable(false);
        btnUpdate.setEnabled(true);
        btnRemove.setEnabled(true);
        btnCreate.setEnabled(false);
        txtArmorID.setText(dto.getArmorID());
        txtClassfication.setText(dto.getClassfication());
        txtDefense.setText("" + dto.getDefense());
        textArea.setText("" + dto.getDescription());
        txtStatus.setText("" + dto.getStatus());
        Date time = dto.getTimeOfCreate();
        String timeOfCreate = new SimpleDateFormat("dd/MM/yyyy").format(time);
        txtTimeOfCreate.setText(timeOfCreate);
        lbArmorID.setText("");
        lbClassfi.setText("");
        lbDefense.setText("");
        lbStatus.setText("");
    }
    private void tblArmorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArmorMouseClicked
        checkConnection();
        int row = tblArmor.getSelectedRow();
        if (find == true) {
            setMouseClick(dto);
        } else {
            try {
                List<ArmorDTO> list = armorInterface.findArmor();
                ArmorDTO armorDTO = list.get(row);
                setMouseClick(armorDTO);
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_tblArmorMouseClicked

    private void btnFindArmorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindArmorActionPerformed
        checkConnection();
        String id = txtArmorID.getText();
        find = true;
        lbClassfi.setText("");
        lbDefense.setText("");
        lbDescript.setText("");
        lbArmorID.setText("");
        lbStatus.setText("");
        btnCreate.setEnabled(false);

        if (id.trim().length() > 0) {
            try {
                dto = armorInterface.findByArmorID(id);
                if (dto != null) {
                    List<ArmorDTO> list = new ArrayList<>();
                    list.add(dto);
                    setupModel(list);
                    btnRemove.setEnabled(true);
                    btnUpdate.setEnabled(true);
                    txtArmorID.setEditable(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Not found");
                    txtArmorID.setText("");
                    txtClassfication.setText("");
                    txtDefense.setText("");
                    txtStatus.setText("");
                    textArea.setText("");
                    find = false;
                }
            } catch (RemoteException e) {
                System.out.println("btnFind button: "+e.getMessage());
            }
        } else {
            btnCreate.setEnabled(true);
            JOptionPane.showMessageDialog(this, "ID can't be blank");
            find = false;
        }

    }//GEN-LAST:event_btnFindArmorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ArmorClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArmorClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArmorClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArmorClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArmorClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnFindArmor;
    private javax.swing.JButton btnGetAll;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbArmorID;
    private javax.swing.JLabel lbClassfi;
    private javax.swing.JLabel lbDefense;
    private javax.swing.JLabel lbDescript;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTimeOfCreate;
    private javax.swing.JTable tblArmor;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextField txtArmorID;
    private javax.swing.JTextField txtClassfication;
    private javax.swing.JTextField txtDefense;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTimeOfCreate;
    // End of variables declaration//GEN-END:variables
}
