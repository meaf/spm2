package com.meaf.calculations;

import com.meaf.structure.Matrix;
import org.junit.Assert;
import org.junit.Test;

public class SequentialMxMultiplierTest {

  @Test
  public void calcTest(){
    AMxMultiplier sequentialMxMultiplier = new SequentialMxMultiplier();

    Matrix m1 = getM1();
    Matrix m2 = getM2();

    Matrix sequentialRes = sequentialMxMultiplier.multiply(m1, m2);

    Assert.assertEquals(getExpectedResult(), sequentialRes);
  }

  private Matrix getM1(){
    Matrix matrix = new Matrix(2);
    matrix.setEl(0,0 ,  1f);
    matrix.setEl(0,1 ,  2f);
    matrix.setEl(1,0 ,  3f);
    matrix.setEl(1,1 ,  4f);
    return matrix;
  }

  private Matrix getM2() {
    Matrix matrix = new Matrix(2);
    matrix.setEl(0,0 ,  2f);
    matrix.setEl(0,1 ,  0f);
    matrix.setEl(1,0 ,  1f);
    matrix.setEl(1,1 ,  2f);
    return matrix;
  }

  private Matrix getExpectedResult() {
    Matrix matrix = new Matrix(2);
    matrix.setEl(0,0 ,  4f);
    matrix.setEl(0,1 ,  4f);
    matrix.setEl(1,0 ,  10f);
    matrix.setEl(1,1 ,  8f);
    return matrix;
  }

  
}
