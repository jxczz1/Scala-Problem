package co.com.s4n.drone.modelling.dominio
import scala.concurrent.ExecutionContext.Implicits.global
import co.com.s4n.drone.modelling.dominio.entidades._
import co.com.s4n.drone.modelling.dominio.servicios.{ServicioArchivo, ServicioDron}


import scala.concurrent.Future
import scala.util.Try

object main extends App {

  val fileName: Archivo = Archivo("/home/s4n/Documentos/in.txt")

  val pos = Posicion(Coordenada(0,0),N())
  val drone: Drone = new Drone(1,pos,Capacidad(10))

  val listRuta: Ruta= ServicioArchivo.leerArchivo(fileName)
  println(s"Sucess Aqui!! \n ${listRuta}")




}