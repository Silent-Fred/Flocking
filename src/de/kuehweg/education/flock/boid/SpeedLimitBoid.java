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
public class SpeedLimitBoid extends Boid {

	// This is not part of the original description but allows to control the
	// overall speed of the flock easier. Forcing a minimum velocity prevents
	// the flock from resting in one place, e.g. if boids are really meant to
	// "fly", they might fall from the sky when not moving forward..
	private static final double MIN_VELOCITY = 0.5;
	private static final double MAX_VELOCITY = 1.5;

	@Override
	public void accelerateTo(final Vector velocity) {
		if (velocity.length() >= MAX_VELOCITY) {
			super.accelerateTo(velocity.scalarMultiplication(MAX_VELOCITY / velocity.length()));
		} else if (velocity.length() <= MIN_VELOCITY) {
			super.accelerateTo(velocity.scalarMultiplication(MIN_VELOCITY / velocity.length()));
		} else {
			super.accelerateTo(velocity);
		}
	}

}
