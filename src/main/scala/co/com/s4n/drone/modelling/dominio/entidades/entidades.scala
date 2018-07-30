package co.com.s4n.drone.modelling.dominio.entidades

import co.com.s4n.drone.modelling.dominio.servicios.AlgebraInstruccion


// Company de orientacion

object Orientacion {
  def newOrientacion(o:Char): Orientacion ={
    o match {
      case 'N' => N()
      case 'S' => S()
      case 'E' => E()
      case 'O' => O()
      case _ => throw new Exception(s"Caracter invalido para creacion de orientacion: $o")
    }
  }

}


trait Orientacion //Se define sustantivo de Orientacion


  case class  N () extends Orientacion
  case class  S () extends Orientacion
  case class  E () extends Orientacion
  case class  O () extends Orientacion


// Se define sustantivo Posicion compuesta por Coordenada y orientacion
case class Posicion (coordenada: Coordenada,orientacion : Orientacion)

// se define sustantivo coordenada que hace parte del dron
case class  Coordenada( x: Int ,y: Int )

//Se define capacidad del dron
case class  Capacidad( almuerzos : Int)

//Sustantivo
case class Drone (posicion: Posicion,capacidad: Capacidad)

case class  movimiento (posicion: Posicion, instruccion: instruccion)





