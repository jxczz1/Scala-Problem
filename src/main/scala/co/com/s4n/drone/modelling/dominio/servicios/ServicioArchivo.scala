package co.com.s4n.drone.modelling.dominio.servicios

import co.com.s4n.drone.modelling.dominio.entidades._
import scala.io.Source
import java.io._


sealed trait ServicioArchivoAlgebra {

  def leerArchivo(txtName: Archivo): Ruta
  def generarReporteEntrega(reporteEntrega: List[Posicion]): Reporte
}

//Interpretar AlgebraServicioArchivos
sealed trait ServicioArchivo extends ServicioArchivoAlgebra {

  //  Hilo Principal



  def leerArchivo(txtName: Archivo): Ruta = {
     val listaInstrucciones :List[String] = Source.fromFile(txtName.archivo).getLines().toList

     val instruccionesSeparadas: List[List[Char]] = listaInstrucciones.map(x => x.toList)
     val ruta: Ruta = Ruta(instruccionesSeparadas.map(x => x.map(y=> Instruccion.newInstruccion(y.toString))).map(z=> Entrega(z)))
    ruta
   }





  def generarReporteEntrega(reporteEntrega: List[Posicion]): Reporte = {

    val listaResultado: List[String]= reporteEntrega.map(x=>
      (s"(${x.coordenada.x},${x.coordenada.y}) Orientacion ${x.orientacion.toString}")
     )
    val a: File = new  File("files/resultado.txt")

    val bw = new BufferedWriter(new FileWriter(a))

    val Resultado: Reporte = Reporte(listaResultado.drop(1))

    Resultado.reporte.map(x=>
        bw.write(x)
    )
     bw.close()
     Resultado
  }

}

// Trait Object
object ServicioArchivo extends ServicioArchivo