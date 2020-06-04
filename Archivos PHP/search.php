<?php
$con = mysqli_connect("192.168.1.241","javier","futbolista10","cardiofit");
$nombre_usuario = $_GET['nombre_usuario'];
 
$sql = "select * from historial_actividades where nombre_usuario like '%$nombre_usuario%' ORDER BY categoria";
 
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
	array_push($result,array(
    	'ejercicios'=>$row[1],
    	'peso_usado'=>$row[2],
    	'categoria'=>$row[3],
	));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);
 
?>