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

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * @author Michael Kühweg
 */
public enum OffscreenBoidsHint {

	TOP {
		@Override
		public Shape shape(final Scene scene) {
			final Polygon hint = hintBasicShape();
			hint.setTranslateX(scene.getWidth() / 2);
			return hint;
		}
	},
	RIGHT {
		@Override
		public Shape shape(final Scene scene) {
			final Polygon hint = hintBasicShape();
			hint.setRotate(90);
			hint.setTranslateX(scene.getWidth() - 10);
			hint.setTranslateY(scene.getHeight() / 2);
			return hint;
		}
	},
	BOTTOM {
		@Override
		public Shape shape(final Scene scene) {
			final Polygon hint = hintBasicShape();
			hint.setRotate(180);
			hint.setTranslateX(scene.getWidth() / 2);
			hint.setTranslateY(scene.getHeight() - 10);
			return hint;
		}
	},
	LEFT {
		@Override
		public Shape shape(final Scene scene) {
			final Polygon hint = hintBasicShape();
			hint.setRotate(-90);
			hint.setTranslateX(10);
			hint.setTranslateY(scene.getHeight() / 2);
			return hint;
		}
	};

	public abstract Shape shape(final Scene scene);

	private static Polygon hintBasicShape() {
		final Polygon polygon = new Polygon();
		polygon.getPoints().addAll(new Double[] { 0.0, 0.0, -20.0, 10.0, 20.0, 10.0 });
		polygon.setStroke(Color.DARKCYAN);
		polygon.setFill(Color.DARKCYAN);
		polygon.setOpacity(0.5);
		return polygon;
	}

}
