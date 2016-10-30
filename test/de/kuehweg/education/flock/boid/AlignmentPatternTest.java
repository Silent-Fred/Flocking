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

import de.kuehweg.education.flock.boid.AlignmentPattern;
import de.kuehweg.education.flock.boid.Boid;
import de.kuehweg.education.flock.boid.Flock;
import de.kuehweg.education.flock.vector.Vector;

/**
 * @author Michael Kühweg
 */
public class AlignmentPatternTest {

	@Test
	public void testAlignment() {
		final Boid b1 = new Boid();
		b1.relocateTo(new Vector(0, 0, 0));
		b1.accelerateTo(new Vector(-0.5, 0.5, 0));

		final Boid alignToThisBoid = new Boid();
		alignToThisBoid.relocateTo(new Vector(1, 1, 0));
		alignToThisBoid.accelerateTo(new Vector(1, 1, 0));

		final Flock flock = new Flock();
		flock.addBoid(b1);
		flock.addBoid(alignToThisBoid);

		final double initialAngle = b1.velocity().angle(alignToThisBoid.velocity());
		final AlignmentPattern alignment = new AlignmentPattern(b1);
		for (int i = 0; i < 4; i++) {
			final Vector correction = alignment.adaptToFlock(flock);
			b1.accelerateTo(b1.velocity().add(correction));
			assertTrue(initialAngle > b1.velocity().angle(alignToThisBoid.velocity()));
		}
	}

}
