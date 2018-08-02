package co.com.s4n.drone.modelling.dominio.servicios

import java.util.concurrent.Executors

import co.com.s4n.drone.modelling.dominio.entidades._
import co.com.s4n.drone.modelling.dominio.main.{drone, listRuta}
import scala.concurrent.ExecutionContext.global
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import scala.util.Try


//Defino un Algebra Api
sealed trait ServicioDronAlgebra {
  def girarIzquierda(drone: Drone): Drone

  def girarDerecha(drone: Drone): Drone

  def avanzarDrone(drone: Drone): Drone

  def hacerEntrega(entrega: Entrega, drone: Drone): Drone

  def hacerRuta(ruta: Ruta, drone: Drone): List[Drone]

  def operarInstruccion(drone: Drone, instruccion: Instruccion): Drone

  def reporteEntrega(drone: List[Drone]): List[Posicion]

  def repartirPedidos(drone: Drone, ruta: Ruta)(implicit ec: ExecutionContext): Future[List[Drone]]
}


sealed trait ServicioDron extends ServicioDronAlgebra {

  //  Hilo Principal
  val ecDrones = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(20))

  //Operaciones Orientacion

  def girarIzquierda(drone: Drone): Drone = { //Pos= posicion actual

    val Fpos: Posicion = {
      // la Posicion actualizada
      val posicion: Orientacion = drone.posicion.orientacion match {

        case N() => O()
        case S() => E()
        case E() => N()
        case O() => S()
      }

      val posActu = Posicion(drone.posicion.coordenada, posicion)
      posActu //Posicion actualizada con la cardinalidad
    }
    val dronPosActualizada = Drone(drone.id, Fpos, drone.capacidad)
    dronPosActualizada
  }

  def girarDerecha(drone: Drone): Drone = { //Pos= posicion actual


    val Fpos: Posicion = {
      val posicion: Orientacion = drone.posicion.orientacion match {

        case N() => E()
        case S() => O()
        case E() => S()
        case O() => N()
      }
      val posActu = Posicion(drone.posicion.coordenada, posicion)
      posActu //Posicion actualizada en la cardinalidad
    }
    val dronPosActualizada: Drone = Drone(drone.id, Fpos, drone.capacidad)
    dronPosActualizada
  }

  //Operaciones avanzar

  def avanzarDrone(drone: Drone): Drone = {


    val orien: Orientacion = drone.posicion.orientacion //Orientacion que define en que direccion avanza


    val coordenadaActualizada: Coordenada = {
      orien match {

        case N() => Coordenada(drone.posicion.coordenada.x, drone.posicion.coordenada.y + 1)
        case S() => Coordenada(drone.posicion.coordenada.x, drone.posicion.coordenada.y - 1)
        case E() => Coordenada(drone.posicion.coordenada.x + 1, drone.posicion.coordenada.y)
        case O() => Coordenada(drone.posicion.coordenada.x - 1, drone.posicion.coordenada.y)
      }
    }
    val posActual = Posicion(coordenadaActualizada, drone.posicion.orientacion)
    val droneActual = Drone(drone.id, posActual, drone.capacidad)
    droneActual
  }


  def operarInstruccion(drone: Drone, instruccion: Instruccion): Drone = {

    val posActualizada: Drone = instruccion match {
      case A() => ServicioDron.avanzarDrone(drone)
      case D() => ServicioDron.girarDerecha(drone)
      case I() => ServicioDron.girarIzquierda(drone)

    }
    posActualizada
  }


  def hacerEntrega(entrega: Entrega, drone: Drone): Drone = {

    val lipos: List[Drone] = List(drone) //Lista posicion
    // val ruta2:  List[Char] = entrega.toList   //convertimos la cadena en una lista de char para leer la instruccion
    val liposActu: List[Drone] = entrega.listaInstrucciones.foldLeft(lipos) { (resultado, item) =>
      resultado :+ operarInstruccion(resultado.last, item)
    }
    val posFinalDron: Drone = liposActu.last // Ultima posicion recorrido dron durante la ruta
    posFinalDron

  }

  def hacerRuta(ruta: Ruta, drone: Drone): List[Drone]= { //Funcion que entrega los domilios en lso destinos



      val liposDrone: List[Drone] = List(drone)
      val resultadoEntrega: List[Drone] = ruta.listaEntregas.foldLeft(liposDrone) {
        (resultado, item) =>
          resultado :+ ServicioDron.hacerEntrega(item, resultado.last)
        }
      resultadoEntrega

  }

  def reporteEntrega(drone: List[Drone]): List[Posicion] = { //Funcion que entrega Resultados de entrega domicilios dron

    val reporteEntrega: List[Posicion] = drone.map(x => x.posicion)
    reporteEntrega

  }

  def repartirPedidos(drone: Drone, ruta: Ruta)(implicit ec: ExecutionContext): Future[List[Drone]]= Future(hacerRuta(listRuta,drone))

}

// Trait Object
object ServicioDron extends ServicioDron







