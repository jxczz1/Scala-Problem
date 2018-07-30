package co.com.s4n.drone.modelling.dominio.servicios

import co.com.s4n.drone.modelling.dominio.entidades._



  //Defino un Algebra Api
   sealed trait AlgebraServicioDrone {
    def giroAlaIzquierda(pos:Posicion):Posicion
    def giroAlaDerecha(pos:Posicion):Posicion
    def avanzarDrone(pos: Posicion): Posicion

  }


   sealed trait InterpretacionServicioDrone extends AlgebraServicioDrone {

    //Operaciones Orientacion

    def giroAlaIzquierda(pos: Posicion): Posicion = { //Pos= posicion actual

     val Fpos: Posicion = {
      val posicion: Orientacion =pos.orientacion match {

        case N() => O()
        case S() => E()
        case E() => N()
        case O() => S()
        case _ => throw new Exception(s"Caracter invalido para cambio de orientacion: ${pos}")
      }
       val posActu =Posicion(pos.coordenada, posicion)
       posActu //Posicion actualizada con la cardinalidad
    }
     Fpos //Futuro de la Posicion actualizada
   }

    def giroAlaDerecha(pos: Posicion): Posicion = { //Pos= posicion actual


      val Fpos: Posicion =  {
        val posicion: Orientacion = pos.orientacion match {

          case N() => E()
          case S() => O()
          case E() => S()
          case O() => N()
          case _ => throw new Exception(s"Caracter invalido para cambio de orientacion: ${pos}")
        }
       val posActu=Posicion(pos.coordenada,posicion)
        posActu //Posicion actualizada en la cardinalidad
      }
      Fpos //la Posicion actualizada
    }

     //Operaciones avanzar

    def avanzarDrone(pos: Posicion): Posicion = {


      val orien: Orientacion = pos.orientacion //Orientacion que define en que direccion avanza

      val res: Posicion =  {

        val coordenadaActualizada: Coordenada = {
          orien match {

            case N() => Coordenada(pos.coordenada.x, pos.coordenada.y + 1)
            case S() => Coordenada(pos.coordenada.x, pos.coordenada.y - 1)
            case E() => Coordenada(pos.coordenada.x + 1, pos.coordenada.y)
            case O() => Coordenada(pos.coordenada.x - 1, pos.coordenada.y)
            case _ => throw new Exception(s"Caracter invalido para cambio de orientacion: $orien")
          }
        }
        val posActual = Posicion(coordenadaActualizada, pos.orientacion)
        posActual
      }
      println(s"avanzar drone Aquiiii${res}")
      res

    }



    def MovimientoDron( ruta: Array[String],pos :Posicion) :movimiento ={







    }




  }

  // Trait Object
  object InterpretacionServicioDrone extends InterpretacionServicioDrone







