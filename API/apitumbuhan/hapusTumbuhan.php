<?php

    //Mendapatkan Nilai ID
    $id = $_GET['id'];
    
    //Import File Koneksi Database
    require_once('koneksi.php');
    
    //Membuat SQL Query
    $sql = "DELETE FROM tumbuhan WHERE id=$id;";
    
    
    //Menghapus Nilai pada Database 
    if(mysqli_query($con,$sql)){
    echo 'Berhasil Menghapus Tumbuhan';
    }else{
    echo 'Gagal Menghapus Tumbuhan';
    }
    
    mysqli_close($con);

?>