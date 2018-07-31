package co.com.s4n.drone.modelling.dominio.entidades

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
    }
  }
}

