package co.com.s4n.drone.modelling.dominio.entidades

import com.sun.net.httpserver.Authenticator.Failure

import scala.util.{Failure, Try}


// Company de orientacion
object Orientacion {
  def newOrientacion(o:Char): Orientacion ={
    o match {
      case 'N' => N()
      case 'S' => S()
      case 'E' => E()
      case 'O' => O()
    }
  }

}


trait Orientacion //Se define sustantivo de Orientacion


  case class  N () extends Orientacion{
    override def toString: String = {
      "Norte"
    }

  }

  case class  S () extends Orientacion{
    override def toString: String = {
      "Sur"
    }

  }
  case class  E () extends Orientacion{
    override def toString: String = {
      "Oriente"
    }

  }
  case class  O () extends Orientacion{
    override def toString: String = {
      "Occidente"
    }
  }

//Se definen Sustantivos
trait Instruccion

// extienden de instruccion
case class A( ) extends  Instruccion
case class D( ) extends  Instruccion
case class I( ) extends  Instruccion

object Instruccion {    //Se verifica dato Que ingresa
  def newInstruccion(c:String):Instruccion ={
    c match {
      case "A" => A()
      case "D" => D()
      case "I" => I()
      //case _ => Failure(new IllegalArgumentException("Instruccion incorrecta"))
    }
  }
}



// Se define sustantivo Posicion compuesta por Coordenada y orientacion
case class Posicion (coordenada: Coordenada,orientacion : Orientacion)

// se define sustantivo coordenada que hace parte del dron
case class  Coordenada( x: Int ,y: Int )

//Se define capacidad del dron
case class  Capacidad( cantidadAlmuerzos : Int)

//Sustantivo drone
case class Drone ( id:Int, posicion: Posicion,capacidad: Capacidad )

// Sustantivo  entrega
case  class Entrega(listaInstrucciones : List [Instruccion])

// Sustantivo  entrega
case  class Ruta(listaEntregas: List[Entrega])








