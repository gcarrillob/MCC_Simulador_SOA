<?php

    $file="trace.txt";
    $file = fopen("trace.txt", "w");
    for ($i=0;$i<1000;$i++){
        $working = rand(25, 35);
        $repair = rand(8, 12);
        fwrite($file, "w $working" . PHP_EOL);
        fwrite($file, "r $repair" . PHP_EOL);
    }
    fclose($file);
?>

