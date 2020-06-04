<?php
$con = mysqli_connect("192.168.1.241","javier","futbolista10","cardiofit");
$nombre_usuario = $_GET['nombre_usuario'];

$sql = "SELECT usuarios.nombre, usuarios.nombre_usuario, usuarios.contrasena, datos_personales.edad, datos_personales.peso, datos_personales.altura 
		FROM usuarios, datos_personales WHERE usuarios.nombre_usuario like '$nombre_usuario' AND datos_personales.nombre_usuario like '$nombre_usuario'";  

$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
	array_push($result,array(
    	'nombre'=>$row[0],
    	'nombre_usuario'=>$row[1],
    	'contrasena'=>$row[2],
    	'edad'=>$row[3],
    	'peso'=>$row[4],
    	'altura'=>$row[5],
	));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);

?>