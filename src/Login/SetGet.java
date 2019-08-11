/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

/**
 *
 * @author Lenovo
 */
public class SetGet {
    private static String id_user; 
    private static String akses; 
    private static String nama;
    private static String id_barang;

    public static void setId_user(String id_user){
        SetGet.id_user=id_user;
    }
    public static String getId_user(){
        return id_user;
    }
    public static void setAkses(String akses){
        SetGet.akses=akses;
    }
    public static String getAkses(){
        return akses;
    }
    public static void setNama(String nama){
        SetGet.nama=nama;
    }
    public static String getNama(){
        return nama;
    }
    
    public static void setId_barang(String id_barang){
        SetGet.id_barang=id_barang;
    }
    public static String getId_barang(){
        return id_barang;
    }

    
}
