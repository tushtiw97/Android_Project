<?php
    session_start();
    
    $dbname = $_SESSION['dbname'];
    $dbuser = $_SESSION['dbuser'];
    
    //$billid = $_GET['billid'];
    $price = $_GET['price'];
    $gid = $_GET['gid'];
    
    $connection = new mysqli("localhost","$dbuser","tushar1997","$dbname");
    
    if(!$connection){
        echo "Cannot connect";
    }
    else {
        $result = $connection->query("select max(BillId) from food");
        if(!$result){
            echo "Cannot fetch max id";
        }
        else {
            $row = $result->fetch_assoc();
            $billid = (int)$row['max(BillId)'] + 1;
            $result1 = $connection->query("insert into food values ($billid,$price)");
            
            if(!$result1){
                echo "Cannot insert into food table";
            }
            else {
                $result2 = $connection->query("insert into orders values ($billid,$gid)");
                
                if(!$result2){
                    echo "Cannot insert into orders table";
                }
                else {
                    echo "Your food has been ordered";
                }
            }
        }
        $connection->close();
    }
?>