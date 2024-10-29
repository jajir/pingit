package jirout.pingit;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    private final static Option OPTION_HELP = new Option("h", "help", false,
            "display help");
    private final static Option OPTION_HOST = new Option(null, "host", true,
            "required host name, e.g. google.com");
    private final static Option OPTION_COUNT = Option//
            .builder("c")//
            .longOpt("count")//
            .desc("How many times should be ping performed. "
                    + "When this option is not used, than default value 10 is used. "
                    + "If there is no count argument than program will run until interrupted")//
            .hasArg(true)//
            .optionalArg(true)//
            .build();

    private final static Options options = new Options()//
            .addOption(OPTION_HELP)//
            .addOption(OPTION_COUNT)//
            .addOption(OPTION_HOST);

    public static void main(final String[] args) throws Exception {
        try {
            parseAndExecute(args);
        } catch (ParseException e) {
            System.out.println("unable to parse: " + e.getMessage());
        }
    }

    private static void parseAndExecute(final String[] args)
            throws ParseException {
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption(OPTION_HELP)) {
            printHelp();
        } else {
            int count = parseCount(cmd);
            if (cmd.hasOption(OPTION_HOST)) {
                final String hostName = cmd.getOptionValue(OPTION_HOST);
                Ping ping = new Ping();
                for (int i = 0; i < count; i++) {
                    ping.pingHost(hostName);
                }
            } else {
                System.out.println(
                        "option host is required, for more information try --help");
            }
        }
    }

    private final static int parseCount(final CommandLine cmd)
            throws ParseException {
        int count = 10;
        if (cmd.hasOption(OPTION_COUNT)) {
            final String value = cmd.getOptionValue(OPTION_COUNT,
                    Integer.toString(Integer.MAX_VALUE));
            try {
                count = Integer.valueOf(value);
            } catch (NumberFormatException e) {
                throw new ParseException("Unable parse count value " + value);
            }
        }
        return count;
    }

    private final static void printHelp() {
        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar testit.jar",
                "Simple tool that show server latency by pinging it", options,
                null);
    }

}
