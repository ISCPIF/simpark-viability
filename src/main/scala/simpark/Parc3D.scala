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

case class Parc3D(
                   integrationStep: Double = 0.01,
                   timeStep: Double = 0.1,
                   g: Double = 20.0,
                   s: Double = 1.0,
                   M: Double = 5000.0,
                   h: Double = 0.001,
                   b: Double = 1.0,
                   a: Double = 8.0,
                   c: Double = 0.5,
                   r: Double = 1.0,
                   K: Double = 100.0,
                   eps: Double = 10
                   // valeurs de controle: d control(0) et epsilon control(1)
                 ) {

  def dynamic(state: Vector[Double], control: Vector[Double]) = {
    // A: state(0), T: state(1), E: state(2)
    def ADot(state: Vector[Double], t: Double) = state(0) * g * (1 - state(0)/(1+ M * state(2))) - h * state(1) * state(0)

    def TDot(state: Vector[Double], t: Double) = state(1) * ( a * state(0) + b * state(2) - c * state(1) - control(0))

    def EDot(state: Vector[Double], t: Double)= state(2) * r * (1 - state(2)/K ) - s * state(1) *state(2) + eps * state(1) * (K - state(2))
    val dynamic = Dynamic(ADot, TDot, EDot)
    dynamic.integrate(state.toArray, integrationStep, timeStep)
  }

}
