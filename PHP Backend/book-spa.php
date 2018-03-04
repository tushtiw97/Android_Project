<?php
    session_start();
    $dbname = $_SESSION['dbname'];
    $dbuser = $_SESSION['dbuser'];
    
    $gid = $_GET['gid'];
    $pckid = $_GET['pckid'];
    $date = $_GET['date'];
    $time = $_GET['time'];
    
    $connection = new mysqli("localhost","$dbuser","tushar1997","$dbname");
    
    if(!$connection){
        echo "Cannot connect to DB";
    }
    else {
        $result = $connection->query("insert into books values ($gid, $pckid,'$date','$time')");
        
        if(!$result){
            echo "The selected slot is not available";
        }
        else {
            echo "Your spa booking has been confirmed";
        }
    }
    $connection->close();
?>