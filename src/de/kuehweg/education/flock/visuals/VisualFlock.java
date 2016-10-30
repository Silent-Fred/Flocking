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

package de.kuehweg.education.flock.visuals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.kuehweg.education.flock.contract.IBoidVisualisation2D;
import de.kuehweg.education.flock.contract.IFlock;
import javafx.scene.shape.Shape;

/**
 * @author Michael Kühweg
 */
public class VisualFlock {

	private double zoomFactor = 5.0;

	private final IFlock flock;

	private final Set<VisualBoid> visualBoids = new HashSet<>();

	public VisualFlock(final IFlock flock) {
		this.flock = flock;
		initialiseVisualBoids(flock);
	}

	private void initialiseVisualBoids(final IFlock flock) {
		visualBoids.clear();
		for (final IBoidVisualisation2D boid : flock.boidsForVisualisation2D()) {
			visualBoids.add(new VisualBoid(boid));
		}
	}

	public Set<VisualBoid> visualBoids() {
		return new HashSet<>(visualBoids);
	}

	public void move() {
		flock.move();
		refreshShapesOFAllBoidsInFlock();
	}

	public Collection<Shape> shapes() {
		final Collection<Shape> shapes = new ArrayList<>();
		for (final VisualBoid boid : visualBoids()) {
			shapes.add(boid.shape());
		}
		return shapes;
	}

	public double getZoomFactor() {
		return zoomFactor;
	}

	public void setZoomFactor(final double zoomFactor) {
		this.zoomFactor = zoomFactor;
		for (final VisualBoid visualBoid : visualBoids) {
			visualBoid.setZoomFactor(zoomFactor);
		}
		refreshShapesOFAllBoidsInFlock();
	}

	private void refreshShapesOFAllBoidsInFlock() {
		for (final VisualBoid visualBoid : visualBoids) {
			visualBoid.refreshShape();
		}
	}

	public boolean somethingVisible() {
		for (final VisualBoid visualBoid : visualBoids) {
			if (visualBoid.isVisible()) {
				return true;
			}
		}
		return false;
	}

	public boolean somethingOffTop() {
		for (final VisualBoid visualBoid : visualBoids) {
			if (visualBoid.isOffTop()) {
				return true;
			}
		}
		return false;
	}

	public boolean somethingOffRight() {
		for (final VisualBoid visualBoid : visualBoids) {
			if (visualBoid.isOffRight()) {
				return true;
			}
		}
		return false;
	}

	public boolean somethingOffBottom() {
		for (final VisualBoid visualBoid : visualBoids) {
			if (visualBoid.isOffBottom()) {
				return true;
			}
		}
		return false;
	}

	public boolean somethingOffLeft() {
		for (final VisualBoid visualBoid : visualBoids) {
			if (visualBoid.isOffLeft()) {
				return true;
			}
		}
		return false;
	}
}
