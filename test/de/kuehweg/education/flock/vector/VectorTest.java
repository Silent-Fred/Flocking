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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.kuehweg.education.flock.vector.Vector;

/**
 * @author Michael Kühweg
 */
public class VectorTest {

	@Test
	public void testCreation() {
		final Vector v1 = new Vector(1, 2);
		assertEquals(1, Math.round(v1.coordinate(0)));
		assertEquals(2, Math.round(v1.coordinate(1)));
		assertEquals(2, v1.dimensions());

		final Vector v2 = new Vector(v1);
		assertEquals(1, Math.round(v2.coordinate(0)));
		assertEquals(2, Math.round(v2.coordinate(1)));
		assertEquals(2, v2.dimensions());

		final Vector v3 = new Vector(1, 2, 3);
		assertEquals(1, Math.round(v3.coordinate(0)));
		assertEquals(2, Math.round(v3.coordinate(1)));
		assertEquals(3, Math.round(v3.coordinate(2)));
		assertEquals(3, v3.dimensions());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testCreationAndDimensions() {
		final Vector v1 = new Vector(1, 2);
		v1.coordinate(2);
	}

	@Test
	public void testAddition() {
		final Vector v1 = new Vector(0, 0);
		final Vector v2 = new Vector(1, 2);
		final Vector v3 = new Vector(2, 1);

		assertEquals(1, Math.round(v1.add(v2).coordinate(0)));
		assertEquals(2, Math.round(v1.add(v2).coordinate(1)));

		assertEquals(3, Math.round(v1.add(v2).add(v3).coordinate(0)));
		assertEquals(3, Math.round(v1.add(v2).add(v3).coordinate(1)));

		// immutable
		assertEquals(0, Math.round(v1.coordinate(0)));
		assertEquals(0, Math.round(v1.coordinate(1)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdditionDifferentDimensions() {
		new Vector(0, 0).add(new Vector(1, 2, 3));
	}

	@Test
	public void testSubtraction() {
		final Vector v1 = new Vector(0, 0);
		final Vector v2 = new Vector(1, 2);
		final Vector v3 = new Vector(2, 1);

		assertEquals(-1, Math.round(v1.subtract(v2).coordinate(0)));
		assertEquals(-2, Math.round(v1.subtract(v2).coordinate(1)));

		assertEquals(-3, Math.round(v1.subtract(v2).subtract(v3).coordinate(0)));
		assertEquals(-3, Math.round(v1.subtract(v2).subtract(v3).coordinate(1)));

		// immutable
		assertEquals(0, Math.round(v1.coordinate(0)));
		assertEquals(0, Math.round(v1.coordinate(1)));
	}

	@Test
	public void scalarMultiplication() {
		final Vector v1 = new Vector(0, 0);
		final Vector v2 = new Vector(1, 2);

		assertEquals(0, Math.round(v1.scalarMultiplication(-1).coordinate(0)));
		assertEquals(0, Math.round(v1.scalarMultiplication(-1).coordinate(1)));

		assertEquals(-1, Math.round(v2.scalarMultiplication(-1).coordinate(0)));
		assertEquals(-2, Math.round(v2.scalarMultiplication(-1).coordinate(1)));

		// immutable
		assertEquals(0, Math.round(v1.coordinate(0)));
		assertEquals(0, Math.round(v1.coordinate(1)));
	}

	@Test
	public void dotProduct() {
		final Vector v1 = new Vector(1.7, 2.3);
		final Vector v2 = new Vector(3.1, 4.8);

		assertEquals(Math.round(1.7 * 3.1 + 2.3 * 4.8), Math.round(v1.dotProduct(v2)));

		// immutable
		assertEquals(2, Math.round(v1.coordinate(0)));
		assertEquals(2, Math.round(v1.coordinate(1)));
	}

	@Test
	public void length() {
		assertEquals(0, Math.round(new Vector(0, 0).length()));
		assertEquals(5, Math.round(new Vector(3, 4).length()));
		assertEquals(5, Math.round(new Vector(-3, 4).length()));
	}

	@Test
	public void angle() {
		assertEquals(0, Math.round(new Vector(1, 0).angle(new Vector(1, 0))));
		assertTrue(aboutTheSameValue(Math.PI / 4, new Vector(1, 0).angle(new Vector(1, 1)), 4));
		assertTrue(aboutTheSameValue(Math.PI, new Vector(1, 0).angle(new Vector(-1, 0)), 4));
		assertTrue(aboutTheSameValue(3 * Math.PI / 4, new Vector(1, 0).angle(new Vector(-1, -1)), 4));
		assertTrue(aboutTheSameValue(Math.PI / 4, new Vector(1, 0).angle(new Vector(1, -1)), 4));
	}

	private boolean aboutTheSameValue(final double value1, final double value2, final int precision) {
		return Math.round(value1 * Math.pow(10, precision)) == Math.round(value2 * Math.pow(10, precision));
	}

}
