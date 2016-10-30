/*
 * Copyright (c) 2016, Michael Kühweg
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package de.kuehweg.education.flock.boid;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.kuehweg.education.flock.boid.Boid;
import de.kuehweg.education.flock.boid.CohesionPattern;
import de.kuehweg.education.flock.boid.Flock;
import de.kuehweg.education.flock.vector.Vector;

/**
 * @author Michael Kühweg
 */
public class CohesionPatternTest {

	@Test
	public void testCohesion() {
		final Boid b1 = new Boid();
		b1.relocateTo(new Vector(0, 0, 0));
		b1.accelerateTo(new Vector(-0.5, 0.5, 0));

		final Boid cohesionBoid = new Boid();
		cohesionBoid.relocateTo(new Vector(10, 10, 0));
		cohesionBoid.accelerateTo(new Vector(1, 1, 0));

		final Flock flock = new Flock();
		flock.addBoid(b1);
		flock.addBoid(cohesionBoid);

		final double initialDistance = cohesionBoid.location().subtract(b1.location()).length();
		final CohesionPattern cohesion = new CohesionPattern(b1);
		// depending on initial velocity, b1 might move away from the center for
		// a few steps, before approaching it
		for (int i = 0; i < 3; i++) {
			b1.accelerateTo(b1.velocity().add(cohesion.adaptToFlock(flock)));
			b1.move();
			// with the given values, b1 immediately approaches the center
			assertTrue(initialDistance > cohesionBoid.location().subtract(b1.location()).length());
		}
		assertTrue(initialDistance > cohesionBoid.location().subtract(b1.location()).length());
	}

}
