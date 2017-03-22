package simpark

/*
 * Copyright (C) 10/10/13 Isabelle Alvarez
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import viabilitree.model._

case class Parc2D(
                   integrationStep: Double = 0.01,
                   timeStep: Double = 0.1,
                   g: Double = 20.0,
                   s: Double = 1.0,
                   M: Double = 5000.0,
                   h: Double = 0.001,
                   b: Double = 1.0,
                   a: Double = 10.0,
                   c: Double = 1.0,
                   d: Double = 10.0,
                   r: Double = 1.0,
                   K: Double = 100.0,
                   eps: Double = 80,
                   eta: Double = 0.005
                   // valeurs de controle: eps control(0) et d control(1)
                  ) {

  def dynamic(state: Vector[Double], control: Vector[Double]) = {
    // A: state(0), T: state(1), E: state(2)
    def ADot(state: Vector[Double], t: Double) = state(0)*g*(1-state(0)/( 1+M/(1+eta*state(1)/(control(0)+1)) ) )-h*state(1)*state(0)
    // def yDot(state: Array[Double], t: Double) = b*state(1)-r*math.pow(state(1),8)/(pow(m,8)+pow(state(1),8))
    def TDot(state: Vector[Double], t: Double) = state(1) * ( a * state(0)  - c * state(1) - d)
   // state(0) - (b * state(1) - r * math.pow(state(1), 8) / (1 + pow(state(1), 8)))
   // def EDot(state: Vector[Double], t: Double)= state(2) * r * (1 - state(2)/K ) - s * state(1) *state(2) + control(1) * state(1) * (K - state(2))
    val dynamic = Dynamic(ADot, TDot)
    dynamic.integrate(state.toArray, integrationStep, timeStep)
  }

}
