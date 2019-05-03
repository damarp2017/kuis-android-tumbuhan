<?php

    if($_SERVER['REQUEST_METHOD']=='POST'){
        //MEndapatkan Nilai Dari Variable 
        $id = $_POST['id'];
        $nama = $_POST['nama'];
        $jenis = $_POST['jenis'];
        $warna = $_POST['salary'];
        
        //import file koneksi database 
        require_once('koneksi.php');
        
        //Membuat SQL Query
        $sql = "UPDATE tumbuhan SET nama = '$nama', jenis = '$jenis', warna = '$warna' WHERE id = $id;";
        
        //Meng-update Database 
        if(mysqli_query($con,$sql)){
        echo 'Berhasil Update Data Tumbuhan';
        }else{
        echo 'Gagal Update Data Tumbuhan';
        }
        
        mysqli_close($con);
    }

?>