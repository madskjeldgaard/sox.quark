Sox {

    // Runs a sox command with all arguments in the list
    *run{|...argumentList|
        var cmd = "sox %".format(argumentList.join(" "));

        ^if(this.isInstalled, {
            "Running the following sox command: ".postln;
            cmd.postln;
            cmd.unixCmdGetStdOut;
        }, {
            "Sox is not installed".error;
        })
    }

    *normalize{|infile, outfile, level=(-0.1)|
        ^this.run(infile, outfile, "norm", level.asFloat)
    }

    *channels{|infile, outfile, channels=1|
        ^this.run(infile, outfile, "channels", channels.asInteger)
    }

    *rate{|infile, outfile, rate=48|
        ^this.run(infile, outfile, "rate", rate.asFloat.asString ++ "k")
    }

    *bits{|infile, outfile, bits=16|
        ^this.run(infile, outfile, "-b", bits.asInteger)
    }

    *reverse{|infile, outfile|
        ^this.run(infile, outfile, "reverse")
    }

    *trim{|infile, outfile, start="0:0", end="10:0"|
        ^this.run(infile, outfile, "trim", start, end)
    }

    *trimSilenceFromBeginning{|infile, outfile, aboveperiods=1, duration=0.1, threshold=1|
        ^this.run(
            infile,
            outfile,
            "silence",
            "%".format(aboveperiods.asInteger),
            "%".format(duration.asFloat),
            "%%".format(threshold.asInteger, "%")
        )
    }

    *trimSilenceFromBeginningAndEnd{|
        infile,
        outfile,
        aboveperiodsStart=1,
        durationStart=0.1,
        thresholdStart=1,
        aboveperiodsEnd=1,
        durationEnd=0.1,
        thresholdEnd=1|

        ^this.run(
            infile,
            outfile,
            "silence",
            "%".format(aboveperiodsStart.asInteger),
            "%".format(durationStart.asFloat),
            "%%".format(thresholdStart.asInteger, "%"),
            "%".format(aboveperiodsEnd.asInteger),
            "%".format(durationEnd.asFloat),
            "%%".format(thresholdEnd.asInteger, "%"),
        )
    }

    *splitBySilence{|infile,
        outfile,
        aboveperiodsStart=1,
        durationStart=0.1,
        thresholdStart=1,
        aboveperiodsEnd=1,
        durationEnd=0.1,
        thresholdEnd=1|

        ^this.run(
            infile,
            outfile,
            "silence",
            "%".format(aboveperiodsStart.asInteger),
            "%".format(durationStart.asFloat),
            "%%".format(thresholdStart.asInteger, "%"),
            "%".format(aboveperiodsEnd.asInteger),
            "%".format(durationEnd.asFloat),
            "%%".format(thresholdEnd.asInteger, "%"),
            ":",
            "newfile",
            ":",
            "restart"
        )
    }

    *isInstalled{
        var checkCmd = "command -v sox";
        ^checkCmd.systemCmd == 0
    }

}
