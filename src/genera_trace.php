<?php

    $file="trace.txt";
    $file = fopen("trace.txt", "w");
    for ($i=0;$i<1000;$i++){
        $r1=100/rand(1,100);
        $r2=100/rand(1,100);
        $working = rand(25, 35)+$r1;
        $repair = rand(8, 12)+$r2;
        
        fwrite($file, "w $working" . PHP_EOL);
        fwrite($file, "r $repair" . PHP_EOL);
    }
    fclose($file);
?>

