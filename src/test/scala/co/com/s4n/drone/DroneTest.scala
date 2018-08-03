package co.com.s4n.drone

import co.com.s4n.drone.modelling.dominio.entidades._
import org.scalatest.FunSuite
import co.com.s4n.drone.modelling.dominio.servicios.{ServicioArchivo, ServicioDron}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._





class DroneTest extends FunSuite {


  test("probando retorno posicion ") {


    val pos = new Posicion(Coordenada(5, 6), N())
    val drone: Drone = new Drone(1,pos,Capacidad(10))
    println(s"Aquiiii ${drone}")
    assert(drone == Drone(1,Posicion(Coordenada(5,6),N()),Capacidad(10)))


  }


  test("Avanzar drone en direccion de cardinalidad ") {


     val pos = Posicion(Coordenada(4,5),N())
     val drone: Drone = new Drone(1,pos,Capacidad(10))
     val prueba: Drone= ServicioDron.avanzarDrone(drone)

     assert(prueba ==  Drone(1,Posicion(Coordenada(4,6),N()),Capacidad(10)))

  }





  test("Girar Drone 90 Grados a la izquierda ") {


    val pos = Posicion(Coordenada(4,5),S())
    val drone: Drone = new Drone(1,pos,Capacidad(10))
    val posActualizada: Drone= ServicioDron.girarIzquierda(drone )


    assert(posActualizada == Drone(1,Posicion(Coordenada(4,5),E()),Capacidad(10)) )

  }


  test("Girar Drone 90 Grados a la Derecha ") {


    val pos = Posicion(Coordenada(4,5),S())
    val drone: Drone = new Drone(1,pos,Capacidad(10))
    val posActualizada: Drone= ServicioDron.girarDerecha(drone)


    assert(posActualizada ==  Drone(1,Posicion(Coordenada(4,5),O()),Capacidad(10)))

  }







  test("Interpretar ruta de una entrega  del drone ") {

    val listaInstrucciones: Entrega =Entrega(List(I(),I(),I(),I(),D(),A(),D(),D(),A(),A()))
    val pos = Posicion(Coordenada(-7,-3),E())
    val drone: Drone = new Drone(1,pos,Capacidad(10))


    val posfinRuta : Drone =  ServicioDron.hacerEntrega(listaInstrucciones,drone)

    assert(posfinRuta==Drone(1,Posicion(Coordenada(-7,-2),N()),Capacidad(10)) )

  }



  test("Test leer Archivo de instrucciones  ") {

    val fileName : Archivo = Archivo("files/in.txt")
    println("\n ***********************LISTA INSTRUCCIONES*************** ")
    val listIntrucciones = ServicioArchivo.leerArchivo(fileName)
    println( s" Lista de instrucciones txt \n ${listIntrucciones}")
   assert(listIntrucciones==Ruta(List(Entrega(List(A(), A(), A(), A(), I(), A(), A(), D())), Entrega(List(D(), D(), A(), I(), A(), D())), Entrega(List(A(), A(), I(), A(), D(), A(), D())), Entrega(List(A(), A(), A(), D(), A(), I(), A())), Entrega(List(D(), A(), A(), I(), A(), I(), A(), A())), Entrega(List(A(), A(), I(), D(), I(), A(), D(), D(), A())), Entrega(List(A(), A(), A(), D(), I(), D(), D(), A())), Entrega(List(D(), A(), A(), D(), A(), I(), I(), A())), Entrega(List(I(), I(), I(), I(), D(), A(), D(), D(), A(), A())), Entrega(List(A(), A(), D(), A(), A(), I(), A(), A())))))
  }




  test("Test Resultados coordenadas  de entregas") {

    val fileName: Archivo= Archivo("files/in.txt")


    val pos = Posicion(Coordenada(0,0),N())
    val drone: Drone = new Drone(1,pos,Capacidad(10))
    val listRuta: Ruta= ServicioArchivo.leerArchivo(fileName)
    val reporte: Future[Reporte] = ServicioArchivo.generarReporteEntrega(ServicioDron.reporteEntrega(ServicioDron.hacerRuta(listRuta,drone)))
    println("\n ***********************LISTA Resultados*************** ")
    println(s"Lista Resultados Dron en paralelo \n ${Await.result(reporte,10 seconds)}")

    (reporte ==Future(Reporte(List("(-2,4) Orientacion Norte", "(-1,3) Orientacion Sur","(0,0) Orientacion Occidente","(-4,1) Orientacion Occidente","(-5,1) Orientacion Sur","(-5,-1) Orientacion Occidente","(-7,-1) Orientacion Oriente","(-7,-3) Orientacion Oriente","(-7,-2) Orientacion Norte","(-5,2) Orientacion Norte"))))

  }


  test("Test Archivo con Instrucciones malas") {

    val fileName: Archivo= Archivo("files/in.txt")
    val pos = Posicion(Coordenada(0,0),N())
    val drone: Drone = new Drone(1,pos,Capacidad(10))
    val listRuta: Ruta= ServicioArchivo.leerArchivo(fileName)

    val reporte: Future[Reporte]=
      ServicioArchivo.generarReporteEntrega(ServicioDron.reporteEntrega(ServicioDron.hacerRuta(listRuta,drone))
    )
    println("\n ***********************Lista Resultados*************** ")
    println(s"Lista Resultados Dron en paralelo \n ${Await.result(reporte,10 seconds)}  ")

    assert(1==1 )

  }







}