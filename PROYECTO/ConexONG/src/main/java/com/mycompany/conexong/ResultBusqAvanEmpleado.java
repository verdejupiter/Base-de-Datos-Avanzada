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
import com.mongodb.client.model.Filters;
import java.awt.Component;
import java.awt.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import static org.bson.BSON.NULL;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author USUARIO
 */
public class ResultBusqAvanEmpleado extends javax.swing.JFrame {
    private DefaultTableModel model;
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

    /**
     *
     * @param empleados
     */
    public ResultBusqAvanEmpleado(String item1, String item2, String year1, String year2) {
        initComponents();
        this.setTitle("BASE DE DATOS ONG HILO ROJO");
        connectToMongoDB();
        this.setLocationRelativeTo(null);
        
        model = new DefaultTableModel();
        JTable.setModel(model);
                
        model.setColumnIdentifiers(new Object[]{"NUM","DNI", "Nombres", "Apellidos","Edad","Cargo","Telefono",
            "Calle", "Distrito","Provincia", "FechaInicio", "FechaSalida", });
        
        Document filtro = new Document();
        
        // Año a buscar
        // Consulta utilizando expresión regular
        String regex1 = ".*" + year1 + "$";
        String regex2 = ".*" + year2 + "$";
        
        if (!"".equals(item1) && item2.equals("") && year1.equals("") && year2.equals("")) //diferente
        {
            filtro.append("distritoEm", item1);
        }
        else if (!"".equals(item2) && item1.equals("") && year1.equals("") && year2.equals(""))
        {
            filtro.append("cargoEm", item2);
        }
        else if (!"".equals(year2) && item1.equals("") && item2.equals("") && year1.equals(""))
        {
            filtro.append("fechaIniEm", new Document("$regex", regex2));
        }
        else if (!"".equals(year1) && item1.equals("") && item2.equals("") && year2.equals(""))
        {
            filtro.append("fechaSaliEm", new Document("$regex", regex1));
        }
        else if (!"".equals(item1) && !"".equals(item2) && !"".equals(year2) && year1.equals(""))
        {
            filtro.append("cargoEm", item2);
            filtro.append("distritoEm", item1);
            filtro.append("fechaIniEm", new Document("$regex", regex2));
        }
        else if (!"".equals(item1) && !"".equals(item2) && !"".equals(year1) && year2.equals(""))
        {
            filtro.append("cargoEm", item2);
            filtro.append("distritoEm", item1);
            filtro.append("fechaSaliEm", new Document("$regex", regex1));
        }
        else if (!"".equals(item1) && !"".equals(item2) && year1.equals("") && year2.equals("")){
            filtro.append("cargoEm", item2);
            filtro.append("distritoEm", item1);
        }
        else
        {
            filtro.append("cargoEm", item2);
            filtro.append("distritoEm", item1);
            filtro.append("fechaIniEm", new Document("$regex", regex2));
            filtro.append("fechaSaliEm", new Document("$regex", regex1));
        }
        
        FindIterable<Document> iterable = collection.find(filtro);
        MongoCursor<Document> cursor = iterable.iterator();
        
        int contador=0;
        while (cursor.hasNext()) {
            Document empleado = cursor.next();
            
            pnum= contador+1;
            pdni = empleado.getString("dniEmp");
            pnombre4 = empleado.getString("nombreEm");
            papellido4= empleado.getString("apellidoEm");
            pedad = empleado.getInteger("edadEm");
            pcargo = empleado.getString("cargoEm");
            ptelefono = empleado.getString("telefEm");
            pcalle = empleado.getString("calleEm");
            pdistrito = empleado.getString("distritoEm");
            pprovincia = empleado.getString("provinciaEm");
            pfechaini = empleado.getString("fechaIniEm");
            pfechafin = empleado.getString("fechaSaliEm");            
            
            model.addRow(new Object[] {pnum, pdni, pnombre4, papellido4, pedad,pcargo , ptelefono,pcalle , pdistrito,pprovincia,pfechaini,pfechafin});
            
            contador++;
        } 

        jLabel2.setText(String.valueOf(contador)); 
        
        
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

    private ResultBusqAvanEmpleado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }    

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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 0, 0));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("BASE DE DATOS DE LA ONG HILO ROJO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel13)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Resultados de la Búsqueda Avanzada Empleado");

        JTable.setBackground(new java.awt.Color(255, 255, 204));
        JTable.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(JTable);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
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
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel3.setText("Total:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addGap(42, 42, 42)
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
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        BusquedaAvanzadaEmpleado acceso = new BusquedaAvanzadaEmpleado();
        acceso.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed
     
    
    public static void main(String args[]) {          
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ResultBusqAvanEmpleado().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable JTable;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
