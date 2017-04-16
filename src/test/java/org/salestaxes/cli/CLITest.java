package org.salestaxes.cli;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.salestaxes.cli.CLI;

@RunWith(value = Parameterized.class)
public class CLITest {

  @Parameter(value = 0)
  public String argA;

  @Parameter(value = 1)
  public String argB;

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {{"-i", "file.txt"}, {"-i", "another_file.txt"}});
  }

  @Test
  public void givenRightInputArgs_whenAreParsedToCLI_thenShouldReturnTheInputFilePath() {

    String actual = new CLI(new String[] {argA, argB}).parse().get();

    assertThat(actual, equalTo(argB));

  }

  @Test(expected = IllegalArgumentException.class)
  public void givenNullInputArgs_whenCreatingCLI_thenShouldThrowException() {
    new CLI(null);

  }

}
