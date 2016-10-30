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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.kuehweg.education.flock.vector.Vector;

/**
 * @author michael Kühweg
 */
public class SeparationPatternTest {

	@Test
	public void testSeparation() {
		final Boid b1 = new Boid();
		b1.relocateTo(new Vector(0, 0, 0));
		b1.accelerateTo(new Vector(1, 0, 0));

		final Boid separationBoid = new Boid();
		separationBoid.relocateTo(new Vector(0.5, 0.5, 0));
		separationBoid.accelerateTo(new Vector(1, 0, 0));

		final Flock flock = new Flock();
		flock.addBoid(b1);
		flock.addBoid(separationBoid);

		final double initialDistance = separationBoid.location().subtract(b1.location()).length();
		final SeparationPattern separation = new SeparationPattern(b1);
		b1.accelerateTo(b1.velocity().add(separation.adaptToFlock(flock)));
		b1.move();
		assertTrue(initialDistance < separationBoid.location().subtract(b1.location()).length());
	}

	@Test
	public void testNoSeparation() {
		final Boid b1 = new Boid();
		b1.relocateTo(new Vector(0, 0, 0));
		b1.accelerateTo(new Vector(1, 0, 0));

		final Boid farAwayBoid = new Boid();
		farAwayBoid.relocateTo(new Vector(10, 10, 0));
		farAwayBoid.accelerateTo(new Vector(1, 0, 0));

		final Flock flock = new Flock();
		flock.addBoid(b1);
		flock.addBoid(farAwayBoid);

		final SeparationPattern separation = new SeparationPattern(b1);
		assertEquals(0.0, separation.adaptToFlock(flock).length(), 0.00005);
	}

}
