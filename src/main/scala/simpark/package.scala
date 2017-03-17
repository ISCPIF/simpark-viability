package simpark

import viabilitree.export._
import viabilitree.viability._
import viabilitree.viability.kernel._

object Parc3DViabilityKernel extends App {

  val parc = Parc3D()
  val rng = new util.Random(42)

  val vk = KernelComputation(
    dynamic = parc.dynamic,
    depth = 12,
    zone = Vector((3000.0, 5000.0), (5000.0, 20000.0), (5000.0, 10000.0)),
    controls = Vector((0.0 to 15000.0 by 1000.0))
    // controls = Vector((5000.0 to 15000.0 by 1000.0),(10.0 to 200.0 by 10.0))
  )

  val (ak, steps) = approximate(vk, rng)

  println(steps)

  saveVTK2D(ak, "/tmp/resparc3D.vtk")

  //println(volume(res))

}