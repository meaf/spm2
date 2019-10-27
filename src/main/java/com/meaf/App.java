package com.meaf;

import com.meaf.calculations.AMxMultiplier;
import com.meaf.calculations.ParallelMxMultiplier;
import com.meaf.calculations.SequentialMxMultiplier;
import com.meaf.structure.Matrix;
import com.meaf.structure.MatrixBuilder;

import java.util.Random;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    new App().start();
  }

  private void start() {
    Integer mxSize = getInputMxSize();
    Random rand = new Random(mxSize);
    Matrix mx1 = createMatrix(mxSize, rand);
    Matrix mx2 = createMatrix(mxSize, rand);

    System.out.println("Performing sequential calculations...");
    AMxMultiplier sequentialMultiplier = new SequentialMxMultiplier();
    long sequentialExecutionStart = System.nanoTime();
    Matrix sResult = sequentialMultiplier.multiply(mx1, mx2);
    long sequentialExecutionEnd = System.nanoTime();

    System.out.println("Performing parallel calculations...");
    AMxMultiplier parallelMultiplier = new ParallelMxMultiplier();
    long parallelExecutionStart = System.nanoTime();
    Matrix pResult = parallelMultiplier.multiply(mx1, mx2);
    long parallelExecutionEnd = System.nanoTime();

    printMatrices(mx1, mx2, sResult, pResult);

    long sequentialDuration = sequentialExecutionEnd - sequentialExecutionStart;
    long parallelDuration = parallelExecutionEnd - parallelExecutionStart;

    System.out.printf("Result for %dx%d matrices multiplication\n", mxSize, mxSize);
    System.out.printf("Sequential execution time, nanoseconds: %15d\n", sequentialDuration);
    System.out.printf("Parallel execution time, nanoseconds:   %15d\n", parallelDuration);
    System.out.printf("Delta (st - pt), nanoseconds:           %15d\n", (sequentialDuration - parallelDuration));
    System.out.printf("Delta(%%):                               %15d\n", (sequentialDuration - parallelDuration)*100/sequentialDuration);
  }

  private void printMatrices(Matrix mx1, Matrix mx2, Matrix sResult, Matrix pResult) {
    System.out.println("M1:");
    System.out.println(mx1.toString());

    System.out.println("M2:");
    System.out.println(mx2.toString());

    System.out.println("Sequential result:");
    System.out.println(sResult);

    System.out.println("Parallel result:");
    System.out.println(pResult);
  }

  private Matrix createMatrix(Integer mxSize, Random rand) {
    return MatrixBuilder
        .getBuilder()
        .withSize(mxSize)
        .withRandomNumGenerator(rand)
        .build();
  }

  private Integer getInputMxSize() {
    Integer result;
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter matrix size [1..10000]:");
    while(true) {
        if(sc.hasNextInt()) {
          result = sc.nextInt();
          if (result > 0 && result <= 10_000)
            return result;
        } else {
          System.err.println("Cannot parse number");
        }
      System.err.println("Matrix size should be an integer in the range [1..10000]");
      sc.nextLine();
    }
  }
}
