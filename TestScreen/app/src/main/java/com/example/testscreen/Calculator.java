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

public class Calculator {

  public enum Operator { ADD, SUB, DIV, MUL, POW }

  public double add(double firstOperand, double secondOperand) {
    return firstOperand + secondOperand;
  }

  public double sub(double firstOperand, double secondOperand) {
    return firstOperand - secondOperand;
  }

  public double div(double firstOperand, double secondOperand) {
    if (secondOperand == 0d) {
      throw new IllegalArgumentException();
    } else {
      return firstOperand / secondOperand;
    }
  }

  public double mul(double firstOperand, double secondOperand) {
    return firstOperand * secondOperand;
  }

  public double pow(double firstOperand, double secondOperand) {
    return Math.pow(firstOperand, secondOperand);
  }
}
