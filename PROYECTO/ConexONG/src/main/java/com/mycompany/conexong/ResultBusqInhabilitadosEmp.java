/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.conexong;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mycompany.conexong.BusquedaindividualEmpleado.dato;
import static com.mycompany.conexong.BusquedaindividualEmpleado.validarDNI;
import static com.mycompany.conexong.BusquedaindividualEmpleado.validarNombre;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.bson.Document;

/**
 *
 * @author USUARIO
 */
public class ResultBusqInhabilitadosEmp extends javax.swing.JFrame {
    private DefaultTableModel model;
    
    public String Item2="";
    
    public int pnum;
    public String pdni;
    public String pnombre4;
    public String papellido4;
    public int pedad;
    public String pcargo;
    public String ptelefono;
    public String pcalle;
    public String pdistrito;
    public String pprovincia;
    public String pfechaini;
    public String pfechafin;


    //private JTable table;

    /**
     * Creates new form ResultBusqIndividual
     */
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    
    public ResultBusqInhabilitadosEmp() {
        initComponents();
        this.setTitle("BASE DE DATOS ONG HILO ROJO");
        connectToMongoDB();
        this.setLocationRelativeTo(null);
        
        model = new DefaultTableModel();
        JTable.setModel(model);
        
        model.setColumnIdentifiers(new Object[]{"NUM","DNI", "Nombres", "Apellidos","Edad","Cargo","Telefono",
        "Calle", "Distrito","Provincia", "FechaInicio", "FechaSalida", });
        
        Document query = new Document("elimlogica", new Document("$eq", 0));
        FindIterable<Document> results = collection.find(query);
             
            int count = 0;
            for (Document doc : results) {          
                
            pnum = count + 1;         
            pdni = doc.getString("dniEmp");
            pnombre4 = doc.getString("nombreEm");
            papellido4= doc.getString("apellidoEm");
            pedad = doc.getInteger("edadEm");
            pcargo = doc.getString("cargoEm");
            ptelefono = doc.getString("telefEm");
            pcalle = doc.getString("calleEm");
            pdistrito = doc.getString("distritoEm");
            pprovincia = doc.getString("provinciaEm");
            pfechaini = doc.getString("fechaIniEm");
            pfechafin = doc.getString("fechaSaliEm"); 
                 
           model.addRow(new Object[] {pnum, pdni, pnombre4, papellido4, pedad,pcargo , ptelefono,pcalle , pdistrito,pprovincia,pfechaini,pfechafin});

                 count++;
         } 
             jLabel2.setText(String.valueOf(count));
             
             
        // Ajustar el ancho de las columnas según el texto en las filas
        TableColumnModel columnModel = JTable.getColumnModel();
        for (int column = 0; column < JTable.getColumnCount(); column++) {
            TableColumn tableColumn = columnModel.getColumn(column);
            int preferredWidth = 0;
            int maxTextWidth = 100; // Ancho máximo permitido para una celda
            for (int row = 0; row < JTable.getRowCount(); row++) {
                TableCellRenderer cellRenderer = JTable.getCellRenderer(row, column);
                Component c = JTable.prepareRenderer(cellRenderer, row, column);
                int cellWidth = c.getPreferredSize().width + JTable.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, cellWidth);
                // Limitar el ancho máximo de la columna
                preferredWidth = Math.min(preferredWidth, maxTextWidth);
            }
            tableColumn.setPreferredWidth(preferredWidth);
        } 
    }
    private void connectToMongoDB() {
             try {
            MongoClientURI uri = new MongoClientURI("mongodb+srv://ONG:12345@ong.nskhjqm.mongodb.net/?retryWrites=true&w=majority");
            mongoClient = new MongoClient(uri);
            database = mongoClient.getDatabase("OngHiloRojo");
            collection = database.getCollection("Empleado");
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    /*private ResultBusqInhabilitadosEmp() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTable = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        BUSCAR = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 0, 0));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("BASE DE DATOS DE LA ONG HILO ROJO - Resultados de la Búsqueda Inhabilitados");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel13)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        JTable.setBackground(new java.awt.Color(255, 255, 204));
        JTable.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(JTable);

        jButton2.setBackground(new java.awt.Color(255, 153, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Habilitar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));

        jComboBox1.setBackground(new java.awt.Color(255, 153, 51));
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CRITERIOS", "DNI", "NOMBRES", "APELLIDOS" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        BUSCAR.setBackground(new java.awt.Color(255, 153, 51));
        BUSCAR.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        BUSCAR.setText("Buscar");
        BUSCAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUSCARActionPerformed(evt);
            }
        });

        JTable1.setBackground(new java.awt.Color(255, 255, 204));
        JTable1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        JTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(JTable1);

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/atras.png"))); // NOI18N
        jButton3.setText("ATRÁS");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(BUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(327, 327, 327)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int elimlog = 1;
        Document filtro = new Document("dniEmp", pdni);        
        
        Document nuevosValores = new Document("$set", new Document("dniEmp", pdni)
                                            .append("elimlogica", elimlog)                                                                                      
        );
        collection.updateOne(filtro, nuevosValores);       

        JOptionPane.showMessageDialog(null,"¡Se habilitó el registro del empleado correctamente!");
        BusquedaAvanzadaEmpleado c = new BusquedaAvanzadaEmpleado();
        c.setVisible(true);
        this.setVisible(false);
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        Item2 = jComboBox1.getSelectedItem().toString();
    }//GEN-LAST:event_jComboBox1ActionPerformed
    public static boolean validarDNI(String dni) {
        // Verificar que el DNI tenga 8 dígitos
        if (dni.length() != 8) {
            return false;
        }

        // Verificar que todos los caracteres sean dígitos
        for (int i = 0; i < dni.length(); i++) {
            if (!Character.isDigit(dni.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean validarCarnetExtranjeria(String codigo) {
        // Verificar que el código tenga 9 caracteres
        if (codigo.length() != 9) {
            return false;
        }
        
        // Verificar que los primeros dos caracteres sean "00"
        if (!codigo.startsWith("00")) {
            return false;
        }
        
        // Verificar que los últimos 7 caracteres sean dígitos
        for (int i = 2; i < 9; i++) {
            if (!Character.isDigit(codigo.charAt(i))) {
                return false;
            }
        }
        
        // El código cumple con los criterios de validación
        return true;
    }
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void BUSCARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUSCARActionPerformed
        int count = 0;
        // TODO add your handling code here:
        if(Item2 == "DNI"){
            String D=dato.getText();
            System.out.println(D);

            int num = 1;
            if (validarDNI(D) != false)
            {  
                //Document result = collection.find(query).first();
                
                Document query = new Document("dniEmp", D);
                Document result = collection.find(query).first();
                int elimina = result.getInteger("elimlogica");

                if (result != null && elimina==0)
                {
                    // Usuario válido, iniciar sesión
                    System.out.println("Búsqueda exitosa");
                    JOptionPane.showMessageDialog(null,"¡Búsqueda exitosa!");
                    
                    int pnum = count + 1;            
                    String pdni = result.getString("dniEmp");   
                    String pnombre4 = result.getString("nombreEm");
                    String papellido4 = result.getString("apellidoEm");
                    int pedad = result.getInteger("edadEm");
                    String pcargo = result.getString("cargoEm");
                    String ptelefono = result.getString("telefEm");
                    String pcalle = result.getString("calleEm");
                    String pdistrito = result.getString("distritoEm");
                    String pprovincia = result.getString("provinciaEm");
                    String pfechaini = result.getString("fechaIniEm");
                    String pfechafin = result.getString("fechaSaliEm");
                    
                    /*ResultBusqIndividualVoluntario nuevo = new ResultBusqIndividualVoluntario(carnetExt_dniV,nombrev, apellidov,edadv, telefonov,nacionalidadV,cursov,fechaIniV,fechaSaliV);
                    nuevo.setVisible(true);
                    this.setVisible(false);*/
                    
                    model.addRow(new Object[] {pnum, pdni, pnombre4, papellido4, pedad,pcargo , ptelefono,pcalle , pdistrito,pprovincia,pfechaini,pfechafin});
                    
                    // Ajustar el ancho de las columnas según el texto en las filas
                    TableColumnModel columnModel = JTable1.getColumnModel();
                    for (int column = 0; column < JTable1.getColumnCount(); column++) {
                        TableColumn tableColumn = columnModel.getColumn(column);
                        int preferredWidth = 0;
                        int maxTextWidth = 100; // Ancho máximo permitido para una celda
                        for (int row = 0; row < JTable1.getRowCount(); row++) {
                            TableCellRenderer cellRenderer = JTable1.getCellRenderer(row, column);
                            Component c = JTable1.prepareRenderer(cellRenderer, row, column);
                            int cellWidth = c.getPreferredSize().width + JTable1.getIntercellSpacing().width;
                            preferredWidth = Math.max(preferredWidth, cellWidth);
                            // Limitar el ancho máximo de la columna
                            preferredWidth = Math.min(preferredWidth, maxTextWidth);
                        }
                        tableColumn.setPreferredWidth(preferredWidth);
                    } 
                                                       
                    
                } else {
                    // Usuario no válido, mostrar mensaje de error
                    System.out.println("No existe empleado con dicho DNI");
                    JOptionPane.showMessageDialog(null,"No existe empleado con dicho DNI o ya ha sido eliminado");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"¡Ingrese un DNI correcto (8 digitos y números)");
            }
        }
        if("NOMBRES".equals(Item2)){
            String D=dato.getText();
            System.out.println(D);            

            if (validarNombre(D) != false)
            {  
                //Document result = collection.find(query).first();
                Document query = new Document("nombreEm", D);
                Document result = collection.find(query).first();  
                int elimina = result.getInteger("elimlogica");

                if (result != null&& elimina==0)
                {
                    // Usuario válido, iniciar sesión
                    System.out.println("Búsqueda exitosa");
                    JOptionPane.showMessageDialog(null,"¡Búsqueda exitosa!");                    
                    
                    int pnum = count + 1;                    
                    String pdni = result.getString("dniEmp");   
                    String pnombre4 = result.getString("nombreEm");
                    String papellido4 = result.getString("apellidoEm");
                    int pedad = result.getInteger("edadEm");
                    String pcargo = result.getString("cargoEm");
                    String ptelefono = result.getString("telefEm");
                    String pcalle = result.getString("calleEm");
                    String pdistrito = result.getString("distritoEm");
                    String pprovincia = result.getString("provinciaEm");
                    String pfechaini = result.getString("fechaIniEm");
                    String pfechafin = result.getString("fechaSaliEm");
                    
                    model.addRow(new Object[] {pnum, pdni, pnombre4, papellido4, pedad,pcargo , ptelefono,pcalle , pdistrito,pprovincia,pfechaini,pfechafin});
                    
                    // Ajustar el ancho de las columnas según el texto en las filas
                    TableColumnModel columnModel = JTable1.getColumnModel();
                    for (int column = 0; column < JTable1.getColumnCount(); column++) {
                        TableColumn tableColumn = columnModel.getColumn(column);
                        int preferredWidth = 0;
                        int maxTextWidth = 100; // Ancho máximo permitido para una celda
                        for (int row = 0; row < JTable1.getRowCount(); row++) {
                            TableCellRenderer cellRenderer = JTable1.getCellRenderer(row, column);
                            Component c = JTable1.prepareRenderer(cellRenderer, row, column);
                            int cellWidth = c.getPreferredSize().width + JTable1.getIntercellSpacing().width;
                            preferredWidth = Math.max(preferredWidth, cellWidth);
                            // Limitar el ancho máximo de la columna
                            preferredWidth = Math.min(preferredWidth, maxTextWidth);
                        }
                        tableColumn.setPreferredWidth(preferredWidth);
                    } 
                    

                    /*ResultBusqIndividualVoluntario nuevo = new ResultBusqIndividualVoluntario(carnetExt_dniV,nombrev, apellidov,edadv, telefonov,nacionalidadV,cursov,fechaIniV,fechaSaliV);
                    nuevo.setVisible(true);
                    this.setVisible(false);*/
                } else {
                   // Usuario no válido, mostrar mensaje de error
                    System.out.println("No existe empleado con dicho NOMBRE");
                    JOptionPane.showMessageDialog(null,"No existe empleado con dicho NOMBRE");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"¡Ingrese un NOMBRE correcto (Solo letras)");
            }
        
        
        }
    }//GEN-LAST:event_BUSCARActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        BusquedaAvanzadaEmpleado c = new BusquedaAvanzadaEmpleado();
        c.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param result
     */
    
      
    
    public static void main(String args[]) {
          
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ResultBusqInhabilitadosEmp().setVisible(true);
        });
        
        
       /* public void obtenvalores (int D, Document result){
        int numero = D;
        Document resultA = result;
        imprimirtabla( numero, resultA);
    }
    
    public void imprimirtabla(int D, Document result)
    {
        if (D==1)
        {
        System.out.println("Hola"+D);
        String nombre = result.getString("nombreEst");
        String apellido = result.getString("apellidoEst");
        String edad = result.getString("calleEst");
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("calleEst: " + edad);
        jLabel1.setText(nombre);
        DefaultTableModel model = new DefaultTableModel();           
        //frame.add(new JScrollPane(table), BorderLayout.CENTER);
        // Crear un modelo de tabla para mostrar los datos
        // Cambiar los nombres de las columnas
        /*table.getColumnModel().getColumn(0).setHeaderValue("Código");
        table.getColumnModel().getColumn(1).setHeaderValue("Nombres");
        table.getColumnModel().getColumn(2).setHeaderValue("Apellidos");
        table.getColumnModel().getColumn(2).setHeaderValue("Telefono");
        table.getColumnModel().getColumn(2).setHeaderValue("Calle");
        table.getColumnModel().getColumn(2).setHeaderValue("Distrito");
        table.getTableHeader().repaint(); 
        model.addColumn("Código");
        model.addColumn("Nombres");
        model.addColumn("Apellidos");
        model.addColumn("Telefono");
        model.addColumn("Calle");
        model.addColumn("Distrito");
        model.addColumn("Provincia");
        model.addColumn("DNI");
        model.addColumn("Grado");
        model.addColumn("Nivel");
        model.addColumn("Edad");
        this.JTable.setModel(model);*/
        //JTable table = new JTable(model);
        

        // Recorrer los resultados y agregar los datos al modelo de tabla
        /*Document document : result;
        {
        String codigo = result.getString("Id_Est");
        String nombre4 = result.getString("nombreEst");
        String apellido4 = result.getString("apellidoEst");
        String telefono = result.getString("telefEst");
        String calle = result.getString("calleEst");
        String distrito = result.getString("distritoEst");
        String provincia = result.getString("provinciaEst");
        String dni = result.getString("dniEst");
        int grado = result.getInteger("GradoAcade");
        String nivel = result.getString("NivelAcad");
        int edad4 = result.getInteger("edad");                                 
        model.addRow(new Object[]{codigo,nombre4, apellido4, telefono,calle,distrito,provincia,dni,grado,nivel,edad4});                        
        }*/
            // Establecer el modelo de tabla en la tabla de la interfaz gráfica
        /*table.setModel(model);*/
       
        
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton BUSCAR;
    public javax.swing.JTable JTable;
    public javax.swing.JTable JTable1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
