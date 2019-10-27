package com.meaf.calculations;

import com.meaf.structure.Matrix;

public abstract class AMxMultiplier {
  public final Matrix multiply(final Matrix mx1, final Matrix mx2) {
    Integer size = getSize(mx1, mx2);
    Matrix resMx = new Matrix(size);
    calculate(mx1, mx2, size, resMx);
    return resMx;
  }

  private Integer getSize(Matrix mx1, Matrix mx2) {
    Integer size = mx1.getSize();
    if (!size.equals(mx2.getSize()))
      throw new RuntimeException("Matrix sizes does not match, exiting...");

    return size;
  }

  protected abstract void calculate(Matrix mx1, Matrix mx2, Integer size, Matrix resMx);
}
