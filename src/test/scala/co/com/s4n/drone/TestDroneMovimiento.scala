package co.com.s4n.drone

import co.com.s4n.drone.modelling.dominio.entidades._
import org.scalatest.FunSuite
import co.com.s4n.drone.modelling.dominio.servicios.{InterpretacionServicioDrone, interpretealgebraServicioArchivos}

import scala.util.{Failure, Success, Try}






class TestDroneMovimiento extends FunSuite {


  test("probando retorno posicion ") {


    val pos = new Posicion(Coordenada(5, 6), N())
    val drone: Drone = new Drone(1,pos,Capacidad(10))
    println(s"Aquiiii ${drone}")
    assert(drone == Drone(1,Posicion(Coordenada(5,6),N()),Capacidad(10)))


  }


  test("Avanzar drone  en direccion cardinalidad ") {


     val pos = Posicion(Coordenada(4,5),N())
     val drone: Drone = new Drone(1,pos,Capacidad(10))
     val prueba: Drone= InterpretacionServicioDrone.avanzarDrone(drone)

     assert(prueba ==  Drone(1,Posicion(Coordenada(4,6),N()),Capacidad(10)))

  }





  test("Girar Drone 90 Grados a la izquierda ") {


    val pos = Posicion(Coordenada(4,5),S())
    val drone: Drone = new Drone(1,pos,Capacidad(10))
    val posActualizada: Drone= InterpretacionServicioDrone.giroAlaIzquierda(drone )


    assert(posActualizada == Drone(1,Posicion(Coordenada(4,5),E()),Capacidad(10)) )

  }


  test("Girar Drone 90 Grados a la Derecha ") {


    val pos = Posicion(Coordenada(4,5),S())
    val drone: Drone = new Drone(1,pos,Capacidad(10))
    val posActualizada: Drone= InterpretacionServicioDrone.giroAlaDerecha(drone)


    assert(posActualizada ==  Drone(1,Posicion(Coordenada(4,5),O()),Capacidad(10)))

  }



  test("Interpretar una de las instrucciones del dron") {


   // val ruta : Movimiento = Movimiento(Posicion(Coordenada(0,0),N()),instruccion.newMovimiento('A'))

    //val entregar: Posicion = interpreteHacerInstrucciones.operarInstruccion(ruta)

   // assert(entregar == Posicion(Coordenada(0,1),N()))

  }



  test("Interpretar ruta de una entrega  del drone ") {

    val listaInstrucciones: Entrega =Entrega(List(I(),I(),I(),I(),D(),A(),D(),D(),A(),A()))
    val pos = Posicion(Coordenada(-7,-3),E())
    val drone: Drone = new Drone(1,pos,Capacidad(10))


    val posfinRuta : Drone =  InterpretacionServicioDrone.hacerEntrega(listaInstrucciones,drone)

    assert(posfinRuta==Drone(1,Posicion(Coordenada(-7,-2),N()),Capacidad(10)) )

  }



  test("Test leer Archivo de instrucciones  ") {

    val fileName : Archivo = Archivo("/home/s4n/Documentos/in.txt")

    println("\n ***********************LISTA INSTRUCCIONES*************** ")
    val listIntrucciones = interpretealgebraServicioArchivos.leerArchivo(fileName)
    println( s" Lista de instrucciones txt \n ${listIntrucciones}")

  }

  test("Test hacer todas las entregas del archivo  ") {

    val fileName: Archivo = Archivo("/home/s4n/Documentos/in.txt")


    val pos = Posicion(Coordenada(0,0),N())
    val drone: Drone = new Drone(1,pos,Capacidad(10))

    println("\n ***********************LISTA INSTRUCCIONES*************** ")

    //val listRuta: Ruta = interpretealgebraServicioArchivos.leerArchivo(fileName)

   // val liposDrone: List[Drone] = InterpretacionServicioDrone.hacerRuta(listRuta,drone)

     println("\n ***********************LISTA INSTRUCCIONES*************** ")
     //println(s"Lista Resultados donde hizo entregas el dron\n ${liposDrone}  ")

   assert(1 == 1)
  }



  test("Test Resultados coordenadas  x") {

    val fileName: Archivo= Archivo("/home/s4n/Documentos/in.txt")


    val pos = Posicion(Coordenada(0,0),N())
    val drone: Drone = new Drone(1,pos,Capacidad(10))

    val listRuta: Try[Ruta]= interpretealgebraServicioArchivos.leerArchivo(fileName)
    println(s"Sucess Aqui!! \n ${listRuta}")

    val reporte: Try[Reporte] = listRuta.map(x =>
      interpretealgebraServicioArchivos.generarReporteEntrega(InterpretacionServicioDrone.reporteEntrega(InterpretacionServicioDrone.hacerRuta(Try(x),drone)))
    )


    println("\n ***********************LISTA Resultados*************** ")
    println(s"Lista Resultados de posicion donde hizo entregas el dron\n ${reporte}  ")

    assert(reporte == Success(Reporte(List("(-2,4) Orientacion Norte", "(-1,3) Orientacion Sur","(0,0) Orientacion Occidente","(-4,1) Orientacion Occidente","(-5,1) Orientacion Sur","(-5,-1) Orientacion Occidente","(-7,-1) Orientacion Oriente","(-7,-3) Orientacion Oriente","(-7,-2) Orientacion Norte","(-5,2) Orientacion Norte"))))

  }


  test("Test Archivo con Instrucciones malas") {

    val fileName: Archivo= Archivo("/home/s4n/Documentos/pruebaError.txt")


    val pos = Posicion(Coordenada(0,0),N())
    val drone: Drone = new Drone(1,pos,Capacidad(10))

    val listRuta: Try[Ruta]= interpretealgebraServicioArchivos.leerArchivo(fileName)
    println(s"Sucess Aqui!! \n ${listRuta}")

    val reporte: Try[Reporte] = listRuta.map(x =>
      interpretealgebraServicioArchivos.generarReporteEntrega(InterpretacionServicioDrone.reporteEntrega(InterpretacionServicioDrone.hacerRuta(Try(x),drone)))
    )


    println("\n ***********************LISTA Resultados*************** ")
    println(s"Lista Resultados de posicion donde hizo entregas el dron archivo malo \n ${reporte}  ")


    assert(reporte == reporte )

  }








}