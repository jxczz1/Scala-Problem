package co.com.s4n.drone.modelling.dominio.servicios

import co.com.s4n.drone.modelling.dominio.entidades._


//Defino Algebra Api LeerInstruccion
sealed trait algebraServicioInstrucciones {

  def operarInstruccion (drone: Drone , instruccion : Instruccion): Drone // Interpreto instrucciones  y muestro los movimientos

}


//Interpretar AlgebraInstrucciones
sealed trait interpreteHacerInstrucciones extends algebraServicioInstrucciones {


  def operarInstruccion(drone: Drone, instruccion : Instruccion): Drone = {

    val posActualizada: Drone = instruccion match {
      case A() => InterpretacionServicioDrone.avanzarDrone(drone)
      case D() => InterpretacionServicioDrone.giroAlaDerecha(drone)
      case I() => InterpretacionServicioDrone.giroAlaIzquierda(drone)

    }
    posActualizada
  }

}

// Trait Object
object interpreteHacerInstrucciones extends interpreteHacerInstrucciones