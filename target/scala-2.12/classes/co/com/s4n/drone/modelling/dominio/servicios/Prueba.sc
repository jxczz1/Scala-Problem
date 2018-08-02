import co.com.s4n.drone.modelling.dominio.entidades._
import co.com.s4n.drone.modelling.dominio.servicios.{ServicioDron, ServicioArchivo}

import scala.concurrent.Await
import scala.util.Try


val fileName: Archivo= Archivo("/home/s4n/Documentos/in.txt")


val pos = Posicion(Coordenada(0,0),N())
val drone: Drone = new Drone(1,pos,Capacidad(10))

val listRuta: Ruta= ServicioArchivo.leerArchivo(fileName)
println(s"Sucess Aqui!! \n ${listRuta}")

val reporte: Reporte = ServicioArchivo.generarReporteEntrega(ServicioDron.reporteEntrega(ServicioDron.hacerRuta(listRuta,drone)))




