package com.meaf.structure;

import java.util.Random;

public class MatrixBuilder {

  public static ISize getBuilder() {
    return new Builder();
  }

  public static class Builder implements ISize, IRandom, IFill {
    private Integer size;
    private Random random;

    private Builder() {
    }

    @Override
    public IRandom withSize(Integer size) {
      this.size = size;
      return this;
    }

    @Override
    public IFill withRandomNumGenerator(Random randomGenerator) {
      this.random = randomGenerator;
      return this;
    }

    @Override
    public Matrix build() {
      Matrix matrix = new Matrix(size);

      for (int j = 0; j < size; j++)
        for (int i = 0; i < size; i++)
          matrix.setEl(i, j, generateFloat());

      return matrix;
    }

    private Float generateFloat() {
      return random.nextFloat();
    }
  }

  public interface ISize {
    IRandom withSize(Integer size);
  }

  public interface IRandom {
    IFill withRandomNumGenerator(Random randomGenerator);
  }

  public interface IFill {
    Matrix build();
  }

}
