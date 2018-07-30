package co.com.s4n.drone.droneTest

import co.com.s4n.drone.modelling.dominio.entidades._
import org.scalatest.FunSuite
import co.com.s4n.drone.modelling.dominio.servicios.InterpretacionServicioDrone

import scala.concurrent.Future






class TestDroneMovimiento extends FunSuite {


  test("probando retorno posicion ") {


    val pos = new Posicion(Coordenada(5, 6), N())
    println(s"Aquiiii ${pos}")
    assert(1 == 1)


  }


  test("Avanzar drone  en direccion cardinalidad ") {


     val posicion = Posicion(Coordenada(4,5),N())

     val prueba: Posicion= InterpretacionServicioDrone.avanzarDrone(posicion)


     assert(prueba == Posicion(Coordenada(4,6),N()))

  }





  test("Girar Drone 90 Grados a la izquierda ") {


    val posActual = Posicion(Coordenada(4,5),S())

      val  posActualizada: Posicion= InterpretacionServicioDrone.giroAlaIzquierda(posActual )


    assert(posActualizada == Posicion(Coordenada(4,5),E()))

  }


  test("Girar Drone 90 Grados a la Derecha ") {


    val posActual = Posicion(Coordenada(4,5),S())

    val  posActualizada: Posicion= InterpretacionServicioDrone.giroAlaDerecha(posActual )


    assert(posActualizada == Posicion(Coordenada(4,5),O()))

  }



}