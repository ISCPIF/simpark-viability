package simpark

import viabilitree.export._
import viabilitree.viability._
import viabilitree.viability.kernel._

object Parc3DViabilityKernel extends App {

  val parc = Parc3D()
  val rng = new util.Random(42)

  val vk = KernelComputation(
    dynamic = parc.dynamic,
    depth = 18,
    zone = Vector((20000.0, 24000.0), (1.0, 10000.0), (1.0, 5000.0)),
    controls = (x: Vector[Double]) =>
      for {
        zeta <- (0.01 to 0.02 by 0.01 )
        eps <- (0.0 to 100.0 by 10.0)
      } yield Control(zeta, eps)

  )

  val (ak, steps) = approximate(vk, rng)



  saveVTK3D(ak, s"/Users/laetitiazaleski/Desktop/results/resparc3CATDepth${vk.depth}2controls_2.vtk")
  saveHyperRectangles(vk)(ak,s"/Users/laetitiazaleski/Desktop/results/rescat5WithControlD${vk.depth}.txt")
//  saveHyperRectangles(vk)(ak,s"/tmp/rescatWithControlD${vk.depth}.txt")
//  saveHyperRectangles(vk)(ak,s"/tmp/resparcCATWithControlD${vk.depth}.txt")

  println(steps)
  //println(volume(res))

}