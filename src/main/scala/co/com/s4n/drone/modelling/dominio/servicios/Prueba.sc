import co.com.s4n.drone.modelling.dominio.entidades._
import co.com.s4n.drone.modelling.dominio.servicios.interpretealgebraServicioArchivos

import scala.io.Source

val listaResultado: List[Posicion] = List(Posicion(Coordenada(0,0),N()), Posicion(Coordenada(-2,4),N()), Posicion(Coordenada(-1,3),S()), Posicion(Coordenada(0,0),O()), Posicion(Coordenada(-4,1),O()), Posicion(Coordenada(-5,1),S()), Posicion(Coordenada(-5,-1),O()), Posicion(Coordenada(-7,-1),E()), Posicion(Coordenada(-7,-3),E()), Posicion(Coordenada(-7,-2),N()), Posicion(Coordenada(-5,2),N()))

val b: Reporte = interpretealgebraServicioArchivos.generarReporteEntrega(listaResultado)