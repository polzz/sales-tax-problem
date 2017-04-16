package org.salestaxes.cli;

import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public class CLI {

  private static Logger logger = LoggerFactory.getLogger(CLI.class);

  private final String[] args;
  private final Options options = new Options();

  public CLI(String[] args) {
    Preconditions.checkArgument(args != null, "input arguments could not be null");
    this.args = args;

    options.addOption("i", "input", true, "input file name");
    options.addOption("h", "help", false, "print usage");
  }

  public Optional<String> parse() {
    final CommandLineParser parser = new DefaultParser();
    final CommandLine cmd;
    try {
      cmd = parser.parse(options, args);

      if (cmd.hasOption("h") || !cmd.hasOption("i")) {
        help();
      } else {
        return Optional.of(cmd.getOptionValue("i"));
      }

    } catch (ParseException e) {
      logger.error("unable to parse command line parameters {}", Arrays.toString(args), e);
      help();
    }
    System.exit(0);
    return Optional.empty();
  }

  private void help() {
    final HelpFormatter formater = new HelpFormatter();
    formater.printHelp("i.e. usage: java -jar app.jar -i <input_file_name>\n"
        + "java -jar app.jar -h will print this message", options);
  }
}
