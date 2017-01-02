package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doomin on 02.01.17.
 */
public class Collections {

  public static void main (String[] args) {
    String[] langs = {"Java", "C#","Python","PHP"};

    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");
    languages.add("PHP");



    for (int i = 0; i < langs.length; i++){
      System.out.println("Chce nauczyc sie " + langs[i]);
    }

    for (String l: languages) {
      System.out.println("Chce nauczyc sie " + l);
    }
  }
}
