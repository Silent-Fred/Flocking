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

package de.kuehweg.education.flock.contract;

import java.util.Random;

import de.kuehweg.education.flock.boid.Boid;
import de.kuehweg.education.flock.boid.Flock;
import de.kuehweg.education.flock.boid.SpeedLimitBoid;
import de.kuehweg.education.flock.vector.Vector;

/**
 * @author Michael Kühweg
 */
public class RandomFlockFactory {

	public static IFlock randomFlock(final int numberOfBoids) {
		final Flock flock = new Flock();
		final Random random = new Random();
		// reproduce pseudo-random results
		// final Random random = new Random(42);
		// Initial velocity is mainly to enforce a general direction across the
		// screen. Boids should not disappear right from the start ;-)
		final Vector initialVelocity = new Vector(0.5, 0.2);
		for (int i = 0; i < numberOfBoids; i++) {
			final Boid boid = new SpeedLimitBoid();
			boid.relocateTo(new Vector(i, random.nextDouble() * numberOfBoids));
			final Vector randomVelocity = initialVelocity
					.add(new Vector(random.nextDouble() / 2.0, random.nextDouble() / 2.0));
			boid.accelerateTo(randomVelocity);
			flock.addBoid(boid);
		}
		return flock;
	}
}
