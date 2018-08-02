package co.com.s4n.drone.modelling.dominio.servicios

import co.com.s4n.drone.modelling.dominio.entidades._

import scala.concurrent.Future
import scala.util.Try



  //Defino un Algebra Api
   sealed trait AlgebraServicioDrone {
    def giroAlaIzquierda(drone: Drone):Drone
    def giroAlaDerecha(drone: Drone):Drone
    def avanzarDrone(drone: Drone): Drone
    def hacerEntrega (entrega: Entrega  , drone :Drone): Drone
    def hacerRuta (ruta: Try[Ruta]  , drone :Drone): Future[List[Drone]]
    def operarInstruccion (drone: Drone , instruccion : Instruccion): Drone
    def reporteEntrega ( drone: List[Drone] ): List[Posicion]
  }


   sealed trait InterpretacionServicioDrone extends AlgebraServicioDrone {

    //Operaciones Orientacion

    def giroAlaIzquierda(drone: Drone): Drone = { //Pos= posicion actual

      val Fpos: Posicion = {// la Posicion actualizada
      val posicion: Orientacion =drone.posicion.orientacion match {

        case N() => O()
        case S() => E()
        case E() => N()
        case O() => S()
      }
       val posActu =Posicion(drone.posicion.coordenada, posicion)
       posActu //Posicion actualizada con la cardinalidad
    }
       val dronPosActualizada = Drone(drone.id,Fpos,drone.capacidad)
      dronPosActualizada
   }

    def giroAlaDerecha(drone: Drone): Drone = { //Pos= posicion actual


      val Fpos: Posicion =  {
        val posicion: Orientacion = drone.posicion.orientacion match {

          case N() => E()
          case S() => O()
          case E() => S()
          case O() => N()
        }
       val posActu=Posicion(drone.posicion.coordenada,posicion)
        posActu //Posicion actualizada en la cardinalidad
      }
      val dronPosActualizada: Drone = Drone(drone.id,Fpos,drone.capacidad)
    dronPosActualizada
    }

     //Operaciones avanzar

    def avanzarDrone(drone: Drone): Drone = {



      val orien: Orientacion = drone.posicion.orientacion //Orientacion que define en que direccion avanza



        val coordenadaActualizada: Coordenada = {
          orien match {

            case N() => Coordenada(drone.posicion.coordenada.x,drone.posicion.coordenada.y + 1)
            case S() => Coordenada(drone.posicion.coordenada.x,drone.posicion.coordenada.y- 1)
            case E() => Coordenada(drone.posicion.coordenada.x + 1, drone.posicion.coordenada.y)
            case O() => Coordenada(drone.posicion.coordenada.x - 1, drone.posicion.coordenada.y)
          }
        }
        val posActual = Posicion(coordenadaActualizada, drone.posicion.orientacion)
        val droneActual = Drone(drone.id,posActual,drone.capacidad)
        droneActual
    }


     def operarInstruccion(drone: Drone, instruccion : Instruccion): Drone = {

       val posActualizada: Drone = instruccion match {
         case A() => InterpretacionServicioDrone.avanzarDrone(drone)
         case D() => InterpretacionServicioDrone.giroAlaDerecha(drone)
         case I() => InterpretacionServicioDrone.giroAlaIzquierda(drone)

       }
       posActualizada
     }



     def hacerEntrega ( entrega: Entrega , drone :Drone): Drone ={

       val lipos: List[Drone] = List(drone) //Lista posicion
       // val ruta2:  List[Char] = entrega.toList   //convertimos la cadena en una lista de char para leer la instruccion
       val liposActu :List[Drone] = entrega.listaInstrucciones.foldLeft(lipos){ (resultado,item) =>
         resultado :+ operarInstruccion(resultado.last,item)
       }
       val posFinalDron: Drone =liposActu.last// Ultima posicion recorrido dron durante la ruta
       posFinalDron

     }

     def hacerRuta ( ruta : Try[Ruta]  , drone :Drone): Future[List[Drone]] ={ //Funcion que entrega los domilios en lso destinos

     val res = Future{
       val liposDrone: List[Drone] = List(drone)
       val resultadoEntrega: List[Drone] = ruta.get.listaEntregas.foldLeft(liposDrone) {
         (resultado, item) =>
          resultado :+ InterpretacionServicioDrone.hacerEntrega(item,resultado.last)
       }
        resultadoEntrega
        }
       res
     }

     def reporteEntrega ( drone: Future[List[Drone]] ): List[Posicion] ={ //Funcion que entrega Resultados de entrega domicilios dron

       val reporteEntrega: List[Posicion] = drone.map(x => x.posicion)
       reporteEntrega
     }



   }

  // Trait Object
  object InterpretacionServicioDrone extends InterpretacionServicioDrone







