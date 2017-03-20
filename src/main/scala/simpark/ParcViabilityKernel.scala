package simpark

import viabilitree.export._
import viabilitree.viability._
import viabilitree.viability.kernel._

object Parc2DViabilityKernel extends App {

  val parc = Parc2D()
  val rng = new util.Random(42)

  val vk = KernelComputation(
    dynamic = parc.dynamic,
    depth = 16,
    zone = Vector((0.0, 5000.0), (0.0, 20000.0)),
    controls = Vector((0.0 to 10000.0 by 1000.0))
   // controls = Vector((5000.0 to 15000.0 by 1000.0),(10.0 to 200.0 by 10.0))
  )

  val (ak, steps) = approximate(vk, rng)

  println(steps)

  saveVTK2D(ak, "/tmp/resparc.vtk")

  //println(volume(res))

}