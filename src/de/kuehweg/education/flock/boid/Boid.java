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

import de.kuehweg.education.flock.contract.IBoidVisualisation2D;
import de.kuehweg.education.flock.vector.Vector;

/**
 * @author Michael Kühweg
 */
public class Boid implements IBoidVisualisation2D {

	private static final double ANGLE_OF_VISION = 3.0 * Math.PI / 4.0;

	// depending on values for RANGE_OF_VISION, a tendency for splitting the
	// flock can emerge or a tendency to find a more or less static place for
	// the flock, when all boids are more concerned about separation and
	// cohesion than a general direction
	private static final double RANGE_OF_VISION = 15.0;

	private Vector location;
	private Vector velocity;
	private Vector nextStep;

	public Vector location() {
		return location;
	}

	public void relocateTo(final Vector location) {
		this.location = location;
	}

	public Vector velocity() {
		return velocity;
	}

	public void accelerateTo(final Vector velocity) {
		this.velocity = velocity;
	}

	public void move() {
		location = location.add(velocity);
	}

	public boolean perceive(final Boid other) {
		final double angle = velocity.angle(other.location.subtract(location));
		final double distance = Math.abs(other.location.subtract(location).length());
		return angle <= ANGLE_OF_VISION && distance <= RANGE_OF_VISION;
	}

	public void thinkAboutNextStep(final Flock flock) {
		final Flock perceivedPartOfFlock = new SeparationPattern(this).perceivedPartOfFlock(flock);
		nextStep = nullsafeVectorAddition(new SeparationPattern(this).adaptToInfluencers(perceivedPartOfFlock),
				new CohesionPattern(this).adaptToInfluencers(perceivedPartOfFlock),
				new AlignmentPattern(this).adaptToInfluencers(perceivedPartOfFlock));

		// It is the usual dilemma of "premature optimization" - do we really
		// have to care about performance?
		// This way would perhaps be a little bit "cleaner", but it requires to
		// reiterate the calculation of the perceived part of the flock.
		// nextStep = nullsafeVectorAddition(new
		// SeparationPattern(this).adaptToFlock(flock),
		// new CohesionPattern(this).adaptToFlock(flock), new
		// AlignmentPattern(this).adaptToFlock(flock));
	}

	public void makeNextStep() {
		accelerateTo(nullsafeVectorAddition(velocity, nextStep));
		move();
	}

	private Vector nullsafeVectorAddition(final Vector... vectors) {
		Vector result = null;
		for (final Vector vector : vectors) {
			if (result == null) {
				result = vector;
			} else if (vector != null) {
				result = result.add(vector);
			}
		}
		return result;
	}

	@Override
	public double getXCoordinate() {
		return location.coordinate(0);
	}

	@Override
	public double getYCoordinate() {
		return location.coordinate(1);
	}

	@Override
	public double getAngleInDegrees() {
		return Math.signum(velocity().coordinate(1)) * Math.toDegrees(velocity().angle(new Vector(1, 0)));
	}

}
