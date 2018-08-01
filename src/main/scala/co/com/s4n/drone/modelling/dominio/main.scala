package co.com.s4n.drone.modelling.dominio

import co.com.s4n.drone.modelling.dominio.entidades._
import co.com.s4n.drone.modelling.dominio.servicios.interpretealgebraServicioArchivos

object main extends App {

  val fileName: Archivo = Archivo("/home/s4n/Documentos/in.txt")
  val prueba = interpretealgebraServicioArchivos.leerArchivo(fileName)
  //val prueba2 = prueba.fold[List[Drone]](x => {List(Drone(1,Posicion(Coordenada(0,0),N()),Capacidad(4)))},y=>{interpretealgebraServicioArchivos.leerArchivo(fileName)})
  println(prueba)





}