package com.dou.loggerstarter.util;

import java.util.UUID;

public final class Util {

  private Util() {

  }

  public static String createId() {
    final UUID uuid = UUID.randomUUID();
    return uuid.toString().replace("-", "");
  }
}
