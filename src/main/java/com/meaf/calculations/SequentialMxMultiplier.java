package com.meaf.calculations;

import com.meaf.structure.Matrix;

public class SequentialMxMultiplier extends AMxMultiplier {

  @Override
  protected void calculate(Matrix mx1, Matrix mx2, Integer size, Matrix resMx) {
    for (int i = 0; i < size; i++)
      for (int j = 0; j < size; j++)
        calculateElement(resMx, mx1, mx2, i, j);
  }

  private void calculateElement(Matrix resMx, Matrix mx1, Matrix mx2, int i, int j) {
    float sum = 0f;
    for (int k = 0; k < mx1.getSize(); k++)
      sum += mx1.getEl(i, k) * mx2.getEl(k, j);
    resMx.setEl(i, j, sum);
  }
}
