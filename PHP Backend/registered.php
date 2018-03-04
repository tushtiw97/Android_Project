<?php
    session_start();
    $dbuser = $_SESSION['dbuser'];
    $dbname = $_SESSION['dbname'];
    
    $connection = new mysqli("localhost","$dbuser","tushar1997","$dbname");
    if(!$connection){
        echo "Cannot connect";
    }
    else {
        $id = $_GET['rid'];
        $result = $connection->query("update room set Available=false where id=$id");
        if(!$result){
            echo "Cannot perform room availability status query";
        }
        else {
            $username = $_SESSION['username'];
            $password = $_GET['rid'];
            $result2 = $connection->query("insert into login values ('$username','$password')");
            if(!$result2){
                echo "Cannot update login details";
                echo "<br>$password";
                echo "<br>".var_dump($password);
            }
            else {
                $id = (int)$_GET['gid'];
                $result3 = $connection->query("insert into account values ('$id','$username')");
                if(!$result3){
                    echo "Cannot insert into account table";
                    echo var_dump($id);
                    echo "<br>";
                    echo var_dump($username);
                }
                else {
                    $gid = (int)$_GET['gid'];
                    $rid = (int)$_GET['rid'];
                    $result4 = $connection->query("insert into alloted values ($gid,$rid)");
                    if(!$result4){
                        echo "Cannot perform room alloted query";
                    }
                    else {
                        $connection->close();
                        header("Location: registered.html");
                    }
                }
            }
        }
    }
?>