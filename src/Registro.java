import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Registro {
    private JPanel cedLabel;
    private JLabel codLabel;
    private JTextField codigo;
    private JTextField cedula;
    private JTextField nombre;
    private JTextField fecha;
    private JComboBox signo;
    private JButton buscarCodBt;
    private JButton buscarNomBt;
    private JButton buscarSigBt;
    private JButton deleteBt;
    private JButton updateBt;
    private JButton insertBt;
    private JButton clearBt;

    static final String DB_URL="jdbc:mysql://localhost/REGISTRO";
    static final String USER="root";
    static final String PASS="root_bas3";
    static final String QUERYCOM="Select *From USUARIOS ;";

    public static String codI;
    public static String nombreI;
    public static String signoI;
    public static String userCod;
    public static String userCed;
    public static String userNom;
    public static String userSig;
    public static String userFec;
    public static String codV;
    public static String nombreV;
    public static String cedulaV;
    public static String fechaV;
    public static String signoV;

    public Registro() {
        buscarCodBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userCod = codigo.getText();
                comprobarCod();
                cedula.setText(cedulaV);
                nombre.setText(nombreV);
                fecha.setText(fechaV);
                signo.setSelectedItem(signoV);
            }
        });
        buscarNomBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userNom = nombre.getText();
                comprobarNom();
                codigo.setText(codV);
                cedula.setText(cedulaV);
                fecha.setText(fechaV);
                signo.setSelectedItem(signoV);
            }
        });
        buscarSigBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userSig = (String) signo.getSelectedItem();
                comprobarSig();
                codigo.setText(codV);
                nombre.setText(nombreV);
                cedula.setText(cedulaV);
                fecha.setText(fechaV);
            }
        });
        clearBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigo.setText("");
                cedula.setText("");
                nombre.setText("");
                fecha.setText("");
                signo.setSelectedItem("Seleccionar");
            }
        });
        insertBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userCod = codigo.getText();
                userCed = cedula.getText();
                userNom = nombre.getText();
                userFec = fecha.getText();
                userSig =  (String) signo.getSelectedItem();
                ingresar(userCod, userCed, userNom, userFec, userSig);
            }
        });
        deleteBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userCod = codigo.getText();
                borrar(userCod);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro");
        frame.setContentPane(new Registro().cedLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void comprobarCod(){
        try (
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); //Esencial para la conección
            Statement stmt= conn.createStatement();
            ResultSet rs= stmt.executeQuery(QUERYCOM);
        ){
            while (rs.next()){
                codI = rs.getString("Codigo");
                if(codI.equals(userCod)){
                    System.out.println("Codigo: "+rs.getString("Codigo"));
                    codV = rs.getString("Codigo");
                    System.out.println("Nombre: "+rs.getString("Nombre"));
                    nombreV = rs.getString("Nombre");
                    System.out.println("Cedula: "+rs.getString("Cedula"));
                    cedulaV = rs.getString("Cedula");
                    System.out.println("Fecha Nacimiento: "+rs.getString("FechaNac"));
                    fechaV = rs.getString("FechaNac");
                    System.out.println("Signo: "+rs.getString("Signo"));
                    signoV = rs.getString("Signo");
                }
                System.out.println("Coneccion aprobada");
            }
        }
        catch (Exception ex){
        throw new RuntimeException(ex);
        }
    }
    public static void comprobarNom(){
        try (
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); //Esencial para la conección
                Statement stmt= conn.createStatement();
                ResultSet rs= stmt.executeQuery(QUERYCOM);
        ){
            while (rs.next()){
                nombreI = rs.getString("Nombre");
                if(nombreI.equals(userNom)){
                    System.out.println("Codigo: "+rs.getString("Codigo"));
                    codV = rs.getString("Codigo");
                    System.out.println("Nombre: "+rs.getString("Nombre"));
                    nombreV = rs.getString("Nombre");
                    System.out.println("Cedula: "+rs.getString("Cedula"));
                    cedulaV = rs.getString("Cedula");
                    System.out.println("Fecha Nacimiento: "+rs.getString("FechaNac"));
                    fechaV = rs.getString("FechaNac");
                    System.out.println("Signo: "+rs.getString("Signo"));
                    signoV = rs.getString("Signo");
                }
                System.out.println("Coneccion aprobada");
            }
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    public static void comprobarSig(){
        try (
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); //Esencial para la conección
                Statement stmt= conn.createStatement();
                ResultSet rs= stmt.executeQuery(QUERYCOM);
        ){
            while (rs.next()){
                signoI = rs.getString("Signo");
                if(signoI.equals(userSig)){
                    System.out.println("Codigo: "+rs.getString("Codigo"));
                    codV = rs.getString("Codigo");
                    System.out.println("Nombre: "+rs.getString("Nombre"));
                    nombreV = rs.getString("Nombre");
                    System.out.println("Cedula: "+rs.getString("Cedula"));
                    cedulaV = rs.getString("Cedula");
                    System.out.println("Fecha Nacimiento: "+rs.getString("FechaNac"));
                    fechaV = rs.getString("FechaNac");
                    System.out.println("Signo: "+rs.getString("Signo"));
                    signoV = rs.getString("Signo");
                }
                System.out.println("Coneccion aprobada");
            }
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    public static void ingresar(String codx, String cedulax, String nombrex, String fechax, String signox){
        String QUERYING="INSERT INTO USUARIOS VALUES('"+codx+"', '"+cedulax+"','"+nombrex+"','"+fechax+"','"+signox+"');";

        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt= conn.createStatement();
        ){
            stmt.executeUpdate(QUERYING);
            System.out.println("Usuario Registrado");
        }catch (Exception el){
            throw new RuntimeException(el);
        }
    }
    public static void borrar(String codx){
        String QUERYDEL="DELETE FROM USUARIOS WHERE Codigo = '"+codx+"'";

        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt= conn.createStatement();
        ){
            stmt.executeUpdate(QUERYDEL);
            System.out.println("Usuario Eliminado");
        }catch (Exception el){
            throw new RuntimeException(el);
        }
    }

}
