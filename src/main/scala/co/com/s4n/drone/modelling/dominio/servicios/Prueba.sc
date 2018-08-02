import co.com.s4n.drone.modelling.dominio.entidades._
import co.com.s4n.drone.modelling.dominio.servicios.{ServicioDron, ServicioArchivo}

import scala.concurrent.Await
import scala.util.Try


val fileName: Archivo= Archivo("/home/s4n/Documentos/in.txt")


val pos = Posicion(Coordenada(0,0),N())
val drone: Drone = new Drone(1,pos,Capacidad(10))

val listRuta: Try[Ruta]= interpretealgebraServicioArchivoAlgebra.leerArchivo(fileName)
println(s"Sucess Aqui!! \n ${listRuta}")

val reporte: Try[Reporte] = listRuta.map(x =>
  interpretealgebraServicioArchivoAlgebra.generarReporteEntrega(InterpretacionServicioDronAlgebra.reporteEntrega(InterpretacionServicioDronAlgebra.hacerRuta(Try(x),drone)))

)



