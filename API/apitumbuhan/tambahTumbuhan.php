<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
		
    //Mendapatkan Nilai Variable
    $nama = $_POST['nama'];
    $jenis = $_POST['jenis'];
    $warna = $_POST['warna'];
    
    //Pembuatan Syntax SQL
    $sql = "INSERT INTO tumbuhan (nama,jenis,warna) VALUES ('$nama','$jenis','$warna')";
    
    //Import File Koneksi database
    require_once('koneksi.php');
    
    //Eksekusi Query database
    if(mysqli_query($con,$sql)){
        echo 'Berhasil Menambahkan Tumbuhan';
    }else{
        echo 'Gagal Menambahkan Tumbuhan';
    }
    mysqli_close($con);
}

?>