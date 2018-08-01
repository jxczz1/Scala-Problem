package co.com.s4n.drone.modelling.dominio.servicios

import co.com.s4n.drone.modelling.dominio.entidades._

import scala.io.Source
import java.io._


sealed trait algebraServicioArchivos {

  def leerArchivo(txtName: Archivo): Ruta
  def generarReporteEntrega(reporteEntrega: List[Posicion]): Reporte
}

//Interpretar AlgebraInstrucciones
sealed trait interpretealgebraServicioArchivos extends algebraServicioArchivos {


   def leerArchivo(txtName: Archivo): Ruta = {
     val listaInstrucciones :List[String] = Source.fromFile(txtName.archivo).getLines().toList//
     val a = listaInstrucciones.map(x => x.toList)
     val b = a.map( x => x.map(y=> Instruccion.newInstruccion(y.toString)))
     val c: List[Entrega] = b.map(x=> Entrega(x))
     val ruta = Ruta(c)
     ruta
   }


  def generarReporteEntrega(reporteEntrega: List[Posicion]): Reporte = {

     val listaResultado: List[String] = reporteEntrega.map(x=>
      s"(${x.coordenada.x},${x.coordenada.y}) Orientacion ${x.orientacion.toString}"
     )
    val a: File = new  File("/home/s4n/Documentos/resultado.txt")

    val bw = new BufferedWriter(new FileWriter(a))

    val Resultado: Reporte = Reporte(listaResultado.drop(1))

    Resultado.reporte.foreach( x =>
     bw.write(s"${x}\n")
    )
    bw.close()
   Resultado



  }

}

// Trait Object
object interpretealgebraServicioArchivos extends interpretealgebraServicioArchivos