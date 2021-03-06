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
    zone = Vector((1000.0, 3000.0), (3000.0, 3800.0)),
   // controls = Vector((0.02 to 0.4 by 0.02 ))
   controls = (x: Vector[Double]) =>
      for {
        c1 <- (0.02 to 0.04 by 0.02 )
        c2 <- (0.0 to 10.0 by 10.0)
      } yield Control(c1, c2)



  )

  val (ak, steps) = approximate(vk, rng)

  println(steps)


  saveVTK2D(ak, s"/Users/laetitiazaleski/Desktop/results/resparc2_2DDepth${vk.depth}2controls_Touristes.vtk")
  saveHyperRectangles(vk)(ak,s"/Users/laetitiazaleski/Desktop/results/resparc2DBWithControlD${vk.depth}_Touristes.txt")
 // saveHyperRectangles(vk)(ak,s"/tmp/resparcWithControlD${vk.depth}.txt")

  //println(volume(res))

/*

  val pointA=Vector(100.0,3000.0)

  val u2=Vector(0.001)
  val pointB=parc.dynamic(pointA,u2)
  println(pointB)
*/


  // noyau vide avec    controls = Vector((0.00 to 0.1 by 0.01 ),(0.0 to 100.0 by 10.0)) et    zone = Vector((3000.0, 5000.0), (5000.0, 7500.0)),
}