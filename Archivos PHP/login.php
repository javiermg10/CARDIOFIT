<?php 
		$con=mysqli_connect("192.168.1.241","javier","futbolista10","cardiofit");
	
		$nombre_usuario = $_POST["nombre_usuario"];
		$contrasena = $_POST["contrasena"];

		$sql = "SELECT * FROM usuarios WHERE  nombre_usuario = '$nombre_usuario' AND contrasena = '$contrasena'";
		$result = mysqli_query($con,$sql);
		
		if($result->num_rows > 0){
			echo "Sesion Iniciada" ;
		}else{
  			 echo "user not found";
}
?>