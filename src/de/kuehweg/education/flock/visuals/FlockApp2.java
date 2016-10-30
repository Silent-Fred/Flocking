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
import de.kuehweg.education.flock.contract.IFlock;
import de.kuehweg.education.flock.contract.RandomFlockFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Michael Kühweg
 */
public class FlockApp2 extends Application {

	private static final int ANIMATION_MILLISECONDS = 100;

	private static final int SIZE_OF_FLOCK = 50;

	private Group root;

	private Timeline myTimeline;

	private IFlock flock;

	@Override
	public void start(final Stage primaryStage) {
		setUpFlock();
		root = new Group();
		primaryStage.setTitle("The Flock");
		primaryStage.setScene(createAndSetUpScene());
		primaryStage.show();
		root.getChildren().add(paint());
		animate();
	}

	public static void main(final String[] args) {
		launch(args);
	}

	private Scene createAndSetUpScene() {
		final Rectangle2D size = calculateSizeDependingOnScreenSize();
		final Scene scene = new Scene(root, size.getWidth(), size.getHeight());
		return scene;
	}

	private ImageView paint() {
		final Rectangle2D size = calculateSizeDependingOnScreenSize();
		final WritableImage image = new WritableImage((int) size.getWidth(), (int) size.getHeight());
		final PixelWriter writer = image.getPixelWriter();
		for (final IBoidVisualisation2D boid : flock.boidsForVisualisation2D()) {
			if (isOnImage(boid, image)) {
				writer.setColor((int) boid.getXCoordinate(), (int) boid.getYCoordinate(), Color.DARKCYAN);
			}
		}
		return new ImageView(image);
	}

	private boolean isOnImage(final IBoidVisualisation2D boid, final Image image) {
		return boid.getXCoordinate() >= 0 && boid.getYCoordinate() >= 0 && boid.getXCoordinate() < image.getWidth()
				&& boid.getYCoordinate() < image.getHeight();
	}

	private void setUpFlock() {
		flock = RandomFlockFactory.randomFlock(SIZE_OF_FLOCK);
	}

	private void move() {
		flock.move();
		root.getChildren().clear();
		root.getChildren().add(paint());
	}

	private void animate() {
		myTimeline = new Timeline(new KeyFrame(Duration.millis(ANIMATION_MILLISECONDS), event -> move()));
		// myTimeline.setCycleCount(200);
		myTimeline.setCycleCount(Timeline.INDEFINITE);
		myTimeline.play();
	}

	private Rectangle2D calculateSizeDependingOnScreenSize() {
		return calculateSizeDependingOnScreenSize(Screen.getPrimary().getVisualBounds());
	}

	private Rectangle2D calculateSizeDependingOnScreenSize(final Rectangle2D bounds) {

		final double horizontalMargin = calculateHorizontalMargin(bounds);
		final double verticalMargin = calculateVerticalMargin(bounds);

		return new Rectangle2D(bounds.getMinX() + horizontalMargin / 2, bounds.getMinY() + verticalMargin,
				bounds.getWidth() - horizontalMargin, bounds.getHeight() - verticalMargin);
	}

	private double calculateHorizontalMargin(final Rectangle2D bounds) {
		final double preferredMargin = 64;
		if (preferredMargin * 2 >= bounds.getWidth()) {
			return 10;
		}
		return preferredMargin;
	}

	private double calculateVerticalMargin(final Rectangle2D bounds) {
		final double preferredMargin = 64;
		if (preferredMargin * 2 >= bounds.getWidth()) {
			return 10;
		}
		return preferredMargin;
	}
}