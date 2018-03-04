<?php
    session_start();
    $dbname = $_SESSION['dbname'];
    $dbuser = $_SESSION['dbuser'];
    
    $gid = $_GET['gid'];
    $pckid = $_GET['pckid'];
    $date = $_GET['date'];
    
    $connection = new mysqli("localhost","$dbuser","tushar1997","$dbname");
    
    if(!$connection){
        echo "Cannot connect to DB";
    }
    else {
        $result = $connection->query("insert into buys_touristpack values ($gid,$pckid,'$date')");
        
        if(!$result){
            echo "Error processing request";
        }
        else {
            echo "Your Tourist Pack has been activated";
        }
    }
    $connection->close();
?>