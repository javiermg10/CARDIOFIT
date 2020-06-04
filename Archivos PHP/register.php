<?php 

	#con=mysqli_connect("ip_servidor","nombre_usuario","contraseña","base_datos")	

	$con=mysqli_connect("192.168.1.241","javier","futbolista10","cardiofit");
	$con2=mysqli_connect("192.168.1.241","javier","futbolista10","cardiofit");
	
	$nombre = $_POST["nombre"];
	$nombre_usuario = $_POST["nombre_usuario"];
	$contrasena =$_POST["contrasena"];
	$edad =$_POST["edad"];
	$peso =$_POST["peso"];
	$altura =$_POST["altura"];

	$sql = "INSERT INTO usuarios(nombre,nombre_usuario,contrasena) VALUES ('$nombre','$nombre_usuario','$contrasena')";
	$sql2 = "INSERT INTO datos_personales(edad,peso,altura,nombre_usuario) VALUES ('$edad','$peso','$altura','$nombre_usuario')";

	$result = mysqli_query( $con,$sql );
	$result2 = mysqli_query( $con2,$sql2);
	
	if($result) {
		echo "Registro guardado";
	}
	else {
		echo "Error, correo existente";
	}

?>