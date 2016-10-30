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
public class SeparationPattern extends BehaviouralPattern {

	private static final double RANGE_OF_SEPARATION = 4.0;
	private static final double SEPARATION_WEIGHT = 1.0 / 10.0;

	public SeparationPattern(final Boid boid) {
		super(boid);
	}

	@Override
	public Vector adaptToInfluencers(final Flock perceivedPartOfFlock) {
		// initialise with a zero length n-dimensional vector, where n is
		// derived from the boid
		Vector separation = me.location().scalarMultiplication(0.0);
		for (final Boid boid : perceivedPartOfFlock.boids()) {
			if (tooClose(boid)) {
				separation = separation.add(separation(boid));
			}
		}
		return separation;
	}

	private boolean tooClose(final Boid you) {
		return you.location().subtract(me.location()).length() <= RANGE_OF_SEPARATION;
	}

	private Vector separation(final Boid you) {
		return you.location().subtract(me.location()).scalarMultiplication(-SEPARATION_WEIGHT);
	}
}
