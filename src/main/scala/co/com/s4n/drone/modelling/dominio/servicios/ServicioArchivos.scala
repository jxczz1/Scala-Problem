package co.com.s4n.drone.modelling.dominio.servicios

import co.com.s4n.drone.modelling.dominio.entidades.{Instruccion, Orientacion}

import scala.io.Source


sealed trait algebraServicioArchivos {

  def leerArchivo (txtName : String) : List[List[Instruccion]]
}
//Interpretar AlgebraInstrucciones
sealed trait interpretealgebraServicioArchivos extends algebraServicioArchivos {


   def leerArchivo(txtName: String): List[List[Instruccion]] = {

      val listaInstrucciones :List[String] = Source.fromFile(txtName).getLines().toList
      listaInstrucciones.map(x => x.split("").toList.map(y => Instruccion.newInstruccion(y)))
   }

}

// Trait Object
object interpretealgebraServicioArchivos extends interpretealgebraServicioArchivos