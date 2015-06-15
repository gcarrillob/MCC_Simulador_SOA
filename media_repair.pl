use strict;
use warnings;

#ejecutar el programa de simulación y
#guardar la salida en una variable
my $salida =  `java -cp bin DiscreteEventSimulation`; 
my @lineas = split /\n/, $salida;


my $t_inicio=0; 
my $t_fin = 0;
my $encontrado=0;
my $num=0;
my $total =0;

#leer cada linea y parsear el contenido
foreach my $linea (@lineas) {
   if ( $linea =~  /([\d.]+)\s(.*)/ ) {
	my ($tiempo, $evento) = ($1, $2);
	#capturar el evento machine failure
	if ($evento eq "starting repair") {
		$encontrado++;
		$t_inicio = $tiempo;
	}
	#capturar el evento finishing repair
	if ($evento eq "finishing repair") {
		$encontrado++;
		$t_fin = $tiempo;
	}
	
	if ($encontrado>1){
		$encontrado = 0;
		$total += ($t_fin - $t_inicio);
		$num++;
	}	
   }  
}

if ($num > 0){
    my $promedio = $total /$num;
    print "El tiempo promedio que le toma a la máquina ser reparada es : $promedio\n";
}

