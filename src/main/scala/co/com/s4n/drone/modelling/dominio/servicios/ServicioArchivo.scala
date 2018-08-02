package co.com.s4n.drone.modelling.dominio.servicios

import co.com.s4n.drone.modelling.dominio.entidades._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.io.Source
import java.io._
import java.util.concurrent.Executors

import scala.concurrent.{Future}
import scala.util.Try


sealed trait ServicioArchivoAlgebra {

  def leerArchivo(txtName: Archivo): Try[Ruta]
  def generarReporteEntrega(reporteEntrega: Future[List[Posicion]]): Reporte
}

//Interpretar AlgebraServicioArchivos
sealed trait ServicioArchivo extends ServicioArchivoAlgebra {

  //  Hilo Principal




   def leerArchivo(txtName: Archivo): Try[Ruta] = {
     val listaInstrucciones :List[String] = Source.fromFile(txtName.archivo).getLines().toList

     val instruccionesSeparadas: List[List[Char]] = listaInstrucciones.map(x => x.toList)
     val ruta: Try[Ruta] = Try(Ruta(instruccionesSeparadas.map(x => x.map(y=> Instruccion.newInstruccion(y.toString))).map(z=> Entrega(z))))
     ruta
   }





  def generarReporteEntrega(reporteEntrega: Future[List[Posicion]]): Reporte = {

    val listaResultado: Future[String]= reporteEntrega.map(x=>
      (s"(${x.map(a=> a.coordenada.x)},${x.map(b=> b.coordenada.y)}) Orientacion ${x.map(c=> c.orientacion.toString)}")
     )
    val a: File = new  File("/home/s4n/Documentos/resultado.txt")

    val bw = new BufferedWriter(new FileWriter(a))

    val Resultado: Reporte = Reporte(listaResultado.map(x=> x.drop(1)))

    Resultado.reporte.map(x=>
        bw.write(x)
    )
     bw.close()
     Resultado
  }

}

// Trait Object
object ServicioArchivo extends ServicioArchivo