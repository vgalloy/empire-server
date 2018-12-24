package com.vgalloy.empire.feature.internal.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class PackageScanner {

  private final List<String> packages = new ArrayList<>();

  /**
   * Add a package to scan.
   *
   * @param packages one or several package
   */
  public void addPackage(final String... packages) {
    this.packages.addAll(Arrays.asList(packages));
  }

  /**
   * The list of package to scan.
   *
   * @return the packages, not null, not empty
   */
  public List<String> getPackage() {
    if (packages.isEmpty()) {
      return new ArrayList<>();
    }
    // TODO immutable
    return packages;
  }
}
