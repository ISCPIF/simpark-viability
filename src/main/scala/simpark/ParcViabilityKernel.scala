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
    zone = Vector((4000.0, 5000.0), (8000.0, 10000.0)),
    controls = Vector((0.0 to 0.1 by 0.01))
  )
  val (ak, steps) = approximate(vk, rng)

  println(steps)

  saveVTK2D(ak, "/tmp/resparc2.vtk")


val pointA=Vector(100.0,3000.0)
  val u2=Vector(0.001)
  val pointB=parc.dynamic(pointA,u2)
  println(pointB)

  //println(volume(res))

}