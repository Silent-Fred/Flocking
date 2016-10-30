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
public abstract class BehaviouralPattern {

	protected final Boid me;

	public BehaviouralPattern(final Boid boid) {
		me = boid;
	}

	public abstract Vector adaptToInfluencers(Flock perceivedPartOfFlock);

	// a "clean" but rather slow solution to always recalculate perception
	public Vector adaptToFlock(final Flock flock) {
		return adaptToInfluencers(perceivedPartOfFlock(flock));
	}

	public Flock perceivedPartOfFlock(final Flock flock) {
		final Flock perceivedFlock = new Flock();
		for (final Boid boid : flock.boids()) {
			if (!me.equals(boid) && me.perceive(boid)) {
				perceivedFlock.addBoid(boid);
			}
		}
		return perceivedFlock;
	}
}
