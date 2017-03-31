package simpark

import viabilitree.export._
import viabilitree.viability._
import viabilitree.viability.kernel._

object Parc2DViabilityKernel extends App {

  val parc = Parc2D_B()
  val rng = new util.Random(42)

  val vk = KernelComputation(
    dynamic = parc.dynamic,
    depth = 16,
    zone = Vector((3000.0, 5000.0), (4500.0, 6500.0)),
//    controls = Vector((0.00 to 0.1 by 0.01 ),(0.0 to 100.0 by 10.0))
    controls = (x: Vector[Double]) =>
      for {
        c1 <- (0.00 to 0.1 by 0.01 )
        c2 <- (0.0 to 100.0 by 10.0)
      } yield Control(c1, c2)

  )
  val (ak, steps) = approximate(vk, rng)

  println(steps)

  saveVTK2D(ak, s"/tmp/resparcDepth${vk.depth}2controls.vtk")
  saveHyperRectangles(vk)(ak,s"/tmp/resparcWithControlD${vk.depth}.txt")


/*
val pointA=Vector(100.0,3000.0)
  val u2=Vector(0.001)
  val pointB=parc.dynamic(pointA,u2)
  println(pointB)
*/

  //println(volume(res))

  // noyau vide avec    controls = Vector((0.00 to 0.1 by 0.01 ),(0.0 to 100.0 by 10.0)) et    zone = Vector((3000.0, 5000.0), (5000.0, 7500.0)),
}