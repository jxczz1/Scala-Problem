package co.com.s4n.drone.modelling.dominio

import co.com.s4n.drone.modelling.dominio.entidades._
import co.com.s4n.drone.modelling.dominio.servicios.{InterpretacionServicioDrone, interpretealgebraServicioArchivos}

import scala.util.Try

object main extends App {

  val fileName: Archivo = Archivo("/home/s4n/Documentos/in.txt")

  val pos = Posicion(Coordenada(0,0),N())
  val drone: Drone = new Drone(1,pos,Capacidad(10))

  val listRuta: Try[Ruta]= interpretealgebraServicioArchivos.leerArchivo(fileName)
  println(s"Sucess Aqui!! \n ${listRuta}")

  val reporte: Try[Reporte] = listRuta.map(x =>
    interpretealgebraServicioArchivos.generarReporteEntrega(InterpretacionServicioDrone.reporteEntrega(InterpretacionServicioDrone.hacerRuta(Try(x),drone)))
  )

  println("\n ***********************LISTA Resultados*************** ")
  println(s"Lista Resultados de posicion donde hizo entregas el dron\n ${reporte}")


}