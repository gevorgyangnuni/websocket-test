package com.zamyshlyaev.websockettest.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class StringUtilsTest {

  @Test
  public void reverseTest() {
    String reverseText = StringUtils.reverse("123456789");
    Assert.assertEquals(reverseText, "987654321");

    reverseText = StringUtils.reverse("aaaabcccc");
    Assert.assertEquals(reverseText, "ccccbaaaa");
  }

  @Test
  public void isEmptyTest() {
    boolean isEmpty = StringUtils.isEmpty(null);
    Assert.assertTrue(isEmpty);

    isEmpty = StringUtils.isEmpty("   ");
    Assert.assertTrue(isEmpty);

    isEmpty = StringUtils.isEmpty("\n\t");
    Assert.assertTrue(isEmpty);

    isEmpty = StringUtils.isEmpty(".");
    Assert.assertFalse(isEmpty);
  }
}
