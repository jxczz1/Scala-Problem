package co.com.s4n.drone.modelling.dominio.servicios

import co.com.s4n.drone.modelling.dominio.entidades.{Posicion, movimiento}


//Defino Algebra Api LeerInstruccion
sealed trait algebraInstrucciones {

  def OperarInstruccion (mov : movimiento): Posicion // Interpreto instrucciones  y muestro lso movimientos

}


//Interpretar AlgebraInstrucciones
scaled trait interpreteHacerInstrucciones extends algebraInstrucciones{


  def OperarInstruccion (mov : movimiento): Posicion ={

    val posActualizada : Posicion = mov.instruccion match {
      case A() => interpreteSeguirRuta.avanzar(mov.posicion)
      case I() => interpreteSeguirRuta.moverDerecha(mov.posicion)
      case D() => interpreteSeguirRuta.moverIzquierda(mov.posicion)
      }
    posActualizada
  }


}

// Trait Object
object interpreteHacerInstrucciones extends interpreteHacerInstrucciones