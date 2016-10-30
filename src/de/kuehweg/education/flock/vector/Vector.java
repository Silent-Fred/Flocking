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

package de.kuehweg.education.flock.vector;

import java.util.Arrays;

/**
 * @author Michael Kühweg
 */
public class Vector {

	private final double[] coordinates;

	public Vector(final double... coordinates) {
		this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
	}

	public Vector(final Vector vector) {
		this(vector.coordinates);
	}

	public int dimensions() {
		return coordinates.length;
	}

	public double coordinate(final int dimension) {
		return coordinates[dimension];
	}

	public Vector add(final Vector vector) {
		assertSameDimension(vector);
		final Vector result = new Vector(this);
		for (int i = 0; i < coordinates.length; i++) {
			result.coordinates[i] += vector.coordinates[i];
		}
		return result;
	}

	public Vector subtract(final Vector vector) {
		return add(vector.scalarMultiplication(-1.0));
	}

	public Vector scalarMultiplication(final double scalar) {
		final Vector result = new Vector(this);
		for (int i = 0; i < coordinates.length; i++) {
			result.coordinates[i] *= scalar;
		}
		return result;
	}

	public double dotProduct(final Vector vector) {
		assertSameDimension(vector);
		double result = 0.0;
		for (int i = 0; i < coordinates.length; i++) {
			result += coordinates[i] * vector.coordinates[i];
		}
		return result;
	}

	public double length() {
		double sum = 0.0;
		for (final double coordinate : coordinates) {
			sum += coordinate * coordinate;
		}
		return Math.sqrt(sum);
	}

	public double angle(final Vector vector) {
		return Math.acos(dotProduct(vector) / (length() * vector.length()));
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		for (final double coordinate : coordinates) {
			builder.append(builder.length() == 0 ? "(" : ", ");
			builder.append(coordinate);
		}
		return builder.append(")").toString();
	}

	private void assertSameDimension(final Vector vector) {
		if (dimensions() != vector.dimensions()) {
			throw new IllegalArgumentException();
		}
	}
}
