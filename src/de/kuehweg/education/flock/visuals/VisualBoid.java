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

import de.kuehweg.education.flock.contract.IBoidVisualisation2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * @author Michael Kühweg
 */
public class VisualBoid {

	private double zoomFactor = 5.0;

	private final IBoidVisualisation2D boid;

	private final Shape shape;

	private double offsetX = 0.0;
	private double offsetY = 0.0;

	public VisualBoid(final IBoidVisualisation2D boid) {
		this.boid = boid;
		shape = basicBoidShape();
		refreshShape();
	}

	public Shape shape() {
		return shape;
	}

	public void refreshShape() {
		shape.setScaleX(zoomFactor);
		shape.setScaleY(zoomFactor);
		shape.setScaleZ(zoomFactor);
		shape.setTranslateX((offsetX + boid.getXCoordinate()) * zoomFactor);
		shape.setTranslateY((offsetY + boid.getYCoordinate()) * zoomFactor);
		shape.setRotate(boid.getAngleInDegrees());
	}

	public void setZoomFactor(final double zoomFactor) {
		this.zoomFactor = zoomFactor;
		refreshShape();
	}

	public double getZoomFactor() {
		return zoomFactor;
	}

	public void translateOffsetTo(final double offsetX, final double offsetY) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	public double offsetX() {
		return offsetX;
	}

	public double offsetY() {
		return offsetY;
	}

	public boolean isVisible() {
		final Scene scene = shape.getScene();
		if (scene != null) {
			return shape.getBoundsInParent().intersects(scene.getX(), scene.getY(), scene.getWidth(),
					scene.getHeight());
		}
		return false;
	}

	public boolean isOffLeft() {
		return shape.getBoundsInParent().getMaxX() < 0;
	}

	public boolean isOffRight() {
		if (shape.getScene() != null) {
			return shape.getBoundsInParent().getMinX() > shape.getScene().getWidth();
		}
		return false;
	}

	public boolean isOffTop() {
		return shape.getBoundsInParent().getMaxY() < 0;
	}

	public boolean isOffBottom() {
		if (shape.getScene() != null) {
			return shape.getBoundsInParent().getMinY() > shape.getScene().getHeight();
		}
		return false;
	}

	private Shape basicBoidShape() {
		final Polygon polygon = new Polygon();
		polygon.getPoints().addAll(new Double[] { 0.0, 0.0, -0.3, -0.1, -0.25, 0.0, -0.3, 0.1 });
		polygon.setStroke(Color.DARKCYAN);
		polygon.setFill(Color.DARKCYAN);
		return polygon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (boid == null ? 0 : boid.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final VisualBoid other = (VisualBoid) obj;
		if (boid == null) {
			if (other.boid != null) {
				return false;
			}
		} else if (!boid.equals(other.boid)) {
			return false;
		}
		return true;
	}
}
