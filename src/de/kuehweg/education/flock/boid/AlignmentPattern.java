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

import de.kuehweg.education.flock.vector.Vector;

/**
 * @author Michael Kühweg
 */
public class AlignmentPattern extends BehaviouralPattern {

	private static final double ALIGNMENT_BIAS = 1.0 / 2.0;

	public AlignmentPattern(final Boid boid) {
		super(boid);
	}

	@Override
	public Vector adaptToInfluencers(final Flock perceivedPartOfFlock) {
		return generalVelocity(perceivedPartOfFlock).subtract(me.velocity()).scalarMultiplication(ALIGNMENT_BIAS);
	}

	private Vector generalVelocity(final Flock flock) {
		// initialise with a zero length n-dimensional vector, where n is
		// derived from the boid
		Vector sum = me.velocity().scalarMultiplication(0.0);
		for (final Boid boid : flock.boids()) {
			sum = sum.add(boid.velocity());
		}
		return flock.boids().isEmpty() ? me.velocity() : sum.scalarMultiplication(1.0 / flock.boids().size());
	}
}
