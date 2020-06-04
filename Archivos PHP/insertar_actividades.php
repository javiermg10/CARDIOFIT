<?php 

	#con=mysqli_connect("ip_servidor","nombre_usuario","contraseña","base_datos")	

	$con=mysqli_connect("192.168.1.241","javier","futbolista10","cardiofit");
	
	$ejercicios = $_POST["ejercicios"];
	$peso_usado =$_POST["peso_usado"];
	$categoria =$_POST["categoria"];
	$nombre_usuario =$_POST["nombre_usuario"];

	$sql = "INSERT INTO historial_actividades(ejercicios,peso_usado,categoria,nombre_usuario) VALUES ('$ejercicios','$peso_usado','$categoria','$nombre_usuario')";

	$result = mysqli_query( $con,$sql );
	
	if($result) {
		echo "Registro guardado";
	}
	else {
		echo "Error, escriba de nuevo";
	}

?>