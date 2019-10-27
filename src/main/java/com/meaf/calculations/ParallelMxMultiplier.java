package com.meaf.calculations;

import com.meaf.structure.Matrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelMxMultiplier extends AMxMultiplier {
  private ExecutorService executorService;

  public ParallelMxMultiplier() {
    super();
    this.executorService = Executors.newCachedThreadPool();
  }

  @Override
  protected void calculate(Matrix mx1, Matrix mx2, Integer size, Matrix resMx) {
    try {
      for (int i = 0; i < size; i++)
        for (int j = 0; j < size; j++)
          calculateElement(resMx, mx1, mx2, i, j);
    } catch (Exception e) {
      throw new RuntimeException("Calculation error, exiting...", e);
    } finally {
      executorService.shutdown();
    }
  }

  private void calculateElement(final Matrix resMx, final Matrix mx1, final Matrix mx2, final int i, final int j) {
    executorService.submit(new Runnable(){
      public void run() {
        float sum = 0f;
        for (int k = 0; k < mx1.getSize(); k++)
          sum += mx1.getEl(i, k) * mx2.getEl(k, j);
        resMx.setEl(i, j, sum);
      }
    });
  }

}
