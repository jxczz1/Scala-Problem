package co.com.s4n.drone.modelling.dominio.entidades



//Se definen Sustantivos
trait movimiento

object movimiento {
  def newMovimiento(c:Char):movimiento ={
    c match {
      case 'A' => A()
      case 'D' => D()
      case 'I' => I()
      case _ => throw new Exception(s"Caracter invalido para creacion de movimiento: $c")
    }
  }
}

 // extienden de movimiento
  case class A( ) extends movimiento
  case class I( ) extends movimiento
  case class D( ) extends movimiento



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





