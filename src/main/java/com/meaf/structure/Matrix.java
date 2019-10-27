package com.meaf.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Matrix {
  private final ArrayList<ArrayList<Float>> content;

  public Matrix(Integer size) {
    content = new ArrayList<>(size);
    for(int i=0; i<size; i++){
      content.add(new ArrayList<>(Collections.<Float>nCopies(size, null)));
    }
  }

  public Float getEl(int i, int j) {
    return content.get(j).get(i);
  }

  public void setEl(int i, int j, Float num){
    content.get(j).set(i, num);
  }

  public Integer getSize(){
    return content.size();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(String.format("Matrix (size=%s) content:", getSize()));
    for (List row : content) {
      sb.append('\n');
      sb.append(row.toString());
    }
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Matrix matrix = (Matrix) o;
    return Objects.equals(content, matrix.content);
  }
}
