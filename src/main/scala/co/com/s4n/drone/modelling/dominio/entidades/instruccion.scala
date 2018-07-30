package co.com.s4n.drone.modelling.dominio.entidades

//Se definen Sustantivos
trait instruccion

object instruccion {
  def newMovimiento(c:Char):instruccion ={
    c match {
      case 'A' => A()
      case 'D' => D()
      case 'I' => I()
      case _ => throw new Exception(s"Caracter invalido para creacion de movimiento: $c")
    }
  }
}

// extienden de movimiento
case class A( ) extends  instruccion
case class D( ) extends  instruccion
case class I( ) extends  instruccion