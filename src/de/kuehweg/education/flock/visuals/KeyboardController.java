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

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Michael Kühweg
 */
public class KeyboardController implements EventHandler<KeyEvent> {

	private static final double STEP_ON_KEYPRESS = 5.0;

	private static final double ZOOM_ON_KEYPRESS = 1.0;

	private final VisualFlock visualFlock;

	public KeyboardController(final VisualFlock visualFlock) {
		this.visualFlock = visualFlock;
	}

	@Override
	public void handle(final KeyEvent event) {
		translateXIfRequested(event);
		translateYIfRequested(event);
		zoomIfRequested(event);
	}

	private void translateXIfRequested(final KeyEvent event) {
		if (event.getCode() == KeyCode.LEFT) {
			dragThemAll(STEP_ON_KEYPRESS, 0.0);
		} else if (event.getCode() == KeyCode.RIGHT) {
			dragThemAll(-STEP_ON_KEYPRESS, 0.0);
		}
	}

	private void translateYIfRequested(final KeyEvent event) {
		if (event.getCode() == KeyCode.UP) {
			dragThemAll(0.0, STEP_ON_KEYPRESS);
		} else if (event.getCode() == KeyCode.DOWN) {
			dragThemAll(0.0, -STEP_ON_KEYPRESS);
		}
	}

	private void zoomIfRequested(final KeyEvent event) {
		if (event.isMetaDown()) {
			if ("+".equals(event.getCharacter())) {
				visualFlock.setZoomFactor(visualFlock.getZoomFactor() + ZOOM_ON_KEYPRESS);
			} else if ("-".equals(event.getCharacter())) {
				final double smaller = visualFlock.getZoomFactor() - ZOOM_ON_KEYPRESS;
				visualFlock.setZoomFactor(smaller > 0.0 ? smaller : visualFlock.getZoomFactor());
			}
		}
	}

	private void dragThemAll(final double translateX, final double translateY) {
		for (final VisualBoid visualBoid : visualFlock.visualBoids()) {
			visualBoid.translateOffsetTo(visualBoid.offsetX() + translateX, visualBoid.offsetY() + translateY);
			visualBoid.refreshShape();
		}
	}
}
