/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.testscreen;

import androidx.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
@SmallTest
public class CalculatorTest {

  private Calculator calculator;

  @Before
  public void setUp() {
      calculator = new Calculator();
  }

  @Test
  public void add() {
    assertThat(calculator.add(1d, 1d), is(equalTo(2d)));
  }

  @Test
  public void addNegative() {
    assertThat(calculator.add(-1d, 2d), is(equalTo(1d)));
  }

  @Test
  public void addFloats() {
    assertThat(calculator.add(1.111f, 1.111d), is(closeTo(2.222, 0.01)));
  }

  @Test
  public void addLarge() {
    assertThat(calculator.add(123456789123d, 123456789123d), is(equalTo(246913578246d)));
  }

  @Test
  public void addDifferentTypeOperands() {
    assertThat(calculator.add(1, 1.0), is(equalTo(2.0)));
  }

  @Test
  public void addZero() {
    assertThat(calculator.add(1d, 0d), is(equalTo(1d)));
  }

  @Test
  public void addInfinity() {
    assertThat(calculator.add(1d, Double.POSITIVE_INFINITY), is(equalTo(Double.POSITIVE_INFINITY)));
  }

  @Test
  public void sub() {
    assertThat(calculator.sub(1d, 1d), is(equalTo(0d)));
  }

  @Test
  public void subNegative() {
    assertThat(calculator.sub(3d, 5d), is(equalTo(-2d)));
  }

  @Test
  public void mul() {
    assertThat(calculator.mul(5d, 3d), is(equalTo(15d)));
  }

  @Test
  public void mulZero() {
    assertThat(calculator.mul(5d, 0d), is(equalTo(0d)));
  }

  @Test
  public void div() {
    assertThat(calculator.div(15d, 3d), is(equalTo(5d)));
  }

  //@Test
  //public void divZero() {
  //  assertThat(calculator.div(5d, 0d), is(equalTo(Double.POSITIVE_INFINITY)));
  //}

  @Test
  public void pow() {
    assertThat(calculator.pow(5, 4), is(equalTo(625d)));
  }

  @Test
  public void powNegativeFirst() {
    assertThat(calculator.pow(-5, 3), is(equalTo(-125d)));
  }

  @Test
  public void powNegativeSecond() {
    assertThat(calculator.pow(5, -4), is(equalTo(1/625d)));
  }

  @Test
  public void powZeroFirst() {
    assertThat(calculator.pow(0, 4), is(equalTo(0d)));
  }

  @Test
  public void powZeroSecond() {
    assertThat(calculator.pow(5, 0), is(equalTo(1d)));
  }

  @Test
  public void powZeroNegativeOne() {
    assertThat(calculator.pow(0, -1), is(equalTo(Double.POSITIVE_INFINITY)));
  }

  @Test
  public void powNegativeZeroNegative() {
    assertThat(calculator.pow(-0, -4), is(equalTo(Double.POSITIVE_INFINITY)));
  }
}
