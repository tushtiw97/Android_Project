<?php
    session_start();
    $dbuser = $_SESSION['dbuser'];
    $dbname = $_SESSION['dbname'];
    $fname = $_POST['fname'];
    $mname = $_POST['mname'];
    $lname = $_POST['lname'];
    $mobno = $_POST['mobno'];
    $checkin = $_POST['checkin'];
    $checkout = $_POST['checkout'];
    $email = $_POST['email'];
    
    if($mname == ""){
        $mname = "mname";
    }
    
    $connection = new mysqli("localhost","$dbuser","tushar1997","$dbname");
    if(!$connection){
        echo "Cannot connect to DB";
    }
    else {
        $result = $connection->query("select max(id) from guest") or die($connection->error);
        $row = $result->fetch_assoc();
        $id = (int)(((int) $row['max(id)']) + 1);
        $result = $connection->query("insert into guest values ($id,'$fname','$mname','$lname','$mobno','$checkin','$checkout','$email')");
        $username = "$fname[0]"."$fname[1]"."$fname[2]"."$mname[0]"."$mname[1]"."$mname[2]"."$lname[0]"."$lname[1]"."$lname[2]"."$id";
        $_SESSION['username'] = $username;
        if(!$result){
            echo "Cannot process insert query";
        }
        else {
            $connection->close();
            $to = $email;
            $subject = "You have successfully been registered";
            $txt = "Your username is $username and password is your room number which you'll select next";
            
            mail($to,$subject,$txt);
            
            header("Location: select-room.php?gid=$id");
        }
    }
?>